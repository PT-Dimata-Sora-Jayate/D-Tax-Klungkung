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

public class ContentDataJenisPemeringkat extends Entity {

    private String namaJenisPemeringkat = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getNamaJenisPemeringkat() {
        return namaJenisPemeringkat;
    }

    public void setNamaJenisPemeringkat(String namaJenisPemeringkat) {
        this.namaJenisPemeringkat = namaJenisPemeringkat;
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
