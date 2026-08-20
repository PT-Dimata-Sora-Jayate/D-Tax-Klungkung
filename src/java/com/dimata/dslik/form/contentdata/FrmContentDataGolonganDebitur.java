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

public class FrmContentDataGolonganDebitur extends FRMHandler implements I_FRMInterface, I_FRMType {
  private ContentDataGolonganDebitur entContentDataGolonganDebitur;
  public static final String FRM_CONTENT_DATA_GOLONGAN_DEBITUR = "FRM_CONTENT_DATA_GOLONGAN_DEBITUR";
  public static final int FRM_FIELD_GOLONGAN_DEBITUR_OID = 0;
  public static final int FRM_FIELD_GOLONGAN_DEBITUR = 1;
  public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
  public static final int FRM_FIELD_KODE_OJK = 3;


public static String[] fieldNames = {
    "FRM_FIELD_GOLONGAN_DEBITUR_OID",
    "FRM_FIELD_GOLONGAN_DEBITUR",
    "FRM_FIELD_KODE_CORE_BANKING",
    "FRM_FIELD_KODE_OJK"
};

public static int[] fieldTypes = {
    TYPE_LONG,
    TYPE_STRING,
    TYPE_STRING,
    TYPE_STRING
};

public FrmContentDataGolonganDebitur() {
}

public FrmContentDataGolonganDebitur(ContentDataGolonganDebitur entContentDataGolonganDebitur) {
this.entContentDataGolonganDebitur = entContentDataGolonganDebitur;
}

public FrmContentDataGolonganDebitur(HttpServletRequest request, ContentDataGolonganDebitur entContentDataGolonganDebitur) {
super(new FrmContentDataGolonganDebitur(entContentDataGolonganDebitur), request);
this.entContentDataGolonganDebitur = entContentDataGolonganDebitur;
}

public String getFormName() {
return FRM_CONTENT_DATA_GOLONGAN_DEBITUR;
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

public ContentDataGolonganDebitur getEntityObject() {
return entContentDataGolonganDebitur;
}

public void requestEntityObject(ContentDataGolonganDebitur entContentDataGolonganDebitur) {
try {
this.requestParam();
    //entContentDataGolonganDebitur.setGolonganDebiturOid(getLong(FRM_FIELD_GOLONGAN_DEBITUR_OID));
    entContentDataGolonganDebitur.setGolonganDebitur(getString(FRM_FIELD_GOLONGAN_DEBITUR));
    entContentDataGolonganDebitur.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
    entContentDataGolonganDebitur.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
} catch (Exception e) {
System.out.println("Error on requestEntityObject : " + e.toString());
}
}

}
