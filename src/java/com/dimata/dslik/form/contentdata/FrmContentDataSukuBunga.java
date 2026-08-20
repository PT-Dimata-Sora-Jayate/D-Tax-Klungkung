/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSukuBunga;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataSukuBunga extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataSukuBunga entContentDataSukuBunga;
    public static final String FRM_NAME_CONTENT_DATA_SUKU_BUNGA = "FRM_NAME_CONTENT_DATA_SUKU_BUNGA";
    public static final int FRM_FIELD_SUKU_BUNGA_OID = 0;
    public static final int FRM_FIELD_NAMA_SUKU_BUNGA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_SUKU_BUNGA_OID",
        "FRM_FIELD_NAMA_SUKU_BUNGA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataSukuBunga() {
    }

    public FrmContentDataSukuBunga(ContentDataSukuBunga entContentDataSukuBunga) {
        this.entContentDataSukuBunga = entContentDataSukuBunga;
    }

    public FrmContentDataSukuBunga(HttpServletRequest request, ContentDataSukuBunga entContentDataSukuBunga) {
        super(new FrmContentDataSukuBunga(entContentDataSukuBunga), request);
        this.entContentDataSukuBunga = entContentDataSukuBunga;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_SUKU_BUNGA;
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

    public ContentDataSukuBunga getEntityObject() {
        return entContentDataSukuBunga;
    }

    public void requestEntityObject(ContentDataSukuBunga entContentDataSukuBunga) {
        try {
            this.requestParam();
//            entContentDataSukuBunga.setSukuBungaOid(getLong(FRM_FIELD_SUKU_BUNGA_OID));
            entContentDataSukuBunga.setNamaSukuBunga(getString(FRM_FIELD_NAMA_SUKU_BUNGA));
            entContentDataSukuBunga.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataSukuBunga.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
