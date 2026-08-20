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
public class ContentDataKodeProgramPemerintah extends Entity {

    private String namaProgramPemerintah = "";
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
     * @return the namaProgramPemerintah
     */
    public String getNamaProgramPemerintah() {
        return namaProgramPemerintah;
    }

    /**
     * @param namaProgramPemerintah the namaProgramPemerintah to set
     */
    public void setNamaProgramPemerintah(String namaProgramPemerintah) {
        this.namaProgramPemerintah = namaProgramPemerintah;
    }
}
