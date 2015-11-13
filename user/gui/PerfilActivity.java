package krys.threer.user.gui;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;

import krys.threer.R;
import krys.threer.RecycleStore.dao.RecycleStoreLocalDataBase;
import krys.threer.RecycleStore.domino.RecycleStore;
import krys.threer.system.gui.SelectCategoryActivity;
import krys.threer.user.dao.UserSession;
import krys.threer.user.dominio.User;


public class PerfilActivity extends ActionBarActivity implements View.OnClickListener {

    private ImageView imgPerfil;
    private EditText edtPerfilName, edtPerfilEmail, edtPerfilStreet, edtPerfilNumber
            ,edtPerfilBurgh,edtPerfilCity,edtPerfilState,edtPerfilCountry;
    private Button btnEdit, btnChangePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#238e23")));

        imgPerfil = (ImageView) findViewById(R.id.imgPerfil);
        edtPerfilName = (EditText) findViewById(R.id.edtPerfilName);
        edtPerfilEmail = (EditText) findViewById(R.id.edtPerfilEmail);
        edtPerfilStreet = (EditText) findViewById(R.id.edtPerfilStreet);
        edtPerfilNumber = (EditText) findViewById(R.id.edtPerfilNumber);
        edtPerfilBurgh = (EditText) findViewById(R.id.edtPerfilBurgh);
        edtPerfilCity = (EditText) findViewById(R.id.edtPerfilCity);
        edtPerfilState = (EditText) findViewById(R.id.edtPerfilState);
        edtPerfilCountry = (EditText) findViewById(R.id.edtPerfilCountry);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnChangePassword = (Button) findViewById(R.id.btnChangePassword);

        UserSession session = new UserSession(this);
        User user = session.getLoggedUser();
        setUserData(user);

        btnEdit.setOnClickListener(this);


    }

    private void setUserData(User user) {
        edtPerfilName.setText(user.getName());
        edtPerfilEmail.setText(user.getEmail());
        edtPerfilStreet.setText(user.getAddres().getStreet());
        edtPerfilNumber.setText(user.getAddres().getNumber());
        edtPerfilBurgh.setText(user.getAddres().getBurgh());
        edtPerfilCity.setText(user.getAddres().getCity());
        edtPerfilState.setText(user.getAddres().getState());
        edtPerfilCountry.setText(user.getAddres().getCountry());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.id_logout){
            UserSession session = new UserSession(this);
            session.userClearAll();
            session.setUserLogged(false);

            RecycleStoreLocalDataBase recycleStore = new RecycleStoreLocalDataBase(this);
            recycleStore.clearAll();

            finish();
            Intent intent = new Intent(PerfilActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(PerfilActivity.this, SelectCategoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
       int id = v.getId();
        switch (id){
            case R.id.btnEdit:
                if(btnEdit.getText() == getResources().getString(R.string.edit)) {
                    enableAllTexts();
                    showMessageDialog(getResources().getString(R.string.enable_text));
                    btnEdit.setText(getResources().getString(R.string.confirm));
                }

                else if(btnEdit.getText() == getResources().getString(R.string.confirm)){
                    updateUser();
                    disableAllTexts();
                    showMessageDialog(getResources().getString(R.string.confirm_alterations));
                    btnEdit.setText(getResources().getString(R.string.edit));
                }
                break;
        }

    }

    private void updateUser() {
        //TODO
    }

    private void disableAllTexts() {
        edtPerfilName.setEnabled(false);
        edtPerfilEmail.setEnabled(false);
        edtPerfilStreet.setEnabled(false);
        edtPerfilNumber.setEnabled(false);
        edtPerfilBurgh.setEnabled(false);
        edtPerfilCity.setEnabled(false);
        edtPerfilState.setEnabled(false);
        edtPerfilCountry.setEnabled(false);
    }

    private void showMessageDialog(String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(getResources().getString(R.string.ok), null);
        alertDialog.show();

    }


    private void enableAllTexts() {
        edtPerfilName.setEnabled(true);
        edtPerfilEmail.setEnabled(true);
        edtPerfilStreet.setEnabled(true);
        edtPerfilNumber.setEnabled(true);
        edtPerfilBurgh.setEnabled(true);
        edtPerfilCity.setEnabled(true);
        edtPerfilState.setEnabled(true);
        edtPerfilCountry.setEnabled(true);
    }
}
