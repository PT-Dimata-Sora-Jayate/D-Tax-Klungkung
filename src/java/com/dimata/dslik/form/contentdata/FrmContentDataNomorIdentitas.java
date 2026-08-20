/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataNomorIdentitas;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataNomorIdentitas extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataNomorIdentitas entContentDataNomorIdentitas;
    public static final String FRM_NAME_CONTENT_DATA_NOMOR_IDENTITAS = "FRM_NAME_CONTENT_DATA_NOMOR_IDENTITAS";
    public static final int FRM_FIELD_NOMOR_IDENTITAS_OID = 0;
    public static final int FRM_FIELD_NOMOR_IDENTITAS = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_NOMOR_IDENTITAS_OID",
        "FRM_FIELD_NOMOR_IDENTITAS",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataNomorIdentitas() {
    }

    public FrmContentDataNomorIdentitas(ContentDataNomorIdentitas entContentDataNomorIdentitas) {
        this.entContentDataNomorIdentitas = entContentDataNomorIdentitas;
    }

    public FrmContentDataNomorIdentitas(HttpServletRequest request, ContentDataNomorIdentitas entContentDataNomorIdentitas) {
        super(new FrmContentDataNomorIdentitas(entContentDataNomorIdentitas), request);
        this.entContentDataNomorIdentitas = entContentDataNomorIdentitas;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_NOMOR_IDENTITAS;
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

    public ContentDataNomorIdentitas getEntityObject() {
        return entContentDataNomorIdentitas;
    }

    public void requestEntityObject(ContentDataNomorIdentitas entContentDataNomorIdentitas) {
        try {
            this.requestParam();
//            entContentDataNomorIdentitas.setNomorIdentitasOid(getLong(FRM_FIELD_NOMOR_IDENTITAS_OID));
            entContentDataNomorIdentitas.setNomorIdentitas(getString(FRM_FIELD_NOMOR_IDENTITAS));
            entContentDataNomorIdentitas.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataNomorIdentitas.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
