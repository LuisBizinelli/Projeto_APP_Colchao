package com.example.app_colchao.DataBase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.app_colchao.DAO.ProdutoDAO;
import com.example.app_colchao.DAO.TipoDAO;
import com.example.app_colchao.DAO.UsuarioDAO;
import com.example.app_colchao.Model.Produto;
import com.example.app_colchao.Model.Tipo;
import com.example.app_colchao.Model.Usuario;


@Database(entities = {Produto.class, Tipo.class, Usuario.class},version = 1)
public abstract class AppRoomDataBase extends RoomDatabase {
    private static volatile AppRoomDataBase INSTANCE;
    public abstract UsuarioDAO usuarioDAO();
    public abstract ProdutoDAO produtoDAO();
    public abstract TipoDAO tipoDAO();

    public static AppRoomDataBase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppRoomDataBase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppRoomDataBase.class,"produto_database").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
