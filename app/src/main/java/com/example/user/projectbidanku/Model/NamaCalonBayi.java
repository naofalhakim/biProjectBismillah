package com.example.user.projectbidanku.Model;

/**
 * Created by user on 31/08/2018.
 */

public class NamaCalonBayi {
    private int id;
    private String jeniskelamin;
    private int sign_fav;

    public NamaCalonBayi(int id, String jeniskelamin, int id_bayi, String nama, String arti) {
        this.id = id;
        this.jeniskelamin = jeniskelamin;
        this.id_bayi = id_bayi;
        this.nama = nama;
        this.arti = arti;
        this.sign_fav = 0;
    }

    public int getSign_fav() {
        return sign_fav;
    }

    public void setSign_fav(int sign_fav) {
        this.sign_fav = sign_fav;
    }

    public int getId_bayi() {
        return id_bayi;
    }

    public void setId_bayi(int id_bayi) {
        this.id_bayi = id_bayi;
    }

    private int id_bayi;
    private String nama, arti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public NamaCalonBayi(String jeniskelamin, String nama, String arti) {

        this.jeniskelamin = jeniskelamin;
        this.nama = nama;
        this.arti = arti;
    }

    public NamaCalonBayi() {

    }

    public NamaCalonBayi(int id, String jeniskelamin, String nama, String arti) {

        this.id = id;
        this.jeniskelamin = jeniskelamin;
        this.nama = nama;
        this.arti = arti;
    }
}
