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

public class FrmContentDataBentukBadanUsaha extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataBentukBadanUsaha entContentDataBentukBadanUsaha;
    public static final String FRM_CONTENT_DATA_BENTUK_BADAN_USAHA = "FRM_CONTENT_DATA_BENTUK_BADAN_USAHA";
    public static final int FRM_FIELD_BENTUK_BADAN_USAHA_OID = 0;
    public static final int FRM_FIELD_BENTUK_BADAN_USAHA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_BENTUK_BADAN_USAHA_OID",
        "FRM_FIELD_BENTUK_BADAN_USAHA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataBentukBadanUsaha() {
    }

    public FrmContentDataBentukBadanUsaha(ContentDataBentukBadanUsaha entContentDataBentukBadanUsaha) {
        this.entContentDataBentukBadanUsaha = entContentDataBentukBadanUsaha;
    }

    public FrmContentDataBentukBadanUsaha(HttpServletRequest request, ContentDataBentukBadanUsaha entContentDataBentukBadanUsaha) {
        super(new FrmContentDataBentukBadanUsaha(entContentDataBentukBadanUsaha), request);
        this.entContentDataBentukBadanUsaha = entContentDataBentukBadanUsaha;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_BENTUK_BADAN_USAHA;
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

    public ContentDataBentukBadanUsaha getEntityObject() {
        return entContentDataBentukBadanUsaha;
    }

    public void requestEntityObject(ContentDataBentukBadanUsaha entContentDataBentukBadanUsaha) {
        try {
            this.requestParam();
            entContentDataBentukBadanUsaha.setBentukBadanUsaha(getString(FRM_FIELD_BENTUK_BADAN_USAHA));
            entContentDataBentukBadanUsaha.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataBentukBadanUsaha.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
