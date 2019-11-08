package com.example.app_colchao.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.app_colchao.Model.Produto;
import com.example.app_colchao.Model.Tipo;

@Dao
public interface TipoDAO {
    @Insert
    void insert(Tipo tipo);

    @Update
    void update(Tipo tipo);

    @Query("SELECT * from tipo_table ORDER BY nome DESC")
    List<Tipo> loadTipos();

}
