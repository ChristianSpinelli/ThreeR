package krys.threer.user.dao;

import android.content.Context;

import krys.threer.user.negocio.GetUserCallback;
import krys.threer.system.dao.ServerRequest;
import krys.threer.user.dominio.User;

/**
 * Created by Krys on 22/10/2015.
 */
public class UserDao extends ServerRequest {

    public UserDao(Context context) {
        super(context);
    }

    public void setUser(User user, GetUserCallback userCallback){
        super.progressDialog.show();
        new StoreUserDataAsyncTask(user,userCallback).execute();
    }

    public void getUser(User user, GetUserCallback userCallback){
        super.progressDialog.show();
        new GetUserDataAsyncTask(user, userCallback).execute();
    }

    public void setUserAdress(User user, GetUserCallback callback){
        super.progressDialog.show();
        new SetUserAdressDataAsyncTask(user,callback).execute();

    }
}
