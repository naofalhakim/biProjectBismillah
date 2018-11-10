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

import com.example.user.projectbidanku.Adapter.NamaBayiFavRecyclerViewAdapter;
import com.example.user.projectbidanku.Adapter.NamaCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.RealmHelper;
import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.NamaCalonBayiFavorit;
import com.example.user.projectbidanku.Model.VolleyCalback;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class FavoritNamaFragment extends Fragment implements View.OnClickListener{

    private FloatingActionButton floatingActionButton;
    private ServerHelper serverHelper;
    private SessionManager sessionManager;
    private Context context ;
    private List<NamaCalonBayiFavorit> namaList;
    private RecyclerView recyclerView;
    private RealmHelper realmHelper;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favorit_nama, container, false);

        namaList = new ArrayList();
        context = this.getContext();

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.btn_add_baby);
        floatingActionButton.setOnClickListener(this);
        serverHelper = new ServerHelper(getContext());
        sessionManager = new SessionManager(getContext());

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclre_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        namaList = realmHelper.selectNamaCalonBayiFavorit(sessionManager.getLoginID());
        recyclerView.setAdapter(new NamaBayiFavRecyclerViewAdapter(namaList,context,realmHelper));

//        serverHelper.showBabyName(sessionManager.getLoginID(), new VolleyListCalback() {
//            @Override
//            public void onSuccess(List<Object> objectList) {
//                namaList = (List<NamaCalonBayi>) (Object) objectList;
//                recyclerView.setAdapter(new NamaCalonBayiRecyclerViewAdapter(namaList,context,realmHelper));
//            }
//        });




        floatingActionButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        showForm();
    }

    public void showForm(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_adding_namaanak, null);
        mBuilder.setView(mView);

        final TextInputEditText etNamaBayi = (TextInputEditText) mView.findViewById(R.id.et_nama_bayi);
        final TextInputEditText etArtiBayi = (TextInputEditText) mView.findViewById(R.id.et_arti_bayi);
        final RadioButton rdLaki = (RadioButton) mView.findViewById(R.id.rd_laki);
        RadioButton rdPerempuan = (RadioButton) mView.findViewById(R.id.rd_perempuan);
        Button addButton = (Button) mView.findViewById(R.id.btn_add_baby);
        final AlertDialog dialog = mBuilder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(rdLaki.isChecked()){
                    gender = "L";
                }else{
                    gender = "P";
                }
                NamaCalonBayi namaCalonBayi = new NamaCalonBayi(gender,etNamaBayi.getText().toString(), etArtiBayi.getText().toString());
                realmHelper.saveNamaCalonBayiFavorit(namaCalonBayi,sessionManager.getLoginID());

                namaList = realmHelper.selectNamaCalonBayiFavorit(sessionManager.getLoginID());
                recyclerView.setAdapter(new NamaBayiFavRecyclerViewAdapter(namaList,context,realmHelper));
                dialog.dismiss();
//                   online server
//                serverHelper.addBabyNameFunction(sessionManager.getLoginID(), etNamaBayi.getText().toString(),
//                        etArtiBayi.getText().toString(), gender, new VolleyCalback() {
//                            @Override
//                            public void onSuccess(String result, String result2) {
//
//                            }
//                        });
//                serverHelper.showBabyName(sessionManager.getLoginID(), new VolleyListCalback() {
//                    @Override
//                    public void onSuccess(List<Object> objectList) {
//                        namaList = (List<NamaCalonBayi>) (Object) objectList;
//                        recyclerView.setAdapter(new NamaCalonBayiRecyclerViewAdapter(namaList,context,realmHelper));
//                    }
//                });
            }
        });

        dialog.show();
    }
}
