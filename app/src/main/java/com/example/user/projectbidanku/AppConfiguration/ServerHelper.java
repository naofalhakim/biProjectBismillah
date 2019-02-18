package com.example.user.projectbidanku.AppConfiguration;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.user.projectbidanku.Model.Anak;
import com.example.user.projectbidanku.Model.CatatanKesehatan;
import com.example.user.projectbidanku.Model.DataKehamilan;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.Patient;
import com.example.user.projectbidanku.Model.VolleyCalback;
import com.example.user.projectbidanku.Model.VolleyCallbackObject;
import com.example.user.projectbidanku.Model.VolleyListCalback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 16/09/2018.
 */

public class ServerHelper {
    private Context context;

    public ServerHelper(Context context) {
        this.context = context;
    }

    public void loginFunction(final String email, final String password, final VolleyCalback callback){

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Waiting for Login");
        progressDialog.show();
        if(!email.equals("") && !password.equals(null)){
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    ConfigDB.LOGIN+email+"/"+password,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                if(!jsonArray.get(0).equals(null)){
                                    JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                                    callback.onSuccess(jsonObject.getString("email"),jsonObject.getString("id_patients"));
                                }else{
                                    callback.onSuccess("","");
                                }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("jsonku",error.getMessage());
                    progressDialog.dismiss();
                }
            }
            );

            RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
        }

    }

    public void addBabyNameFunction(final int id, final String nama, final String arti, final String jk, final VolleyCalback callback) {
        Log.e("dataku", id + "/" + nama + "/" + arti + "/" + jk + "/");

        JSONObject json = new JSONObject();
        try {
            json.put("id_pasien",id);
            json.put("jenis_kelamin",jk);
            json.put("nama_bayi",nama);
            json.put("arti_nama",arti);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Menambahkan kedalam list");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConfigDB.INSERT_NAMA_BAYI, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Volley:Response ", "" + response.toString());
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Volley:Response ", "" + error.getMessage());
                progressDialog.dismiss();
            }
        });

        RequestHandler.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void showBabyName(int id_pasien, final VolleyListCalback callback){
        final List<Object> namaCalonBayis = new ArrayList();

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Showing Babies Name");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.GET_NAMA_BAYI+id_pasien,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.get(0) != null){
                                JSONArray jsonArray1 = (JSONArray) jsonArray.get(0);
                                JSONObject jsonObject;
                                for (int i = 0; i < jsonArray1.length(); i++) {
                                    jsonObject = jsonArray1.getJSONObject(i);
                                    namaCalonBayis.add(new NamaCalonBayi(
                                            jsonObject.getInt("id_patients"),
                                            jsonObject.getString("gender"),
                                            jsonObject.getInt("id_baby_names"),
                                            jsonObject.getString("name"),
                                            jsonObject.getString("meaning")
                                    ));
                                }
                                callback.onSuccess(namaCalonBayis);
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        );

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

