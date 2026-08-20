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
public class PstContentDataLembagaPemeringkat extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_LEMBAGA_PEMERINGKAT = "dslik_cd_lembaga_pemeringkat";
    public static final int FLD_LEMBAGA_PEMERINGKAT_OID = 0;
    public static final int FLD_NAMA_LEMBAGA_PEMERINGKAT = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "LEMBAGA_PEMERINGKAT_OID",
        "NAMA_LEMBAGA_PEMERINGKAT",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataLembagaPemeringkat() {
    }

    public PstContentDataLembagaPemeringkat(int i) throws DBException {
        super(new PstContentDataLembagaPemeringkat());
    }

    public PstContentDataLembagaPemeringkat(String sOid) throws DBException {
        super(new PstContentDataLembagaPemeringkat(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataLembagaPemeringkat(long lOid) throws DBException {
        super(new PstContentDataLembagaPemeringkat(0));
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
        return TBL_CONTENT_DATA_LEMBAGA_PEMERINGKAT;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataLembagaPemeringkat().getClass().getName();
    }

    public static ContentDataLembagaPemeringkat fetchExc(long oid) throws DBException {
        try {
            ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat = new ContentDataLembagaPemeringkat();
            PstContentDataLembagaPemeringkat pstContentDataLembagaPemeringkat = new PstContentDataLembagaPemeringkat(oid);
            entContentDataLembagaPemeringkat.setOID(oid);
            entContentDataLembagaPemeringkat.setNamaLembagaPemeringkat(pstContentDataLembagaPemeringkat.getString(FLD_NAMA_LEMBAGA_PEMERINGKAT));
            entContentDataLembagaPemeringkat.setKodeCoreBanking(pstContentDataLembagaPemeringkat.getString(FLD_KODE_CORE_BANKING));
            entContentDataLembagaPemeringkat.setKodeOjk(pstContentDataLembagaPemeringkat.getString(FLD_KODE_OJK));
            return entContentDataLembagaPemeringkat;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataLembagaPemeringkat(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat = fetchExc(entity.getOID());
        entity = (Entity) entContentDataLembagaPemeringkat;
        return entContentDataLembagaPemeringkat.getOID();
    }

    public static synchronized long updateExc(ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) throws DBException {
        try {
            if (entContentDataLembagaPemeringkat.getOID() != 0) {
                PstContentDataLembagaPemeringkat pstContentDataLembagaPemeringkat = new PstContentDataLembagaPemeringkat(entContentDataLembagaPemeringkat.getOID());
                pstContentDataLembagaPemeringkat.setString(FLD_NAMA_LEMBAGA_PEMERINGKAT, entContentDataLembagaPemeringkat.getNamaLembagaPemeringkat());
                pstContentDataLembagaPemeringkat.setString(FLD_KODE_CORE_BANKING, entContentDataLembagaPemeringkat.getKodeCoreBanking());
                pstContentDataLembagaPemeringkat.setString(FLD_KODE_OJK, entContentDataLembagaPemeringkat.getKodeOjk());
                pstContentDataLembagaPemeringkat.update();
                return entContentDataLembagaPemeringkat.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataLembagaPemeringkat(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataLembagaPemeringkat) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataLembagaPemeringkat pstContentDataLembagaPemeringkat = new PstContentDataLembagaPemeringkat(oid);
            pstContentDataLembagaPemeringkat.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataLembagaPemeringkat(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) throws DBException {
        try {
            PstContentDataLembagaPemeringkat pstContentDataLembagaPemeringkat = new PstContentDataLembagaPemeringkat(0);
            pstContentDataLembagaPemeringkat.setString(FLD_NAMA_LEMBAGA_PEMERINGKAT, entContentDataLembagaPemeringkat.getNamaLembagaPemeringkat());
            pstContentDataLembagaPemeringkat.setString(FLD_KODE_CORE_BANKING, entContentDataLembagaPemeringkat.getKodeCoreBanking());
            pstContentDataLembagaPemeringkat.setString(FLD_KODE_OJK, entContentDataLembagaPemeringkat.getKodeOjk());
            pstContentDataLembagaPemeringkat.insert();
            entContentDataLembagaPemeringkat.setOID(pstContentDataLembagaPemeringkat.getlong(FLD_LEMBAGA_PEMERINGKAT_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataLembagaPemeringkat(0), DBException.UNKNOWN);
        }
        return entContentDataLembagaPemeringkat.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataLembagaPemeringkat) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) {
        try {
            entContentDataLembagaPemeringkat.setOID(rs.getLong(PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_LEMBAGA_PEMERINGKAT_OID]));
            entContentDataLembagaPemeringkat.setNamaLembagaPemeringkat(rs.getString(PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_NAMA_LEMBAGA_PEMERINGKAT]));
            entContentDataLembagaPemeringkat.setKodeCoreBanking(rs.getString(PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_KODE_CORE_BANKING]));
            entContentDataLembagaPemeringkat.setKodeOjk(rs.getString(PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat) {
        try {
            entContentDataLembagaPemeringkat.setNamaLembagaPemeringkat(rs.getString(PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_NAMA_LEMBAGA_PEMERINGKAT]));
            entContentDataLembagaPemeringkat.setKodeOjk(rs.getString(PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_LEMBAGA_PEMERINGKAT;
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
                ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat = new ContentDataLembagaPemeringkat();
                resultToObject(rs, entContentDataLembagaPemeringkat);
                lists.add(entContentDataLembagaPemeringkat);
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
                    + fieldNames[FLD_NAMA_LEMBAGA_PEMERINGKAT]+" FROM " + TBL_CONTENT_DATA_LEMBAGA_PEMERINGKAT;
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
                ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat = new ContentDataLembagaPemeringkat();
                resultToObjectWithoutOid(rs, entContentDataLembagaPemeringkat);
                lists.add(entContentDataLembagaPemeringkat);
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

    public static boolean checkOID(long entContentDataLembagaPemeringkatId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_LEMBAGA_PEMERINGKAT + " WHERE "
                    + PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_LEMBAGA_PEMERINGKAT_OID] + " = " + entContentDataLembagaPemeringkatId;
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
            String sql = "SELECT COUNT(" + PstContentDataLembagaPemeringkat.fieldNames[PstContentDataLembagaPemeringkat.FLD_LEMBAGA_PEMERINGKAT_OID] + ") FROM " + TBL_CONTENT_DATA_LEMBAGA_PEMERINGKAT;
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
                    ContentDataLembagaPemeringkat entContentDataLembagaPemeringkat = (ContentDataLembagaPemeringkat) list.get(ls);
                    if (oid == entContentDataLembagaPemeringkat.getOID()) {
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
