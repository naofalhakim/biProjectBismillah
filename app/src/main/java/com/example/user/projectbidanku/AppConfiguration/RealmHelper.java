package com.example.user.projectbidanku.AppConfiguration;

import com.example.user.projectbidanku.Model.Foto;
import com.example.user.projectbidanku.Model.NamaCalonBayi;
import com.example.user.projectbidanku.Model.NamaCalonBayiFavorit;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by user on 10/11/2018.
 */

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public int getMaxFoto(){
        Number currentData = realm.where(Foto.class).max("id");
        int numberId;
        if(currentData==null){
            numberId = 1;
        }else{
            numberId = currentData.intValue()+1;
        }
        return numberId;
    }

    public void saveFotoCalonBayi(Foto foto){
        realm.beginTransaction();
        realm.copyToRealm(foto);
        realm.commitTransaction();
    }

    public List<Foto> selectFotoCalonBayi(){
        RealmResults<Foto> namaCalonBayis = realm.where(Foto.class).findAll();
        return namaCalonBayis;
    }

    public int getMaxNamaFavoritBayi(){
        Number currentData = realm.where(NamaCalonBayiFavorit.class).max("id");
        int numberId;
        if(currentData==null){
            numberId = 1;
        }else{
            numberId = currentData.intValue()+1;
        }
        return numberId;
    }

    public void saveNamaCalonBayiFirst(NamaCalonBayi namaCalonBayi){
            realm.beginTransaction();
            realm.copyToRealm(namaCalonBayi);
            realm.commitTransaction();
    }

    public void saveNamaCalonBayiFavorit(NamaCalonBayi namaCalonBayi, int idUser){
        NamaCalonBayiFavorit namaCalonBayiFavorit =
                new NamaCalonBayiFavorit(namaCalonBayi.getJeniskelamin(),namaCalonBayi.getNama(),namaCalonBayi.getArti());
        realm.beginTransaction();
        namaCalonBayiFavorit.setId(getMaxNamaFavoritBayi());
        namaCalonBayiFavorit.setId_user(idUser);
        realm.copyToRealm(namaCalonBayiFavorit);
        realm.commitTransaction();
    }

    public List<NamaCalonBayi> selectNamaCalonBayi(){
        RealmResults<NamaCalonBayi> namaCalonBayis = realm.where(NamaCalonBayi.class).findAll();
        return namaCalonBayis;
    }

    public NamaCalonBayi selectNamaBayiFav(String nama){
        NamaCalonBayi namaCalonBayi = realm.where(NamaCalonBayi.class).equalTo("nama",nama).findFirst();
        return namaCalonBayi;
    }

    public List<NamaCalonBayiFavorit> selectNamaCalonBayiFavorit(int idUser){
        RealmResults<NamaCalonBayiFavorit> namaCalonBayis = realm.where(NamaCalonBayiFavorit.class).equalTo("id_bayi",idUser).findAll();
        return namaCalonBayis;
    }

    public void updateDataRekomendasiNama(final int id, final int sign){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NamaCalonBayi namaCalonBayi = realm.where(NamaCalonBayi.class).equalTo("id", id).findFirst();
                namaCalonBayi.setSign_fav(sign);
            }
        });
    }

    public void deleteDataNamaFavorit(final int id){
        final RealmResults<NamaCalonBayiFavorit> results = realm.where(NamaCalonBayiFavorit.class).equalTo("id",id).findAll();
        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove single match
                results.deleteFirstFromRealm();
            }
        });
    }

//    public List<Appointment> selectAppointment(int idDoctor){
//        List<Patient> patients = selectPatient(idDoctor);
//        List<Appointment> appointments = new ArrayList();
//        for (int i = 0; i < patients.size(); i++) {
//            RealmResults<Appointment>  a = realm.where(Appointment.class).equalTo("idPatient",patients.get(i).getId()).findAll().sort("date");
//            for (int j = 0; j < a.size(); j++) {
//                appointments.add(a.get(j));
//            }
//        }
//
//        return appointments;
//    }
//
//    public void registerDoctor(DoctorAccount doctorAccount, DoctorProfile doctorProfile){
//        realm.beginTransaction();
//
//        doctorAccount.setId(getMaxDoctorID());
//        doctorProfile.setId(getMaxDoctorID());
//
//        realm.copyToRealm(doctorAccount);
//        realm.copyToRealm(doctorProfile);
//
//        realm.commitTransaction();
//    }
//
//    public DoctorAccount loginDoctor(String email){
//        DoctorAccount doctorAccount = realm.where(DoctorAccount.class).equalTo("username",email).findFirst();
////        Log.e("Akun",doctorAccount.getEmail());
//        return doctorAccount;
//    }
//
//    public void addTask(Task task, int idUser){
//        task.setId(getMaxTaskID());
//        task.setIdUser(idUser);
//
//        realm.beginTransaction();
//        realm.copyToRealm(task);
//        realm.commitTransaction();
//    }
//
//    public List<Task> showAllTask(int idUser){
//        RealmResults<Task>  tasks = realm.where(Task.class).equalTo("idUser",idUser).findAll();
//        return tasks;
//    }
//
//    public List<Quote> showAllQuote (int idUser){
//        RealmResults<Quote>  quotes = realm.where(Quote.class).equalTo("patient",idUser).findAll();
//        return quotes;
//    }
//
//    public Boolean deleteQuote (int idQuote){
//        RealmResults<Quote>  quotes = realm.where(Quote.class).equalTo("id",idQuote).findAll();
//        realm.beginTransaction();
//        boolean a = quotes.deleteAllFromRealm();
//        realm.commitTransaction();
//        Log.e("DeleteQuote",a+"");
//        return a;
//    }
}
