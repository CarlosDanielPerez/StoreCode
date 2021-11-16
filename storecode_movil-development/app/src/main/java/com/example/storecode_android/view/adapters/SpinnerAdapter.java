package com.example.storecode_android.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.storecode_android.R;

import java.util.List;


public class SpinnerAdapter extends ArrayAdapter<Double> {
    LayoutInflater layoutInflater;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Double> stock) {
        super(context, resource, stock);
        layoutInflater= LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView =layoutInflater.inflate(R.layout.custom_spinner_adapter,null, true);
        Double item = getItem(position);
        TextView tvSpinnerItem = rowView.findViewById(R.id.tvSpinnerItem);
        Integer value= item.intValue();
        tvSpinnerItem.setText(value.toString());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
            convertView =layoutInflater.inflate(R.layout.custom_spinner_adapter,parent, false);

        Double item = getItem(position);
        TextView tvSpinnerItem = convertView.findViewById(R.id.tvSpinnerItem);
        Integer value= item.intValue();
        tvSpinnerItem.setText(value.toString());
        return convertView;
    }


}
