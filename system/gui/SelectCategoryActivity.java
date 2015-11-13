package krys.threer.system.gui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import krys.threer.user.gui.LoginActivity;
import krys.threer.user.gui.PerfilActivity;


public class SelectCategoryActivity extends ActionBarActivity {

    private ListView lstCategories;
    private ArrayList<String> categories = new ArrayList<String>();
    private ArrayList<Integer> images = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#238e23")));

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

                final User user = session.getLoggedUser();

                setUserRecentlyLocation(user);

                session.storeDataUser(user);


                final String selectCategory = categories.get(position);

                recycleDao.getRecycleList(selectCategory, user.getRecentlyAddres(), new GetRecylceCallback() {
                    @Override
                    public void done(ArrayList<RecycleStore> recycleList) {
                        if (recycleList.size() > 0) {
                            RecycleStoreLocalDataBase recycleDataBase = new RecycleStoreLocalDataBase(SelectCategoryActivity.this);
                            recycleDataBase.setRecycleStoreList(recycleList);

                            finish();
                            Intent intent = new Intent(SelectCategoryActivity.this, TabbedActivity.class);
                            intent.putExtra("selectCategory", selectCategory);
                            startActivity(intent);
                        } else {
                            showMessageDialog(selectCategory, user.getRecentlyAddres().getCity());
                        }


                    }
                });


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_threer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.id_logout){
            UserSession session = new UserSession(this);
            RecycleStoreLocalDataBase recycleStore = new RecycleStoreLocalDataBase(this);

            session.setUserLogged(false);
            session.userClearAll();
            recycleStore.clearAll();

            finish();
            Intent intent = new Intent(SelectCategoryActivity.this, LoginActivity.class);
            startActivity(intent);

        }

        if(item.getItemId() == R.id.id_perfil){
            finish();
            Intent intent = new Intent(SelectCategoryActivity.this, PerfilActivity.class);
            startActivity(intent);
        }


        return true;
    }

    private void setUserRecentlyLocation(User user) {
        // cria o gerenciado de localizacoes para ver as localizacoes do sistema
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // usa o gerenciador para pegar a ultima localizacao do aparelho
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses = new ArrayList<Address>();

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String city = addresses.get(1).getLocality();

        Addres addres = new Addres(null, city, null, null, latitude, longitude);


        user.setRecentlyAddres(addres);

    }

    private void showMessageDialog(String categoria, String city) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(getResources().getString(R.string.recycle_not_found)+" de "+categoria+" em "+city);
        alertDialog.setPositiveButton(getResources().getString(R.string.ok), null);
        alertDialog.show();

    }

    private void setUpResources() {

        categories.add(getResources().getString(R.string.category_batery));
        images.add(R.drawable.pilha_e_bateria);

        categories.add(getResources().getString(R.string.category_metal));
        images.add(R.drawable.metal);

        categories.add(getResources().getString(R.string.category_glass));
        images.add(R.drawable.vidro);

        categories.add(getResources().getString(R.string.category_plastic));
        images.add(R.drawable.plastico);

        categories.add(getResources().getString(R.string.category_paper));
        images.add(R.drawable.papel_e_papelao);

        categories.add(getResources().getString(R.string.category_cloth));
        images.add(R.drawable.roupa);

        categories.add(getResources().getString(R.string.category_book));
        images.add(R.drawable.livro);

        categories.add(getResources().getString(R.string.category_wood));
        images.add(R.drawable.madeira);

        categories.add(getResources().getString(R.string.category_oil));
        images.add(R.drawable.oleo);

        categories.add(getResources().getString(R.string.category_print));
        images.add(R.drawable.cartucho_de_impressora);

    }

}
