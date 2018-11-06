package com.example.user.projectbidanku.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.projectbidanku.FragmentMenu.DetailDataKehamilanFragment;
import com.example.user.projectbidanku.Model.CatatanKesehatan;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.R;

import java.util.Calendar;
import java.util.List;

/**

 */
public class DataCekUpRecyclerViewAdapter extends RecyclerView.Adapter<DataCekUpRecyclerViewAdapter.ViewHolder> {

    private final List<CatatanKesehatan> mValues;
    private Context context;

    public DataCekUpRecyclerViewAdapter(List<CatatanKesehatan> items, Context context) {
        this.mValues = items;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_catatan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText("Cek Up ke -"+holder.mItem.getNama());
        holder.mContentKeterangan.setText(holder.mItem.getCheckup_date());
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

        public CatatanKesehatan mItem;

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
            showDetailCatatan();
        }

        public void showDetailCatatan(){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
            View mView = LayoutInflater.from(context).inflate(R.layout.dialog_detail_catatan, null);

            ImageButton button = (ImageButton) mView.findViewById(R.id.btn_close);
            TextView txtNama = (TextView) mView.findViewById(R.id.txt_nama);
            TextView txtPregnancyAge = (TextView) mView.findViewById(R.id.txt_usia);
            TextView txtTanggalCekup = (TextView) mView.findViewById(R.id.txt_tanggal_cekup);
            TextView txtTanggalReCekup = (TextView) mView.findViewById(R.id.txt_tanggal_recekup);
            TextView txtAlamat = (TextView) mView.findViewById(R.id.txt_alamat);
            TextView txtComplaint = (TextView) mView.findViewById(R.id.txt_complaint);
            TextView txtHasillab = (TextView) mView.findViewById(R.id.txt_lab);
            TextView txtBlood = (TextView) mView.findViewById(R.id.txt_bloodpreasure);
            TextView txtBeratBadan = (TextView) mView.findViewById(R.id.txt_beratBadan);
            TextView txtFundus = (TextView) mView.findViewById(R.id.txt_fudus);
            TextView txtFtuspos = (TextView) mView.findViewById(R.id.txt_futuspos);
            TextView txtFtusPulse = (TextView) mView.findViewById(R.id.txt_fetusPulse);
            TextView txtHeart = (TextView) mView.findViewById(R.id.txt_heart);
            TextView txtSwoolean = (TextView) mView.findViewById(R.id.txt_swoolen);

            txtNama.setText("Cekup ke -"+mItem.getNama());
            txtPregnancyAge.setText("Usia Kandungan :"+mItem.getPregnancy_age());
            txtTanggalCekup.setText("Tanggal Cekup :"+mItem.getCheckup_date());
            txtTanggalReCekup.setText("Tanggal Recekup :"+mItem.getRecheckup_date());
            txtAlamat.setText("Alamat :"+mItem.getCheckup_location());
            txtComplaint.setText("Complaint :"+mItem.getComplaint());
            txtHasillab.setText("Complaint :"+mItem.getLab_result());
            txtBlood.setText("Tekanan Darah :"+mItem.getBlood_preasure());
            txtBeratBadan.setText("Berat Badan :"+mItem.getBody_wieght());
            txtFundus.setText("Tinggi Fudus :"+mItem.getFudus_height());
            txtFtuspos.setText("Posisi Fetus :"+mItem.getFetus_position());
            txtFtusPulse.setText("Fetus Pulse :"+mItem.getFetus_pulse());
            txtHeart.setText("Heart pulse :"+mItem.getHeart_pulse());
            txtSwoolean.setText("Swollen Foot :"+mItem.getSwollen_foot());

            mBuilder.setView(mView);
            final AlertDialog dialog = mBuilder.create();
            dialog.show();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }


    }
}
