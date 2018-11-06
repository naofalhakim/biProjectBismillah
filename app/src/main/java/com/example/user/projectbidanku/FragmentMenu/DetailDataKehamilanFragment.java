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
import com.example.user.projectbidanku.Model.CatatanKesehatan;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;

public class DetailDataKehamilanFragment extends Fragment {

    private String nama,T_Lahir, T_mens, metode;
    private int arm;
    private double heigth;
    private boolean kek;
    private TextView txtNama, txtTanggalLahir, txtTanggalHamil, txtMetode, txtArm, txtHeight, txtKEK;
    private RecyclerView recyclerView;
    private List<CatatanKesehatan> detailDataKehamilanFragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nama = getArguments().getString("Nama");
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

        detailDataKehamilanFragments = new ArrayList();
        for (int i = 0; i < 3; i++) {
            CatatanKesehatan catatanKesehatan =
                    new CatatanKesehatan(i, 15, (7*(i+1))+" September 2018", (7*(i+2))+" September 2018",
                    "Masih Baik Baik saja", "Kandungan Sehat", "Lakukan Kegiatan Biasa saja", "Belum ada", "Bandung" ,80.3,
            65.3, 98.1, 67.8, 80.66, 76.9, 100);
            catatanKesehatan.setNama(""+(i+1));
            detailDataKehamilanFragments.add(catatanKesehatan);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new DataCekUpRecyclerViewAdapter(detailDataKehamilanFragments,this.getContext()));
        return v;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setTextDetail(){
        txtKEK.setText("Menggunakan KEK : "+kek);
        txtNama.setText("Kehamilan ke -"+nama);
        txtMetode.setText("Metode Kontrasepsi : "+metode);
        txtHeight.setText("Tinggi Badan : "+heigth);
        txtTanggalHamil.setText("Tanggal Terakhir Menstruasi : "+T_mens);
        txtTanggalLahir.setText("Estimasi Tanggal Kelahiran : "+T_Lahir);
        txtArm.setText("Lingkar Lengan : "+arm+" cm");
    }
}
