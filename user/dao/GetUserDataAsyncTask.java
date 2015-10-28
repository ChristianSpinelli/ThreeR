package krys.threer.user.dao;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.util.EntityUtils;

import krys.threer.system.dominio.Addres;
import krys.threer.user.negocio.GetUserCallback;
import krys.threer.system.dao.ServerRequest;
import krys.threer.user.dominio.User;

/**
 * Created by Krys on 22/10/2015.
 */
public class GetUserDataAsyncTask extends AsyncTask<Void,Void,User> {

    private User user;
    private GetUserCallback userCallback;

    public GetUserDataAsyncTask(User user,GetUserCallback userCallback){
        this.user = user;
        this.userCallback = userCallback;
    }

    @Override
    protected void onPostExecute(User user) {
        ServerRequest.progressDialog.dismiss();
        userCallback.done(user);
        super.onPostExecute(user);
    }

    @Override
    protected User doInBackground(Void... params) {
        ArrayList<NameValuePair> dataToSend = new ArrayList<>();
        dataToSend.add(new BasicNameValuePair("password", user.getPassword()));
        dataToSend.add(new BasicNameValuePair("email", user.getEmail()));

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, ServerRequest.CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, ServerRequest.CONNECTION_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(ServerRequest.SERVER_ADRESS + "fetchUserData.php");


        User user = null;

        try {
            post.setEntity(new UrlEncodedFormEntity(dataToSend));
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(result);

            if(jsonObject.length() == 0){
                user = null;
            }else {
                String name = jsonObject.getString("name");
                String street = jsonObject.getString("street");
                String number = jsonObject.getString("number");
                String burgh = jsonObject.getString("burgh");
                String city = jsonObject.getString("city");
                String state = jsonObject.getString("state");
                String country = jsonObject.getString("country");
                double latitude = jsonObject.getDouble("latitude");
                double longitude = jsonObject.getDouble("longitude");

                Addres addres = new Addres(street,number,burgh,city,state, country,latitude, longitude);


                user = new User(name,this.user.getPassword(),this.user.getEmail(),addres);
            }



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return user;
    }


}
