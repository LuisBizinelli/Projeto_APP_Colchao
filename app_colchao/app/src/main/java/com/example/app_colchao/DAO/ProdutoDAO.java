package com.example.app_colchao.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.example.app_colchao.model.Filme;
import com.example.app_colchao.model.Genero;

@Dao
public interface ProdutoDAO {

    @Insert
    void insert(Produto produto);

    @Update
    void update(Produto produto);

    @Query("SELECT * FROM produto_table WHERE produto_table.ID == :id")
    Produto loadProdutoByID(Long id);

    @Query("DELETE FROM produto_table where produto_table.ID == :id")
    void delete(long id);

    @Query("SELECT * from produto_table ORDER BY avaliacao DESC")
    List<Produto> loadProdutos();

    @Query("SELECT produto_table.ID,produto_table.titulo,produto_table.ano_producao,produto_table.avaliacao, tipo_table.ID as tipo_ID ,tipo_table.nome as tipo_nome from produto_table INNER JOIN tipo_table ON produto_table.tipo_id = tipo_table.ID ORDER BY avaliacao DESC")
    List<ProdutoJoin> loadProdutosJoin();

    @Query("SELECT titulo from produto_table")
    List<String> loadProdutosNames();

    static class ProdutoJoin{
        @Embedded
        public Produto produto;
        @Embedded(prefix = "tipo_")
        public Tipo tipo;
    }

}
