package com.example.app_colchao.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tipo_table")
public class Tipo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private long ID;
    private String nome;

    public Tipo(){}

    public Tipo(long ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

