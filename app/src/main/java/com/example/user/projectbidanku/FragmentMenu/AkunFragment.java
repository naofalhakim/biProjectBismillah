package com.example.user.projectbidanku.FragmentMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.projectbidanku.Adapter.RequestBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.Anak;
import com.example.user.projectbidanku.Model.Patient;
import com.example.user.projectbidanku.Model.VolleyCallbackObject;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;


public class AkunFragment extends Fragment implements View.OnClickListener{

    private ImageButton btnIbu, btnSuami, btnKeluarga, btnAnak;
    private EditText etNama, etEmail, etLocation, etAge, etKehamilan, etPendidikan, etTelphoneNumber;
    private AlertDialog.Builder mBuilder;
    private View mView;
    private TextView[] listTextView = new TextView[11];
    private ServerHelper serverHelper;
    private SessionManager sessionManager;
    private Patient patient,suami;
    private List<Anak> anakList;

    private int[] motherText = {R.id.txt_jkn, R.id.txt_notelp, R.id.txt_agama,R.id.txt_usia_anak,R.id.txt_Jumlah_Melahirkan
    ,R.id.txt_Jumlah_keguguran,R.id.txt_jumlah_bayi_meninggal, R.id.txt_jumlah_anak_prematur,R.id.txt_jumlah_bayi_hidup};
    private int[] husbandText = {R.id.txt_nama,R.id.txt_ttl,R.id.txt_agama, R.id.txt_goldar,R.id.txt_pekerjaan};
    private int[] addressText = {R.id.txt_alamat_rumah,R.id.txt_kelurahan,R.id.txt_kecamatan,R.id.txt_notelp};

    public AkunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_akun, container, false);

        sessionManager = new SessionManager(this.getContext());
        serverHelper = new ServerHelper(this.getContext());

        btnIbu = (ImageButton) v.findViewById(R.id.btn_moremom);
        btnSuami = (ImageButton) v.findViewById(R.id.btn_morehusband);
        btnKeluarga = (ImageButton) v.findViewById(R.id.btn_morehome);
        btnAnak = (ImageButton) v.findViewById(R.id.btn_morekids);

        etAge = (EditText) v.findViewById(R.id.et_usia);
        etEmail = (EditText) v.findViewById(R.id.et_email);
        etKehamilan = (EditText) v.findViewById(R.id.et_kehamilan);
        etLocation = (EditText) v.findViewById(R.id.et_alamat);
        etPendidikan = (EditText) v.findViewById(R.id.et_pendidikan);
        etTelphoneNumber = (EditText) v.findViewById(R.id.et_notelp);

        mBuilder = new AlertDialog.Builder(getContext());
        btnIbu.setOnClickListener(this);
        btnSuami.setOnClickListener(this);
        btnKeluarga.setOnClickListener(this);
        btnAnak.setOnClickListener(this);

        serverHelper.showDetailMom(sessionManager.getLoginID(), new VolleyCallbackObject() {
            @Override
            public void onSuccess(Object object) {
                patient = (Patient) object;
                etAge.setText(patient.getAge()+"");
                etEmail.setText(patient.getEmail());
                etLocation.setText(patient.getAlamat());
                etPendidikan.setText(patient.getPendidikan());
                etTelphoneNumber.setText(patient.getNotelp());
                etKehamilan.setText(patient.getKehamilanke()+"");
            }
        });

        serverHelper.showDetailHusband(sessionManager.getLoginID(), new VolleyCallbackObject() {
            @Override
            public void onSuccess(Object object) {
                suami = (Patient) object;
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        if(btnAnak ==v){
            showDialogAnak();
        }else if(btnKeluarga ==v){
            showDialogKeluarga();
        }else if(btnIbu ==v){
            showDialogIbu();
        }else{
            showDialogSuami();
        }
    }

        public void showDialogIbu(){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_detail_mother, null);
            String[] contentMother = {"Kode KJN : "+patient.getKjn(),"Notelp : "+patient.getNotelp(),"Agama : "+patient.getAgama(),
                    "Usia Anak Terakhir : "+patient.getUsiaAnakTerakhir()+" thn",
                    "Jumlah Melahrikan : "+patient.getJmlmelahrikan()+"",
                    "Jumlah Keguguran : "+patient.getJmlkeguguran()+"",
                    "Jumlah Bayi Meninggal : "+patient.getJmlbayimeninggal()+"",
                    "Jumlah Anak Prematur : "+patient.getJmlprematur()+"",
                    "Jumlah Bayi Hidup : "+patient.getJmlbayihidup()+""};

            for (int i = 0; i < motherText.length; i++) {
                listTextView[i] = (TextView) mView.findViewById(motherText[i]);
                listTextView[i].setText(contentMother[i]);
            }

            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
        }

        public void showDialogSuami(){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_detail_suami, null);
            String[] contentHusband = {"Nama : "+suami.getNama(),"TTL : "+suami.getAlamat(),"Agama : "+suami.getAgama(),"Golongan Darah : "+suami.getGoldar(),
                    "Pekerjaan : "+suami.getPendidikan()
            };
            for (int i = 0; i < husbandText.length; i++) {
                listTextView[i] = (TextView) mView.findViewById(husbandText[i]);
                listTextView[i].setText(contentHusband[i]);
            }
            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
        }

        public void showDialogKeluarga(){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_detail_rumah, null);
            String[] contentAddress = {"Alamat rumah : "+patient.getAlamat(),"Kelurahan : "+patient.getKelurahan(),"Kecamatan : "+patient.getKecamatan()+""+"Notelp yang bisa dihubungu"};
            for (int i = 0; i < addressText.length; i++) {
                listTextView[i] = (TextView) mView.findViewById(addressText[i]);
                listTextView[i].setText(contentAddress[i]);
            }
            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
        }

        public void showDialogAnak(){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_detail_anak, null);
            final RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.recyclre_list_anak);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            serverHelper.showChildren(sessionManager.getLoginID(), new VolleyListCalback() {
                @Override
                public void onSuccess(List<Object> objectList) {
                    anakList = (List<Anak>) (Object) objectList;
                    recyclerView.setAdapter(new RequestBayiRecyclerViewAdapter(anakList,getContext()));
                }
            });

            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();
        }


}
