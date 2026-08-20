/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataLembagaPemeringkat;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataLembagaPemeringkat extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat;
    public static final String FRM_NAME_CONTENT_DATA_LEMBAGA_PEMERINGKAT = "FRM_NAME_CONTENT_DATA_LEMBAGA_PEMERINGKAT";
    public static final int FRM_FIELD_LEMBAGA_PEMERINGKAT_OID = 0;
    public static final int FRM_FIELD_NAMA_LEMBAGA_PEMERINGKAT = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_LEMBAGA_PEMERINGKAT_OID",
        "FRM_FIELD_NAMA_LEMBAGA_PEMERINGKAT",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataLembagaPemeringkat() {
    }

    public FrmContentDataLembagaPemeringkat(ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) {
        this.entContentDataLembagaPemeringkat = entContentDataLembagaPemeringkat;
    }

    public FrmContentDataLembagaPemeringkat(HttpServletRequest request, ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) {
        super(new FrmContentDataLembagaPemeringkat(entContentDataLembagaPemeringkat), request);
        this.entContentDataLembagaPemeringkat = entContentDataLembagaPemeringkat;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_LEMBAGA_PEMERINGKAT;
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

    public ContentDataLembagaPemeringkat getEntityObject() {
        return entContentDataLembagaPemeringkat;
    }

    public void requestEntityObject(ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) {
        try {
            this.requestParam();
//            entContentDataLembagaPemeringkat.setLembagaPemeringkatOid(getLong(FRM_FIELD_LEMBAGA_PEMERINGKAT_OID));
            entContentDataLembagaPemeringkat.setNamaLembagaPemeringkat(getString(FRM_FIELD_NAMA_LEMBAGA_PEMERINGKAT));
            entContentDataLembagaPemeringkat.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataLembagaPemeringkat.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
