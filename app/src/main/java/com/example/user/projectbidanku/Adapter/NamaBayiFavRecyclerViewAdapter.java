package com.example.user.projectbidanku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.projectbidanku.AppConfiguration.RealmHelper;
import com.example.user.projectbidanku.AppConfiguration.SessionManager;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.NamaCalonBayiFavorit;
import com.example.user.projectbidanku.R;

import java.util.List;

/**

 */
public class NamaBayiFavRecyclerViewAdapter extends RecyclerView.Adapter<NamaBayiFavRecyclerViewAdapter.ViewHolder> {

    private final List<NamaCalonBayiFavorit> mValues;
    private Context context;
    private RealmHelper realmHelper;
    private SessionManager sessionManager;

    public NamaBayiFavRecyclerViewAdapter(List<NamaCalonBayiFavorit> items, Context context, RealmHelper realmHelper) {
        this.mValues = items;
        this.context = context;
        this.realmHelper = realmHelper;
        this.sessionManager = new SessionManager(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_nama, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(holder.mItem.getNama());
        holder.mContentKeterangan.setText(holder.mItem.getArti());
        if (holder.mItem.getJeniskelamin().equals("L")){
            holder.mImageView.setText("L");
        }else{
            holder.mImageView.setText("P");
        }

        holder.imageView.setImageResource(R.drawable.ic_close);
        holder.imageView.setBackgroundResource(R.color.colorPrimaryDark);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NamaCalonBayi namaCalonBayi = realmHelper.selectNamaBayiFav(holder.mItem.getNama());
                if(namaCalonBayi != null){
                    Log.e("namafav","bukan rekomendasi");
                    realmHelper.updateDataRekomendasiNama(namaCalonBayi.getId(),0);
                }
                realmHelper.deleteDataNamaFavorit(holder.mItem.getId());
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mContentView;
        public final TextView mContentKeterangan;
        public final TextView mImageView;
        public final ImageView imageView;

        public NamaCalonBayiFavorit mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.txt_nama_bayi);
            mContentKeterangan = (TextView) view.findViewById(R.id.txt_arti);
            mImageView = (TextView) view.findViewById(R.id.img_info1);
            imageView = (ImageView) view.findViewById(R.id.img_info);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
