/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.masterdata;

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

public class PstMasterCifHapusBuku extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_MASTERCIFHAPUSBUKU = "dslik_debitur_hapus_buku";
    public static final int FLD_MASTERCIHAPUSBUKU = 0;
    public static final int FLD_CIFHAPUSBUKU = 1;

    public static String[] fieldNames = {
        "CIF_OID",
        "CIF_HAPUS_BUKU"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING
    };

    public PstMasterCifHapusBuku() {
    }

    public PstMasterCifHapusBuku(int i) throws DBException {
        super(new PstMasterCifHapusBuku());
    }

    public PstMasterCifHapusBuku(String sOid) throws DBException {
        super(new PstMasterCifHapusBuku(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstMasterCifHapusBuku(long lOid) throws DBException {
        super(new PstMasterCifHapusBuku(0));
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
        return TBL_MASTERCIFHAPUSBUKU;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstMasterCifHapusBuku().getClass().getName();
    }

    public static MasterCifHapusBuku fetchExc(long oid) throws DBException {
        try {
            MasterCifHapusBuku entMasterCifHapusBuku = new MasterCifHapusBuku();
            PstMasterCifHapusBuku pstMasterCifHapusBuku = new PstMasterCifHapusBuku(oid);
            entMasterCifHapusBuku.setOID(oid);
            entMasterCifHapusBuku.setCifHapusBuku(pstMasterCifHapusBuku.getString(FLD_CIFHAPUSBUKU));
            return entMasterCifHapusBuku;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterCifHapusBuku(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        MasterCifHapusBuku entMasterCifHapusBuku = fetchExc(entity.getOID());
        entity = (Entity) entMasterCifHapusBuku;
        return entMasterCifHapusBuku.getOID();
    }

    public static synchronized long updateExc(MasterCifHapusBuku entMasterCifHapusBuku) throws DBException {
        try {
            if (entMasterCifHapusBuku.getOID() != 0) {
                PstMasterCifHapusBuku pstMasterCifHapusBuku = new PstMasterCifHapusBuku(entMasterCifHapusBuku.getOID());
                pstMasterCifHapusBuku.setString(FLD_CIFHAPUSBUKU, entMasterCifHapusBuku.getCifHapusBuku());
                pstMasterCifHapusBuku.update();
                return entMasterCifHapusBuku.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterCifHapusBuku(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((MasterCifHapusBuku) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstMasterCifHapusBuku pstMasterCifHapusBuku = new PstMasterCifHapusBuku(oid);
            pstMasterCifHapusBuku.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterCifHapusBuku(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(MasterCifHapusBuku entMasterCifHapusBuku) throws DBException {
        try {
            PstMasterCifHapusBuku pstMasterCifHapusBuku = new PstMasterCifHapusBuku(0);
            pstMasterCifHapusBuku.setString(FLD_CIFHAPUSBUKU, entMasterCifHapusBuku.getCifHapusBuku());
            pstMasterCifHapusBuku.insert();
            entMasterCifHapusBuku.setOID(pstMasterCifHapusBuku.getLong(FLD_MASTERCIHAPUSBUKU));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstMasterCifHapusBuku(0), DBException.UNKNOWN);
        }
        return entMasterCifHapusBuku.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((MasterCifHapusBuku) entity);
    }

    public static void resultToObject(ResultSet rs, MasterCifHapusBuku entMasterCifHapusBuku) {
        try {
            entMasterCifHapusBuku.setOID(rs.getLong(PstMasterCifHapusBuku.fieldNames[PstMasterCifHapusBuku.FLD_MASTERCIHAPUSBUKU]));
            entMasterCifHapusBuku.setCifHapusBuku(rs.getString(PstMasterCifHapusBuku.fieldNames[PstMasterCifHapusBuku.FLD_CIFHAPUSBUKU]));
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
            String sql = "SELECT * FROM " + TBL_MASTERCIFHAPUSBUKU;
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
                MasterCifHapusBuku entMasterCifHapusBuku = new MasterCifHapusBuku();
                resultToObject(rs, entMasterCifHapusBuku);
                lists.add(entMasterCifHapusBuku);
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

    public static boolean checkOID(long entMasterCifHapusBukuId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_MASTERCIFHAPUSBUKU + " WHERE "
                    + PstMasterCifHapusBuku.fieldNames[PstMasterCifHapusBuku.FLD_MASTERCIHAPUSBUKU] + " = " + entMasterCifHapusBukuId;
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
            String sql = "SELECT COUNT(" + PstMasterCifHapusBuku.fieldNames[PstMasterCifHapusBuku.FLD_MASTERCIHAPUSBUKU] + ") FROM " + TBL_MASTERCIFHAPUSBUKU;
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
                    MasterCifHapusBuku entMasterCifHapusBuku = (MasterCifHapusBuku) list.get(ls);
                    if (oid == entMasterCifHapusBuku.getOID()) {
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
