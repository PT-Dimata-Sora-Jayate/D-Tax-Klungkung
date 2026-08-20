/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

/**
 *
 * @author dimata005
 */
import com.dimata.dslik.entity.contentdata.ContentDataKecamatan;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmContentDataKecamatan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKecamatan entContentDataKecamatan;
    public static final String FRM_NAME_CONTENTDATAKECAMATAN = "FRM_NAME_CONTENTDATAKECAMATAN";
    public static final int FRM_FIELD_KECAMATANID = 0;
    public static final int FRM_FIELD_KODEKABUPATENKOTA = 1;
    public static final int FRM_FIELD_NAMAKECAMATAN = 2;
    public static final int FRM_FIELD_KODECOREBANKING = 3;
    public static final int FRM_FIELD_KODEOJK = 4;

    public static String[] fieldNames = {
        "FRM_FIELD_KECAMATANID",
        "FRM_FIELD_KODEKABUPATENKOTA",
        "FRM_FIELD_NAMAKECAMATAN",
        "FRM_FIELD_KODECOREBANKING",
        "FRM_FIELD_KODEOJK"
    };

    public static int[] fieldTypes = {
        TYPE_BLOB,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKecamatan() {
    }

    public FrmContentDataKecamatan(ContentDataKecamatan entContentDataKecamatan) {
        this.entContentDataKecamatan = entContentDataKecamatan;
    }

    public FrmContentDataKecamatan(HttpServletRequest request, ContentDataKecamatan entContentDataKecamatan) {
        super(new FrmContentDataKecamatan(entContentDataKecamatan), request);
        this.entContentDataKecamatan = entContentDataKecamatan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENTDATAKECAMATAN;
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

    public ContentDataKecamatan getEntityObject() {
        return entContentDataKecamatan;
    }

    public void requestEntityObject(ContentDataKecamatan entContentDataKecamatan) {
        try {
            this.requestParam();
            entContentDataKecamatan.setKodeKabupatenKota(getString(FRM_FIELD_KODEKABUPATENKOTA));
            entContentDataKecamatan.setNamaKecamatan(getString(FRM_FIELD_NAMAKECAMATAN));
            entContentDataKecamatan.setKodeCoreBanking(getString(FRM_FIELD_KODECOREBANKING));
            entContentDataKecamatan.setKodeOjk(getString(FRM_FIELD_KODEOJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
