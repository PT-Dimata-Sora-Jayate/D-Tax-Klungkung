/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.payment;

/**
 *
 * @author IanRizky
 */
 
import com.dimata.dtaxintegration.entity.inquery.BphtbIprotax;
import java.sql.*;
import com.dimata.util.lang.I_Language;
import com.dimata.qdep.db.*;
import com.dimata.qdep.entity.*;
import com.dimata.util.Command;
import com.dimata.webclient.AppSetting;
import java.util.Vector;

public class PstPaymentBphtbIprotaxOld extends DBHandler implements I_DBInterface, I_DBType, I_PersintentExc, I_Language {

    //public static final String TBL_PAYMENTBPHTBIPROTAX = "IPROTAXBPHTB.PEMBAYARAN_BPHTB";
    public static final String TBL_PAYMENTBPHTBIPROTAX = "IPROTAXBPHTB.PEMBAYARAN_SSPD";
    public static final int FLD_KDPROPINSI = 0;
    public static final int FLD_KDDATI2 = 1;
    public static final int FLD_THNBPHTB = 2;
    public static final int FLD_BLNBPHTB = 3;
    public static final int FLD_TGLBPHTB = 4;
    public static final int FLD_NOURUTBPHTB = 5;
    public static final int FLD_INDEKSBPHTB = 6;
    public static final int FLD_KDPEJABAT = 7;
    public static final int FLD_KDBANKTUNGGAL = 8;
    public static final int FLD_KDBANKPERSEPSI = 9;
    public static final int FLD_TGLPEMBAYARAN = 10;
    public static final int FLD_NOTRANSBAYAR = 11;
    public static final int FLD_NAMAWP = 12;
    public static final int FLD_BPHTBKURANGBAYAR = 13;
    public static final int FLD_BPHTBSUDAHBAYAR = 14;
    public static final int FLD_KDKECAMATANOP = 15;
    public static final int FLD_KDKELURAHANOP = 16;
    public static final int FLD_KDBLOKOP = 17;
    public static final int FLD_NOURUTOP = 18;
    public static final int FLD_KDJENISOP = 19;
    public static final int FLD_KDTP = 20;
    public static final int FLD_USERBANKREKAM = 21;
    public static final int FLD_NMPENYETOR = 22;
	public static final int FLD_KDSUMBERDATA = 23;
	public static final int FLD_NOTRANSAKSIBYR = 24;
	public static final int FLD_NOTRANSAKSIBYRBANK = 25;
    //06KLK scema baru 
    public static final int FLD_REKAM_BAYAR = 26;

    public static String[] fieldNames = {
		"KD_PROPINSI",
		"KD_DATI2",
		"THN_BPHTB",
		"BLN_BPHTB",
		"TGL_BPHTB",
		"NO_URUT_BPHTB",
		"INDEKS_BPHTB",
		"KD_PEJABAT",
		"KD_BANK_TUNGGAL",
		"KD_BANK_PERSEPSI",
		"TGL_PEMBAYARAN",
		"NO_TRANS_BAYAR",
		"NAMA_WP",
		"BPHTB_KURANG_BAYAR",
		"BPHTB_SDH_DIBAYAR",
		"KD_KECAMATAN_OP",
		"KD_KELURAHAN_OP",
		"KD_BLOK_OP",
		"NO_URUT_OP",
		"KD_JNS_OP",
		"KD_TP",
		"USER_BANK_REKAM",
		"NM_PENYETOR",
		"KD_SUMBER_DATA",
		"NO_TRANSAKSI_BYR",
		"NO_TRANSAKSI_BYR_BANK",
		"TGL_REKAM_BYR"
    };

    public static int[] fieldTypes = {
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
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_FLOAT,
        TYPE_FLOAT,
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
		TYPE_STRING,
        TYPE_DATE
    };

    public PstPaymentBphtbIprotaxOld() {
    }

    public PstPaymentBphtbIprotaxOld(int i) throws DBException {
        super(new PstPaymentBphtbIprotaxOld());
    }

