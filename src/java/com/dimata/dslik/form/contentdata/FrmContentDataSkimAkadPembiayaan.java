/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSkimAkadPembiayaan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataSkimAkadPembiayaan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan;
    public static final String FRM_NAME_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN = "FRM_NAME_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN";
    public static final int FRM_FIELD_AKAD_PEMBIAYAAN_OID = 0;
    public static final int FRM_FIELD_NAMA_AKAD_PEMBIAYAAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_AKAD_PEMBIAYAAN_OID",
        "FRM_FIELD_NAMA_AKAD_PEMBIAYAAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataSkimAkadPembiayaan() {
    }

    public FrmContentDataSkimAkadPembiayaan(ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) {
        this.entContentDataSkimAkadPembiayaan = entContentDataSkimAkadPembiayaan;
    }

    public FrmContentDataSkimAkadPembiayaan(HttpServletRequest request, ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) {
        super(new FrmContentDataSkimAkadPembiayaan(entContentDataSkimAkadPembiayaan), request);
        this.entContentDataSkimAkadPembiayaan = entContentDataSkimAkadPembiayaan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN;
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

    public ContentDataSkimAkadPembiayaan getEntityObject() {
        return entContentDataSkimAkadPembiayaan;
    }

    public void requestEntityObject(ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) {
        try {
            this.requestParam();
//            entContentDataSkimAkadPembiayaan.setAkadPembiayaanOid(getLong(FRM_FIELD_AKAD_PEMBIAYAAN_OID));
            entContentDataSkimAkadPembiayaan.setNamaAkadPembiayaan(getString(FRM_FIELD_NAMA_AKAD_PEMBIAYAAN));
            entContentDataSkimAkadPembiayaan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataSkimAkadPembiayaan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
