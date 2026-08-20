/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.report;

import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.DebiturCompare;
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
public class SessDebitur {
    public static Vector listPerubanKodeJenisDebitur(int limitStart, int recordToGet, String whereClause, String order, long periodeId) {
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
            String sql = ""+ 
                " SELECT a.CIF AS PREVCIF, a.KODE_JENIS_NSB AS PREV_KODE_JENIS_NSB, b.CIF AS CIF, b.KODE_JENIS_NSB AS KODE_JENIS_NSB FROM (" +
                " SELECT a.CIF, a.KODE_JENIS_NSB" +
                " FROM dslik_debitur AS a WHERE a.periode_id = '"+prevPeriodeId+"'" +
                " )AS a " +
                " INNER JOIN dslik_debitur AS b ON a.CIF=b.CIF" +
                " WHERE b.periode_id = '"+periodeId+"' AND a.KODE_JENIS_NSB!=b.KODE_JENIS_NSB";
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
                
                DebiturCompare cebiturCompare = new DebiturCompare();
                
                cebiturCompare.setCif(rs.getString("CIF"));
                cebiturCompare.setKodeJenisNsb(rs.getString("KODE_JENIS_NSB"));
                
                cebiturCompare.setPrevCif(rs.getString("PREVCIF"));
                cebiturCompare.setPrevJenisNsb(rs.getString("PREV_KODE_JENIS_NSB"));
                
                lists.add(cebiturCompare);
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
