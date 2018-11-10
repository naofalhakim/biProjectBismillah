package com.example.user.projectbidanku.FragmentMenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.user.projectbidanku.Adapter.NamaCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.RealmHelper;
import com.example.user.projectbidanku.Model.DataNamaBayi;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class RekomendasiNamaFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<NamaCalonBayi> namaList;
    private Context context ;
    private DataNamaBayi dataNamaBayi;
    private RealmHelper realmHelper;
    private Realm realm;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rekomendasi_nama, container, false);

        dataNamaBayi = new DataNamaBayi();
        namaList = new ArrayList();
        context = this.getContext();

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclre_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<NamaCalonBayi> namaCalonBayis = realmHelper.selectNamaCalonBayi();
        Log.e("sizeNamaCalonBayi",namaCalonBayis.size()+"");

        if(namaCalonBayis.size() == 0){
            for (int i = 0; i < dataNamaBayi.dataNama.length; i++) {
                NamaCalonBayi a = new NamaCalonBayi(i, dataNamaBayi.dataNama[i][2], dataNamaBayi.dataNama[i][0], dataNamaBayi.dataNama[i][1]);
                realmHelper.saveNamaCalonBayiFirst(a);
            }
        }else{
            recyclerView.setAdapter(new NamaCalonBayiRecyclerViewAdapter(namaCalonBayis,context,realmHelper));
        }

        return root;
    }


}
