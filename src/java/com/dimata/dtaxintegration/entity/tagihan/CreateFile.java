/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.entity.tagihan;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dimata.dtaxintegration.entity.inquery.BphtbIprotax;
import com.dimata.dtaxintegration.entity.inquery.Pbb;
import com.dimata.dtaxintegration.entity.inquery.Simpatda;
import com.dimata.dtaxintegration.entity.loghistory.LogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.loghistory.PstLogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.payment.PaymentPbb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbb;
import com.dimata.dtaxintegration.session.ConvertAngkaToHuruf;
import com.dimata.dtaxintegration.session.DTaxManagerBphtb;
import com.dimata.dtaxintegration.session.DTaxManagerPbb;
import com.dimata.dtaxintegration.session.DTaxManagerPhr;
import com.dimata.dtaxintegration.session.SessPbbIprotax;
import com.dimata.dtaxintegration.session.SessSimpatda;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import static com.dimata.qdep.form.FRMHandler.SYSTEM_DECIMAL_SYMBOL;
import static com.dimata.qdep.form.FRMHandler.SYSTEM_DIGIT_GROUP;
import com.dimata.util.Diskon;
import java.io.FileNotFoundException;
import java.util.Date;
import java.io.PrintWriter;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateFile {

    /**
     *
     * @param createDate
     * @param patch
     * @param type -> 0:cdr lama alamat email adalah alamat email hotel && 1 :
     * cdr baru dengan alamat email alamat email customer
     * @return
     */
    public static String sentPbb(FileSent fileSent) {

        PrintWriter pw = null;
        String patchFle = "";
        DTaxManagerPbb dTaxManagerPbbx = new DTaxManagerPbb();
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = fileSent.getLocation() + System.getProperty("file.separator") + AppSetting.INSTANSI_PBB + ".txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Vector vPBB = new Vector();
            String whereClause = "";
            int startYear = 0;
            int endYear = 0;

            //proses delete semua transaksi di log history terlebih dahuluh
            String whereDelete = "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI] + "='" + AppSetting.INSTANSI_PBB + "'";
            SessSimpatda.deleteExc(whereDelete);

            if (!(fileSent.getTahunStart().equals("") && fileSent.equals(""))) {

                whereClause = " WHERE TAHUN BETWEEN " + fileSent.getTahunStart() + ""
                        + " AND " + fileSent.getTahunEnd() + "";

                startYear = Integer.parseInt(fileSent.getTahunStart());
                endYear = Integer.parseInt(fileSent.getTahunEnd());
            }

            int count = SessSimpatda.countPBB(whereClause);
            DTaxManagerPbb.countTotal = count;

            pw.print("id" + "\t");
            pw.print("nama" + "\t");
            pw.print("jum_tagihan" + "\t");
            pw.print("instansi" + "\t");
            pw.print("NPWP" + "\t");
            pw.print("Alamat WP" + "\t");
            pw.print("Letak Objek Pajak" + "\t");
            pw.print("Tahun" + "\t");
            pw.print("Tgl Jatuh Tempo" + "\t");
            pw.print("Luas Bumi" + "\t");
            pw.print("Luas Bangunan" + "\t");
            pw.print("NJOP Bumi" + "\t");
            pw.print("NJOP Bangunan" + "\t");
            pw.print("NJOPTKP" + "\t");
            pw.print("Denda" + "\t");
            pw.print("Formula" + "\t");
            pw.print("Terbilang" + "\t");
            pw.println();

            if (startYear != endYear) {

                for (int k = startYear; k <= endYear; k++) {

                    whereClause = " WHERE TAHUN BETWEEN " + k + "" + " AND " + k + "";

                    //vPBB = SessSimpatda.getListPBBThread(whereClause);
                    DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Proses file tahun " + k;
                    DBResultSet dbrs = null;
                    // if(vPBB.size()>0){
                    String nop = "";
                    int counterRs = 0;
                    try {

                        String sql = "SELECT * FROM VIEW_PBB";

                        if (!whereClause.equals("")) {
                            sql = sql + whereClause;
                        }

                        dbrs = DBHandler.execQueryResultNew(sql);

                        ResultSet rs = dbrs.getResultSet();

                        while (rs.next()) {

                            Pbb pbb = new Pbb();
                            counterRs = counterRs + 1;
                            try {

                                pbb.setId(rs.getString("NOP"));
                                nop = pbb.getId();
                                pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                                pbb.setNama(rs.getString("NAMA"));//4//sNama
                                pbb.setInstansi(rs.getString("INSTANSI"));
                                pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                                pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                                pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                                pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                                pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo
                                pbb.setLuasBumi(rs.getString("LUAS_BUMI_SPPT"));//12//luas bangunan
                                pbb.setLuasBangunan(rs.getString("LUAS_BNG_SPPT"));//13
                                pbb.setnJOPBumi(rs.getString("NJOP_BUMI_SPPT"));//14
                                pbb.setnJOPBangunan(rs.getString("NJOP_BNG_SPPT"));
                                pbb.setnJOPTKP(rs.getString("NJOPTKP_SPPT"));//NJOPTKP_SPPT
                                pbb.setDenda(rs.getString("DENDA"));
                                pbb.setPokok(rs.getString("POKOK"));
                                pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");

                                pbb.setTerbilang("");

                                //result.add(pbb);
                                pw.print(pbb.getId() + "\t");
                                pw.print(pbb.getNama() + "\t");
                                pw.print(pbb.getJumlahTagihan() + "\t");
                                pw.print("" + AppSetting.INSTANSI_PBB + "\t");
                                pw.print(pbb.getNpwpd() + "\t");
                                pw.print(pbb.getAlamat() + "\t");
                                pw.print(pbb.getLetakObjectPajak() + "\t");
                                pw.print(pbb.getTahun() + "\t");
                                pw.print(pbb.getTglJatuhTempo() + "\t");
                                pw.print(pbb.getLuasBumi() + "\t");
                                pw.print(pbb.getLuasBangunan() + "\t");
                                pw.print(pbb.getnJOPBumi() + "\t");
                                pw.print(pbb.getnJOPBangunan() + "\t");
                                pw.print(pbb.getnJOPTKP() + "\t");
                                pw.print(pbb.getDenda() + "\t");
                                pw.print(pbb.getFormula() + "\t");

                                if (!pbb.getJumlahTagihan().equals("")) {
                                    double total = Double.valueOf(pbb.getJumlahTagihan());
                                    long mylong = (long) (total);
                                    ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                                    pbb.setTerbilang(convert.getText());

                                    pw.print(pbb.getTerbilang() + "\t");
                                } else {
                                    pw.print(pbb.getTerbilang() + "\t");
                                }

                                pw.println();

                                //insert history
                                LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                logHistory.setId(pbb.getId());
                                logHistory.setNama(pbb.getNama());
                                if (!pbb.getJumlahTagihan().equals("")) {
                                    logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                                } else {
                                    logHistory.setJumlahPajak(0);
                                }
                                logHistory.setTahun(pbb.getTahun());
                                logHistory.setBulan("");
                                logHistory.setInstansi(pbb.getInstansi());

                                if (!pbb.getDenda().equals("")) {
                                    logHistory.setDenda(Double.valueOf(pbb.getDenda()));
                                } else {
                                    logHistory.setDenda(0);
                                }

                                logHistory.setAlamat(pbb.getAlamat());
                                logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                                if (!pbb.getLuasBangunan().equals("")) {
                                    logHistory.setLuasBangunan(Double.valueOf(pbb.getLuasBangunan()));
                                } else {
                                    logHistory.setLuasBangunan(0);
                                }

                                if (!pbb.getLuasBumi().equals("")) {
                                    logHistory.setLuasBumi(Double.valueOf(pbb.getLuasBumi()));
                                } else {
                                    logHistory.setLuasBumi(0);
                                }

                                if (!pbb.getnJOPBangunan().equals("")) {
                                    logHistory.setnJOPBangunan(Double.valueOf(pbb.getnJOPBangunan()));
                                } else {
                                    logHistory.setnJOPBangunan(0);
                                }

                                if (!pbb.getnJOPBumi().equals("")) {
                                    logHistory.setnJOPBumi(Double.valueOf(pbb.getnJOPBumi()));
                                } else {
                                    logHistory.setnJOPBumi(0);
                                }

                                if (!pbb.getnJOPTKP().equals("")) {
                                    logHistory.setnJOPTKP(Double.valueOf(pbb.getnJOPTKP()));
                                } else {
                                    logHistory.setnJOPTKP(0);
                                }

                                try {

                                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                                } catch (Exception es) {

                                    DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Eror Proses insert log history NOP : " + logHistory.getId();

                                }

                                DTaxManagerPbb.countQuery = DTaxManagerPbb.countQuery + 1;

                                DTaxManagerPbb.count = DTaxManagerPbb.count + 1;

                            } catch (Exception es) {

                                DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Eror Proses insert log history NOP : " + pbb.getNop();

                            }
                        }
                        rs.close();
                        DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Proses file tahun " + k + " selesai";
                    } catch (Exception e) {
                        System.out.println("Exc in getListAP >>> " + e.toString());
                        DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + nop + " no urut: " + counterRs + " Eror create file " + e.toString();
                    } finally {
                        DBResultSet.close(dbrs);
                    }
                }

            } else {

                vPBB = SessSimpatda.getListPBBThread(whereClause);

                if (vPBB.size() > 0) {
                    for (int i = 0; i < vPBB.size(); i++) {
                        Pbb pbb = (Pbb) vPBB.get(i);

                        if (!DTaxManagerPbb.running) {
                            return patchFle;
                        }
                        //pw.println(""+pbb.getId()+" "+pbb.getNama()+" "+pbb.getJumlahTagihan()+" PBB_GIANYAR"+" "+pbb.getNpwpd()+" "+pbb.getAlamat()+" "+pbb.getLetakObjectPajak()+" "+pbb.getTahun()+" "+pbb.getTglJatuhTempo()+" 
                        //"+pbb.getLuasBumi()+" "+pbb.getLuasBangunan()+" "+pbb.getnJOPBumi()+" "+pbb.getnJOPBangunan()+" "+pbb.getnJOPTKP()+" "+pbb.getDenda()+" "+pbb.getFormula()+" "+pbb.getTerbilang()+" ");                           
                        pw.print(pbb.getId() + "\t");
                        pw.print(pbb.getNama() + "\t");
                        pw.print(pbb.getJumlahTagihan() + "\t");
                        pw.print("PBB_GIANYAR" + "\t");
                        pw.print(pbb.getNpwpd() + "\t");
                        pw.print(pbb.getAlamat() + "\t");
                        pw.print(pbb.getLetakObjectPajak() + "\t");
                        pw.print(pbb.getTahun() + "\t");
                        pw.print(pbb.getTglJatuhTempo() + "\t");
                        pw.print(pbb.getLuasBumi() + "\t");
                        pw.print(pbb.getLuasBangunan() + "\t");
                        pw.print(pbb.getnJOPBumi() + "\t");
                        pw.print(pbb.getnJOPBangunan() + "\t");
                        pw.print(pbb.getnJOPTKP() + "\t");
                        pw.print(pbb.getDenda() + "\t");
                        pw.print(pbb.getFormula() + "\t");
                        //pw.print(pbb.getTerbilang()+"\t");
                        if (!pbb.getJumlahTagihan().equals("")) {
                            double total = Double.valueOf(pbb.getJumlahTagihan());
                            long mylong = (long) (total);
                            ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                            pbb.setTerbilang(convert.getText());

                            pw.print(pbb.getTerbilang() + "\t");
                        } else {
                            pw.print(pbb.getTerbilang() + "\t");
                        }

                        pw.println();

                        //insert history
                        LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                        logHistory.setId(pbb.getId());
                        logHistory.setNama(pbb.getNama());
                        if (!pbb.getJumlahTagihan().equals("")) {
                            logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                        } else {
                            logHistory.setJumlahPajak(0);
                        }
                        logHistory.setTahun(pbb.getTahun());
                        logHistory.setBulan("");
                        logHistory.setInstansi(pbb.getInstansi());
                        if (!pbb.getDenda().equals("")) {
                            logHistory.setDenda(Double.valueOf(pbb.getDenda()));
                        } else {
                            logHistory.setDenda(0);
                        }

                        logHistory.setAlamat(pbb.getAlamat());
                        logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                        if (!pbb.getLuasBangunan().equals("")) {
                            logHistory.setLuasBangunan(Double.valueOf(pbb.getLuasBangunan()));
                        } else {
                            logHistory.setLuasBangunan(0);
                        }

                        if (!pbb.getLuasBumi().equals("")) {
                            logHistory.setLuasBumi(Double.valueOf(pbb.getLuasBumi()));
                        } else {
                            logHistory.setLuasBumi(0);
                        }

                        if (!pbb.getnJOPBangunan().equals("")) {
                            logHistory.setnJOPBangunan(Double.valueOf(pbb.getnJOPBangunan()));
                        } else {
                            logHistory.setnJOPBangunan(0);
                        }

                        if (!pbb.getnJOPBumi().equals("")) {
                            logHistory.setnJOPBumi(Double.valueOf(pbb.getnJOPBumi()));
                        } else {
                            logHistory.setnJOPBumi(0);
                        }

                        if (!pbb.getnJOPTKP().equals("")) {
                            logHistory.setnJOPTKP(Double.valueOf(pbb.getnJOPTKP()));
                        } else {
                            logHistory.setnJOPTKP(0);
                        }

                        long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                        DTaxManagerPbb.count = DTaxManagerPbb.count + 1;
                    }
                }
            }

        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();

        return patchFle;
    }

    public static String sentPbbIpRotax(FileSent fileSent) {

        PrintWriter pw = null;
        String patchFle = "";
        DTaxManagerPbb dTaxManagerPbbx = new DTaxManagerPbb();
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = fileSent.getLocation() + System.getProperty("file.separator") + fileSent.getFileName() + ".txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            double totalTagihan = 0;
            Vector vPBB = new Vector();
            String whereClause = "";//" WHERE JUMLAH_TAGIHAN > 0"; 
            int startYear = 0;
            int endYear = 0;

            //proses delete semua transaksi di log history terlebih dahuluh
            //String whereDelete = ""+PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI]+"='"+AppSetting.INSTANSI_PBB+"'";
            //SessSimpatda.deleteExc(whereDelete);
            if (!(fileSent.getTahunStart().equals("") && fileSent.equals(""))) {

                //whereClause = " WHERE TAHUN BETWEEN "+fileSent.getTahunStart()+""
                //+" AND "+fileSent.getTahunEnd()+" AND JUMLAH_TAGIHAN > 0 ";
                whereClause = " WHERE TAHUN BETWEEN " + fileSent.getTahunStart() + ""
                        + " AND " + fileSent.getTahunEnd() + " ";

                startYear = Integer.parseInt(fileSent.getTahunStart());
                endYear = Integer.parseInt(fileSent.getTahunEnd());
            }
                        
             //preload untuk mengambil tunggakan pada tagihan tahun 2022 hingga 2026
            HashSet<String> setTunggakan = new HashSet<>();
            String sqlPenyesuaian = "SELECT NOP " +
                                    "FROM view_pbb " +
                                    "GROUP BY NOP " +
                                    "HAVING SUM(CASE WHEN THN_PAJAK_SPPT BETWEEN 2022 AND 2026 THEN 1 ELSE 0 END) = 0";
            DBResultSet dbrs2 = null;
            try {
                dbrs2 = DBHandler.execQueryResultNew(sqlPenyesuaian);
                ResultSet rs2 = dbrs2.getResultSet();
                while (rs2.next()) {
                    setTunggakan.add(rs2.getString("NOP"));
                }
                rs2.close();
            } finally {
                DBResultSet.close(dbrs2);
            }
            //end of preload tunggakan tahun 2022 hingga 2026
            
            int count = SessSimpatda.countPBB(whereClause);
            DTaxManagerPbb.countTotal = count;

            pw.print("id" + "\t");
            pw.print("nama" + "\t");
            pw.print("jum_tagihan" + "\t");
            pw.print("instansi" + "\t");
            pw.print("NPWP" + "\t");
            pw.print("Alamat WP" + "\t");
            pw.print("Letak Objek Pajak" + "\t");
            pw.print("Tahun" + "\t");
            pw.print("Tgl Jatuh Tempo" + "\t");
            pw.print("Luas Bumi" + "\t");
            pw.print("Luas Bangunan" + "\t");
            pw.print("NJOP Bumi" + "\t");
            pw.print("NJOP Bangunan" + "\t");
            pw.print("NJOPTKP" + "\t");
            pw.print("Denda" + "\t");
            pw.print("Formula" + "\t");
            pw.print("Terbilang" + "\t");
            pw.println();

            if (startYear != endYear) {

                for (int k = startYear; k <= endYear; k++) {

                    //whereClause = " WHERE TAHUN BETWEEN "+k+""+" AND "+k+" AND JUMLAH_TAGIHAN > 0";
                    whereClause = " WHERE TAHUN BETWEEN " + k + "" + " AND " + k;

                    //vPBB = SessSimpatda.getListPBBThread(whereClause);
                    DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Proses file tahun " + k;
                    DBResultSet dbrs = null;
                    // if(vPBB.size()>0){
                    String nop = "";
                    int counterRs = 0;
                    try {

                        String sql = "SELECT * FROM VIEW_PBB";

                        if (!whereClause.equals("")) {
                            sql = sql + whereClause;
                        }

                        dbrs = DBHandler.execQueryResultNew(sql);
                        ResultSet rs = dbrs.getResultSet();
                        double totTagihan = 0;
                        double totDenda = 0;
                        while (rs.next()) {

                            Pbb pbb = new Pbb();
                            counterRs = counterRs + 1;

                            try {

                                pbb.setId(rs.getString("NOP"));
                                nop = pbb.getId();
                                
                                
                            
                                if(setTunggakan.contains(nop)){
                                    continue;
                                }
                                
                                pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                                pbb.setNama(rs.getString("NAMA"));//4//sNama
                                pbb.setInstansi(rs.getString("INSTANSI"));
                                pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                                pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                                pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                                pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                                pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo

//                                        pbb.setLuasBumi(rs.getString("LUAS_BUMI_SPPT"));//12//luas bangunan
//                                        pbb.setLuasBangunan(rs.getString("LUAS_BNG_SPPT"));//13
//                                        pbb.setnJOPBumi(rs.getString("NJOP_BUMI_SPPT"));//14
//                                        pbb.setnJOPBangunan(rs.getString("NJOP_BNG_SPPT"));
//                                        pbb.setnJOPTKP(rs.getString("NJOPTKP_SPPT"));//NJOPTKP_SPPT
                                String sLuasBumiSppt = rs.getString("LUAS_BUMI_SPPT");
                                double luasBumiSppt = Double.valueOf(sLuasBumiSppt);
//                                pbb.setLuasBumi(Formater.formatNumber(luasBumiSppt, "#,###,##0"));//12//luas bangunan
                                pbb.setLuasBumi(Formater.formatNumberNew(luasBumiSppt, "#,##0"));//12//luas bangunan

                                String sLuasBgnSppt = rs.getString("LUAS_BNG_SPPT");
                                double luasBgnSppt = Double.valueOf(sLuasBgnSppt);
//                                pbb.setLuasBangunan(Formater.formatNumber(luasBgnSppt, "#,###,##0"));//13
                                pbb.setLuasBangunan(Formater.formatNumberNew(luasBgnSppt, "#,##0"));//13

                                String sNjopBumi = rs.getString("NJOP_BUMI_SPPT");
                                double NjopBumi = Double.valueOf(sNjopBumi);                              
                                //new
                                String NJOPBUMI = Formater.formatNumberNew(NjopBumi, "#,##0");
//                                pbb.setnJOPBumi(Formater.formatNumber(NjopBumi, "#,###,##0"));//14
                                pbb.setnJOPBumi(Formater.formatNumberNew(NjopBumi, "#,##0"));//14

                                String sNjopBgn = rs.getString("NJOP_BNG_SPPT");
                                double NjopBgn = Double.valueOf(sNjopBgn);
//                                pbb.setnJOPBangunan(Formater.formatNumber(NjopBgn, "#,###,##0"));
                                pbb.setnJOPBangunan(Formater.formatNumberNew(NjopBgn, "#,##0"));

                                String snJOPTKP = rs.getString("NJOPTKP_SPPT");
                                double nJOPTKP = Double.valueOf(snJOPTKP);
//                                pbb.setnJOPTKP(Formater.formatNumber(nJOPTKP, "#,###,##0"));//NJOPTKP_SPPT
                                pbb.setnJOPTKP(Formater.formatNumberNew(nJOPTKP, "#,##0"));//NJOPTKP_SPPT

                                pbb.setNjkpSppt(rs.getDouble("NJKP_SPPT"));
                                pbb.setTarifSppt(rs.getDouble("TARIF_SPPT"));
                                
                                double dendaPBB = Double.valueOf(rs.getString("DENDA"));
                                pbb.setDenda(Formater.formatNumberNew(dendaPBB, "#,##0"));
                                
                                double pokokPBB = Double.valueOf(rs.getString("POKOK"));
                                pbb.setPokok(Formater.formatNumberNew(pokokPBB, "#,##0"));
                                //pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");
                                pbb.setTerbilang("");

                                //proses perhitungan jumlah tagihan dan denda
                                double totPambayaran = Double.valueOf(pbb.getJumlahTagihan());//0;//SessPbbIprotax.PerhitunganPbbYangHarusDibayar(pbb.getId(), pbb.getTahun(), pbb.getJumlahTagihan());
                                totTagihan = totTagihan + totPambayaran;

                                /*count denda adm sppt*/
//                                double denda = Math.ceil(Double.valueOf(pbb.getDenda()));//SessPbbIprotax.PerhitunganDenda(pbb.getId(),  pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(),totPambayaran);
                                double denda = Math.ceil(dendaPBB);//SessPbbIprotax.PerhitunganDenda(pbb.getId(),  pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(),totPambayaran);
                                totDenda = totDenda + denda;
                                /*total yang harus dibayarkan*/
                                double ygHarusDibayar = totPambayaran + denda;
                                try {
                                    pbb.setJumlahTagihan(String.format("%.0f", ygHarusDibayar));
                                } catch (Exception ex) {
                                }
                                try {
                                    pbb.setDenda(Formater.formatNumber(denda, "#,###,##0"));
                                } catch (Exception ex) {
                                }
                                pbb.setFormula("(" + pbb.getnJOPBumi() + " + " + pbb.getnJOPBangunan() + " - " + pbb.getnJOPTKP() + ") X " + (pbb.getTarifSppt() * (pbb.getNjkpSppt() / 100)) + " % + " + pbb.getDenda());

                                //result.add(pbb);
                                pw.print(pbb.getId() + "\t");
                                pw.print(pbb.getNama() + "\t");
                                pw.print(pbb.getJumlahTagihan() + "\t");
                                pw.print("" + AppSetting.INSTANSI_PBB + "\t");
                                pw.print(pbb.getNpwpd() + "\t");
                                pw.print(pbb.getAlamat() + "\t");
                                pw.print(pbb.getLetakObjectPajak() + "\t");
                                pw.print(pbb.getTahun() + "\t");
                                pw.print(pbb.getTglJatuhTempo() + "\t");
                                pw.print(pbb.getLuasBumi() + "\t");
                                pw.print(pbb.getLuasBangunan() + "\t");
                                pw.print(pbb.getnJOPBumi() + "\t");
                                pw.print(pbb.getnJOPBangunan() + "\t");
                                pw.print(pbb.getnJOPTKP() + "\t");
                                pw.print(pbb.getDenda() + "\t");
                                pw.print(pbb.getFormula() + "\t");

                                if (!pbb.getJumlahTagihan().equals("")) {
                                    double total = Double.valueOf(pbb.getJumlahTagihan());
                                    long mylong = (long) (total);
                                    ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                                    pbb.setTerbilang(convert.getText() + " rupiah");
                                    pw.print(pbb.getTerbilang() + "\t");
                                } else {
                                    pw.print(pbb.getTerbilang() + "\t");
                                }

                                pw.println();

                                //insert history
                                /*LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                         logHistory.setId(pbb.getId());
                                         logHistory.setNama(pbb.getNama());
                                         if(!pbb.getJumlahTagihan().equals("")){
                                             logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                                         }else{
                                             logHistory.setJumlahPajak(0);
                                         }
                                         logHistory.setTahun(pbb.getTahun());
                                         logHistory.setBulan("");
                                         logHistory.setInstansi(pbb.getInstansi());

                                         if(!pbb.getDenda().equals("")){
                                             logHistory.setDenda(denda);
                                         }else{
                                             logHistory.setDenda(0);
                                         }

                                         logHistory.setAlamat(pbb.getAlamat());
                                         logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                                         if(!pbb.getLuasBangunan().equals("")){
                                             logHistory.setLuasBangunan(luasBgnSppt);
                                         }else{
                                             logHistory.setLuasBangunan(0);
                                         }

                                         if(!pbb.getLuasBumi().equals("")){
                                             logHistory.setLuasBumi(luasBumiSppt);
                                         }else{
                                             logHistory.setLuasBumi(0);
                                         }

                                         if(!pbb.getnJOPBangunan().equals("")){
                                             logHistory.setnJOPBangunan(NjopBgn);
                                         }else{
                                             logHistory.setnJOPBangunan(0);
                                         }

                                         if(!pbb.getnJOPBumi().equals("")){
                                             logHistory.setnJOPBumi(NjopBumi);
                                         }else{
                                             logHistory.setnJOPBumi(0);
                                         }

                                         if(!pbb.getnJOPTKP().equals("")){
                                             logHistory.setnJOPTKP(nJOPTKP);
                                         }else{
                                             logHistory.setnJOPTKP(0);
                                         }

                                         try{

                                             long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                                         }catch(Exception es){
                                             
                                             DTaxManagerPbb.statusProses=dTaxManagerPbbx.getProses() + "<br> "+ "Eror Proses insert log history NOP : "+logHistory.getId();
                                             
                                         }*/
                                DTaxManagerPbb.countQuery = DTaxManagerPbb.countQuery + 1;

                                DTaxManagerPbb.count = DTaxManagerPbb.count + 1;

                            } catch (Exception es) {

                                DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Eror Proses insert log history NOP : " + pbb.getNop();

                            }
                        }
                        rs.close();
                        totalTagihan = totalTagihan + (totTagihan + totDenda);
                        DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Proses file tahun " + k + " selesai pada pukul " + Formater.formatDate(new Date(), "hh:mm") + " dengan Tagihan : " + Formater.formatNumber(totTagihan, "#,###") + ", Denda : " + Formater.formatNumber(totDenda, "#,###");
                    } catch (Exception e) {
                        System.out.println("Exc in getListAP >>> " + e.toString());
                        DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + nop + " no urut: " + counterRs + " Eror create file " + e.toString();
                    } finally {
                        DBResultSet.close(dbrs);
                    }

                }

            } else {

                vPBB = SessSimpatda.getListPBBThreadIProtax(whereClause);

                if (vPBB.size() > 0) {
                    for (int i = 0; i < vPBB.size(); i++) {
                        Pbb pbb = (Pbb) vPBB.get(i);

                        if (!DTaxManagerPbb.running) {
                            return patchFle;
                        }

                        //proses perhitungan jumlah tagihan dan denda
                        double totPambayaran = SessPbbIprotax.PerhitunganPbbYangHarusDibayar(pbb.getId(), pbb.getTahun(), pbb.getJumlahTagihan());
                        /*count denda adm sppt*/
                        double denda = SessPbbIprotax.PerhitunganDenda(pbb.getId(), pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(), totPambayaran);
                        /*total yang harus dibayarkan*/
                        double ygHarusDibayar = totPambayaran + denda;
                        try {
                            pbb.setJumlahTagihan(String.format("%.0f", ygHarusDibayar));
                        } catch (Exception ex) {
                        }
                        try {
                            pbb.setDenda(Formater.formatNumber(denda, "#,###,##0"));
                        } catch (Exception ex) {
                        }
                        pbb.setFormula("(" + pbb.getnJOPBumi() + " + " + pbb.getnJOPBangunan() + " - " + pbb.getnJOPTKP() + ") X " + (pbb.getTarifSppt() * (pbb.getNjkpSppt() / 100)) + " % + " + pbb.getDenda());

                        pw.print(pbb.getId() + "\t");
                        pw.print(pbb.getNama() + "\t");
                        pw.print(pbb.getJumlahTagihan() + "\t");
                        pw.print("" + AppSetting.INSTANSI_PBB + "\t");
                        pw.print(pbb.getNpwpd() + "\t");
                        pw.print(pbb.getAlamat() + "\t");
                        pw.print(pbb.getLetakObjectPajak() + "\t");
                        pw.print(pbb.getTahun() + "\t");
                        pw.print(pbb.getTglJatuhTempo() + "\t");
                        pw.print(pbb.getLuasBumi() + "\t");
                        pw.print(pbb.getLuasBangunan() + "\t");
                        pw.print(pbb.getnJOPBumi() + "\t");
                        pw.print(pbb.getnJOPBangunan() + "\t");
                        pw.print(pbb.getnJOPTKP() + "\t");
                        String dendaString = pbb.getDenda();
                        double dendaBaru = Double.valueOf(dendaString);
//                        pw.print(pbb.getDenda() + "\t");
                        pw.print(dendaBaru + "\t");
                        pw.print(pbb.getFormula() + "\t");
                        //pw.print(pbb.getTerbilang()+"\t");
                        if (!pbb.getJumlahTagihan().equals("")) {
                            double total = Double.valueOf(pbb.getJumlahTagihan());
                            long mylong = (long) (total);
                            ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                            pbb.setTerbilang(convert.getText() + " rupiah ");

                            pw.print(pbb.getTerbilang() + "\t");
                        } else {
                            pw.print(pbb.getTerbilang() + "\t");
                        }

                        pw.println();

                        //insert history
                        LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                        logHistory.setId(pbb.getId());
                        logHistory.setNama(pbb.getNama());
                        if (!pbb.getJumlahTagihan().equals("")) {
                            logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                        } else {
                            logHistory.setJumlahPajak(0);
                        }
                        logHistory.setTahun(pbb.getTahun());
                        logHistory.setBulan("");
                        logHistory.setInstansi(pbb.getInstansi());
                        if (!pbb.getDenda().equals("")) {
                            logHistory.setDenda(denda);
                        } else {
                            logHistory.setDenda(0);
                        }

                        logHistory.setAlamat(pbb.getAlamat());
                        logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                        if (!pbb.getLuasBangunan().equals("")) {
                            logHistory.setLuasBangunan(Double.valueOf(pbb.getLuasBangunan()));
                        } else {
                            logHistory.setLuasBangunan(0);
                        }

                        if (!pbb.getLuasBumi().equals("")) {
                            logHistory.setLuasBumi(Double.valueOf(pbb.getLuasBumi()));
                        } else {
                            logHistory.setLuasBumi(0);
                        }

                        if (!pbb.getnJOPBangunan().equals("")) {
                            logHistory.setnJOPBangunan(Double.valueOf(pbb.getnJOPBangunan()));
                        } else {
                            logHistory.setnJOPBangunan(0);
                        }

                        if (!pbb.getnJOPBumi().equals("")) {
                            logHistory.setnJOPBumi(Double.valueOf(pbb.getnJOPBumi()));
                        } else {
                            logHistory.setnJOPBumi(0);
                        }

                        if (!pbb.getnJOPTKP().equals("")) {                            
                            logHistory.setnJOPTKP(Double.valueOf(pbb.getnJOPTKP()));
                        } else {
                            logHistory.setnJOPTKP(0);
                        }

                        long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                        DTaxManagerPbb.count = DTaxManagerPbb.count + 1;
                    }
                }
            }
            DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> Total tagihan + denda keseluruhan : " + Formater.formatNumber(totalTagihan, "#,###");
        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();

        return patchFle;
    }

    public static String sentPbbIpRotaxV2(FileSent fileSent) {

        Diskon diskon = new Diskon();
        PrintWriter pw = null;
        String patchFle = "";
        DTaxManagerPbb dTaxManagerPbbx = new DTaxManagerPbb();
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = fileSent.getLocation() + System.getProperty("file.separator") + fileSent.getFileName() + ".txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            double totalTagihan = 0;
            Vector vPBB = new Vector();
            String whereClause = "";//" WHERE JUMLAH_TAGIHAN > 0"; 
            int startYear = 0;
            int endYear = 0;
            
           
                      
            if (!(fileSent.getTahunStart().equals("") && fileSent.equals(""))) {

                startYear = Integer.parseInt(fileSent.getTahunStart());
                endYear = Integer.parseInt(fileSent.getTahunEnd());
            }          

            pw.print("id" + "\t");
            pw.print("nama" + "\t");
            pw.print("jum_tagihan" + "\t");
            pw.print("instansi" + "\t");
            pw.print("NPWP" + "\t");
            pw.print("Alamat WP" + "\t");
            pw.print("Letak Objek Pajak" + "\t");
            pw.print("Tahun" + "\t");
            pw.print("Tgl Jatuh Tempo" + "\t");
            pw.print("Luas Bumi" + "\t");
            pw.print("Luas Bangunan" + "\t");
            pw.print("NJOP Bumi" + "\t");
            pw.print("NJOP Bangunan" + "\t");
            pw.print("NJOPTKP" + "\t");
            pw.print("Denda" + "\t");
            pw.print("Formula" + "\t");
            pw.print("Terbilang" + "\t");
            pw.println();

            int data = 0;

            for (int k = startYear; k <= endYear; k++) {

                //whereClause = " WHERE TAHUN BETWEEN "+k+""+" AND "+k+" AND JUMLAH_TAGIHAN > 0";
                whereClause = " WHERE TAHUN BETWEEN " + k + "" + " AND " + k;
                //whereClause = " WHERE VIEW_PBB.NOP = 510306000302400740 AND VIEW_PBB.THN_PAJAK_SPPT = 2008";

                //vPBB = SessSimpatda.getListPBBThread(whereClause);
                DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Proses file tahun " + k;
                DBResultSet dbrs = null;
                // if(vPBB.size()>0){
                String nop = "";
                int counterRs = 0;
                try {

                    String sql = "SELECT * FROM VIEW_PBB";

                    if (!whereClause.equals("")) {
                        sql = sql + whereClause;
                    }

                    Hashtable hashPembayaran = PstPaymentPbb.hashIpprotax(0, 0, whereClause, "VIEW_PEMBAYARAN_PBB.NOP, VIEW_PEMBAYARAN_PBB.PEMBAYARAN_SPPT_KE");

                    dbrs = DBHandler.execQueryResultNew(sql);
                    ResultSet rs = dbrs.getResultSet();
                    double totTagihan = 0;
                    double totDenda = 0;
                    while (rs.next()) {

                        Pbb pbb = new Pbb();
                        counterRs = counterRs + 1;

                        try {
                            
                            pbb.setId(rs.getString("NOP"));
                            nop = pbb.getId();                                                        
                            
                            pbb.setNpwpd(rs.getString("NPWPD"));//3//sNoId
                            pbb.setNama(rs.getString("NAMA"));//4//sNama
                            pbb.setInstansi(rs.getString("INSTANSI"));
                            //pbb.setJumlahTagihan(rs.getString("JUMLAH_TAGIHAN"));//5 jum_tagihan
                            pbb.setAlamat(rs.getString("ALAMAT_WP"));//sKet_2//Alamat
                            pbb.setLetakObjectPajak(rs.getString("LETAK"));//Letak Objek Pajak
                            pbb.setTahun(rs.getString("TAHUN"));//tahun//10
                            pbb.setTglJatuhTempo(rs.getString("JATUH_TEMPO"));//11//jatuh tempo

//                                        pbb.setLuasBumi(rs.getString("LUAS_BUMI_SPPT"));//12//luas bangunan
//                                        pbb.setLuasBangunan(rs.getString("LUAS_BNG_SPPT"));//13
//                                        pbb.setnJOPBumi(rs.getString("NJOP_BUMI_SPPT"));//14
//                                        pbb.setnJOPBangunan(rs.getString("NJOP_BNG_SPPT"));
//                                        pbb.setnJOPTKP(rs.getString("NJOPTKP_SPPT"));//NJOPTKP_SPPT
                            String sLuasBumiSppt = rs.getString("LUAS_BUMI_SPPT");
                            double luasBumiSppt = Double.valueOf(sLuasBumiSppt);
                            pbb.setLuasBumi(Formater.formatNumber(luasBumiSppt, "#,###,##0"));//12//luas bangunan

                            String sLuasBgnSppt = rs.getString("LUAS_BNG_SPPT");
                            double luasBgnSppt = Double.valueOf(sLuasBgnSppt);
                            pbb.setLuasBangunan(Formater.formatNumber(luasBgnSppt, "#,###,##0"));//13

                            String sNjopBumi = rs.getString("NJOP_BUMI_SPPT");
                            double NjopBumi = Double.valueOf(sNjopBumi);
                            pbb.setnJOPBumi(Formater.formatNumber(NjopBumi, "#,###,##0"));//14

                            String sNjopBgn = rs.getString("NJOP_BNG_SPPT");
                            double NjopBgn = Double.valueOf(sNjopBgn);
                            pbb.setnJOPBangunan(Formater.formatNumber(NjopBgn, "#,###,##0"));

                            String snJOPTKP = rs.getString("NJOPTKP_SPPT");
                            double nJOPTKP = Double.valueOf(snJOPTKP);
                            pbb.setnJOPTKP(Formater.formatNumber(nJOPTKP, "#,###,##0"));//NJOPTKP_SPPT

                            pbb.setNjkpSppt(rs.getDouble("NJKP_SPPT"));
                            pbb.setTarifSppt(rs.getDouble("TARIF_SPPT"));

                            //pbb.setDenda(rs.getString("DENDA"));
                            pbb.setPokok(rs.getString("POKOK"));

                            //start hitung denda dan pembayaran
                            double jumlahTagihan = rs.getDouble("JUMLAH_TAGIHAN_MURNI");
                            Date tglJatuhTempo = rs.getDate("TGL_JATUH_TEMPO_SPPT");
                            Calendar startCalendar = Calendar.getInstance();
                            Calendar endCalendar = Calendar.getInstance();

                            //2017-132 untuk menghitung denda 
                            //String strJatuhTempoNew = "2021-01-31";
//                                                                                String strJatuhTempoNew = diskon.jatuhTempo;
//                                                                                Date dtJatuhTempo = new SimpleDateFormat("yyyy-MM-dd").parse(strJatuhTempoNew);
//                                                                                //endCalendar.setTime(new Date());
//                                                                                int tahun = 0;
//                                                                                try {
//                                                                                    tahun = Integer.valueOf(pbb.getTahun());
//                                                                                    if (tahun < 2021){
//                                                                                        startCalendar.setTime(dtJatuhTempo);
//                                                                                    } else {
//                                                                                        startCalendar.setTime(tglJatuhTempo);
//                                                                                    }
//                                                                                } catch (Exception exc){
//                                                                                    startCalendar.setTime(tglJatuhTempo);
//                                                                                }
                            String strJatuhTempoNew = diskon.jatuhTempo;//untuk menghitung denda
                            String strJatuhTempoNew21 = diskon.jatuhTempo21;//untuk menghitung denda
                            String strJatuhTempoNew22 = diskon.jatuhTempo22;//untuk menghitung denda

                            Date dtJatuhTempo = new SimpleDateFormat("yyyy-MM-dd").parse(strJatuhTempoNew);
                            Date dtJatuhTempo21 = new SimpleDateFormat("yyyy-MM-dd").parse(strJatuhTempoNew21);
                            Date dtJatuhTempo22 = new SimpleDateFormat("yyyy-MM-dd").parse(strJatuhTempoNew22);
                            //endCalendar.setTime(new Date());
                            int tahun = 0;
                            try {
                                tahun = Integer.valueOf(pbb.getTahun());
                                if (tahun > 2018 && tahun < 2021) {
                                    startCalendar.setTime(dtJatuhTempo);
                                } else if (tahun == 2021) {
                                    startCalendar.setTime(dtJatuhTempo21);
                                } else if (tahun == 2022) {
                                    startCalendar.setTime(dtJatuhTempo22);
                                } else {
                                    startCalendar.setTime(tglJatuhTempo);
                                }
                            } catch (Exception exc) {
                                startCalendar.setTime(tglJatuhTempo);
                            }

                            int tunggakan = 0;
                            int diffYear = 0;
                            int diffMonth = 0;
                            int typePembayaran = 0;

                            //String where = "NOP="+nop+" AND THN_PAJAK_SPPT="+pbb.getTahun();
                            //Vector listPembayaran = PstPaymentPbb.listIpprotax(0, 0, where, "TGL_PEMBAYARAN_SPPT");
                            double totalPembayaran = 0;
                            double pembayaranPertama = 0;
                            double pembayaranDenda = 0;
                            Date tglDendaSeharusnya = null;
                            Date tglDendaPembayaranPertama = null;
                            if (hashPembayaran.get(nop) != null) {
                                Vector listPembayaran = (Vector) hashPembayaran.get(nop);
                                if (listPembayaran.size() > 0) {
                                    for (int i = 0; i < listPembayaran.size(); i++) {
                                        PaymentPbb paymentPbb = (PaymentPbb) listPembayaran.get(i);
                                        if ((paymentPbb.getJmlSpptYgDibayar() - paymentPbb.getDendaSppt()) >= 0) {
                                            totalPembayaran += (paymentPbb.getJmlSpptYgDibayar() - paymentPbb.getDendaSppt());
                                            pembayaranDenda += paymentPbb.getDendaSppt();
                                            if (paymentPbb.getJmlSpptYgDibayar() - paymentPbb.getDendaSppt() > 0) {
                                                tglDendaSeharusnya = paymentPbb.getTglPembayaranSppt();
                                            }
                                            if (paymentPbb.getPembayaranSpptKe() == 1) {
                                                tglDendaPembayaranPertama = paymentPbb.getTglPembayaranSppt();
                                                pembayaranPertama = paymentPbb.getJmlSpptYgDibayar() - paymentPbb.getDendaSppt();
                                            }
                                        }
                                    }
                                }
                            }

                            diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
                            diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

                            if (endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH)) {
                                diffMonth += 1;
                            }

                            if (diffMonth > 0) {
                                tunggakan = diffMonth;
                            }

//										if (tglDendaSeharusnya != null && totalPembayaran >= jumlahTagihan){
//											
//											
//											endCalendar.setTime(tglDendaSeharusnya);
//
//											/*diffYear = tglDendaSeharusnya.getYear() - tglJatuhTempo.getYear();
//											diffMonth = diffYear * 12 + tglDendaSeharusnya.getMonth() - tglJatuhTempo.getMonth();*/
//											
//											diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//											diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//
//											if (endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH)){
//												diffMonth += 1;
//											}
//											
//											if (diffMonth < 0 && totalPembayaran >= jumlahTagihan){
//												tunggakan = 0;
//												typePembayaran = 2;
//											} else {
//												
//												if (diffMonth < 0){
//													String date = "2017-07-01";
//													typePembayaran = 1;
//													Date dtNow = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//													//endCalendar.setTime(new Date());
//													endCalendar.setTime(dtNow);
//													/*diffYear = dtNow.getYear() - tglJatuhTempo.getYear();
//													diffMonth = diffYear * 12 + dtNow.getMonth() - tglJatuhTempo.getMonth();*/
//													
//													diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//													diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//
//													if (endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH)){
//														diffMonth += 1;
//													}
//
//													if (diffMonth > 0){
//														tunggakan = diffMonth;
//													}
//
//												} else {
//													tunggakan = diffMonth;
//													typePembayaran = 1;
//													if (jumlahTagihan - pembayaranPertama >=0 && diffMonth > 0){
//														typePembayaran = 2;
//													}
//													/*
//													if (tglDendaPembayaranPertama != null){
//														endCalendar.setTime(tglDendaPembayaranPertama);
//
//														
//														
//														diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//														diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//
//														if ((endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH) && endCalendar.get(Calendar.DAY_OF_MONTH) != 31 
//																&& endCalendar.get(Calendar.DAY_OF_MONTH) != 30 && startCalendar.get(Calendar.DAY_OF_MONTH) != 31 && startCalendar.get(Calendar.DAY_OF_MONTH) != 30) 
//																|| (endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH) && endCalendar.get(Calendar.MONTH) == startCalendar.get(Calendar.MONTH)) ){
//															diffMonth += 1;
//														}
//														
//														if (jumlahTagihan - pembayaranPertama >=0 && diffMonth > 0){
//															typePembayaran = 2;
//														}
//													} */
//												}
//											}
//											
//											
//											
//										} else if (tglDendaPembayaranPertama != null && jumlahTagihan - pembayaranPertama <=0){
//											endCalendar.setTime(tglDendaPembayaranPertama);
//											typePembayaran = 2;
//											/*diffYear = tglDendaPembayaranPertama.getYear() - tglJatuhTempo.getYear();
//											diffMonth = diffYear * 12 + tglDendaPembayaranPertama.getMonth() - tglJatuhTempo.getMonth();
//											tunggakan = diffMonth;*/
//											
//												
//											diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//											diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//
//											if (endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH)){
//												diffMonth += 1;
//											}
//											
//											tunggakan = diffMonth;
//										} else {
//											String date = "2017-07-01";
//												typePembayaran = 3;
//												Date dtNow = new SimpleDateFormat("yyyy-MM-dd").parse(date);
//												//endCalendar.setTime(new Date());
//												endCalendar.setTime(dtNow);
//												
//												diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
//												diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
//												
//												if (endCalendar.get(Calendar.DAY_OF_MONTH) > startCalendar.get(Calendar.DAY_OF_MONTH)){
//													diffMonth += 1;
//												}
//												
//												if (diffMonth > 0){
//													tunggakan = diffMonth;
//												}
//										}
                            double persentaseDenda = 0;
                            if (tunggakan > 0) {
                                if (tunggakan > 24) {
                                    persentaseDenda = 24.0 * (2.0 / 100.0);
                                } else {
                                    persentaseDenda = tunggakan * (2.0 / 100.0);
                                }
                            }
                            double denda = 0;
//										if (typePembayaran == 1){
//											denda = Math.ceil(((jumlahTagihan - pembayaranPertama) * persentaseDenda)-pembayaranDenda);
//										} else if (typePembayaran == 2){
//											denda = Math.ceil((jumlahTagihan * persentaseDenda)-pembayaranDenda);
//										} else if (typePembayaran == 3){
//											denda = Math.ceil((jumlahTagihan-totalPembayaran) * persentaseDenda);
//										}
//2017-132
                            double countDenda = (jumlahTagihan - totalPembayaran) * persentaseDenda;
                            try {
                                NumberFormat formatter = new DecimalFormat("#0.00");
                                String condenda = formatter.format(countDenda);
                                countDenda = Double.valueOf(condenda);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            denda = Math.ceil(countDenda);//Math.ceil((jumlahTagihan-totalPembayaran) * persentaseDenda);

                            //2017-132
                            //if (denda < 0 || tahun < 2019){
//                                                                                if (denda < 0 || tahun < 2021){
//											denda = 0;
//										}
                            denda = diskon.diskonDenda(tahun, denda);

                            try {
                                String sDate1 = "2021-02-01";
                                Date dateDenda = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
                                if (new Date().before(dateDenda)) {
                                    denda = 0;
                                }
                            } catch (Exception exc) {

                            }

                            //pbb.setFormula("(NJOP Bumi + NJOP Bangunan - NJOPTKP) X 0,1 Persen + Denda");
                            pbb.setTerbilang("");

                            //proses perhitungan jumlah tagihan dan denda
                            double totPambayaran = 0; //Double.valueOf(pbb.getJumlahTagihan());//0;//SessPbbIprotax.PerhitunganPbbYangHarusDibayar(pbb.getId(), pbb.getTahun(), pbb.getJumlahTagihan());
                            if ((jumlahTagihan - totalPembayaran) > 0) {
                                totPambayaran = (jumlahTagihan - totalPembayaran);
                            }
                            totTagihan = totTagihan + totPambayaran;

                            /*count denda adm sppt*/
                            //double denda=Math.ceil(Double.valueOf(pbb.getDenda()));//SessPbbIprotax.PerhitunganDenda(pbb.getId(),  pbb.getTahun(), pbb.getTglJatuhTempo(), pbb.getJumlahTagihan(),totPambayaran);
                            totDenda = totDenda + denda;
                            //2017-132                         
                            //hitung diskon
                            totPambayaran = diskon.diskonPajak(tahun, totPambayaran);

                            /*total yang harus dibayarkan*/
                            double ygHarusDibayar = totPambayaran + denda;
                            if (ygHarusDibayar <= 0) {
                                continue;
                            }
                            try {
                                pbb.setJumlahTagihan(String.format("%.0f", ygHarusDibayar));
                            } catch (Exception ex) {
                            }
                            try {
                                pbb.setDenda(Formater.formatNumber(denda, "#,###,##0"));
                            } catch (Exception ex) {
                            }
                            pbb.setFormula("(" + pbb.getnJOPBumi() + " + " + pbb.getnJOPBangunan() + " - " + pbb.getnJOPTKP() + ") X " + (pbb.getTarifSppt() * (pbb.getNjkpSppt() / 100)) + " % + " + pbb.getDenda());

                            //result.add(pbb);
                            pw.print(pbb.getId() + "\t");
                            pw.print(pbb.getNama() + "\t");
                            pw.print(pbb.getJumlahTagihan() + "\t");
                            pw.print("" + AppSetting.INSTANSI_PBB + "\t");
                            pw.print(pbb.getNpwpd() + "\t");
                            pw.print(pbb.getAlamat() + "\t");
                            pw.print(pbb.getLetakObjectPajak() + "\t");
                            pw.print(pbb.getTahun() + "\t");
                            pw.print(pbb.getTglJatuhTempo() + "\t");
                            pw.print(pbb.getLuasBumi() + "\t");
                            pw.print(pbb.getLuasBangunan() + "\t");
                            pw.print(pbb.getnJOPBumi() + "\t");
                            pw.print(pbb.getnJOPBangunan() + "\t");
                            pw.print(pbb.getnJOPTKP() + "\t");
                            pw.print(pbb.getDenda() + "\t");
                            pw.print(pbb.getFormula() + "\t");

                            if (!pbb.getJumlahTagihan().equals("")) {
                                double total = Double.valueOf(pbb.getJumlahTagihan());
                                long mylong = (long) (total);
                                ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                                pbb.setTerbilang(convert.getText() + " rupiah");
                                pw.print(pbb.getTerbilang() + "\t");
                            } else {
                                pw.print(pbb.getTerbilang() + "\t");
                            }

                            pw.println();

                            //insert history
                            /*LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                         logHistory.setId(pbb.getId());
                                         logHistory.setNama(pbb.getNama());
                                         if(!pbb.getJumlahTagihan().equals("")){
                                             logHistory.setJumlahPajak(Double.valueOf(pbb.getJumlahTagihan()));
                                         }else{
                                             logHistory.setJumlahPajak(0);
                                         }
                                         logHistory.setTahun(pbb.getTahun());
                                         logHistory.setBulan("");
                                         logHistory.setInstansi(pbb.getInstansi());

                                         if(!pbb.getDenda().equals("")){
                                             logHistory.setDenda(denda);
                                         }else{
                                             logHistory.setDenda(0);
                                         }

                                         logHistory.setAlamat(pbb.getAlamat());
                                         logHistory.setLetakObjeckPajak(pbb.getLetakObjectPajak());

                                         if(!pbb.getLuasBangunan().equals("")){
                                             logHistory.setLuasBangunan(luasBgnSppt);
                                         }else{
                                             logHistory.setLuasBangunan(0);
                                         }

                                         if(!pbb.getLuasBumi().equals("")){
                                             logHistory.setLuasBumi(luasBumiSppt);
                                         }else{
                                             logHistory.setLuasBumi(0);
                                         }

                                         if(!pbb.getnJOPBangunan().equals("")){
                                             logHistory.setnJOPBangunan(NjopBgn);
                                         }else{
                                             logHistory.setnJOPBangunan(0);
                                         }

                                         if(!pbb.getnJOPBumi().equals("")){
                                             logHistory.setnJOPBumi(NjopBumi);
                                         }else{
                                             logHistory.setnJOPBumi(0);
                                         }

                                         if(!pbb.getnJOPTKP().equals("")){
                                             logHistory.setnJOPTKP(nJOPTKP);
                                         }else{
                                             logHistory.setnJOPTKP(0);
                                         }

                                         try{

                                             long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                                         }catch(Exception es){
                                             
                                             DTaxManagerPbb.statusProses=dTaxManagerPbbx.getProses() + "<br> "+ "Eror Proses insert log history NOP : "+logHistory.getId();
                                             
                                         }*/
                            DTaxManagerPbb.countQuery = DTaxManagerPbb.countQuery + 1;

                            DTaxManagerPbb.count = DTaxManagerPbb.count + 1;

                        } catch (Exception es) {

                            DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Eror Proses insert log history NOP : " + pbb.getNop();

                        }
                    }
                    rs.close();
                    totalTagihan = totalTagihan + (totTagihan + totDenda);
                    DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + "Proses file tahun " + k + " selesai pada pukul " + Formater.formatDate(new Date(), "hh:mm") + " dengan Tagihan : " + Formater.formatNumber(totTagihan, "#,###") + ", Denda : " + Formater.formatNumber(totDenda, "#,###");
                } catch (Exception e) {
                    System.out.println("Exc in getListAP >>> " + e.toString());
                    DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> " + nop + " no urut: " + counterRs + " Eror create file " + e.toString();
                } finally {
                    DBResultSet.close(dbrs);
                }

            }

            DTaxManagerPbb.statusProses = dTaxManagerPbbx.getProses() + "<br> Total tagihan + denda keseluruhan : " + Formater.formatNumber(totalTagihan, "#,###");
        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();

        return patchFle;
    }

    public static String sentBphtbIprotax(FileSent fileSent) {

        PrintWriter pw = null;
        String patchFle = "";
        String patchFleZip = "";
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = fileSent.getLocation() + System.getProperty("file.separator") + fileSent.getFileName() + ".txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //delete history

            String whereDelete = "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI] + "='" + AppSetting.INSTANSI_BPHTB + "'";
            SessSimpatda.deleteExc(whereDelete);

            Vector vSimpatda = new Vector();
            int count = SessSimpatda.countBPHTB(" WHERE THN_BPHTB = 2025");
            DTaxManagerBphtb.countTotal = count;
            vSimpatda = SessSimpatda.getListBphtbThread(" WHERE THN_BPHTB = 2025");

            pw.print("NO_ID\t");
            pw.print("NAMA" + "\t");
            pw.print("JUM_TAGIHAN" + "\t");
            pw.print("INSTANSI_ID" + "\t");
            pw.print("sNoId" + "\t");
            pw.print("PPAT" + "\t");
            pw.print("Terbilang" + "\t");
            pw.println();
            if (vSimpatda.size() > 0) {
                for (int i = 0; i < vSimpatda.size(); i++) {
                    BphtbIprotax bphtb = (BphtbIprotax) vSimpatda.get(i);

                    if (!DTaxManagerBphtb.running) {
                        return patchFle;
                    }
                    //pw.println(""+simpatda.getId()+" "+simpatda.getNamaSimpatda()+" "+simpatda.getJumlahPajakSimpatda()+" PHR_GIANYAR"+" "
                    //+simpatda.getAlamat()+" "+simpatda.getBulanSimpatda()+" "+simpatda.getTahunSimpatda()+" "+simpatda.getPokok()+" "+simpatda.getDenda()+" ");                           
                    pw.print(bphtb.getNoId() + "\t");
                    pw.print(bphtb.getNama() + "\t");
                    pw.print(bphtb.getJumTagihan() + "\t");
                    pw.print(AppSetting.INSTANSI_BPHTB + "\t");
                    pw.print(bphtb.getsNoId() + "\t");
                    pw.print(bphtb.getPpat() + "\t");
                    if (!bphtb.getJumTagihan().equals("")) {
                        double total = Double.valueOf(bphtb.getJumTagihan());
                        long mylong = (long) (total);
                        ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                        bphtb.setTerbilang(convert.getText() + " rupiah ");

                        pw.print(bphtb.getTerbilang() + "\t");
                    } else {
                        pw.print(bphtb.getTerbilang() + "\t");
                    }
                    pw.println();

                    //cek log history
                    LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                    logHistory.setId(bphtb.getNoId());
                    logHistory.setNama(bphtb.getNama());
                    logHistory.setInstansi(bphtb.getInstansi());
                    logHistory.setJumlahPajak(Double.valueOf(bphtb.getJumTagihan()));

                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                    DTaxManagerBphtb.count = DTaxManagerBphtb.count + 1;
                }
            }

        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();
        return patchFle;

    }

    public static String makeZip(String patch) {
        String patchFleZip = "";
        try {
            FileOutputStream fos = new FileOutputStream("PBB_GIANYAR.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            String file1Name = patch;
            addToZipFile(file1Name, zos);

            zos.close();
            fos.close();
            patchFleZip = patch + System.getProperty("file.separator") + "PBB_GIANYAR.zip";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patchFleZip;
    }

    //public static void zipFile(File inputFile, String zipFilePath) {
    public static String zipFile(File inputFile, FileSent fileSent, int type) {
        String patchFleZip = "";

        if (type == 0) {
            patchFleZip = fileSent.getLocation() + System.getProperty("file.separator") + fileSent.getFileName() + ".zip";
        }
        if (type == 1) {
            patchFleZip = fileSent.getLocation() + System.getProperty("file.separator") + fileSent.getFileName() + ".zip";
        } else {
            patchFleZip = fileSent.getLocation() + System.getProperty("file.separator") + fileSent.getFileName() + ".zip";
        }

        try {

            // Wrap a FileOutputStream around a ZipOutputStream
            // to store the zip stream to a file. Note that this is
            // not absolutely necessary
            FileOutputStream fileOutputStream = new FileOutputStream(patchFleZip);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            zipOutputStream.setLevel(9);
            // a ZipEntry represents a file entry in the zip archive
            // We name the ZipEntry after the original file's name
            ZipEntry zipEntry = new ZipEntry(inputFile.getName());
            zipOutputStream.putNextEntry(zipEntry);

            FileInputStream fileInputStream = new FileInputStream(inputFile);
            byte[] buf = new byte[1024];
            int bytesRead;

            // Read the input file by chucks of 1024 bytes
            // and write the read bytes to the zip stream
            while ((bytesRead = fileInputStream.read(buf)) > 0) {
                zipOutputStream.write(buf, 0, bytesRead);
            }

            // close ZipEntry to store the stream to the file
            zipOutputStream.closeEntry();

            zipOutputStream.close();
            fileOutputStream.close();

            // System.out.println("Regular file :" + inputFile.getCanonicalPath()+" is zipped to archive :"+zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patchFleZip;
    }

    public static String sentPhr(String patch) {

        PrintWriter pw = null;
        String patchFle = "";
        String patchFleZip = "";
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = patch + System.getProperty("file.separator") + "PHR_GIANYAR.txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //delete history

            String whereDelete = "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI] + "='" + AppSetting.INSTANSI_PHR + "'";
            SessSimpatda.deleteExc(whereDelete);

            Vector vSimpatda = new Vector();
            int count = SessSimpatda.countPHR("");
            DTaxManagerPhr.countTotal = count;
            vSimpatda = SessSimpatda.getListSimpatdaThread("");

            pw.print("id" + "\t");
            pw.print("nama" + "\t");
            pw.print("jum_tagihan" + "\t");
            pw.print("instansi" + "\t");
            pw.print("Alamat" + "\t");
            pw.print("Bulan" + "\t");
            pw.print("Tahun" + "\t");
            pw.print("Pokok" + "\t");
            pw.print("Denda" + "\t");
            pw.print("No_Sptpd" + "\t");
            pw.println();
            if (vSimpatda.size() > 0) {
                for (int i = 0; i < vSimpatda.size(); i++) {
                    Simpatda simpatda = (Simpatda) vSimpatda.get(i);

                    if (!DTaxManagerPhr.running) {
                        return patchFle;
                    }
                    //pw.println(""+simpatda.getId()+" "+simpatda.getNamaSimpatda()+" "+simpatda.getJumlahPajakSimpatda()+" PHR_GIANYAR"+" "
                    //+simpatda.getAlamat()+" "+simpatda.getBulanSimpatda()+" "+simpatda.getTahunSimpatda()+" "+simpatda.getPokok()+" "+simpatda.getDenda()+" ");                           
                    pw.print(simpatda.getId() + "\t");
                    pw.print(simpatda.getNamaSimpatda() + "\t");
                    pw.print(simpatda.getJumlahPajakSimpatda() + "\t");
                    pw.print(AppSetting.INSTANSI_PHR + "\t");
                    pw.print(simpatda.getAlamat() + "\t");
                    pw.print(simpatda.getBulanSimpatda() + "\t");
                    pw.print(simpatda.getTahunSimpatda() + "\t");
                    pw.print(simpatda.getPokok() + "\t");
                    pw.print(simpatda.getDenda() + "\t");
                    pw.print(simpatda.getNoSspdSimpatda() + "\t");
                    pw.println();

                    //cek log history
                    LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                    logHistory.setId(simpatda.getId());
                    logHistory.setNama(simpatda.getNamaSimpatda());
                    if (!simpatda.getJumlahPajakSimpatda().equals("")) {
                        logHistory.setJumlahPajak(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
                    } else {
                        logHistory.setJumlahPajak(0);
                    }
                    logHistory.setTahun(simpatda.getTahunSimpatda());
                    logHistory.setBulan(simpatda.getBulanSimpatda());
                    logHistory.setInstansi(simpatda.getInstansi());

                    if (!simpatda.getDenda().equals("")) {
                        logHistory.setDenda(Double.valueOf(simpatda.getDenda()));
                    } else {
                        logHistory.setDenda(0);
                    }

                    if (!simpatda.getPokok().equals("")) {
                        logHistory.setPokok(Double.valueOf(simpatda.getPokok()));
                    } else {
                        logHistory.setPokok(0);
                    }
                    logHistory.setAlamat(simpatda.getAlamat());

                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                    DTaxManagerPhr.count = DTaxManagerPhr.count + 1;
                }
            }

        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();
        return patchFle;

    }

    public static String sentPhrOpenPhr(String patch) {

        PrintWriter pw = null;
        String patchFle = "";
        String patchFleZip = "";
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = patch + System.getProperty("file.separator") + AppSetting.INSTANSI_PHR + ".txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //delete history

            String whereDelete = "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI] + "='" + AppSetting.INSTANSI_PHR + "'";
            SessSimpatda.deleteExc(whereDelete);

            Vector vSimpatda = new Vector();
            int count = 0;
            if (AppSetting.TYPE_APP_BACKOFFICE == AppSetting.APP_OPEN_PHR) {
                count = SessSimpatda.countPHROpenPHR("");
            } else {
                count = SessSimpatda.countPHR("");
            }

            DTaxManagerPhr.countTotal = count;
            vSimpatda = SessSimpatda.getListSimpatdaThreadOpenPhr("");

            pw.print("id" + "\t");
            pw.print("nama" + "\t");
            pw.print("jum_tagihan" + "\t");
            pw.print("instansi" + "\t");
            pw.print("Alamat" + "\t");
            pw.print("Bulan" + "\t");
            pw.print("Tahun" + "\t");
            pw.print("Pokok" + "\t");
            pw.print("Denda" + "\t");
            pw.print("Keteran" + "\t");
            pw.print("NPWPD" + "\t");
            pw.print("TanggalAwal" + "\t");
            pw.print("TanggalAkhir" + "\t");
            pw.println();
            if (vSimpatda.size() > 0) {
                for (int i = 0; i < vSimpatda.size(); i++) {
                    Simpatda simpatda = (Simpatda) vSimpatda.get(i);

                    if (!DTaxManagerPhr.running) {
                        return patchFle;
                    }
                    //pw.println(""+simpatda.getId()+" "+simpatda.getNamaSimpatda()+" "+simpatda.getJumlahPajakSimpatda()+" PHR_GIANYAR"+" "
                    //+simpatda.getAlamat()+" "+simpatda.getBulanSimpatda()+" "+simpatda.getTahunSimpatda()+" "+simpatda.getPokok()+" "+simpatda.getDenda()+" ");                           
                    pw.print(simpatda.getId() + "\t");
                    pw.print(simpatda.getNamaSimpatda() + "\t");
                    pw.print(simpatda.getJumlahPajakSimpatda() + "\t");
                    pw.print(AppSetting.INSTANSI_PHR + "\t");
                    pw.print(simpatda.getAlamat() + "\t");
                    pw.print(simpatda.getBulanSimpatda() + "\t");
                    pw.print(simpatda.getTahunSimpatda() + "\t");
                    pw.print(simpatda.getPokok() + "\t");
                    pw.print(simpatda.getDenda() + "\t");
                    pw.print(simpatda.getKeterangan() + "\t");
                    pw.print(simpatda.getNpwpd() + "\t");
                    pw.print(simpatda.getTanggalAwal() + "\t");
                    pw.print(simpatda.getTanggalAkhir() + "\t");
                    pw.println();

                    //cek log history
                    LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                    logHistory.setId(simpatda.getId());
                    logHistory.setNama(simpatda.getNamaSimpatda());
                    if (!simpatda.getJumlahPajakSimpatda().equals("")) {
                        logHistory.setJumlahPajak(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
                    } else {
                        logHistory.setJumlahPajak(0);
                    }
                    logHistory.setTahun(simpatda.getTahunSimpatda());
                    logHistory.setBulan(simpatda.getBulanSimpatda());
                    logHistory.setInstansi(simpatda.getInstansi());

                    if (!simpatda.getDenda().equals("")) {
                        logHistory.setDenda(Double.valueOf(simpatda.getDenda()));
                    } else {
                        logHistory.setDenda(0);
                    }

                    if (!simpatda.getPokok().equals("")) {
                        logHistory.setPokok(Double.valueOf(simpatda.getPokok()));
                    } else {
                        logHistory.setPokok(0);
                    }
                    logHistory.setAlamat(simpatda.getAlamat());

                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                    DTaxManagerPhr.count = DTaxManagerPhr.count + 1;
                }
            }

        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();
        return patchFle;

    }

    public static String sentPhrPhrH(String patch) {

        PrintWriter pw = null;
        String patchFle = "";
        String patchFleZip = "";
        try {
            //buatkan lokasi dan tempat file yang akan di upload
            Date dateNow = new Date();
            Date transaksiCreate = dateNow;
            String strYear = String.valueOf(transaksiCreate.getYear() + 1900);
            String strMonth = String.valueOf(transaksiCreate.getMonth() + 1);
            String strDate = String.valueOf(transaksiCreate.getDate());
            patchFle = patch + System.getProperty("file.separator") + AppSetting.INSTANSI_PHR + ".txt";
            pw = new PrintWriter(patchFle);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(sentcsv.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //delete history

            String whereDelete = "" + PstLogHistoryTransaksi.fieldNames[PstLogHistoryTransaksi.FLD_INSTANSI] + "='" + AppSetting.INSTANSI_PHR + "'";
            SessSimpatda.deleteExc(whereDelete);

            Vector vSimpatda = new Vector();
            int count = SessSimpatda.countPHRPHRH("");

            DTaxManagerPhr.countTotal = count;
            vSimpatda = SessSimpatda.getListSimpatdaThreadPhrH("");

            pw.print("id" + "\t");
            pw.print("nama" + "\t");
            pw.print("jum_tagihan" + "\t");
            pw.print("instansi" + "\t");
            pw.print("NPWPD" + "\t");
            pw.print("JenisUsaha" + "\t");
            pw.print("Alamat" + "\t");
            pw.print("Bulan" + "\t");
            pw.print("Tahun" + "\t");
            pw.print("TanggalJatuhTempo" + "\t");
            pw.print("TagihanPajak" + "\t");
            pw.print("TagihanAdmin" + "\t");
            pw.print("TagihanDenda" + "\t");
            pw.print("Waktu" + "\t");
            pw.println();
            if (vSimpatda.size() > 0) {
                for (int i = 0; i < vSimpatda.size(); i++) {
                    Simpatda simpatda = (Simpatda) vSimpatda.get(i);

                    if (!DTaxManagerPhr.running) {
                        return patchFle;
                    }
                    //pw.println(""+simpatda.getId()+" "+simpatda.getNamaSimpatda()+" "+simpatda.getJumlahPajakSimpatda()+" PHR_GIANYAR"+" "
                    //+simpatda.getAlamat()+" "+simpatda.getBulanSimpatda()+" "+simpatda.getTahunSimpatda()+" "+simpatda.getPokok()+" "+simpatda.getDenda()+" ");                           
                    pw.print(simpatda.getId() + "\t");
                    pw.print(simpatda.getNamaSimpatda() + "\t");
                    pw.print(simpatda.getJumlahPajakSimpatda() + "\t");
                    pw.print(AppSetting.INSTANSI_PHR + "\t");

                    pw.print(simpatda.getNpwpd() + "\t");
                    pw.print(simpatda.getJenisUsaha() + "\t");
                    pw.print(simpatda.getAlamat() + "\t");
                    pw.print(simpatda.getBulanSimpatda() + "\t");
                    pw.print(simpatda.getTahunSimpatda() + "\t");
                    pw.print(simpatda.getJatuhTempo() + "\t");
                    pw.print(simpatda.getPokok() + "\t");
                    pw.print(simpatda.getTagihanAdmin() + "\t");
                    pw.print(simpatda.getDenda() + "\t");
                    pw.print(simpatda.getWaktu() + "\t");

                    pw.println();

                    //cek log history
                    LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                    logHistory.setId(simpatda.getId());
                    logHistory.setNama(simpatda.getNamaSimpatda());
                    if (!simpatda.getJumlahPajakSimpatda().equals("")) {
                        logHistory.setJumlahPajak(Double.valueOf(simpatda.getJumlahPajakSimpatda()));
                    } else {
                        logHistory.setJumlahPajak(0);
                    }
                    logHistory.setTahun(simpatda.getTahunSimpatda());
                    logHistory.setBulan(simpatda.getBulanSimpatda());
                    logHistory.setInstansi(simpatda.getInstansi());

                    if (!simpatda.getDenda().equals("")) {
                        logHistory.setDenda(Double.valueOf(simpatda.getDenda()));
                    } else {
                        logHistory.setDenda(0);
                    }

                    if (!simpatda.getPokok().equals("")) {
                        logHistory.setPokok(Double.valueOf(simpatda.getPokok()));
                    } else {
                        logHistory.setPokok(0);
                    }
                    logHistory.setAlamat(simpatda.getAlamat());

                    long oid = PstLogHistoryTransaksi.insertExc(logHistory);

                    DTaxManagerPhr.count = DTaxManagerPhr.count + 1;
                }
            }

        } catch (Exception exc) {
            System.out.println("ini eornya" + exc);
        }

        pw.flush();
        return patchFle;

    }

    public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

}
