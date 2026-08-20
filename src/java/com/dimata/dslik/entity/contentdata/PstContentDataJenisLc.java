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

public class PstContentDataJenisLc extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_JENIS_LC = "dslik_cd_jenis_lc";
    public static final int FLD_JENIS_LC_OID = 0;
    public static final int FLD_NAMA_LC = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "JENIS_LC_OID",
        "NAMA_LC",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataJenisLc() {
    }

    public PstContentDataJenisLc(int i) throws DBException {
        super(new PstContentDataJenisLc());
    }

    public PstContentDataJenisLc(String sOid) throws DBException {
        super(new PstContentDataJenisLc(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataJenisLc(long lOid) throws DBException {
        super(new PstContentDataJenisLc(0));
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
        return TBL_CONTENT_DATA_JENIS_LC;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataJenisLc().getClass().getName();
    }

    public static ContentDataJenisLc fetchExc(long oid) throws DBException {
        try {
            ContentDataJenisLc entContentDataJenisLc = new ContentDataJenisLc();
            PstContentDataJenisLc pstContentDataJenisLc = new PstContentDataJenisLc(oid);
            entContentDataJenisLc.setOID(oid);
            entContentDataJenisLc.setNamaLc(pstContentDataJenisLc.getString(FLD_NAMA_LC));
            entContentDataJenisLc.setKodeCoreBanking(pstContentDataJenisLc.getString(FLD_KODE_CORE_BANKING));
            entContentDataJenisLc.setKodeOjk(pstContentDataJenisLc.getString(FLD_KODE_OJK));
            return entContentDataJenisLc;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisLc(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataJenisLc entContentDataJenisLc = fetchExc(entity.getOID());
        entity = (Entity) entContentDataJenisLc;
        return entContentDataJenisLc.getOID();
    }

    public static synchronized long updateExc(ContentDataJenisLc entContentDataJenisLc) throws DBException {
        try {
            if (entContentDataJenisLc.getOID() != 0) {
                PstContentDataJenisLc pstContentDataJenisLc = new PstContentDataJenisLc(entContentDataJenisLc.getOID());
                pstContentDataJenisLc.setString(FLD_NAMA_LC, entContentDataJenisLc.getNamaLc());
                pstContentDataJenisLc.setString(FLD_KODE_CORE_BANKING, entContentDataJenisLc.getKodeCoreBanking());
                pstContentDataJenisLc.setString(FLD_KODE_OJK, entContentDataJenisLc.getKodeOjk());
                pstContentDataJenisLc.update();
                return entContentDataJenisLc.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisLc(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataJenisLc) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataJenisLc pstContentDataJenisLc = new PstContentDataJenisLc(oid);
            pstContentDataJenisLc.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisLc(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataJenisLc entContentDataJenisLc) throws DBException {
        try {
            PstContentDataJenisLc pstContentDataJenisLc = new PstContentDataJenisLc(0);
            pstContentDataJenisLc.setString(FLD_NAMA_LC, entContentDataJenisLc.getNamaLc());
            pstContentDataJenisLc.setString(FLD_KODE_CORE_BANKING, entContentDataJenisLc.getKodeCoreBanking());
            pstContentDataJenisLc.setString(FLD_KODE_OJK, entContentDataJenisLc.getKodeOjk());
            pstContentDataJenisLc.insert();
            entContentDataJenisLc.setOID(pstContentDataJenisLc.getlong(FLD_JENIS_LC_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataJenisLc(0), DBException.UNKNOWN);
        }
        return entContentDataJenisLc.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataJenisLc) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataJenisLc entContentDataJenisLc) {
        try {
            entContentDataJenisLc.setOID(rs.getLong(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_JENIS_LC_OID]));
            entContentDataJenisLc.setNamaLc(rs.getString(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]));
            entContentDataJenisLc.setKodeCoreBanking(rs.getString(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_KODE_CORE_BANKING]));
            entContentDataJenisLc.setKodeOjk(rs.getString(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataJenisLc entContentDataJenisLc) {
        try {
            entContentDataJenisLc.setNamaLc(rs.getString(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_NAMA_LC]));
            entContentDataJenisLc.setKodeOjk(rs.getString(PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_LC;
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
                ContentDataJenisLc entContentDataJenisLc = new ContentDataJenisLc();
                resultToObject(rs, entContentDataJenisLc);
                lists.add(entContentDataJenisLc);
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
                    + fieldNames[FLD_NAMA_LC]+" "
                    + "FROM " + TBL_CONTENT_DATA_JENIS_LC;
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
                ContentDataJenisLc entContentDataJenisLc = new ContentDataJenisLc();
                resultToObjectWithoutOid(rs, entContentDataJenisLc);
                lists.add(entContentDataJenisLc);
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

    public static boolean checkOID(long entContentDataJenisLcId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_JENIS_LC + " WHERE "
                    + PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_JENIS_LC_OID] + " = " + entContentDataJenisLcId;
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
            String sql = "SELECT COUNT(" + PstContentDataJenisLc.fieldNames[PstContentDataJenisLc.FLD_JENIS_LC_OID] + ") FROM " + TBL_CONTENT_DATA_JENIS_LC;
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
                    ContentDataJenisLc entContentDataJenisLc = (ContentDataJenisLc) list.get(ls);
                    if (oid == entContentDataJenisLc.getOID()) {
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
