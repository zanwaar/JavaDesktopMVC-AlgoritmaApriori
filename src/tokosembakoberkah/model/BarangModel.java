/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah.model;

/**
 *
 * @author Lenovo
 */
public class BarangModel {
    private int id;
    private String kode;
    private String nama;
    private String kategori;
    private String satuan;
    private int stok;
    private int hargaSatuan;

    public BarangModel(int id, String kode, String nama, String kategori, String satuan, int stok, int hargaSatuan) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.kategori = kategori;
        this.satuan = satuan;
        this.stok = stok;
        this.hargaSatuan = hargaSatuan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(int hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }
}