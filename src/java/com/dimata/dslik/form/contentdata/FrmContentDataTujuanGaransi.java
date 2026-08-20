/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataTujuanGaransi;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataTujuanGaransi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataTujuanGaransi entContentDataTujuanGaransi;
    public static final String FRM_NAME_CONTENT_DATA_TUJUAN_GARANSI = "FRM_NAME_CONTENT_DATA_TUJUAN_GARANSI";
    public static final int FRM_FIELD_TUJUAN_GARANSI_OID = 0;
    public static final int FRM_FIELD_TUJUAN_GARANSI = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_TUJUAN_GARANSI_OID",
        "FRM_FIELD_TUJUAN_GARANSI",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataTujuanGaransi() {
    }

    public FrmContentDataTujuanGaransi(ContentDataTujuanGaransi entContentDataTujuanGaransi) {
        this.entContentDataTujuanGaransi = entContentDataTujuanGaransi;
    }

    public FrmContentDataTujuanGaransi(HttpServletRequest request, ContentDataTujuanGaransi entContentDataTujuanGaransi) {
        super(new FrmContentDataTujuanGaransi(entContentDataTujuanGaransi), request);
        this.entContentDataTujuanGaransi = entContentDataTujuanGaransi;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_TUJUAN_GARANSI;
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

    public ContentDataTujuanGaransi getEntityObject() {
        return entContentDataTujuanGaransi;
    }

    public void requestEntityObject(ContentDataTujuanGaransi entContentDataTujuanGaransi) {
        try {
            this.requestParam();
//            entContentDataTujuanGaransi.setTujuanGaransiOid(getLong(FRM_FIELD_TUJUAN_GARANSI_OID));
            entContentDataTujuanGaransi.setTujuanGaransi(getString(FRM_FIELD_TUJUAN_GARANSI));
            entContentDataTujuanGaransi.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataTujuanGaransi.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
