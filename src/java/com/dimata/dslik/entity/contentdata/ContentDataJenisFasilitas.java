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

public class ContentDataJenisFasilitas extends Entity {

    private String jenisFasilitas = "";
    private String kodeCoreBanking = "";
    private String kodeOjk = "";

    public String getJenisFasilitas() {
        return jenisFasilitas;
    }

    public void setJenisFasilitas(String jenisFasilitas) {
        this.jenisFasilitas = jenisFasilitas;
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
