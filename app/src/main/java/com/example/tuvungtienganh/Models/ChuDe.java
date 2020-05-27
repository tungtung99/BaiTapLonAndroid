package com.example.tuvungtienganh.Models;

public class ChuDe {
    private int idChuDe;
    private String name;
    private byte[] anh;
    public int getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(int idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public ChuDe() {
    }

    public ChuDe(int idChuDe, String name, byte[] anh) {
        this.idChuDe = idChuDe;
        this.name = name;
        this.anh = anh;
    }
}
