package com.example.app_colchao.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.app_colchao.R;
import com.example.app_colchao.adapter.TipoAdapter;
import com.example.app_colchao.model.Produto;
import com.example.app_colchao.model.Tipo;
import com.example.app_colchao.repository.ProdutoRepository;
import com.example.app_colchao.repository.Repository;

public class NovoProdutoActivity extends Activity {

    private EditText editTitulo, editAno;
    private Spinner spinnerTipo;
    private RatingBar ratingProduto;
    private Repository repository;
    private Produto produto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        editTitulo = findViewById(R.id.editTitulo);
        editAno = findViewById(R.id.editAno);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        ratingProduto = findViewById(R.id.ratingNotaProduto);
        repository = new Repository(getApplicationContext());
        produto = new Produto();
        loadTipos();
    }

    private void loadTipos() {
        final TipoAdapter adapter = new TipoAdapter(this,android.R.layout.simple_spinner_item,repository.getTipoRepository().getAllTipos());
        spinnerTipo.setAdapter(adapter);
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Tipo tipo = (Tipo) adapterView.getItemAtPosition(i);
                produto.setTipoId(tipo.getID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void salvarProduto(View view){
        //Toast.makeText(this, ""+produto.getTipoId(), Toast.LENGTH_SHORT).show();
        //Produto produto = new Produto();
        produto.setTitulo(editTitulo.getText().toString());
        produto.setAno_producao(Integer.parseInt(editAno.getText().toString()));
        produto.setAvaliacao((int)ratingProduto.getRating());
        repository.getProdutoRepository().insert(produto);
        callMainActivity();
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(NovoProdutoActivity.this,MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
