package com.chris.parkingandroidapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisantoinette on 11/11/17.
 */

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the animal images
    //private final Integer[] imageIDarray;

    //to store the list of countries
    private List<String> parkingNames_= new ArrayList<String>();

    //to store the list of countries
    private List<String> parkingAddresses_ = new ArrayList<String>();
    private final double[] parkingDistances_;
    public CustomListAdapter(Activity context, List<String> parkingNames,
                             List<String> parkingAddresses, double[] parkingDistances)
    {
        super(context, R.layout.listview_row , parkingNames.toArray(new String[0]));

        this.context=context;
        //this.imageIDarray = imageIDArrayParam;
        this.parkingNames_ = parkingNames;
        this.parkingAddresses_ = parkingAddresses;
        this.parkingDistances_ = parkingDistances;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView nameTextField = (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewID);
        TextView distanceField = (TextView) rowView.findViewById(R.id.distanceId);
        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(parkingNames_.get(position));
        infoTextField.setText(parkingAddresses_.get(position));
        //imageView.setImageResource(imageIDarray[position]);
        distanceField.setText(parkingDistances_[position]+"m");
        return rowView;

    };
}
