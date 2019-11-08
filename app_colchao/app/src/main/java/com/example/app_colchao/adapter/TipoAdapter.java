package com.example.app_colchao.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import com.example.app_colchao.R;
import com.example.app_colchao.Model.Produto;
import com.example.app_colchao.Model.Tipo;

public class TipoAdapter extends ArrayAdapter<Tipo> {

    private int rId;

    public TipoAdapter(Context context, int resource,List<Tipo> objects) {
        super(context, resource, objects);
        rId = resource;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        /*View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(rId,null);
        }*/

        Tipo tipo = getItem(position);

        TextView textTipo = (TextView)super.getView(position,currentView,parent);
        textTipo.setText(tipo.getNome());

        return textTipo;
    }

    @Override
    public View getDropDownView(int position, View currentView, ViewGroup parent) {
        Tipo tipo = getItem(position);
        TextView label = (TextView) super.getDropDownView(position, currentView, parent);
        label.setText(tipo.getNome());

        return label;
    }
}
