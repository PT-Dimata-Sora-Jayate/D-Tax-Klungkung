/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.masterdata;

import com.dimata.dslik.entity.masterdata.PeriodeCabang;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmPeriodeCabang extends FRMHandler implements I_FRMInterface, I_FRMType {

    private PeriodeCabang entPeriodeCabang;
    public static final String FRM_NAME_PERIODE_CABANG = "FRM_NAME_PERIODE_CABANG";
    public static final int FRM_FIELD_PERIODE_CABANG_ID = 0;
    public static final int FRM_FIELD_PERIODE_ID = 1;
    public static final int FRM_FIELD_CABANG_ID = 2;
    public static final int FRM_FIELD_KETERANGAN = 3;
    public static final int FRM_FIELD_STATUS = 4;
    public static final int FRM_FIELD_USER_ID = 5;
    public static final int FRM_FIELD_TANGGAL_CREATE = 6;
    public static final int FRM_FIELD_TANGGAL_POSTING = 7;

    public static String[] fieldNames = {
        "FRM_FIELD_PERIODE_CABANG_ID",
        "FRM_FIELD_PERIODE_ID",
        "FRM_FIELD_CABANG_ID",
        "FRM_FIELD_KETERANGAN",
        "FRM_FIELD_STATUS",
        "FRM_FIELD_USER_ID",
        "FRM_FIELD_TANGGAL_CREATE",
        "FRM_FIELD_TANGGAL_POSTING"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_INT,
        TYPE_LONG,
        TYPE_DATE,
        TYPE_DATE
    };

    public FrmPeriodeCabang() {
    }

    public FrmPeriodeCabang(PeriodeCabang entPeriodeCabang) {
        this.entPeriodeCabang = entPeriodeCabang;
    }

    public FrmPeriodeCabang(HttpServletRequest request, PeriodeCabang entPeriodeCabang) {
        super(new FrmPeriodeCabang(entPeriodeCabang), request);
        this.entPeriodeCabang = entPeriodeCabang;
    }

    public String getFormName() {
        return FRM_NAME_PERIODE_CABANG;
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

    public PeriodeCabang getEntityObject() {
        return entPeriodeCabang;
    }

    public void requestEntityObject(PeriodeCabang entPeriodeCabang) {
        try {
            this.requestParam();
//            entPeriodeCabang.setPeriodeCabangId(getLong(FRM_FIELD_PERIODE_CABANG_ID));
            entPeriodeCabang.setPeriodeId(getLong(FRM_FIELD_PERIODE_ID));
            entPeriodeCabang.setCabangId(getLong(FRM_FIELD_CABANG_ID));
            entPeriodeCabang.setKeterangan(getString(FRM_FIELD_KETERANGAN));
            entPeriodeCabang.setStatus(getInt(FRM_FIELD_STATUS));
            entPeriodeCabang.setUserId(getLong(FRM_FIELD_USER_ID));
            entPeriodeCabang.setTanggalCreate(getDate(FRM_FIELD_TANGGAL_CREATE));
            entPeriodeCabang.setTanggalPosting(getDate(FRM_FIELD_TANGGAL_POSTING));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
