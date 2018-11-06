package com.example.user.projectbidanku.FragmentMenu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.projectbidanku.Adapter.DataKehamilanRecyclerViewAdapter;
import com.example.user.projectbidanku.Adapter.NamaCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;


public class DataKehamilanFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<DataKehamilan> kehamilanList;

    public DataKehamilanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_kehamilan, container, false);

        kehamilanList = new ArrayList();

        for (int i = 0; i < 2; i++) {
            DataKehamilan dataKehamilan = new DataKehamilan(i,(20+i),"20-09-2018","20-07-2019","Tidak Menggunakan", true, 145.9);
            dataKehamilan.setNama(""+(i+1));
            kehamilanList.add(dataKehamilan);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclre_kehamilan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new DataKehamilanRecyclerViewAdapter(kehamilanList,this.getContext()));

        return view;
    }

}
