/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.report;

import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import static com.dimata.dslik.entity.kredit.PstKredit.TBL_KREDIT;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.util.Formater;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author dimata005
 */
public class SessKredit {
    
    public static Vector listJoinKredit(int limitStart, int recordToGet, String whereClause, String order, long periodeId) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        //cari prev periode sebelumnya
        long prevPeriodeId=0;
            try{
                Periode periode = new Periode();
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periode.getTglAwal().setMonth(periode.getTglAwal().getMonth()-1);
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periode = (Periode) listPeriodex.get(0);
                            prevPeriodeId = periode.getOID();
                        }
                     }
                }
            }catch(Exception ex){
            }
        
        try {
            String sql = ""
                + " SELECT * FROM dslik_kredit WHERE periode_id='"+prevPeriodeId+"' AND KODE_KONDISI != '02' " 
                + " AND (NO_REKENING,CIF) NOT IN ( " 
                + " SELECT NO_REKENING,CIF FROM dslik_kredit WHERE periode_id='"+periodeId+"' " 
                + " )";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                Kredit entKredit = new Kredit();
                entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
                entKredit.setCif(rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]));
                lists.add(entKredit);
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
    
    
    public static Vector listJoinBankGaransi(int limitStart, int recordToGet, String whereClause, String order, long periodeId) {
        Vector lists = new Vector();
        DBResultSet dbrs = null;
        //cari prev periode sebelumnya
        long prevPeriodeId=0;
            try{
                Periode periode = new Periode();
                Vector listPeriode = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_PERIODE_ID]+"='"+periodeId+"'", "");
                if(listPeriode != null){
                    periode = (Periode) listPeriode.get(0);
                    periode.getTglAwal().setMonth(periode.getTglAwal().getMonth()-1);
                    Vector listPeriodex = PstPeriode.list(0, 1, ""+PstPeriode.fieldNames[PstPeriode.FLD_TGL_AWAL]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd") +"' AND '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"'", "");
                    if(listPeriodex != null){
                        if(listPeriodex.size()>0){
                            periode = (Periode) listPeriodex.get(0);
                            prevPeriodeId = periode.getOID();
                        }
                     }
                }
            }catch(Exception ex){
            }
        
        try {
            String sql = ""
                + " SELECT * FROM dslik_bank_garansi WHERE periode_id='"+prevPeriodeId+"' AND KODE_KONDISI != '02' " 
                + " AND (NO_REKENING,CIF) NOT IN ( " 
                + " SELECT NO_REKENING,CIF FROM dslik_bank_garansi WHERE periode_id='"+periodeId+"' " 
                + " )";
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " AND " + whereClause;
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
                BankGaransi bankGaransi = new BankGaransi();
                bankGaransi.setNoRekening(rs.getString(PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]));
                bankGaransi.setCif(rs.getString(PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]));
                lists.add(bankGaransi);
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
    
    public static String checkCifKredir(long periodeId, String noRekening) {
        DBResultSet dbrs = null;
        String result = "";
        try {
            String sql = "SELECT DISTINCT * FROM " + PstKredit.TBL_KREDIT + " WHERE "
                    + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + " = '" +periodeId+"' AND "
                    + PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+"='"+noRekening+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
    
    public static double checkCountCifKredir(long periodeId, String cif) {
        DBResultSet dbrs = null;
        double result = 0;
        try {
            String sql = "SELECT COUNT(NO_REKENING) AS CIF FROM " + PstKredit.TBL_KREDIT + " WHERE "
                    + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + " = '" +periodeId+"' AND "
                    + PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_CIF]);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
    
    public static double checkCountCifBankGaransi(long periodeId, String cif) {
        DBResultSet dbrs = null;
        double result = 0;
        try {
            String sql = "SELECT COUNT(NO_REKENING) AS CIF FROM " + PstBankGaransi.TBL_BANK_GARANSI + " WHERE "
                    + PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID] + " = '" +periodeId+"' AND "
                    + PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_CIF]);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
    
      public static String checkNorekeningKredit(long periodeId, String cif) {
        DBResultSet dbrs = null;
        String result = "";
        try {
            String sql = "SELECT NO_REKENING FROM " + PstKredit.TBL_KREDIT + " WHERE "
                    + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + " = '" +periodeId+"' AND "
                    + PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            int count=0;
            while (rs.next()) {
                if(count==0){
                    result = rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]);
                }else{
                    result = result +"<br>"+rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]);
                }
                count=count+1;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
      
      public static String checkNorekeningBankGaransi(long periodeId, String cif) {
        DBResultSet dbrs = null;
        String result = "";
        try {
            String sql = "SELECT NO_REKENING FROM " + PstKredit.TBL_KREDIT + " WHERE "
                    + PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID] + " = '" +periodeId+"' AND "
                    + PstKredit.fieldNames[PstKredit.FLD_CIF]+"='"+cif+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            int count=0;
            while (rs.next()) {
                if(count==0){
                    result = rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]);
                }else{
                    result = result +"<br>"+rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]);
                }
                count=count+1;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }  
      
    
    public static String checkCifBankGaransi(long periodeId, String noRekening) {
        DBResultSet dbrs = null;
        String result = "";
        try {
            String sql = "SELECT DISTINCT * FROM " + PstBankGaransi.TBL_BANK_GARANSI + " WHERE "
                    + PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID] + " = '" +periodeId+"' AND "
                    + PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+"='"+noRekening+"'";
            dbrs = DBHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getString(PstBankGaransi.fieldNames[PstBankGaransi.FLD_CIF]);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            DBResultSet.close(dbrs);
            return result;
        }
    }
    
}
