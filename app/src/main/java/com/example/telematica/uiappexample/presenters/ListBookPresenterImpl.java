package com.example.telematica.uiappexample.presenters;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.telematica.uiappexample.connection.HttpServerConnection;
import com.example.telematica.uiappexample.models.Libro;
import com.example.telematica.uiappexample.presenters.contract.ListBookPresenter;
import com.example.telematica.uiappexample.views.ListBookView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matias on 15-01-2016.
 */
public class ListBookPresenterImpl implements ListBookPresenter{

    private RecyclerView.Adapter mAdapter;

    private ListBookView mListBookView;

    public ListBookPresenterImpl(ListBookView mListBookView) {
        this.mListBookView = mListBookView;
    }

    AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected String doInBackground(Void... params) {
            String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/56990dc51200009e47e25b44", 15000);
            return resultado;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result != null){
                System.out.println(result);

                // specify an adapter (see also next example)
                mAdapter = new UIAdapter(getLista(result));
                mListBookView.manageRecyclerView(mAdapter);
            }
        }
    };

    public AsyncTask<Void, Void, String> getTask() {
        return task;
    }

    private List<Libro> getLista(String result){
        List<Libro> listaLibros = new ArrayList<Libro>();
        try {
            JSONArray lista = new JSONArray(result);

            int size = lista.length();
            for(int i = 0; i < size; i++){
                Libro libro = new Libro();
                JSONObject objeto = lista.getJSONObject(i);

                libro.setId(objeto.getInt("id"));
                libro.setNombre(objeto.getString("nombre"));
                libro.setEditorial(objeto.getString("editorial"));
                libro.setGenero(objeto.getString("genero"));
                libro.setAutor(objeto.getInt("autor"));

                listaLibros.add(libro);
            }
            return listaLibros;
        } catch (JSONException e) {
            e.printStackTrace();
            return listaLibros;
        }
    }
}
