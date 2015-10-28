package krys.threer.system.gui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import krys.threer.R;
import krys.threer.RecycleStore.dao.RecycleDao;
import krys.threer.RecycleStore.dao.RecycleStoreLocalDataBase;
import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.RecycleStore.negocio.GetRecylceCallback;

import krys.threer.system.dominio.Addres;
import krys.threer.system.negocio.ArrayAdapterCategories;
import krys.threer.user.dao.UserSession;
import krys.threer.user.dominio.User;


public class SelectCategoryActivity extends ActionBarActivity {

    private ListView lstCategories;
    private ArrayList<String> categories = new ArrayList<String>();
    private ArrayList<Integer> images = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        lstCategories = (ListView) findViewById(R.id.lstCategories);

        setUpResources();

    }

    @Override
    protected void onStart() {
        super.onStart();

        ArrayAdapterCategories adapter = new ArrayAdapterCategories(this,categories,images);
        lstCategories.setAdapter(adapter);

        lstCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                RecycleDao recycleDao = new RecycleDao(view.getContext());

                UserSession session = new UserSession(view.getContext());



                // cria o gerenciado de localizacoes para ver as localizacoes do sistema
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                // usa o gerenciador para pegar a ultima localizacao do aparelho
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Geocoder geocoder = new Geocoder(view.getContext());
                List<Address> addresses = new ArrayList<Address>();

                try {
                    addresses = geocoder.getFromLocation(latitude,longitude,2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final String city = addresses.get(1).getLocality();

                Addres addres = new Addres(null,city,null,null,latitude, longitude);

                User user = session.getLoggedUser();
                user.setRecentlyAddres(addres);

                session.storeDataUser(user);


                final String selectCategory = categories.get(position);

                recycleDao.getRecycleList(selectCategory,user.getRecentlyAddres(), new GetRecylceCallback() {
                    @Override
                    public void done(ArrayList<RecycleStore> recycleList) {
                        if(recycleList.size() >0) {
                            RecycleStoreLocalDataBase recycleDataBase = new RecycleStoreLocalDataBase(SelectCategoryActivity.this);
                            recycleDataBase.setRecycleStoreList(recycleList);

                            finish();
                            Intent intent = new Intent(SelectCategoryActivity.this, MainActivity.class);
                            intent.putExtra("selectCategory",selectCategory);
                            startActivity(intent);
                        }else{
                            showMessageDialog(selectCategory,city);
                        }



                    }
                });


            }
        });

    }

    private void showMessageDialog(String categoria, String city) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Nao ha nenhum ponto de "+categoria+" em "+city);
        alertDialog.setPositiveButton("OK", null);
        alertDialog.show();

    }

    private void setUpResources() {

        categories.add("Pilha e Bateria");
        images.add(R.drawable.pilha);

        categories.add("Metal");
        images.add(R.drawable.metal);

        categories.add("Vidro");
        images.add(R.drawable.vidro);

        categories.add("Plastico");
        images.add(R.drawable.plastico);

        categories.add("Papel e Papelao");
        images.add(R.drawable.papel);

        categories.add("Roupa");
        images.add(R.drawable.roupas);

        categories.add("Livro");
        images.add(R.drawable.livro);

        categories.add("Madeira");
        images.add(R.drawable.madeira);

        categories.add("Oleo");
        images.add(R.drawable.oleo);

        categories.add("Cartucho de impressora");
        images.add(R.drawable.cartucho_impressora);

    }

}
