package krys.threer.RecycleStore.dao;

import android.os.AsyncTask;

import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.RecycleStore.negocio.GetRecylceCallback;
import krys.threer.system.dao.ServerRequest;

/**
 * Created by Krys on 25/10/2015.
 */
public class SetRecyleDataAsyncTask extends AsyncTask<Void,Void,Void> {

    private RecycleStore recycle;
    private GetRecylceCallback callback;

    public SetRecyleDataAsyncTask(RecycleStore recycle, GetRecylceCallback callback) {

        this.recycle  = recycle;
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
        dataToSend.add(new BasicNameValuePair("name",recycle.getName()));





        return null;
    }
}
