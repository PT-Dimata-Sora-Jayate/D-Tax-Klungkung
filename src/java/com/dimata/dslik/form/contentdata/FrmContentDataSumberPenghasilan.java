/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSumberPenghasilan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataSumberPenghasilan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataSumberPenghasilan entContentDataSumberPenghasilan;
    public static final String FRM_NAME_CONTENT_DATA_SUMBER_PENGHASILAN = "FRM_NAME_CONTENT_DATA_SUMBER_PENGHASILAN";
    public static final int FRM_FIELD_SUMBER_PENGHASILAN_OID = 0;
    public static final int FRM_FIELD_SUMBER_PENGHASILAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_SUMBER_PENGHASILAN_OID",
        "FRM_FIELD_SUMBER_PENGHASILAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataSumberPenghasilan() {
    }

    public FrmContentDataSumberPenghasilan(ContentDataSumberPenghasilan entContentDataSumberPenghasilan) {
        this.entContentDataSumberPenghasilan = entContentDataSumberPenghasilan;
    }

    public FrmContentDataSumberPenghasilan(HttpServletRequest request, ContentDataSumberPenghasilan entContentDataSumberPenghasilan) {
        super(new FrmContentDataSumberPenghasilan(entContentDataSumberPenghasilan), request);
        this.entContentDataSumberPenghasilan = entContentDataSumberPenghasilan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_SUMBER_PENGHASILAN;
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

    public ContentDataSumberPenghasilan getEntityObject() {
        return entContentDataSumberPenghasilan;
    }

    public void requestEntityObject(ContentDataSumberPenghasilan entContentDataSumberPenghasilan) {
        try {
            this.requestParam();
//            entContentDataSumberPenghasilan.setSumberPenghasilanOid(getLong(FRM_FIELD_SUMBER_PENGHASILAN_OID));
            entContentDataSumberPenghasilan.setSumberPenghasilan(getString(FRM_FIELD_SUMBER_PENGHASILAN));
            entContentDataSumberPenghasilan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataSumberPenghasilan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
