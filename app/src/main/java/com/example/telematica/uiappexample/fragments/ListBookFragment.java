package com.example.telematica.uiappexample.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.telematica.uiappexample.R;
import com.example.telematica.uiappexample.activities.MainActivity;
import com.example.telematica.uiappexample.presenters.ListBookPresenterImpl;

/**
 * Created by Matias on 15-01-2016.
 */
public class ListBookFragment extends Fragment{

    private ListBookPresenterImpl mListBookPresenter;

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;

    public static ListBookFragment newInstance() {
        ListBookFragment fragment = new ListBookFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_listbook, null);

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mListBookPresenter = new ListBookPresenterImpl(mRecyclerView, mAdapter);
        mListBookPresenter.getTask().execute();

        return mainView;
    }

}
