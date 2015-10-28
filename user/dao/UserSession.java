package krys.threer.user.dao;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.system.dominio.Addres;
import krys.threer.user.dominio.User;

/**
 * Created by Krys on 21/10/2015.
 */
public class UserSession {

    private static final String SP_NAME = "userDetails";
    private SharedPreferences userLocalDataBase;

    public UserSession(Context context){
        userLocalDataBase = context.getSharedPreferences(SP_NAME,0);
    }

    public void storeDataUser(User user){
        SharedPreferences.Editor editor = userLocalDataBase.edit();
        editor.putString("name",user.getName());
        editor.putString("password",user.getPassword());
        editor.putString("email", user.getEmail());

        editor.putString("street",user.getAddres().getStreet());
        editor.putString("number",user.getAddres().getNumber());
        editor.putString("burgh",user.getAddres().getBurgh());
        editor.putString("city",user.getAddres().getCity());
        editor.putString("state", user.getAddres().getState());
        editor.putString("country", user.getAddres().getCountry());
        editor.putFloat("latitude", (float) user.getAddres().getLatitude());
        editor.putFloat("longitude", (float) user.getAddres().getLongitude());

        if(user.getRecentlyAddres() !=null) {
            editor.putString("recentlyBurgh", user.getRecentlyAddres().getBurgh());
            editor.putString("recentlyCity", user.getRecentlyAddres().getCity());
            editor.putString("recentlyState", user.getRecentlyAddres().getState());
            editor.putString("recentlyCountry", user.getRecentlyAddres().getCountry());
            editor.putFloat("recentlyLatitude", (float) user.getRecentlyAddres().getLatitude());
            editor.putFloat("recentlyLongitude", (float) user.getRecentlyAddres().getLongitude());
        }
            editor.commit();
    }


    public User getLoggedUser(){
        String name = userLocalDataBase.getString("name", "");
        String password = userLocalDataBase.getString("password","");
        String email = userLocalDataBase.getString("email","");

        String street = userLocalDataBase.getString("street","");
        String number = userLocalDataBase.getString("number","");
        String burgh = userLocalDataBase.getString("burgh","");
        String city = userLocalDataBase.getString("city","");
        String state = userLocalDataBase.getString("state","");
        String country = userLocalDataBase.getString("country", "");
        double latitude = userLocalDataBase.getFloat("latitude", 0.f);
        double longitude = userLocalDataBase.getFloat("longitude", 0.f);

        Addres addres = new Addres(street,number,burgh,city,state,country,latitude,longitude);


        String recentlyBurgh = userLocalDataBase.getString("recentlyBurgh", "");
        String recentlyCity = userLocalDataBase.getString("recentlyCity", "");
        String recentlyState = userLocalDataBase.getString("recentlyState", "");
        String recentlyCountry = userLocalDataBase.getString("recentlyCountry", "");
        double recentlyLatitude = userLocalDataBase.getFloat("recentlyLatitude", 0.f);
        double recentlyLongitude = userLocalDataBase.getFloat("recentlyLongitude", 0.f);

        Addres recentlyAddres = new Addres(recentlyBurgh, recentlyCity, recentlyState,
                    recentlyCountry, recentlyLatitude, recentlyLongitude);


        User user = new User(name, password, email,addres,recentlyAddres);

        return user;

    }

    public void setUserLogged(boolean islogged){
        SharedPreferences.Editor editor = userLocalDataBase.edit();
        editor.putBoolean("loggedin",islogged);
        editor.commit();
    }

    public boolean isUserLoggedIn(){
        boolean islogged = false;
        if(userLocalDataBase.getBoolean("loggedin",false)){
            islogged =  true;
        }
        return islogged;

    }

    public void userClearAll(){
        SharedPreferences.Editor editor = userLocalDataBase.edit();
        editor.clear();
    }




}
