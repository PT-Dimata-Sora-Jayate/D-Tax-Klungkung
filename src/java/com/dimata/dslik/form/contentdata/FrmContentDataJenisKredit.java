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

public class FrmContentDataJenisKredit extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisKredit entContentDataJenisKredit;
    public static final String FRM_CONTENT_DATA_JENIS_KREDIT = "FRM_CONTENT_DATA_JENIS_KREDIT";
    public static final int FRM_FIELD_JENIS_KREDIT_OID = 0;
    public static final int FRM_FIELD_NAMA_JENIS_KREDIT = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_KREDIT_OID",
        "FRM_FIELD_NAMA_JENIS_KREDIT",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisKredit() {
    }

    public FrmContentDataJenisKredit(ContentDataJenisKredit entContentDataJenisKredit) {
        this.entContentDataJenisKredit = entContentDataJenisKredit;
    }

    public FrmContentDataJenisKredit(HttpServletRequest request, ContentDataJenisKredit entContentDataJenisKredit) {
        super(new FrmContentDataJenisKredit(entContentDataJenisKredit), request);
        this.entContentDataJenisKredit = entContentDataJenisKredit;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_KREDIT;
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

    public ContentDataJenisKredit getEntityObject() {
        return entContentDataJenisKredit;
    }

    public void requestEntityObject(ContentDataJenisKredit entContentDataJenisKredit) {
        try {
            this.requestParam();
            //entContentDataJenisKredit.setJenisKreditOid(getLong(FRM_FIELD_JENIS_KREDIT_OID));
            entContentDataJenisKredit.setNamaJenisKredit(getString(FRM_FIELD_NAMA_JENIS_KREDIT));
            entContentDataJenisKredit.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisKredit.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
