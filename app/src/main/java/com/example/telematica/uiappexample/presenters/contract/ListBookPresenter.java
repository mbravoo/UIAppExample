package com.example.telematica.uiappexample.presenters.contract;

import android.os.AsyncTask;

import com.example.telematica.uiappexample.models.Libro;

import java.util.List;

/**
 * Created by Matias on 15-01-2016.
 */
public interface ListBookPresenter {
    public AsyncTask<Void, Void, String> getTask();
}
