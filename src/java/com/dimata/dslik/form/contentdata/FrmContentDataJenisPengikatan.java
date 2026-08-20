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

public class FrmContentDataJenisPengikatan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisPengikatan entContentDataJenisPengikatan;
    public static final String FRM_NAME_CONTENT_DATA_JENIS_PENGIKATAN = "FRM_NAME_CONTENT_DATA_JENIS_PENGIKATAN";
    public static final int FRM_FIELD_JENIS_PENGIKATAN_OID = 0;
    public static final int FRM_FIELD_NAMA_JENIS_PENGIKATAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_PENGIKATAN_OID",
        "FRM_FIELD_NAMA_JENIS_PENGIKATAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisPengikatan() {
    }

    public FrmContentDataJenisPengikatan(ContentDataJenisPengikatan entContentDataJenisPengikatan) {
        this.entContentDataJenisPengikatan = entContentDataJenisPengikatan;
    }

    public FrmContentDataJenisPengikatan(HttpServletRequest request, ContentDataJenisPengikatan entContentDataJenisPengikatan) {
        super(new FrmContentDataJenisPengikatan(entContentDataJenisPengikatan), request);
        this.entContentDataJenisPengikatan = entContentDataJenisPengikatan;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_JENIS_PENGIKATAN;
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

    public ContentDataJenisPengikatan getEntityObject() {
        return entContentDataJenisPengikatan;
    }

    public void requestEntityObject(ContentDataJenisPengikatan entContentDataJenisPengikatan) {
        try {
            this.requestParam();
            //entContentDataJenisPengikatan.setJenisPengikatanOid(getLong(FRM_FIELD_JENIS_PENGIKATAN_OID));
            entContentDataJenisPengikatan.setNamaJenisPengikatan(getString(FRM_FIELD_NAMA_JENIS_PENGIKATAN));
            entContentDataJenisPengikatan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisPengikatan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
