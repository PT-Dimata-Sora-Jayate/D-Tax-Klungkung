/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.session.proses;

import com.dimata.dslik.entity.debitur.Debitur;
import com.dimata.dslik.entity.debitur.PstDebitur;
import com.dimata.dslik.entity.kredit.Kredit;
import com.dimata.dslik.entity.kredit.PstKredit;
import com.dimata.dslik.entity.masterdata.Periode;
import com.dimata.dslik.entity.masterdata.PstPeriode;
import com.dimata.dslik.entity.pengurusataupemilik.PengurusAtauPemilik;
import com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik;
import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.DBHandler;
import static com.dimata.qdep.db.DBHandler.execUpdate;
import com.dimata.qdep.db.DBResultSet;
import com.dimata.util.Formater;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author dimata005
 */
public class SessFixedCifKredit {
    
    public static long moveDataKreditPindah(String noRekening, long periodeId, String cif) throws DBException {
        long hasil = 0;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
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
                
                if(prevPeriodeId!=0){
                    try {
                        String sql = " SELECT * FROM dslik_kredit WHERE PERIODE_ID='"+prevPeriodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+cif+"' limit 0,1 ";
                        dbrs = DBHandler.execQueryResult(sql);
                        ResultSet rs = dbrs.getResultSet();
                        while (rs.next()) {
                            Kredit entKredit = new Kredit();
                            entKredit.setOID(0);
                            entKredit.setFlagDetail(rs.getString(PstKredit.fieldNames[PstKredit.FLD_FLAG_DETAIL]));
                            entKredit.setNoRekening(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]));
                            entKredit.setCif(rs.getString(PstKredit.fieldNames[PstKredit.FLD_CIF]));
                            entKredit.setKodeSifat(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SIFAT]));
                            entKredit.setKodeJenisKredit(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_KREDIT]));
                            entKredit.setKodeAkad(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_AKAD]));
                            entKredit.setNoAkadAwal(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AWAL]));
                            entKredit.setTglAkadAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AWAL]));
                            entKredit.setNoAkadAkhir(rs.getString(PstKredit.fieldNames[PstKredit.FLD_NO_AKAD_AKHIR]));
                            entKredit.setTglAkadAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AKAD_AKHIR]));
                            entKredit.setBaruPerpanjangan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_BARU_PERPANJANGAN]));
                            entKredit.setTglAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_AWAL]));
                            entKredit.setTglMulai(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MULAI]));
                            entKredit.setTglTempo(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_TEMPO]));
                            entKredit.setKodeKatDbitur(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAT_DBITUR]));
                            entKredit.setKodeJenisPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_JENIS_PENGGUNAAN]));
                            entKredit.setKodeOrientasiPenggunaan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_ORIENTASI_PENGGUNAAN]));
                            entKredit.setKodeSektorEkonomi(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEKTOR_EKONOMI]));
                            entKredit.setKodeKab(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KAB]));
                            entKredit.setNilai(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI]));
                            entKredit.setKodeValuta(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_VALUTA]));
                            entKredit.setProsentaseBunga(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_PROSENTASE_BUNGA]));
                            entKredit.setJenisBunga(rs.getString(PstKredit.fieldNames[PstKredit.FLD_JENIS_BUNGA]));
                            entKredit.setKreditPemerintah(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KREDIT_PEMERINTAH]));
                            entKredit.setTakeover(rs.getString(PstKredit.fieldNames[PstKredit.FLD_TAKEOVER]));
                            entKredit.setSumberDana(rs.getString(PstKredit.fieldNames[PstKredit.FLD_SUMBER_DANA]));
                            entKredit.setPlafonAwal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON_AWAL]));
                            entKredit.setPlafon(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_PLAFON]));
                            entKredit.setRealisasi(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_REALISASI]));
                            entKredit.setDenda(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_DENDA]));
                            entKredit.setBakiDebet(0);
                            entKredit.setNilaiUangAsal(rs.getDouble(PstKredit.fieldNames[PstKredit.FLD_NILAI_UANG_ASAL]));
                            entKredit.setKodeKolektibilitas(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KOLEKTIBILITAS]));
                            entKredit.setTglMacet(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_MACET]));
                            entKredit.setKodeSebabMacet(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_SEBAB_MACET]));
                            entKredit.setTunggakanPokok(0);
                            entKredit.setTunggakanBunga(0);
                            entKredit.setJmlHariTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_JML_HARI_TUNGGAKAN]));
                            entKredit.setFrekuensiTunggakan(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_TUNGGAKAN]));
                            entKredit.setFrekuensiRestrukturisasi(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_FREKUENSI_RESTRUKTURISASI]));
                            entKredit.setTglRestrukturisasiAwal(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AWAL]));
                            entKredit.setTglRestrukturisasiAkhir(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_RESTRUKTURISASI_AKHIR]));
                            entKredit.setKodeCara(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_CARA]));
                            entKredit.setKodeKondisi("08");
                            entKredit.setTglKondisi(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_TGL_KONDISI]));
                            entKredit.setKeterangan(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KETERANGAN]));
                            entKredit.setKodeKantorCabang(rs.getString(PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]));
                            entKredit.setOperasiData(rs.getString(PstKredit.fieldNames[PstKredit.FLD_OPERASI_DATA]));
                            entKredit.setOpenDate(rs.getDate(PstKredit.fieldNames[PstKredit.FLD_OPEN_DATE]));
                            entKredit.setStatusData(rs.getInt(PstKredit.fieldNames[PstKredit.FLD_STATUS_DATA]));
                            entKredit.setPeriodeId(periodeId);
                            entKredit.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
                            try{
                                long kredit = PstKredit.insertExc(entKredit);
                            }catch(Exception ex){
                            }
                        }
                        rs.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    } finally {
                        DBResultSet.close(dbrs);
                    }
                }
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    public static long replaceCifNoRekening(String noRekening, long periodeId,String currenCif, String prevCif) throws DBException {
        long hasil = 0;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
                    String stSql="";
                    int iResult = 0;
                    try {
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_debitur SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_kredit SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_agunan SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_pengurus_atau_pemilik SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    } 
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
    public static long replaceCifNoRekeningBankGaransi(String noRekening, long periodeId,String currenCif, String prevCif) throws DBException {
        long hasil = 0;
        DBResultSet dbrs = null;
        try {
            if(periodeId!=0){
                    String stSql="";
                    int iResult = 0;
                    try {
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_debitur SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_bank_garansi SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_agunan SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_pengurus_atau_pemilik SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    } 
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    public static long replaceInsertCifNoRekening(String noRekening, long periodeId,String currenCif, String prevCif) throws DBException {
        long hasil = 0;
        DBResultSet dbrs = null;
        try {
            
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
            
            if(prevPeriodeId!=0){
                    //proses insert data debitur terlebih dahulu dari prev periode
                    boolean cekExistingDebitur = PstDebitur.checkCifExisting(periodeId,prevCif);
                    if(cekExistingDebitur==false){
                        try {
                            String sql = " SELECT * FROM dslik_debitur WHERE PERIODE_ID='"+prevPeriodeId+"' AND CIF='"+prevCif+"' limit 0,1 ";
                            dbrs = DBHandler.execQueryResult(sql);
                            ResultSet rs = dbrs.getResultSet();
                            while (rs.next()) {
                                Debitur entDebitur = new Debitur();
                                try{
                                    entDebitur.setOID(0);
                                    entDebitur.setFlagDetail(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_FLAG_DETAIL]));
                                    entDebitur.setCif(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_CIF]));
                                    entDebitur.setJenisIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_JENIS_IDENTITAS]));
                                    entDebitur.setNik(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NIK]));
                                    entDebitur.setNamaIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]));
                                    entDebitur.setNamaLengkap(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_LENGKAP]));
                                    entDebitur.setKodeStatusGelar(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_STATUS_GELAR]));
                                    entDebitur.setJekel(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_JEKEL]));
                                    entDebitur.setTempatLahir(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_LAHIR]));
                                    entDebitur.setTglLahir(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_LAHIR]));
                                    entDebitur.setNpwp(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NPWP]));
                                    entDebitur.setAlamat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT]));
                                    entDebitur.setKelurahan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KELURAHAN]));
                                    entDebitur.setKecamatan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KECAMATAN]));
                                    entDebitur.setKodeKab(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_KAB]));
                                    entDebitur.setKodePos(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_POS]));
                                    entDebitur.setTelepon(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TELEPON]));
                                    entDebitur.setNomorHp(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NOMOR_HP]));
                                    entDebitur.setEmail(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_EMAIL]));
                                    entDebitur.setKodeDomisili(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_DOMISILI]));
                                    entDebitur.setKodePekerjaan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_PEKERJAAN]));
                                    entDebitur.setTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_BEKERJA]));
                                    entDebitur.setKodeUsahaTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
                                    entDebitur.setAlamatTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT_TEMPAT_BEKERJA]));
                                    entDebitur.setPenghasilanKotor(rs.getDouble(PstDebitur.fieldNames[PstDebitur.FLD_PENGHASILAN_KOTOR]));
                                    entDebitur.setKodePenghasilan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_PENGHASILAN]));
                                    entDebitur.setJmlTanggungan(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_JML_TANGGUNGAN]));
                                    entDebitur.setKodeHub(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_HUB]));
                                    entDebitur.setKodeGol(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_GOL]));
                                    entDebitur.setStatus(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_STATUS]));
                                    entDebitur.setNikPasangan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NIK_PASANGAN]));
                                    entDebitur.setNamaPasangan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_PASANGAN]));
                                    entDebitur.setTglLahirPasangan(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_LAHIR_PASANGAN]));
                                    entDebitur.setPerjanjianPisahHarga(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_PERJANJIAN_PISAH_HARGA]));
                                    entDebitur.setMelanggarBmpk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_MELANGGAR_BMPK]));
                                    entDebitur.setMelampauiBmpk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_MELAMPAUI_BMPK]));
                                    entDebitur.setNamaIbuKandung(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IBU_KANDUNG]));
                                    entDebitur.setKodeKantorCabang(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]));
                                    entDebitur.setOperasiData(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_OPERASI_DATA]));
                                    entDebitur.setNoIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_IDENTITAS]));
                                    entDebitur.setNamaBadanUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_BADAN_USAHA]));
                                    entDebitur.setKodeJenisUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_USAHA]));
                                    entDebitur.setTempatPendirian(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_PENDIRIAN]));
                                    entDebitur.setNoAkte(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_AKTE]));
                                    entDebitur.setTglAktePendirian(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_AKTE_PENDIRIAN]));
                                    entDebitur.setNoAktePerubahan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_AKTE_PERUBAHAN]));
                                    entDebitur.setTglAktePerubahan(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_AKTE_PERUBAHAN]));
                                    entDebitur.setKodeBidangUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_BIDANG_USAHA]));
                                    entDebitur.setKodeHubLjk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_HUB_LJK]));
                                    entDebitur.setGoPublic(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_GO_PUBLIC]));
                                    entDebitur.setPeringkat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_PERINGKAT]));
                                    entDebitur.setLembagaPemeringkat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_LEMBAGA_PEMERINGKAT]));
                                    entDebitur.setTglPemeringkat(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_PEMERINGKAT]));
                                    entDebitur.setNamaGroup(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_GROUP]));
                                    entDebitur.setOpenDate(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_OPEN_DATE]));
                                    entDebitur.setKodeJenisNsb(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
                                    entDebitur.setPeriodeId(periodeId);

                                    long debituroid = PstDebitur.insertExc(entDebitur);

                                    if(entDebitur.getKodeJenisNsb()!=1){
                                        //badan usaha, cari pengurus dan pemilik bulan lalu
                                        try {
                                            sql = " SELECT * FROM dslik_pengurus_atau_pemilik WHERE PERIODE_ID='"+prevPeriodeId+"' AND CIF='"+prevCif+"' limit 0,1 ";
                                            dbrs = DBHandler.execQueryResult(sql);
                                            rs = dbrs.getResultSet();
                                            while (rs.next()) {
                                                PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
                                                entPengurusAtauPemilik.setOID(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]));
                                                entPengurusAtauPemilik.setFlagDetail(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_FLAG_DETAIL]));
                                                entPengurusAtauPemilik.setNoIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]));
                                                entPengurusAtauPemilik.setCif(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]));
                                                entPengurusAtauPemilik.setJenisIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_IDENTITAS]));
                                                entPengurusAtauPemilik.setNamaPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]));
                                                entPengurusAtauPemilik.setJenisKelamin(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_KELAMIN]));
                                                entPengurusAtauPemilik.setAlamat(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]));
                                                entPengurusAtauPemilik.setKelurahan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KELURAHAN]));
                                                entPengurusAtauPemilik.setKecamatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KECAMATAN]));
                                                entPengurusAtauPemilik.setKodeKabupaten(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KABUPATEN]));
                                                entPengurusAtauPemilik.setKodeJabatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_JABATAN]));
                                                entPengurusAtauPemilik.setPangsaKepemilikan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PANGSA_KEPEMILIKAN]));
                                                entPengurusAtauPemilik.setStatusPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PENGURUS]));
                                                entPengurusAtauPemilik.setKodeKantorCabang(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]));
                                                entPengurusAtauPemilik.setOperasiData(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPERASI_DATA]));
                                                entPengurusAtauPemilik.setOpenDate(rs.getDate(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPEN_DATE]));
                                                entPengurusAtauPemilik.setStatusData(rs.getInt(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_DATA]));
                                                entPengurusAtauPemilik.setPeriodeId(periodeId);
                                                entPengurusAtauPemilik.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
                                                try{
                                                    long kredit = PstPengurusAtauPemilik.insertExc(entPengurusAtauPemilik);
                                                }catch(Exception ex){
                                                }
                                            }
                                            rs.close();
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        } finally {
                                            DBResultSet.close(dbrs);
                                        }
                                    }

                                }catch(Exception ex){
                                }
                            }
                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e);
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                    }
                
                    String stSql="";
                    int iResult = 0;
                    try {
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_kredit SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_agunan SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    } 
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    public static long replaceInsertCifNoRekeningBankGaransi(String noRekening, long periodeId,String currenCif, String prevCif) throws DBException {
        long hasil = 0;
        DBResultSet dbrs = null;
        try {
            
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
            
            if(prevPeriodeId!=0){
                    //proses insert data debitur terlebih dahulu dari prev periode
                    boolean cekExistingDebitur = PstDebitur.checkCifExisting(periodeId,prevCif);
                    if(cekExistingDebitur==false){
                        try {
                            String sql = " SELECT * FROM dslik_debitur WHERE PERIODE_ID='"+prevPeriodeId+"' AND CIF='"+prevCif+"' limit 0,1 ";
                            dbrs = DBHandler.execQueryResult(sql);
                            ResultSet rs = dbrs.getResultSet();
                            while (rs.next()) {
                                Debitur entDebitur = new Debitur();
                                try{
                                    entDebitur.setOID(0);
                                    entDebitur.setFlagDetail(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_FLAG_DETAIL]));
                                    entDebitur.setCif(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_CIF]));
                                    entDebitur.setJenisIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_JENIS_IDENTITAS]));
                                    entDebitur.setNik(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NIK]));
                                    entDebitur.setNamaIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IDENTITAS]));
                                    entDebitur.setNamaLengkap(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_LENGKAP]));
                                    entDebitur.setKodeStatusGelar(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_STATUS_GELAR]));
                                    entDebitur.setJekel(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_JEKEL]));
                                    entDebitur.setTempatLahir(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_LAHIR]));
                                    entDebitur.setTglLahir(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_LAHIR]));
                                    entDebitur.setNpwp(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NPWP]));
                                    entDebitur.setAlamat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT]));
                                    entDebitur.setKelurahan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KELURAHAN]));
                                    entDebitur.setKecamatan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KECAMATAN]));
                                    entDebitur.setKodeKab(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_KAB]));
                                    entDebitur.setKodePos(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_POS]));
                                    entDebitur.setTelepon(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TELEPON]));
                                    entDebitur.setNomorHp(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NOMOR_HP]));
                                    entDebitur.setEmail(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_EMAIL]));
                                    entDebitur.setKodeDomisili(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_DOMISILI]));
                                    entDebitur.setKodePekerjaan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_PEKERJAAN]));
                                    entDebitur.setTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_BEKERJA]));
                                    entDebitur.setKodeUsahaTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_USAHA_TEMPAT_BEKERJA]));
                                    entDebitur.setAlamatTempatBekerja(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_ALAMAT_TEMPAT_BEKERJA]));
                                    entDebitur.setPenghasilanKotor(rs.getDouble(PstDebitur.fieldNames[PstDebitur.FLD_PENGHASILAN_KOTOR]));
                                    entDebitur.setKodePenghasilan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_PENGHASILAN]));
                                    entDebitur.setJmlTanggungan(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_JML_TANGGUNGAN]));
                                    entDebitur.setKodeHub(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_HUB]));
                                    entDebitur.setKodeGol(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_GOL]));
                                    entDebitur.setStatus(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_STATUS]));
                                    entDebitur.setNikPasangan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NIK_PASANGAN]));
                                    entDebitur.setNamaPasangan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_PASANGAN]));
                                    entDebitur.setTglLahirPasangan(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_LAHIR_PASANGAN]));
                                    entDebitur.setPerjanjianPisahHarga(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_PERJANJIAN_PISAH_HARGA]));
                                    entDebitur.setMelanggarBmpk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_MELANGGAR_BMPK]));
                                    entDebitur.setMelampauiBmpk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_MELAMPAUI_BMPK]));
                                    entDebitur.setNamaIbuKandung(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_IBU_KANDUNG]));
                                    entDebitur.setKodeKantorCabang(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_KANTOR_CABANG]));
                                    entDebitur.setOperasiData(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_OPERASI_DATA]));
                                    entDebitur.setNoIdentitas(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_IDENTITAS]));
                                    entDebitur.setNamaBadanUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_BADAN_USAHA]));
                                    entDebitur.setKodeJenisUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_USAHA]));
                                    entDebitur.setTempatPendirian(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_TEMPAT_PENDIRIAN]));
                                    entDebitur.setNoAkte(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_AKTE]));
                                    entDebitur.setTglAktePendirian(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_AKTE_PENDIRIAN]));
                                    entDebitur.setNoAktePerubahan(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NO_AKTE_PERUBAHAN]));
                                    entDebitur.setTglAktePerubahan(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_AKTE_PERUBAHAN]));
                                    entDebitur.setKodeBidangUsaha(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_BIDANG_USAHA]));
                                    entDebitur.setKodeHubLjk(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_KODE_HUB_LJK]));
                                    entDebitur.setGoPublic(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_GO_PUBLIC]));
                                    entDebitur.setPeringkat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_PERINGKAT]));
                                    entDebitur.setLembagaPemeringkat(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_LEMBAGA_PEMERINGKAT]));
                                    entDebitur.setTglPemeringkat(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_TGL_PEMERINGKAT]));
                                    entDebitur.setNamaGroup(rs.getString(PstDebitur.fieldNames[PstDebitur.FLD_NAMA_GROUP]));
                                    entDebitur.setOpenDate(rs.getDate(PstDebitur.fieldNames[PstDebitur.FLD_OPEN_DATE]));
                                    entDebitur.setKodeJenisNsb(rs.getInt(PstDebitur.fieldNames[PstDebitur.FLD_KODE_JENIS_NSB]));
                                    entDebitur.setPeriodeId(periodeId);

                                    long debituroid = PstDebitur.insertExc(entDebitur);

                                    if(entDebitur.getKodeJenisNsb()!=1){
                                        //badan usaha, cari pengurus dan pemilik bulan lalu
                                        try {
                                            sql = " SELECT * FROM dslik_pengurus_atau_pemilik WHERE PERIODE_ID='"+prevPeriodeId+"' AND CIF='"+prevCif+"' limit 0,1 ";
                                            dbrs = DBHandler.execQueryResult(sql);
                                            rs = dbrs.getResultSet();
                                            while (rs.next()) {
                                                PengurusAtauPemilik entPengurusAtauPemilik = new PengurusAtauPemilik();
                                                entPengurusAtauPemilik.setOID(rs.getLong(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PENGURUS_ATAU_PEMILIK_OID]));
                                                entPengurusAtauPemilik.setFlagDetail(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_FLAG_DETAIL]));
                                                entPengurusAtauPemilik.setNoIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]));
                                                entPengurusAtauPemilik.setCif(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_CIF]));
                                                entPengurusAtauPemilik.setJenisIdentitas(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_IDENTITAS]));
                                                entPengurusAtauPemilik.setNamaPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NAMA_PENGURUS]));
                                                entPengurusAtauPemilik.setJenisKelamin(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_JENIS_KELAMIN]));
                                                entPengurusAtauPemilik.setAlamat(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_ALAMAT]));
                                                entPengurusAtauPemilik.setKelurahan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KELURAHAN]));
                                                entPengurusAtauPemilik.setKecamatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KECAMATAN]));
                                                entPengurusAtauPemilik.setKodeKabupaten(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KABUPATEN]));
                                                entPengurusAtauPemilik.setKodeJabatan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_JABATAN]));
                                                entPengurusAtauPemilik.setPangsaKepemilikan(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PANGSA_KEPEMILIKAN]));
                                                entPengurusAtauPemilik.setStatusPengurus(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PENGURUS]));
                                                entPengurusAtauPemilik.setKodeKantorCabang(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]));
                                                entPengurusAtauPemilik.setOperasiData(rs.getString(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPERASI_DATA]));
                                                entPengurusAtauPemilik.setOpenDate(rs.getDate(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_OPEN_DATE]));
                                                entPengurusAtauPemilik.setStatusData(rs.getInt(PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_DATA]));
                                                entPengurusAtauPemilik.setPeriodeId(periodeId);
                                                entPengurusAtauPemilik.setStatusOperasiData(rs.getInt("STATUS_OPERASI_DATA"));
                                                try{
                                                    long kredit = PstPengurusAtauPemilik.insertExc(entPengurusAtauPemilik);
                                                }catch(Exception ex){
                                                }
                                            }
                                            rs.close();
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        } finally {
                                            DBResultSet.close(dbrs);
                                        }
                                    }

                                }catch(Exception ex){
                                }
                            }
                            rs.close();
                        } catch (Exception e) {
                            System.out.println(e);
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                    }
                
                    String stSql="";
                    int iResult = 0;
                    try {
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_bank_garansi SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                        //proses replace cif kredit
                        stSql = "UPDATE dslik_agunan SET CIF='"+prevCif+"' WHERE PERIODE_ID='"+periodeId+"' AND NO_REKENING='"+noRekening+"' AND CIF='"+currenCif+"'";
                        try {
                            iResult = DBHandler.execUpdate(stSql);
                        } catch (DBException e) {
                            e.printStackTrace();
                        } finally {
                            DBResultSet.close(dbrs);
                        }
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    } 
                hasil = periodeId;
            }
        }
        catch(Exception e) {
            
        }
        return hasil;
    }
    
    
}
