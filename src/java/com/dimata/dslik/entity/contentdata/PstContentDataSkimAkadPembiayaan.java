/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

/**
 *
 * @author Dewa
 */
public class PstContentDataSkimAkadPembiayaan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN = "dslik_cd_skim_akad_pembiayaan";
    public static final int FLD_AKAD_PEMBIAYAAN_OID = 0;
    public static final int FLD_NAMA_AKAD_PEMBIAYAAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "AKAD_PEMBIAYAAN_OID",
        "NAMA_AKAD_PEMBIAYAAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataSkimAkadPembiayaan() {
    }

    public PstContentDataSkimAkadPembiayaan(int i) throws DBException {
        super(new PstContentDataSkimAkadPembiayaan());
    }

    public PstContentDataSkimAkadPembiayaan(String sOid) throws DBException {
        super(new PstContentDataSkimAkadPembiayaan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataSkimAkadPembiayaan(long lOid) throws DBException {
        super(new PstContentDataSkimAkadPembiayaan(0));
        String sOid = "0";
        try {
            sOid = String.valueOf(lOid);
        } catch (Exception e) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public int getFieldSize() {
        return fieldNames.length;
    }

    public String getTableName() {
        return TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataSkimAkadPembiayaan().getClass().getName();
    }

    public static ContentDataSkimAkadPembiayaan fetchExc(long oid) throws DBException {
        try {
            ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan = new ContentDataSkimAkadPembiayaan();
            PstContentDataSkimAkadPembiayaan pstContentDataSkimAkadPembiayaan = new PstContentDataSkimAkadPembiayaan(oid);
            entContentDataSkimAkadPembiayaan.setOID(oid);
            entContentDataSkimAkadPembiayaan.setNamaAkadPembiayaan(pstContentDataSkimAkadPembiayaan.getString(FLD_NAMA_AKAD_PEMBIAYAAN));
            entContentDataSkimAkadPembiayaan.setKodeCoreBanking(pstContentDataSkimAkadPembiayaan.getString(FLD_KODE_CORE_BANKING));
            entContentDataSkimAkadPembiayaan.setKodeOjk(pstContentDataSkimAkadPembiayaan.getString(FLD_KODE_OJK));
            return entContentDataSkimAkadPembiayaan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSkimAkadPembiayaan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataSkimAkadPembiayaan;
        return entContentDataSkimAkadPembiayaan.getOID();
    }

    public static synchronized long updateExc(ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) throws DBException {
        try {
            if (entContentDataSkimAkadPembiayaan.getOID() != 0) {
                PstContentDataSkimAkadPembiayaan pstContentDataSkimAkadPembiayaan = new PstContentDataSkimAkadPembiayaan(entContentDataSkimAkadPembiayaan.getOID());
                pstContentDataSkimAkadPembiayaan.setString(FLD_NAMA_AKAD_PEMBIAYAAN, entContentDataSkimAkadPembiayaan.getNamaAkadPembiayaan());
                pstContentDataSkimAkadPembiayaan.setString(FLD_KODE_CORE_BANKING, entContentDataSkimAkadPembiayaan.getKodeCoreBanking());
                pstContentDataSkimAkadPembiayaan.setString(FLD_KODE_OJK, entContentDataSkimAkadPembiayaan.getKodeOjk());
                pstContentDataSkimAkadPembiayaan.update();
                return entContentDataSkimAkadPembiayaan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSkimAkadPembiayaan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataSkimAkadPembiayaan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataSkimAkadPembiayaan pstContentDataSkimAkadPembiayaan = new PstContentDataSkimAkadPembiayaan(oid);
            pstContentDataSkimAkadPembiayaan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSkimAkadPembiayaan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) throws DBException {
        try {
            PstContentDataSkimAkadPembiayaan pstContentDataSkimAkadPembiayaan = new PstContentDataSkimAkadPembiayaan(0);
            pstContentDataSkimAkadPembiayaan.setString(FLD_NAMA_AKAD_PEMBIAYAAN, entContentDataSkimAkadPembiayaan.getNamaAkadPembiayaan());
            pstContentDataSkimAkadPembiayaan.setString(FLD_KODE_CORE_BANKING, entContentDataSkimAkadPembiayaan.getKodeCoreBanking());
            pstContentDataSkimAkadPembiayaan.setString(FLD_KODE_OJK, entContentDataSkimAkadPembiayaan.getKodeOjk());
            pstContentDataSkimAkadPembiayaan.insert();
            entContentDataSkimAkadPembiayaan.setOID(pstContentDataSkimAkadPembiayaan.getlong(FLD_AKAD_PEMBIAYAAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSkimAkadPembiayaan(0), DBException.UNKNOWN);
        }
        return entContentDataSkimAkadPembiayaan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataSkimAkadPembiayaan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) {
        try {
            entContentDataSkimAkadPembiayaan.setOID(rs.getLong(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_AKAD_PEMBIAYAAN_OID]));
            entContentDataSkimAkadPembiayaan.setNamaAkadPembiayaan(rs.getString(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_NAMA_AKAD_PEMBIAYAAN]));
            entContentDataSkimAkadPembiayaan.setKodeCoreBanking(rs.getString(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_KODE_CORE_BANKING]));
            entContentDataSkimAkadPembiayaan.setKodeOjk(rs.getString(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan) {
        try {
            entContentDataSkimAkadPembiayaan.setNamaAkadPembiayaan(rs.getString(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_NAMA_AKAD_PEMBIAYAAN]));
            entContentDataSkimAkadPembiayaan.setKodeOjk(rs.getString(PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan = new ContentDataSkimAkadPembiayaan();
                resultToObject(rs, entContentDataSkimAkadPembiayaan);
                lists.add(entContentDataSkimAkadPembiayaan);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }
    
    public static Vector listWithoutOid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "+fieldNames[FLD_KODE_OJK]+","
                    + fieldNames[FLD_NAMA_AKAD_PEMBIAYAAN]+" "
                    + "FROM " + TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan = new ContentDataSkimAkadPembiayaan();
                resultToObjectWithoutOid(rs, entContentDataSkimAkadPembiayaan);
                lists.add(entContentDataSkimAkadPembiayaan);
            }
            rs.close();
            return lists;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBResultSet.close(dbrs);
        }
        return new Vector();
    }

    public static boolean checkOID(long entContentDataSkimAkadPembiayaanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN + " WHERE "
                    + PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_AKAD_PEMBIAYAAN_OID] + " = " + entContentDataSkimAkadPembiayaanId;
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }

    public static int getCount(String whereClause) {
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(" + PstContentDataSkimAkadPembiayaan.fieldNames[PstContentDataSkimAkadPembiayaan.FLD_AKAD_PEMBIAYAAN_OID] + ") FROM " + TBL_CONTENT_DATA_SKIM_AKAD_PEMBIAYAAN;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            DBResultSet.close(dbrs);
        }
    }

    public static int findLimitStart(long oid, int recordToGet, String whereClause, String orderClause) {
        int size = getCount(whereClause);
        int start = 0;
        boolean found = false;
        for (int i = 0; (i < size) && !found; i = i + recordToGet) {
            Vector list = list(i, recordToGet, whereClause, orderClause);
            start = i;
            if (list.size() > 0) {
                for (int ls = 0; ls < list.size(); ls++) {
                    ContentDataSkimAkadPembiayaan entContentDataSkimAkadPembiayaan = (ContentDataSkimAkadPembiayaan) list.get(ls);
                    if (oid == entContentDataSkimAkadPembiayaan.getOID()) {
                        found = true;
                    }
                }
            }
        }
        if ((start >= size) && (size > 0)) {
            start = start - recordToGet;
        }
        return start;
    }

    public static int findLimitCommand(int start, int recordToGet, int vectSize) {
        int cmd = Command.LIST;
        int mdl = vectSize % recordToGet;
        vectSize = vectSize + (recordToGet - mdl);
        if (start == 0) {
            cmd = Command.FIRST;
        } else {
            if (start == (vectSize - recordToGet)) {
                cmd = Command.LAST;
            } else {
                start = start + recordToGet;
                if (start <= (vectSize - recordToGet)) {
                    cmd = Command.NEXT;
                    System.out.println("next.......................");
                } else {
                    start = start - recordToGet;
                    if (start > 0) {
                        cmd = Command.PREV;
                        System.out.println("prev.......................");
                    }
                }
            }
        }
        return cmd;
    }
}
