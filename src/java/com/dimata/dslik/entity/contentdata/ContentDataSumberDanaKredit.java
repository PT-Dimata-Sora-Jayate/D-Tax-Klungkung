/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

import com.dimata.qdep.entity.Entity;

/**
 *
 * @author dimata005
 */
public class ContentDataSumberDanaKredit extends Entity{
    
    private String sumberDanaKredit = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";


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
     * @return the sumberDanaKredit
     */
    public String getSumberDanaKredit() {
        return sumberDanaKredit;
    }

    /**
     * @param sumberDanaKredit the sumberDanaKredit to set
     */
    public void setSumberDanaKredit(String sumberDanaKredit) {
        this.sumberDanaKredit = sumberDanaKredit;
    }
}
