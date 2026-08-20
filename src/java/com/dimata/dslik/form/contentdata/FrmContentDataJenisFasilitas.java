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

public class FrmContentDataJenisFasilitas extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisFasilitas entContentDataJenisFasilitas;
    public static final String FRM_CONTENT_DATA_JENIS_FASILITAS = "FRM_CONTENT_DATA_JENIS_FASILITAS";
    public static final int FRM_FIELD_JENIS_FASILITAS_OID = 0;
    public static final int FRM_FIELD_JENIS_FASILITAS = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_FASILITAS_OID",
        "FRM_FIELD_JENIS_FASILITAS",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisFasilitas() {
    }

    public FrmContentDataJenisFasilitas(ContentDataJenisFasilitas entContentDataJenisFasilitas) {
        this.entContentDataJenisFasilitas = entContentDataJenisFasilitas;
    }

    public FrmContentDataJenisFasilitas(HttpServletRequest request, ContentDataJenisFasilitas entContentDataJenisFasilitas) {
        super(new FrmContentDataJenisFasilitas(entContentDataJenisFasilitas), request);
        this.entContentDataJenisFasilitas = entContentDataJenisFasilitas;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_FASILITAS;
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

    public ContentDataJenisFasilitas getEntityObject() {
        return entContentDataJenisFasilitas;
    }

    public void requestEntityObject(ContentDataJenisFasilitas entContentDataJenisFasilitas) {
        try {
            this.requestParam();
            //entContentDataJenisFasilitas.setJenisFasilitasOid(getLong(FRM_FIELD_JENIS_FASILITAS_OID));
            entContentDataJenisFasilitas.setJenisFasilitas(getString(FRM_FIELD_JENIS_FASILITAS));
            entContentDataJenisFasilitas.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisFasilitas.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
