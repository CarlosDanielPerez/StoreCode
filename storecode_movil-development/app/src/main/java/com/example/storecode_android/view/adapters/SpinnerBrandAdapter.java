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
import com.example.storecode_android.entidades.Brand;
import com.example.storecode_android.entidades.Category;

import java.util.List;

public class SpinnerBrandAdapter extends ArrayAdapter<Brand> {
    LayoutInflater layoutInflater;

    public SpinnerBrandAdapter(@NonNull Context context, int resource, @NonNull List<Brand> brands) {
        super(context, resource, brands);
        layoutInflater= LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView =layoutInflater.inflate(R.layout.category_spinner_adapter,null, true);
        Brand item = getItem(position);
        TextView tvSpinnerItem = rowView.findViewById(R.id.tvSpinnerItem);

        tvSpinnerItem.setText(item.getDesMarca());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null)
            convertView =layoutInflater.inflate(R.layout.custom_spinner_adapter,parent, false);

        Brand item = getItem(position);
        TextView tvSpinnerItem = convertView.findViewById(R.id.tvSpinnerItem);
        tvSpinnerItem.setText(item.getDesMarca());
        return convertView;
    }
}
