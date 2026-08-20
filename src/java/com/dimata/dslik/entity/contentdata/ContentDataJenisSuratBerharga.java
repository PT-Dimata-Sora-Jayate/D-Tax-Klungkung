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

public class ContentDataJenisSuratBerharga extends Entity {

    private String jenisSuratBerharga = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getJenisSuratBerharga() {
        return jenisSuratBerharga;
    }

    public void setJenisSuratBerharga(String jenisSuratBerharga) {
        this.jenisSuratBerharga = jenisSuratBerharga;
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
