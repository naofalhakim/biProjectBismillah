package com.example.user.projectbidanku.FragmentMenu;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.projectbidanku.Adapter.InfoRecyclerViewAdapter;
import com.example.user.projectbidanku.Model.Info;
import com.example.user.projectbidanku.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class BerandaFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Info> taksinfo;
    private TextView txtCountKehamilan;
    private ProgressBar progressKehamilan;
    private Date firstDate, secondDate;
    private String tglHamil, tglSekarang, tglLahir;

    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        txtCountKehamilan = (TextView) rootView.findViewById(R.id.txt_count_kehamilan);
        progressKehamilan = (ProgressBar) rootView.findViewById(R.id.progress_kehamilan);

        Calendar cal = Calendar. getInstance();
        Date date= cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        tglSekarang = sdf.format(date);

        tglHamil = "11/24/2018";
        tglLahir = "09/30/2019";

        try {
            firstDate = sdf.parse(tglHamil);
            secondDate = sdf.parse(tglLahir);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        diffInMillies = Math.abs(secondDate.getTime() - date.getTime());
        long diff2 = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        txtCountKehamilan.setText("Prediksi Kelahiran Menunggu : "+diff2/7+" Minggu "+diff2%7+" Hari");
        progressKehamilan.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressKehamilan.setProgress((int)Math.round(((double)diff2/diff)*100), true);
        }else{
            progressKehamilan.setProgress((int)Math.round(((double)diff2/diff)*100));
        }

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
