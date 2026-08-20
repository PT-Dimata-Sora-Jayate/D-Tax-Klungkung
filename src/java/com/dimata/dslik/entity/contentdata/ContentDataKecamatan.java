/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

/**
 *
 * @author dimata005
 */
import com.dimata.qdep.entity.Entity;

public class ContentDataKecamatan extends Entity {

    private String kodeKabupatenKota = "";
    private String namaKecamatan = "";
    private String namaKabupatenKota = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";
    

    public String getKodeKabupatenKota() {
        return kodeKabupatenKota;
    }

    public void setKodeKabupatenKota(String kodeKabupatenKota) {
        this.kodeKabupatenKota = kodeKabupatenKota;
    }

    public String getNamaKabupatenKota() {
        return namaKabupatenKota;
    }

    public void setNamaKabupatenKota(String namaKabupatenKota) {
        this.namaKabupatenKota = namaKabupatenKota;
    }

    public String getKodeCoreBanking() {
        return kodeCoreBanking;
    }

    public void setKodeCoreBanking(String kodeCoreBanking) {
        this.kodeCoreBanking = kodeCoreBanking;
    }

    public String getKodeOjk() {
        return kodeOjk;
    }

    public void setKodeOjk(String kodeOjk) {
        this.kodeOjk = kodeOjk;
    }

    /**
     * @return the namaKecamatan
     */
    public String getNamaKecamatan() {
        return namaKecamatan;
    }

    /**
     * @param namaKecamatan the namaKecamatan to set
     */
    public void setNamaKecamatan(String namaKecamatan) {
        this.namaKecamatan = namaKecamatan;
    }

}
