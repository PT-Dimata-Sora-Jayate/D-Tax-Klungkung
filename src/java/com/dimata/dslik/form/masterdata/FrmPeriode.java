/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.masterdata;

import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.util.Formater;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmPeriode extends FRMHandler implements I_FRMInterface, I_FRMType {

    private Periode entPeriode;
    public static final String FRM_NAME_PERIODE = "FRM_NAME_PERIODE";
    public static final int FRM_FIELD_PERIODE_ID = 0;
    public static final int FRM_FIELD_TGL_AWAL = 1;
    public static final int FRM_FIELD_TGL_AKHIR = 2;
    public static final int FRM_FIELD_NAMA = 3;
    public static final int FRM_FIELD_KETERANGAN = 4;
    public static final int FRM_FIELD_POSTED = 5;
    public static final int FRM_FIELD_TGL_TERAKHIR_ENTRY = 6;

    public static String[] fieldNames = {
        "FRM_FIELD_PERIODE_ID",
        "FRM_FIELD_TGL_AWAL",
        "FRM_FIELD_TGL_AKHIR",
        "FRM_FIELD_NAMA",
        "FRM_FIELD_KETERANGAN",
        "FRM_FIELD_POSTED",
        "FRM_FIELD_TGL_TERAKHIR_ENTRY"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_STRING
    };

    public FrmPeriode() {
    }

    public FrmPeriode(Periode entPeriode) {
        this.entPeriode = entPeriode;
    }

    public FrmPeriode(HttpServletRequest request, Periode entPeriode) {
        super(new FrmPeriode(entPeriode), request);
        this.entPeriode = entPeriode;
    }

    public String getFormName() {
        return FRM_NAME_PERIODE;
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

    public Periode getEntityObject() {
        return entPeriode;
    }

    public void requestEntityObject(Periode entPeriode) {
        try {
            this.requestParam();
            entPeriode.setTglAwal(Formater.formatDate(getString(FRM_FIELD_TGL_AWAL),"yyyy-MM-dd"));
            entPeriode.setTglAkhir(Formater.formatDate(getString(FRM_FIELD_TGL_AKHIR),"yyyy-MM-dd"));
            entPeriode.setNama(getString(FRM_FIELD_NAMA));
            entPeriode.setKeterangan(getString(FRM_FIELD_KETERANGAN));
            entPeriode.setPosted(getInt(FRM_FIELD_POSTED));
            entPeriode.setTglTerakhirEntry(Formater.formatDate(getString(FRM_FIELD_TGL_TERAKHIR_ENTRY),"yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
