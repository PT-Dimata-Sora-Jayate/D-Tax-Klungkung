/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.debitur;

/**
 *
 * @author dimata005
 */
public class DebiturCompare {
    private String cif = "";
    private String kodeJenisNsb = "";
    private String prevCif = "";
    private String prevJenisNsb="";

    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the kodeJenisNsb
     */
    public String getKodeJenisNsb() {
        return kodeJenisNsb;
    }

    /**
     * @param kodeJenisNsb the kodeJenisNsb to set
     */
    public void setKodeJenisNsb(String kodeJenisNsb) {
        this.kodeJenisNsb = kodeJenisNsb;
    }

    /**
     * @return the prevCif
     */
    public String getPrevCif() {
        return prevCif;
    }

    /**
     * @param prevCif the prevCif to set
     */
    public void setPrevCif(String prevCif) {
        this.prevCif = prevCif;
    }

    /**
     * @return the prevJenisNsb
     */
    public String getPrevJenisNsb() {
        return prevJenisNsb;
    }

    /**
     * @param prevJenisNsb the prevJenisNsb to set
     */
    public void setPrevJenisNsb(String prevJenisNsb) {
        this.prevJenisNsb = prevJenisNsb;
    }
    
}
