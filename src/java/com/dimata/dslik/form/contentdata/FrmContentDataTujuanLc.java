/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataTujuanLc;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataTujuanLc extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataTujuanLc entContentDataTujuanLc;
    public static final String FRM_NAME_CONTENT_DATA_TUJUAN_LC = "FRM_NAME_CONTENT_DATA_TUJUAN_LC";
    public static final int FRM_FIELD_TUJUAN_LC_OID = 0;
    public static final int FRM_FIELD_NAMA_TUJUAN_LC = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_TUJUAN_LC_OID",
        "FRM_FIELD_NAMA_TUJUAN_LC",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataTujuanLc() {
    }

    public FrmContentDataTujuanLc(ContentDataTujuanLc entContentDataTujuanLc) {
        this.entContentDataTujuanLc = entContentDataTujuanLc;
    }

    public FrmContentDataTujuanLc(HttpServletRequest request, ContentDataTujuanLc entContentDataTujuanLc) {
        super(new FrmContentDataTujuanLc(entContentDataTujuanLc), request);
        this.entContentDataTujuanLc = entContentDataTujuanLc;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_TUJUAN_LC;
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

    public ContentDataTujuanLc getEntityObject() {
        return entContentDataTujuanLc;
    }

    public void requestEntityObject(ContentDataTujuanLc entContentDataTujuanLc) {
        try {
            this.requestParam();
//    entContentDataTujuanLc.setTujuanLcOid(getLong(FRM_FIELD_TUJUAN_LC_OID));
            entContentDataTujuanLc.setNamaTujuanLc(getString(FRM_FIELD_NAMA_TUJUAN_LC));
            entContentDataTujuanLc.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataTujuanLc.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
