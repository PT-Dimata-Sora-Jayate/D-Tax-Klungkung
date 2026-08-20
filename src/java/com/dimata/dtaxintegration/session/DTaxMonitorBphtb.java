package com.dimata.dtaxintegration.session;

import com.dimata.common.session.email.SessEmail;
import com.dimata.dtaxintegration.entity.tagihan.FileSent;
import com.dimata.util.Formater;
import com.dimata.webclient.UploadFile;
import com.dimata.webclient.UploadFileBPHTB;
import java.util.Date;

public class DTaxMonitorBphtb implements Runnable {
  
    private FileSent fileSent = null;

    private String Proggess = "";

    public DTaxMonitorBphtb() {
    }

    public DTaxMonitorBphtb(FileSent fileSent) {
        try {
            this.fileSent = fileSent;
            this.Proggess = "";
        } catch (Exception e) {
            System.out.println(" ! EXC : initiate thread =  " + e.toString());
        }
    }

    public void run() {
        System.out.println("start .... ");
        while (DTaxManagerBphtb.running) {
            try {
                UploadFileBPHTB upload = new UploadFileBPHTB();
                String result = upload.actionBPHTB(this.fileSent);
                Date newDate = new Date();
                DTaxManagerBphtb.statusEnd = " Proses " + result + " date/time : " + Formater.formatDate(newDate, "dd-MM-yyyy kk:mm");
                String message = "Dear Team, Berikut hasil proses pengiriman tagihan<br><br>";
                DTaxManagerBphtb dTaxManagerBphtb = new DTaxManagerBphtb();
                message = message + dTaxManagerBphtb.getProses() + "<br>";
                message = message + dTaxManagerBphtb.getEnd() + "<br><br>";
                message = message + "<small>" + dTaxManagerBphtb.getResponStatus() + "</small>" + result + dTaxManagerBphtb.getCountTotal() + "<br>Terima Kasih";
                String subject = "";
                if (dTaxManagerBphtb.getResponStatus().equals("00")) {
                    subject = subject + "[BERHASIL]";
                } else {
                    subject = subject + "[GAGAL]";
                }
                subject = subject + " Notifikasi Pengiriman Integrasi Pajak BPHTB BPHTB_KLUNGKUNG_" + Formater.formatDate(new Date(), "yyyyMMdd");
                SessEmail sessEmail = new SessEmail();
                String str1 = sessEmail.sendEamil("paymentpbbklungkung@gmail.com", subject, message);
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
            DTaxManagerBphtb.running = false;
        }
        System.out.println("stop .... ");
    }
}
