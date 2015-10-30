package krys.threer.user.gui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import krys.threer.R;
import krys.threer.system.dominio.Addres;
import krys.threer.user.dao.UserDao;
import krys.threer.user.dao.UserSession;
import krys.threer.user.dominio.User;
import krys.threer.user.negocio.GetUserCallback;

public class RegisterAddresActivity extends ActionBarActivity {

    private Button btnBack, btnJump, btnConfirmAddres;
    private EditText edtStreet, edtNumber, edtBurgh, edtCity, edtState, edtCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_addres);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnJump = (Button) findViewById(R.id.btnJump);
        btnConfirmAddres = (Button) findViewById(R.id.btnConfirmAddres);

        edtStreet = (EditText) findViewById(R.id.edtStreet);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtBurgh = (EditText) findViewById(R.id.edtBurgh);
        edtCity = (EditText) findViewById(R.id.edtCity);
        edtState = (EditText) findViewById(R.id.edtState);
        edtCountry = (EditText) findViewById(R.id.edtCountry);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(v.getContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btnConfirmAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();

                String email = (String) intent.getExtras().get("email");

                String street = edtStreet.getText().toString();
                String number = edtNumber.getText().toString();
                String burgh = edtBurgh.getText().toString();
                String city = edtCity.getText().toString();
                String state = edtState.getText().toString();
                String country = edtCountry.getText().toString();

                User user = new User();
                user.setEmail(email);

                Addres addres = new Addres(street,number,burgh,city,state,country);

                user.setAddres(addres);

                registerUser(user);




            }
        });



    }

    private void registerUser(User user) {
        final UserDao userDao = new UserDao(this);
        userDao.setUserAdress(user, new GetUserCallback() {
            @Override
            public void done(User user) {
                finish();
                Intent intent = new Intent(RegisterAddresActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }


}
