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
public class PstContentDataTujuanGaransi extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_TUJUAN_GARANSI = "dslik_cd_tujuan_garansi";
    public static final int FLD_TUJUAN_GARANSI_OID = 0;
    public static final int FLD_TUJUAN_GARANSI = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "TUJUAN_GARANSI_OID",
        "TUJUAN_GARANSI",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataTujuanGaransi() {
    }

    public PstContentDataTujuanGaransi(int i) throws DBException {
        super(new PstContentDataTujuanGaransi());
    }

    public PstContentDataTujuanGaransi(String sOid) throws DBException {
        super(new PstContentDataTujuanGaransi(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataTujuanGaransi(long lOid) throws DBException {
        super(new PstContentDataTujuanGaransi(0));
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
        return TBL_CONTENT_DATA_TUJUAN_GARANSI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataTujuanGaransi().getClass().getName();
    }

    public static ContentDataTujuanGaransi fetchExc(long oid) throws DBException {
        try {
            ContentDataTujuanGaransi entContentDataTujuanGaransi = new ContentDataTujuanGaransi();
            PstContentDataTujuanGaransi pstContentDataTujuanGaransi = new PstContentDataTujuanGaransi(oid);
            entContentDataTujuanGaransi.setOID(oid);
            entContentDataTujuanGaransi.setTujuanGaransi(pstContentDataTujuanGaransi.getString(FLD_TUJUAN_GARANSI));
            entContentDataTujuanGaransi.setKodeCoreBanking(pstContentDataTujuanGaransi.getString(FLD_KODE_CORE_BANKING));
            entContentDataTujuanGaransi.setKodeOjk(pstContentDataTujuanGaransi.getString(FLD_KODE_OJK));
            return entContentDataTujuanGaransi;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanGaransi(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataTujuanGaransi entContentDataTujuanGaransi = fetchExc(entity.getOID());
        entity = (Entity) entContentDataTujuanGaransi;
        return entContentDataTujuanGaransi.getOID();
    }

    public static synchronized long updateExc(ContentDataTujuanGaransi entContentDataTujuanGaransi) throws DBException {
        try {
            if (entContentDataTujuanGaransi.getOID() != 0) {
                PstContentDataTujuanGaransi pstContentDataTujuanGaransi = new PstContentDataTujuanGaransi(entContentDataTujuanGaransi.getOID());
                pstContentDataTujuanGaransi.setString(FLD_TUJUAN_GARANSI, entContentDataTujuanGaransi.getTujuanGaransi());
                pstContentDataTujuanGaransi.setString(FLD_KODE_CORE_BANKING, entContentDataTujuanGaransi.getKodeCoreBanking());
                pstContentDataTujuanGaransi.setString(FLD_KODE_OJK, entContentDataTujuanGaransi.getKodeOjk());
                pstContentDataTujuanGaransi.update();
                return entContentDataTujuanGaransi.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanGaransi(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataTujuanGaransi) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataTujuanGaransi pstContentDataTujuanGaransi = new PstContentDataTujuanGaransi(oid);
            pstContentDataTujuanGaransi.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanGaransi(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataTujuanGaransi entContentDataTujuanGaransi) throws DBException {
        try {
            PstContentDataTujuanGaransi pstContentDataTujuanGaransi = new PstContentDataTujuanGaransi(0);
            pstContentDataTujuanGaransi.setString(FLD_TUJUAN_GARANSI, entContentDataTujuanGaransi.getTujuanGaransi());
            pstContentDataTujuanGaransi.setString(FLD_KODE_CORE_BANKING, entContentDataTujuanGaransi.getKodeCoreBanking());
            pstContentDataTujuanGaransi.setString(FLD_KODE_OJK, entContentDataTujuanGaransi.getKodeOjk());
            pstContentDataTujuanGaransi.insert();
            entContentDataTujuanGaransi.setOID(pstContentDataTujuanGaransi.getlong(FLD_TUJUAN_GARANSI_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataTujuanGaransi(0), DBException.UNKNOWN);
        }
        return entContentDataTujuanGaransi.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataTujuanGaransi) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataTujuanGaransi entContentDataTujuanGaransi) {
        try {
            entContentDataTujuanGaransi.setOID(rs.getLong(PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI_OID]));
            entContentDataTujuanGaransi.setTujuanGaransi(rs.getString(PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI]));
            entContentDataTujuanGaransi.setKodeCoreBanking(rs.getString(PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_KODE_CORE_BANKING]));
            entContentDataTujuanGaransi.setKodeOjk(rs.getString(PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataTujuanGaransi entContentDataTujuanGaransi) {
        try {
            entContentDataTujuanGaransi.setTujuanGaransi(rs.getString(PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI]));
            entContentDataTujuanGaransi.setKodeOjk(rs.getString(PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_TUJUAN_GARANSI;
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
                ContentDataTujuanGaransi entContentDataTujuanGaransi = new ContentDataTujuanGaransi();
                resultToObject(rs, entContentDataTujuanGaransi);
                lists.add(entContentDataTujuanGaransi);
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
                    + fieldNames[FLD_TUJUAN_GARANSI]+" "
                    + "FROM " + TBL_CONTENT_DATA_TUJUAN_GARANSI;
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
                ContentDataTujuanGaransi entContentDataTujuanGaransi = new ContentDataTujuanGaransi();
                resultToObjectWithoutOid(rs, entContentDataTujuanGaransi);
                lists.add(entContentDataTujuanGaransi);
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

    public static boolean checkOID(long entContentDataTujuanGaransiId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_TUJUAN_GARANSI + " WHERE "
                    + PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI_OID] + " = " + entContentDataTujuanGaransiId;
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
            String sql = "SELECT COUNT(" + PstContentDataTujuanGaransi.fieldNames[PstContentDataTujuanGaransi.FLD_TUJUAN_GARANSI_OID] + ") FROM " + TBL_CONTENT_DATA_TUJUAN_GARANSI;
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
                    ContentDataTujuanGaransi entContentDataTujuanGaransi = (ContentDataTujuanGaransi) list.get(ls);
                    if (oid == entContentDataTujuanGaransi.getOID()) {
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
