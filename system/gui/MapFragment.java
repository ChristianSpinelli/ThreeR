package krys.threer.system.gui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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
import krys.threer.user.dao.UserSession;
import krys.threer.user.dominio.User;



public class MapFragment extends Fragment {

    private GoogleMap map;
    private LatLng loc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_map, container, false);

        if(MapCheckInstance()){

            setUpMap();
        }

        setUpRecyclesInMap();

        return view;
    }


    private boolean MapCheckInstance() {
        boolean isCheked = false;
        if (map == null) {
            // coloca o mapa do xml em uma instancia.
            map = ((com.google.android.gms.maps.SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        }

        if(map != null) {
            isCheked = true;
        }

        return isCheked;
    }

    private void setUpMap() {
        // habilita o mapa pegar a localizacao do usuario.
        map.setMyLocationEnabled(true);

        //pega usuario logado
        UserSession session = new UserSession(getActivity());
        User user = session.getLoggedUser();

        // cria um objeto de localizacao com a latitude e longitude encontrada para ser nserido no mapa
        loc = new LatLng(user.getRecentlyAddres().getLatitude(), user.getRecentlyAddres().getLongitude());

        // escolhe o tipo do mapa
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // cria a camera na localizacao indicada com um zoom de 16 e faz a camera ir ate la
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(loc, 12);
        map.animateCamera(camera);
    }


    private void setUpRecyclesInMap() {
        RecycleStoreLocalDataBase recycleDataBase = new RecycleStoreLocalDataBase(getActivity());
        ArrayList<RecycleStore> recycleList = new ArrayList<>();
        recycleList = recycleDataBase.getRecycleStoreList();
        Intent intent = getActivity().getIntent();
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
        Uri uri = Uri.parse("android.resource://krys.threer/mipmap/" + selectCategory);
        Bitmap image = null;
        try {
            InputStream is = getActivity().getContentResolver().openInputStream(uri);
            image = BitmapFactory.decodeStream(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BitmapDescriptor icon = BitmapDescriptorFactory.fromBitmap(image);

        return icon;
    }
}