    public PstPaymentBphtbIprotaxOld(String sOid) throws DBException {
        super(new PstPaymentBphtbIprotaxOld(0));
        if (!locate(sOid)) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public PstPaymentBphtbIprotaxOld(long lOid) throws DBException {
        super(new PstPaymentBphtbIprotaxOld(0));
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
        return TBL_PAYMENTBPHTBIPROTAX;
    }

    public String[] getFieldNames() {
        return fieldNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new PstPaymentPbbIprotax().getClass().getName();
    }

    public static PaymentBphtbIprotax fetchExc(long oid) throws DBException {
        try {
            PaymentBphtbIprotax entPaymentBphtbprotax = new PaymentBphtbIprotax();
            PstPaymentBphtbIprotaxOld pstPaymentBphtbIprotax = new PstPaymentBphtbIprotaxOld(oid);
            entPaymentBphtbprotax.setOID(oid);
            entPaymentBphtbprotax.setKdDati2(pstPaymentBphtbIprotax.getString(FLD_KDDATI2));
            entPaymentBphtbprotax.setThbBphtb(pstPaymentBphtbIprotax.getString(FLD_THNBPHTB));
            entPaymentBphtbprotax.setBlnBphtb(pstPaymentBphtbIprotax.getString(FLD_BLNBPHTB));
            entPaymentBphtbprotax.setTglBphtb(pstPaymentBphtbIprotax.getString(FLD_TGLBPHTB));
            entPaymentBphtbprotax.setNoUrutBphtb(pstPaymentBphtbIprotax.getString(FLD_NOURUTBPHTB));
            entPaymentBphtbprotax.setIndeksBphtb(pstPaymentBphtbIprotax.getString(FLD_INDEKSBPHTB));
            entPaymentBphtbprotax.setKdPejabat(pstPaymentBphtbIprotax.getString(FLD_KDPEJABAT));
            entPaymentBphtbprotax.setKdBankPersepsi(pstPaymentBphtbIprotax.getString(FLD_KDBANKPERSEPSI));
            entPaymentBphtbprotax.setTglPembayaran(pstPaymentBphtbIprotax.getDate(FLD_TGLPEMBAYARAN));
            entPaymentBphtbprotax.setNoTransBayar(pstPaymentBphtbIprotax.getString(FLD_NOTRANSBAYAR));
            entPaymentBphtbprotax.setNamaWP(pstPaymentBphtbIprotax.getString(FLD_NAMAWP));
            entPaymentBphtbprotax.setBphtbKurangBayar(pstPaymentBphtbIprotax.getdouble(FLD_BPHTBKURANGBAYAR));
            entPaymentBphtbprotax.setBphtbSdhBayar(pstPaymentBphtbIprotax.getdouble(FLD_BPHTBSUDAHBAYAR));
            entPaymentBphtbprotax.setKdKecamatanOp(pstPaymentBphtbIprotax.getString(FLD_KDKECAMATANOP));
            entPaymentBphtbprotax.setKdKelurahanOp(pstPaymentBphtbIprotax.getString(FLD_KDKELURAHANOP));
            entPaymentBphtbprotax.setKdBlokOp(pstPaymentBphtbIprotax.getString(FLD_KDBLOKOP));
            entPaymentBphtbprotax.setNoUrutOp(pstPaymentBphtbIprotax.getString(FLD_NOURUTOP));
            entPaymentBphtbprotax.setKdJnsOp(pstPaymentBphtbIprotax.getString(FLD_KDJENISOP));
            entPaymentBphtbprotax.setKdTp(pstPaymentBphtbIprotax.getString(FLD_KDTP));
            entPaymentBphtbprotax.setUserBankRekam(pstPaymentBphtbIprotax.getString(FLD_USERBANKREKAM));
            entPaymentBphtbprotax.setNmPenyetor(pstPaymentBphtbIprotax.getString(FLD_NMPENYETOR));
            entPaymentBphtbprotax.setKdSumberData(pstPaymentBphtbIprotax.getString(FLD_KDSUMBERDATA));
			entPaymentBphtbprotax.setNoTransaksiBayar(pstPaymentBphtbIprotax.getString(FLD_NOTRANSAKSIBYR));
			entPaymentBphtbprotax.setNoTransaksiBayarBank(pstPaymentBphtbIprotax.getString(FLD_NOTRANSAKSIBYRBANK));
            return entPaymentBphtbprotax;
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbIprotax(0), DBException.UNKNOWN);
        }
    }

    public long fetchExc(Entity entity) throws Exception {
        PaymentBphtbIprotax entPaymentBphtbIprotax = fetchExc(entity.getOID());
        entity = (Entity) entPaymentBphtbIprotax;
        return entPaymentBphtbIprotax.getOID();
    }

