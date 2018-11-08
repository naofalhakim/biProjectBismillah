package com.example.user.projectbidanku.FragmentMenu;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.example.user.projectbidanku.Adapter.MainMenuAdapter;
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

public class NamaBayiFragment extends Fragment{

    private TabLayout tabLayout;
    private ViewPager viewPager;
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
        context = this.getContext();

        tabLayout = (TabLayout) rootView.findViewById(R.id.toolbraMenu);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPagerMenu);

        tabLayout.addTab(tabLayout.newTab().setText("Rekomendasi"));
        tabLayout.addTab(tabLayout.newTab().setText("Favorite"));

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(new MainMenuAdapter(getActivity().getSupportFragmentManager(),tabLayout.getTabCount()));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        serverHelper = new ServerHelper(getContext());
        sessionManager = new SessionManager(getContext());

        serverHelper.showBabyName(sessionManager.getLoginID(), new VolleyListCalback() {
            @Override
            public void onSuccess(List<Object> objectList) {

            }
        });


        return rootView;
    }
}
