package krys.threer.user.gui;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import krys.threer.R;
import krys.threer.system.dominio.Addres;
import krys.threer.user.dao.UserDao;
import krys.threer.user.dominio.User;
import krys.threer.user.negocio.GetUserCallback;


public class RegisterActivity extends ActionBarActivity {

    private Button btnConfirm, btnCancel;
    private EditText edtName, edtRegisterPassword, edtRegisterEmail, edtRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        edtRegisterEmail = (EditText) findViewById(R.id.edtRegisterEmail);
        edtRegisterPassword = (EditText) findViewById(R.id.edtRegisterPassword);
        edtRepeatPassword = (EditText) findViewById(R.id.edtRepeatPassword);
        edtName = (EditText) findViewById((R.id.edtName));

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String password = edtRegisterPassword.getText().toString();
                String repeatPassword = edtRepeatPassword.getText().toString();
                String email = edtRegisterEmail.getText().toString();

                User user = new User(name,password,email);

                registerUser(user);

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerUser(User user) {
        UserDao userDao = new UserDao(this);
        userDao.setUser(user, new GetUserCallback() {
            @Override
            public void done(User user) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showMessageDialog() {
        Toast toast = Toast.makeText(this,"Usuario cadastrado com sucesso",Toast.LENGTH_LONG);
        toast.show();
    }

}
