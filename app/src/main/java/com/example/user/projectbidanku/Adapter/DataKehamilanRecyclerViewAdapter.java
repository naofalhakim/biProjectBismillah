package com.example.user.projectbidanku.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.projectbidanku.FragmentMenu.BerandaFragment;
import com.example.user.projectbidanku.FragmentMenu.DetailDataKehamilanFragment;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.R;

import java.util.List;

/**

 */
public class DataKehamilanRecyclerViewAdapter extends RecyclerView.Adapter<DataKehamilanRecyclerViewAdapter.ViewHolder> {

    private final List<DataKehamilan> mValues;
    private Context context;

    public DataKehamilanRecyclerViewAdapter(List<DataKehamilan> items, Context context) {
        this.mValues = items;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_kehamilan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText("Kehamilan ke-"+holder.mItem.getNama());
        holder.mContentKeterangan.setText(holder.mItem.getLast_mens_date());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;

        public final TextView mContentView;
        public final TextView mContentKeterangan;
        public final ImageView mImageView;

        public DataKehamilan mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.txt_nama);
            mContentKeterangan = (TextView) view.findViewById(R.id.txt_tanggal_hamil);
            mImageView = (ImageView) view.findViewById(R.id.img_info1);
            mView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            DetailDataKehamilanFragment fragment = new DetailDataKehamilanFragment();
            Bundle args = new Bundle();
            args.putString("Nama", mItem.getNama());
            args.putString("T_hamil", mContentKeterangan.getText().toString());
            args.putString("T_lahir", mItem.getEstimation_birth_date());
            args.putInt("Arm", mItem.getArm_size());
            args.putDouble("B_height", mItem.getBody_height());
            args.putString("Contra", mItem.getContraception_methode());
            args.putBoolean("kek", mItem.isKEK());

            fragment.setArguments(args);

            FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_menu, fragment);
            fragmentTransaction.commit();
        }
    }
}
