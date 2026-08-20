/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

/**
 *
 * @author m20n9
 */
import com.dimata.qdep.entity.Entity;

public class ContentDataKantorCabang extends Entity {

    private String namaKantorCabang = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getNamaKantorCabang() {
        return namaKantorCabang;
    }

    public void setNamaKantorCabang(String namaKantorCabang) {
        this.namaKantorCabang = namaKantorCabang;
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
}