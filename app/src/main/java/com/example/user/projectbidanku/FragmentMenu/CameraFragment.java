package com.example.user.projectbidanku.FragmentMenu;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.user.projectbidanku.Adapter.FotoCalonBayiRecyclerViewAdapter;
import com.example.user.projectbidanku.AppConfiguration.RealmHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.Foto;
import com.example.user.projectbidanku.R;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    private RecyclerView recyclerView;
    private Uri file;
    private RealmHelper realmHelper;
    private Realm realm;
    private List<Foto> fotos;
    private SessionManager sessionManager;
    private FotoCalonBayiRecyclerViewAdapter adapter;

    public CameraFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclre_foto);
        fotos = new ArrayList();
        sessionManager = new SessionManager(getContext());

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        fotos = realmHelper.selectFotoCalonBayi();

        adapter = new FotoCalonBayiRecyclerViewAdapter(fotos,getContext());
        adapter.notifyDataSetChanged();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        }
    }

    public void takePicture() {
        if(isIntentAvailable(getContext(),MediaStore.ACTION_IMAGE_CAPTURE)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            file = Uri.fromFile(getOutputMediaFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            startActivityForResult(intent, 100);
        }

    }
    public void takeImageFromGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery,0);
    }

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+
                "/Bidanku/");

        if (!mediaStorageDir.exists()){
            mediaStorageDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Foto foto = null;

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                foto = new Foto(file.getPath(),sessionManager.getLoginEmail());
                foto.setId(realmHelper.getMaxFoto());
                realmHelper.saveFotoCalonBayi(foto);
                adapter.notifyDataSetChanged();
            }
        }else if(requestCode == 0) {
            if(resultCode == RESULT_OK){
                Uri selectedImage = data.getData();
                String s= getRealPathFromURI(selectedImage);
                foto = new Foto(s,sessionManager.getLoginEmail());
                foto.setId(realmHelper.getMaxFoto());
                realmHelper.saveFotoCalonBayi(foto);
                adapter.notifyDataSetChanged();
            }
        }


    }

    public String getRealPathFromURI(Uri contentUri) {

        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery( contentUri,
                proj, // Which columns to return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_camera,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_camera:
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                    ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
                }
                return true;

            case R.id.action_gallery:
                    takeImageFromGallery();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
