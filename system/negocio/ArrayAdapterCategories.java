package krys.threer.system.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import krys.threer.R;

/**
 * Created by Krys on 23/10/2015.
 */
public class ArrayAdapterCategories extends ArrayAdapter<String>{

    private ArrayList<String> categories = new ArrayList<String>();
    private ArrayList<Integer> images = new ArrayList<Integer>();
    private Context context;
    private LayoutInflater layouInflater;

    private final static int LAYOUT = R.layout.category_item;
    private final static int IMAGE = R.id.imgCategoryItem;
    private final static int TEXT = R.id.txtCategoryItem;

    public ArrayAdapterCategories(Context context, ArrayList<String> categories, ArrayList<Integer> images) {
        super(context,LAYOUT, categories);

        this.context = context;
        this.categories = categories;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            layouInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layouInflater.inflate(LAYOUT, null);
        }

        TextView txtCategoryItem;
        ImageView imgCategoryItem;

        txtCategoryItem = (TextView) convertView.findViewById(TEXT);
        imgCategoryItem = (ImageView) convertView.findViewById(IMAGE);

        txtCategoryItem.setText(categories.get(position));
        imgCategoryItem.setImageResource(images.get(position));


        return convertView;
    }
}
