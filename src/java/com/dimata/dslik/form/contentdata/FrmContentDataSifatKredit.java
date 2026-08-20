/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSifatKredit;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataSifatKredit extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataSifatKredit entContentDataSifatKredit;
    public static final String FRM_NAME_CONTENT_DATA_SIFAT_KREDIT = "FRM_NAME_CONTENT_DATA_SIFAT_KREDIT";
    public static final int FRM_FIELD_SIFAT_KREDIT_OID = 0;
    public static final int FRM_FIELD_SIFAT_KREDIT = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_SIFAT_KREDIT_OID",
        "FRM_FIELD_SIFAT_KREDIT",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataSifatKredit() {
    }

    public FrmContentDataSifatKredit(ContentDataSifatKredit entContentDataSifatKredit) {
        this.entContentDataSifatKredit = entContentDataSifatKredit;
    }

    public FrmContentDataSifatKredit(HttpServletRequest request, ContentDataSifatKredit entContentDataSifatKredit) {
        super(new FrmContentDataSifatKredit(entContentDataSifatKredit), request);
        this.entContentDataSifatKredit = entContentDataSifatKredit;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_SIFAT_KREDIT;
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

    public ContentDataSifatKredit getEntityObject() {
        return entContentDataSifatKredit;
    }

    public void requestEntityObject(ContentDataSifatKredit entContentDataSifatKredit) {
        try {
            this.requestParam();
//    entContentDataSifatKredit.setSifatKreditOid(getLong(FRM_FIELD_SIFAT_KREDIT_OID));
            entContentDataSifatKredit.setSifatKredit(getString(FRM_FIELD_SIFAT_KREDIT));
            entContentDataSifatKredit.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataSifatKredit.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
