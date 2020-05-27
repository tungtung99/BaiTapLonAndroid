package com.example.tuvungtienganh.Models;

public class TuVung {
    private int id;
    private int idChuDe;
    private int fav;
    private String tuVung;
    private String nghia;
    private String viDu;
    private byte[] anh;

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

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public int getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(int idChuDe) {
        this.idChuDe = idChuDe;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public TuVung() {
    }

    public TuVung(int id,int idChuDe, String tuVung, String nghia, String viDu, byte[] anh, int idlove) {
        this.id = id;
        this.idChuDe = idChuDe;
        this.tuVung = tuVung;
        this.nghia = nghia;
        this.viDu = viDu;
        this.anh = anh;
        this.fav=idlove;
    }
}
