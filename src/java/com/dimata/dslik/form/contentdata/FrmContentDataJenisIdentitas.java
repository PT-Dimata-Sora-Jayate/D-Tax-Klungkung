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

public class FrmContentDataJenisIdentitas extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisIdentitas entContentDataJenisIdentitas;
    public static final String FRM_CONTENT_DATA_JENIS_IDENTITAS = "FRM_CONTENT_DATA_JENIS_IDENTITAS";
    public static final int FRM_FIELD_JENIS_IDENTITAS_OID = 0;
    public static final int FRM_FIELD_NAMA_IDENTITAS = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_IDENTITAS_OID",
        "FRM_FIELD_NAMA_IDENTITAS",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisIdentitas() {
    }

    public FrmContentDataJenisIdentitas(ContentDataJenisIdentitas entContentDataJenisIdentitas) {
        this.entContentDataJenisIdentitas = entContentDataJenisIdentitas;
    }

    public FrmContentDataJenisIdentitas(HttpServletRequest request, ContentDataJenisIdentitas entContentDataJenisIdentitas) {
        super(new FrmContentDataJenisIdentitas(entContentDataJenisIdentitas), request);
        this.entContentDataJenisIdentitas = entContentDataJenisIdentitas;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_IDENTITAS;
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

    public ContentDataJenisIdentitas getEntityObject() {
        return entContentDataJenisIdentitas;
    }

    public void requestEntityObject(ContentDataJenisIdentitas entContentDataJenisIdentitas) {
        try {
            this.requestParam();
            //entContentDataJenisIdentitas.setJenisIdentitasOid(getLong(FRM_FIELD_JENIS_IDENTITAS_OID));
            entContentDataJenisIdentitas.setNamaIdentitas(getString(FRM_FIELD_NAMA_IDENTITAS));
            entContentDataJenisIdentitas.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisIdentitas.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
