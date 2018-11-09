package com.example.user.projectbidanku.FragmentMenu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.user.projectbidanku.Adapter.NamaCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.VolleyCalback;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;


public class RekomendasiNamaFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<NamaCalonBayi> namaList;
    private Context context ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rekomendasi_nama, container, false);

        namaList = new ArrayList();
        context = this.getContext();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclre_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        for (int i = 0; i < 10; i++) {
            NamaCalonBayi a = new NamaCalonBayi(i, "L", "Paijo", "Paijo");
            if(i % 2 == 0){
                a.setSign_fav(1);
            }
            namaList.add(a);

        }

        recyclerView.setAdapter(new NamaCalonBayiRecyclerViewAdapter(namaList,context));
        return root;
    }


}
