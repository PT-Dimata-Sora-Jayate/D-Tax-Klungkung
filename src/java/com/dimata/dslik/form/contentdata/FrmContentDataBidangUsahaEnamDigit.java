/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

/**
 *
 * @author dimata005
 */
/**
 *
 * @author m20n9
 */
import com.dimata.dslik.entity.contentdata.*;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmContentDataBidangUsahaEnamDigit extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataBidangUsahaEnamDigit entContentDataBidangUsaha;
    public static final String FRM_CONTENT_DATA_BIDANG_USAHA = "FRM_CONTENT_DATA_BIDANG_USAHA";
    public static final int FRM_FIELD_BIDANG_USAHA_OID = 0;
    public static final int FRM_FIELD_NAMA_BIDANG_USAHA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    
    public static String[] fieldNames = {
        "FRM_FIELD_BIDANG_USAHA_OID",
        "FRM_FIELD_NAMA_BIDANG_USAHA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataBidangUsahaEnamDigit() {
    }

    public FrmContentDataBidangUsahaEnamDigit(ContentDataBidangUsahaEnamDigit entContentDataBidangUsaha) {
        this.entContentDataBidangUsaha = entContentDataBidangUsaha;
    }

    public FrmContentDataBidangUsahaEnamDigit(HttpServletRequest request, ContentDataBidangUsahaEnamDigit entContentDataBidangUsaha) {
        super(new FrmContentDataBidangUsahaEnamDigit(entContentDataBidangUsaha), request);
        this.entContentDataBidangUsaha = entContentDataBidangUsaha;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_BIDANG_USAHA;
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

    public ContentDataBidangUsahaEnamDigit getEntityObject() {
        return entContentDataBidangUsaha;
    }

    public void requestEntityObject(ContentDataBidangUsahaEnamDigit entContentDataBidangUsaha) {
        try {
            this.requestParam();
            //entContentDataBidangUsaha.setBidangUsahaOid(getLong(FRM_FIELD_BIDANG_USAHA_OID));
            entContentDataBidangUsaha.setNamaBidangUsaha(getString(FRM_FIELD_NAMA_BIDANG_USAHA));
            entContentDataBidangUsaha.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataBidangUsaha.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}