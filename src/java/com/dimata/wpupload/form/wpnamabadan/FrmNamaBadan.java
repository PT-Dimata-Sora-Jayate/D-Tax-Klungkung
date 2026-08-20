/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.form.wpnamabadan;

import com.dimata.qdep.form.FRMHandler;
import com.dimata.qdep.form.I_FRMInterface;
import com.dimata.qdep.form.I_FRMType;
import com.dimata.wpupload.entity.wpnamabadan.NamaBadan;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ardiadi
 */
public class FrmNamaBadan extends FRMHandler implements I_FRMInterface, I_FRMType {
  private NamaBadan entNamaBadan;
  public static final String FRM_NAME_NAMABADAN = "FRM_NAME_NAMABADAN";
  public static final int FRM_FIELD_NPWPD = 0;
  public static final int FRM_FIELD_NAMA = 1;
  public static final int FRM_FIELD_ALAMAT = 2;
  public static final int FRM_FIELD_RT = 3;
  public static final int FRM_FIELD_RW = 4;
  public static final int FRM_FIELD_KELURAHAN = 5;
  public static final int FRM_FIELD_KECAMATAN = 6;
  public static final int FRM_FIELD_KABUPATEN = 7;
  public static final int FRM_FIELD_NAMA_BADAN = 8;
  public static final int FRM_FIELD_JENIS_USAHA = 9;
  public static final int FRM_FIELD_ALAMAT_USAHA = 10;
  public static final int FRM_FIELD_KODE_JENIS_USAHA = 11;
  public static final int FRM_FIELD_KODE_JENIS_WP = 12;
  public static final int FRM_FIELD_SUBJENIS_WP = 13;
  public static final int FRM_FIELD_KD_LOKASI = 14;
  public static final int FRM_FIELD_TARIF = 15;


public static String[] fieldNames = {
    "FRM_FIELD_NPWPD",
    "FRM_FIELD_NAMA",
    "FRM_FIELD_ALAMAT",
    "FRM_FIELD_RT",
    "FRM_FIELD_RW",
    "FRM_FIELD_KELURAHAN",
    "FRM_FIELD_KECAMATAN",
    "FRM_FIELD_KABUPATEN",
    "FRM_FIELD_NAMA_BADAN",
    "FRM_FIELD_JENIS_USAHA",
    "FRM_FIELD_ALAMAT_USAHA",
    "FRM_FIELD_KODE_JENIS_USAHA",
    "FRM_FIELD_KODE_JENIS_WP",
    "FRM_FIELD_SUBJENIS_WP",
    "FRM_FIELD_KD_LOKASI",
    "FRM_FIELD_TARIF"
};

public static int[] fieldTypes = {
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_FLOAT
};

public FrmNamaBadan() {
}

public FrmNamaBadan(NamaBadan entNamaBadan) {
this.entNamaBadan = entNamaBadan;
}

public FrmNamaBadan(HttpServletRequest request, NamaBadan entNamaBadan) {
super(new FrmNamaBadan(entNamaBadan), request);
this.entNamaBadan = entNamaBadan;
}

public String getFormName() {
return FRM_NAME_NAMABADAN;
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

public NamaBadan getEntityObject() {
return entNamaBadan;
}

public void requestEntityObject(NamaBadan entNamaBadan) {
try {
this.requestParam();
    entNamaBadan.setNbNPWPD(getString(FRM_FIELD_NPWPD));
    entNamaBadan.setNbNama(getString(FRM_FIELD_NAMA));
    entNamaBadan.setNbAlamat(getString(FRM_FIELD_ALAMAT));
    entNamaBadan.setNbRT(getString(FRM_FIELD_RT));
    entNamaBadan.setNbRW(getString(FRM_FIELD_RW));
    entNamaBadan.setNbKelurahan(getString(FRM_FIELD_KELURAHAN));
    entNamaBadan.setNbKecamatan(getString(FRM_FIELD_KECAMATAN));
    entNamaBadan.setNbKabupaten(getString(FRM_FIELD_KABUPATEN));
    entNamaBadan.setNbNamaBadan(getString(FRM_FIELD_NAMA_BADAN));
    entNamaBadan.setNbJenisUsaha(getString(FRM_FIELD_JENIS_USAHA));
    entNamaBadan.setNbAlamatUsaha(getString(FRM_FIELD_ALAMAT_USAHA));
    entNamaBadan.setNbKDJenisUsaha(getString(FRM_FIELD_KODE_JENIS_USAHA));
    entNamaBadan.setNbKDJenisWP(getString(FRM_FIELD_KODE_JENIS_WP));
    entNamaBadan.setNbSubjenisWP(getString(FRM_FIELD_SUBJENIS_WP));
    entNamaBadan.setNbKDLokasi(getString(FRM_FIELD_KD_LOKASI));
    entNamaBadan.setNbTarif(getDouble(FRM_FIELD_TARIF));
} catch (Exception e) {
System.out.println("Error on requestEntityObject : " + e.toString());
}
}
}
