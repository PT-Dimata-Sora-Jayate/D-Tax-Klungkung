
package com.dimata.wponline.entity.wajibpajakonline;

import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.Entity;
import com.dimata.qdep.entity.I_PersintentExc;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import static java.lang.String.format;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class PstWajibPajak 
extends DBHandler 
implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {
    
    public static final String TBL_WAJIB_PAJAK_ONLINE = "daftar_wajib_pajak_online";
    public static final int FLD_WP_ID=0;
    public static final int FLD_NAMA_USER=1;
    public static final int FLD_PASSWORD=2;
    public static final int FLD_NAMA_WAJIB_PAJAK=3;
    public static final int FLD_ALAMAT=4;
    public static final int FLD_NOP=5;
    public static final int FLD_TELP=6;
    public static final int FLD_FAX=7;
    public static final int FLD_STATUS=8;
    public static final int FLD_EMAIL=9;
    public static final int FLD_KODE_KONFIRMASI = 10;
    public static final int FLD_TGL_PENDAFTARAN = 11;
    
    public static String[] fieldNames = {
        "WP_ID",
        "NAMA_USER",
        "PASSWORD",
        "NAMA_WAJIB_PAJAK",
        "ALAMAT",
        "NOP",
        "TELP",
        "FAX",
        "STATUS",
        "EMAIL",
        "KODE_KONFIRMASI",
        "TGL_PENDAFTARAN"
    };
    
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE
    };
    
    public PstWajibPajak() {
    }

    public PstWajibPajak(int i) throws DBException {
        super(new PstWajibPajak());
    }

    public PstWajibPajak(String sOid) throws DBException {
        super(new PstWajibPajak(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstWajibPajak(long lOid) throws DBException {
        super(new PstWajibPajak(0));//merupakan induk construktor dari DBHandler, 0 fungsinya sebagai default PSTGEJALA
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
    
    @Override
    public int getFieldSize() {
        return fieldNames.length;
    }

    @Override
    public String getTableName() {
        return TBL_WAJIB_PAJAK_ONLINE;
    }

    @Override
    public String[] getFieldNames() {
        return fieldNames;
    }

    @Override
    public int[] getFieldTypes() {
        return fieldTypes;
    }

    @Override
    public String getPersistentName() {
        return new PstWajibPajak().getClass().getName();
    }
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public static WajibPajak fetchExc(long oid) throws DBException {

        try {
            
            WajibPajak wajibPajak = new WajibPajak();
            PstWajibPajak pstWajibPajak = new PstWajibPajak(oid);
            wajibPajak.setOID(oid);
            wajibPajak.setNamaUser(pstWajibPajak.getString(FLD_NAMA_USER));
            wajibPajak.setPassword(pstWajibPajak.getString(FLD_PASSWORD));
            wajibPajak.setNamaWajibPajak(pstWajibPajak.getString(FLD_NAMA_WAJIB_PAJAK));
            wajibPajak.setAlamat(pstWajibPajak.getString(FLD_ALAMAT));
            wajibPajak.setNop(pstWajibPajak.getString(FLD_NOP));
            wajibPajak.setTelp(pstWajibPajak.getString(FLD_TELP));
            wajibPajak.setFax(pstWajibPajak.getString(FLD_FAX));
            wajibPajak.setStatus(pstWajibPajak.getString(FLD_STATUS));
            wajibPajak.setEmail(pstWajibPajak.getString(FLD_EMAIL));
            wajibPajak.setKodeKonfirmasi(pstWajibPajak.getString(FLD_KODE_KONFIRMASI));
            return wajibPajak;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstWajibPajak(0), DBException.UNKNOWN);
        }
    }
    @Override
    public long fetchExc(Entity ent) throws Exception {
        WajibPajak wajibPajak = fetchExc(ent.getOID());
        ent = (Entity)wajibPajak;
        return wajibPajak.getOID();
    }
    
    public static synchronized long updateExc(WajibPajak wajibPajak) throws DBException {
        try {
            if (wajibPajak.getOID() != 0) {
                PstWajibPajak pstWajibPajak = new PstWajibPajak(wajibPajak.getOID());
                pstWajibPajak.setString(FLD_NAMA_USER, wajibPajak.getNamaUser());
                pstWajibPajak.setString(FLD_PASSWORD, wajibPajak.getPassword());
                pstWajibPajak.setString(FLD_NAMA_WAJIB_PAJAK, wajibPajak.getNamaWajibPajak());
                pstWajibPajak.setString(FLD_ALAMAT, wajibPajak.getAlamat());
                pstWajibPajak.setString(FLD_NOP, wajibPajak.getNop());
                pstWajibPajak.setString(FLD_TELP, wajibPajak.getTelp());
                pstWajibPajak.setString(FLD_FAX, wajibPajak.getFax());
                pstWajibPajak.setString(FLD_STATUS, wajibPajak.getStatus());
                pstWajibPajak.setString(FLD_EMAIL, wajibPajak.getEmail()); 
                pstWajibPajak.setString(FLD_KODE_KONFIRMASI, wajibPajak.getKodeKonfirmasi());
                pstWajibPajak.update();

                return wajibPajak.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstWajibPajak(0), DBException.UNKNOWN);
        }

        return 0;

    }

    @Override
    public long updateExc(Entity ent) throws Exception {
        return updateExc((WajibPajak)ent);
    }
    
    public static synchronized long deleteExc(long oid) throws DBException {
        try {

            PstWajibPajak pstWajibPajak = new PstWajibPajak(oid);
            pstWajibPajak.delete();

        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstWajibPajak(0), DBException.UNKNOWN);

        }
        return oid;
    }
    

    @Override
    public long deleteExc(Entity ent) throws Exception {
        if(ent==null){
            throw  new DBException(this,DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }
    
    public static synchronized long insertExc(WajibPajak wajibPajak) 
            throws DBException {
    try {
            PstWajibPajak pstWajibPajak = new PstWajibPajak(0);           
            pstWajibPajak.setString(FLD_NAMA_USER, wajibPajak.getNamaUser());
            pstWajibPajak.setString(FLD_PASSWORD, wajibPajak.getPassword());
            pstWajibPajak.setString(FLD_NAMA_WAJIB_PAJAK, wajibPajak.getNamaWajibPajak());
            pstWajibPajak.setString(FLD_ALAMAT, wajibPajak.getAlamat());
            pstWajibPajak.setString(FLD_NOP, wajibPajak.getNop());
            pstWajibPajak.setString(FLD_TELP, wajibPajak.getTelp());
            pstWajibPajak.setString(FLD_FAX, wajibPajak.getFax());
            pstWajibPajak.setString(FLD_STATUS, wajibPajak.getStatus());
            pstWajibPajak.setString(FLD_EMAIL, wajibPajak.getEmail());
            pstWajibPajak.setString(FLD_KODE_KONFIRMASI, wajibPajak.getKodeKonfirmasi());
           
            pstWajibPajak.setDate(FLD_TGL_PENDAFTARAN,wajibPajak.getTglPendaftaran());
            pstWajibPajak.insert();
            
            wajibPajak.setOID(pstWajibPajak.getlong(FLD_WP_ID));

        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstWajibPajak(0), DBException.UNKNOWN);
        }
        return wajibPajak.getOID();
    }

    @Override
    public long insertExc(Entity ent) throws Exception {
       return insertExc((WajibPajak)ent);
    }
    
    public static void resultToObject(ResultSet rs, WajibPajak wajibPajak) {

        try {
            wajibPajak.setOID(rs.getLong(PstWajibPajak.fieldNames[PstWajibPajak.FLD_WP_ID]));
            wajibPajak.setNamaUser(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_NAMA_USER]));
            wajibPajak.setPassword(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_PASSWORD]));
            wajibPajak.setNamaWajibPajak(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_NAMA_WAJIB_PAJAK]));
            wajibPajak.setAlamat(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_ALAMAT]));
            wajibPajak.setNop(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_NOP]));
            wajibPajak.setTelp(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_TELP]));
            wajibPajak.setFax(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_FAX]));
            wajibPajak.setStatus(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_STATUS]));
            wajibPajak.setEmail(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_EMAIL]));
            wajibPajak.setKodeKonfirmasi(rs.getString(PstWajibPajak.fieldNames[PstWajibPajak.FLD_KODE_KONFIRMASI]));
            wajibPajak.setTglPendaftaran(rs.getDate(PstWajibPajak.fieldNames[PstWajibPajak.FLD_TGL_PENDAFTARAN]));
        } catch (Exception e) {
        }

    }
    
    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {

        Vector lists = new Vector();
        DBResultSet dbrs = null;

        try {
            String sql = "SELECT * FROM " + TBL_WAJIB_PAJAK_ONLINE;

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
                WajibPajak wajibPajak = new WajibPajak();
                resultToObject(rs, wajibPajak);
                lists.add(wajibPajak);
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
    
    public static Vector listAll() {
        return list(0, 500, "", "");
    }
    
    public static boolean checkOID(long mSId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_WAJIB_PAJAK_ONLINE + " WHERE "
                    + PstWajibPajak.fieldNames[PstWajibPajak.FLD_WP_ID] + " = " + mSId;
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
            String sql = "SELECT COUNT(" + PstWajibPajak.fieldNames[PstWajibPajak.FLD_WP_ID] 
                    + ") FROM " + TBL_WAJIB_PAJAK_ONLINE;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            dbrs = DBHandler.execQueryResult(sql);//execute query sql
            ResultSet rs = dbrs.getResultSet();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);//ambil isi ResultSet yg 1 atau PstEmployee.fieldNames[PstEmployee.FLD_JENIS_ITEM_ID] 
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
                    WajibPajak jenisItems = (WajibPajak) list.get(ls);
                    if (oid == jenisItems.getOID()) {
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
    
    public static WajibPajak getByLoginIDAndPassword(String namaUser, String password) {
        if ((namaUser == null) || (namaUser.length() < 1) || (password == null) || (password.length() < 1)) {
            return null;
        }
        try {

            String whereClause = " " + fieldNames[FLD_NAMA_USER] + "='" + namaUser + "' AND "
                    + fieldNames[FLD_PASSWORD] + "='" + password + "' AND "+fieldNames[FLD_STATUS]+"='2'";

            Vector vector = list(0, 0, whereClause, "");

            if ((vector == null) || (vector.size() != 1)) {
                return new WajibPajak();
            }

            return (WajibPajak) vector.get(0);

        } catch (Exception e) {
            System.out.println("getByLoginIDAndPassword " + e);
            return null;
        }
    }
    
    
    
}