//    === ini yang belum ada url API nya

    public void showDetailMomPregnancy(final int id, final VolleyCallbackObject callback){
        //select_detail_patient
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Waiting");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_DETAIL_PATIENT+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.get(0) != null){
                                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                                callback.onSuccess(new Patient(id,
                                        jsonObject.getInt("last_child_age"),
                                        jsonObject.getInt("labour_count"),
                                        jsonObject.getInt("misscarriage_count"),
                                        jsonObject.getInt("born_died_count"),
                                        jsonObject.getInt("premature_child_count"),
                                        jsonObject.getInt("life_child_count"),
                                        jsonObject.getString("jkn_number"),
                                        jsonObject.getString("religion")));
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("patient_id",String.valueOf(id));
                return super.getParams();
            }
        };

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showDetailMom(final int id, final VolleyCallbackObject callback){
        //select_detail_patient
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Waiting");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_DETAIL_PATIENT+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.length() != 0){
                                Log.e("husbangeror",jsonArray.get(0)+"");
                                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                                callback.onSuccess(new Patient(id,
                                        jsonObject.getInt("pregnancy_count"),
                                        jsonObject.getString("name"),
                                        jsonObject.getString("address"),
                                        jsonObject.getString("email"),
                                        jsonObject.getString("education"),
                                        jsonObject.getString("phone_number")
                                        ));
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("patient_id",String.valueOf(id));
                return super.getParams();
            }
        };

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showDetailAlamat(final int id, final VolleyCallbackObject callback){
        //select_detail_patient
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Waiting");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_DETAIL_PATIENT+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.get(0) != null){
                                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                                callback.onSuccess(new Patient(id,
                                        jsonObject.getString("address"),
                                        jsonObject.getString("phone_number"),
                                        jsonObject.getString("sub_district"),
                                        jsonObject.getString("district")
                                        ));
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("patient_id",String.valueOf(id));
                return super.getParams();
            }
        };;

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showDetailHusband(final int id, final VolleyCallbackObject callback){
        //select_detail_husband
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Waiting");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_DETAIL_HUSBAND+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                            if(jsonArray1.length() != 0){
                                Log.e("myhusband",jsonArray1.get(0)+"");
                                JSONObject jsonObject = (JSONObject) jsonArray1.get(0);

                                callback.onSuccess(new Patient(
                                        Integer.parseInt(jsonObject.getString("id_husbands")),
                                        jsonObject.getString("name_husbands"),
                                        jsonObject.getString("birth_place_husbands")+","+jsonObject.getString("birth_date_husbands"),
                                        jsonObject.getString("occupation_husbands"),
                                        jsonObject.getString("education_husbands"),
                                        jsonObject.getString("religion_husbands"),
                                        jsonObject.getString("blood_type_id_husbands")
                                        ));
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("patient_id",String.valueOf(id));
                return super.getParams();
            }
        };;

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showChildren(final int id_pasien, final VolleyListCalback callback){
        final List<Object> children = new ArrayList();
        //select_childs
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Showing Babies Name");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_CHILDS+id_pasien,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.get(0) != null){
                                JSONArray jsonArray1 = (JSONArray) jsonArray.get(0);
                                JSONObject jsonObject;
                                for (int i = 0; i < jsonArray1.length(); i++) {
                                    jsonObject = jsonArray1.getJSONObject(i);
                                    children.add(new Anak(
                                            1,
                                            jsonObject.getInt("child_order"),
                                            1,
                                            jsonObject.getString("name"),
                                            jsonObject.getString("birth_certificate_number"),
                                            jsonObject.getString("birth_place")+","+jsonObject.getString("birth_date")
                                    ));
                                }
                                callback.onSuccess(children);
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("patient_id",String.valueOf(id_pasien));
                return super.getParams();
            }
        };

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showPregnancy(int id_pasien, final VolleyListCalback callback){
        final List<Object> pregnancyList = new ArrayList();
        //select_pregnancy
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Showing Your Pregnancy List");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_PREGNANCY+id_pasien,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.get(0) != null){
                                JSONArray jsonArray1 = (JSONArray) jsonArray.get(0);
                                JSONObject jsonObject;
                                for (int i = 0; i < jsonArray1.length(); i++) {
                                    jsonObject = jsonArray1.getJSONObject(i);
                                    DataKehamilan dataKehamilan = new DataKehamilan(
                                            jsonObject.getInt("id_pregnancy_info"),
                                            jsonObject.getString("last_menstruation_date"),
                                            ""
                                    );
                                    dataKehamilan.setNama(""+(i+1));
                                    pregnancyList.add(dataKehamilan);
                                }
                                callback.onSuccess(pregnancyList);
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        );

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showPregnancyDetail(final int id_pregnancy, final VolleyCallbackObject callback){
        //select_pregnancy_detail
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Showing Your Pregnancy List");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_PREGNANCY_DETAIL+id_pregnancy,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.e("mykehamilan",response);
                            JSONArray jsonArray = new JSONArray(response);
                            JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                            if(jsonArray1.get(0) != null){
                                JSONObject jsonObject;
                                jsonObject = jsonArray1.getJSONObject(0);
                                callback.onSuccess(new DataKehamilan(
                                        jsonObject.getInt("id_pregnancy_info"), jsonObject.getInt("upper_arm_circumference"),
                                        jsonObject.getString("last_menstruation_date"),
                                        jsonObject.getString("estimated_birth_date"),
                                        jsonObject.getString("contraception_method"),
                                        jsonObject.getString("KEK"),
                                        jsonObject.getDouble("body_height")
                                ));
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("id",String.valueOf(id_pregnancy));
                return super.getParams();
            }
        };;

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showHealtyNotes(final int id_pregnancy, final VolleyListCalback callback){
        final List<Object> healtyNotesList = new ArrayList();
        //select_healtynotes
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Showing Your Notes List");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_HEALTYNOTES+id_pregnancy,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            Log.e("catatankesehatan",response+"");
                            JSONArray jsonArray = new JSONArray(response);
                            if(jsonArray.get(0) != null){
                                JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                                JSONObject jsonObject;
                                for (int i = 0; i < jsonArray1.length(); i++) {
                                    jsonObject = jsonArray1.getJSONObject(i);
                                    CatatanKesehatan catatanKesehatan = new CatatanKesehatan(
                                            jsonObject.getInt("id_healthy_notes"),
                                            jsonObject.getString("checkup_date")
                                    );
                                    catatanKesehatan.setNama(""+(i+1));
                                    healtyNotesList.add(catatanKesehatan);
                                }
                                callback.onSuccess(healtyNotesList);
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("pregnancy_info_id",String.valueOf(id_pregnancy));
                return super.getParams();
            }
        };


        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void showhealtyNoteDetail(final int id_pregnancy, final VolleyCallbackObject callback){
        //select_healtynotes_detail
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Showing Your Pregnancy List");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                ConfigDB.SELECT_HEALTYNOTES_DETAIL+id_pregnancy,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONArray jsonArray1 = jsonArray.getJSONArray(0);
                            if(jsonArray1.get(0) != null){
                                JSONObject jsonObject;
                                jsonObject = jsonArray1.getJSONObject(0);
                                CatatanKesehatan catatanKesehatan = new CatatanKesehatan(
                                        jsonObject.getInt("id_healthy_notes"),
                                        jsonObject.getInt("pregnancy_age"),
                                        jsonObject.getString("checkup_date"),
                                        jsonObject.getString("recheckup_date"),
                                        jsonObject.getString("complaint"),
                                        jsonObject.getString("lab_result"),
                                        jsonObject.getString("action"),
                                        jsonObject.getString("advice"),
                                        jsonObject.getString("checkup_location"),
                                        jsonObject.getDouble("blood_pressure"),
                                        jsonObject.getDouble("body_weight"),
                                        jsonObject.getDouble("fundus_height"),
                                        jsonObject.getDouble("fetus_position"),
                                        jsonObject.getDouble("fetus_pulse"),
                                        jsonObject.getDouble("heart_pulse"),
                                        jsonObject.getDouble("swollen_foot")
                                );
                                callback.onSuccess(catatanKesehatan);
                            }else{
                                callback.onSuccess(null);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("jsonku",error.getMessage());
                progressDialog.dismiss();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> stringStringHashMap = new HashMap();
                stringStringHashMap.put("id_healthy_notes",String.valueOf(id_pregnancy));
                return super.getParams();
            }
        };;

        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);
    }


}
