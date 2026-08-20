/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataKodeNegaraDomisili;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataKodeNegaraDomisili extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili;
    public static final String FRM_NAME_CONTENT_DATA_KODE_NEGARA_DOMISILI = "FRM_NAME_CONTENT_DATA_KODE_NEGARA_DOMISILI";
    public static final int FRM_FIELD_NEGARA_DOMISILI_OID = 0;
    public static final int FRM_FIELD_NAMA_NEGARA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_NEGARA_DOMISILI_OID",
        "FRM_FIELD_NAMA_NEGARA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKodeNegaraDomisili() {
    }

    public FrmContentDataKodeNegaraDomisili(ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) {
        this.entContentDataKodeNegaraDomisili = entContentDataKodeNegaraDomisili;
    }

    public FrmContentDataKodeNegaraDomisili(HttpServletRequest request, ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) {
        super(new FrmContentDataKodeNegaraDomisili(entContentDataKodeNegaraDomisili), request);
        this.entContentDataKodeNegaraDomisili = entContentDataKodeNegaraDomisili;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_KODE_NEGARA_DOMISILI;
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

    public ContentDataKodeNegaraDomisili getEntityObject() {
        return entContentDataKodeNegaraDomisili;
    }

    public void requestEntityObject(ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili) {
        try {
            this.requestParam();
//            entContentDataKodeNegaraDomisili.setNegaraDomisiliOid(getLong(FRM_FIELD_NEGARA_DOMISILI_OID));
            entContentDataKodeNegaraDomisili.setNamaNegara(getString(FRM_FIELD_NAMA_NEGARA));
            entContentDataKodeNegaraDomisili.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKodeNegaraDomisili.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
