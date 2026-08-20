/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataOrientasiPenggunaan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataOrientasiPenggunaan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan;
    public static final String FRM_NAME_CONTENT_DATA_ORIENTASI_PENGGUNAAN = "FRM_NAME_CONTENT_DATA_ORIENTASI_PENGGUNAAN";
    public static final int FRM_FIELD_ORIENTASI_PENGGUNAAN_OID = 0;
    public static final int FRM_FIELD_NAMA_ORIENTASI_PENGGUNAAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_ORIENTASI_PENGGUNAAN_OID",
        "FRM_FIELD_NAMA_ORIENTASI_PENGGUNAAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataOrientasiPenggunaan() {
    }

    public FrmContentDataOrientasiPenggunaan(ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) {
        this.entContentDataOrientasiPenggunaan = entContentDataOrientasiPenggunaan;
    }

    public FrmContentDataOrientasiPenggunaan(HttpServletRequest request, ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) {
        super(new FrmContentDataOrientasiPenggunaan(entContentDataOrientasiPenggunaan), request);
        this.entContentDataOrientasiPenggunaan = entContentDataOrientasiPenggunaan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_ORIENTASI_PENGGUNAAN;
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

    public ContentDataOrientasiPenggunaan getEntityObject() {
        return entContentDataOrientasiPenggunaan;
    }

    public void requestEntityObject(ContentDataOrientasiPenggunaan entContentDataOrientasiPenggunaan) {
        try {
            this.requestParam();
//            entContentDataOrientasiPenggunaan.setOrientasiPenggunaanOid(getLong(FRM_FIELD_ORIENTASI_PENGGUNAAN_OID));
            entContentDataOrientasiPenggunaan.setNamaOrientasiPenggunaan(getString(FRM_FIELD_NAMA_ORIENTASI_PENGGUNAAN));
            entContentDataOrientasiPenggunaan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataOrientasiPenggunaan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
