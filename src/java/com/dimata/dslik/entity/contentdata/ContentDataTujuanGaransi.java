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
public class ContentDataTujuanGaransi extends Entity {

    private String tujuanGaransi = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getTujuanGaransi() {
        return tujuanGaransi;
    }

    public void setTujuanGaransi(String tujuanGaransi) {
        this.tujuanGaransi = tujuanGaransi;
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
