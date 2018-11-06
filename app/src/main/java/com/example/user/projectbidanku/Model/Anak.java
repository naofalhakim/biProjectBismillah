package com.example.user.projectbidanku.Model;

/**
 * Created by user on 31/08/2018.
 */

public class Anak {
    private int id, jeniskelamin, anakKe, jumlahAnak;
    private String nama, kodeAkta, TTL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(int jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public int getAnakKe() {
        return anakKe;
    }

    public void setAnakKe(int anakKe) {
        this.anakKe = anakKe;
    }

    public int getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(int jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKodeAkta() {
        return kodeAkta;
    }

    public void setKodeAkta(String kodeAkta) {
        this.kodeAkta = kodeAkta;
    }

    public String getTTL() {
        return TTL;
    }

    public void setTTL(String TTL) {
        this.TTL = TTL;
    }

    public Anak(int id, int jeniskelamin, int anakKe, int jumlahAnak, String nama, String kodeAkta, String TTL) {
        this.id = id;
        this.jeniskelamin = jeniskelamin;
        this.anakKe = anakKe;
        this.jumlahAnak = jumlahAnak;
        this.nama = nama;
        this.kodeAkta = kodeAkta;
        this.TTL = TTL;
    }

    public Anak(int jeniskelamin, int anakKe, int jumlahAnak, String nama, String kodeAkta, String TTL) {

        this.jeniskelamin = jeniskelamin;
        this.anakKe = anakKe;
        this.jumlahAnak = jumlahAnak;
        this.nama = nama;
        this.kodeAkta = kodeAkta;
        this.TTL = TTL;
    }
}
