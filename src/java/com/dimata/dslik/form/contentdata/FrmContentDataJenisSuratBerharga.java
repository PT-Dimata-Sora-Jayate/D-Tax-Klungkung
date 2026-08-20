/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmContentDataJenisSuratBerharga extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga;
    public static final String FRM_CONTENT_DATA_JENIS_SURAT_BERHARGA = "FRM_CONTENT_DATA_JENIS_SURAT_BERHARGA";
    public static final int FRM_FIELD_JENIS_SURAT_BERHARGA_OID = 0;
    public static final int FRM_FIELD_JENIS_SURAT_BERHARGA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_SURAT_BERHARGA_OID",
        "FRM_FIELD_JENIS_SURAT_BERHARGA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisSuratBerharga() {
    }

    public FrmContentDataJenisSuratBerharga(ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) {
        this.entContentDataJenisSuratBerharga = entContentDataJenisSuratBerharga;
    }

    public FrmContentDataJenisSuratBerharga(HttpServletRequest request, ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) {
        super(new FrmContentDataJenisSuratBerharga(entContentDataJenisSuratBerharga), request);
        this.entContentDataJenisSuratBerharga = entContentDataJenisSuratBerharga;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_SURAT_BERHARGA;
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

    public ContentDataJenisSuratBerharga getEntityObject() {
        return entContentDataJenisSuratBerharga;
    }

    public void requestEntityObject(ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga) {
        try {
            this.requestParam();
            //entContentDataJenisSuratBerharga.setJenisSuratBerhargaOid(getLong(FRM_FIELD_JENIS_SURAT_BERHARGA_OID));
            entContentDataJenisSuratBerharga.setJenisSuratBerharga(getString(FRM_FIELD_JENIS_SURAT_BERHARGA));
            entContentDataJenisSuratBerharga.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisSuratBerharga.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
