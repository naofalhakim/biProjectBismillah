package com.example.user.projectbidanku.Model;

/**
 * Created by user on 21/09/2018.
 */

public class Patient {
    private int id, age, kehamilanke, usiaAnakTerakhir, jmlmelahrikan, jmlkeguguran, jmlbayimeninggal, jmlprematur, jmlbayihidup;
    private String nama, alamat, email, pendidikan, notelp,kjn, kelurahan, kecamatan, agama, goldar;

    public Patient(int id, int usiaAnakTerakhir, int jmlmelahrikan, int jmlkeguguran, int jmlbayimeninggal, int jmlprematur, int jmlbayihidup, String kjn, String agama) {
        this.id = id;
        this.usiaAnakTerakhir = usiaAnakTerakhir;
        this.jmlmelahrikan = jmlmelahrikan;
        this.jmlkeguguran = jmlkeguguran;
        this.jmlbayimeninggal = jmlbayimeninggal;
        this.jmlprematur = jmlprematur;
        this.jmlbayihidup = jmlbayihidup;
        this.kjn = kjn;
        this.agama = agama;
    }

    public Patient(int id, int kehamilanke, String nama, String alamat, String email, String pendidikan, String notelp) {

        this.id = id;
        this.kehamilanke = kehamilanke;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.pendidikan = pendidikan;
        this.notelp = notelp;
    }

    public Patient(int id, String alamat, String notelp, String kelurahan, String kecamatan) {
        this.id = id;
        this.alamat = alamat;
        this.notelp = notelp;
        this.kelurahan = kelurahan;
        this.kecamatan = kecamatan;
    }

//  ini kusus buat husband alamat == TTL and pendidikan == occupation
    public Patient(int id, String nama, String alamat, String pendidikan, String agama, String goldar) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.pendidikan = pendidikan;
        this.agama = agama;
        this.goldar = goldar;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoldar() {
        return goldar;
    }

    public void setGoldar(String goldar) {
        this.goldar = goldar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getKehamilanke() {
        return kehamilanke;
    }

    public void setKehamilanke(int kehamilanke) {
        this.kehamilanke = kehamilanke;
    }

    public int getUsiaAnakTerakhir() {
        return usiaAnakTerakhir;
    }

    public void setUsiaAnakTerakhir(int usiaAnakTerakhir) {
        this.usiaAnakTerakhir = usiaAnakTerakhir;
    }

    public int getJmlmelahrikan() {
        return jmlmelahrikan;
    }

    public void setJmlmelahrikan(int jmlmelahrikan) {
        this.jmlmelahrikan = jmlmelahrikan;
    }

    public int getJmlkeguguran() {
        return jmlkeguguran;
    }

    public void setJmlkeguguran(int jmlkeguguran) {
        this.jmlkeguguran = jmlkeguguran;
    }

    public int getJmlbayimeninggal() {
        return jmlbayimeninggal;
    }

    public void setJmlbayimeninggal(int jmlbayimeninggal) {
        this.jmlbayimeninggal = jmlbayimeninggal;
    }

    public int getJmlprematur() {
        return jmlprematur;
    }

    public void setJmlprematur(int jmlprematur) {
        this.jmlprematur = jmlprematur;
    }

    public int getJmlbayihidup() {
        return jmlbayihidup;
    }

    public void setJmlbayihidup(int jmlbayihidup) {
        this.jmlbayihidup = jmlbayihidup;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getKjn() {
        return kjn;
    }

    public void setKjn(String kjn) {
        this.kjn = kjn;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }
}
