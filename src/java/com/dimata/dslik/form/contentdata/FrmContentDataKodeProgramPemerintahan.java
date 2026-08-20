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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dimata.dslik.entity.contentdata.ContentDataKodeProgramPemerintah;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dewa
 */
public class FrmContentDataKodeProgramPemerintahan extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKodeProgramPemerintah entContentDataKodeProgramPemerintah;
    public static final String FRM_NAME_CONTENT_DATA_KODE_PROGRAM_PEMERINTAHAN = "FRM_NAME_CONTENT_DATA_KODE_PROGRAM_PEMERINTAHAN";
    public static final int FRM_FIELD_KREDIT_PROGRAM_PEMERINTAH_OID = 0;
    public static final int FRM_FIELD_NAMA_PROGRAM_PEMERINTAG = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "FRM_FIELD_KREDIT_PROGRAM_PEMERINTAH_OID",
        "FRM_FIELD_NAMA_PROGRAM_PEMERINTAG",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKodeProgramPemerintahan() {
    }

    public FrmContentDataKodeProgramPemerintahan(ContentDataKodeProgramPemerintah entContentDataKodeProgramPemerintah) {
        this.entContentDataKodeProgramPemerintah = entContentDataKodeProgramPemerintah;
    }

    public FrmContentDataKodeProgramPemerintahan(HttpServletRequest request, ContentDataKodeProgramPemerintah entContentDataKodeProgramPemerintah) {
        super(new FrmContentDataKodeProgramPemerintahan(entContentDataKodeProgramPemerintah), request);
        this.entContentDataKodeProgramPemerintah = entContentDataKodeProgramPemerintah;
    }

    public String getFormName() {
        return FRM_NAME_CONTENT_DATA_KODE_PROGRAM_PEMERINTAHAN;
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

    public ContentDataKodeProgramPemerintah getEntityObject() {
        return entContentDataKodeProgramPemerintah;
    }

    public void requestEntityObject(ContentDataKodeProgramPemerintah entContentDataKodeProgramPemerintah) {
        try {
            this.requestParam();
//          entContentDataKodePekerjaan.setPekerjaanOid(getLong(FRM_FIELD_PEKERJAAN_OID));
            entContentDataKodeProgramPemerintah.setNamaProgramPemerintah(getString(FRM_FIELD_NAMA_PROGRAM_PEMERINTAG));
            entContentDataKodeProgramPemerintah.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKodeProgramPemerintah.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
