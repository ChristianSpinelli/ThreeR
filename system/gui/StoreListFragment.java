package krys.threer.system.gui;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import krys.threer.R;
import krys.threer.RecycleStore.dao.RecycleStoreLocalDataBase;
import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.system.negocio.ArrayAdapterRecycles;

public class StoreListFragment extends Fragment {

    private ListView lstRecycleItem;
    private ArrayList<Uri> images = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>(),streets = new ArrayList<>(),
    numbers = new ArrayList<>(),burghs = new ArrayList<>(),citys = new ArrayList<>(),
            states = new ArrayList<>(),countrys = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_list, container,false);

        if(checkInstance(view)){
            setUpResources();

            ArrayAdapterRecycles adapter = new ArrayAdapterRecycles(getActivity(),images,names,streets,
                    numbers,burghs,citys,states,countrys);

            lstRecycleItem.setAdapter(adapter);

        }


        return view;

    }

    private boolean checkInstance(View view) {
        boolean isChecked = false;
        if(lstRecycleItem==null){
            lstRecycleItem = (ListView) view.findViewById(R.id.lstRecycleItem);
        }

        if(lstRecycleItem != null){
            isChecked=true;
        }


        return isChecked;

    }

    private void setUpResources() {
        RecycleStoreLocalDataBase recycleDataBase = new RecycleStoreLocalDataBase(getActivity());
        ArrayList<RecycleStore> recycles = recycleDataBase.getRecycleStoreList();

        Intent intent = getActivity().getIntent();
        String selectCategory = intent.getExtras().getString("selectCategory");
        selectCategory = selectCategory.replaceAll(" ", "_")
                .replaceAll(getResources().getString(R.string.excepcional_a_1), "a")
                .replaceAll(getResources().getString(R.string.excepcional_a_2), "a")
                .replaceAll(getResources().getString(R.string.excepcional_o_1), "O").toLowerCase();
        Uri uri = Uri.parse("android.resource://krys.threer/mipmap/" + selectCategory);


        for (int i = 0; i <recycles.size() ; i++) {
            images.add(uri);
            names.add(recycles.get(i).getName());
            streets.add(recycles.get(i).getAddres().getStreet());
            numbers.add(recycles.get(i).getAddres().getNumber());
            burghs.add(recycles.get(i).getAddres().getBurgh());
            citys.add(recycles.get(i).getAddres().getCity());
            states.add(recycles.get(i).getAddres().getState());
            countrys.add(recycles.get(i).getAddres().getCountry());

        }

    }



}
