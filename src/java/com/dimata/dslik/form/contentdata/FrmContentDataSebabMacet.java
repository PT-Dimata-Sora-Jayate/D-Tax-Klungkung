/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSebabMacet;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataSebabMacet extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataSebabMacet entContentDataSebabMacet;
    public static final String FRM_NAME_CONTENT_DATA_SEBAB_MACET = "FRM_NAME_CONTENT_DATA_SEBAB_MACET";
    public static final int FRM_FIELD_SEBAB_MACET_OID = 0;
    public static final int FRM_FIELD_SEBAB_MACET = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_SEBAB_MACET_OID",
        "FRM_FIELD_SEBAB_MACET",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataSebabMacet() {
    }

    public FrmContentDataSebabMacet(ContentDataSebabMacet entContentDataSebabMacet) {
        this.entContentDataSebabMacet = entContentDataSebabMacet;
    }

    public FrmContentDataSebabMacet(HttpServletRequest request, ContentDataSebabMacet entContentDataSebabMacet) {
        super(new FrmContentDataSebabMacet(entContentDataSebabMacet), request);
        this.entContentDataSebabMacet = entContentDataSebabMacet;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_SEBAB_MACET;
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

    public ContentDataSebabMacet getEntityObject() {
        return entContentDataSebabMacet;
    }

    public void requestEntityObject(ContentDataSebabMacet entContentDataSebabMacet) {
        try {
            this.requestParam();
//    entContentDataSebabMacet.setSebabMacetOid(getLong(FRM_FIELD_SEBAB_MACET_OID));
            entContentDataSebabMacet.setSebabMacet(getString(FRM_FIELD_SEBAB_MACET));
            entContentDataSebabMacet.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataSebabMacet.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
