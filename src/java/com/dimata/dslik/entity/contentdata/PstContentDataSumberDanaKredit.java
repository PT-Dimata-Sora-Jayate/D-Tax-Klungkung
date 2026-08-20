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
 * @author dimata005
 */
public class PstContentDataSumberDanaKredit extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language{
    
    public static final String TBL_CONTENT_DATA_SUMBER_DANA_KREDIT = "dslik_cd_sumber_dana";
    public static final int FLD_SUMBER_DANA_KREDIT_OID = 0;
    public static final int FLD_NAMA_SUMBER_DANA_KREDIT = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "SUMBER_DANA_OID",
        "SUMBER_DANA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataSumberDanaKredit() {
    }

    public PstContentDataSumberDanaKredit(int i) throws DBException {
        super(new PstContentDataSumberDanaKredit());
    }

    public PstContentDataSumberDanaKredit(String sOid) throws DBException {
        super(new PstContentDataSumberDanaKredit(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataSumberDanaKredit(long lOid) throws DBException {
        super(new PstContentDataSumberDanaKredit(0));
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
        return TBL_CONTENT_DATA_SUMBER_DANA_KREDIT;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataSumberDanaKredit().getClass().getName();
    }

    public static ContentDataSumberDanaKredit fetchExc(long oid) throws DBException {
        try {
            ContentDataSumberDanaKredit entContentDataSumberDanaKredit = new ContentDataSumberDanaKredit();
            PstContentDataSumberDanaKredit pstContentDataSumberDanaKredit = new PstContentDataSumberDanaKredit(oid);
            entContentDataSumberDanaKredit.setOID(oid);
            entContentDataSumberDanaKredit.setSumberDanaKredit(pstContentDataSumberDanaKredit.getString(FLD_NAMA_SUMBER_DANA_KREDIT));
            entContentDataSumberDanaKredit.setKodeCoreBanking(pstContentDataSumberDanaKredit.getString(FLD_KODE_CORE_BANKING));
            entContentDataSumberDanaKredit.setKodeOjk(pstContentDataSumberDanaKredit.getString(FLD_KODE_OJK));
            return entContentDataSumberDanaKredit;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberDanaKredit(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataSumberDanaKredit entContentDataSumberDanaKredit = fetchExc(entity.getOID());
        entity = (Entity) entContentDataSumberDanaKredit;
        return entContentDataSumberDanaKredit.getOID();
    }

    public static synchronized long updateExc(ContentDataSumberDanaKredit entContentDataSumberDanaKredit) throws DBException {
        try {
            if (entContentDataSumberDanaKredit.getOID() != 0) {
                PstContentDataSumberDanaKredit pstContentDataSumberDanaKredit = new PstContentDataSumberDanaKredit(entContentDataSumberDanaKredit.getOID());
                pstContentDataSumberDanaKredit.setString(FLD_NAMA_SUMBER_DANA_KREDIT, entContentDataSumberDanaKredit.getSumberDanaKredit());
                pstContentDataSumberDanaKredit.setString(FLD_KODE_CORE_BANKING, entContentDataSumberDanaKredit.getKodeCoreBanking());
                pstContentDataSumberDanaKredit.setString(FLD_KODE_OJK, entContentDataSumberDanaKredit.getKodeOjk());
                pstContentDataSumberDanaKredit.update();
                return entContentDataSumberDanaKredit.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberDanaKredit(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataSumberDanaKredit) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataSumberDanaKredit pstContentDataSumberDanaKredit = new PstContentDataSumberDanaKredit(oid);
            pstContentDataSumberDanaKredit.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberDanaKredit(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataSumberDanaKredit entContentDataSumberDanaKredit) throws DBException {
        try {
            PstContentDataSumberDanaKredit pstContentDataSumberDanaKredit = new PstContentDataSumberDanaKredit(0);
            pstContentDataSumberDanaKredit.setString(FLD_NAMA_SUMBER_DANA_KREDIT, entContentDataSumberDanaKredit.getSumberDanaKredit());
            pstContentDataSumberDanaKredit.setString(FLD_KODE_CORE_BANKING, entContentDataSumberDanaKredit.getKodeCoreBanking());
            pstContentDataSumberDanaKredit.setString(FLD_KODE_OJK, entContentDataSumberDanaKredit.getKodeOjk());
            pstContentDataSumberDanaKredit.insert();
            entContentDataSumberDanaKredit.setOID(pstContentDataSumberDanaKredit.getlong(FLD_SUMBER_DANA_KREDIT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSumberDanaKredit(0), DBException.UNKNOWN);
        }
        return entContentDataSumberDanaKredit.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataSumberDanaKredit) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataSumberDanaKredit entContentDataSumberDanaKredit) {
        try {
            entContentDataSumberDanaKredit.setOID(rs.getLong(PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_SUMBER_DANA_KREDIT_OID]));
            entContentDataSumberDanaKredit.setSumberDanaKredit(rs.getString(PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_NAMA_SUMBER_DANA_KREDIT]));
            entContentDataSumberDanaKredit.setKodeCoreBanking(rs.getString(PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_KODE_CORE_BANKING]));
            entContentDataSumberDanaKredit.setKodeOjk(rs.getString(PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWitoutOid(ResultSet rs, ContentDataSumberDanaKredit entContentDataSumberDanaKredit) {
        try {
            entContentDataSumberDanaKredit.setSumberDanaKredit(rs.getString(PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_NAMA_SUMBER_DANA_KREDIT]));
            entContentDataSumberDanaKredit.setKodeOjk(rs.getString(PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SUMBER_DANA_KREDIT;
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
                ContentDataSumberDanaKredit entContentDataSumberDanaKredit = new ContentDataSumberDanaKredit();
                resultToObject(rs, entContentDataSumberDanaKredit);
                lists.add(entContentDataSumberDanaKredit);
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
                    + fieldNames[FLD_NAMA_SUMBER_DANA_KREDIT]+" "
                    + "FROM " + TBL_CONTENT_DATA_SUMBER_DANA_KREDIT;
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
                ContentDataSumberDanaKredit entContentDataSumberDanaKredit = new ContentDataSumberDanaKredit();
                resultToObjectWitoutOid(rs, entContentDataSumberDanaKredit);
                lists.add(entContentDataSumberDanaKredit);
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

    public static boolean checkOID(long entContentDataSumberDanaKreditId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SUMBER_DANA_KREDIT+ " WHERE "
                    + PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_SUMBER_DANA_KREDIT_OID] + " = " + entContentDataSumberDanaKreditId;
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
            String sql = "SELECT COUNT(" + PstContentDataSumberDanaKredit.fieldNames[PstContentDataSumberDanaKredit.FLD_SUMBER_DANA_KREDIT_OID] + ") FROM " + TBL_CONTENT_DATA_SUMBER_DANA_KREDIT;
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
                    ContentDataSumberDanaKredit entContentDataSumberDanaKredit = (ContentDataSumberDanaKredit) list.get(ls);
                    if (oid == entContentDataSumberDanaKredit.getOID()) {
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
