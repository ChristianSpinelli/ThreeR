package krys.threer.system.gui;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import krys.threer.R;

public class ContactFragment extends Fragment {

    private EditText edtSubject,edtMessage;
    private Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact,container,false);

        edtSubject = (EditText) view.findViewById(R.id.edtSubject);
        edtMessage = (EditText) view.findViewById(R.id.edtMessage);
        btnSend = (Button) view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Subject = edtSubject.getText().toString();
                String Message = edtMessage.getText().toString();
            }
        });




        return view;
    }


}