    public static synchronized long updateExc(PaymentBphtbIprotax entPaymentBphtbIprotax) throws DBException {
        try {
            if (entPaymentBphtbIprotax.getOID() != 0) {
                PstPaymentBphtbIprotaxOld pstPaymentBphtbIprotax = new PstPaymentBphtbIprotaxOld(entPaymentBphtbIprotax.getOID());
                pstPaymentBphtbIprotax.setString(FLD_KDDATI2, entPaymentBphtbIprotax.getKdDati2());
                pstPaymentBphtbIprotax.setString(FLD_THNBPHTB, entPaymentBphtbIprotax.getThbBphtb());
                pstPaymentBphtbIprotax.setString(FLD_BLNBPHTB, entPaymentBphtbIprotax.getBlnBphtb());
                pstPaymentBphtbIprotax.setString(FLD_TGLBPHTB, entPaymentBphtbIprotax.getTglBphtb());
                pstPaymentBphtbIprotax.setString(FLD_NOURUTBPHTB, entPaymentBphtbIprotax.getNoUrutBphtb());
                pstPaymentBphtbIprotax.setString(FLD_INDEKSBPHTB, entPaymentBphtbIprotax.getIndeksBphtb());
                pstPaymentBphtbIprotax.setString(FLD_KDPEJABAT, entPaymentBphtbIprotax.getKdPejabat());
                pstPaymentBphtbIprotax.setString(FLD_KDBANKTUNGGAL, entPaymentBphtbIprotax.getKdBankTunggal());
                pstPaymentBphtbIprotax.setString(FLD_KDBANKPERSEPSI, entPaymentBphtbIprotax.getKdBankPersepsi());
                pstPaymentBphtbIprotax.setDate(FLD_TGLPEMBAYARAN, entPaymentBphtbIprotax.getTglPembayaran());
                pstPaymentBphtbIprotax.setString(FLD_NOTRANSBAYAR, entPaymentBphtbIprotax.getNoTransBayar());
                pstPaymentBphtbIprotax.setString(FLD_NAMAWP, entPaymentBphtbIprotax.getNamaWP());
                pstPaymentBphtbIprotax.setDouble(FLD_BPHTBKURANGBAYAR, entPaymentBphtbIprotax.getBphtbKurangBayar());
                pstPaymentBphtbIprotax.setDouble(FLD_BPHTBSUDAHBAYAR, entPaymentBphtbIprotax.getBphtbSdhBayar());
                pstPaymentBphtbIprotax.setString(FLD_KDKECAMATANOP, entPaymentBphtbIprotax.getKdKecamatanOp());
                pstPaymentBphtbIprotax.setString(FLD_KDKELURAHANOP, entPaymentBphtbIprotax.getKdKelurahanOp());
                pstPaymentBphtbIprotax.setString(FLD_KDBLOKOP, entPaymentBphtbIprotax.getKdBlokOp());
                pstPaymentBphtbIprotax.setString(FLD_NOURUTOP, entPaymentBphtbIprotax.getNoUrutOp());
                pstPaymentBphtbIprotax.setString(FLD_KDJENISOP, entPaymentBphtbIprotax.getKdJnsOp());
                pstPaymentBphtbIprotax.setString(FLD_KDTP, entPaymentBphtbIprotax.getKdTp());
                pstPaymentBphtbIprotax.setString(FLD_USERBANKREKAM, entPaymentBphtbIprotax.getUserBankRekam());
                pstPaymentBphtbIprotax.setString(FLD_NMPENYETOR, entPaymentBphtbIprotax.getNmPenyetor());
				pstPaymentBphtbIprotax.setString(FLD_KDSUMBERDATA, entPaymentBphtbIprotax.getKdSumberData());
				pstPaymentBphtbIprotax.setString(FLD_NOTRANSAKSIBYR, entPaymentBphtbIprotax.getNoTransaksiBayar());
				pstPaymentBphtbIprotax.setString(FLD_NOTRANSAKSIBYRBANK, entPaymentBphtbIprotax.getNoTransaksiBayarBank());
                pstPaymentBphtbIprotax.update();
                return entPaymentBphtbIprotax.getOID();
            }
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentBphtbIprotaxOld(0), DBException.UNKNOWN);
        }
        return 0;
    }

    public long updateExc(Entity entity) throws Exception {
        return updateExc((PaymentBphtbIprotax) entity);
    }

    public static synchronized long deleteExc(long oid) throws DBException {
        try {
            PstPaymentBphtbIprotaxOld pstPaymentBphtbIprotax = new PstPaymentBphtbIprotaxOld(oid);
            pstPaymentBphtbIprotax.delete();
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentPbbIprotax(0), DBException.UNKNOWN);
        }
        return oid;
    }

    public long deleteExc(Entity entity) throws Exception {
        if (entity == null) {
            throw new DBException(this, DBException.RECORD_NOT_FOUND);
        }
        return deleteExc(entity.getOID());
    }

    public static synchronized long insertExc(PaymentBphtbIprotax entPaymentBphtbIprotax) throws DBException {
        try {
            PstPaymentBphtbIprotaxOld pstPaymentBphtbIprotax = new PstPaymentBphtbIprotaxOld(0);
            pstPaymentBphtbIprotax.setString(FLD_KDPROPINSI, entPaymentBphtbIprotax.getKdProvinsi());
            pstPaymentBphtbIprotax.setString(FLD_KDDATI2, entPaymentBphtbIprotax.getKdDati2());
			pstPaymentBphtbIprotax.setString(FLD_THNBPHTB, entPaymentBphtbIprotax.getThbBphtb());
			pstPaymentBphtbIprotax.setString(FLD_BLNBPHTB, entPaymentBphtbIprotax.getBlnBphtb());
			pstPaymentBphtbIprotax.setString(FLD_TGLBPHTB, entPaymentBphtbIprotax.getTglBphtb());
			pstPaymentBphtbIprotax.setString(FLD_NOURUTBPHTB, entPaymentBphtbIprotax.getNoUrutBphtb());
			pstPaymentBphtbIprotax.setString(FLD_INDEKSBPHTB, entPaymentBphtbIprotax.getIndeksBphtb());
			pstPaymentBphtbIprotax.setString(FLD_KDPEJABAT, entPaymentBphtbIprotax.getKdPejabat());
			pstPaymentBphtbIprotax.setString(FLD_KDBANKTUNGGAL, entPaymentBphtbIprotax.getKdBankTunggal());
			pstPaymentBphtbIprotax.setString(FLD_KDBANKPERSEPSI, entPaymentBphtbIprotax.getKdBankPersepsi());
			pstPaymentBphtbIprotax.setDate(FLD_TGLPEMBAYARAN, entPaymentBphtbIprotax.getTglPembayaran());
                        
			//pstPaymentBphtbIprotax.setString(FLD_NOTRANSBAYAR, entPaymentBphtbIprotax.getNoTransBayar());
			pstPaymentBphtbIprotax.setString(FLD_NAMAWP, entPaymentBphtbIprotax.getNamaWP());
			pstPaymentBphtbIprotax.setDouble(FLD_BPHTBKURANGBAYAR, entPaymentBphtbIprotax.getBphtbKurangBayar());
			pstPaymentBphtbIprotax.setDouble(FLD_BPHTBSUDAHBAYAR, entPaymentBphtbIprotax.getBphtbSdhBayar());
			//pstPaymentBphtbIprotax.setString(FLD_KDKECAMATANOP, entPaymentBphtbIprotax.getKdKecamatanOp());
			//pstPaymentBphtbIprotax.setString(FLD_KDKELURAHANOP, entPaymentBphtbIprotax.getKdKelurahanOp());
			//pstPaymentBphtbIprotax.setString(FLD_KDBLOKOP, entPaymentBphtbIprotax.getKdBlokOp());
			//pstPaymentBphtbIprotax.setString(FLD_NOURUTOP, entPaymentBphtbIprotax.getNoUrutOp());
			//pstPaymentBphtbIprotax.setString(FLD_KDJENISOP, entPaymentBphtbIprotax.getKdJnsOp());
			pstPaymentBphtbIprotax.setString(FLD_KDTP, entPaymentBphtbIprotax.getKdTp());
			pstPaymentBphtbIprotax.setString(FLD_USERBANKREKAM, entPaymentBphtbIprotax.getUserBankRekam());
			pstPaymentBphtbIprotax.setString(FLD_NMPENYETOR, entPaymentBphtbIprotax.getNmPenyetor());
			pstPaymentBphtbIprotax.setString(FLD_KDSUMBERDATA, entPaymentBphtbIprotax.getKdSumberData());
			pstPaymentBphtbIprotax.setString(FLD_NOTRANSAKSIBYRBANK, entPaymentBphtbIprotax.getNoTransaksiBayarBank());
			pstPaymentBphtbIprotax.setString(FLD_NOTRANSAKSIBYR, entPaymentBphtbIprotax.getNoTransaksiBayar());
			pstPaymentBphtbIprotax.setDate(FLD_REKAM_BAYAR, entPaymentBphtbIprotax.getTglPembayaran());
            
            pstPaymentBphtbIprotax.insert();
            entPaymentBphtbIprotax.setOID(1);
        } catch (DBException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new DBException(new PstPaymentBphtbIprotaxOld(0), DBException.UNKNOWN);
        }
        return entPaymentBphtbIprotax.getOID();
    }

    public long insertExc(Entity entity) throws Exception {
        return insertExc((PaymentPbbIprotax) entity);
    }

    public static void resultToObject(ResultSet rs, PaymentBphtbIprotax entPaymentBphtbIprotax) {
        try {
            
            //entPaymentPbbIprotax.setOID(rs.getLong(PstPaymentPbbRaversalIprotax.fieldNames[PstPaymentPbbRaversalIprotax.FLD_IDRAVERSAL]));
			entPaymentBphtbIprotax.setKdProvinsi(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDPROPINSI]));
            entPaymentBphtbIprotax.setKdDati2(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDDATI2]));
            entPaymentBphtbIprotax.setThbBphtb(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_THNBPHTB]));
            entPaymentBphtbIprotax.setBlnBphtb(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_BLNBPHTB]));
            entPaymentBphtbIprotax.setTglBphtb(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_TGLBPHTB]));
            entPaymentBphtbIprotax.setNoUrutBphtb(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NOURUTBPHTB]));
            entPaymentBphtbIprotax.setIndeksBphtb(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_INDEKSBPHTB]));
            entPaymentBphtbIprotax.setKdPejabat(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDPEJABAT]));
            entPaymentBphtbIprotax.setKdBankPersepsi(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDBANKPERSEPSI]));
            entPaymentBphtbIprotax.setTglPembayaran(rs.getDate(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_TGLPEMBAYARAN]));
            entPaymentBphtbIprotax.setNoTransBayar(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NOTRANSBAYAR]));
            entPaymentBphtbIprotax.setNamaWP(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NAMAWP]));
            entPaymentBphtbIprotax.setBphtbKurangBayar(rs.getDouble(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_BPHTBKURANGBAYAR]));
            entPaymentBphtbIprotax.setBphtbSdhBayar(rs.getDouble(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_BPHTBSUDAHBAYAR]));
            entPaymentBphtbIprotax.setKdKecamatanOp(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDKECAMATANOP]));
            entPaymentBphtbIprotax.setKdKelurahanOp(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDKELURAHANOP]));
            entPaymentBphtbIprotax.setKdBlokOp(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDBLOKOP]));
            entPaymentBphtbIprotax.setNoUrutOp(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NOURUTOP]));
            entPaymentBphtbIprotax.setKdJnsOp(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDJENISOP]));
            entPaymentBphtbIprotax.setKdTp(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDTP]));
            entPaymentBphtbIprotax.setUserBankRekam(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_USERBANKREKAM]));
            entPaymentBphtbIprotax.setNmPenyetor(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NMPENYETOR]));
            entPaymentBphtbIprotax.setKdSumberData(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDSUMBERDATA]));
			entPaymentBphtbIprotax.setNoTransaksiBayar(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NOTRANSAKSIBYR]));
			entPaymentBphtbIprotax.setNoTransaksiBayarBank(rs.getString(PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_NOTRANSAKSIBYRBANK]));
            
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
            String sql = "SELECT * FROM " + TBL_PAYMENTBPHTBIPROTAX;
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
                PaymentBphtbIprotax entPaymentBphtbIprotax = new PaymentBphtbIprotax();
                resultToObject(rs, entPaymentBphtbIprotax);
                lists.add(entPaymentBphtbIprotax);
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

    public static boolean checkOID(long entPaymentBphtbIprotaxId) {
        DBResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + TBL_PAYMENTBPHTBIPROTAX + " WHERE "
                    + PstPaymentBphtbIprotaxOld.fieldNames[PstPaymentBphtbIprotaxOld.FLD_KDPROPINSI] + " = " + entPaymentBphtbIprotaxId;
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
	
	public static BphtbIprotax checkNOp(String  entNop, String tahun) {
        DBResultSet dbrs = null;
        BphtbIprotax entBphtbIprotax = null;
        try {
            String sql = "SELECT * FROM VIEW_BPHTB_ALL WHERE NO_ID  = '" + entNop+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                entBphtbIprotax=new BphtbIprotax();
                entBphtbIprotax.setNoId(rs.getString("NO_ID"));
                entBphtbIprotax.setKdPropinsi(rs.getString("KD_PROPINSI"));
                entBphtbIprotax.setKdDati2(rs.getString("KD_DATI2"));
                entBphtbIprotax.setThnBphtb(rs.getString("THN_BPHTB"));
				entBphtbIprotax.setBlnBphtb(rs.getString("BLN_BPHTB"));
                entBphtbIprotax.setTglBphtb(rs.getString("TGL_BPHTB"));
                entBphtbIprotax.setNoUrutBphtb(rs.getString("NO_URUT_BPHTB"));
                entBphtbIprotax.setIndeksBphtb(rs.getString("INDEKS_BPHTB"));
                entBphtbIprotax.setKdPejabat(rs.getString("KD_PEJABAT"));
                entBphtbIprotax.setKdBankTunggal(rs.getString("KD_BANK_TUNGGAL"));
                entBphtbIprotax.setKdBankPersepsi(rs.getString("KD_BANK_PERSEPSI"));
                entBphtbIprotax.setNama(rs.getString("NAMA"));
                entBphtbIprotax.setJumTagihan(rs.getString("JUM_TAGIHAN"));
                entBphtbIprotax.setsNoId(rs.getString("SNOID"));
                entBphtbIprotax.setPpat(rs.getString("PPAT"));
                entBphtbIprotax.setKdKecamatanOp(rs.getString("KD_KECAMATAN_OP"));
                entBphtbIprotax.setKdKelurahanOp(rs.getString("KD_KELURAHAN_OP"));
                entBphtbIprotax.setKdBlokOp(rs.getString("KD_BLOK_OP"));
                entBphtbIprotax.setNoUrutOp(rs.getString("NO_URUT_OP"));
                entBphtbIprotax.setKdJenisOp(rs.getString("KD_JNS_OP"));
				//entBphtbIprotax.setTglBayar(rs.getDate("TGL_BAYAR_SSB_WP"));
 
            }
            rs.close();
            return entBphtbIprotax;
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return entBphtbIprotax;
        }
    }
	
	public static int DeleteDataPembayaran(String idPaymentBank) {

        int iResult = 0;
        DBResultSet dbrs = null;
        String stSql = " DELETE FROM IPROTAXPBB.PEMBAYARAN_BPHTB WHERE "+
                                    "NO_TRANSAKSI_BYR_BANK='"+idPaymentBank+"'";
//                                  " KD_PROPINSI='"+kdprovinsi+"' "+ 
//                                  " AND KD_DATI2='"+kddati+"' "+ 
//                                  " AND KD_KECAMATAN='"+kecamatan+"' " +
//                                  " AND KD_KELURAHAN='"+kelurahan+"'" +
//                                  " AND KD_BLOK='"+kdBlock+"'" +
//                                  " AND NO_URUT='"+noUrut+"'" +
//                                  " AND KD_JNS_OP='"+kdjnsop+"'" +
//                                  " AND THN_PAJAK_SPPT='"+thnPajk+"'";
        try {
            iResult = DBHandler.execUpdate(stSql);
        } catch (DBException e) {
            e.printStackTrace();
        } finally {
            DBResultSet.close(dbrs);
        }
        return iResult;
    }
	
	public static Vector listPerBulan(int limitStart, int recordToGet, String whereClause, String order, String groupBy) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
			String sql = "";
			if (AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				sql = "SELECT SUM("+fieldNames[FLD_BPHTBSUDAHBAYAR]+") "+fieldNames[FLD_BPHTBSUDAHBAYAR]+", "
						+ "TO_DATE(TO_CHAR("+fieldNames[FLD_TGLPEMBAYARAN]+",'Month YYYY'),'MM YYYY') "+fieldNames[FLD_TGLPEMBAYARAN]+" FROM "+TBL_PAYMENTBPHTBIPROTAX;
				if (whereClause != null && whereClause.length() > 0) {
					sql = sql + " WHERE " + whereClause;
				}

				if(groupBy != null && groupBy.length() > 0){
					sql = sql + " GROUP BY "+ groupBy;
				}
				if (order != null && order.length() > 0) {
					sql = sql + " ORDER BY " + order;
				}
				if (limitStart == 0 && recordToGet == 0) {
					sql = sql + "";
				} else {
					sql = sql + " LIMIT " + limitStart + "," + recordToGet;
				}
			} else {
				sql = "SELECT SUM("+fieldNames[FLD_BPHTBSUDAHBAYAR]+") "+fieldNames[FLD_BPHTBSUDAHBAYAR]+", "
						+ "CONCAT(convert(varchar(7), TGL_PEMBAYARAN_SPPT, 126),'-01') AS "+fieldNames[FLD_TGLPEMBAYARAN]+" FROM "+TBL_PAYMENTBPHTBIPROTAX;
				if (whereClause != null && whereClause.length() > 0) {
					sql = sql + " WHERE " + whereClause;
				}

				if(groupBy != null && groupBy.length() > 0){
					sql = sql + " GROUP BY "+ groupBy;
				}
				if (order != null && order.length() > 0) {
					sql = sql + " ORDER BY " + order;
				}
				if (limitStart == 0 && recordToGet == 0) {
					sql = sql + "";
				} else {
					sql = sql + " LIMIT " + limitStart + "," + recordToGet;
				}	
			}
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                PaymentBphtbIprotax entPaymentBphtb = new PaymentBphtbIprotax();
                entPaymentBphtb.setTglPembayaran(rs.getDate(2));
				entPaymentBphtb.setBphtbSdhBayar(rs.getDouble(1));
                lists.add(entPaymentBphtb);
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
	
	public static Vector listPaymentBphtbDaily(int limitStart, int recordToGet, String whereClause, String order, String group) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        try {
			String sql = "";
			if (AppSetting.SQL_VERSION == AppSetting.DBSVR_ORACLE){
				sql = "SELECT TO_DATE(TO_CHAR(TGL_PEMBAYARAN,'DD Month YYYY'),'DD MM YYYY') TGL_PEMBAYARAN, SUM(BPHTB_SDH_DIBAYAR) AS BPHTB_SDH_DIBAYAR"
						+ " FROM "+TBL_PAYMENTBPHTBIPROTAX;
				if (whereClause != null && whereClause.length() > 0) {
					sql = sql + " WHERE " + whereClause;
				}
				if (group != null && group.length() > 0) {
					sql = sql + " GROUP BY " + group;
				}
				if (order != null && order.length() > 0) {
					sql = sql + " ORDER BY " + order;
				}
				if (limitStart == 0 && recordToGet == 0) {
					sql = sql + "";
				} else {
					sql = sql + " LIMIT " + limitStart + "," + recordToGet;
				}
			} else {
				sql = "SELECT CAST(TGL_PEMBAYARAN AS DATE) TGL_PEMBAYARAN, SUM(BPHTB_SDH_DIBAYAR) AS BPHTB_SDH_DIBAYAR"
						+ " FROM VIEW_PEMBAYARAN_PBB";
				if (whereClause != null && whereClause.length() > 0) {
					sql = sql + " WHERE " + whereClause;
				}
				if (group != null && group.length() > 0) {
					sql = sql + " GROUP BY " + group;
				}
				if (order != null && order.length() > 0) {
					sql = sql + " ORDER BY " + order;
				}
				if (limitStart == 0 && recordToGet == 0) {
					sql = sql + "";
				} else {
					sql = sql + " LIMIT " + limitStart + "," + recordToGet;
				}
			}
            dbrs = DBHandler.execQueryResult(sql);
			System.out.println(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                PaymentBphtbIprotax entPaymentBphtb = new PaymentBphtbIprotax();
                entPaymentBphtb.setTglPembayaran(rs.getDate(1));
				entPaymentBphtb.setBphtbSdhBayar(rs.getDouble(2));
				lists.add(entPaymentBphtb);
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
    
}