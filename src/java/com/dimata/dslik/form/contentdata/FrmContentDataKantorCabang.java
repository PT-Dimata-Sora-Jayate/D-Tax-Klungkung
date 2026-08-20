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

public class FrmContentDataKantorCabang extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKantorCabang entContentDataKantorCabang;
    public static final String FRM_CONTENT_DATA_KANTOR_CABANG = "FRM_CONTENT_DATA_KANTOR_CABANG";
    public static final int FRM_FIELD_KANTOR_CABANG_OID = 0;
    public static final int FRM_FIELD_NAMA_KANTOR_CABANG = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_KANTOR_CABANG_OID",
        "FRM_FIELD_NAMA_KANTOR_CABANG",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKantorCabang() {
    }

    public FrmContentDataKantorCabang(ContentDataKantorCabang entContentDataKantorCabang) {
        this.entContentDataKantorCabang = entContentDataKantorCabang;
    }

    public FrmContentDataKantorCabang(HttpServletRequest request, ContentDataKantorCabang entContentDataKantorCabang) {
        super(new FrmContentDataKantorCabang(entContentDataKantorCabang), request);
        this.entContentDataKantorCabang = entContentDataKantorCabang;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_KANTOR_CABANG;
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

    public ContentDataKantorCabang getEntityObject() {
        return entContentDataKantorCabang;
    }

    public void requestEntityObject(ContentDataKantorCabang entContentDataKantorCabang) {
        try {
            this.requestParam();
            //entContentDataKantorCabang.setKantorCabangOid(getLong(FRM_FIELD_KANTOR_CABANG_OID));
            entContentDataKantorCabang.setNamaKantorCabang(getString(FRM_FIELD_NAMA_KANTOR_CABANG));
            entContentDataKantorCabang.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKantorCabang.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
