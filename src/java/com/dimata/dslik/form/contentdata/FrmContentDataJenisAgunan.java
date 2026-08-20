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

public class FrmContentDataJenisAgunan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisAgunan entContentDataJenisAgunan;
    public static final String FRM_CONTENT_DATA_JENIS_AGUNAN = "FRM_CONTENT_DATA_JENIS_AGUNAN";
    public static final int FRM_FIELD_JENIS_AGUNAN_OID = 0;
    public static final int FRM_FIELD_JENIS_AGUNAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_AGUNAN_OID",
        "FRM_FIELD_JENIS_AGUNAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisAgunan() {
    }

    public FrmContentDataJenisAgunan(ContentDataJenisAgunan entContentDataJenisAgunan) {
        this.entContentDataJenisAgunan = entContentDataJenisAgunan;
    }

    public FrmContentDataJenisAgunan(HttpServletRequest request, ContentDataJenisAgunan entContentDataJenisAgunan) {
        super(new FrmContentDataJenisAgunan(entContentDataJenisAgunan), request);
        this.entContentDataJenisAgunan = entContentDataJenisAgunan;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_AGUNAN;
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

    public ContentDataJenisAgunan getEntityObject() {
        return entContentDataJenisAgunan;
    }

    public void requestEntityObject(ContentDataJenisAgunan entContentDataJenisAgunan) {
        try {
            this.requestParam();
            //entContentDataJenisAgunan.setJenisAgunanOid(getLong(FRM_FIELD_JENIS_AGUNAN_OID));
            entContentDataJenisAgunan.setJenisAgunan(getString(FRM_FIELD_JENIS_AGUNAN));
            entContentDataJenisAgunan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisAgunan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
