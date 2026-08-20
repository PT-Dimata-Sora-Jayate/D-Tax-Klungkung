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

public class FrmContentDataJenisPemeringkat extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisPemeringkat entContentDataJenisPemeringkat;
    public static final String FRM_CONTENT_DATA_JENIS_PEMERINGKAT = "FRM_CONTENT_DATA_JENIS_PEMERINGKAT";
    public static final int FRM_FIELD_JENIS_PEMERINGKAT_OID = 0;
    public static final int FRM_FIELD_NAMA_JENIS_PEMERINGKAT = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_PEMERINGKAT_OID",
        "FRM_FIELD_NAMA_JENIS_PEMERINGKAT",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisPemeringkat() {
    }

    public FrmContentDataJenisPemeringkat(ContentDataJenisPemeringkat entContentDataJenisPemeringkat) {
        this.entContentDataJenisPemeringkat = entContentDataJenisPemeringkat;
    }

    public FrmContentDataJenisPemeringkat(HttpServletRequest request, ContentDataJenisPemeringkat entContentDataJenisPemeringkat) {
        super(new FrmContentDataJenisPemeringkat(entContentDataJenisPemeringkat), request);
        this.entContentDataJenisPemeringkat = entContentDataJenisPemeringkat;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_PEMERINGKAT;
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

    public ContentDataJenisPemeringkat getEntityObject() {
        return entContentDataJenisPemeringkat;
    }

    public void requestEntityObject(ContentDataJenisPemeringkat entContentDataJenisPemeringkat) {
        try {
            this.requestParam();
            //entContentDataJenisPemeringkat.setJenisPemeringkatOid(getLong(FRM_FIELD_JENIS_PEMERINGKAT_OID));
            entContentDataJenisPemeringkat.setNamaJenisPemeringkat(getString(FRM_FIELD_NAMA_JENIS_PEMERINGKAT));
            entContentDataJenisPemeringkat.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisPemeringkat.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
