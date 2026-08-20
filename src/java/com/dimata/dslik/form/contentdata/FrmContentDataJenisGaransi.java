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

public class FrmContentDataJenisGaransi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisGaransi entContentDataJenisGaransi;
    public static final String FRM_CONTENT_DATA_JENIS_GARANSI = "FRM_CONTENT_DATA_JENIS_GARANSI";
    public static final int FRM_FIELD_JENIS_GARANSI_OID = 0;
    public static final int FRM_FIELD_JENIS_GARANSI = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_GARANSI_OID",
        "FRM_FIELD_JENIS_GARANSI",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisGaransi() {
    }

    public FrmContentDataJenisGaransi(ContentDataJenisGaransi entContentDataJenisGaransi) {
        this.entContentDataJenisGaransi = entContentDataJenisGaransi;
    }

    public FrmContentDataJenisGaransi(HttpServletRequest request, ContentDataJenisGaransi entContentDataJenisGaransi) {
        super(new FrmContentDataJenisGaransi(entContentDataJenisGaransi), request);
        this.entContentDataJenisGaransi = entContentDataJenisGaransi;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_GARANSI;
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

    public ContentDataJenisGaransi getEntityObject() {
        return entContentDataJenisGaransi;
    }

    public void requestEntityObject(ContentDataJenisGaransi entContentDataJenisGaransi) {
        try {
            this.requestParam();
            //entContentDataJenisGaransi.setJenisGaransiOid(getLong(FRM_FIELD_JENIS_GARANSI_OID));
            entContentDataJenisGaransi.setJenisGaransi(getString(FRM_FIELD_JENIS_GARANSI));
            entContentDataJenisGaransi.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisGaransi.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
