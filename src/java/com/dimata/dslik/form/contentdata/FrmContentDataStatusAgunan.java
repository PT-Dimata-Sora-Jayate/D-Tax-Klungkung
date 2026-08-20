/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataStatusAgunan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataStatusAgunan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataStatusAgunan entContentDataStatusAgunan;
    public static final String FRM_NAME_CONTENT_DATA_STATUS_AGUNAN = "FRM_NAME_CONTENT_DATA_STATUS_AGUNAN";
    public static final int FRM_FIELD_STATUS_AGUNAN_OID = 0;
    public static final int FRM_FIELD_NAMA_STATUS_AGUNAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_STATUS_AGUNAN_OID",
        "FRM_FIELD_NAMA_STATUS_AGUNAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataStatusAgunan() {
    }

    public FrmContentDataStatusAgunan(ContentDataStatusAgunan entContentDataStatusAgunan) {
        this.entContentDataStatusAgunan = entContentDataStatusAgunan;
    }

    public FrmContentDataStatusAgunan(HttpServletRequest request, ContentDataStatusAgunan entContentDataStatusAgunan) {
        super(new FrmContentDataStatusAgunan(entContentDataStatusAgunan), request);
        this.entContentDataStatusAgunan = entContentDataStatusAgunan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_STATUS_AGUNAN;
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

    public ContentDataStatusAgunan getEntityObject() {
        return entContentDataStatusAgunan;
    }

    public void requestEntityObject(ContentDataStatusAgunan entContentDataStatusAgunan) {
        try {
            this.requestParam();
//            entContentDataStatusAgunan.setStatusAgunanOid(getLong(FRM_FIELD_STATUS_AGUNAN_OID));
            entContentDataStatusAgunan.setNamaStatusAgunan(getString(FRM_FIELD_NAMA_STATUS_AGUNAN));
            entContentDataStatusAgunan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataStatusAgunan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
