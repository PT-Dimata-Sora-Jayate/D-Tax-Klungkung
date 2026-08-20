/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.masterdata;

/**
 *
 * @author dimata005
 */
import com.dimata.dslik.entity.masterdata.MasterCifHapusBuku;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

public class FrmMasterCifHapusBuku extends FRMHandler implements I_FRMInterface, I_FRMType {

    private MasterCifHapusBuku entMasterCifHapusBuku;
    public static final String FRM_NAME_MASTERCIFHAPUSBUKU = "FRM_NAME_MASTERCIFHAPUSBUKU";
    public static final int FRM_FIELD_MASTERCIHAPUSBUKU = 0;
    public static final int FRM_FIELD_CIFHAPUSBUKU = 1;

    public static String[] fieldNames = {
        "FRM_FIELD_MASTERCIHAPUSBUKU",
        "FRM_FIELD_CIFHAPUSBUKU"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING
    };

    public FrmMasterCifHapusBuku() {
    }

    public FrmMasterCifHapusBuku(MasterCifHapusBuku entMasterCifHapusBuku) {
        this.entMasterCifHapusBuku = entMasterCifHapusBuku;
    }

    public FrmMasterCifHapusBuku(HttpServletRequest request, MasterCifHapusBuku entMasterCifHapusBuku) {
        super(new FrmMasterCifHapusBuku(entMasterCifHapusBuku), request);
        this.entMasterCifHapusBuku = entMasterCifHapusBuku;
    }

    public String getFormName() {
        return FRM_NAME_MASTERCIFHAPUSBUKU;
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

    public MasterCifHapusBuku getEntityObject() {
        return entMasterCifHapusBuku;
    }

    public void requestEntityObject(MasterCifHapusBuku entMasterCifHapusBuku) {
        try {
            this.requestParam();
            entMasterCifHapusBuku.setCifHapusBuku(getString(FRM_FIELD_CIFHAPUSBUKU));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
