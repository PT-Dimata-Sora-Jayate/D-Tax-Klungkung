/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

import com.dimata.qdep.entity.Entity;
import java.util.Date;

/**
 *
 * @author Dewa
 */
public class PeriodeCabang extends Entity {

    private long periodeId = 0;
    private long cabangId = 0;
    private String keterangan = "";
    private int status = 0;
    private long userId = 0;
    private Date tanggalCreate = null;
    private Date tanggalPosting = null;
    private String namaPeriode = "";
    private String namaCabang = "";

    public String getNamaPeriode() {
        return namaPeriode;
    }

    public void setNamaPeriode(String namaPeriode) {
        this.namaPeriode = namaPeriode;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public long getPeriodeId() {
        return periodeId;
    }

    public void setPeriodeId(long periodeId) {
        this.periodeId = periodeId;
    }

    public long getCabangId() {
        return cabangId;
    }

    public void setCabangId(long cabangId) {
        this.cabangId = cabangId;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getTanggalCreate() {
        return tanggalCreate;
    }

    public void setTanggalCreate(Date tanggalCreate) {
        this.tanggalCreate = tanggalCreate;
    }

    public Date getTanggalPosting() {
        return tanggalPosting;
    }

    public void setTanggalPosting(Date tanggalPosting) {
        this.tanggalPosting = tanggalPosting;
    }

}
