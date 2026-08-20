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

public class FrmContentDataNegaraDomisili extends FRMHandler implements I_FRMInterface, I_FRMType {

    private ContentDataNegaraDomisili entContentDataNegaraDomisili;
    public static final String FRM_CONTENT_DATA_NEGARA_DOMISILI = "FRM_CONTENT_DATA_NEGARA_DOMISILI";
    public static final int FRM_FIELD_NEGARA_DOMISILI_OID = 0;
    public static final int FRM_FIELD_NAMANEGARA = 1;
    public static final int FRM_FIELD_KODE_CORE_BANKING = 2;
    public static final int FRM_FIELD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "FRM_FIELD_NEGARA_DOMISILI_OID",
        "FRM_FIELD_NAMANEGARA",
        "FRM_FIELD_KODE_CORE_BANKING",
        "FRM_FIELD_KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public FrmContentDataNegaraDomisili() {
    }

    public FrmContentDataNegaraDomisili(ContentDataNegaraDomisili entContentDataNegaraDomisili) {
        this.entContentDataNegaraDomisili = entContentDataNegaraDomisili;
    }

    public FrmContentDataNegaraDomisili(HttpServletRequest request, ContentDataNegaraDomisili entContentDataNegaraDomisili) {
        super(new FrmContentDataNegaraDomisili(entContentDataNegaraDomisili), request);
        this.entContentDataNegaraDomisili = entContentDataNegaraDomisili;
    }

    public String getFormName() {
        return FRM_CONTENT_DATA_NEGARA_DOMISILI;
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

    public ContentDataNegaraDomisili getEntityObject() {
        return entContentDataNegaraDomisili;
    }

    public void requestEntityObject(ContentDataNegaraDomisili entContentDataNegaraDomisili) {
        try {
            this.requestParam();
            //entContentDataNegaraDomisili.setNegaraDomisiliOid(getLong(FRM_FIELD_NEGARA_DOMISILI_OID));
            entContentDataNegaraDomisili.setNamaNegara(getString(FRM_FIELD_NAMANEGARA));
            entContentDataNegaraDomisili.setKodeCoreBanking(getString(FRM_FIELD_KODE_CORE_BANKING));
            entContentDataNegaraDomisili.setKodeOjk(getString(FRM_FIELD_KODE_OJK));
        } catch (Exception e) {
            System.out.println("Error on requestEntityObject : " + e.toString());
        }
    }
}
