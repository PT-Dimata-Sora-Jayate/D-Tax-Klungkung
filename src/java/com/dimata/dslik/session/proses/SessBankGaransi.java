/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.dslik.entity.bankgaransi.BankGaransi;
import com.dimata.dslik.entity.bankgaransi.PstBankGaransi;
import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha;
import com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.pengurusataupemilik.PengurusAtauPemilik;
import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import com.dimata.util.Formater;
import java.util.Vector;

/**
 *
 * @author dimata005
 */
public class SessBankGaransi {
    public static String sentDataBankGaransiLunasSebelumWaktunya(long periodeId) {
        String result="";
        
        //cek periodesebelumnya
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
        
        String where = " KODE_KONDISI='00' AND PERIODE_ID='"+prevPeriodeId+"' AND NO_REKENING NOT IN (SELECT NO_REKENING FROM dslik_bank_garansi WHERE PERIODE_ID='"+periodeId+"')";
        Vector listBankGaransi = PstBankGaransi.list(0,0,""+where,"");
        if(listBankGaransi.size()>0){
            for (int p = 0; p < listBankGaransi.size(); p++) {
                BankGaransi bankGaransi = (BankGaransi) listBankGaransi.get(p);
                bankGaransi.setOID(0);
                bankGaransi.setPeriodeId(periodeId);
                bankGaransi.setKodeKondisi("");
                try{
                    long bankGaransiId = PstBankGaransi.insertExc(bankGaransi);
                    if(bankGaransiId!=0){
                        long updateData = PstBankGaransi.updateStatusDataNotComplate(bankGaransiId);
                    }
                    
                    //cek apakah punya debitur atau tidak
                    String whereDebitur=""+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+bankGaransi.getCif()+"' AND "+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+periodeId+"'";
                    boolean oidDebitur = PstDebitur.checkCif(whereDebitur);
                    if(oidDebitur==false){
                        String whereDebiturPrev=""+PstDebitur.fieldNames[PstDebitur.FLD_CIF]+"='"+bankGaransi.getCif()+"' AND "+PstDebitur.fieldNames[PstDebitur.FLD_PERIODE_ID]+"='"+prevPeriodeId+"'";
                        Vector vDebitur = PstDebitur.list(0,0,whereDebiturPrev, "");
                        for (int d = 0; d < vDebitur.size(); d++) {
                            Debitur entDebitur = (Debitur) vDebitur.get(d);
                            entDebitur.setOID(0);
                            entDebitur.setPeriodeId(periodeId);
                            entDebitur.setNamaLengkap("");
                            entDebitur.setNamaIdentitas("");
                            try{
                                long debiturOid = PstDebitur.insertExc(entDebitur);
                                if(debiturOid!=0){
                                    long updateData = PstDebiturBdnUsaha.updateStatusDataNotComplate(debiturOid);
                                    if(entDebitur.getKodeJenisNsb()!=1){
                                        //insertkan pengurus dan pemilik
                                        String wherePengurus = ""+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]+"='"+bankGaransi.getCif()+"' AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+prevPeriodeId+"'";
                                        Vector vPengurusPemilik = PstPengurusAtauPemilik.list(0, 0, wherePengurus, "");
                                        for (int i = 0; i < vPengurusPemilik.size(); i++) {
                                            PengurusAtauPemilik entPengurusAtauPemilik = (PengurusAtauPemilik) vPengurusPemilik.get(i);
                                            entPengurusAtauPemilik.setOID(0);
                                            entPengurusAtauPemilik.setPeriodeId(periodeId);
                                            try{
                                                long pengurusId = PstPengurusAtauPemilik.insertExc(entPengurusAtauPemilik);
                                            }catch(Exception ex){
                                            }
                                        }
                                    }
                                }
                            }catch(Exception ex){
                            }
                        }
                    }
                }catch(Exception es){
                }
            }
        }
        
        return result;
    }
}
