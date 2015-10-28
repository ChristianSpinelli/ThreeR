package krys.threer.system.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;

import android.net.Uri;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;




import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import krys.threer.R;
import krys.threer.RecycleStore.dao.RecycleStoreLocalDataBase;
import krys.threer.RecycleStore.domino.RecycleStore;


public class MainActivity extends ActionBarActivity {

    private GoogleMap map;
    private  LatLng loc;
    private Button btnChangeCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChangeCategory = (Button) findViewById(R.id.btnCategory);

        btnChangeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(v.getContext(),SelectCategoryActivity.class);
                startActivity(intent);
            }
        });

        if(MapCheckInstance()){
            setUpMap();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        setUpRecyclesInMap();

    }

    private void setUpRecyclesInMap() {
        RecycleStoreLocalDataBase recycleDataBase = new RecycleStoreLocalDataBase(MainActivity.this);
        ArrayList<RecycleStore> recycleList = new ArrayList<>();
        recycleList = recycleDataBase.getRecycleStoreList();
        Intent intent = getIntent();
        String selectCategory = intent.getExtras().getString("selectCategory");
        BitmapDescriptor icon = parseCategoryToIcon(selectCategory);

       for (int i = 0; i <recycleList.size() ; i++) {
           double latitude = recycleList.get(i).getAddres().getLatitude();
           double longitude = recycleList.get(i).getAddres().getLongitude();
           String name = recycleList.get(i).getName();

           map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                   .title(name)
                   .icon(icon));
       }


    }

    private BitmapDescriptor parseCategoryToIcon(String selectCategory) {
        selectCategory = selectCategory.replaceAll(" ","_").toLowerCase();
        Uri uri = Uri.parse("android.resource://krys.threer/mipmap/"+selectCategory);
        Bitmap image = null;
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            image = BitmapFactory.decodeStream(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(image);

        return icon;
    }

    private boolean MapCheckInstance() {
        boolean isCheked = false;
        if (map == null) {
            // coloca o mapa do xml em uma instancia.
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        }

        if(map != null) {
            isCheked = true;
        }

        return isCheked;
    }

    private void setUpMap() {
        // habilita o mapa pegar a localizacao do usuario.
        map.setMyLocationEnabled(true);

        // cria o gerenciado de localizacoes para ver as localizacoes do sistema
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // usa o gerenciador para pegar a ultima localizacao do aparelho
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        // cria um objeto de localizacao com a latitude e longitude encontrada para ser nserido no mapa
        loc = new LatLng(location.getLatitude(), location.getLongitude());

        // escolhe o tipo do mapa
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // cria a camera na localizacao indicada com um zoom de 16 e faz a camera ir ate la
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(loc, 16);
        map.animateCamera(camera);


    }

    private void showMessageDialog(ArrayList<RecycleStore> recyleList) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("latitude: " + recyleList.get(0).getAddres().getLatitude());
        alertDialog.setPositiveButton("OK", null);
        alertDialog.show();


    }

}
