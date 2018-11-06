package com.example.user.projectbidanku.DrawerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.FragmentMenu.AkunFragment;
import com.example.user.projectbidanku.FragmentMenu.BerandaFragment;
import com.example.user.projectbidanku.FragmentMenu.DataKehamilanFragment;
import com.example.user.projectbidanku.FragmentMenu.NamaBayiFragment;
import com.example.user.projectbidanku.FragmentMenu.RencanaKelahiranFragment;
import com.example.user.projectbidanku.LoginActivity;
import com.example.user.projectbidanku.R;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_menu, new BerandaFragment());
        getSupportActionBar().setTitle("Beranda");
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sessionManager = new SessionManager(MenuActivity.this);

        View root = navigationView.getHeaderView(0);
        TextView txtNama = (TextView) root.findViewById(R.id.txt_nama);
        txtNama.setText(sessionManager.getLoginEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_berandaberanda) {
            fragmentTransaction.replace(R.id.fragment_menu, new BerandaFragment());
            getSupportActionBar().setTitle("Beranda");
        } else if (id == R.id.nav_akun) {
            fragmentTransaction.replace(R.id.fragment_menu, new AkunFragment());
            getSupportActionBar().setTitle("Akun");
        } else if (id == R.id.nav_data_kehamilan) {
            fragmentTransaction.replace(R.id.fragment_menu, new DataKehamilanFragment());
            getSupportActionBar().setTitle("Data Kehamilan Anda");
        } else if (id == R.id.nav_namabayi) {
            fragmentTransaction.replace(R.id.fragment_menu, new NamaBayiFragment());
            getSupportActionBar().setTitle("Nama Bayi Anda");
        } else if (id == R.id.nav_galeri) {

        }else if (id == R.id.nav_persiapan_kehamilan) {
            fragmentTransaction.replace(R.id.fragment_menu, new RencanaKelahiranFragment());
            getSupportActionBar().setTitle("Rencana Kehamilan");
        }else if (id == R.id.nav_logout) {
            startActivity(new Intent(MenuActivity.this, LoginActivity.class));
            finish();
            sessionManager.doLogout();
        }else{
            fragmentTransaction.replace(R.id.fragment_menu, new BerandaFragment());
            getSupportActionBar().setTitle("Beranda");
        }

        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
