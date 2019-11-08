package com.example.app_colchao.Repository;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import com.example.app_colchao.DAO.ProdutoDAO;
import com.example.app_colchao.DataBase.AppRoomDataBase;
import com.example.app_colchao.Model.Produto;

public class ProdutoRepository {
    private ProdutoDAO mProdutoDAO;
    private List<Produto> mProdutos;
    private List<ProdutoDAO.ProdutoJoin> mProdutosJoin;

    public ProdutoRepository(Context context){
        AppRoomDataBase db = AppRoomDataBase.getDatabase(context);
        mProdutoDAO = db.produtoDAO();
    }

    public List<Produto> getAllProdutos(){
        mProdutos = mProdutoDAO.loadProdutos();
        return mProdutos;
    }

    public List<ProdutoDAO.ProdutoJoin> getAllProdutosJoin(){
        mProdutosJoin = mProdutoDAO.loadProdutosJoin();
        return mProdutosJoin;
    }

    public Produto loadProdutoByID(long ID) {
        return mProdutoDAO.loadProdutoByID(ID);
    }

    public void insert(Produto produto){
        new insertAsyncTask(mProdutoDAO).execute(produto);
    }
    public void delete(long id){mProdutoDAO.delete(id);}
    public void update(Produto produto) {mProdutoDAO.update(produto);}

    private static class insertAsyncTask extends AsyncTask<Produto,Void,Void>{

        private ProdutoDAO mAsyncTaskDAO;

        insertAsyncTask(ProdutoDAO dao){
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final Produto... params){
            mAsyncTaskDAO.insert(params[0]);
            return null;
        }
    }
}
