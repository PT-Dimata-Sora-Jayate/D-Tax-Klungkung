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

public class FrmContentDataHubDgnPelapor extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataHubDgnPelapor entContentDataHubDgnPelapor;
    public static final String FRM_CONTENT_DATA_HUB_DGN_PELAPOR = "FRM_CONTENT_DATA_HUB_DGN_PELAPOR";
    public static final int FRM_FIELD_HUBUNGAN_DGN_PELAPOR_OID = 0;
    public static final int FRM_FIELD_HUBUNGAN_DGN_PELAPOR = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_HUBUNGAN_DGN_PELAPOR_OID",
        "FRM_FIELD_HUBUNGAN_DGN_PELAPOR",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataHubDgnPelapor() {
    }

    public FrmContentDataHubDgnPelapor(ContentDataHubDgnPelapor entContentDataHubDgnPelapor) {
        this.entContentDataHubDgnPelapor = entContentDataHubDgnPelapor;
    }

    public FrmContentDataHubDgnPelapor(HttpServletRequest request, ContentDataHubDgnPelapor entContentDataHubDgnPelapor) {
        super(new FrmContentDataHubDgnPelapor(entContentDataHubDgnPelapor), request);
        this.entContentDataHubDgnPelapor = entContentDataHubDgnPelapor;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_HUB_DGN_PELAPOR;
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

    public ContentDataHubDgnPelapor getEntityObject() {
        return entContentDataHubDgnPelapor;
    }

    public void requestEntityObject(ContentDataHubDgnPelapor entContentDataHubDgnPelapor) {
        try {
            this.requestParam();
            //entContentDataHubDgnPelapor.setHubunganDgnPelaporOid(getLong(FRM_FIELD_HUBUNGAN_DGN_PELAPOR_OID));
            entContentDataHubDgnPelapor.setHubunganDgnPelapor(getString(FRM_FIELD_HUBUNGAN_DGN_PELAPOR));
            entContentDataHubDgnPelapor.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataHubDgnPelapor.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
