package krys.threer.system.dao;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Krys on 22/10/2015.
 */


import krys.threer.R;
public class ServerRequest {

    public static ProgressDialog progressDialog;
    public final static int CONNECTION_TIMEOUT = 1000 * 15;
    public final static String SERVER_ADRESS = "http://threer.esy.es/";


    public ServerRequest(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(context.getResources().getString(R.string.processing));
        progressDialog.setMessage(context.getResources().getString(R.string.please_wait));
    }


}
