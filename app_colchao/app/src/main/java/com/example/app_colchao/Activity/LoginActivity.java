package com.example.app_colchao.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app_colchao.DataBase.AppRoomDataBase;
import com.example.app_colchao.Model.Usuario;
import com.example.app_colchao.R;
import com.example.app_colchao.util.Util;


public class LoginActivity extends AppCompatActivity {

    private Button btnNovo, btnLogin;
    private AppRoomDataBase db;
    private EditText editLogin, editSenha;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnNovo = findViewById(R.id.btnNovo);
        btnLogin = findViewById(R.id.btnLogin);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);

        preferences = getSharedPreferences(Util.pref_name, Context.MODE_PRIVATE);

        db = AppRoomDataBase.getDatabase(getApplicationContext());

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNewUser();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        validateUser();
    }

    private void validateUser() {
        boolean loggedIn = preferences.getBoolean("logged",false);
        if(loggedIn){
            loadDashBoard();
        }

    }

    private void setLoggedUser(Long id){
        Editor editor = preferences.edit();
        editor.putBoolean("logged",true);
        editor.putLong("id",id);
        editor.commit();
    }

    private void loginUser() {
        String login, senha;
        login = editLogin.getText().toString().trim();
        senha = editSenha.getText().toString().trim();

        if(login.isEmpty()){
            editLogin.setError("Campo Obrigatório");
            editLogin.requestFocus();
            return;
        }

        if(senha.isEmpty()){
            editSenha.setError("Campo Obrigatório");
            editSenha.requestFocus();
            return;
        }

        Usuario user = db.usuarioDAO().getUser(login,senha);

        if(user != null){
            setLoggedUser(user.getID());
            loadDashBoard();
        }
    }

    private void loadDashBoard() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void loadNewUser() {
        Intent intent = new Intent(LoginActivity.this,NewUserActivity.class);
        startActivity(intent);
    }
}
