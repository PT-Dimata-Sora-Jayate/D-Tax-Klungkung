/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.session;

import com.dimata.dtaxintegration.entity.payment.PaymentPhr;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhr;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.wpupload.entity.compareesptpd.CompareEsptpd;
import com.dimata.wpupload.entity.wpuser.PstAppUserWP;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Ardiadi
 */
public class SessHistoryPajakOnline {
    public static Vector listJoin(int limitStart, int recordToGet, String whereClause, String order) {
	Vector lists = new Vector();
	DBResultSet dbrs = null;
	try {
	    String sql = "SELECT "+ PstPaymentPhr.TBL_PAYMENTPHR+"."+PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]+","
		    + PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_NPWD]+","
		    + PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_MASAPAJAK]+","
		    + PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TAHUNPAJAK]+","
		    + PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_POKOK]+","
		    + PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_DENDA]+","
		    + PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_JUMLAHBAYAR]+" "
		    + "FROM "+PstPaymentPhr.TBL_PAYMENTPHR+" "
		    + "INNER JOIN "+ PstAppUserWP.TBL_APPUSERWP+" "
		    + "ON "+PstPaymentPhr.TBL_PAYMENTPHR+"."+ PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_NPWD]+"="+PstAppUserWP.TBL_APPUSERWP+"."+PstAppUserWP.fieldNames[PstAppUserWP.FLD_NPWPD];
	    
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
		PaymentPhr entPaymentPhr = new PaymentPhr();
		resultToObjectJoin(rs, entPaymentPhr);
		lists.add(entPaymentPhr);
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
    
    public static void resultToObjectJoin(ResultSet rs, PaymentPhr paymentPhr) {
	try {

		 paymentPhr.setTanggal(rs.getDate(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TANGGAL]));
		 paymentPhr.setNpwpd(rs.getString(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_NPWD]));
		 paymentPhr.setMasaPajak(rs.getString(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_MASAPAJAK]));
		 paymentPhr.setTahunPajak(rs.getString(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_TAHUNPAJAK]));
		 paymentPhr.setPokok(rs.getDouble(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_POKOK]));
		 paymentPhr.setDenda(rs.getDouble(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_DENDA]));
		 paymentPhr.setJumlahBayar(rs.getDouble(PstPaymentPhr.fieldNames[PstPaymentPhr.FLD_JUMLAHBAYAR]));
	} catch (Exception e) {
	}
   }
}
