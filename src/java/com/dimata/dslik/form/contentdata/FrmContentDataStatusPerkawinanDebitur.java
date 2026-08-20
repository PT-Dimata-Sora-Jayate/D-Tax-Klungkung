/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataStatusPerkawinanDebitur;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataStatusPerkawinanDebitur extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur;
    public static final String FRM_NAME_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR = "FRM_NAME_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR";
    public static final int FRM_FIELD_STATUS_PERKAWINAN_OID = 0;
    public static final int FRM_FIELD_STATUS_PERKAWINAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_STATUS_PERKAWINAN_OID",
        "FRM_FIELD_STATUS_PERKAWINAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataStatusPerkawinanDebitur() {
    }

    public FrmContentDataStatusPerkawinanDebitur(ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) {
        this.entContentDataStatusPerkawinanDebitur = entContentDataStatusPerkawinanDebitur;
    }

    public FrmContentDataStatusPerkawinanDebitur(HttpServletRequest request, ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) {
        super(new FrmContentDataStatusPerkawinanDebitur(entContentDataStatusPerkawinanDebitur), request);
        this.entContentDataStatusPerkawinanDebitur = entContentDataStatusPerkawinanDebitur;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR;
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

    public ContentDataStatusPerkawinanDebitur getEntityObject() {
        return entContentDataStatusPerkawinanDebitur;
    }

    public void requestEntityObject(ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) {
        try {
            this.requestParam();
//            entContentDataStatusPerkawinanDebitur.setStatusPerkawinanOid(getLong(FRM_FIELD_STATUS_PERKAWINAN_OID));
            entContentDataStatusPerkawinanDebitur.setStatusPerkawinan(getString(FRM_FIELD_STATUS_PERKAWINAN));
            entContentDataStatusPerkawinanDebitur.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataStatusPerkawinanDebitur.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
