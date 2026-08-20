/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

/**
 *
 * @author m20n9
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstContentDataCaraRestrukturisasi extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_CARA_RESTRUKTURISASI = "dslik_cd_cara_restrukturisasi";
    public static final int FLD_RESTRUKTURISASI_OID = 0;
    public static final int FLD_CARA_RESTRUKTURISASI = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "RESTRUKTURISASI_OID",
        "CARA_RESTRUKTURISASI",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataCaraRestrukturisasi() {
    }

    public PstContentDataCaraRestrukturisasi(int i) throws DBException {
        super(new PstContentDataCaraRestrukturisasi());
    }

    public PstContentDataCaraRestrukturisasi(String sOid) throws DBException {
        super(new PstContentDataCaraRestrukturisasi(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataCaraRestrukturisasi(long lOid) throws DBException {
        super(new PstContentDataCaraRestrukturisasi(0));
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
        return TBL_CONTENT_CARA_RESTRUKTURISASI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataCaraRestrukturisasi().getClass().getName();
    }

    public static ContentDataCaraRestrukturisasi fetchExc(long oid) throws DBException {
        try {
            ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi = new ContentDataCaraRestrukturisasi();
            PstContentDataCaraRestrukturisasi pstContentDataCaraRestrukturisasi = new PstContentDataCaraRestrukturisasi(oid);
            entContentDataCaraRestrukturisasi.setOID(oid);
            entContentDataCaraRestrukturisasi.setCaraRestrukturisasi(pstContentDataCaraRestrukturisasi.getString(FLD_CARA_RESTRUKTURISASI));
            entContentDataCaraRestrukturisasi.setKodeCoreBanking(pstContentDataCaraRestrukturisasi.getString(FLD_KODE_CORE_BANKING));
            entContentDataCaraRestrukturisasi.setKodeOjk(pstContentDataCaraRestrukturisasi.getString(FLD_KODE_OJK));
            return entContentDataCaraRestrukturisasi;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataCaraRestrukturisasi(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi = fetchExc(entity.getOID());
        entity = (Entity) entContentDataCaraRestrukturisasi;
        return entContentDataCaraRestrukturisasi.getOID();
    }

    public static synchronized long updateExc(ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi) throws DBException {
        try {
            if (entContentDataCaraRestrukturisasi.getOID() != 0) {
                PstContentDataCaraRestrukturisasi pstContentDataCaraRestrukturisasi = new PstContentDataCaraRestrukturisasi(entContentDataCaraRestrukturisasi.getOID());
                pstContentDataCaraRestrukturisasi.setString(FLD_CARA_RESTRUKTURISASI, entContentDataCaraRestrukturisasi.getCaraRestrukturisasi());
                pstContentDataCaraRestrukturisasi.setString(FLD_KODE_CORE_BANKING, entContentDataCaraRestrukturisasi.getKodeCoreBanking());
                pstContentDataCaraRestrukturisasi.setString(FLD_KODE_OJK, entContentDataCaraRestrukturisasi.getKodeOjk());
                pstContentDataCaraRestrukturisasi.update();
                return entContentDataCaraRestrukturisasi.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataCaraRestrukturisasi(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataCaraRestrukturisasi) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataCaraRestrukturisasi pstContentDataCaraRestrukturisasi = new PstContentDataCaraRestrukturisasi(oid);
            pstContentDataCaraRestrukturisasi.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataCaraRestrukturisasi(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi) throws DBException {
        try {
            PstContentDataCaraRestrukturisasi pstContentDataCaraRestrukturisasi = new PstContentDataCaraRestrukturisasi(0);
            pstContentDataCaraRestrukturisasi.setString(FLD_CARA_RESTRUKTURISASI, entContentDataCaraRestrukturisasi.getCaraRestrukturisasi());
            pstContentDataCaraRestrukturisasi.setString(FLD_KODE_CORE_BANKING, entContentDataCaraRestrukturisasi.getKodeCoreBanking());
            pstContentDataCaraRestrukturisasi.setString(FLD_KODE_OJK, entContentDataCaraRestrukturisasi.getKodeOjk());
            pstContentDataCaraRestrukturisasi.insert();
            entContentDataCaraRestrukturisasi.setOID(pstContentDataCaraRestrukturisasi.getlong(FLD_RESTRUKTURISASI_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataCaraRestrukturisasi(0), DBException.UNKNOWN);
        }
        return entContentDataCaraRestrukturisasi.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataCaraRestrukturisasi) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi) {
        try {
            entContentDataCaraRestrukturisasi.setOID(rs.getLong(PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_RESTRUKTURISASI_OID]));
            entContentDataCaraRestrukturisasi.setCaraRestrukturisasi(rs.getString(PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_CARA_RESTRUKTURISASI]));
            entContentDataCaraRestrukturisasi.setKodeCoreBanking(rs.getString(PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_KODE_CORE_BANKING]));
            entContentDataCaraRestrukturisasi.setKodeOjk(rs.getString(PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi) {
        try {
            entContentDataCaraRestrukturisasi.setCaraRestrukturisasi(rs.getString(PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_CARA_RESTRUKTURISASI]));
            entContentDataCaraRestrukturisasi.setKodeOjk(rs.getString(PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_CARA_RESTRUKTURISASI;
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
                ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi = new ContentDataCaraRestrukturisasi();
                resultToObject(rs, entContentDataCaraRestrukturisasi);
                lists.add(entContentDataCaraRestrukturisasi);
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
    
    public static Vector listWitoutOid(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT DISTINCT "+fieldNames[FLD_CARA_RESTRUKTURISASI]+","
                    + fieldNames[FLD_KODE_OJK]+" "
                    + "FROM " + TBL_CONTENT_CARA_RESTRUKTURISASI;
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
                ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi = new ContentDataCaraRestrukturisasi();
                resultToObjectWithoutOid(rs, entContentDataCaraRestrukturisasi);
                lists.add(entContentDataCaraRestrukturisasi);
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

    public static boolean checkOID(long entContentDataCaraRestrukturisasiId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_CARA_RESTRUKTURISASI + " WHERE "
                    + PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_RESTRUKTURISASI_OID] + " = " + entContentDataCaraRestrukturisasiId;
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
            String sql = "SELECT COUNT(" + PstContentDataCaraRestrukturisasi.fieldNames[PstContentDataCaraRestrukturisasi.FLD_RESTRUKTURISASI_OID] + ") FROM " + TBL_CONTENT_CARA_RESTRUKTURISASI;
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
                    ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi = (ContentDataCaraRestrukturisasi) list.get(ls);
                    if (oid == entContentDataCaraRestrukturisasi.getOID()) {
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
