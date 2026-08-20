
package com.dimata.wponline.form.wajibpajakonline;

import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.wponline.entity.wajibpajakonline.WajibPajak;
import com.dimata.wpupload.form.wpnamabadan.FrmNamaBadan;
import javax.servlet.http.HttpServletRequest;


public class FrmWajibPajak 
extends FRMHandler 
implements I_FRMInterface, I_FRMType {
    
    private WajibPajak wajibPajak;
    public static final String FRM_WAJIB_PAJAK = "FRM_WAJIB_PAJAK_ONLINE";
    public static final int FRM_FIELD_WP_ID = 0;
    public static final int FRM_FIELD_NAMA_USER = 1;
    public static final int FRM_FIELD_PASSWORD = 2;
    public static final int FRM_FIELD_NAMA_WAJIB_PAJAK = 3;
    public static final int FRM_FIELD_ALAMAT = 4;
    public static final int FRM_FIELD_NOP = 5;
    public static final int FRM_FIELD_TELP = 6;
    public static final int FRM_FIELD_FAX = 7;
    public static final int FRM_FIELD_STATUS = 8;
    public static final int FRM_FIELD_EMAIL = 9;
    public static final int FRM_FIELD_KODE_KONFIRMASI = 10;
  
    public static String[] fieldNames = {
        "FRM_FIELD_WP_ID",
        "FRM_FIELD_NAMA_USER",
        "FRM_FIELD_PASSWORD",
        "FRM_FIELD_NAMA_WAJIB_PAJAK",
        "FRM_FIELD_ALAMAT",
        "FRM_FIELD_NOP",
        "FRM_FIELD_TELP",
        "FRM_FIELD_FAX",
        "FRM_FIELD_STATUS",
        "FRM_FIELD_EMAIL",
        "FRM_FIELD_KODE_KONFIRMASI"
    };
    
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING + ENTRY_REQUIRED,
        TYPE_STRING + ENTRY_REQUIRED,
        TYPE_STRING + ENTRY_REQUIRED,
        TYPE_STRING,
        TYPE_STRING + ENTRY_REQUIRED,
        TYPE_STRING + ENTRY_REQUIRED,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING + ENTRY_REQUIRED,
        TYPE_STRING
    };
    
    public FrmWajibPajak() {
    }

    public FrmWajibPajak(WajibPajak wajibPajak) {
        this.wajibPajak = wajibPajak;
    }

    public FrmWajibPajak(HttpServletRequest request, WajibPajak wajibPajak) {
        super(new FrmWajibPajak(wajibPajak), request);
        this.wajibPajak = wajibPajak;
    }
    
    
    @Override
    public int getFieldSize() {
        return fieldNames.length;
    }

    @Override
    public String getFormName() {
        return FRM_WAJIB_PAJAK;
    }

    @Override
    public String[] getFieldNames() {
        return fieldNames;
    }

    @Override
    public int[] getFieldTypes() {
        return fieldTypes;
    }
    
    public void requestEntityObject(WajibPajak wajibPajak) {
    try {
        this.requestParam();
        wajibPajak.setNamaUser(getString(FRM_FIELD_NAMA_USER));
        wajibPajak.setPassword(getString(FRM_FIELD_PASSWORD));
        wajibPajak.setNamaWajibPajak(getString(FRM_FIELD_NAMA_WAJIB_PAJAK));
        wajibPajak.setAlamat(getString(FRM_FIELD_ALAMAT));
        wajibPajak.setNop(getString(FRM_FIELD_NOP));
        wajibPajak.setTelp(getString(FRM_FIELD_TELP));
        wajibPajak.setFax(getString(FRM_FIELD_FAX));
        wajibPajak.setStatus(getString(FRM_FIELD_STATUS));
        wajibPajak.setEmail(getString(FRM_FIELD_EMAIL));
        wajibPajak.setKodeKonfirmasi(getString(FRM_FIELD_KODE_KONFIRMASI));
        
    } catch (Exception e) {
        System.out.println("Error on requestEntityObject : " + e.toString());
    }
}
    
}
