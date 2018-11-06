package com.example.user.projectbidanku.FragmentMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.projectbidanku.Adapter.InfoRecyclerViewAdapter;
import com.example.user.projectbidanku.Model.Info;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Info> taksinfo;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        taksinfo = new ArrayList();
        for (int i = 0; i < 15; i++) {
            taksinfo.add(new Info("Kontent 1", R.drawable.ic_menu_camera, i));
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclre_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new InfoRecyclerViewAdapter(taksinfo,this.getContext()));

        return rootView;
    }

}
