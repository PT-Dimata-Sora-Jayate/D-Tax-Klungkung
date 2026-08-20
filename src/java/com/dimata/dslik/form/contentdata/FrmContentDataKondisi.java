/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataKondisi;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataKondisi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKondisi entContentDataKondisi;
    public static final String FRM_NAME_CONTENT_DATA_KONDISI = "FRM_NAME_CONTENT_DATA_KONDISI";
    public static final int FRM_FIELD_KONDISI_OID = 0;
    public static final int FRM_FIELD_NAMA_KONDISI = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_KONDISI_OID",
        "FRM_FIELD_NAMA_KONDISI",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKondisi() {
    }

    public FrmContentDataKondisi(ContentDataKondisi entContentDataKondisi) {
        this.entContentDataKondisi = entContentDataKondisi;
    }

    public FrmContentDataKondisi(HttpServletRequest request, ContentDataKondisi entContentDataKondisi) {
        super(new FrmContentDataKondisi(entContentDataKondisi), request);
        this.entContentDataKondisi = entContentDataKondisi;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_KONDISI;
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

    public ContentDataKondisi getEntityObject() {
        return entContentDataKondisi;
    }

    public void requestEntityObject(ContentDataKondisi entContentDataKondisi) {
        try {
            this.requestParam();
//    entContentDataKondisi.setKondisiOid(getLong(FRM_FIELD_KONDISI_OID));
            entContentDataKondisi.setNamaKondisi(getString(FRM_FIELD_NAMA_KONDISI));
            entContentDataKondisi.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKondisi.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
