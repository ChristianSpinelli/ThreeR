package krys.threer.RecycleStore.dao;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

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
import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.RecycleStore.negocio.GetRecylceCallback;
import krys.threer.system.dao.ServerRequest;
import krys.threer.system.dominio.Addres;
import krys.threer.user.dominio.User;

/**
 * Created by Krys on 25/10/2015.
 */
public class GetRecycleDataAsyncTask extends AsyncTask<Void,Void,ArrayList<RecycleStore>>  {

    private Addres userAddres;
    private GetRecylceCallback callback;
    private String categoria;

    public GetRecycleDataAsyncTask( String categoria, Addres userAddres, GetRecylceCallback callback) {
        this.userAddres=userAddres;
        this.callback=callback;
        this.categoria=categoria;
    }

    @Override
    protected void onPostExecute(ArrayList<RecycleStore> recycleStores) {
        ServerRequest.progressDialog.dismiss();
        callback.done(recycleStores);
        super.onPostExecute(recycleStores);
    }

    @Override
    protected ArrayList<RecycleStore> doInBackground(Void... params) {

        ArrayList<NameValuePair> dataToSend = new ArrayList<>();
        dataToSend.add(new BasicNameValuePair("city", userAddres.getCity()));

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, ServerRequest.CONNECTION_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, ServerRequest.CONNECTION_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(ServerRequest.SERVER_ADRESS + "GetRecyclesCity.php");

       ArrayList<RecycleStore> recycleList = new ArrayList<>();

        try {
            post.setEntity(new UrlEncodedFormEntity(dataToSend));
            HttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(result);

            if(jsonObject.length() == 0){
               recycleList = null;
            }else {
                String name = null, categorie = null, street = null, number=null,burgh=null,
                        city=null, state=null, country = null;
                double latitude = 0, longitude=0;
                ArrayList<String> categories = null;

                JSONArray recycles = jsonObject.getJSONArray("recycles");

                recycleList = new ArrayList<RecycleStore>();

               for (int i =0; i<=recycles.length();i++) {
                   name = recycles.getJSONObject(i).getString("name");
                   categorie = recycles.getJSONObject(i).getString("categories");

                   street = recycles.getJSONObject(i).getString("street");
                   number = recycles.getJSONObject(i).getString("number");
                   burgh = recycles.getJSONObject(i).getString("burgh");
                   city = recycles.getJSONObject(i).getString("city");
                   state = recycles.getJSONObject(i).getString("state");
                   country = recycles.getJSONObject(i).getString("country");
                   latitude = recycles.getJSONObject(i).getDouble("latitude");
                   longitude = recycles.getJSONObject(i).getDouble("longitude");

                   Addres addres = new Addres(street, number,burgh,city,state,country,latitude,longitude);



                   categories = new ArrayList<String>(Arrays.asList(categorie.split(",")));
                   for (int j = 0; j <categories.size() ; j++) {

                       if (categories.get(j).toLowerCase().equals(this.categoria.toLowerCase())){
                           RecycleStore recycle = new RecycleStore(name,addres,categories);
                           recycleList.add(recycle);
                       }

                   }

                }

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


        return recycleList;
    }
}
