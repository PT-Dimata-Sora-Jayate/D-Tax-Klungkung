/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.contentdata;

/**
 *
 * @author dimata005
 */
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

public class PstContentDataKecamatan extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENTDATAKECAMATAN = "dslik_cd_kecamatan";
    public static final int FLD_KECAMATANID = 0;
    public static final int FLD_KODEKABUPATENKOTA = 1;
    public static final int FLD_NAMAKECAMATAN = 2;
    public static final int FLD_KODECOREBANKING = 3;
    public static final int FLD_KODEOJK = 4;

    public static String[] fieldNames = {
        "KECAMATAN_OID",
        "KODE_DATI_II",
        "NAMA_KECAMATAN",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataKecamatan() {
    }

    public PstContentDataKecamatan(int i) throws DBException {
        super(new PstContentDataKecamatan());
    }

    public PstContentDataKecamatan(String sOid) throws DBException {
        super(new PstContentDataKecamatan(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataKecamatan(long lOid) throws DBException {
        super(new PstContentDataKecamatan(0));
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
        return TBL_CONTENTDATAKECAMATAN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataKecamatan().getClass().getName();
    }

    public static ContentDataKecamatan fetchExc(long oid) throws DBException {
        try {
            ContentDataKecamatan entContentDataKecamatan = new ContentDataKecamatan();
            PstContentDataKecamatan pstContentDataKecamatan = new PstContentDataKecamatan(oid);
            entContentDataKecamatan.setOID(oid);
            entContentDataKecamatan.setKodeKabupatenKota(pstContentDataKecamatan.getString(FLD_KODEKABUPATENKOTA));
            entContentDataKecamatan.setNamaKecamatan(pstContentDataKecamatan.getString(FLD_NAMAKECAMATAN));
            entContentDataKecamatan.setKodeCoreBanking(pstContentDataKecamatan.getString(FLD_KODECOREBANKING));
            entContentDataKecamatan.setKodeOjk(pstContentDataKecamatan.getString(FLD_KODEOJK));
            return entContentDataKecamatan;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKecamatan(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataKecamatan entContentDataKecamatan = fetchExc(entity.getOID());
        entity = (Entity) entContentDataKecamatan;
        return entContentDataKecamatan.getOID();
    }

    public static synchronized long updateExc(ContentDataKecamatan entContentDataKecamatan) throws DBException {
        try {
            if (entContentDataKecamatan.getOID() != 0) {
                PstContentDataKecamatan pstContentDataKecamatan = new PstContentDataKecamatan(entContentDataKecamatan.getOID());
                pstContentDataKecamatan.setString(FLD_KODEKABUPATENKOTA, entContentDataKecamatan.getKodeKabupatenKota());
                pstContentDataKecamatan.setString(FLD_NAMAKECAMATAN, entContentDataKecamatan.getNamaKecamatan());
                pstContentDataKecamatan.setString(FLD_KODECOREBANKING, entContentDataKecamatan.getKodeCoreBanking());
                pstContentDataKecamatan.setString(FLD_KODEOJK, entContentDataKecamatan.getKodeOjk());
                pstContentDataKecamatan.update();
                return entContentDataKecamatan.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKecamatan(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataKecamatan) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataKecamatan pstContentDataKecamatan = new PstContentDataKecamatan(oid);
            pstContentDataKecamatan.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKecamatan(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataKecamatan entContentDataKecamatan) throws DBException {
        try {
            PstContentDataKecamatan pstContentDataKecamatan = new PstContentDataKecamatan(0);
            pstContentDataKecamatan.setString(FLD_KODEKABUPATENKOTA, entContentDataKecamatan.getKodeKabupatenKota());
            pstContentDataKecamatan.setString(FLD_NAMAKECAMATAN, entContentDataKecamatan.getNamaKecamatan());
            pstContentDataKecamatan.setString(FLD_KODECOREBANKING, entContentDataKecamatan.getKodeCoreBanking());
            pstContentDataKecamatan.setString(FLD_KODEOJK, entContentDataKecamatan.getKodeOjk());
            pstContentDataKecamatan.insert();
            entContentDataKecamatan.setOID(pstContentDataKecamatan.getLong(FLD_KECAMATANID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataKecamatan(0), DBException.UNKNOWN);
        }
        return entContentDataKecamatan.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataKecamatan) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataKecamatan entContentDataKecamatan) {
        try {
            entContentDataKecamatan.setOID(rs.getLong(PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_KECAMATANID]));
            entContentDataKecamatan.setKodeKabupatenKota(rs.getString(PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_KODEKABUPATENKOTA]));
            entContentDataKecamatan.setNamaKecamatan(rs.getString(PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_NAMAKECAMATAN]));
            entContentDataKecamatan.setKodeCoreBanking(rs.getString(PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_KODECOREBANKING]));
            entContentDataKecamatan.setKodeOjk(rs.getString(PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_KODEOJK]));
            entContentDataKecamatan.setNamaKabupatenKota(rs.getString("NAMA_KABUPATEN"));
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
            String sql = "SELECT * FROM " + TBL_CONTENTDATAKECAMATAN;
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
                ContentDataKecamatan entContentDataKecamatan = new ContentDataKecamatan();
                resultToObject(rs, entContentDataKecamatan);
                lists.add(entContentDataKecamatan);
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
    
    
    public static Vector listJoin(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
            String sql = "SELECT kc.*, kb."+PstContentDataKabupatenKota.fieldNames[PstContentDataKabupatenKota.FLD_NAMA_KABUPATEN_KOTA]+" AS NAMA_KABUPATEN FROM " + TBL_CONTENTDATAKECAMATAN +" AS kc INNER JOIN "+PstContentDataKabupatenKota.TBL_CONTENT_DATA_KABUPATEN_KOTA+" AS kb "+
                         " ON kc."+fieldNames[FLD_KODEKABUPATENKOTA]+"=kb."+PstContentDataKabupatenKota.fieldNames[PstContentDataKabupatenKota.FLD_KODE_CORE_BANKING];
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
                ContentDataKecamatan entContentDataKecamatan = new ContentDataKecamatan();
                resultToObject(rs, entContentDataKecamatan);
                lists.add(entContentDataKecamatan);
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

    public static boolean checkOID(long entContentDataKecamatanId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENTDATAKECAMATAN + " WHERE "
                    + PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_KECAMATANID] + " = " + entContentDataKecamatanId;
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
            String sql = "SELECT COUNT(" + PstContentDataKecamatan.fieldNames[PstContentDataKecamatan.FLD_KECAMATANID] + ") FROM " + TBL_CONTENTDATAKECAMATAN;
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
                    ContentDataKecamatan entContentDataKecamatan = (ContentDataKecamatan) list.get(ls);
                    if (oid == entContentDataKecamatan.getOID()) {
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
        } else if (start == (vectSize - recordToGet)) {
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
        return cmd;
    }
}
