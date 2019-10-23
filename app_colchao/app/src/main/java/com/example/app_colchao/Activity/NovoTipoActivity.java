package com.example.app_colchao.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.app_colchao.R;
import com.example.app_colchao.model.Produto;
import com.example.app_colchao.model.Tipo;
import com.example.app_colchao.repository.Repository;

public class NovoTipoActivity {

    private EditText editTitulo;
    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_tipo);

        editTitulo = findViewById(R.id.editTipo);
        repository = new Repository(getApplicationContext());
    }

    public void salvarTipo(View view){
        Tipo tipo = new Tipo();
        tipo.setNome(editTitulo.getText().toString());

        repository.getTipoRepository().insert(tipo);
        callMainActivity();
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(NovoTipoActivity.this,MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
