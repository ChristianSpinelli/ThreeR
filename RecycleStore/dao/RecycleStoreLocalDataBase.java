package krys.threer.RecycleStore.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.user.dominio.User;

/**
 * Created by Krys on 27/10/2015.
 */
public class RecycleStoreLocalDataBase {

    private static final String SP_NAME = "recycleStoreDetails";
    private SharedPreferences recycleStoreLocalDataBase;

    public RecycleStoreLocalDataBase(Context context){
       recycleStoreLocalDataBase = context.getSharedPreferences(SP_NAME,0);
    }

    public void setRecycleStoreList(ArrayList<RecycleStore> recycleList){
        SharedPreferences.Editor editor = recycleStoreLocalDataBase.edit();
        Gson gson = new Gson();
        String jsonRecycles = gson.toJson(recycleList);
        editor.putString("recycles",jsonRecycles);
        editor.commit();


    }

    public ArrayList<RecycleStore> getRecycleStoreList(){
        ArrayList<RecycleStore> recycleList = new ArrayList<>();
        String jsonRecycles = recycleStoreLocalDataBase.getString("recycles","");
        Gson gson = new Gson();
        recycleList = (ArrayList<RecycleStore>) gson.fromJson(jsonRecycles,
                new TypeToken<ArrayList<RecycleStore>>() {
                }.getType());

        return recycleList;
    }


}
