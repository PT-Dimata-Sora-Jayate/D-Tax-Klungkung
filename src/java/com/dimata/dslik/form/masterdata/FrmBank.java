/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.masterdata;

import com.dimata.dslik.entity.masterdata.Bank;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmBank extends FRMHandler implements I_FRMInterface, I_FRMType {

    private Bank entBank;
    public static final String FRM_NAME_BANK = "FRM_NAME_BANK";
    public static final int FRM_FIELD_BANK_ID = 0;
    public static final int FRM_FIELD_NAMA_BANK = 1;
    public static final int FRM_FIELD_ALAMAT_BANK = 2;
    public static final int FRM_FIELD_PROVINSI = 3;
    public static final int FRM_FIELD_KABUPATEN = 4;
    public static final int FRM_FIELD_EMAIL = 5;

    public static String[] fieldNames = {
        "FRM_FIELD_BANK_ID",
        "FRM_FIELD_NAMA_BANK",
        "FRM_FIELD_ALAMAT_BANK",
        "FRM_FIELD_PROVINSI",
        "FRM_FIELD_KABUPATEN",
        "FRM_FIELD_EMAIL"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmBank() {
    }

    public FrmBank(Bank entBank) {
        this.entBank = entBank;
    }

    public FrmBank(HttpServletRequest request, Bank entBank) {
        super(new FrmBank(entBank), request);
        this.entBank = entBank;
    }

    public String getFormName() {
        return FRM_NAME_BANK;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public Bank getEntityObject() {
        return entBank;
    }

    public void requestEntityObject(Bank entBank) {
        try {
            this.requestParam();
//            entBank.setBankId(getLong(FRM_FIELD_BANK_ID));
            entBank.setNamaBank(getString(FRM_FIELD_NAMA_BANK));
            entBank.setAlamatBank(getString(FRM_FIELD_ALAMAT_BANK));
            entBank.setProvinsi(getString(FRM_FIELD_PROVINSI));
            entBank.setKabupaten(getString(FRM_FIELD_KABUPATEN));
            entBank.setEmail(getString(FRM_FIELD_EMAIL));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
