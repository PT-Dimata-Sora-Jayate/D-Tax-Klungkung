/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session.bphtb;

import com.dimata.dtaxintegration.entity.bi.PajakTypeDetail;
import com.dimata.dtaxintegration.entity.bi.PstPajakTypeDetail;
import com.dimata.dtaxintegration.entity.bi.SearchDataPajak;
import com.dimata.dtaxintegration.entity.inquery.Bphtb;
import com.dimata.dtaxintegration.entity.inquery.BphtbIprotax;
import com.dimata.dtaxintegration.entity.inquery.Payment;
import com.dimata.dtaxintegration.entity.inquery.Pbb;
import com.dimata.dtaxintegration.entity.inquery.PbbIprotax;
import com.dimata.dtaxintegration.entity.inquery.PstPbbIprotax;
import com.dimata.dtaxintegration.entity.inquery.Retribusi;
import com.dimata.dtaxintegration.entity.inquery.Simpatda;
import com.dimata.dtaxintegration.entity.laporan.LaporanPayment;
import com.dimata.dtaxintegration.entity.loghistory.LogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.loghistory.PstLogHistoryTransaksi;
import com.dimata.dtaxintegration.entity.payment.PaymentBphtb;
import com.dimata.dtaxintegration.entity.payment.PaymentBphtbIprotax;
import com.dimata.dtaxintegration.entity.payment.PaymentPbb;
import com.dimata.dtaxintegration.entity.payment.PaymentPbbIprotax;
import com.dimata.dtaxintegration.entity.payment.PaymentPhr;
import com.dimata.dtaxintegration.entity.payment.PaymentPhrforOpenPhr;
import com.dimata.dtaxintegration.entity.payment.PaymentPhrforPhrH;
import com.dimata.dtaxintegration.entity.payment.PaymentRetribusi;
import com.dimata.dtaxintegration.entity.payment.PstPaymentBphtb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentBphtbIprotax;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbb;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbbHistory;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPbbIprotax;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhr;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhrAll;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhrforOpenPhr;
import com.dimata.dtaxintegration.entity.payment.PstPaymentPhrforPhrH;
import com.dimata.dtaxintegration.entity.payment.PstPaymentRetribusi;
import com.dimata.dtaxintegration.entity.tagihan.TagihanDelete;
import com.dimata.dtaxintegration.entity.tagihan.TagihanInsert;
import com.dimata.dtaxintegration.session.ConvertAngkaToHuruf;
import com.dimata.dtaxintegration.session.DTaxIntegrationManager;
import com.dimata.dtaxintegration.session.DTaxManagerAutomaticPhr;
import com.dimata.dtaxintegration.session.DTaxManagerBphtb;
import com.dimata.dtaxintegration.session.DTaxManagerPbb;
import com.dimata.dtaxintegration.session.SessDataPajak;
import com.dimata.dtaxintegration.session.SessPbbIprotax;
import com.dimata.dtaxintegration.session.SessSimpatda;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.qdep.form.FRMHandler;
import com.dimata.util.Diskon;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import com.dimata.webclient.EchoLaporanPaymentDetail;
import com.dimata.webclient.EchoLaporanPaymentDetailSetelahNoBukti;
import com.dimata.webclient.EchoTagihanDeleteById;
import com.dimata.webclient.EchoTagihanInsert;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class DTaxIntegrationMonitorBphtb implements Runnable {

    /**
     * @return the logErorPosting
     */
    public static String getLogErorPosting() {
        return logErorPosting;
    }

    /**
     * @param aLogErorPosting the logErorPosting to set
     */
    public static void setLogErorPosting(String aLogErorPosting) {
        logErorPosting = aLogErorPosting;
    }
    private boolean updateFinish = false;
    private long sleepTimeMinute = (1 * 3600); //menit per 6 jam
    public static String massage = "";
    private static String logErorPosting = "";

    public DTaxIntegrationMonitorBphtb() {

    }

    public void run() {

        System.out.println("start .... ");

        while (DTaxIntegrationManager.running) {
            try {
                Date newDay = new Date();
                String startDate = Formater.formatDate(newDay, "yyyy-MM-dd");
                switch (AppSetting.TYPE_APP_BACKOFFICE) {
                    case AppSetting.APP_OPEN_PHR:
                        //inputPaymentPHRforOpenPHR(startDate, "");
                        break;
                    case AppSetting.APP_PHRH:
                        //inputPaymentPHRforPhrH(startDate, "");
                        break;
                    default:
                        //inputPaymentPHR(startDate, "");
                        break;
                }

                Thread.sleep((long) (2 * 60000));//milisecond tiap 4 jam = 240 menit

            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
        }

        System.out.println("stop .... ");
    }

    public void prosesSimpatda(String var) {

        String[] splits = var.split(",");
        int count = 0;
        String idTagihan = "";
        String tahun = "";
        String bulan = "";
        String jumlah = "";
        String idkey = "";
        for (String asset : splits) {
            if (asset != "") {
                String[] splitsDua = asset.split(";");
                for (String value : splitsDua) {
                    count = count + 1;
                    if (count == 1) {
                        idTagihan = value;
                    }
                    if (count == 2) {
                        tahun = value;
                    }
                    if (count == 3) {
                        bulan = value;
                    }
                    if (count == 4) {
                        idkey = value;
                    }
                    if (count == 5) {
                        jumlah = value;
                        String whereSent = " WHERE ID='" + idTagihan + "' AND MASA_PAJAK='" + bulan + "' AND TAHUN_PAJAK='" + tahun + "' AND JUMLAH='" + jumlah + "' AND ID_KEY='" + idkey + "'";
                        sentSimpatda(whereSent);
                        count = 0;
                    }
                }
            }
        }
    }

    public void sentSimpatda(String where) {

        try {
            try {
                //proses query and sent simpatda ke server
                Vector vSimpatda = new Vector();
                vSimpatda = SessSimpatda.getListSimpatda(where);
                /*
                         *  
                         *  id --> sNoId   
                            nama --> sNama   
                            jum_tagihan --> jum_tagihan   
                            instansi --> sInstansi: PHR_GIANYAR   
                            Alamat --> sKet_1   
                            Bulan --> sKet_2
                            Tahun --> sKet_3   
                            Pokok --> sKet_4   
                            Denda --> sKet_5   
                            No SSPD --> sKet_6   
                            Keterangan --> sKet_7

                 */
                EchoTagihanInsert echo = new EchoTagihanInsert();
                if (vSimpatda.size() > 0) {
                    for (int i = 0; i < vSimpatda.size(); i++) {
                        Simpatda simpatda = (Simpatda) vSimpatda.get(i);

                        TagihanInsert tagihanInsert = new TagihanInsert();
                        tagihanInsert.setsUser(AppSetting.USERNAME_PHR);//1
                        tagihanInsert.setSPassword(AppSetting.PWD_PHR);//2
                        tagihanInsert.setSNoId("" + simpatda.getId());//3
                        tagihanInsert.setSNama("" + simpatda.getNamaSimpatda());//4
                        tagihanInsert.setJumTagihan(Double.valueOf(simpatda.getJumlahPajakSimpatda()));//5
                        tagihanInsert.setSInstansi(AppSetting.INSTANSI_PHR);//6
                        tagihanInsert.setSKet_1("" + simpatda.getAlamat());//ALAMAT//7
                        tagihanInsert.setSKet_2("" + simpatda.getBulanSimpatda());//BULAN//8
                        tagihanInsert.setSKet_3("" + simpatda.getTahunSimpatda());//TAHUN//9
                        tagihanInsert.setSKet_4("" + simpatda.getPokok());//POKOK//10
                        tagihanInsert.setSKet_5("" + simpatda.getDenda());//11
                        tagihanInsert.setSKet_6("" + simpatda.getNoSspdSimpatda());//12
                        tagihanInsert.setSKet_7("" + simpatda.getKeterangan());//13
                        tagihanInsert.setSKet_8("");//14
                        tagihanInsert.setSKet_9("");
                        tagihanInsert.setSKet_10("");
                        tagihanInsert.setSKet_11("");
                        tagihanInsert.setSKet_12("");
                        tagihanInsert.setSKet_13("");

                        String resp_code = "";
                        ///cek apakah di history
                        boolean cekHistory = false;
                        //cekHistory = SessSimpatda.check(simpatda.getId(),simpatda.getTahunSimpatda(),simpatda.getJumlahPajakSimpatda(),simpatda.getBulanSimpatda(),simpatda.getInstansi());
                        //jika history datanya tidak berubah dan tidak ada di history insertkan (false)
                        if (!cekHistory) {
                            resp_code = echo.action(tagihanInsert);
                            massage = "Insert ID " + simpatda.getNpwpd() + " Proses : " + resp_code;
                            if (resp_code.equals("00")) {
                                //insert history
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
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Err Sent CDR :" + e);
            }
        } catch (Exception e) {
            System.out.println("Err Err Sent CDR :" + e);
        }
    }

    public void prosesBphtb(String var) {
        String[] splits = var.split(",");
        int count = 0;
        String idTagihan = "";
        String tahun = "";
        String bulan = "";
        for (String asset : splits) {
            if (asset != "") {
                String[] splitsDua = asset.split(";");
                for (String value : splitsDua) {
                    count = count + 1;
                    if (count == 1) {
                        idTagihan = value;
                    }
                    if (count == 2) {
                        tahun = value;
                        //String whereSent= " WHERE NPWPD='"+idTagihan+"' AND BULAN='"+bulan+"' AND TAHUN='"+tahun+"'";
                        //String whereSent=" WHERE NOP='"+idTagihan+"' AND TAHUN='"+tahun+"'";
                        String whereSent = " WHERE ID='" + idTagihan + "' AND NOP='" + tahun + "'";
                        sentBphtb(whereSent);
                        count = 0;
                    }
                }
            }
        }
    }

    public void prosesBphtbIprotax(String var) {
        String[] splits = var.split(",");
        int count = 0;
        String idTagihan = "";
        String tahun = "";
        String bulan = "";
        for (String asset : splits) {
            if (asset != "") {
                String[] splitsDua = asset.split(";");
                for (String value : splitsDua) {
                    count = count + 1;
                    if (count == 1) {
                        idTagihan = value;
                    }
                    if (count == 2) {
                        tahun = value;
                        //String whereSent= " WHERE NPWPD='"+idTagihan+"' AND BULAN='"+bulan+"' AND TAHUN='"+tahun+"'";
                        //String whereSent=" WHERE NOP='"+idTagihan+"' AND TAHUN='"+tahun+"'";
                        String whereSent = " WHERE NO_ID='" + idTagihan + "' AND SNOID='" + tahun + "'";
                        sentBphtbIprotax(whereSent);
                        count = 0;
                    }
                }
            }
        }
    }

    public void sentBphtb(String where) {

        try {
            try {
                //proses query and sent simpatda ke server
                Vector vSimpatda = new Vector();
                vSimpatda = SessSimpatda.getListBphtb(where);
                /*
                         id --> sNoId   
                         nama --> sNama   
                         jum_tagihan --> jum_tagihan   
                         instansi --> sInstansi: BPHTB_GIANYAR   
                         NOP --> sKet_1
                         Letak Objek Pajak --> sKet2
                 */
                EchoTagihanInsert echo = new EchoTagihanInsert();
                if (vSimpatda.size() > 0) {
                    for (int i = 0; i < vSimpatda.size(); i++) {
                        Bphtb bphtb = (Bphtb) vSimpatda.get(i);

                        TagihanInsert tagihanInsert = new TagihanInsert();
                        tagihanInsert.setsUser(AppSetting.USERNAME_BPHTB);//1
                        tagihanInsert.setSPassword(AppSetting.PWD_BPHTB);//2
                        tagihanInsert.setSNoId("" + bphtb.getId());//3
                        tagihanInsert.setSNama("" + bphtb.getNama());//4
                        tagihanInsert.setJumTagihan(Double.valueOf(bphtb.getJumlahTagihan()));//5
                        tagihanInsert.setSInstansi(AppSetting.INSTANSI_BPHTB);//6
                        tagihanInsert.setSKet_1("" + bphtb.getNop());//ALAMAT//7
                        tagihanInsert.setSKet_2("" + bphtb.getLetakObjectPajak());//BULAN//8

                        //tagihanInsert.setSKet_3(""+bphtb.getPokok());//TAHUN//9
                        //tagihanInsert.setSKet_4(""+bphtb.getDenda());//POKOK//10
                        String valStr = Formater.formatNumber(Double.valueOf(bphtb.getPokok()), "#,###");
                        tagihanInsert.setSKet_3("" + valStr);//TAHUN//9

                        String denda = "0";
                        if (!bphtb.getDenda().equals("")) {
                            denda = Formater.formatNumber(Double.valueOf(bphtb.getDenda()), "#,###");
                        }
                        tagihanInsert.setSKet_4("" + denda);//POKOK//10

                        tagihanInsert.setSKet_5("");//11
                        tagihanInsert.setSKet_6("");//12
                        tagihanInsert.setSKet_7("");//13
                        tagihanInsert.setSKet_8("");//14
                        tagihanInsert.setSKet_9("");
                        tagihanInsert.setSKet_10("");
                        tagihanInsert.setSKet_11("");
                        tagihanInsert.setSKet_12("");
                        tagihanInsert.setSKet_13("");

//                                    String resp_code = echo.action(tagihanInsert);
//                                    massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                        String resp_code = "";
                        boolean cekHistory = false;
                        //cekHistory = SessSimpatda.check(bphtb.getId(),"",bphtb.getJumlahTagihan(),"",bphtb.getInstansi());

                        if (!cekHistory) {
                            resp_code = echo.action(tagihanInsert);
                            massage = "Insert ID " + bphtb.getId() + " Proses : " + resp_code;
                            if (resp_code.equals("00")) {
                                //insert history
                                LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                logHistory.setId(bphtb.getId());
                                logHistory.setNama(bphtb.getNama());
                                if (!bphtb.getJumlahTagihan().equals("")) {
                                    logHistory.setJumlahPajak(Double.valueOf(bphtb.getJumlahTagihan()));
                                } else {
                                    logHistory.setJumlahPajak(0);
                                }
                                logHistory.setTahun("");
                                logHistory.setBulan("");
                                logHistory.setInstansi(bphtb.getInstansi());
                                logHistory.setDenda(0);

                                long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                            }
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Err Sent CDR :" + e);
            }
        } catch (Exception e) {
            System.out.println("Err Err Sent CDR :" + e);
        }
    }

    public void sentBphtbIprotax(String where) {

        try {
            try {
                //proses query and sent simpatda ke server
                Vector vSimpatda = new Vector();
                vSimpatda = SessSimpatda.getListBphtbIprotax(where);
                /*
                         id --> sNoId   
                         nama --> sNama   
                         jum_tagihan --> jum_tagihan   
                         instansi --> sInstansi: BPHTB_GIANYAR   
                         NOP --> sKet_1
                         Letak Objek Pajak --> sKet2
                 */
                EchoTagihanInsert echo = new EchoTagihanInsert();
                if (vSimpatda.size() > 0) {
                    for (int i = 0; i < vSimpatda.size(); i++) {
                        BphtbIprotax bphtb = (BphtbIprotax) vSimpatda.get(i);

                        TagihanInsert tagihanInsert = new TagihanInsert();
                        tagihanInsert.setsUser(AppSetting.USERNAME_BPHTB);//1
                        tagihanInsert.setSPassword(AppSetting.PWD_BPHTB);//2
                        tagihanInsert.setSNoId("" + bphtb.getNoId());//3
                        tagihanInsert.setSNama("" + bphtb.getNama());//4
                        tagihanInsert.setJumTagihan(Double.valueOf(bphtb.getJumTagihan()));//5
                        tagihanInsert.setSInstansi(AppSetting.INSTANSI_BPHTB);//6
                        tagihanInsert.setSKet_1("" + bphtb.getsNoId());//ALAMAT//7
                        tagihanInsert.setSKet_2("" + bphtb.getPpat());//BULAN//8
                        if (!bphtb.getJumTagihan().equals("")) {
                            double total = Double.valueOf(bphtb.getJumTagihan());
                            long mylong = (long) (total);
                            ConvertAngkaToHuruf convert = new ConvertAngkaToHuruf(mylong);
                            bphtb.setTerbilang(convert.getText());
                            tagihanInsert.setSKet_3("" + bphtb.getTerbilang());
                        } else {
                            tagihanInsert.setSKet_3("" + bphtb.getTerbilang());
                        }

                        tagihanInsert.setSKet_4("");//POKOK//10

                        tagihanInsert.setSKet_5("");//11
                        tagihanInsert.setSKet_6("");//12
                        tagihanInsert.setSKet_7("");//13
                        tagihanInsert.setSKet_8("");//14
                        tagihanInsert.setSKet_9("");
                        tagihanInsert.setSKet_10("");
                        tagihanInsert.setSKet_11("");
                        tagihanInsert.setSKet_12("");
                        tagihanInsert.setSKet_13("");

//                                    String resp_code = echo.action(tagihanInsert);
//                                    massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                        String resp_code = "";
                        boolean cekHistory = false;
                        //cekHistory = SessSimpatda.check(bphtb.getId(),"",bphtb.getJumlahTagihan(),"",bphtb.getInstansi());

                        if (!cekHistory) {
                            resp_code = echo.action(tagihanInsert);
                            massage = "Insert ID " + bphtb.getNoId() + " Proses : " + resp_code;
                            if (resp_code.equals("00")) {
                                DTaxManagerBphtb dTaxManagerBphtbx = new DTaxManagerBphtb();
                                if (dTaxManagerBphtbx.getStatusAutoUpload().length() > 0) {
                                    DTaxManagerBphtb.statusAutoUpload = dTaxManagerBphtbx.getStatusAutoUpload() + " Upload berhasil<br><br>";
                                }
                                //insert history
                                LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                logHistory.setId(bphtb.getNoId());
                                logHistory.setNama(bphtb.getNama());
                                if (!bphtb.getJumTagihan().equals("")) {
                                    logHistory.setJumlahPajak(Double.valueOf(bphtb.getJumTagihan()));
                                } else {
                                    logHistory.setJumlahPajak(0);
                                }
                                logHistory.setTahun("");
                                logHistory.setBulan("");
                                logHistory.setInstansi(bphtb.getInstansi());
                                logHistory.setDenda(0);

                                long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                            }
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Err Sent CDR :" + e);
            }
        } catch (Exception e) {
            System.out.println("Err Err Sent CDR :" + e);
        }
    }

    public void sentAutoBphtb(String where) {

        try {
            try {
                //proses query and sent simpatda ke server
                Vector vSimpatda = new Vector();
                vSimpatda = SessSimpatda.getListAutoBphtb(where);
                /*
                         id --> sNoId   
                         nama --> sNama   
                         jum_tagihan --> jum_tagihan   
                         instansi --> sInstansi: BPHTB_GIANYAR   
                         NOP --> sKet_1
                         Letak Objek Pajak --> sKet2
                 */
                EchoTagihanInsert echo = new EchoTagihanInsert();
                if (vSimpatda.size() > 0) {
                    for (int i = 0; i < vSimpatda.size(); i++) {
                        Bphtb bphtb = (Bphtb) vSimpatda.get(i);

                        TagihanInsert tagihanInsert = new TagihanInsert();
                        tagihanInsert.setsUser(AppSetting.USERNAME_BPHTB);//1
                        tagihanInsert.setSPassword(AppSetting.PWD_BPHTB);//2
                        tagihanInsert.setSNoId("" + bphtb.getId());//3
                        tagihanInsert.setSNama("" + bphtb.getNama());//4
                        tagihanInsert.setJumTagihan(Double.valueOf(bphtb.getJumlahTagihan()));//5
                        tagihanInsert.setSInstansi(AppSetting.INSTANSI_BPHTB);//6
                        tagihanInsert.setSKet_1("" + bphtb.getNop());//ALAMAT//7
                        tagihanInsert.setSKet_2("" + bphtb.getLetakObjectPajak());//BULAN//8

                        String valStr = Formater.formatNumber(Double.valueOf(bphtb.getPokok()), "#,###");
                        tagihanInsert.setSKet_3("" + valStr);//TAHUN//9

                        String denda = "0";
                        if (!bphtb.getDenda().equals("")) {
                            denda = Formater.formatNumber(Double.valueOf(bphtb.getDenda()), "#,###");
                        }
                        tagihanInsert.setSKet_4("" + denda);//POKOK//10
                        tagihanInsert.setSKet_5("");//11
                        tagihanInsert.setSKet_6("");//12
                        tagihanInsert.setSKet_7("");//13
                        tagihanInsert.setSKet_8("");//14
                        tagihanInsert.setSKet_9("");
                        tagihanInsert.setSKet_10("");
                        tagihanInsert.setSKet_11("");
                        tagihanInsert.setSKet_12("");
                        tagihanInsert.setSKet_13("");

//                                    String resp_code = echo.action(tagihanInsert);
//                                    massage="Insert ID "+bphtb.getId()+" Proses : "+resp_code;
                        String resp_code = "";
                        boolean cekHistory = false;
                        //cekHistory = SessSimpatda.check(bphtb.getId(),"",bphtb.getJumlahTagihan(),"",bphtb.getInstansi());

                        if (!cekHistory) {
                            resp_code = echo.action(tagihanInsert);
                            massage = "Insert ID " + bphtb.getId() + " Proses : " + resp_code;
                            if (resp_code.equals("00")) {
                                //insert history
                                LogHistoryTransaksi logHistory = new LogHistoryTransaksi();
                                logHistory.setId(bphtb.getId());
                                logHistory.setTib(Long.parseLong(bphtb.getId()));
                                logHistory.setNama(bphtb.getNama());
                                if (!bphtb.getJumlahTagihan().equals("")) {
                                    logHistory.setJumlahPajak(Double.valueOf(bphtb.getJumlahTagihan()));
                                } else {
                                    logHistory.setJumlahPajak(0);
                                }
                                logHistory.setTahun("");
                                logHistory.setBulan("");
                                logHistory.setInstansi(bphtb.getInstansi());
                                logHistory.setDenda(0);

                                long oid = PstLogHistoryTransaksi.insertExc(logHistory);
                            }
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Err Sent CDR :" + e);
            }
        } catch (Exception e) {
            System.out.println("Err Err Sent CDR :" + e);
        }
    }


    public void inputPaymentBphtb(String dateLaporan, String noID) {

        try {
            try {
                //proses query and sent simpatda ke server
                Vector vPaymentBphtb = new Vector();
                EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                Date newDay = new Date();
                LaporanPayment laporanPayment = new LaporanPayment();

                laporanPayment.setsUser(AppSetting.USERNAME_BPHTB);
                laporanPayment.setsPassword(AppSetting.PWD_BPHTB);
                laporanPayment.setsInstansi(AppSetting.INSTANSI_BPHTB);
                laporanPayment.setsNoId("" + noID);
                laporanPayment.setsDate("" + dateLaporan);
                vPaymentBphtb = echoLaporan.getListPaymentDetailBPHTB(laporanPayment);

                if (vPaymentBphtb.size() > 0) {

                    for (int i = 0; i < vPaymentBphtb.size(); i++) {

                        Payment payment = (Payment) vPaymentBphtb.get(i);
                        PaymentBphtb paymentBphtb = new PaymentBphtb();
                        paymentBphtb.setNoTib(Long.valueOf(payment.getNoId()));
                        paymentBphtb.setIdPaymentBank(Long.valueOf(payment.getId()));
                        if (!payment.getTagihan().equals("")) {
                            paymentBphtb.setJumlahBayar(Double.valueOf(payment.getTagihan()));
                        }
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                        String dateStringTransaksi = "" + payment.getTglTx();
                        try {
                            Date transaksiDate = formatter.parse(dateStringTransaksi);
                            paymentBphtb.setTglBayar(transaksiDate);

                        } catch (Exception e) {
                            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date transaksiDate = formatter1.parse(dateStringTransaksi);
                            paymentBphtb.setTglBayar(transaksiDate);
                            System.out.println("error parsing tanggal :" + e.toString());
                        }

                        paymentBphtb.setStatus(Integer.parseInt(payment.getStsReversal()));

                        try {
                            boolean cekHistory = SessSimpatda.checkPaymentBphtb(payment.getId());

                            if (cekHistory) {
                                String idKey = SessSimpatda.checkKeyIdBphtb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                paymentBphtb.setIdKey(idKey);
                                long oid = PstPaymentBphtb.insertExc(paymentBphtb);
                                if (payment.getStsReversal().equals("1")) {
                                    String update = SessSimpatda.updateStatusRaversalBphtb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                }
                            }

                        } catch (Exception ex) {
                            System.out.print("Tidak bisa proses input payment");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("inputPaymentBphtb :" + e);
            }
        } catch (Exception e) {
            System.out.println("inputPaymentBphtb :" + e);
        }
    }

    public void inputPaymentBphtbIprotax(String dateLaporan, String noID) {
        try {
            try {
                //proses query and sent simpatda ke server
                Vector vPaymentBphtb = new Vector();
                EchoLaporanPaymentDetail echoLaporan = new EchoLaporanPaymentDetail();
                Date newDay = new Date();
                LaporanPayment laporanPayment = new LaporanPayment();

                laporanPayment.setsUser(AppSetting.USERNAME_BPHTB);
                laporanPayment.setsPassword(AppSetting.PWD_BPHTB);
                laporanPayment.setsInstansi(AppSetting.INSTANSI_BPHTB);
                laporanPayment.setsNoId("" + noID);
                laporanPayment.setsDate("" + dateLaporan);
                vPaymentBphtb = echoLaporan.getListPaymentDetailIprotax(laporanPayment);

                if (vPaymentBphtb.size() > 0) {

                    for (int i = 0; i < vPaymentBphtb.size(); i++) {

                        Payment payment = (Payment) vPaymentBphtb.get(i);
                        PaymentBphtbIprotax paymentBphtb = new PaymentBphtbIprotax();

                        //cek detail data yang dibyarkan berdasarkan nop
                        String nop = payment.getNoId();

                        /*cek aplikasi*/
                        BphtbIprotax bphtbIprotax = PstPaymentBphtbIprotax.checkNOp(nop, payment.getTahun());

                        paymentBphtb.setKdProvinsi(bphtbIprotax.getKdPropinsi());
                        paymentBphtb.setKdDati2(bphtbIprotax.getKdDati2());
                        paymentBphtb.setThbBphtb(bphtbIprotax.getThnBphtb());
                        paymentBphtb.setBlnBphtb(bphtbIprotax.getBlnBphtb());
                        paymentBphtb.setTglBphtb(bphtbIprotax.getTglBphtb());
                        paymentBphtb.setNoUrutBphtb(bphtbIprotax.getNoUrutBphtb());
                        paymentBphtb.setIndeksBphtb(bphtbIprotax.getIndeksBphtb());
                        paymentBphtb.setKdPejabat(bphtbIprotax.getKdPejabat());
                        paymentBphtb.setKdBankTunggal("99");
                        paymentBphtb.setKdBankPersepsi("99");
                        paymentBphtb.setTglPembayaranReal(bphtbIprotax.getTglBayar());
                        if (!payment.getTagihan().equals("")) {
                            paymentBphtb.setBphtbSdhBayar(Math.abs(Double.valueOf(payment.getTagihan())));
                        }
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                        String dateStringTransaksi = "" + payment.getTglTx();
                        try {
                            Date transaksiDate = formatter.parse(dateStringTransaksi);
                            paymentBphtb.setTglPembayaran(transaksiDate);

                        } catch (Exception e) { 
                            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                            Date transaksiDate = formatter1.parse(dateStringTransaksi);
                            paymentBphtb.setTglPembayaran(transaksiDate);
                            e.printStackTrace();
                        }
                        paymentBphtb.setNoTransBayar("" + (payment.getKetTagihan().equals("") ? "0" : payment.getKetTagihan()));
                        paymentBphtb.setNamaWP(bphtbIprotax.getNama());
                        if (!bphtbIprotax.getJumTagihan().equals("")) {
                            paymentBphtb.setBphtbKurangBayar(Math.abs(Double.valueOf(bphtbIprotax.getJumTagihan()) - paymentBphtb.getBphtbSdhBayar()));
                        }
                        paymentBphtb.setKdKecamatanOp(bphtbIprotax.getKdKecamatanOp());
                        paymentBphtb.setKdKelurahanOp(bphtbIprotax.getKdKelurahanOp());
                        paymentBphtb.setKdBlokOp(bphtbIprotax.getKdBlokOp());
                        paymentBphtb.setNoUrutOp(bphtbIprotax.getNoUrutOp());
                        paymentBphtb.setKdJnsOp(bphtbIprotax.getKdJenisOp());
                        paymentBphtb.setKdTp("05");
                        paymentBphtb.setUserBankRekam(payment.getKdUser());
                        paymentBphtb.setNmPenyetor(payment.getNama());
                        paymentBphtb.setKdSumberData("9");
                        paymentBphtb.setNoTransaksiBayar(bphtbIprotax.getNoId());
                        paymentBphtb.setNoTransaksiBayarBank(payment.getId());
                        paymentBphtb.setStatus(Integer.parseInt(payment.getStsReversal()));
                        
                        try {
                            boolean cekHistory = SessSimpatda.checkPaymentBphtb(payment.getId());

                            if (true) {
                                //String idKey = SessSimpatda.checkKeyIdBphtb(payment.getNoId(), payment.getTahun(), payment.getBulan(), 0);
                                System.out.print("1");
                                if (payment.getStsReversal().equals("1")) {
                                    if (paymentBphtb.getBphtbSdhBayar() > 0) {
                                        boolean cekHistoryReversal = SessSimpatda.checkPaymentBphtbReversal(payment.getId());
                                        System.out.print("2");
                                        if (cekHistoryReversal) {

                                            int oid = SessSimpatda.DeleteDataPembayaranBPHTB(payment.getId());
                                            System.out.print("3");
                                            //boolean zzz = SessSimpatda.insertPaymentPbbRaversalIprotax(paymentBphtb);
                                            String whereUpdate = "KD_PROPINSI = '" + bphtbIprotax.getKdPropinsi() + "' AND "
                                                    + "KD_DATI2 = '" + bphtbIprotax.getKdDati2() + "' AND THN_BPHTB = '" + bphtbIprotax.getThnBphtb() + "' "
                                                    + " AND BLN_BPHTB = '" + bphtbIprotax.getBlnBphtb() + "'  AND TGL_BPHTB = '" + bphtbIprotax.getTglBphtb() + "' "
                                                    + " AND NO_URUT_BPHTB = '" + bphtbIprotax.getNoUrutBphtb() + "' AND INDEKS_BPHTB = '" + bphtbIprotax.getIndeksBphtb() + "'";
                                            //06KLK schema lama
//                                            String sql = " UPDATE IPROTAXBPHTB.DAT_SSB_WP SET KD_BANK_TUNGGAL = '00', KD_BANK_PERSEPSI = 00', NO_TRANS_BAYAR = '0'"
//                                                    + ", TGL_BAYAR_SSB_WP = NULL WHERE " + whereUpdate;
                                            
                                            String sql = " UPDATE IPROTAXBPHTB.DAT_SSPD SET KD_BANK_TUNGGAL = '00', KD_BANK_PERSEPSI = '00', NO_TRANS_BAYAR = '0'"
                                                    + ", TGL_BAYAR_SSB_WP = NULL WHERE " + whereUpdate;
                                            System.out.print("4");
                                            try {
                                                int iResult = DBHandler.execUpdate(sql);
                                            } catch (DBException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                } else {
                                    if (cekHistory) {
                                        long oid = PstPaymentBphtbIprotax.insertExc(paymentBphtb);
                                        System.out.print("5");
                                        String whereUpdate = "KD_PROPINSI = '" + bphtbIprotax.getKdPropinsi() + "' AND "
                                                + "KD_DATI2 = '" + bphtbIprotax.getKdDati2() + "' AND THN_BPHTB = '" + bphtbIprotax.getThnBphtb() + "' "
                                                + " AND BLN_BPHTB = '" + bphtbIprotax.getBlnBphtb() + "'  AND TGL_BPHTB = '" + bphtbIprotax.getTglBphtb() + "' "
                                                + " AND NO_URUT_BPHTB = '" + bphtbIprotax.getNoUrutBphtb() + "' AND INDEKS_BPHTB = '" + bphtbIprotax.getIndeksBphtb() + "'";
                                        
                                        //06KLK schema lama 
                                        //String sql = " UPDATE IPROTAXBPHTB.DAT_SSB_WP SET KD_BANK_TUNGGAL = '99', KD_BANK_PERSEPSI = '99', NO_TRANS_BAYAR = '" + payment.getId() + "'"
                                                //+ ", TGL_BAYAR_SSB_WP = TO_DATE('" + Formater.formatDate(paymentBphtb.getTglPembayaran(), "yyyy-MM-dd") + "','YYYY-MM-DD') WHERE " + whereUpdate;
                                        String sql = " UPDATE IPROTAXBPHTB.DAT_SSPD SET KD_BANK_TUNGGAL = '00', KD_BANK_PERSEPSI = '00', NO_TRANS_BAYAR = '" + payment.getId() + "'"
                                        + ", TGL_BAYAR_SSB_WP = '" + Formater.formatDate(paymentBphtb.getTglPembayaran(), "yyyy-MM-dd") + "' WHERE " + whereUpdate;
                                        try {
                                            int iResult = DBHandler.execUpdate(sql);
                                            System.out.print("6");
                                        } catch (DBException e) {
                                            e.printStackTrace();
                                        }
                                        
                                        //06KLK tambahan scema baru 
                                        sql = " UPDATE IPROTAXBPHTB.DAT_BPHTB SET NO_TRANS_BAYAR = '" + payment.getId() + "' WHERE " + whereUpdate;
                                        try {
                                            int iResult = DBHandler.execUpdate(sql);
                                        } catch (DBException e) {
                                            System.out.println("update DAT_BPHTB=>"+e);
                                        }
                                        //schema lama  
                                        //String sqlTelitiSSB = " UPDATE IPROTAXBPHTB.DAFTAR_TELITI_SSB SET STATUS_DOKUMEN = '8' WHERE " + whereUpdate;
                                        String sqlTelitiSSB = " UPDATE IPROTAXBPHTB.DAFTAR_TELITI_SSPD SET STATUS_DOKUMEN = '8' WHERE " + whereUpdate;
                                        try {
                                            int iResult = DBHandler.execUpdate(sqlTelitiSSB);
                                        } catch (DBException e) {
                                            e.printStackTrace();
                                        }
                                
                                        //06KLK tambahan scema baru
                                        //perubahan karena no transaksi yang dimasukkan seharusnya nomor bukti transaksi bukan NOP
//                                        String sqlOpSspd = " UPDATE IPROTAXBPHTB.DAT_OP_SSPD SET NO_TRANSAKSI = '" + payment.getNoId() + "' WHERE " + whereUpdate;
                                        String sqlOpSspd = " UPDATE IPROTAXBPHTB.DAT_OP_SSPD SET NO_TRANSAKSI = '" + payment.getId() + "' WHERE " + whereUpdate;
                                        try { 
                                            int iResult = DBHandler.execUpdate(sqlOpSspd);
                                        } catch (DBException e) {
                                            e.printStackTrace(); 
                                        }
                                    }
                                }
                            }

                        } catch (Exception ex) {
                            System.out.print("Tidak bisa proses input payment");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("inputPaymentBphtb :" + e);
            }
        } catch (Exception e) {
            System.out.println("inputPaymentBphtb :" + e);
        }
    }

    public static int getIdleSleepTime(long current, long delay, long quarter, long day, long night) {

        long gap = 0;

        if (current < delay) {

            System.out.println("_______________ start service monitoy sebelum delay");

            gap = delay - current - 100;

            return (int) gap;

        } else {

            if (current == delay) {

                System.out.println("_______________ start service monitoy sama delay");

                return 0;

            } else {

                if (current > delay && current < quarter) {

                    System.out.println("_______________ start service monitoy sebelum quarter");

                    gap = quarter - current - 100;

                    return (int) gap;

                } else {

                    if (current == quarter) {

                        System.out.println("_______________  start service monitoy sama delay");

                        return 0;

                    } else {

                        if (current > quarter && current < day) {

                            System.out.println("_______________  start service monitoy sebelum day");

                            gap = day - current - 100;

                            return (int) gap;

                        } else {

                            if (current == day) {

                                System.out.println("_______________  start service monitoy sama day");

                                return 0;

                            } else {

                                if (current > day && current < night) {

                                    System.out.println("_______________  start service monitoy sebelum night");

                                    gap = night - current - 100;

                                    return (int) gap;

                                } else {

                                    if (current == night) {

                                        System.out.println("_______________  start service monitoy sama delay");

                                        return 0;

                                    } else {

                                        System.out.println("_______________  start service monitoy sebelum delay besoknya");

                                        gap = delay - current - 100;

                                        return (int) gap;

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

    }

    public static String dateConvert(String dateString) {
        String[] arr = dateString.split("-");
        String day = arr[0];
        String month = arr[1];
        String year = arr[2];
        String date = "";
        String realMonth = "";
        switch (month) {
            case "JAN":
                realMonth = "01";
                break;
            case "FEB":
                realMonth = "02";
                break;
            case "MAR":
                realMonth = "03";
                break;
            case "APR":
                realMonth = "04";
                break;
            case "MEI":
                realMonth = "05";
                break;
            case "JUN":
                realMonth = "06";
                break;
            case "JUL":
                realMonth = "07";
                break;
            case "AGT":
                realMonth = "08";
                break;
            case "SEP":
                realMonth = "09";
                break;
            case "OKT":
                realMonth = "10";
                break;
            case "NOV":
                realMonth = "11";
                break;
            case "DES":
                realMonth = "12";
                break;
            default:
        }
        date = day + "-" + realMonth + "-" + year;
        return date;
    }

    public static int getIdleSleepTime(long current, long delay) {

        long gap = 0;

        if (current < delay) {

            System.out.println("_______________ start service monitoy sebelum delay");

            gap = delay - current - 100;

            return (int) gap;

        }
        return (int) gap;
    }

    /**
     * @return the sleepTimeMinute
     */
    public long getSleepTimeMinute() {
        return sleepTimeMinute;
    }

    /** 
     * @param sleepTimeMinute the sleepTimeMinute to set
     */
    public void setSleepTimeMinute(long sleepTimeMinute) {
        this.sleepTimeMinute = sleepTimeMinute;
    }

}
