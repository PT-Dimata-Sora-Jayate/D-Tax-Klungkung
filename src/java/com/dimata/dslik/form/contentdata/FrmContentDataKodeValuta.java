/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataKodeValuta;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataKodeValuta extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKodeValuta entContentDataKodeValuta;
    public static final String FRM_NAME_CONTENT_DATA_KODE_VALUTA = "FRM_NAME_CONTENT_DATA_KODE_VALUTA";
    public static final int FRM_FIELD_VALUTA_OID = 0;
    public static final int FRM_FIELD_NAMA_VALUTA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_VALUTA_OID",
        "FRM_FIELD_NAMA_VALUTA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKodeValuta() {
    }

    public FrmContentDataKodeValuta(ContentDataKodeValuta entContentDataKodeValuta) {
        this.entContentDataKodeValuta = entContentDataKodeValuta;
    }

    public FrmContentDataKodeValuta(HttpServletRequest request, ContentDataKodeValuta entContentDataKodeValuta) {
        super(new FrmContentDataKodeValuta(entContentDataKodeValuta), request);
        this.entContentDataKodeValuta = entContentDataKodeValuta;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_KODE_VALUTA;
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

    public ContentDataKodeValuta getEntityObject() {
        return entContentDataKodeValuta;
    }

    public void requestEntityObject(ContentDataKodeValuta entContentDataKodeValuta) {
        try {
            this.requestParam();
//            entContentDataKodeValuta.setValutaOid(getLong(FRM_FIELD_VALUTA_OID));
            entContentDataKodeValuta.setNamaValuta(getString(FRM_FIELD_NAMA_VALUTA));
            entContentDataKodeValuta.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKodeValuta.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
