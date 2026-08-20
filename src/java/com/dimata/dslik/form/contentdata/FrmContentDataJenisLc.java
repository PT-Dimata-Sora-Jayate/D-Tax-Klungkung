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

public class FrmContentDataJenisLc extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisLc entContentDataJenisLc;
    public static final String FRM_CONTENT_DATA_JENIS_LC = "FRM_CONTENT_DATA_JENIS_LC";
    public static final int FRM_FIELD_JENIS_LC_OID = 0;
    public static final int FRM_FIELD_NAMA_LC = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_LC_OID",
        "FRM_FIELD_NAMA_LC",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisLc() {
    }

    public FrmContentDataJenisLc(ContentDataJenisLc entContentDataJenisLc) {
        this.entContentDataJenisLc = entContentDataJenisLc;
    }

    public FrmContentDataJenisLc(HttpServletRequest request, ContentDataJenisLc entContentDataJenisLc) {
        super(new FrmContentDataJenisLc(entContentDataJenisLc), request);
        this.entContentDataJenisLc = entContentDataJenisLc;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_LC;
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

    public ContentDataJenisLc getEntityObject() {
        return entContentDataJenisLc;
    }

    public void requestEntityObject(ContentDataJenisLc entContentDataJenisLc) {
        try {
            this.requestParam();
            //entContentDataJenisLc.setJenisLcOid(getLong(FRM_FIELD_JENIS_LC_OID));
            entContentDataJenisLc.setNamaLc(getString(FRM_FIELD_NAMA_LC));
            entContentDataJenisLc.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisLc.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
