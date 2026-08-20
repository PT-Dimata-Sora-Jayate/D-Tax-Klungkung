/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import java.util.Vector;

/**
 *
 * @author Dimata 007
 */
public class PstMasterPenjamin extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MASTER_PENJAMIN = "dslik_master_penjamin";
    public static final int FLD_MASTER_PENJAMIN_OID = 0;
    public static final int FLD_NO_ID_PENJAMIN = 1;
    public static final int FLD_JENIS_IDENTITAS = 2;
    public static final int FLD_NAMA_IDENTITAS = 3;
    public static final int FLD_NAMA_LENGKAP = 4;
    public static final int FLD_KODE_GOL_PENJAMIN = 5;
    public static final int FLD_ALAMAT_PENJAMIN = 6;
    public static final int FLD_KETERANGAN = 7;

    public static String[] fieldNames = {
        "MASTER_PENJAMIN_OID",
        "NO_ID_PENJAMIN",
        "JENIS_IDENTITAS",
        "NAMA_IDENTITAS",
        "NAMA_LENGKAP",
        "KODE_GOL_PENJAMIN",
        "ALAMAT_PENJAMIN",
        "KETERANGAN"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstMasterPenjamin() {
    }

    public PstMasterPenjamin(int i) throws DBException {
        super(new PstMasterPenjamin());
    }

    public PstMasterPenjamin(String sOid) throws DBException {
        super(new PstMasterPenjamin(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMasterPenjamin(long lOid) throws DBException {
        super(new PstMasterPenjamin(0));
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
        return TBL_MASTER_PENJAMIN;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMasterPenjamin().getClass().getName();
    }

    public static MasterPenjamin fetchExc(long oid) throws DBException {
        try {
            MasterPenjamin entMasterPenjamin = new MasterPenjamin();
            PstMasterPenjamin pstMasterPenjamin = new PstMasterPenjamin(oid);
            entMasterPenjamin.setOID(oid);
            entMasterPenjamin.setNoIdPenjamin(pstMasterPenjamin.getString(FLD_NO_ID_PENJAMIN));
            entMasterPenjamin.setJenisIdentitas(pstMasterPenjamin.getString(FLD_JENIS_IDENTITAS));
            entMasterPenjamin.setNamaIdentitas(pstMasterPenjamin.getString(FLD_NAMA_IDENTITAS));
            entMasterPenjamin.setNamaLengkap(pstMasterPenjamin.getString(FLD_NAMA_LENGKAP));
            entMasterPenjamin.setKodeGolPenjamin(pstMasterPenjamin.getString(FLD_KODE_GOL_PENJAMIN));
            entMasterPenjamin.setAlamatPenjamin(pstMasterPenjamin.getString(FLD_ALAMAT_PENJAMIN));
            entMasterPenjamin.setKeterangan(pstMasterPenjamin.getString(FLD_KETERANGAN));
            return entMasterPenjamin;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterPenjamin(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MasterPenjamin entMasterPenjamin = fetchExc(entity.getOID());
        entity = (Entity) entMasterPenjamin;
        return entMasterPenjamin.getOID();
    }

    public static synchronized long updateExc(MasterPenjamin entMasterPenjamin) throws DBException {
        try {
            if (entMasterPenjamin.getOID() != 0) {
                PstMasterPenjamin pstMasterPenjamin = new PstMasterPenjamin(entMasterPenjamin.getOID());
                pstMasterPenjamin.setString(FLD_NO_ID_PENJAMIN, entMasterPenjamin.getNoIdPenjamin());
                pstMasterPenjamin.setString(FLD_JENIS_IDENTITAS, entMasterPenjamin.getJenisIdentitas());
                pstMasterPenjamin.setString(FLD_NAMA_IDENTITAS, entMasterPenjamin.getNamaIdentitas());
                pstMasterPenjamin.setString(FLD_NAMA_LENGKAP, entMasterPenjamin.getNamaLengkap());
                pstMasterPenjamin.setString(FLD_KODE_GOL_PENJAMIN, entMasterPenjamin.getKodeGolPenjamin());
                pstMasterPenjamin.setString(FLD_ALAMAT_PENJAMIN, entMasterPenjamin.getAlamatPenjamin());
                pstMasterPenjamin.setString(FLD_KETERANGAN, entMasterPenjamin.getKeterangan());
                pstMasterPenjamin.update();
                return entMasterPenjamin.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterPenjamin(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MasterPenjamin) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMasterPenjamin pstMasterPenjamin = new PstMasterPenjamin(oid);
            pstMasterPenjamin.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterPenjamin(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MasterPenjamin entMasterPenjamin) throws DBException {
        try {
            PstMasterPenjamin pstMasterPenjamin = new PstMasterPenjamin(0);
            pstMasterPenjamin.setString(FLD_NO_ID_PENJAMIN, entMasterPenjamin.getNoIdPenjamin());
            pstMasterPenjamin.setString(FLD_JENIS_IDENTITAS, entMasterPenjamin.getJenisIdentitas());
            pstMasterPenjamin.setString(FLD_NAMA_IDENTITAS, entMasterPenjamin.getNamaIdentitas());
            pstMasterPenjamin.setString(FLD_NAMA_LENGKAP, entMasterPenjamin.getNamaLengkap());
            pstMasterPenjamin.setString(FLD_KODE_GOL_PENJAMIN, entMasterPenjamin.getKodeGolPenjamin());
            pstMasterPenjamin.setString(FLD_ALAMAT_PENJAMIN, entMasterPenjamin.getAlamatPenjamin());
            pstMasterPenjamin.setString(FLD_KETERANGAN, entMasterPenjamin.getKeterangan());
            pstMasterPenjamin.insert();
            entMasterPenjamin.setOID(pstMasterPenjamin.getlong(FLD_MASTER_PENJAMIN_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterPenjamin(0), DBException.UNKNOWN);
        }
        return entMasterPenjamin.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MasterPenjamin) entity);
    }

    public static void resultToObject(ResultSet rs, MasterPenjamin entMasterPenjamin) {
        try {
            entMasterPenjamin.setOID(rs.getLong(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_MASTER_PENJAMIN_OID]));
            entMasterPenjamin.setNoIdPenjamin(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NO_ID_PENJAMIN]));
            entMasterPenjamin.setJenisIdentitas(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_JENIS_IDENTITAS]));
            entMasterPenjamin.setNamaIdentitas(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_IDENTITAS]));
            entMasterPenjamin.setNamaLengkap(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_LENGKAP]));
            entMasterPenjamin.setKodeGolPenjamin(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KODE_GOL_PENJAMIN]));
            entMasterPenjamin.setAlamatPenjamin(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_ALAMAT_PENJAMIN]));
            entMasterPenjamin.setKeterangan(rs.getString(PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KETERANGAN]));
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
            String sql = "SELECT * FROM " + TBL_MASTER_PENJAMIN;
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
                MasterPenjamin entMasterPenjamin = new MasterPenjamin();
                resultToObject(rs, entMasterPenjamin);
                lists.add(entMasterPenjamin);
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

    public static boolean checkOID(long entMasterPenjaminId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MASTER_PENJAMIN + " WHERE "
                    + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_MASTER_PENJAMIN_OID] + " = " + entMasterPenjaminId;
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
            String sql = "SELECT COUNT(" + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_MASTER_PENJAMIN_OID] + ") FROM " + TBL_MASTER_PENJAMIN;
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
                    MasterPenjamin entMasterPenjamin = (MasterPenjamin) list.get(ls);
                    if (oid == entMasterPenjamin.getOID()) {
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
