/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.util;

/**
 *
 * @author Asus
 */
public class Diskon {
    //jatuh tempo pengenaan denda berdasarkan tahun
    public String jatuhTempo = "2021-01-31";//untuk jatuh tempo tahun 2019 & 2020 2%
    public String jatuhTempo21 = "2021-12-31";//untuk jatuh tempo tahun 2021 denda 2%
    public String jatuhTempo22 = "2022-09-31";//untuk jatuh tempo tahun 2022 denda 2%
    public String jatuhTempo23 = "2023-09-31";//untuk jatuh tempo tahun 2022 denda 2%
    public String jatuhTempo24 = "2024-09-31";//untuk jatuh tempo tahun 2022 denda 2%
    public String jatuhTempo25 = "2025-09-31";//untuk jatuh tempo tahun 2022 denda 2%
    
    //2017-132
    //hitung diskon pajak
    public double diskonPajak(int tahun,double tagihan){
        double total = 0;
        double diskon = 0;
        double jmlDiskon = 0;
        
        if(tahun <= 2009){
            //diskon 50%
            diskon = 0.5;
        }else if(tahun >= 2010 && tahun <= 2015){
            //diskon 25%
            diskon = 0.25;
        }
        
        jmlDiskon = tagihan * diskon;
        total = tagihan - jmlDiskon;
        return total;
    }
    
    //hitung jumlah diskon pajak
    public double jumlahDiskon(int tahun,double tagihan){
        double jmlhDiskon = 0;
        double diskon = 0;
        
        if(tahun <= 2009){
            //diskon 50%
            diskon = 0.5;
        }else if(tahun >= 2010 && tahun <= 2015){
            //diskon 25%
            diskon = 0.25;
        }
        
        jmlhDiskon = tagihan * diskon;
        return jmlhDiskon;
    }
    
    public double diskonDenda(int tahun,double denda){
        double total = denda;
        //if (denda < 0 || tahun < 2019){
        //if (denda < 0 || tahun < 2021){
        if (denda < 0 || tahun < 2022){
                total = 0;
        }
        return total;
    }
}
