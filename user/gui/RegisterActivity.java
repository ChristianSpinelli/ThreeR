package krys.threer.user.gui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Telephony;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import krys.threer.R;
import krys.threer.system.dominio.Addres;
import krys.threer.user.dao.UserDao;
import krys.threer.user.dao.UserSession;
import krys.threer.user.dominio.User;
import krys.threer.user.negocio.GetUserCallback;


public class RegisterActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnConfirm, btnCancel;
    private EditText edtName, edtRegisterPassword, edtRegisterEmail, edtRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#238e23")));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        edtRegisterEmail = (EditText) findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = (EditText) findViewById(R.id.edtRegisterPassword);
        edtRepeatPassword = (EditText) findViewById(R.id.edtRepeatPassword);
        edtName = (EditText) findViewById((R.id.edtName));

        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnConfirm:
                String name = edtName.getText().toString();
                String password = edtRegisterPassword.getText().toString();
                String repeatPassword = edtRepeatPassword.getText().toString();
                String email = edtRegisterEmail.getText().toString();

                User user = new User(name,password,email);

                registerUser(user);
                break;
            case R.id.btnCancel:
                finish();
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void registerUser(final User registerUser) {
        UserDao userDao = new UserDao(this);

        userDao.setUser(registerUser, new GetUserCallback() {
            @Override
            public void done(User user) {
                finish();
                Intent intent = new Intent(RegisterActivity.this,RegisterAddresActivity.class);
                intent.putExtra("email", registerUser.getEmail());
                startActivity(intent);

            }
        });

    }


}
