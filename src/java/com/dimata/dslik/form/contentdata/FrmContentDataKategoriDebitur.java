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

public class FrmContentDataKategoriDebitur extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataKategoriDebitur entContentDataKategoriDebitur;
    public static final String FRM_CONTENT_DATA_KATEGORI_DEBITUR = "FRM_NAME_CONTENTDATAKATEGORIDEBITUR";
    public static final int FRM_FIELD_KATEGORI_DEBITUR_OID = 0;
    public static final int FRM_FIELD_NAMA_KATEGORI_DEBITUR = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_KATEGORI_DEBITUR_OID",
        "FRM_FIELD_NAMA_KATEGORI_DEBITUR",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataKategoriDebitur() {
    }

    public FrmContentDataKategoriDebitur(ContentDataKategoriDebitur entContentDataKategoriDebitur) {
        this.entContentDataKategoriDebitur = entContentDataKategoriDebitur;
    }

    public FrmContentDataKategoriDebitur(HttpServletRequest request, ContentDataKategoriDebitur entContentDataKategoriDebitur) {
        super(new FrmContentDataKategoriDebitur(entContentDataKategoriDebitur), request);
        this.entContentDataKategoriDebitur = entContentDataKategoriDebitur;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_KATEGORI_DEBITUR;
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

    public ContentDataKategoriDebitur getEntityObject() {
        return entContentDataKategoriDebitur;
    }

    public void requestEntityObject(ContentDataKategoriDebitur entContentDataKategoriDebitur) {
        try {
            this.requestParam();
            //entContentDataKategoriDebitur.setKategoriDebiturOid(getLong(FRM_FIELD_KATEGORI_DEBITUR_OID));
            entContentDataKategoriDebitur.setNamaKategoriDebitur(getString(FRM_FIELD_NAMA_KATEGORI_DEBITUR));
            entContentDataKategoriDebitur.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataKategoriDebitur.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
