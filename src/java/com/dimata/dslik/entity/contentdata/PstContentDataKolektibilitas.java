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
public class PstContentDataKolektibilitas extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_KOLEKTIBILITAS = "dslik_cd_kolektibilitas";
    public static final int FLD_KOLEKTIBILITAS_OID = 0;
    public static final int FLD_NAMA_KOLEKTIBILITAS = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "KOLEKTIBILITAS_OID",
        "NAMA_KOLEKTIBILITAS",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataKolektibilitas() {
    }

    public PstContentDataKolektibilitas(int i) throws DBException {
        super(new PstContentDataKolektibilitas());
    }

    public PstContentDataKolektibilitas(String sOid) throws DBException {
        super(new PstContentDataKolektibilitas(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataKolektibilitas(long lOid) throws DBException {
        super(new PstContentDataKolektibilitas(0));
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
        return TBL_CONTENT_DATA_KOLEKTIBILITAS;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataKolektibilitas().getClass().getName();
    }

    public static ContentDataKolektibilitas fetchExc(long oid) throws DBException {
        try {
            ContentDataKolektibilitas entContentDataKolektibilitas = new ContentDataKolektibilitas();
            PstContentDataKolektibilitas pstContentDataKolektibilitas = new PstContentDataKolektibilitas(oid);
            entContentDataKolektibilitas.setOID(oid);
            entContentDataKolektibilitas.setNamaKolektibilitas(pstContentDataKolektibilitas.getString(FLD_NAMA_KOLEKTIBILITAS));
            entContentDataKolektibilitas.setKodeCoreBanking(pstContentDataKolektibilitas.getString(FLD_KODE_CORE_BANKING));
            entContentDataKolektibilitas.setKodeOjk(pstContentDataKolektibilitas.getString(FLD_KODE_OJK));
            return entContentDataKolektibilitas;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKolektibilitas(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataKolektibilitas entContentDataKolektibilitas = fetchExc(entity.getOID());
        entity = (Entity) entContentDataKolektibilitas;
        return entContentDataKolektibilitas.getOID();
    }

    public static synchronized long updateExc(ContentDataKolektibilitas entContentDataKolektibilitas) throws DBException {
        try {
            if (entContentDataKolektibilitas.getOID() != 0) {
                PstContentDataKolektibilitas pstContentDataKolektibilitas = new PstContentDataKolektibilitas(entContentDataKolektibilitas.getOID());
                pstContentDataKolektibilitas.setString(FLD_NAMA_KOLEKTIBILITAS, entContentDataKolektibilitas.getNamaKolektibilitas());
                pstContentDataKolektibilitas.setString(FLD_KODE_CORE_BANKING, entContentDataKolektibilitas.getKodeCoreBanking());
                pstContentDataKolektibilitas.setString(FLD_KODE_OJK, entContentDataKolektibilitas.getKodeOjk());
                pstContentDataKolektibilitas.update();
                return entContentDataKolektibilitas.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKolektibilitas(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataKolektibilitas) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataKolektibilitas pstContentDataKolektibilitas = new PstContentDataKolektibilitas(oid);
            pstContentDataKolektibilitas.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKolektibilitas(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataKolektibilitas entContentDataKolektibilitas) throws DBException {
        try {
            PstContentDataKolektibilitas pstContentDataKolektibilitas = new PstContentDataKolektibilitas(0);
            pstContentDataKolektibilitas.setString(FLD_NAMA_KOLEKTIBILITAS, entContentDataKolektibilitas.getNamaKolektibilitas());
            pstContentDataKolektibilitas.setString(FLD_KODE_CORE_BANKING, entContentDataKolektibilitas.getKodeCoreBanking());
            pstContentDataKolektibilitas.setString(FLD_KODE_OJK, entContentDataKolektibilitas.getKodeOjk());
            pstContentDataKolektibilitas.insert();
            entContentDataKolektibilitas.setOID(pstContentDataKolektibilitas.getlong(FLD_KOLEKTIBILITAS_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKolektibilitas(0), DBException.UNKNOWN);
        }
        return entContentDataKolektibilitas.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataKolektibilitas) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataKolektibilitas entContentDataKolektibilitas) {
        try {
            entContentDataKolektibilitas.setOID(rs.getLong(PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_KOLEKTIBILITAS_OID]));
            entContentDataKolektibilitas.setNamaKolektibilitas(rs.getString(PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_NAMA_KOLEKTIBILITAS]));
            entContentDataKolektibilitas.setKodeCoreBanking(rs.getString(PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_KODE_CORE_BANKING]));
            entContentDataKolektibilitas.setKodeOjk(rs.getString(PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_KODE_OJK]));
        } catch (Exception e) {
        }
    }
    
    public static void resultToObjectWithoutOid(ResultSet rs, ContentDataKolektibilitas entContentDataKolektibilitas) {
        try {
            entContentDataKolektibilitas.setNamaKolektibilitas(rs.getString(PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_NAMA_KOLEKTIBILITAS]));
            entContentDataKolektibilitas.setKodeOjk(rs.getString(PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KOLEKTIBILITAS;
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
                ContentDataKolektibilitas entContentDataKolektibilitas = new ContentDataKolektibilitas();
                resultToObject(rs, entContentDataKolektibilitas);
                lists.add(entContentDataKolektibilitas);
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
            String sql = "SELECT DISTINCT "+fieldNames[FLD_KODE_OJK]+","
                    + fieldNames[FLD_NAMA_KOLEKTIBILITAS]+" "
                    + "FROM " + TBL_CONTENT_DATA_KOLEKTIBILITAS;
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
                ContentDataKolektibilitas entContentDataKolektibilitas = new ContentDataKolektibilitas();
                resultToObjectWithoutOid(rs, entContentDataKolektibilitas);
                lists.add(entContentDataKolektibilitas);
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

    public static boolean checkOID(long entContentDataKolektibilitasId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_KOLEKTIBILITAS + " WHERE "
                    + PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_KOLEKTIBILITAS_OID] + " = " + entContentDataKolektibilitasId;
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
            String sql = "SELECT COUNT(" + PstContentDataKolektibilitas.fieldNames[PstContentDataKolektibilitas.FLD_KOLEKTIBILITAS_OID] + ") FROM " + TBL_CONTENT_DATA_KOLEKTIBILITAS;
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
                    ContentDataKolektibilitas entContentDataKolektibilitas = (ContentDataKolektibilitas) list.get(ls);
                    if (oid == entContentDataKolektibilitas.getOID()) {
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
