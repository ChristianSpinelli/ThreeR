package krys.threer.RecycleStore.domino;

import java.util.ArrayList;

import krys.threer.system.dominio.Addres;

/**
 * Created by Krys on 24/10/2015.
 */
public class RecycleStore {

    private String name;
    private Addres addres;
    private ArrayList<String> categories;

    public RecycleStore(String name, Addres addres, ArrayList<String> categories){

        this.name = name;
        this.addres = addres;
        this.categories = categories;

    }

    public RecycleStore(String name, ArrayList<String> categories){

        this.name = name;
        this.categories = categories;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Addres getAddres() {
        return addres;
    }

    public void setAddres(Addres addres) {
        this.addres = addres;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
}
