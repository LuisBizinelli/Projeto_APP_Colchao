package com.example.app_colchao.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;



@Entity(tableName = "produto_table",
        foreignKeys = @ForeignKey(entity = Tipo.class,
                parentColumns = "ID",
                childColumns = "tipo_id"))
public class Produto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long id;
    private String titulo;
    private int ano_producao;
    private int avaliacao;

    @ColumnInfo(name = "tipo_id")
    private long tipoId;

    public Produto() {
    }

    public Produto(long id, String titulo, int ano_producao, int avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.ano_producao = ano_producao;
        this.avaliacao = avaliacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno_producao() {
        return ano_producao;
    }

    public void setAno_producao(int ano_producao) {
        this.ano_producao = ano_producao;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public long getTipoId() {
        return tipoId;
    }

    public void setTipoId(long tipoId) {
        this.tipoId = tipoId;
    }
}
