package krys.threer.user.dominio;

import krys.threer.system.dominio.Addres;

/**
 * Created by Krys on 21/10/2015.
 */
public class User {

    private String name, password, email;
    private Addres addres, recentlyAddres;


    public User(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String password, String email,Addres addres){
        this.name = name;
        this.password = password;
        this.email = email;
        this.addres = addres;
    }

    public User(String email, Addres addres){
        this.email = email;
        this.addres = addres;
    }

    public  User(){

    }

    public User(String name, String password, String email,Addres addres,Addres recentlyAddres){
        this.name = name;
        this.password = password;
        this.email = email;
        this.addres = addres;
        this.recentlyAddres = recentlyAddres;
    }

    public Addres getRecentlyAddres() {
        return recentlyAddres;
    }

    public void setRecentlyAddres(Addres recentlyAddres) {
        this.recentlyAddres = recentlyAddres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Addres getAddres() {
        return addres;
    }

    public void setAddres(Addres addres) {
        this.addres = addres;
    }
}
