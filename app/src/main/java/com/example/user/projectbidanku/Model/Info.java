package com.example.user.projectbidanku.Model;

/**
 * Created by user on 31/08/2018.
 */

public class Info {
    private String Judul;
    private String[] contents = new String[5];
    private int img,id;
    private int numofCOntents;

    public Info(String judul,int img) {
        Judul = judul;
        this.img = img;
    }

    public Info() {

    }

    public String getJudul() {

        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getContent(int id) {
        return contents[id];
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    public void setContents() {
        for (int i = 0; i < contents.length; i++) {
            this.contents[i] = i+"";
        }
    }

    public void setContent(String content) {
        this.contents[numofCOntents] = content;
        numofCOntents++;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Info(String judul, int img, int id) {
        Judul = judul;
        this.img = img;
        this.id = id;
    }
}