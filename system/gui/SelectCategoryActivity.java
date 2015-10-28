package krys.threer.system.gui;


import android.app.AlertDialog;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView;


import java.util.ArrayList;

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

                User user = session.getLoggedUser();

                final String selectCategory = categories.get(position);

                recycleDao.getRecycleList(selectCategory,user.getAddres(), new GetRecylceCallback() {
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
                            showMessageDialog(selectCategory);
                        }



                    }
                });


            }
        });

    }

    private void showMessageDialog(String categoria) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Nao ha nenhum ponto de "+categoria+" na sua cidade.");
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
