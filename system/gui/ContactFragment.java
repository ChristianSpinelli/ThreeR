package krys.threer.system.gui;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import krys.threer.R;


public class ContactFragment extends Fragment implements View.OnClickListener {

    private EditText edtSubject,edtMessage;
    private Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        edtSubject = (EditText) view.findViewById(R.id.edtSubject);
        edtMessage = (EditText) view.findViewById(R.id.edtMessage);
        btnSend = (Button) view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnSend:
                String subject = edtSubject.getText().toString();
                String message = edtMessage.getText().toString();

                sendMessage(message,subject);

                edtSubject.setText("");
                edtMessage.setText("");
                break;

        }

    }

    private void sendMessage(String message, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setData(Uri.parse("mailto:contact.appgrade@gmail.com"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
