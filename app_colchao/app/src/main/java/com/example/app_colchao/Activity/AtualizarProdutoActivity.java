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

import java.util.List;

import com.example.app_colchao.R;
//import com.example.app_colchao.adapter.TipoAdapter;
import com.example.app_colchao.model.Produto;
//import com.example.app_colchao.model.Tipo;
import com.example.app_colchao.repository.ProdutoRepository;
import com.example.app_colchao.repository.Repository;

public class AtualizarProdutoActivity extends Activity {
    private EditText editTitulo, editAno;
    private Spinner spinnerTipo;
    private RatingBar ratingProduto;
    private Repository repository;
    private Produto produto;
    private TipoAdapter produtoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_produto);

        long extra_id = getIntent().getLongExtra("ID",0);
        Toast.makeText(this, "ID = " + extra_id, Toast.LENGTH_SHORT).show();

        editTitulo = findViewById(R.id.editTitulo);
        editAno = findViewById(R.id.editAno);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        ratingProduto = findViewById(R.id.ratingNotaProduto);
        repository = new Repository(getApplicationContext());
        loadProduto(extra_id);
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                produto.setTipoId(((Tipo)adapterView.getItemAtPosition(i)).getID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadProduto(long extra_id) {
        produto = repository.getProdutoRepository().loadFilmeByID(extra_id);
        tipoAdapter = new TipoAdapter(this,android.R.layout.simple_spinner_item,repository.getTipoRepository().getAllTipos());
        spinnerTipo.setAdapter(tipoAdapter);
        editAno.setText(String.valueOf(produto.getAno_producao()));
        editTitulo.setText(produto.getTitulo());
        List<Tipo> tipos = repository.getTipoRepository().getAllTipos();
        int counter = 0;
        for(Tipo g : tipos){
            if(produto.getTipoId() == g.getID()){
                spinnerTipo.setSelection(counter);
                break;
            }
            counter++;
        }
        ratingProduto.setRating((float)produto.getAvaliacao());
    }

    public void atualizarProduto(View view){
        produto.setTitulo(editTitulo.getText().toString());
        produto.setAno_producao(Integer.parseInt(editAno.getText().toString()));
        produto.setAvaliacao((int)ratingProduto.getRating());
        repository.getProdutoRepository().update(produto);
        callMainActivity();
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(AtualizarProdutoActivity.this,MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
