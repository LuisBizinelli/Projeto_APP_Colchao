package com.example.app_colchao.Repository;

import android.content.Context;

import com.example.app_colchao.Model.Produto;
import com.example.app_colchao.Model.Tipo;

public class Repository {
    private ProdutoRepository produtoRepository;
    private TipoRepository tipoRepository;

    public Repository(Context context){
        produtoRepository = new ProdutoRepository(context);
        tipoRepository = new TipoRepository(context);
    }

    public ProdutoRepository getProdutoRepository() {
        return produtoRepository;
    }

    public TipoRepository getTipoRepository() {
        return tipoRepository;
    }
}
