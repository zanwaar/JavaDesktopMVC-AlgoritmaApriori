/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tokosembakoberkah.model;

/**
 *
 * @author Lenovo
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransaksiModel {
    private int id;
    private String status;
    private Date tanggal;
    private int idUser;
    private int idSp;
    private String invoice;
    private double subTotal;
    private String username;
    private List<DetailTransaksiModel> detailTransaksiList;

    public TransaksiModel(int id, String status, Date tanggal, int idUser, String invoice, double subTotal, int idSp) {
        this.id = id;
        this.status = status;
        this.tanggal = tanggal;
        this.idUser = idUser;
        this.idSp = idSp;
        this.invoice = invoice;
        this.subTotal = subTotal;
        this.detailTransaksiList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
    public int getIdSp() {
        return idSp;
    }

    public void setIdSp(int idSp) {
        this.idSp = idSp;
    }


    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<DetailTransaksiModel> getDetailTransaksiList() {
        return detailTransaksiList;
    }

    public void setDetailTransaksiList(List<DetailTransaksiModel> detailTransaksiList) {
        this.detailTransaksiList = detailTransaksiList;
    }

    public void addDetailTransaksi(DetailTransaksiModel detailTransaksi) {
        this.detailTransaksiList.add(detailTransaksi);
    }
}
