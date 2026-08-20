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
public class PstContentDataNomorIdentitas extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_NOMOR_IDENTITAS = "dslik_cd_nomor_identitas";
    public static final int FLD_NOMOR_IDENTITAS_OID = 0;
    public static final int FLD_NOMOR_IDENTITAS = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;

    public static String[] fieldNames = {
        "NOMOR_IDENTITAS_OID",
        "NOMOR_IDENTITAS",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataNomorIdentitas() {
    }

    public PstContentDataNomorIdentitas(int i) throws DBException {
        super(new PstContentDataNomorIdentitas());
    }

    public PstContentDataNomorIdentitas(String sOid) throws DBException {
        super(new PstContentDataNomorIdentitas(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataNomorIdentitas(long lOid) throws DBException {
        super(new PstContentDataNomorIdentitas(0));
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
        return TBL_CONTENT_DATA_NOMOR_IDENTITAS;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataNomorIdentitas().getClass().getName();
    }

    public static ContentDataNomorIdentitas fetchExc(long oid) throws DBException {
        try {
            ContentDataNomorIdentitas entContentDataNomorIdentitas = new ContentDataNomorIdentitas();
            PstContentDataNomorIdentitas pstContentDataNomorIdentitas = new PstContentDataNomorIdentitas(oid);
            entContentDataNomorIdentitas.setOID(oid);
            entContentDataNomorIdentitas.setNomorIdentitas(pstContentDataNomorIdentitas.getString(FLD_NOMOR_IDENTITAS));
            entContentDataNomorIdentitas.setKodeCoreBanking(pstContentDataNomorIdentitas.getString(FLD_KODE_CORE_BANKING));
            entContentDataNomorIdentitas.setKodeOjk(pstContentDataNomorIdentitas.getString(FLD_KODE_OJK));
            return entContentDataNomorIdentitas;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNomorIdentitas(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataNomorIdentitas entContentDataNomorIdentitas = fetchExc(entity.getOID());
        entity = (Entity) entContentDataNomorIdentitas;
        return entContentDataNomorIdentitas.getOID();
    }

    public static synchronized long updateExc(ContentDataNomorIdentitas entContentDataNomorIdentitas) throws DBException {
        try {
            if (entContentDataNomorIdentitas.getOID() != 0) {
                PstContentDataNomorIdentitas pstContentDataNomorIdentitas = new PstContentDataNomorIdentitas(entContentDataNomorIdentitas.getOID());
                pstContentDataNomorIdentitas.setString(FLD_NOMOR_IDENTITAS, entContentDataNomorIdentitas.getNomorIdentitas());
                pstContentDataNomorIdentitas.setString(FLD_KODE_CORE_BANKING, entContentDataNomorIdentitas.getKodeCoreBanking());
                pstContentDataNomorIdentitas.setString(FLD_KODE_OJK, entContentDataNomorIdentitas.getKodeOjk());
                pstContentDataNomorIdentitas.update();
                return entContentDataNomorIdentitas.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNomorIdentitas(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataNomorIdentitas) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataNomorIdentitas pstContentDataNomorIdentitas = new PstContentDataNomorIdentitas(oid);
            pstContentDataNomorIdentitas.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNomorIdentitas(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataNomorIdentitas entContentDataNomorIdentitas) throws DBException {
        try {
            PstContentDataNomorIdentitas pstContentDataNomorIdentitas = new PstContentDataNomorIdentitas(0);
            pstContentDataNomorIdentitas.setString(FLD_NOMOR_IDENTITAS, entContentDataNomorIdentitas.getNomorIdentitas());
            pstContentDataNomorIdentitas.setString(FLD_KODE_CORE_BANKING, entContentDataNomorIdentitas.getKodeCoreBanking());
            pstContentDataNomorIdentitas.setString(FLD_KODE_OJK, entContentDataNomorIdentitas.getKodeOjk());
            pstContentDataNomorIdentitas.insert();
            entContentDataNomorIdentitas.setOID(pstContentDataNomorIdentitas.getlong(FLD_NOMOR_IDENTITAS_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNomorIdentitas(0), DBException.UNKNOWN);
        }
        return entContentDataNomorIdentitas.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataNomorIdentitas) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataNomorIdentitas entContentDataNomorIdentitas) {
        try {
            entContentDataNomorIdentitas.setOID(rs.getLong(PstContentDataNomorIdentitas.fieldNames[PstContentDataNomorIdentitas.FLD_NOMOR_IDENTITAS_OID]));
            entContentDataNomorIdentitas.setNomorIdentitas(rs.getString(PstContentDataNomorIdentitas.fieldNames[PstContentDataNomorIdentitas.FLD_NOMOR_IDENTITAS]));
            entContentDataNomorIdentitas.setKodeCoreBanking(rs.getString(PstContentDataNomorIdentitas.fieldNames[PstContentDataNomorIdentitas.FLD_KODE_CORE_BANKING]));
            entContentDataNomorIdentitas.setKodeOjk(rs.getString(PstContentDataNomorIdentitas.fieldNames[PstContentDataNomorIdentitas.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_NOMOR_IDENTITAS;
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
                ContentDataNomorIdentitas entContentDataNomorIdentitas = new ContentDataNomorIdentitas();
                resultToObject(rs, entContentDataNomorIdentitas);
                lists.add(entContentDataNomorIdentitas);
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

    public static boolean checkOID(long entContentDataNomorIdentitasId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_NOMOR_IDENTITAS + " WHERE "
                    + PstContentDataNomorIdentitas.fieldNames[PstContentDataNomorIdentitas.FLD_NOMOR_IDENTITAS_OID] + " = " + entContentDataNomorIdentitasId;
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
            String sql = "SELECT COUNT(" + PstContentDataNomorIdentitas.fieldNames[PstContentDataNomorIdentitas.FLD_NOMOR_IDENTITAS_OID] + ") FROM " + TBL_CONTENT_DATA_NOMOR_IDENTITAS;
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
                    ContentDataNomorIdentitas entContentDataNomorIdentitas = (ContentDataNomorIdentitas) list.get(ls);
                    if (oid == entContentDataNomorIdentitas.getOID()) {
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
