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

public class FrmContentDataJenisPenggunaan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataJenisPenggunaan entContentDataJenisPenggunaan;
    public static final String FRM_CONTENT_DATA_JENIS_PENGGUNAAN = "FRM_CONTENT_DATA_JENIS_PENGGUNAAN";
    public static final int FRM_FIELD_JENIS_PENGGUNAAN_OID = 0;
    public static final int FRM_FIELD_JENIS_PENGGUNAAN = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_JENIS_PENGGUNAAN_OID",
        "FRM_FIELD_JENIS_PENGGUNAAN",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataJenisPenggunaan() {
    }

    public FrmContentDataJenisPenggunaan(ContentDataJenisPenggunaan entContentDataJenisPenggunaan) {
        this.entContentDataJenisPenggunaan = entContentDataJenisPenggunaan;
    }

    public FrmContentDataJenisPenggunaan(HttpServletRequest request, ContentDataJenisPenggunaan entContentDataJenisPenggunaan) {
        super(new FrmContentDataJenisPenggunaan(entContentDataJenisPenggunaan), request);
        this.entContentDataJenisPenggunaan = entContentDataJenisPenggunaan;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_JENIS_PENGGUNAAN;
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

    public ContentDataJenisPenggunaan getEntityObject() {
        return entContentDataJenisPenggunaan;
    }

    public void requestEntityObject(ContentDataJenisPenggunaan entContentDataJenisPenggunaan) {
        try {
            this.requestParam();
            //entContentDataJenisPenggunaan.setJenisPenggunaanOid(getLong(FRM_FIELD_JENIS_PENGGUNAAN_OID));
            entContentDataJenisPenggunaan.setJenisPenggunaan(getString(FRM_FIELD_JENIS_PENGGUNAAN));
            entContentDataJenisPenggunaan.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataJenisPenggunaan.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
