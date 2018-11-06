package com.example.user.projectbidanku.Model;

/**
 * Created by user on 14/09/2018.
 */

public class CatatanKesehatan {
    private int id, id_dataKehamilan,pregnancy_age;
    private String checkup_date,recheckup_date, complaint, lab_result, action, advice, checkup_location ,nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private double blood_preasure, body_wieght, fudus_height, fetus_position, fetus_pulse, heart_pulse, swollen_foot;

    public CatatanKesehatan(int id_dataKehamilan, int pregnancy_age, String checkup_date, String recheckup_date, String complaint, String lab_result, String action, String advice, String checkup_location, double blood_preasure, double body_wieght, double fudus_height, double fetus_position, double fetus_pulse, double heart_pulse, double swollen_foot) {
        this.id_dataKehamilan = id_dataKehamilan;
        this.pregnancy_age = pregnancy_age;
        this.checkup_date = checkup_date;
        this.recheckup_date = recheckup_date;
        this.complaint = complaint;
        this.lab_result = lab_result;
        this.action = action;
        this.advice = advice;
        this.checkup_location = checkup_location;
        this.blood_preasure = blood_preasure;
        this.body_wieght = body_wieght;
        this.fudus_height = fudus_height;
        this.fetus_position = fetus_position;
        this.fetus_pulse = fetus_pulse;
        this.heart_pulse = heart_pulse;
        this.swollen_foot = swollen_foot;
    }

    public CatatanKesehatan(int id, String checkup_date) {
        this.id = id;
        this.checkup_date = checkup_date;
    }

    public CatatanKesehatan(int id, int id_dataKehamilan, int pregnancy_age, String checkup_date, String recheckup_date, String complaint, String lab_result, String action, String advice, String checkup_location, double blood_preasure, double body_wieght, double fudus_height, double fetus_position, double fetus_pulse, double heart_pulse, double swollen_foot) {

        this.id = id;
        this.id_dataKehamilan = id_dataKehamilan;
        this.pregnancy_age = pregnancy_age;
        this.checkup_date = checkup_date;
        this.recheckup_date = recheckup_date;
        this.complaint = complaint;
        this.lab_result = lab_result;
        this.action = action;
        this.advice = advice;
        this.checkup_location = checkup_location;
        this.blood_preasure = blood_preasure;
        this.body_wieght = body_wieght;
        this.fudus_height = fudus_height;
        this.fetus_position = fetus_position;
        this.fetus_pulse = fetus_pulse;
        this.heart_pulse = heart_pulse;
        this.swollen_foot = swollen_foot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dataKehamilan() {
        return id_dataKehamilan;
    }

    public void setId_dataKehamilan(int id_dataKehamilan) {
        this.id_dataKehamilan = id_dataKehamilan;
    }

    public int getPregnancy_age() {
        return pregnancy_age;
    }

    public void setPregnancy_age(int pregnancy_age) {
        this.pregnancy_age = pregnancy_age;
    }

    public String getCheckup_date() {
        return checkup_date;
    }

    public void setCheckup_date(String checkup_date) {
        this.checkup_date = checkup_date;
    }

    public String getRecheckup_date() {
        return recheckup_date;
    }

    public void setRecheckup_date(String recheckup_date) {
        this.recheckup_date = recheckup_date;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getLab_result() {
        return lab_result;
    }

    public void setLab_result(String lab_result) {
        this.lab_result = lab_result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getCheckup_location() {
        return checkup_location;
    }

    public void setCheckup_location(String checkup_location) {
        this.checkup_location = checkup_location;
    }

    public double getBlood_preasure() {
        return blood_preasure;
    }

    public void setBlood_preasure(double blood_preasure) {
        this.blood_preasure = blood_preasure;
    }

    public double getBody_wieght() {
        return body_wieght;
    }

    public void setBody_wieght(double body_wieght) {
        this.body_wieght = body_wieght;
    }

    public double getFudus_height() {
        return fudus_height;
    }

    public void setFudus_height(double fudus_height) {
        this.fudus_height = fudus_height;
    }

    public double getFetus_position() {
        return fetus_position;
    }

    public void setFetus_position(double fetus_position) {
        this.fetus_position = fetus_position;
    }

    public double getFetus_pulse() {
        return fetus_pulse;
    }

    public void setFetus_pulse(double fetus_pulse) {
        this.fetus_pulse = fetus_pulse;
    }

    public double getHeart_pulse() {
        return heart_pulse;
    }

    public void setHeart_pulse(double heart_pulse) {
        this.heart_pulse = heart_pulse;
    }

    public double getSwollen_foot() {
        return swollen_foot;
    }

    public void setSwollen_foot(double swollen_foot) {
        this.swollen_foot = swollen_foot;
    }
}
