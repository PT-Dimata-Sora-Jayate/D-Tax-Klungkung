/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.form.compareesptpd;

import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.wpupload.entity.compareesptpd.CompareEsptpd;
import com.dimata.wpupload.entity.esptpd.ESPTPD;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ardiadi
 */
public class FrmCompareEsptpd extends FRMHandler implements I_FRMInterface, I_FRMType {
  private CompareEsptpd entCompareEsptpd;
  public static final String FRM_NAME_ESPTPD = "FRM_NAME_ESPTPD";
  public static final int FRM_FIELD_E_NPWPD = 0;
  public static final int FRM_FIELD_E_TGL_INPUT = 1;
  public static final int FRM_FIELD_E_MASA_PAJAK = 2;
  public static final int FRM_FIELD_E_TAHUN_PAJAK = 3;
  public static final int FRM_FIELD_E_TGL_REKAM = 4;
  public static final int FRM_FIELD_E_NIP_REKAM = 5;
  public static final int FRM_FIELD_E_ID_REKAM = 6;
  public static final int FRM_FIELD_E_TGL_UBAH = 7;
  public static final int FRM_FIELD_E_NIP_UBAH = 8;
  public static final int FRM_FIELD_E_ID_UBAH = 9;
  public static final int FRM_FIELD_E_JUMLAM_OMZET = 10;
  public static final int FRM_FIELD_E_TARIF = 11;
  public static final int FRM_FIELD_E_JUMLAH_PAJAK = 12;
  public static final int FRM_FIELD_E_SERVICE_TAX = 13;
  public static final int FRM_FIELD_E_DENDA = 14;
  public static final int FRM_FIELD_E_PENGURANGAN = 15;
  public static final int FRM_FIELD_E_HARUS_DIBAYAR = 16;
  public static final int FRM_FIELD_E_KETERANGAN = 17;
  public static final int FRM_FIELD_E_KODE_PAJAK = 18;
  public static final int FRM_FIELD_E_NO_REKENING = 19;
  public static final int FRM_FIELD_E_NO_SUBREKENING = 20;
  public static final int FRM_FIELD_E_KODE_LOKASI = 21;
  public static final int FRM_FIELD_E_NO_SPTPD = 22;
  public static final int FRM_FIELD_E_NO_SSPD = 23;


public static String[] fieldNames = {
    "FRM_FIELD_E_NPWPD",
    "FRM_FIELD_E_TGL_INPUT",
    "FRM_FIELD_E_MASA_PAJAK",
    "FRM_FIELD_E_TAHUN_PAJAK",
    "FRM_FIELD_E_TGL_REKAM",
    "FRM_FIELD_E_NIP_REKAM",
    "FRM_FIELD_E_ID_REKAM",
    "FRM_FIELD_E_TGL_UBAH",
    "FRM_FIELD_E_NIP_UBAH",
    "FRM_FIELD_E_ID_UBAH",
    "FRM_FIELD_E_JUMLAM_OMZET",
    "FRM_FIELD_E_TARIF",
    "FRM_FIELD_E_JUMLAH_PAJAK",
    "FRM_FIELD_E_SERVICE_TAX",
    "FRM_FIELD_E_DENDA",
    "FRM_FIELD_E_PENGURANGAN",
    "FRM_FIELD_E_HARUS_DIBAYAR",
    "FRM_FIELD_E_KETERANGAN",
    "FRM_FIELD_E_KODE_PAJAK",
    "FRM_FIELD_E_NO_REKENING",
    "FRM_FIELD_E_NO_SUBREKENING",
    "FRM_FIELD_E_KODE_LOKASI",
    "FRM_FIELD_E_NO_SPTPD",
    "FRM_FIELD_E_NO_SSPD"
};

public static int[] fieldTypes = {
    TYPE_STRING,
    TYPE_DATE,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_DATE,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_DATE,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_FLOAT,
    TYPE_FLOAT,
    TYPE_FLOAT,
    TYPE_FLOAT,
    TYPE_FLOAT,
    TYPE_FLOAT,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING
};

public FrmCompareEsptpd() {
}

public FrmCompareEsptpd(CompareEsptpd entCompareEsptpd) {
this.entCompareEsptpd = entCompareEsptpd;
}

public FrmCompareEsptpd(HttpServletRequest request, CompareEsptpd entCompareEsptpd) {
super(new FrmCompareEsptpd(entCompareEsptpd), request);
this.entCompareEsptpd = entCompareEsptpd;
}

public String getFormName() {
return FRM_NAME_ESPTPD;
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

public CompareEsptpd getEntityObject() {
return entCompareEsptpd;
}

public void requestEntityObject(CompareEsptpd entCompareEsptpd) {
try {
this.requestParam();
    entCompareEsptpd.setCompareENpwpd(getString(FRM_FIELD_E_NPWPD));
    entCompareEsptpd.setCompareETglInput(getDate(FRM_FIELD_E_TGL_INPUT));
    entCompareEsptpd.setCompareEMasaPajak(getString(FRM_FIELD_E_MASA_PAJAK));
    entCompareEsptpd.setCompareETahunPajak(getString(FRM_FIELD_E_TAHUN_PAJAK));
    entCompareEsptpd.setCompareETglRekam(getDate(FRM_FIELD_E_TGL_REKAM));
    entCompareEsptpd.setCompareENIPRekam(getString(FRM_FIELD_E_NIP_REKAM));
    entCompareEsptpd.setCompareEIdRekam(getString(FRM_FIELD_E_ID_REKAM));
    entCompareEsptpd.setCompareETglUbah(getDate(FRM_FIELD_E_TGL_UBAH));
    entCompareEsptpd.setCompareENIPUbah(getString(FRM_FIELD_E_NIP_UBAH));
    entCompareEsptpd.setCompareEIDUbah(getString(FRM_FIELD_E_ID_UBAH));
    entCompareEsptpd.setCompareEJumlahOmzet(getString(FRM_FIELD_E_JUMLAM_OMZET));
    entCompareEsptpd.setCompareETarif(getFloat(FRM_FIELD_E_TARIF));
    entCompareEsptpd.setCompareEJumlahPajak(getFloat(FRM_FIELD_E_JUMLAH_PAJAK));
    entCompareEsptpd.setCompareEServiceTax(getFloat(FRM_FIELD_E_SERVICE_TAX));
    entCompareEsptpd.setCompareEDenda(getFloat(FRM_FIELD_E_DENDA));
    entCompareEsptpd.setCompareEPengurangan(getFloat(FRM_FIELD_E_PENGURANGAN));
    entCompareEsptpd.setCompareEHarusBayar(getFloat(FRM_FIELD_E_HARUS_DIBAYAR));
    entCompareEsptpd.setCompareEKeterangan(getString(FRM_FIELD_E_KETERANGAN));
    entCompareEsptpd.setCompareEKodePajak(getString(FRM_FIELD_E_KODE_PAJAK));
    entCompareEsptpd.setCompareENoRekening(getString(FRM_FIELD_E_NO_REKENING));
    entCompareEsptpd.setCompareENoSubrekening(getString(FRM_FIELD_E_NO_SUBREKENING));
    entCompareEsptpd.setCompareEKodeLokasi(getString(FRM_FIELD_E_KODE_LOKASI));
    entCompareEsptpd.setCompareENoSPTPD(getString(FRM_FIELD_E_NO_SPTPD));
    entCompareEsptpd.setCompareENoSSPD(getString(FRM_FIELD_E_NO_SSPD));
} catch (Exception e) {
System.out.println("Error on requestEntityObject : " + e.toString());
}
}

}
