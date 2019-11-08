package com.example.app_colchao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import  com.example.app_colchao.DAO.ProdutoDAO;
import  com.example.app_colchao.R;
import  com.example.app_colchao.Model.Produto;

public class ProdutoAdapter extends ArrayAdapter<ProdutoDAO.ProdutoJoin> {
    private int rId;

    public ProdutoAdapter(@NonNull Context context, int resource, @NonNull List<ProdutoDAO.ProdutoJoin> objects) {
        super(context, resource, objects);
        rId = resource;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(rId,null);
        }

        ProdutoDAO.ProdutoJoin produtoJoin = getItem(position);

        TextView textTitulo = mView.findViewById(R.id.textNomeProduto);
        TextView textTipo = mView.findViewById(R.id.textTipoProduto);
        TextView textAno = mView.findViewById(R.id.textAnoProduto);
        RatingBar rating = mView.findViewById(R.id.ratingNotaProduto);

        textTitulo.setText(produtoJoin.produto.getTitulo().toUpperCase());
        textTipo.setText(produtoJoin.tipo.getNome());
        textAno.setText("Quantidade: " + String.valueOf(produtoJoin.produto.getAno_producao()));
        rating.setRating((float)produtoJoin.produto.getAvaliacao());

        return mView;
    }
}
