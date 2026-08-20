/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

/**
 *
 * @author dimata005
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dimata.util.Formater;
import java.util.Date;

/**
 *
 * @author dimata005
 */
public class MonitorUpdateTransferDataKredit implements Runnable {
     private String Proggess="";
    
    public MonitorUpdateTransferDataKredit() {
            
    }
    
    public void run() {

        System.out.println("start .... ");
    
        while (ManagerUpdateTransferDataKredit.running) {
            
            try {
                
                /*proses transfer*/
                ProsesTransferDataBank prosesTransferDataBank = new ProsesTransferDataBank();
                //ManagerTransferData xx =  new ManagerTransferData();
                //cek periode berjalan
                ManagerUpdateTransferDataKredit.setStatusProses("Start Periode <br>");
                long periodeId = 0;
                try{
                    periodeId = prosesTransferDataBank.cekOidPeriode("",5);
                }catch(Exception ex){
                }
                
                //Cek jumlah data
                int countKredit = prosesTransferDataBank.countTransferDataKredit("",periodeId);
                
                ManagerUpdateTransferDataKredit.setStatusProses("Kredit :"+countKredit+" <br>");
                
                ManagerUpdateTransferDataKredit.setStatusProses("Get Kredit <br>");
                String kredit = prosesTransferDataBank.actionUpdateTransferDataKredit("",periodeId,countKredit,0);
                
            } catch (Exception e) {
                System.out.println("Interrupted " + e);
            }
            
            Date newDate = new Date();
            ManagerTransferDataKredit.statusEnd =" Proses Berakhir  .... datae/time : "+Formater.formatDate(newDate, "dd-MM-yyyy kk:mm");
            ManagerTransferDataKredit.running=false;
        }
        System.out.println("stop .... ");
    }
    
}
