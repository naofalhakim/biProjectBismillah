package com.example.user.projectbidanku.FragmentMenu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.projectbidanku.Adapter.DataKehamilanRecyclerViewAdapter;
import com.example.user.projectbidanku.Adapter.NamaCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;


public class DataKehamilanFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<DataKehamilan> kehamilanList;
    private ServerHelper serverHelper;
    private SessionManager sessionManager;
    private Context context;

    public DataKehamilanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_kehamilan, container, false);

        kehamilanList = new ArrayList();
        serverHelper = new ServerHelper(this.getContext());
        sessionManager = new SessionManager(this.getContext());
        context = this.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclre_kehamilan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        serverHelper.showPregnancy(sessionManager.getLoginID(), new VolleyListCalback() {
            @Override
            public void onSuccess(List<Object> objectList) {
                kehamilanList = (List<DataKehamilan>) (Object) objectList;
                recyclerView.setAdapter(new DataKehamilanRecyclerViewAdapter(kehamilanList,context));
            }
        });

        return view;
    }

}
