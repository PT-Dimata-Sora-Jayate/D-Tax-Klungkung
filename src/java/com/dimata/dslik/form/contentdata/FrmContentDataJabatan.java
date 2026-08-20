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

public class FrmContentDataJabatan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJabatan entContentDataJabatan;
    public static final String FRM_CONTENT_DATA_JABATAN = "FRM_CONTENT_DATA_JABATAN";
    public static final int FRM_FIELD_JABATAN_OID = 0;
    public static final int FRM_FIELD_NAMA_JABATAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JABATAN_OID",
        "FRM_FIELD_NAMA_JABATAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJabatan() {
    }

    public FrmContentDataJabatan(ContentDataJabatan entContentDataJabatan) {
        this.entContentDataJabatan = entContentDataJabatan;
    }

    public FrmContentDataJabatan(HttpServletRequest request, ContentDataJabatan entContentDataJabatan) {
        super(new FrmContentDataJabatan(entContentDataJabatan), request);
        this.entContentDataJabatan = entContentDataJabatan;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JABATAN;
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

    public ContentDataJabatan getEntityObject() {
        return entContentDataJabatan;
    }

    public void requestEntityObject(ContentDataJabatan entContentDataJabatan) {
        try {
            this.requestParam();
            //entContentDataJabatan.setJabatanOid(getLong(FRM_FIELD_JABATAN_OID));
            entContentDataJabatan.setNamaJabatan(getString(FRM_FIELD_NAMA_JABATAN));
            entContentDataJabatan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJabatan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
