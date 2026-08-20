/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

/**
 *
 * @author m20n9
 * *
 */
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmContentDataCaraRestrukturisasi extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi;
    public static final String FRM_CONTENT_CARA_RESTRUKTURISASI = "FRM_CONTENT_CARA_RESTRUKTURISASI";
    public static final int FRM_FIELD_RESTRUKTURISASI_OID = 0;
    public static final int FRM_FIELD_CARA_RESTRUKTURISASI = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_RESTRUKTURISASI_OID",
        "FRM_FIELD_CARA_RESTRUKTURISASI",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataCaraRestrukturisasi() {
    }

    public FrmContentDataCaraRestrukturisasi(ContentDataCaraRestrukturisasi entContentCaraRestrukturisasi) {
        this.entContentDataCaraRestrukturisasi = entContentCaraRestrukturisasi;
    }

    public FrmContentDataCaraRestrukturisasi(HttpServletRequest request, ContentDataCaraRestrukturisasi entContentCaraRestrukturisasi) {
        super(new FrmContentDataCaraRestrukturisasi(entContentCaraRestrukturisasi), request);
        this.entContentDataCaraRestrukturisasi = entContentCaraRestrukturisasi;
    }

    public String getFormName() {
        return FRM_CONTENT_CARA_RESTRUKTURISASI;
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

    public ContentDataCaraRestrukturisasi getEntityObject() {
        return entContentDataCaraRestrukturisasi;
    }

    public void requestEntityObject(ContentDataCaraRestrukturisasi entContentCaraRestrukturisasi) {
        try {
            this.requestParam();
            //entContentCaraRestrukturisasi.setRestrukturisasiOid(getLong(FRM_FIELD_RESTRUKTURISASI_OID));
            entContentCaraRestrukturisasi.setCaraRestrukturisasi(getString(FRM_FIELD_CARA_RESTRUKTURISASI));
            entContentCaraRestrukturisasi.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentCaraRestrukturisasi.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
