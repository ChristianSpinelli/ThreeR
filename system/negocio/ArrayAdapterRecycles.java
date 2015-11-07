package krys.threer.system.negocio;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import krys.threer.R;

/**
 * Created by Krys on 06/11/2015.
 */
public class ArrayAdapterRecycles extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<Uri> images = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>(), streets = new ArrayList<>(),
            numbers = new ArrayList<>(), burghs = new ArrayList<>(), citys = new ArrayList<>(),
            states = new ArrayList<>(), countrys = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private final static int LAYOUT = R.layout.recycle_store_item;
    private final static int IMAGE = R.id.imgRecycleCategory;
    private final static int RECYCLE_NAME = R.id.txtRecycleName;
    private final static int RECYCLE_STREET = R.id.txtRecycleStreet;
    private final static int RECYCLE_NUMBER = R.id.txtRecycleNumber;
    private final static int RECYCLE_BURGH= R.id.txtRecycleBurgh;
    private final static int RECYCLE_CITY = R.id.txtRecycleCity;
    private final static int RECYCLE_STATE = R.id.txtRecycleState;
    private final static int RECYCLE_COUNTRY = R.id.txtRecycleCountry;




    public ArrayAdapterRecycles(Context context, ArrayList<Uri> images, ArrayList<String> names,
                                ArrayList<String> streets, ArrayList<String> numbers,
                                ArrayList<String> burghs, ArrayList<String> citys,
                                ArrayList<String> states, ArrayList<String> countrys) {
        super(context,LAYOUT,names);

        this.context = context;
        this.images = images;
        this.names = names;
        this.streets = streets;
        this.numbers = numbers;
        this.burghs = burghs;
        this.citys = citys;
        this.states = states;
        this.countrys = countrys;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(LAYOUT, null);
        }

        ImageView imgRecycleItem = (ImageView) convertView.findViewById(IMAGE);
        TextView name = (TextView) convertView.findViewById(RECYCLE_NAME);
        TextView street = (TextView) convertView.findViewById(RECYCLE_STREET);
        TextView burgh = (TextView) convertView.findViewById(RECYCLE_BURGH);
        TextView city = (TextView) convertView.findViewById(RECYCLE_CITY);
        TextView state = (TextView) convertView.findViewById(RECYCLE_STATE);
        TextView number = (TextView) convertView.findViewById(RECYCLE_NUMBER);
        TextView country = (TextView) convertView.findViewById(RECYCLE_COUNTRY);

        name.setText(names.get(position));
        street.setText(streets.get(position));
        number.setText(numbers.get(position));
        city.setText(citys.get(position));
        state.setText(states.get(position));
        burgh.setText(burghs.get(position));
        country.setText(countrys.get(position));
        imgRecycleItem.setImageURI(images.get(position));
        return convertView;
    }
}
