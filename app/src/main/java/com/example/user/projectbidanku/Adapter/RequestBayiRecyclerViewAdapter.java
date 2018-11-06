package com.example.user.projectbidanku.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.projectbidanku.Model.Anak;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.R;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class RequestBayiRecyclerViewAdapter extends RecyclerView.Adapter<RequestBayiRecyclerViewAdapter.ViewHolder> {

//    private final List<Anak> mValues;
    private Context context;
    private List<Anak> anaks = new ArrayList();

    public RequestBayiRecyclerViewAdapter(List<Anak> items, Context context) {
        this.anaks = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_nama_siap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = anaks.get(position);
        holder.mContentView.setText(holder.mItem.getNama());
        holder.mContentAkte.setText(holder.mItem.getKodeAkta());
        holder.mContentKeterangan.setText(holder.mItem.getTTL());

        if (holder.mItem.getJeniskelamin() % 2 != 0){
            holder.mImageView.setImageResource(R.drawable.ic_male_baby);
            holder.mContentKelamin.setText("Laki - Laki");

        }else{
            holder.mImageView.setImageResource(R.drawable.ic_female_baby);
            holder.mContentKelamin.setText("Perempuan");
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return  anaks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mContentView;
        public final TextView mContentKeterangan;
        public final TextView mContentAkte;
        public final TextView mContentKelamin;
        public final ImageView mImageView;

        public Anak mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.txt_nama_bayi);
            mContentKeterangan = (TextView) view.findViewById(R.id.txt_ttl);
            mContentAkte = (TextView) view.findViewById(R.id.txt_noAkte);
            mContentKelamin = (TextView) view.findViewById(R.id.txt_kelamin);
            mImageView = (ImageView) view.findViewById(R.id.img_info);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
