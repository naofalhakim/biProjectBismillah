package com.example.user.projectbidanku.Model;

/**
 * Created by user on 14/09/2018.
 */

public class DataKehamilan {
    private int id, arm_size;
    private String last_mens_date, estimation_birth_date, contraception_methode, nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private boolean KEK;
    private double body_height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArm_size() {
        return arm_size;
    }

    public void setArm_size(int arm_size) {
        this.arm_size = arm_size;
    }

    public String getLast_mens_date() {
        return last_mens_date;
    }

    public void setLast_mens_date(String last_mens_date) {
        this.last_mens_date = last_mens_date;
    }

    public String getEstimation_birth_date() {
        return estimation_birth_date;
    }

    public void setEstimation_birth_date(String estimation_birth_date) {
        this.estimation_birth_date = estimation_birth_date;
    }

    public String getContraception_methode() {
        return contraception_methode;
    }

    public void setContraception_methode(String contraception_methode) {
        this.contraception_methode = contraception_methode;
    }

    public boolean isKEK() {
        return KEK;
    }

    public void setKEK(boolean KEK) {
        this.KEK = KEK;
    }

    public double getBody_height() {
        return body_height;
    }

    public void setBody_height(double body_height) {
        this.body_height = body_height;
    }

    public DataKehamilan(int id, int arm_size, String last_mens_date, String estimation_birth_date, String contraception_methode, boolean KEK, double body_height) {

        this.id = id;
        this.arm_size = arm_size;
        this.last_mens_date = last_mens_date;
        this.estimation_birth_date = estimation_birth_date;
        this.contraception_methode = contraception_methode;
        this.KEK = KEK;
        this.body_height = body_height;
    }

    public DataKehamilan(int arm_size, String last_mens_date, String estimation_birth_date, String contraception_methode, boolean KEK, double body_height) {

        this.arm_size = arm_size;
        this.last_mens_date = last_mens_date;
        this.estimation_birth_date = estimation_birth_date;
        this.contraception_methode = contraception_methode;
        this.KEK = KEK;
        this.body_height = body_height;
    }

    public DataKehamilan(int id, String last_mens_date, String estimation_birth_date) {
        this.id = id;
        this.last_mens_date = last_mens_date;
        this.estimation_birth_date = estimation_birth_date;
    }
}
