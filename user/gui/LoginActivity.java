package krys.threer.user.gui;


import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import krys.threer.R;
import krys.threer.system.gui.SelectCategoryActivity;
import krys.threer.user.dao.UserDao;
import krys.threer.user.dao.UserSession;
import krys.threer.user.dominio.User;
import krys.threer.user.negocio.GetUserCallback;


public class LoginActivity extends ActionBarActivity {

    private Button btnLogin;
    private TextView txtRegister;
    private UserSession userSession;
    private EditText edtEmail, edtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hideActionBar();

        txtRegister = (TextView) findViewById(R.id.txtRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);


        userSession = new UserSession(this);


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(v.getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                User user = new User(null, password, email);

                authenticate(user);

            }
        });


    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    private void authenticate(User user) {
        UserDao userDao = new UserDao(this);
        userDao.getUser(user, new GetUserCallback() {
            @Override
            public void done(User user) {
                if (user == null) {
                    showMessageDialog(getResources().getString(R.string.user_not_found));
                } else {
                    logUser(user);
                }
            }
        });
    }

    private void logUser(User user) {
        userSession.storeDataUser(user);
        userSession.setUserLogged(true);
        finish();
        Intent intent = new Intent(this, SelectCategoryActivity.class);
        startActivity(intent);
    }

    private void showMessageDialog(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(getResources().getString(R.string.ok), null);
        alertDialog.show();
    }






}
