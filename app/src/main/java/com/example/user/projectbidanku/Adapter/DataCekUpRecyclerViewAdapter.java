package com.example.user.projectbidanku.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import com.example.user.projectbidanku.AppConfiguration.ServerHelper;
import com.example.user.projectbidanku.FragmentMenu.DetailDataKehamilanFragment;
import com.example.user.projectbidanku.Model.CatatanKesehatan;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.Model.VolleyCallbackObject;
import com.example.user.projectbidanku.R;

import java.util.Calendar;
import java.util.List;

/**

 */
public class DataCekUpRecyclerViewAdapter extends RecyclerView.Adapter<DataCekUpRecyclerViewAdapter.ViewHolder> {

    private final List<CatatanKesehatan> mValues;
    private Context context;
    private ServerHelper serverHelper;

    public DataCekUpRecyclerViewAdapter(List<CatatanKesehatan> items, Context context,ServerHelper serverHelper) {
        this.mValues = items;
        this.context = context;
        this.serverHelper = serverHelper;

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
            final TextView txtNama = (TextView) mView.findViewById(R.id.txt_nama);
            final TextView txtPregnancyAge = (TextView) mView.findViewById(R.id.txt_usia);
            final TextView txtTanggalCekup = (TextView) mView.findViewById(R.id.txt_tanggal_cekup);
            final TextView txtTanggalReCekup = (TextView) mView.findViewById(R.id.txt_tanggal_recekup);
            final TextView txtAlamat = (TextView) mView.findViewById(R.id.txt_alamat);
            final TextView txtComplaint = (TextView) mView.findViewById(R.id.txt_complaint);
            final TextView txtHasillab = (TextView) mView.findViewById(R.id.txt_lab);
            final TextView txtBlood = (TextView) mView.findViewById(R.id.txt_bloodpreasure);
            final TextView txtBeratBadan = (TextView) mView.findViewById(R.id.txt_beratBadan);
            final TextView txtFundus = (TextView) mView.findViewById(R.id.txt_fudus);
            final TextView txtFtuspos = (TextView) mView.findViewById(R.id.txt_futuspos);
            final TextView txtFtusPulse = (TextView) mView.findViewById(R.id.txt_fetusPulse);
            final TextView txtHeart = (TextView) mView.findViewById(R.id.txt_heart);
            final TextView txtSwoolean = (TextView) mView.findViewById(R.id.txt_swoolen);

            Log.e("idCatatan",mItem.getId()+"");

            serverHelper.showhealtyNoteDetail(mItem.getId(), new VolleyCallbackObject() {
                @Override
                public void onSuccess(Object object) {
                    CatatanKesehatan myItem = (CatatanKesehatan) object;

                    txtNama.setText(myItem.getCheckup_date());
                    txtPregnancyAge.setText("Usia Kandungan :"+myItem.getPregnancy_age());
                    txtTanggalCekup.setText("Tanggal Cekup :"+myItem.getCheckup_date());
                    txtTanggalReCekup.setText("Tanggal Recekup :"+myItem.getRecheckup_date());
                    txtAlamat.setText("Alamat :"+myItem.getCheckup_location());
                    txtComplaint.setText("Complaint :"+myItem.getComplaint());
                    txtHasillab.setText("Complaint :"+myItem.getLab_result());
                    txtBlood.setText("Tekanan Darah :"+myItem.getBlood_preasure());
                    txtBeratBadan.setText("Berat Badan :"+myItem.getBody_wieght());
                    txtFundus.setText("Tinggi Fudus :"+myItem.getFudus_height());
                    txtFtuspos.setText("Posisi Fetus :"+myItem.getFetus_position());
                    txtFtusPulse.setText("Fetus Pulse :"+myItem.getFetus_pulse());
                    txtHeart.setText("Heart pulse :"+myItem.getHeart_pulse());
                    txtSwoolean.setText("Swollen Foot :"+myItem.getSwollen_foot());
                }
            });

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
