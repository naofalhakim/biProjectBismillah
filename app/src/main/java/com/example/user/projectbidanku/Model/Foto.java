package com.example.user.projectbidanku.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by user on 31/08/2018.
 */

public class Foto extends RealmObject{
    @PrimaryKey
    private int id;

    private String alamat, email;

    public Foto(int id, String alamat, String email) {
        this.id = id;
        this.alamat = alamat;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Foto(String alamat, String email) {

        this.alamat = alamat;
        this.email = email;
    }

    public Foto() {

    }
}
