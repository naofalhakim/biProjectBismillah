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
import android.widget.TextView;

import com.example.user.projectbidanku.Adapter.DataCekUpRecyclerViewAdapter;
import com.example.user.projectbidanku.Adapter.DataKehamilanRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.CatatanKesehatan;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.Model.VolleyCallbackObject;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;

public class DetailDataKehamilanFragment extends Fragment {

    private String nama,T_Lahir, T_mens, metode;
    private int arm,id;
    private double heigth;
    private boolean kek;
    private TextView txtNama, txtTanggalLahir, txtTanggalHamil, txtMetode, txtArm, txtHeight, txtKEK;
    private RecyclerView recyclerView;
    private List<CatatanKesehatan> detailDataKehamilanFragments;
    private ServerHelper serverHelper;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nama = getArguments().getString("Nama");
            id = Integer.parseInt(getArguments().getString("Id"));
            T_Lahir = getArguments().getString("T_lahir");
            T_mens = getArguments().getString("T_hamil");
            metode = getArguments().getString("Contra");
            arm = getArguments().getInt("Arm");
            heigth = getArguments().getDouble("B_height");
            kek = getArguments().getBoolean("kek");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail_data_kehamilan, container, false);
        txtArm = (TextView) v.findViewById(R.id.txt_armsize);
        txtNama = (TextView) v.findViewById(R.id.txt_nama);
        txtTanggalHamil = (TextView) v.findViewById(R.id.txt_terakhir_mens);
        txtTanggalLahir = (TextView) v.findViewById(R.id.txt_estimasi_kelahiran);
        txtHeight = (TextView) v.findViewById(R.id.txt_body_height);
        txtMetode = (TextView) v.findViewById(R.id.txt_metode_kontrasep);
        txtKEK = (TextView) v.findViewById(R.id.txt_KEK);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclre_cekup);

        setTextDetail();
        context = this.getContext();

        detailDataKehamilanFragments = new ArrayList();
        serverHelper = new ServerHelper(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        serverHelper.showPregnancyDetail(id, new VolleyCallbackObject() {
            @Override
            public void onSuccess(Object object) {
                DataKehamilan dataKehamilan = (DataKehamilan) object;
                txtArm.setText(getString(R.string.string_lingkarlengan)+dataKehamilan.getArm_size()+"");
                txtTanggalHamil.setText(getString(R.string.string_terakhirmens)+dataKehamilan.getLast_mens_date()+"");
                txtHeight.setText(getString(R.string.string_tinggibadan)+dataKehamilan.getBody_height()+"");
                txtKEK.setText(getString(R.string.string_menggunakankek)+dataKehamilan.isKEK()+"");
                txtMetode.setText(getString(R.string.string_metodekontrsepsi)+dataKehamilan.getContraception_methode()+"");
                txtTanggalLahir.setText(getString(R.string.string_estimasikelahiran)+dataKehamilan.getEstimation_birth_date()+"");
            }
        });

        serverHelper.showHealtyNotes(id, new VolleyListCalback() {
            @Override
            public void onSuccess(List<Object> objectList) {
                detailDataKehamilanFragments = (List<CatatanKesehatan>) (Object) objectList;
                recyclerView.setAdapter(new DataCekUpRecyclerViewAdapter(detailDataKehamilanFragments,context,serverHelper));
            }
        });

        return v;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setTextDetail(){
        txtKEK.setText("Menggunakan KEK : 100");
        txtNama.setText("Kehamilan ke -"+nama);
        txtMetode.setText("Metode Kontrasepsi : Tidak");
        txtHeight.setText("Tinggi Badan : 159cm");
        txtTanggalHamil.setText("Tanggal Terakhir Menstruasi : 10-10-2019");
        txtTanggalLahir.setText("Estimasi Tanggal Kelahiran : 12-08-2020");
        txtArm.setText("Lingkar Lengan : 20 cm");
    }
}
