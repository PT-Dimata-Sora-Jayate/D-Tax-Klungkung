/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.inquery;

/**
 *
 * @author dimata005
 */
public class Bphtb {
    
    private String id = "";
    private String nama = "";
    private String jumlahTagihan = "";
    private String nop = "";
    private String letakObjectPajak = "";
    private String instansi="";
    private String pokok="";
    private String denda="";
    private String alamat="";
    
    private String masaPajak="";
    private String tahunPajak="";
    private String idKey ="";
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * @return the jumlahTagihan
     */
    public String getJumlahTagihan() {
        return jumlahTagihan;
    }

    /**
     * @param jumlahTagihan the jumlahTagihan to set
     */
    public void setJumlahTagihan(String jumlahTagihan) {
        this.jumlahTagihan = jumlahTagihan;
    }

    /**
     * @return the nop
     */
    public String getNop() {
        return nop;
    }

    /**
     * @param nop the nop to set
     */
    public void setNop(String nop) {
        this.nop = nop;
    }

    /**
     * @return the letakObjectPajak
     */
    public String getLetakObjectPajak() {
        return letakObjectPajak;
    }

    /**
     * @param letakObjectPajak the letakObjectPajak to set
     */
    public void setLetakObjectPajak(String letakObjectPajak) {
        this.letakObjectPajak = letakObjectPajak;
    }

    /**
     * @return the instansi
     */
    public String getInstansi() {
        return instansi;
    }

    /**
     * @param instansi the instansi to set
     */
    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    /**
     * @return the pokok
     */
    public String getPokok() {
        return pokok;
    }

    /**
     * @param pokok the pokok to set
     */
    public void setPokok(String pokok) {
        this.pokok = pokok;
    }

    /**
     * @return the denda
     */
    public String getDenda() {
        return denda;
    }

    /**
     * @param denda the denda to set
     */
    public void setDenda(String denda) {
        this.denda = denda;
    }

    /**
     * @return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void setMasaPajak(String masaPajak){
        this.masaPajak = masaPajak;
    }
    
    public String getMasaPajak(){
        return masaPajak;
    }
    
 
    
    public void setTahunPajak(String tahunPajak){
        this.tahunPajak = tahunPajak;
    }
    
    public String getTahunPajak(){
        return tahunPajak;
    }
    
    public void setIdKey(String idKey){
        this.idKey = idKey;
    }
    
    public String getidKey(){
        return idKey;
    }
}
