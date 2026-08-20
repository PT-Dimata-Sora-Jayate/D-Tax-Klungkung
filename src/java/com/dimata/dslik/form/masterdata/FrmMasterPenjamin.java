/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.masterdata;

import com.dimata.dslik.entity.masterdata.MasterPenjamin;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dimata 007
 */
public class FrmMasterPenjamin extends FRMHandler implements I_FRMInterface, I_FRMType {

    private MasterPenjamin entMasterPenjamin;
    public static final String FRM_NAME_MASTER_PENJAMIN = "FRM_NAME_MASTER_PENJAMIN";
    public static final int FRM_FIELD_MASTER_PENJAMIN_OID = 0;
    public static final int FRM_FIELD_NO_ID_PENJAMIN = 1;
    public static final int FRM_FIELD_JENIS_IDENTITAS = 2;
    public static final int FRM_FIELD_NAMA_IDENTITAS = 3;
    public static final int FRM_FIELD_NAMA_LENGKAP = 4;
    public static final int FRM_FIELD_KODE_GOL_PENJAMIN = 5;
    public static final int FRM_FIELD_ALAMAT_PENJAMIN = 6;
    public static final int FRM_FIELD_KETERANGAN = 7;

    public static String[] fieldNames = {
        "FRM_FIELD_MASTER_PENJAMIN_OID",
        "FRM_FIELD_NO_ID_PENJAMIN",
        "FRM_FIELD_JENIS_IDENTITAS",
        "FRM_FIELD_NAMA_IDENTITAS",
        "FRM_FIELD_NAMA_LENGKAP",
        "FRM_FIELD_KODE_GOL_PENJAMIN",
        "FRM_FIELD_ALAMAT_PENJAMIN",
        "FRM_FIELD_KETERANGAN"
    };

    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmMasterPenjamin() {
    }

    public FrmMasterPenjamin(MasterPenjamin entMasterPenjamin) {
        this.entMasterPenjamin = entMasterPenjamin;
    }

    public FrmMasterPenjamin(HttpServletRequest request, MasterPenjamin entMasterPenjamin) {
        super(new FrmMasterPenjamin(entMasterPenjamin), request);
        this.entMasterPenjamin = entMasterPenjamin;
    }

    public String getFormName() {
        return FRM_NAME_MASTER_PENJAMIN;
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

    public MasterPenjamin getEntityObject() {
        return entMasterPenjamin;
    }

    public void requestEntityObject(MasterPenjamin entMasterPenjamin) {
        try {
            this.requestParam();
//            entMasterPenjamin.setMasterPenjaminOid(getLong(FRM_FIELD_MASTER_PENJAMIN_OID));
            entMasterPenjamin.setNoIdPenjamin(getString(FRM_FIELD_NO_ID_PENJAMIN));
            entMasterPenjamin.setJenisIdentitas(getString(FRM_FIELD_JENIS_IDENTITAS));
            entMasterPenjamin.setNamaIdentitas(getString(FRM_FIELD_NAMA_IDENTITAS));
            entMasterPenjamin.setNamaLengkap(getString(FRM_FIELD_NAMA_LENGKAP));
            entMasterPenjamin.setKodeGolPenjamin(getString(FRM_FIELD_KODE_GOL_PENJAMIN));
            entMasterPenjamin.setAlamatPenjamin(getString(FRM_FIELD_ALAMAT_PENJAMIN));
            entMasterPenjamin.setKeterangan(getString(FRM_FIELD_KETERANGAN));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }

}
