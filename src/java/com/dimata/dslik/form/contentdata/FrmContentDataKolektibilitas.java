/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataKolektibilitas;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataKolektibilitas extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKolektibilitas entContentDataKolektibilitas;
    public static final String FRM_NAME_CONTENT_DATA_KOLEKTIBILITAS = "FRM_NAME_CONTENT_DATA_KOLEKTIBILITAS";
    public static final int FRM_FIELD_KOLEKTIBILITAS_OID = 0;
    public static final int FRM_FIELD_NAMA_KOLEKTIBILITAS = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_KOLEKTIBILITAS_OID",
        "FRM_FIELD_NAMA_KOLEKTIBILITAS",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKolektibilitas() {
    }

    public FrmContentDataKolektibilitas(ContentDataKolektibilitas entContentDataKolektibilitas) {
        this.entContentDataKolektibilitas = entContentDataKolektibilitas;
    }

    public FrmContentDataKolektibilitas(HttpServletRequest request, ContentDataKolektibilitas entContentDataKolektibilitas) {
        super(new FrmContentDataKolektibilitas(entContentDataKolektibilitas), request);
        this.entContentDataKolektibilitas = entContentDataKolektibilitas;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_KOLEKTIBILITAS;
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

    public ContentDataKolektibilitas getEntityObject() {
        return entContentDataKolektibilitas;
    }

    public void requestEntityObject(ContentDataKolektibilitas entContentDataKolektibilitas) {
        try {
            this.requestParam();
//            entContentDataKolektibilitas.setKolektibilitasOid(getLong(FRM_FIELD_KOLEKTIBILITAS_OID));
            entContentDataKolektibilitas.setNamaKolektibilitas(getString(FRM_FIELD_NAMA_KOLEKTIBILITAS));
            entContentDataKolektibilitas.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKolektibilitas.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
