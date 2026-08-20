/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

import com.dimata.qdep.entity.Entity;

/**
 *
 * @author Dewa
 */
public class ContentDataSkimAkadPembiayaan extends Entity {

    private String namaAkadPembiayaan = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getNamaAkadPembiayaan() {
        return namaAkadPembiayaan;
    }

    public void setNamaAkadPembiayaan(String namaAkadPembiayaan) {
        this.namaAkadPembiayaan = namaAkadPembiayaan;
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
