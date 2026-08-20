/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dtaxintegration.session;

import com.dimata.common.session.email.SessEmail;
import com.dimata.dtaxintegration.entity.tagihan.FileSent;
import com.dimata.util.Formater;
import com.dimata.webclient.AppSetting;
import com.dimata.webclient.UploadFile;
import java.io.IOException;
import java.util.Date;
import java.text.NumberFormat;
import java.nio.file.*;

public class DTaxMonitorPbb implements Runnable {

    private FileSent fileSent = null;
    private String Proggess = "";

    public DTaxMonitorPbb() {

    }

    public DTaxMonitorPbb(FileSent fileSent) {
        try {
            this.fileSent = fileSent;

            this.Proggess = "";

        } catch (Exception e) {
            System.out.println(" ! EXC : initiate thread =  " + e.toString());

        }

    }

    public void run() {

        System.out.println("start .... ");

        while (DTaxManagerPbb.running) {

            try {

                UploadFile upload = new UploadFile();

                String result = upload.actionPBB(fileSent);

                Date newDate = new Date();

                DTaxManagerPbb.statusEnd = " Proses " + result + " date/time : " + Formater.formatDate(newDate, "dd-MM-yyyy kk:mm");

                String message = "Dear Team, Berikut hasil proses pengiriman tagihan<br><br>";
                DTaxManagerPbb dTaxManagerPbb = new DTaxManagerPbb();
                message += dTaxManagerPbb.getProses() + "<br>";
                message += dTaxManagerPbb.getEnd() + "<br><br>";
                message += "<small>" + dTaxManagerPbb.getResponStatus() + "</small> " + result + " dengan total " + dTaxManagerPbb.getCountTotal() + " Tagihan<br>Terima Kasih<br><br>";

                NumberFormat nf = NumberFormat.getNumberInstance();
                long mb = 1024L * 1024L;
                for (Path root : FileSystems.getDefault().getRootDirectories()) {

                    try {
                        FileStore store = Files.getFileStore(root);
                        message += root + " : Space Sisa : " + nf.format(store.getUsableSpace() / mb) + " MB, "
                                + " Space Total : " + nf.format(store.getTotalSpace() / mb) + " MB <br>";
                    } catch (IOException e) {
                    }
                }

                String subject = "";
                if (dTaxManagerPbb.getResponStatus().equals("00")) {
                    subject += "[BERHASIL]";
                } else {
                    subject += "[GAGAL]";
                }
                subject += " Notifikasi Pengiriman Integrasi Pajak PBB " + AppSetting.INSTANSI_PBB + "_" + Formater.formatDate(new Date(), "yyyyMMdd");

                SessEmail sessEmail = new SessEmail();

                String resultEmail = sessEmail.sendEamil("paymentpbbklungkung@gmail.com", subject, message);

            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
            DTaxManagerPbb.running = false;
        }
        System.out.println("stop .... ");
        DTaxManagerPbb dTaxManagerPbb = new DTaxManagerPbb();
        dTaxManagerPbb.stopMonitor();
    }
}
