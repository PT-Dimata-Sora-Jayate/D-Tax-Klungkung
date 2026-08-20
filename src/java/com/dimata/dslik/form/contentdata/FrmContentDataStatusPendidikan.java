/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataStatusPendidikan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataStatusPendidikan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataStatusPendidikan entContentDataStatusPendidikan;
    public static final String FRM_NAME_CONTENT_DATA_STATUS_PENDIDIKAN = "FRM_NAME_CONTENT_DATA_STATUS_PENDIDIKAN";
    public static final int FRM_FIELD_STATUS_PENDIDIKAN_OID = 0;
    public static final int FRM_FIELD_STATUS_PENDIDIKAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_STATUS_PENDIDIKAN_OID",
        "FRM_FIELD_STATUS_PENDIDIKAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataStatusPendidikan() {
    }

    public FrmContentDataStatusPendidikan(ContentDataStatusPendidikan entContentDataStatusPendidikan) {
        this.entContentDataStatusPendidikan = entContentDataStatusPendidikan;
    }

    public FrmContentDataStatusPendidikan(HttpServletRequest request, ContentDataStatusPendidikan entContentDataStatusPendidikan) {
        super(new FrmContentDataStatusPendidikan(entContentDataStatusPendidikan), request);
        this.entContentDataStatusPendidikan = entContentDataStatusPendidikan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_STATUS_PENDIDIKAN;
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

    public ContentDataStatusPendidikan getEntityObject() {
        return entContentDataStatusPendidikan;
    }

    public void requestEntityObject(ContentDataStatusPendidikan entContentDataStatusPendidikan) {
        try {
            this.requestParam();
//            entContentDataStatusPendidikan.setStatusPendidikanOid(getLong(FRM_FIELD_STATUS_PENDIDIKAN_OID));
            entContentDataStatusPendidikan.setStatusPendidikan(getString(FRM_FIELD_STATUS_PENDIDIKAN));
            entContentDataStatusPendidikan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataStatusPendidikan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
