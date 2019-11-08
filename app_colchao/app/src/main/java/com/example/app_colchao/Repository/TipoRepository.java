package com.example.app_colchao.Repository;

import android.content.Context;

import java.util.List;

import com.example.app_colchao.DAO.TipoDAO;
import com.example.app_colchao.DataBase.AppRoomDataBase;
import com.example.app_colchao.Model.Tipo;

public class TipoRepository {
    private TipoDAO mTipoDAO;
    private List<Tipo> mTipos;

    public TipoRepository(Context context){
        AppRoomDataBase db = AppRoomDataBase.getDatabase(context);
        mTipoDAO = db.tipoDAO();
    }

    public List<Tipo> getAllTipos(){
        mTipos = mTipoDAO.loadTipos();
        return mTipos;
    }

    /*public Tipo loadTipoByID(long ID) {
        return mTiposDAO.loadTipoByID(ID);
    }*/

    public void insert(Tipo tipo){
        mTipoDAO.insert(tipo);
    }
    public void update(Tipo tipo) {mTipoDAO.update(tipo);}
}
