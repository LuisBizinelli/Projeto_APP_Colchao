package com.example.app_colchao.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import com.example.app_colchao.DAO.ProdutoDAO;
import com.example.app_colchao.DataBase.AppRoomDataBase;
import com.example.app_colchao.R;
import com.example.app_colchao.adapter.ProdutoAdapter;
import com.example.app_colchao.Repository.ProdutoRepository;
import com.example.app_colchao.util.Util;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    private ListView listaProdutos;
    private ProdutoRepository repository;
    ArrayAdapter<ProdutoDAO.ProdutoJoin> adapter;
    private TextView textWelcome;
    private Button btnLogout;
    private AppRoomDataBase db;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProdutos = findViewById(R.id.listaProdutos);
        repository = new ProdutoRepository(getApplicationContext());
        atualizarProdutos();
        listaProdutos.setOnItemClickListener(this);
        btnLogout = findViewById(R.id.btnLogout);
        textWelcome = findViewById(R.id.textWelcome);

        db = AppRoomDataBase.getDatabase(getApplicationContext());

        preferences = getSharedPreferences(Util.pref_name, Context.MODE_PRIVATE);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        loadWelcomeText();
    }

    private void loadWelcomeText() {
        long user_id = preferences.getLong("id",0);
        String nome = db.usuarioDAO().getUserById(user_id).getNome();
        textWelcome.setText("Bem-vindo! "+nome);
    }

    private void logout() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        loadLogin();
    }

    private void loadLogin() {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void atualizarProdutos() {
        List<ProdutoDAO.ProdutoJoin> produtos = repository.getAllProdutosJoin();
        adapter = new ProdutoAdapter(getApplicationContext(),R.layout.produto_item, produtos);
        listaProdutos.setAdapter(adapter);
    }

    public void NovoProduto(View view){
        Intent novoProduto = new Intent(MainActivity.this,NovoProdutoActivity.class);
        startActivity(novoProduto);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final ProdutoDAO.ProdutoJoin produtoJoin = (ProdutoDAO.ProdutoJoin) adapterView.getItemAtPosition(i);
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("O que fazer com " + produtoJoin.produto.getTitulo()).setItems(R.array.opcoes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(which == 0) {
                    repository.delete(produtoJoin.produto.getId());
                    atualizarProdutos();
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
