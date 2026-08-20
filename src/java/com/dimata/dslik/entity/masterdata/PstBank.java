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
 * @author Dewa
 */
public class PstBank extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_BANK = "dslik_bank";
    public static final int FLD_BANK_ID = 0;
    public static final int FLD_NAMA_BANK = 1;
    public static final int FLD_ALAMAT_BANK = 2;
    public static final int FLD_PROVINSI = 3;
    public static final int FLD_KABUPATEN = 4;
    public static final int FLD_EMAIL = 5;

    public static String[] fieldNames = {
        "BANK_ID",
        "NAMA_BANK",
        "ALAMAT_BANK",
        "PROVINSI",
        "KABUPATEN",
        "EMAIL"
    };

    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstBank() {
    }

    public PstBank(int i) throws DBException {
        super(new PstBank());
    }

    public PstBank(String sOid) throws DBException {
        super(new PstBank(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstBank(long lOid) throws DBException {
        super(new PstBank(0));
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
        return TBL_BANK;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstBank().getClass().getName();
    }

    public static Bank fetchExc(long oid) throws DBException {
        try {
            Bank entBank = new Bank();
            PstBank pstBank = new PstBank(oid);
            entBank.setOID(oid);
            entBank.setNamaBank(pstBank.getString(FLD_NAMA_BANK));
            entBank.setAlamatBank(pstBank.getString(FLD_ALAMAT_BANK));
            entBank.setProvinsi(pstBank.getString(FLD_PROVINSI));
            entBank.setKabupaten(pstBank.getString(FLD_KABUPATEN));
            entBank.setEmail(pstBank.getString(FLD_EMAIL));
            return entBank;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstBank(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        Bank entBank = fetchExc(entity.getOID());
        entity = (Entity) entBank;
        return entBank.getOID();
    }

    public static synchronized long updateExc(Bank entBank) throws DBException {
        try {
            if (entBank.getOID() != 0) {
                PstBank pstBank = new PstBank(entBank.getOID());
                pstBank.setString(FLD_NAMA_BANK, entBank.getNamaBank());
                pstBank.setString(FLD_ALAMAT_BANK, entBank.getAlamatBank());
                pstBank.setString(FLD_PROVINSI, entBank.getProvinsi());
                pstBank.setString(FLD_KABUPATEN, entBank.getKabupaten());
                pstBank.setString(FLD_EMAIL, entBank.getEmail());
                pstBank.update();
                return entBank.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstBank(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((Bank) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstBank pstBank = new PstBank(oid);
            pstBank.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstBank(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(Bank entBank) throws DBException {
        try {
            PstBank pstBank = new PstBank(0);
            pstBank.setString(FLD_NAMA_BANK, entBank.getNamaBank());
            pstBank.setString(FLD_ALAMAT_BANK, entBank.getAlamatBank());
            pstBank.setString(FLD_PROVINSI, entBank.getProvinsi());
            pstBank.setString(FLD_KABUPATEN, entBank.getKabupaten());
            pstBank.setString(FLD_EMAIL, entBank.getEmail());
            pstBank.insert();
            entBank.setOID(pstBank.getlong(FLD_BANK_ID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstBank(0), DBException.UNKNOWN);
        }
        return entBank.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((Bank) entity);
    }

    public static void resultToObject(ResultSet rs, Bank entBank) {
        try {
            entBank.setOID(rs.getLong(PstBank.fieldNames[PstBank.FLD_BANK_ID]));
            entBank.setNamaBank(rs.getString(PstBank.fieldNames[PstBank.FLD_NAMA_BANK]));
            entBank.setAlamatBank(rs.getString(PstBank.fieldNames[PstBank.FLD_ALAMAT_BANK]));
            entBank.setProvinsi(rs.getString(PstBank.fieldNames[PstBank.FLD_PROVINSI]));
            entBank.setKabupaten(rs.getString(PstBank.fieldNames[PstBank.FLD_KABUPATEN]));
            entBank.setEmail(rs.getString(PstBank.fieldNames[PstBank.FLD_EMAIL]));
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
            String sql = "SELECT * FROM " + TBL_BANK;
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
                Bank entBank = new Bank();
                resultToObject(rs, entBank);
                lists.add(entBank);
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

    public static boolean checkOID(long entBankId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_BANK + " WHERE "
                    + PstBank.fieldNames[PstBank.FLD_BANK_ID] + " = " + entBankId;
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
            String sql = "SELECT COUNT(" + PstBank.fieldNames[PstBank.FLD_BANK_ID] + ") FROM " + TBL_BANK;
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
                    Bank entBank = (Bank) list.get(ls);
                    if (oid == entBank.getOID()) {
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
