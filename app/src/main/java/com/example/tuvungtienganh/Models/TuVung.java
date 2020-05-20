package com.example.tuvungtienganh.Models;

public class TuVung {
    private int id;
    private String tuVung;
    private String nghia;
    private String viDu;
    private String anh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTuVung() {
        return tuVung;
    }

    public void setTuVung(String tuVung) {
        this.tuVung = tuVung;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getViDu() {
        return viDu;
    }

    public void setViDu(String viDu) {
        this.viDu = viDu;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public TuVung() {
    }

    public TuVung(int id, String tuVung, String nghia, String viDu, String anh) {
        this.id = id;
        this.tuVung = tuVung;
        this.nghia = nghia;
        this.viDu = viDu;
        this.anh = anh;
    }
}
