package com.example.user.projectbidanku.FragmentMenu;

import android.content.Context;
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
import android.widget.TextView;

import com.example.user.projectbidanku.Adapter.InfoRecyclerViewAdapter;
import com.example.user.projectbidanku.Adapter.NamaCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.Info;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.VolleyCalback;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;

public class NamaBayiFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<NamaCalonBayi> namaList;
    private FloatingActionButton floatingActionButton;
    private ServerHelper serverHelper;
    private SessionManager sessionManager;
    private Context context ;

    public NamaBayiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nama_bayi, container, false);

        namaList = new ArrayList();
        context = this.getContext();

        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.btn_add_baby);
        floatingActionButton.setOnClickListener(this);
        serverHelper = new ServerHelper(getContext());
        sessionManager = new SessionManager(getContext());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclre_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        serverHelper.showBabyName(sessionManager.getLoginID(), new VolleyListCalback() {
            @Override
            public void onSuccess(List<Object> objectList) {
                namaList = (List<NamaCalonBayi>) (Object) objectList;
                recyclerView.setAdapter(new NamaCalonBayiRecyclerViewAdapter(namaList,context));
            }
        });


        floatingActionButton.setOnClickListener(this);
        return rootView;
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender;
                if(rdLaki.isChecked()){
                    gender = "laki";
                }else{
                    gender = "perempuan";
                }
                serverHelper.addBabyNameFunction(sessionManager.getLoginID(), etNamaBayi.getText().toString(),
                        etArtiBayi.getText().toString(), gender, new VolleyCalback() {
                            @Override
                            public void onSuccess(String result, String result2) {

                            }
                        });
                serverHelper.showBabyName(sessionManager.getLoginID(), new VolleyListCalback() {
                    @Override
                    public void onSuccess(List<Object> objectList) {
                        namaList = (List<NamaCalonBayi>) (Object) objectList;
                        recyclerView.setAdapter(new NamaCalonBayiRecyclerViewAdapter(namaList,context));
                    }
                });
            }
        });

        final AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
