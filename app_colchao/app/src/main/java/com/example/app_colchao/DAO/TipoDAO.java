package com.example.app_colchao.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.example.app_colchao.model.Filme;
import com.example.app_colchao.model.Genero;

@Dao
public interface TipoDAO {
    @Insert
    void insert(Tipo tipo);

    @Update
    void update(Tipo tipo);

    @Query("SELECT * from tipo_table ORDER BY nome DESC")
    List<Tipo> loadTipos();

}
