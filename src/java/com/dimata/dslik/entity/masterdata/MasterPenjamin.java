/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

import com.dimata.qdep.entity.Entity;

/**
 *
 * @author Dimata 007
 */
public class MasterPenjamin extends Entity {

    private String noIdPenjamin = "";
    private String jenisIdentitas = "";
    private String namaIdentitas = "";
    private String namaLengkap = "";
    private String kodeGolPenjamin = "";
    private String alamatPenjamin = "";
    private String keterangan = "";

    public String getNoIdPenjamin() {
        return noIdPenjamin;
    }

    public void setNoIdPenjamin(String noIdPenjamin) {
        this.noIdPenjamin = noIdPenjamin;
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas;
    }

    public String getNamaIdentitas() {
        return namaIdentitas;
    }

    public void setNamaIdentitas(String namaIdentitas) {
        this.namaIdentitas = namaIdentitas;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getKodeGolPenjamin() {
        return kodeGolPenjamin;
    }

    public void setKodeGolPenjamin(String kodeGolPenjamin) {
        this.kodeGolPenjamin = kodeGolPenjamin;
    }

    public String getAlamatPenjamin() {
        return alamatPenjamin;
    }

    public void setAlamatPenjamin(String alamatPenjamin) {
        this.alamatPenjamin = alamatPenjamin;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
