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
public class PstContentDataStatusPerkawinanDebitur extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR = "dslik_cd_status_perkawinan_debitur";
    public static final int FLD_STATUS_PERKAWINAN_OID = 0;
    public static final int FLD_STATUS_PERKAWINAN = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "STATUS_PERKAWINAN_OID",
        "STATUS_PERKAWINAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataStatusPerkawinanDebitur() {
    }

    public PstContentDataStatusPerkawinanDebitur(int i) throws DBException {
        super(new PstContentDataStatusPerkawinanDebitur());
    }

    public PstContentDataStatusPerkawinanDebitur(String sOid) throws DBException {
        super(new PstContentDataStatusPerkawinanDebitur(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataStatusPerkawinanDebitur(long lOid) throws DBException {
        super(new PstContentDataStatusPerkawinanDebitur(0));
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
        return TBL_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataStatusPerkawinanDebitur().getClass().getName();
    }

    public static ContentDataStatusPerkawinanDebitur fetchExc(long oid) throws DBException {
        try {
            ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur = new ContentDataStatusPerkawinanDebitur();
            PstContentDataStatusPerkawinanDebitur pstContentDataStatusPerkawinanDebitur = new PstContentDataStatusPerkawinanDebitur(oid);
            entContentDataStatusPerkawinanDebitur.setOID(oid);
            entContentDataStatusPerkawinanDebitur.setStatusPerkawinan(pstContentDataStatusPerkawinanDebitur.getString(FLD_STATUS_PERKAWINAN));
            entContentDataStatusPerkawinanDebitur.setKodeCoreBanking(pstContentDataStatusPerkawinanDebitur.getString(FLD_KODE_CORE_BANKING));
            entContentDataStatusPerkawinanDebitur.setKodeOjk(pstContentDataStatusPerkawinanDebitur.getString(FLD_KODE_OJK));
            return entContentDataStatusPerkawinanDebitur;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPerkawinanDebitur(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur = fetchExc(entity.getOID());
        entity = (Entity) entContentDataStatusPerkawinanDebitur;
        return entContentDataStatusPerkawinanDebitur.getOID();
    }

    public static synchronized long updateExc(ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) throws DBException {
        try {
            if (entContentDataStatusPerkawinanDebitur.getOID() != 0) {
                PstContentDataStatusPerkawinanDebitur pstContentDataStatusPerkawinanDebitur = new PstContentDataStatusPerkawinanDebitur(entContentDataStatusPerkawinanDebitur.getOID());
                pstContentDataStatusPerkawinanDebitur.setString(FLD_STATUS_PERKAWINAN, entContentDataStatusPerkawinanDebitur.getStatusPerkawinan());
                pstContentDataStatusPerkawinanDebitur.setString(FLD_KODE_CORE_BANKING, entContentDataStatusPerkawinanDebitur.getKodeCoreBanking());
                pstContentDataStatusPerkawinanDebitur.setString(FLD_KODE_OJK, entContentDataStatusPerkawinanDebitur.getKodeOjk());
                pstContentDataStatusPerkawinanDebitur.update();
                return entContentDataStatusPerkawinanDebitur.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPerkawinanDebitur(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataStatusPerkawinanDebitur) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataStatusPerkawinanDebitur pstContentDataStatusPerkawinanDebitur = new PstContentDataStatusPerkawinanDebitur(oid);
            pstContentDataStatusPerkawinanDebitur.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPerkawinanDebitur(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) throws DBException {
        try {
            PstContentDataStatusPerkawinanDebitur pstContentDataStatusPerkawinanDebitur = new PstContentDataStatusPerkawinanDebitur(0);
            pstContentDataStatusPerkawinanDebitur.setString(FLD_STATUS_PERKAWINAN, entContentDataStatusPerkawinanDebitur.getStatusPerkawinan());
            pstContentDataStatusPerkawinanDebitur.setString(FLD_KODE_CORE_BANKING, entContentDataStatusPerkawinanDebitur.getKodeCoreBanking());
            pstContentDataStatusPerkawinanDebitur.setString(FLD_KODE_OJK, entContentDataStatusPerkawinanDebitur.getKodeOjk());
            pstContentDataStatusPerkawinanDebitur.insert();
            entContentDataStatusPerkawinanDebitur.setOID(pstContentDataStatusPerkawinanDebitur.getlong(FLD_STATUS_PERKAWINAN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataStatusPerkawinanDebitur(0), DBException.UNKNOWN);
        }
        return entContentDataStatusPerkawinanDebitur.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataStatusPerkawinanDebitur) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) {
        try {
            entContentDataStatusPerkawinanDebitur.setOID(rs.getLong(PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_STATUS_PERKAWINAN_OID]));
            entContentDataStatusPerkawinanDebitur.setStatusPerkawinan(rs.getString(PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_STATUS_PERKAWINAN]));
            entContentDataStatusPerkawinanDebitur.setKodeCoreBanking(rs.getString(PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_KODE_CORE_BANKING]));
            entContentDataStatusPerkawinanDebitur.setKodeOjk(rs.getString(PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWitoutOid(ResultSet rs, ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur) {
        try {
            entContentDataStatusPerkawinanDebitur.setStatusPerkawinan(rs.getString(PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_STATUS_PERKAWINAN]));
            entContentDataStatusPerkawinanDebitur.setKodeOjk(rs.getString(PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR;
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
                ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur = new ContentDataStatusPerkawinanDebitur();
                resultToObject(rs, entContentDataStatusPerkawinanDebitur);
                lists.add(entContentDataStatusPerkawinanDebitur);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_KODE_OJK]+", "
                    + fieldNames[FLD_STATUS_PERKAWINAN]+" "
                    + "FROM " + TBL_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR;
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
                ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur = new ContentDataStatusPerkawinanDebitur();
                resultToObjectWitoutOid(rs, entContentDataStatusPerkawinanDebitur);
                lists.add(entContentDataStatusPerkawinanDebitur);
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

    public static boolean checkOID(long entContentDataStatusPerkawinanDebiturId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR + " WHERE "
                    + PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_STATUS_PERKAWINAN_OID] + " = " + entContentDataStatusPerkawinanDebiturId;
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
            String sql = "SELECT COUNT(" + PstContentDataStatusPerkawinanDebitur.fieldNames[PstContentDataStatusPerkawinanDebitur.FLD_STATUS_PERKAWINAN_OID] + ") FROM " + TBL_CONTENT_DATA_STATUS_PERKAWINAN_DEBITUR;
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
                    ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur = (ContentDataStatusPerkawinanDebitur) list.get(ls);
                    if (oid == entContentDataStatusPerkawinanDebitur.getOID()) {
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
