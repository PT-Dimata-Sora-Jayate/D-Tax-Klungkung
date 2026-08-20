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
public class PstContentDataSebabMacet extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_SEBAB_MACET = "dslik_cd_sebab_macet";
    public static final int FLD_SEBAB_MACET_OID = 0;
    public static final int FLD_SEBAB_MACET = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "SEBAB_MACET_OID",
        "SEBAB_MACET",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataSebabMacet() {
    }

    public PstContentDataSebabMacet(int i) throws DBException {
        super(new PstContentDataSebabMacet());
    }

    public PstContentDataSebabMacet(String sOid) throws DBException {
        super(new PstContentDataSebabMacet(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataSebabMacet(long lOid) throws DBException {
        super(new PstContentDataSebabMacet(0));
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
        return TBL_CONTENT_DATA_SEBAB_MACET;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataSebabMacet().getClass().getName();
    }

    public static ContentDataSebabMacet fetchExc(long oid) throws DBException {
        try {
            ContentDataSebabMacet entContentDataSebabMacet = new ContentDataSebabMacet();
            PstContentDataSebabMacet pstContentDataSebabMacet = new PstContentDataSebabMacet(oid);
            entContentDataSebabMacet.setOID(oid);
            entContentDataSebabMacet.setSebabMacet(pstContentDataSebabMacet.getString(FLD_SEBAB_MACET));
            entContentDataSebabMacet.setKodeCoreBanking(pstContentDataSebabMacet.getString(FLD_KODE_CORE_BANKING));
            entContentDataSebabMacet.setKodeOjk(pstContentDataSebabMacet.getString(FLD_KODE_OJK));
            return entContentDataSebabMacet;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSebabMacet(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataSebabMacet entContentDataSebabMacet = fetchExc(entity.getOID());
        entity = (Entity) entContentDataSebabMacet;
        return entContentDataSebabMacet.getOID();
    }

    public static synchronized long updateExc(ContentDataSebabMacet entContentDataSebabMacet) throws DBException {
        try {
            if (entContentDataSebabMacet.getOID() != 0) {
                PstContentDataSebabMacet pstContentDataSebabMacet = new PstContentDataSebabMacet(entContentDataSebabMacet.getOID());
                pstContentDataSebabMacet.setString(FLD_SEBAB_MACET, entContentDataSebabMacet.getSebabMacet());
                pstContentDataSebabMacet.setString(FLD_KODE_CORE_BANKING, entContentDataSebabMacet.getKodeCoreBanking());
                pstContentDataSebabMacet.setString(FLD_KODE_OJK, entContentDataSebabMacet.getKodeOjk());
                pstContentDataSebabMacet.update();
                return entContentDataSebabMacet.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSebabMacet(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataSebabMacet) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataSebabMacet pstContentDataSebabMacet = new PstContentDataSebabMacet(oid);
            pstContentDataSebabMacet.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSebabMacet(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataSebabMacet entContentDataSebabMacet) throws DBException {
        try {
            PstContentDataSebabMacet pstContentDataSebabMacet = new PstContentDataSebabMacet(0);
            pstContentDataSebabMacet.setString(FLD_SEBAB_MACET, entContentDataSebabMacet.getSebabMacet());
            pstContentDataSebabMacet.setString(FLD_KODE_CORE_BANKING, entContentDataSebabMacet.getKodeCoreBanking());
            pstContentDataSebabMacet.setString(FLD_KODE_OJK, entContentDataSebabMacet.getKodeOjk());
            pstContentDataSebabMacet.insert();
            entContentDataSebabMacet.setOID(pstContentDataSebabMacet.getlong(FLD_SEBAB_MACET_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataSebabMacet(0), DBException.UNKNOWN);
        }
        return entContentDataSebabMacet.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataSebabMacet) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataSebabMacet entContentDataSebabMacet) {
        try {
            entContentDataSebabMacet.setOID(rs.getLong(PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_SEBAB_MACET_OID]));
            entContentDataSebabMacet.setSebabMacet(rs.getString(PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_SEBAB_MACET]));
            entContentDataSebabMacet.setKodeCoreBanking(rs.getString(PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_KODE_CORE_BANKING]));
            entContentDataSebabMacet.setKodeOjk(rs.getString(PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataSebabMacet entContentDataSebabMacet) {
        try {
            entContentDataSebabMacet.setSebabMacet(rs.getString(PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_SEBAB_MACET]));
            entContentDataSebabMacet.setKodeOjk(rs.getString(PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SEBAB_MACET;
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
                ContentDataSebabMacet entContentDataSebabMacet = new ContentDataSebabMacet();
                resultToObject(rs, entContentDataSebabMacet);
                lists.add(entContentDataSebabMacet);
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
                    + fieldNames[FLD_SEBAB_MACET]+" "
                    + "FROM " + TBL_CONTENT_DATA_SEBAB_MACET;
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
                ContentDataSebabMacet entContentDataSebabMacet = new ContentDataSebabMacet();
                resultToObjectWithoutOid(rs, entContentDataSebabMacet);
                lists.add(entContentDataSebabMacet);
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

    public static boolean checkOID(long entContentDataSebabMacetId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_SEBAB_MACET + " WHERE "
                    + PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_SEBAB_MACET_OID] + " = " + entContentDataSebabMacetId;
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
            String sql = "SELECT COUNT(" + PstContentDataSebabMacet.fieldNames[PstContentDataSebabMacet.FLD_SEBAB_MACET_OID] + ") FROM " + TBL_CONTENT_DATA_SEBAB_MACET;
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
                    ContentDataSebabMacet entContentDataSebabMacet = (ContentDataSebabMacet) list.get(ls);
                    if (oid == entContentDataSebabMacet.getOID()) {
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
