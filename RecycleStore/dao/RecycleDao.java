package krys.threer.RecycleStore.dao;

import android.content.Context;

import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.RecycleStore.negocio.GetRecylceCallback;
import krys.threer.system.dao.ServerRequest;
import krys.threer.system.dominio.Addres;

/**
 * Created by Krys on 24/10/2015.
 */
public class RecycleDao extends ServerRequest {

    public RecycleDao(Context context) {
        super(context);
    }

    public void setRecycle(RecycleStore recycle, GetRecylceCallback recylceCallback){
        ServerRequest.progressDialog.show();
        new SetRecyleDataAsyncTask(recycle, recylceCallback).execute();

    }

    public void getRecycleList(String categoria,Addres userAddres, GetRecylceCallback recylceCallback){
        ServerRequest.progressDialog.show();
        new GetRecycleDataAsyncTask(categoria,userAddres,recylceCallback).execute();

    }
}
