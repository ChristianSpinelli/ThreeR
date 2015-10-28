package krys.threer.user.dao;

import android.os.AsyncTask;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
import krys.threer.system.dao.ServerRequest;
import krys.threer.user.dominio.User;

import krys.threer.user.negocio.GetUserCallback;

/**
 * Created by Krys on 24/10/2015.
 */
public class SetUserAdressDataAsyncTask extends AsyncTask<Void,Void,Void> {

    private User user;
    private GetUserCallback callback;

    public SetUserAdressDataAsyncTask(User user, GetUserCallback callback){
        this.user = user;
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ServerRequest.progressDialog.dismiss();
        callback.done(null);
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {

        ArrayList<NameValuePair> dataToSend = new ArrayList<>();
        dataToSend.add(new BasicNameValuePair("email",user.getEmail()));
        dataToSend.add(new BasicNameValuePair("street",user.getAddres().getStreet()));
        dataToSend.add(new BasicNameValuePair("number", user.getAddres().getNumber()));
        dataToSend.add(new BasicNameValuePair("burgh", user.getAddres().getBurgh()));
        dataToSend.add(new BasicNameValuePair("city",user.getAddres().getCity()));
        dataToSend.add(new BasicNameValuePair("state", user.getAddres().getState()));
        dataToSend.add(new BasicNameValuePair("country", user.getAddres().getCountry()));


        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, ServerRequest.CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, ServerRequest.CONNECTION_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);

        HttpPost post = new HttpPost(ServerRequest.SERVER_ADRESS + "RegisterAddres.php");

        try {
            post.setEntity(new UrlEncodedFormEntity(dataToSend));
            httpClient.execute(post);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
