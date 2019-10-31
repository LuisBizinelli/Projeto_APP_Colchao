package com.example.app_colchao.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import com.example.app_colchao.DAO.ProdutoDAO;
import com.example.app_colchao.R;
import com.example.app_colchao.adapter.ProdutoAdapter;
import com.example.app_colchao.model.Produto;
import com.example.app_colchao.repository.ProdutoRepository;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView listaProdutos;
    private ProdutoRepository repository;
    ArrayAdapter<ProdutoDAO.ProdutoJoin> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProdutos = findViewById(R.id.listaProdutos);
        repository = new ProdutoRepository(getApplicationContext());
        atualizarProdutos();
        listaProdutos.setOnItemClickListener(this);
    }

    public void novoProduto(View view){
        Intent novoProduto = new Intent(MainActivity.this,NovoProdutoActivity.class);
        startActivity(novoProduto);
    }

    private void atualizarPedidos(){
        List<ProdutoDAO.FilmeJoin> filmes = repository.getAllProdutosJoin();
        adapter = new ProdutoAdapter(getApplicationContext(), R.layout.Produto_item, produtos);
        listaProdutos.setAdapter(adapter);
    }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final ProdutoDAO.FilmeJoin produtoJoin = (ProdutoDAO.FilmeJoin) adapterView.getItemAtPosition(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("O que fazer com " + produtoJoin.produto.getTitulo()).setItems(R.array.opcoes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(which == 0) {
                    repository.delete(produtoJoin.produto.getId());
                    atualizarPedidos();
                }
                else if(which == 1){
                    callActivity(produtoJoin.produto.getId());
                }

            }
        }).create().show();
    }

    private void callActivity(Long id) {
        Intent atualizar = new Intent(MainActivity.this,AtualizarProdutoActivity.class);
        atualizar.putExtra("ID",id);
        startActivity(atualizar);
    }

    public void novoTipo(View view) {
        Intent novoTipo = new Intent(MainActivity.this,NovoTipoActivity.class);
        startActivity(novoTipo);
    }
}
