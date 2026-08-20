/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

/**
 *
 * @author m20n9
 */
import com.dimata.qdep.entity.Entity;

public class ContentDataBidangUsahaEnamDigit extends Entity {

    private String namaBidangUsaha = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getNamaBidangUsaha() {
        return namaBidangUsaha;
    }

    public void setNamaBidangUsaha(String namaBidangUsaha) {
        this.namaBidangUsaha = namaBidangUsaha;
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
