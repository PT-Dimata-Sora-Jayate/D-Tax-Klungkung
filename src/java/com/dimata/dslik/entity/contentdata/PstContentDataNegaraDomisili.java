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

public class PstContentDataNegaraDomisili extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    public static final String TBL_CONTENT_DATA_NEGARA_DOMISILI = "dslik_cd_kode_negara_domisili";
    public static final int FLD_NEGARA_DOMISILI_OID = 0;
    public static final int FLD_NAMANEGARA = 1;
    public static final int FLD_KODE_CORE_BANKING = 2;
    public static final int FLD_KODE_OJK = 3;
    public static String[] fieldNames = {
        "NEGARA_DOMISILI_OID",
        "NAMANEGARA",
        "KODE_CORE_BANKING",
        "KODE_OJK"
    };
    public static int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_STRING
    };

    public PstContentDataNegaraDomisili() {
    }

    public PstContentDataNegaraDomisili(int i) throws DBException {
        super(new PstContentDataNegaraDomisili());
    }

    public PstContentDataNegaraDomisili(String sOid) throws DBException {
        super(new PstContentDataNegaraDomisili(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstContentDataNegaraDomisili(long lOid) throws DBException {
        super(new PstContentDataNegaraDomisili(0));
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
        return TBL_CONTENT_DATA_NEGARA_DOMISILI;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstContentDataNegaraDomisili().getClass().getName();
    }

    public static ContentDataNegaraDomisili fetchExc(long oid) throws DBException {
        try {
            ContentDataNegaraDomisili entContentDataNegaraDomisili = new ContentDataNegaraDomisili();
            PstContentDataNegaraDomisili pstContentDataNegaraDomisili = new PstContentDataNegaraDomisili(oid);
            entContentDataNegaraDomisili.setOID(oid);
            entContentDataNegaraDomisili.setNamaNegara(pstContentDataNegaraDomisili.getString(FLD_NAMANEGARA));
            entContentDataNegaraDomisili.setKodeCoreBanking(pstContentDataNegaraDomisili.getString(FLD_KODE_CORE_BANKING));
            entContentDataNegaraDomisili.setKodeOjk(pstContentDataNegaraDomisili.getString(FLD_KODE_OJK));
            return entContentDataNegaraDomisili;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNegaraDomisili(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        ContentDataNegaraDomisili entContentDataNegaraDomisili = fetchExc(entity.getOID());
        entity = (Entity) entContentDataNegaraDomisili;
        return entContentDataNegaraDomisili.getOID();
    }

    public static synchronized long updateExc(ContentDataNegaraDomisili entContentDataNegaraDomisili) throws DBException {
        try {
            if (entContentDataNegaraDomisili.getOID() != 0) {
                PstContentDataNegaraDomisili pstContentDataNegaraDomisili = new PstContentDataNegaraDomisili(entContentDataNegaraDomisili.getOID());
                pstContentDataNegaraDomisili.setString(FLD_NAMANEGARA, entContentDataNegaraDomisili.getNamaNegara());
                pstContentDataNegaraDomisili.setString(FLD_KODE_CORE_BANKING, entContentDataNegaraDomisili.getKodeCoreBanking());
                pstContentDataNegaraDomisili.setString(FLD_KODE_OJK, entContentDataNegaraDomisili.getKodeOjk());
                pstContentDataNegaraDomisili.update();
                return entContentDataNegaraDomisili.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNegaraDomisili(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((ContentDataNegaraDomisili) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstContentDataNegaraDomisili pstContentDataNegaraDomisili = new PstContentDataNegaraDomisili(oid);
            pstContentDataNegaraDomisili.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNegaraDomisili(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(ContentDataNegaraDomisili entContentDataNegaraDomisili) throws DBException {
        try {
            PstContentDataNegaraDomisili pstContentDataNegaraDomisili = new PstContentDataNegaraDomisili(0);
            pstContentDataNegaraDomisili.setString(FLD_NAMANEGARA, entContentDataNegaraDomisili.getNamaNegara());
            pstContentDataNegaraDomisili.setString(FLD_KODE_CORE_BANKING, entContentDataNegaraDomisili.getKodeCoreBanking());
            pstContentDataNegaraDomisili.setString(FLD_KODE_OJK, entContentDataNegaraDomisili.getKodeOjk());
            pstContentDataNegaraDomisili.insert();
            entContentDataNegaraDomisili.setOID(pstContentDataNegaraDomisili.getlong(FLD_NEGARA_DOMISILI_OID));
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstContentDataNegaraDomisili(0), DBException.UNKNOWN);
        }
        return entContentDataNegaraDomisili.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((ContentDataNegaraDomisili) entity);
    }

    public static void resultToObject(ResultSet rs, ContentDataNegaraDomisili entContentDataNegaraDomisili) {
        try {
            entContentDataNegaraDomisili.setOID(rs.getLong(PstContentDataNegaraDomisili.fieldNames[PstContentDataNegaraDomisili.FLD_NEGARA_DOMISILI_OID]));
            entContentDataNegaraDomisili.setNamaNegara(rs.getString(PstContentDataNegaraDomisili.fieldNames[PstContentDataNegaraDomisili.FLD_NAMANEGARA]));
            entContentDataNegaraDomisili.setKodeCoreBanking(rs.getString(PstContentDataNegaraDomisili.fieldNames[PstContentDataNegaraDomisili.FLD_KODE_CORE_BANKING]));
            entContentDataNegaraDomisili.setKodeOjk(rs.getString(PstContentDataNegaraDomisili.fieldNames[PstContentDataNegaraDomisili.FLD_KODE_OJK]));
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
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_NEGARA_DOMISILI;
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
                ContentDataNegaraDomisili entContentDataNegaraDomisili = new ContentDataNegaraDomisili();
                resultToObject(rs, entContentDataNegaraDomisili);
                lists.add(entContentDataNegaraDomisili);
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

    public static boolean checkOID(long entContentDataNegaraDomisiliId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_CONTENT_DATA_NEGARA_DOMISILI + " WHERE "
                    + PstContentDataNegaraDomisili.fieldNames[PstContentDataNegaraDomisili.FLD_NEGARA_DOMISILI_OID] + " = " + entContentDataNegaraDomisiliId;
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
            String sql = "SELECT COUNT(" + PstContentDataNegaraDomisili.fieldNames[PstContentDataNegaraDomisili.FLD_NEGARA_DOMISILI_OID] + ") FROM " + TBL_CONTENT_DATA_NEGARA_DOMISILI;
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
                    ContentDataNegaraDomisili entContentDataNegaraDomisili = (ContentDataNegaraDomisili) list.get(ls);
                    if (oid == entContentDataNegaraDomisili.getOID()) {
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
