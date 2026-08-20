/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSektorEkonomi;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataSektorEkonomi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataSektorEkonomi entContentDataSektorEkonomi;
    public static final String FRM_NAME_CONTENT_DATA_SEKTOR_EKONOMI = "FRM_NAME_CONTENT_DATA_SEKTOR_EKONOMI";
    public static final int FRM_FIELD_SEKTOR_EKONOMI_OID = 0;
    public static final int FRM_FIELD_NAMA_SEKTOR_EKONOMI = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_SEKTOR_EKONOMI_OID",
        "FRM_FIELD_NAMA_SEKTOR_EKONOMI",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataSektorEkonomi() {
    }

    public FrmContentDataSektorEkonomi(ContentDataSektorEkonomi entContentDataSektorEkonomi) {
        this.entContentDataSektorEkonomi = entContentDataSektorEkonomi;
    }

    public FrmContentDataSektorEkonomi(HttpServletRequest request, ContentDataSektorEkonomi entContentDataSektorEkonomi) {
        super(new FrmContentDataSektorEkonomi(entContentDataSektorEkonomi), request);
        this.entContentDataSektorEkonomi = entContentDataSektorEkonomi;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_SEKTOR_EKONOMI;
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

    public ContentDataSektorEkonomi getEntityObject() {
        return entContentDataSektorEkonomi;
    }

    public void requestEntityObject(ContentDataSektorEkonomi entContentDataSektorEkonomi) {
        try {
            this.requestParam();
//    entContentDataSektorEkonomi.setSektorEkonomiOid(getLong(FRM_FIELD_SEKTOR_EKONOMI_OID));
            entContentDataSektorEkonomi.setNamaSektorEkonomi(getString(FRM_FIELD_NAMA_SEKTOR_EKONOMI));
            entContentDataSektorEkonomi.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataSektorEkonomi.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
