/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.entity.debiturbdnusaha;

/**
 *
 * @author m20n9
 */
import com.dimata.common.entity.logger.I_LogHistory;
import com.dimata.common.entity.logger.I_Validasi;
import com.dimata.dslik.session.proses.SessValidasiInputan;
import com.dimata.qdep.entity.Entity;
import java.util.Date;

public class DebiturBdnUsaha extends Entity implements I_LogHistory,I_Validasi{

    private String flagDetail = "";
    private String cif = "";
    private String noIdentitas = "";
    private String nama = "";
    private String kodeJenis = "";
    private String tempat = "";
    private String noAkte = "";
    private Date tglAktePendirian = null;
    private String noAktePerubahan = "";
    private Date tglAktePerubahan = null;
    private String telepon = "";
    private String teleponSeluler = "";
    private String email = "";
    private String alamat = "";
    private String kelurahan = "";
    private String kecamatan = "";
    private String kodeKab = "";
    private String kodePos = "";
    private String kodeNegara = "";
    private String kodeBidangUsaha = "";
    private String kodeHubLjk = "";
    private String melanggarBmpk = "";
    private String melampauiBmpk = "";
    private String goPublic = "";
    private String kodeGol = "";
    private String peringkat = "";
    private String lembagaPemeringkat = "";
    private Date tglPemeringkat = null;
    private String namaGroup = "";
    private String kodeKtrCabang = "";
    private String operasiData = "";
    private long periodeId = 0;
    private String kodeJenisNsb = "";
    private String sqlHistory ="";
    private int statusOperasiData = 0;
    private int statusData = 0;
    private String noRekening = "";
    private Date tglAkadAkhir = null;
    
    //validasi
    private boolean isNotValid=false;
    private String validasiCif = "";
    private String validasiNoIdentitas = "";
    private String validasiNama = "";
    private String validasiKodeJenis = "";
    private String validasiTempat = "";
    private String validasiNoAkte = "";
    private String validasiTglAktePendirian = "";
    private String validasiNoAktePerubahan = "";
    private String validasiTglAktePerubahan = "";
    private String validasiTelepon = "";
    private String validasiTeleponSeluler = "";
    private String validasiEmail = "";
    private String validasiAlamat = "";
    private String validasiKelurahan = "";
    private String validasiKecamatan = "";
    private String validasiKodeKab = "";
    private String validasiKodePos = "";
    private String validasiKodeNegara = "";
    private String validasiKodeBidangUsaha = "";
    private String validasiKodeHubLjk = "";
    private String validasiMelanggarBmpk = "";
    private String validasiMelampauiBmpk = "";
    private String validasiGoPublic = "";
    private String validasiKodeGol = "";
    private String validasiPeringkat = "";
    private String validasiLembagaPemeringkat = "";
    private String validasiTglPemeringkat = "";
    private String validasiNamaGroup = "";
    private String validasiKodeKtrCabang = "";
    private String validasiNoRekening = "";
    private String validasiTglAkadAkhir = "";
    
    //EROR
    private String errorCif = "";
    private String errorNoIdentitas = "";
    private String errorNama = "";
    private String errorKodeJenis = "";
    private String errorTempat = "";
    private String errorNoAkte = "";
    private String errorTglAktePendirian = "";
    private String errorNoAktePerubahan = "";
    private String errorTglAktePerubahan = "";
    private String errorTelepon = "";
    private String errorTeleponSeluler = "";
    private String errorEmail = "";
    private String errorAlamat = "";
    private String errorKelurahan = "";
    private String errorKecamatan = "";
    private String errorKodeKab = "";
    private String errorKodePos = "";
    private String errorKodeNegara = "";
    private String errorKodeBidangUsaha = "";
    private String errorKodeHubLjk = "";
    private String errorMelanggarBmpk = "";
    private String errorMelampauiBmpk = "";
    private String errorGoPublic = "";
    private String errorKodeGol = "";
    private String errorPeringkat = "";
    private String errorLembagaPemeringkat = "";
    private String errorTglPemeringkat = "";
    private String errorNamaGroup = "";
    private String errorKodeKtrCabang = "";
    private String errorNoRekening = "";
    private String errorTglAkadAkhir = "";
    
    
    public String getFlagDetail() {
        return flagDetail;
    }

    public void setFlagDetail(String flagDetail) {
        this.flagDetail = flagDetail;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        if (cif==null){
            this.cif = "";
        }else{
            this.cif = cif;
        }
        
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        if (noIdentitas==null){
            this.noIdentitas = "";
        }else{
            this.noIdentitas = noIdentitas;
        }
        
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama==null){
            this.nama = "";
        }else{
            this.nama = nama;
        }
        
    }

    public String getKodeJenis() {
        return kodeJenis;
    }

    public void setKodeJenis(String kodeJenis) {
        if (kodeJenis==null){
            this.kodeJenis = "";
        }else{
            this.kodeJenis = kodeJenis;
        }
        
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        if (tempat==null){
            this.tempat = "";
        }else{
            this.tempat = tempat;
        }
        
    }

    public String getNoAkte() {
        return noAkte;
    }

    public void setNoAkte(String noAkte) {
        if (noAkte==null){
            this.noAkte = "";
        }else{
            this.noAkte = noAkte;
        }
        
    }

    public Date getTglAktePendirian() {
        return tglAktePendirian;
    }

    public void setTglAktePendirian(Date tglAktePendirian) {
        this.tglAktePendirian = tglAktePendirian;
    }

    public String getNoAktePerubahan() {
        return noAktePerubahan;
    }

    public void setNoAktePerubahan(String noAktePerubahan) {
        if (noAktePerubahan==null){
            this.noAktePerubahan = "";
        }else{
            this.noAktePerubahan = noAktePerubahan;
        }
        
    }

    public Date getTglAktePerubahan() {
        return tglAktePerubahan;
    }

    public void setTglAktePerubahan(Date tglAktePerubahan) {
        this.tglAktePerubahan = tglAktePerubahan;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        if (telepon==null){
            this.telepon = "";
        }else{
            this.telepon = telepon;
        }
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email==null){
            this.email = "";
        }else{
            this.email = email;
        }
        
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        if (alamat==null){
            this.alamat = "";
        }else{
            this.alamat = alamat;
        }
        
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        if (kelurahan==null){
            this.kelurahan = "";
        }else{
            this.kelurahan = kelurahan;
        }
        
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        if (kecamatan==null){
            this.kecamatan = "";
        }else{
            this.kecamatan = kecamatan;
        }
        
    }

    public String getKodeKab() {
        return kodeKab;
    }

    public void setKodeKab(String kodeKab) {
        if (kodeKab==null){
            this.kodeKab = "";
        }else{
            this.kodeKab = kodeKab;
        }
        
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        if (kodePos==null){
            this.kodePos = "";
        }else{
            this.kodePos = kodePos;
        }
        
    }

    /**
     * @return the kodeNegara
     */
    public String getKodeNegara() {
        return kodeNegara;
    }

    /**
     * @param kodeNegara the kodeNegara to set
     */
    public void setKodeNegara(String kodeNegara) {
        if (kodeNegara==null){
            this.kodeNegara = "";
        }else{
            this.kodeNegara = kodeNegara;
        }
        
    }

    /**
     * @return the kodeBidangUsaha
     */
    public String getKodeBidangUsaha() {
        return kodeBidangUsaha;
    }

    /**
     * @param kodeBidangUsaha the kodeBidangUsaha to set
     */
    public void setKodeBidangUsaha(String kodeBidangUsaha) {
        if (kodeBidangUsaha==null){
            this.kodeBidangUsaha = "";
        }else{
            this.kodeBidangUsaha = kodeBidangUsaha;
        }
        
    }

    /**
     * @return the kodeHubLjk
     */
    public String getKodeHubLjk() {
        return kodeHubLjk;
    }

    /**
     * @param kodeHubLjk the kodeHubLjk to set
     */
    public void setKodeHubLjk(String kodeHubLjk) {
        if (kodeHubLjk==null){
            this.kodeHubLjk = "";
        }else{
            this.kodeHubLjk = kodeHubLjk;
        }
        
    }

    /**
     * @return the melanggarBmpk
     */
    public String getMelanggarBmpk() {
        return melanggarBmpk;
    }

    /**
     * @param melanggarBmpk the melanggarBmpk to set
     */
    public void setMelanggarBmpk(String melanggarBmpk) {
        if (melanggarBmpk==null){
            this.melanggarBmpk = "";
        }else{
            this.melanggarBmpk = melanggarBmpk;
        }
        
    }

    /**
     * @return the melampauiBmpk
     */
    public String getMelampauiBmpk() {
        return melampauiBmpk;
    }

    /**
     * @param melampauiBmpk the melampauiBmpk to set
     */
    public void setMelampauiBmpk(String melampauiBmpk) {
        if (melampauiBmpk==null){
            this.melampauiBmpk = "";
        }else{
            this.melampauiBmpk = melampauiBmpk;
        }
        
    }

    /**
     * @return the goPublic
     */
    public String getGoPublic() {
        return goPublic;
    }

    /**
     * @param goPublic the goPublic to set
     */
    public void setGoPublic(String goPublic) {
        if (goPublic==null){
            this.goPublic = "";
        }else{
            this.goPublic = goPublic;
        }
        
    }

    /**
     * @return the kodeGol
     */
    public String getKodeGol() {
        return kodeGol;
    }

    /**
     * @param kodeGol the kodeGol to set
     */
    public void setKodeGol(String kodeGol) {
        if (kodeGol==null){
            this.kodeGol = kodeGol;
        }else{
            this.kodeGol = kodeGol;
        }
        
    }

    /**
     * @return the peringkat
     */
    public String getPeringkat() {
        return peringkat;
    }

    /**
     * @param peringkat the peringkat to set
     */
    public void setPeringkat(String peringkat) {
        if (peringkat==null){
            this.peringkat = "";
        }else{
            this.peringkat = peringkat;
        }
        
    }

    /**
     * @return the lembagaPemeringkat
     */
    public String getLembagaPemeringkat() {
        return lembagaPemeringkat;
    }

    /**
     * @param lembagaPemeringkat the lembagaPemeringkat to set
     */
    public void setLembagaPemeringkat(String lembagaPemeringkat) {
        if (lembagaPemeringkat==null){
            this.lembagaPemeringkat = "";
        }else{
            this.lembagaPemeringkat = lembagaPemeringkat;
        }
        
    }

    /**
     * @return the tglPemeringkat
     */
    public Date getTglPemeringkat() {
        return tglPemeringkat;
    }

    /**
     * @param tglPemeringkat the tglPemeringkat to set
     */
    public void setTglPemeringkat(Date tglPemeringkat) {
        this.tglPemeringkat = tglPemeringkat;
    }

    /**
     * @return the namaGroup
     */
    public String getNamaGroup() {
        return namaGroup;
    }

    /**
     * @param namaGroup the namaGroup to set
     */
    public void setNamaGroup(String namaGroup) {
        if (namaGroup==null){
            this.namaGroup = "";
        }else{
            this.namaGroup = namaGroup;
        }
        
    }

    /**
     * @return the kodeKtrCabang
     */
    public String getKodeKtrCabang() {
        return kodeKtrCabang;
    }

    /**
     * @param kodeKtrCabang the kodeKtrCabang to set
     */
    public void setKodeKtrCabang(String kodeKtrCabang) {
        if (kodeKtrCabang==null){
            this.kodeKtrCabang = "";
        }else{
            this.kodeKtrCabang = kodeKtrCabang;
        }
        
    }

    /**
     * @return the operasiData
     */
    public String getOperasiData() {
        return operasiData;
    }

    /**
     * @param operasiData the operasiData to set
     */
    public void setOperasiData(String operasiData) {
        if (operasiData==null){
            this.operasiData = "";
        }else{
            this.operasiData = operasiData;
        }
        
    }

    
    @Override
    public String getLogDetail(Entity prevDoc) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String history="";
        DebiturBdnUsaha prevMat = (DebiturBdnUsaha)prevDoc;
        
        try {
            //0
            if(prevMat == null || !prevMat.getFlagDetail().equals(this.getFlagDetail())){
                if (prevMat == null){
                    history=history+" Flag Detail : "+this.getFlagDetail()+"<br>";
                }else{
                    history=history+" Flag Detail diubah dari "+prevMat.getFlagDetail()+" menjadi "+this.getFlagDetail()+"<br>";
                }
            }
            
            //1
            if (prevMat == null || !prevMat.getCif().equals(this.getCif())) {
                if (prevMat == null){
                    history=history+" CIF : "+this.getCif()+"<br>";
                }else{
                    history = history + " CIF diubah dari " + prevMat.getCif()+ " menjadi " + this.getCif()+ "<br>";
                }
            }
            
            //2
            if (prevMat == null || !prevMat.getNoIdentitas().equals(this.getNoIdentitas())) {
                if (prevMat == null){
                    history=history+" Nomor identitas Badan Usaha : "+this.getNoIdentitas()+"<br>";
                }else{
                    history = history + " Nomor identitas Badan Usaha diubah dari " + prevMat.getNoIdentitas()+ " menjadi " + this.getNoIdentitas()+ "<br>";
                }
            }
            
            //3
            if (prevMat == null || !prevMat.getNama().equals(this.getNama())) {
                if (prevMat == null){
                    history=history+" Nama Badan Usaha : "+this.getNama()+"<br>";
                }else{
                    history = history + " Nama Badan Usaha diubah dari " + prevMat.getNama()+ " menjadi " + this.getNama()+ "<br>";
                }
            }
            
            //4
            if (prevMat == null || !prevMat.getKodeJenis().equals(this.getKodeJenis())) {
                if (prevMat == null){
                    history=history+" Kode Jenis Badan Usaha : "+this.getKodeJenis()+"<br>";
                }else{
                    history = history + " Kode Jenis Badan Usaha diubah dari " + prevMat.getKodeJenis()+ " menjadi " + this.getKodeJenis()+ "<br>";
                }
            }
            
            //5
            if (prevMat == null || !prevMat.getTempat().equals(this.getTempat())) {
                if (prevMat == null){
                    history=history+" Tempat Pendiriran : "+this.getTempat()+"<br>";
                }else{
                    history = history + " Tempat Pendirian diubah dari " + prevMat.getTempat()+ " menjadi " + this.getTempat()+ "<br>";
                }
            }
            
            //6
            if (prevMat == null || !prevMat.getNoAkte().equals(this.getNoAkte())) {
                if (prevMat == null){
                    history=history+" Nomor Akte Awal : "+this.getNoAkte()+"<br>";
                }else{
                    history = history + " Nomor Akte Awal diubah dari " + prevMat.getNoAkte()+ " menjadi " + this.getNoAkte()+ "<br>";
                }
            }
            
            //7
            if (prevMat == null || !prevMat.getTglAktePendirian().equals(this.getTglAktePendirian())) {
                if (prevMat == null){
                    history=history+" Tanggal Akte Pendirian : "+this.getTglAktePendirian()+"<br>";
                }else{
                    history = history + " Tanggal Akte Pendirian diubah dari " + prevMat.getTglAktePendirian()+ " menjadi " + this.getTglAktePendirian()+ "<br>";
                }
            }
            
            //8
            if (prevMat == null || !prevMat.getNoAktePerubahan().equals(this.getNoAktePerubahan())) {
                if (prevMat == null){
                    history=history+" No Akte Perubahan Terakhir KTP : "+this.getNoAktePerubahan()+"<br>";
                }else{
                    history = history + " No Akte Perubahan Terakhir KTP diubah dari " + prevMat.getNoAktePerubahan()+ " menjadi " + this.getNoAktePerubahan()+ "<br>";
                }
            }
            
            //9
            if (prevMat == null || !prevMat.getTglAktePerubahan().equals(this.getTglAktePerubahan())) {
                if (prevMat == null){
                    history=history+" Tanggal Akte Perubahan Terakhir : "+this.getTglAktePerubahan()+"<br>";
                }else{
                    history = history + " Tanggal Akte Perubahan Terakhir diubah dari " + prevMat.getTglAktePerubahan()+ " menjadi " + this.getTglAktePerubahan()+ "<br>";
                }
            }
            
            //10
            if (prevMat == null || !prevMat.getTelepon().equals(this.getTelepon())) {
                if (prevMat == null){
                    history=history+" Telepon : "+this.getTelepon()+"<br>";
                }else{
                    history = history + " Telepon diubah dari " + prevMat.getTelepon()+ " menjadi " + this.getTelepon()+ "<br>";
                }
            }
            
            //11
            if (prevMat == null || !prevMat.getEmail().equals(this.getEmail())) {
                if (prevMat == null){
                    history=history+" Alamat Email : "+this.getEmail()+"<br>";
                }else{
                    history = history + " Alamat Email diubah dari " + prevMat.getEmail()+ " menjadi " + this.getEmail()+ "<br>";
                }
            }
            
            //12
            if (prevMat == null || !prevMat.getAlamat().equals(this.getAlamat())) {
                if (prevMat == null){
                    history=history+" Alamat : "+this.getAlamat()+"<br>";
                }else{
                    history = history + " Alamat diubah dari " + prevMat.getAlamat()+ " menjadi " + this.getAlamat()+ "<br>";
                }
            }
            
            //13
            if (prevMat == null || !prevMat.getKelurahan().equals(this.getKelurahan())) {
                if (prevMat == null){
                    history=history+" Kelurahan : "+this.getKelurahan()+"<br>";
                }else{
                    history = history + " Kelurahan diubah dari " + prevMat.getKelurahan()+ " menjadi " + this.getKelurahan()+ "<br>";
                }                
            }
            
            //14
            if (prevMat == null || !prevMat.getKecamatan().equals(this.getKecamatan())) {
                if (prevMat == null){
                    history=history+" Kecamatan : "+this.getKecamatan()+"<br>";
                }else{
                    history = history + " Kecamatan diubah dari " + prevMat.getKecamatan()+ " menjadi " + this.getKecamatan()+ "<br>";
                } 
            }
            
            //15
            if (prevMat == null || !prevMat.getKodeKab().equals(this.getKodeKab())) {
                if (prevMat == null){
                    history=history+" Kode Kab/Kota (Dati ii) : "+this.getKodeKab()+"<br>";
                }else{
                    history = history + " Kode Kab/Kota (Dati ii) diubah dari " + prevMat.getKodeKab()+ " menjadi " + this.getKodeKab()+ "<br>";
                }                
            }
            
            //16
            if (prevMat == null || !prevMat.getKodePos().equals(this.getKodePos())) {
                if (prevMat == null){
                    history=history+" Kode Pos : "+this.getKodePos()+"<br>";
                }else{
                    history = history + " Kode Pos diubah dari " + prevMat.getKodePos()+ " menjadi " + this.getKodePos()+ "<br>";
                } 
            }
            
            //17
            if (prevMat == null || !prevMat.getKodeNegara().equals(this.getKodeNegara())) {
                if (prevMat == null){
                    history=history+" Kode Negara Domisili : "+this.getKodeNegara()+"<br>";
                }else{
                    history = history + " Kode Negara Domisili diubah dari " + prevMat.getKodeNegara()+ " menjadi " + this.getKodeNegara()+ "<br>";
                } 
            }
            
            //18
            if (prevMat == null || !prevMat.getKodeBidangUsaha().equals(this.getKodeBidangUsaha())) {
                if (prevMat == null){
                    history=history+" Kode Bidang Usaha : "+this.getKodeBidangUsaha()+"<br>";
                }else{
                    history = history + " Kode Bidang Usaha diubah dari " + prevMat.getKodeBidangUsaha()+ " menjadi " + this.getKodeBidangUsaha()+ "<br>";
                } 
            }
            
            //19
            if (prevMat == null || !prevMat.getKodeHubLjk().equals(this.getKodeHubLjk())) {
                if (prevMat == null){
                    history=history+" Kode hubungan dengan LJK : "+this.getKodeHubLjk()+"<br>";
                }else{
                    history = history + " Kode hubungan dengan LJK diubah dari " + prevMat.getKodeHubLjk()+ " menjadi " + this.getKodeHubLjk()+ "<br>";
                } 
            }
            
            //20
            if (prevMat == null || !prevMat.getMelanggarBmpk().equals(this.getMelanggarBmpk())) {
                if (prevMat == null){
                    history=history+" Melanggar BMPK/BMPD/BMPP : "+this.getMelanggarBmpk()+"<br>";
                }else{
                    history = history + " Melanggar BMPK/BMPD/BMPP diubah dari " + prevMat.getMelanggarBmpk()+ " menjadi " + this.getMelanggarBmpk()+ "<br>";
                } 
            }
            
            //21
            if (prevMat == null || !prevMat.getMelampauiBmpk().equals(this.getMelampauiBmpk())) {
                if (prevMat == null){
                    history=history+" Melampaui BMPK/BMPD/BMPP : "+this.getMelampauiBmpk()+"<br>";
                }else{
                    history = history + " Melampaui BMPK/BMPD/BMPP diubah dari " + prevMat.getMelampauiBmpk()+ " menjadi " + this.getMelampauiBmpk()+ "<br>";
                } 
            }
            
            //22
            if (prevMat == null || !prevMat.getGoPublic().equals(this.getGoPublic())) {
                if (prevMat == null){
                    history=history+" Go Public : "+this.getGoPublic()+"<br>";
                }else{
                   history = history + " Go Public diubah dari " + prevMat.getGoPublic()+ " menjadi " + this.getGoPublic()+ "<br>";
                } 
            }
            
            //23
            if (prevMat == null || !prevMat.getKodeGol().equals(this.getKodeGol())) {
                if (prevMat == null){
                    history=history+" Kode Golongan Debitur : "+this.getKodeGol()+"<br>";
                }else{
                    history = history + " Kode Golongan Debitur diubah dari " + prevMat.getKodeGol()+ " menjadi " + this.getKodeGol()+ "<br>";
                } 
            }
            
            //24
            if (prevMat == null || !prevMat.getPeringkat().equals(this.getPeringkat())) {
                if (prevMat == null){
                    history=history+" Peringkat/Ranking Debitur : "+this.getPeringkat()+"<br>";
                }else{
                    history = history + " Peringkat/Ranking Debitur diubah dari " + prevMat.getPeringkat()+ " menjadi " + this.getPeringkat()+ "<br>";
                } 
            }
            
            //25
            if (prevMat == null || !prevMat.getLembagaPemeringkat().equals(this.getLembagaPemeringkat())) {
                if (prevMat == null){
                    history=history+" Lembaga Pemeringkat/Rating : "+this.getLembagaPemeringkat()+"<br>";
                }else{
                    history = history + " Lembaga Pemeringkat/Rating diubah dari " + prevMat.getLembagaPemeringkat()+ " menjadi " + this.getLembagaPemeringkat()+ "<br>";
                } 
            }
            
            //26
            if (prevMat == null || !prevMat.getTglPemeringkat().equals(this.getTglPemeringkat())) {
                if (prevMat == null){
                    history=history+" Tanggal Pemeringkat : "+this.getTglPemeringkat()+"<br>";
                }else{
                    history = history + " Tanggal Pemeringkat diubah dari " + prevMat.getTglPemeringkat()+ " menjadi " + this.getTglPemeringkat()+ "<br>";
                }
            }
            
            //27
            if (prevMat == null || !prevMat.getNamaGroup().equals(this.getNamaGroup())) {
                if (prevMat == null){
                    history=history+" Nama Group Debitur : "+this.getNamaGroup()+"<br>";
                }else{
                    history = history + " Nama Group Debitur diubah dari " + prevMat.getNamaGroup()+ " menjadi " + this.getNamaGroup()+ "<br>";
                }
            }
            
            //28
            if (prevMat == null || !prevMat.getKodeKtrCabang().equals(this.getKodeKtrCabang())) {
                if (prevMat == null){
                    history=history+" Kode Kantor Cabang : "+this.getKodeKtrCabang()+"<br>";
                }else{
                    history = history + " Kode Kantor Cabang diubah dari " + prevMat.getKodeKtrCabang()+ " menjadi " + this.getKodeKtrCabang()+ "<br>";
                }
            }
            
            //29
            if (prevMat == null || !prevMat.getOperasiData().equals(this.getOperasiData())) {
                if (prevMat == null){
                    history=history+" Operasi Data Cabang : "+this.getOperasiData()+"<br>";
                }else{
                    history = history + " Operasi Data diubah dari " + prevMat.getOperasiData()+ " menjadi " + this.getOperasiData()+ "<br>";
                }
            }
            
        } catch (Exception es) {
            System.out.println(""+es.toString()+"");
        }  
        
        return history;
    }

    /**
     * @return the periodeId
     */
    public long getPeriodeId() {
        return periodeId;
    }

    /**
     * @param periodeId the periodeId to set
     */
    public void setPeriodeId(long periodeId) {
        this.periodeId = periodeId;
    }

    /**
     * @return the kodeJenisNsb
     */
    public String getKodeJenisNsb() {
        return kodeJenisNsb;
    }

    /**
     * @param kodeJenisNsb the kodeJenisNsb to set
     */
    public void setKodeJenisNsb(String kodeJenisNsb) {
        if (kodeJenisNsb==null){
            this.kodeJenisNsb = "";
        }else{
            this.kodeJenisNsb = kodeJenisNsb;
        }
        
    }

    /**
     * @return the sqlHistory
     */
    public String getSqlHistory() {
        return sqlHistory;
    }

    /**
     * @param sqlHistory the sqlHistory to set
     */
    public void setSqlHistory(String sqlHistory) {
        this.sqlHistory = sqlHistory;
    }

    /**
     * @return the statusOperasiData
     */
    public int getStatusOperasiData() {
        return statusOperasiData;
    }

    /**
     * @param statusOperasiData the statusOperasiData to set
     */
    public void setStatusOperasiData(int statusOperasiData) {
        this.statusOperasiData = statusOperasiData;
    }

    /**
     * @return the teleponSeluler
     */
    public String getTeleponSeluler() {
        return teleponSeluler;
    }

    /**
     * @param teleponSeluler the teleponSeluler to set
     */
    public void setTeleponSeluler(String teleponSeluler) {
        //this.teleponSeluler = teleponSeluler;
        if (teleponSeluler==null){
            this.teleponSeluler = "";
        }else{
            this.teleponSeluler = teleponSeluler;
        }
    }

    /**
     * @return the statusData
     */
    public int getStatusData() {
        return statusData;
    }

    /**
     * @param statusData the statusData to set
     */
    public void setStatusData(int statusData) {
        this.statusData = statusData;
    }

    /**
     * @return the noRekening
     */
    public String getNoRekening() {
        return noRekening;
    }

    /**
     * @param noRekening the noRekening to set
     */
    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    /**
     * @return the tglAkadAkhir
     */
    public Date getTglAkadAkhir() {
        return tglAkadAkhir;
    }

    /**
     * @param tglAkadAkhir the tglAkadAkhir to set
     */
    public void setTglAkadAkhir(Date tglAkadAkhir) {
        this.tglAkadAkhir = tglAkadAkhir;
    }

    @Override
    public Entity getValidasiDetail(Entity prevDoc) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DebiturBdnUsaha debiturBadanUsahaValidasi = (DebiturBdnUsaha)prevDoc;
        debiturBadanUsahaValidasi.setIsNotValid(false);
        
        try{
            if(debiturBadanUsahaValidasi.getPeringkat()!=null){
                if(!debiturBadanUsahaValidasi.getPeringkat().equals("")){
                    
                    if(debiturBadanUsahaValidasi.getTglPemeringkat()==null){
                        debiturBadanUsahaValidasi.setValidasiTglPemeringkat("data-required='required'");
                        debiturBadanUsahaValidasi.setErrorTglPemeringkat("TANGGAL PEMERINGKATAN, wajib diisi. Field nomor PERINGKAT/RATING DEBITUR terisi");
                        debiturBadanUsahaValidasi.setIsNotValid(true);
                    }
                    
                    if(debiturBadanUsahaValidasi.getLembagaPemeringkat().equals("")){
                        debiturBadanUsahaValidasi.setValidasiLembagaPemeringkat("data-required='required'");
                        debiturBadanUsahaValidasi.setErrorLembagaPemeringkat("LEMBAGA PEMERINGKAT/RATING, wajib diisi. Field nomor PERINGKAT/RATING DEBITUR terisi");
                        debiturBadanUsahaValidasi.setIsNotValid(true);
                    }
                }
            }
        }catch(Exception es){
        }
        
        try{
             if(!debiturBadanUsahaValidasi.getLembagaPemeringkat().equals("")){
                    if(debiturBadanUsahaValidasi.getTglPemeringkat()==null){
                        debiturBadanUsahaValidasi.setValidasiTglPemeringkat("data-required='required'");
                        debiturBadanUsahaValidasi.setErrorTglPemeringkat("TANGGAL PEMERINGKATAN, wajib diisi. Field nomor PERINGKAT/RATING DEBITUR terisi");
                        debiturBadanUsahaValidasi.setIsNotValid(true);
                    }
                    
                    if(debiturBadanUsahaValidasi.getPeringkat().equals("")){
                        debiturBadanUsahaValidasi.setValidasiPeringkat("data-required='required'");
                        debiturBadanUsahaValidasi.setErrorPeringkat(" PEMERINGKATAN, wajib diisi. Field nomor LEMBAGA PEMERINGKAT/RATING DEBITUR terisi");
                        debiturBadanUsahaValidasi.setIsNotValid(true);
                    }
             }
        }catch(Exception es){
        }
        
        try{
            if(debiturBadanUsahaValidasi.getTglAktePerubahan()!=null || debiturBadanUsahaValidasi.getTglAktePendirian()!=null){
                if(debiturBadanUsahaValidasi.getTglAktePerubahan().compareTo(debiturBadanUsahaValidasi.getTglAktePendirian())==0){
                    if(debiturBadanUsahaValidasi.getNoAkte()!=null || debiturBadanUsahaValidasi.getNoAktePerubahan()!=null){
                        if(!debiturBadanUsahaValidasi.getNoAkte().trim().equals(debiturBadanUsahaValidasi.getNoAktePerubahan().trim())){
                                debiturBadanUsahaValidasi.setNoAktePerubahan("");
                                debiturBadanUsahaValidasi.setValidasiNoAktePerubahan("data-required='required'");
                                debiturBadanUsahaValidasi.setErrorNoAktePerubahan("NOMOR AKTE PERUBAHAN TERAKHIR, NOMOR AKTE PENDIRIAN harus sama dengan NOMOR AKTE PERUBAHAN TERAKHIR, karena TANGGAL AKTE PENDIRIAN = TGL AKTE PERUBAHAN TERAKHIR");
                                debiturBadanUsahaValidasi.setIsNotValid(true);
                        }
                    }
                }
            }
        }catch(Exception ex){
        
        }
        
        try{
            if(debiturBadanUsahaValidasi.getTempat()!=null){
                boolean validLetter =  SessValidasiInputan.validateLettersDebiturIndividu(debiturBadanUsahaValidasi.getTempat());
                if(!validLetter){
                    debiturBadanUsahaValidasi.setTempat("");
                    debiturBadanUsahaValidasi.setValidasiTempat("data-required='required'");
                    debiturBadanUsahaValidasi.setErrorTempat("TEMPAT PENDIRIAN, format data harus huruf, spasi dan karakter -'., Wajib di perbaiki di core");
                    debiturBadanUsahaValidasi.setIsNotValid(true);
                }
            }
        }catch(Exception ex){
        
        }
        
         try{
            if(debiturBadanUsahaValidasi.getNoAkte()!=null){
                boolean validLetter =  SessValidasiInputan.validateNoAkte(debiturBadanUsahaValidasi.getNoAkte());
                if(!validLetter){
                    debiturBadanUsahaValidasi.setNoAkte("");
                    debiturBadanUsahaValidasi.setValidasiNoAkte("data-required='required'");
                    debiturBadanUsahaValidasi.setErrorNoAkte("NOMOR AKTE PERUBAHAN TERAKHIR, format data harus huruf, angka, spasi dan karakter /'-_()., Wajib di perbaiki di core");
                     debiturBadanUsahaValidasi.setIsNotValid(true);
                }
            }
        }catch(Exception ex){
        }
        
        try{
            if(debiturBadanUsahaValidasi.getNoAktePerubahan()!=null){
                boolean validLetter =  SessValidasiInputan.validateNoAkte(debiturBadanUsahaValidasi.getNoAktePerubahan());
                if(!validLetter){
                    debiturBadanUsahaValidasi.setNoAktePerubahan("");
                    debiturBadanUsahaValidasi.setValidasiNoAktePerubahan("data-required='required'");
                    debiturBadanUsahaValidasi.setErrorNoAktePerubahan("NOMOR AKTE PERUBAHAN TERAKHIR, format data harus huruf, angka, spasi dan karakter /'-_()., Wajib di perbaiki di core");
                     debiturBadanUsahaValidasi.setIsNotValid(true);
                }
            }
        }catch(Exception ex){
        }
        
        return  debiturBadanUsahaValidasi;
    }

    /**
     * @return the isNotValid
     */
    public boolean isIsNotValid() {
        return isNotValid;
    }

    /**
     * @param isNotValid the isNotValid to set
     */
    public void setIsNotValid(boolean isNotValid) {
        this.isNotValid = isNotValid;
    }

    /**
     * @return the validasiCif
     */
    public String getValidasiCif() {
        return validasiCif;
    }

    /**
     * @param validasiCif the validasiCif to set
     */
    public void setValidasiCif(String validasiCif) {
        this.validasiCif = validasiCif;
    }

    /**
     * @return the validasiNoIdentitas
     */
    public String getValidasiNoIdentitas() {
        return validasiNoIdentitas;
    }

    /**
     * @param validasiNoIdentitas the validasiNoIdentitas to set
     */
    public void setValidasiNoIdentitas(String validasiNoIdentitas) {
        this.validasiNoIdentitas = validasiNoIdentitas;
    }

    /**
     * @return the validasiNama
     */
    public String getValidasiNama() {
        return validasiNama;
    }

    /**
     * @param validasiNama the validasiNama to set
     */
    public void setValidasiNama(String validasiNama) {
        this.validasiNama = validasiNama;
    }

    /**
     * @return the validasiKodeJenis
     */
    public String getValidasiKodeJenis() {
        return validasiKodeJenis;
    }

    /**
     * @param validasiKodeJenis the validasiKodeJenis to set
     */
    public void setValidasiKodeJenis(String validasiKodeJenis) {
        this.validasiKodeJenis = validasiKodeJenis;
    }

    /**
     * @return the validasiTempat
     */
    public String getValidasiTempat() {
        return validasiTempat;
    }

    /**
     * @param validasiTempat the validasiTempat to set
     */
    public void setValidasiTempat(String validasiTempat) {
        this.validasiTempat = validasiTempat;
    }

    /**
     * @return the validasiNoAkte
     */
    public String getValidasiNoAkte() {
        return validasiNoAkte;
    }

    /**
     * @param validasiNoAkte the validasiNoAkte to set
     */
    public void setValidasiNoAkte(String validasiNoAkte) {
        this.validasiNoAkte = validasiNoAkte;
    }

    /**
     * @return the validasiTglAktePendirian
     */
    public String getValidasiTglAktePendirian() {
        return validasiTglAktePendirian;
    }

    /**
     * @param validasiTglAktePendirian the validasiTglAktePendirian to set
     */
    public void setValidasiTglAktePendirian(String validasiTglAktePendirian) {
        this.validasiTglAktePendirian = validasiTglAktePendirian;
    }

    /**
     * @return the validasiNoAktePerubahan
     */
    public String getValidasiNoAktePerubahan() {
        return validasiNoAktePerubahan;
    }

    /**
     * @param validasiNoAktePerubahan the validasiNoAktePerubahan to set
     */
    public void setValidasiNoAktePerubahan(String validasiNoAktePerubahan) {
        this.validasiNoAktePerubahan = validasiNoAktePerubahan;
    }

    /**
     * @return the validasiTglAktePerubahan
     */
    public String getValidasiTglAktePerubahan() {
        return validasiTglAktePerubahan;
    }

    /**
     * @param validasiTglAktePerubahan the validasiTglAktePerubahan to set
     */
    public void setValidasiTglAktePerubahan(String validasiTglAktePerubahan) {
        this.validasiTglAktePerubahan = validasiTglAktePerubahan;
    }

    /**
     * @return the validasiTelepon
     */
    public String getValidasiTelepon() {
        return validasiTelepon;
    }

    /**
     * @param validasiTelepon the validasiTelepon to set
     */
    public void setValidasiTelepon(String validasiTelepon) {
        this.validasiTelepon = validasiTelepon;
    }

    /**
     * @return the validasiTeleponSeluler
     */
    public String getValidasiTeleponSeluler() {
        return validasiTeleponSeluler;
    }

    /**
     * @param validasiTeleponSeluler the validasiTeleponSeluler to set
     */
    public void setValidasiTeleponSeluler(String validasiTeleponSeluler) {
        this.validasiTeleponSeluler = validasiTeleponSeluler;
    }

    /**
     * @return the validasiEmail
     */
    public String getValidasiEmail() {
        return validasiEmail;
    }

    /**
     * @param validasiEmail the validasiEmail to set
     */
    public void setValidasiEmail(String validasiEmail) {
        this.validasiEmail = validasiEmail;
    }

    /**
     * @return the validasiAlamat
     */
    public String getValidasiAlamat() {
        return validasiAlamat;
    }

    /**
     * @param validasiAlamat the validasiAlamat to set
     */
    public void setValidasiAlamat(String validasiAlamat) {
        this.validasiAlamat = validasiAlamat;
    }

    /**
     * @return the validasiKelurahan
     */
    public String getValidasiKelurahan() {
        return validasiKelurahan;
    }

    /**
     * @param validasiKelurahan the validasiKelurahan to set
     */
    public void setValidasiKelurahan(String validasiKelurahan) {
        this.validasiKelurahan = validasiKelurahan;
    }

    /**
     * @return the validasiKecamatan
     */
    public String getValidasiKecamatan() {
        return validasiKecamatan;
    }

    /**
     * @param validasiKecamatan the validasiKecamatan to set
     */
    public void setValidasiKecamatan(String validasiKecamatan) {
        this.validasiKecamatan = validasiKecamatan;
    }

    /**
     * @return the validasiKodeKab
     */
    public String getValidasiKodeKab() {
        return validasiKodeKab;
    }

    /**
     * @param validasiKodeKab the validasiKodeKab to set
     */
    public void setValidasiKodeKab(String validasiKodeKab) {
        this.validasiKodeKab = validasiKodeKab;
    }

    /**
     * @return the validasiKodePos
     */
    public String getValidasiKodePos() {
        return validasiKodePos;
    }

    /**
     * @param validasiKodePos the validasiKodePos to set
     */
    public void setValidasiKodePos(String validasiKodePos) {
        this.validasiKodePos = validasiKodePos;
    }

    /**
     * @return the validasiKodeNegara
     */
    public String getValidasiKodeNegara() {
        return validasiKodeNegara;
    }

    /**
     * @param validasiKodeNegara the validasiKodeNegara to set
     */
    public void setValidasiKodeNegara(String validasiKodeNegara) {
        this.validasiKodeNegara = validasiKodeNegara;
    }

    /**
     * @return the validasiKodeBidangUsaha
     */
    public String getValidasiKodeBidangUsaha() {
        return validasiKodeBidangUsaha;
    }

    /**
     * @param validasiKodeBidangUsaha the validasiKodeBidangUsaha to set
     */
    public void setValidasiKodeBidangUsaha(String validasiKodeBidangUsaha) {
        this.validasiKodeBidangUsaha = validasiKodeBidangUsaha;
    }

    /**
     * @return the validasiKodeHubLjk
     */
    public String getValidasiKodeHubLjk() {
        return validasiKodeHubLjk;
    }

    /**
     * @param validasiKodeHubLjk the validasiKodeHubLjk to set
     */
    public void setValidasiKodeHubLjk(String validasiKodeHubLjk) {
        this.validasiKodeHubLjk = validasiKodeHubLjk;
    }

    /**
     * @return the validasiMelanggarBmpk
     */
    public String getValidasiMelanggarBmpk() {
        return validasiMelanggarBmpk;
    }

    /**
     * @param validasiMelanggarBmpk the validasiMelanggarBmpk to set
     */
    public void setValidasiMelanggarBmpk(String validasiMelanggarBmpk) {
        this.validasiMelanggarBmpk = validasiMelanggarBmpk;
    }

    /**
     * @return the validasiMelampauiBmpk
     */
    public String getValidasiMelampauiBmpk() {
        return validasiMelampauiBmpk;
    }

    /**
     * @param validasiMelampauiBmpk the validasiMelampauiBmpk to set
     */
    public void setValidasiMelampauiBmpk(String validasiMelampauiBmpk) {
        this.validasiMelampauiBmpk = validasiMelampauiBmpk;
    }

    /**
     * @return the validasiGoPublic
     */
    public String getValidasiGoPublic() {
        return validasiGoPublic;
    }

    /**
     * @param validasiGoPublic the validasiGoPublic to set
     */
    public void setValidasiGoPublic(String validasiGoPublic) {
        this.validasiGoPublic = validasiGoPublic;
    }

    /**
     * @return the validasiKodeGol
     */
    public String getValidasiKodeGol() {
        return validasiKodeGol;
    }

    /**
     * @param validasiKodeGol the validasiKodeGol to set
     */
    public void setValidasiKodeGol(String validasiKodeGol) {
        this.validasiKodeGol = validasiKodeGol;
    }

    /**
     * @return the validasiPeringkat
     */
    public String getValidasiPeringkat() {
        return validasiPeringkat;
    }

    /**
     * @param validasiPeringkat the validasiPeringkat to set
     */
    public void setValidasiPeringkat(String validasiPeringkat) {
        this.validasiPeringkat = validasiPeringkat;
    }

    /**
     * @return the validasiLembagaPemeringkat
     */
    public String getValidasiLembagaPemeringkat() {
        return validasiLembagaPemeringkat;
    }

    /**
     * @param validasiLembagaPemeringkat the validasiLembagaPemeringkat to set
     */
    public void setValidasiLembagaPemeringkat(String validasiLembagaPemeringkat) {
        this.validasiLembagaPemeringkat = validasiLembagaPemeringkat;
    }

    /**
     * @return the validasiTglPemeringkat
     */
    public String getValidasiTglPemeringkat() {
        return validasiTglPemeringkat;
    }

    /**
     * @param validasiTglPemeringkat the validasiTglPemeringkat to set
     */
    public void setValidasiTglPemeringkat(String validasiTglPemeringkat) {
        this.validasiTglPemeringkat = validasiTglPemeringkat;
    }

    /**
     * @return the validasiNamaGroup
     */
    public String getValidasiNamaGroup() {
        return validasiNamaGroup;
    }

    /**
     * @param validasiNamaGroup the validasiNamaGroup to set
     */
    public void setValidasiNamaGroup(String validasiNamaGroup) {
        this.validasiNamaGroup = validasiNamaGroup;
    }

    /**
     * @return the validasiKodeKtrCabang
     */
    public String getValidasiKodeKtrCabang() {
        return validasiKodeKtrCabang;
    }

    /**
     * @param validasiKodeKtrCabang the validasiKodeKtrCabang to set
     */
    public void setValidasiKodeKtrCabang(String validasiKodeKtrCabang) {
        this.validasiKodeKtrCabang = validasiKodeKtrCabang;
    }

    /**
     * @return the validasiNoRekening
     */
    public String getValidasiNoRekening() {
        return validasiNoRekening;
    }

    /**
     * @param validasiNoRekening the validasiNoRekening to set
     */
    public void setValidasiNoRekening(String validasiNoRekening) {
        this.validasiNoRekening = validasiNoRekening;
    }

    /**
     * @return the validasiTglAkadAkhir
     */
    public String getValidasiTglAkadAkhir() {
        return validasiTglAkadAkhir;
    }

    /**
     * @param validasiTglAkadAkhir the validasiTglAkadAkhir to set
     */
    public void setValidasiTglAkadAkhir(String validasiTglAkadAkhir) {
        this.validasiTglAkadAkhir = validasiTglAkadAkhir;
    }

    /**
     * @return the errorCif
     */
    public String getErrorCif() {
        return errorCif;
    }

    /**
     * @param errorCif the errorCif to set
     */
    public void setErrorCif(String errorCif) {
        this.errorCif = errorCif;
    }

    /**
     * @return the errorNoIdentitas
     */
    public String getErrorNoIdentitas() {
        return errorNoIdentitas;
    }

    /**
     * @param errorNoIdentitas the errorNoIdentitas to set
     */
    public void setErrorNoIdentitas(String errorNoIdentitas) {
        this.errorNoIdentitas = errorNoIdentitas;
    }

    /**
     * @return the errorNama
     */
    public String getErrorNama() {
        return errorNama;
    }

    /**
     * @param errorNama the errorNama to set
     */
    public void setErrorNama(String errorNama) {
        this.errorNama = errorNama;
    }

    /**
     * @return the errorKodeJenis
     */
    public String getErrorKodeJenis() {
        return errorKodeJenis;
    }

    /**
     * @param errorKodeJenis the errorKodeJenis to set
     */
    public void setErrorKodeJenis(String errorKodeJenis) {
        this.errorKodeJenis = errorKodeJenis;
    }

    /**
     * @return the errorTempat
     */
    public String getErrorTempat() {
        return errorTempat;
    }

    /**
     * @param errorTempat the errorTempat to set
     */
    public void setErrorTempat(String errorTempat) {
        this.errorTempat = errorTempat;
    }

    /**
     * @return the errorNoAkte
     */
    public String getErrorNoAkte() {
        return errorNoAkte;
    }

    /**
     * @param errorNoAkte the errorNoAkte to set
     */
    public void setErrorNoAkte(String errorNoAkte) {
        this.errorNoAkte = errorNoAkte;
    }

    /**
     * @return the errorTglAktePendirian
     */
    public String getErrorTglAktePendirian() {
        return errorTglAktePendirian;
    }

    /**
     * @param errorTglAktePendirian the errorTglAktePendirian to set
     */
    public void setErrorTglAktePendirian(String errorTglAktePendirian) {
        this.errorTglAktePendirian = errorTglAktePendirian;
    }

    /**
     * @return the errorNoAktePerubahan
     */
    public String getErrorNoAktePerubahan() {
        return errorNoAktePerubahan;
    }

    /**
     * @param errorNoAktePerubahan the errorNoAktePerubahan to set
     */
    public void setErrorNoAktePerubahan(String errorNoAktePerubahan) {
        this.errorNoAktePerubahan = errorNoAktePerubahan;
    }

    /**
     * @return the errorTglAktePerubahan
     */
    public String getErrorTglAktePerubahan() {
        return errorTglAktePerubahan;
    }

    /**
     * @param errorTglAktePerubahan the errorTglAktePerubahan to set
     */
    public void setErrorTglAktePerubahan(String errorTglAktePerubahan) {
        this.errorTglAktePerubahan = errorTglAktePerubahan;
    }

    /**
     * @return the errorTelepon
     */
    public String getErrorTelepon() {
        return errorTelepon;
    }

    /**
     * @param errorTelepon the errorTelepon to set
     */
    public void setErrorTelepon(String errorTelepon) {
        this.errorTelepon = errorTelepon;
    }

    /**
     * @return the errorTeleponSeluler
     */
    public String getErrorTeleponSeluler() {
        return errorTeleponSeluler;
    }

    /**
     * @param errorTeleponSeluler the errorTeleponSeluler to set
     */
    public void setErrorTeleponSeluler(String errorTeleponSeluler) {
        this.errorTeleponSeluler = errorTeleponSeluler;
    }

    /**
     * @return the errorEmail
     */
    public String getErrorEmail() {
        return errorEmail;
    }

    /**
     * @param errorEmail the errorEmail to set
     */
    public void setErrorEmail(String errorEmail) {
        this.errorEmail = errorEmail;
    }

    /**
     * @return the errorAlamat
     */
    public String getErrorAlamat() {
        return errorAlamat;
    }

    /**
     * @param errorAlamat the errorAlamat to set
     */
    public void setErrorAlamat(String errorAlamat) {
        this.errorAlamat = errorAlamat;
    }

    /**
     * @return the errorKelurahan
     */
    public String getErrorKelurahan() {
        return errorKelurahan;
    }

    /**
     * @param errorKelurahan the errorKelurahan to set
     */
    public void setErrorKelurahan(String errorKelurahan) {
        this.errorKelurahan = errorKelurahan;
    }

    /**
     * @return the errorKecamatan
     */
    public String getErrorKecamatan() {
        return errorKecamatan;
    }

    /**
     * @param errorKecamatan the errorKecamatan to set
     */
    public void setErrorKecamatan(String errorKecamatan) {
        this.errorKecamatan = errorKecamatan;
    }

    /**
     * @return the errorKodeKab
     */
    public String getErrorKodeKab() {
        return errorKodeKab;
    }

    /**
     * @param errorKodeKab the errorKodeKab to set
     */
    public void setErrorKodeKab(String errorKodeKab) {
        this.errorKodeKab = errorKodeKab;
    }

    /**
     * @return the errorKodePos
     */
    public String getErrorKodePos() {
        return errorKodePos;
    }

    /**
     * @param errorKodePos the errorKodePos to set
     */
    public void setErrorKodePos(String errorKodePos) {
        this.errorKodePos = errorKodePos;
    }

    /**
     * @return the errorKodeNegara
     */
    public String getErrorKodeNegara() {
        return errorKodeNegara;
    }

    /**
     * @param errorKodeNegara the errorKodeNegara to set
     */
    public void setErrorKodeNegara(String errorKodeNegara) {
        this.errorKodeNegara = errorKodeNegara;
    }

    /**
     * @return the errorKodeBidangUsaha
     */
    public String getErrorKodeBidangUsaha() {
        return errorKodeBidangUsaha;
    }

    /**
     * @param errorKodeBidangUsaha the errorKodeBidangUsaha to set
     */
    public void setErrorKodeBidangUsaha(String errorKodeBidangUsaha) {
        this.errorKodeBidangUsaha = errorKodeBidangUsaha;
    }

    /**
     * @return the errorKodeHubLjk
     */
    public String getErrorKodeHubLjk() {
        return errorKodeHubLjk;
    }

    /**
     * @param errorKodeHubLjk the errorKodeHubLjk to set
     */
    public void setErrorKodeHubLjk(String errorKodeHubLjk) {
        this.errorKodeHubLjk = errorKodeHubLjk;
    }

    /**
     * @return the errorMelanggarBmpk
     */
    public String getErrorMelanggarBmpk() {
        return errorMelanggarBmpk;
    }

    /**
     * @param errorMelanggarBmpk the errorMelanggarBmpk to set
     */
    public void setErrorMelanggarBmpk(String errorMelanggarBmpk) {
        this.errorMelanggarBmpk = errorMelanggarBmpk;
    }

    /**
     * @return the errorMelampauiBmpk
     */
    public String getErrorMelampauiBmpk() {
        return errorMelampauiBmpk;
    }

    /**
     * @param errorMelampauiBmpk the errorMelampauiBmpk to set
     */
    public void setErrorMelampauiBmpk(String errorMelampauiBmpk) {
        this.errorMelampauiBmpk = errorMelampauiBmpk;
    }

    /**
     * @return the errorGoPublic
     */
    public String getErrorGoPublic() {
        return errorGoPublic;
    }

    /**
     * @param errorGoPublic the errorGoPublic to set
     */
    public void setErrorGoPublic(String errorGoPublic) {
        this.errorGoPublic = errorGoPublic;
    }

    /**
     * @return the errorKodeGol
     */
    public String getErrorKodeGol() {
        return errorKodeGol;
    }

    /**
     * @param errorKodeGol the errorKodeGol to set
     */
    public void setErrorKodeGol(String errorKodeGol) {
        this.errorKodeGol = errorKodeGol;
    }

    /**
     * @return the errorPeringkat
     */
    public String getErrorPeringkat() {
        return errorPeringkat;
    }

    /**
     * @param errorPeringkat the errorPeringkat to set
     */
    public void setErrorPeringkat(String errorPeringkat) {
        this.errorPeringkat = errorPeringkat;
    }

    /**
     * @return the errorLembagaPemeringkat
     */
    public String getErrorLembagaPemeringkat() {
        return errorLembagaPemeringkat;
    }

    /**
     * @param errorLembagaPemeringkat the errorLembagaPemeringkat to set
     */
    public void setErrorLembagaPemeringkat(String errorLembagaPemeringkat) {
        this.errorLembagaPemeringkat = errorLembagaPemeringkat;
    }

    /**
     * @return the errorTglPemeringkat
     */
    public String getErrorTglPemeringkat() {
        return errorTglPemeringkat;
    }

    /**
     * @param errorTglPemeringkat the errorTglPemeringkat to set
     */
    public void setErrorTglPemeringkat(String errorTglPemeringkat) {
        this.errorTglPemeringkat = errorTglPemeringkat;
    }

    /**
     * @return the errorNamaGroup
     */
    public String getErrorNamaGroup() {
        return errorNamaGroup;
    }

    /**
     * @param errorNamaGroup the errorNamaGroup to set
     */
    public void setErrorNamaGroup(String errorNamaGroup) {
        this.errorNamaGroup = errorNamaGroup;
    }

    /**
     * @return the errorKodeKtrCabang
     */
    public String getErrorKodeKtrCabang() {
        return errorKodeKtrCabang;
    }

    /**
     * @param errorKodeKtrCabang the errorKodeKtrCabang to set
     */
    public void setErrorKodeKtrCabang(String errorKodeKtrCabang) {
        this.errorKodeKtrCabang = errorKodeKtrCabang;
    }

    /**
     * @return the errorNoRekening
     */
    public String getErrorNoRekening() {
        return errorNoRekening;
    }

    /**
     * @param errorNoRekening the errorNoRekening to set
     */
    public void setErrorNoRekening(String errorNoRekening) {
        this.errorNoRekening = errorNoRekening;
    }

    /**
     * @return the errorTglAkadAkhir
     */
    public String getErrorTglAkadAkhir() {
        return errorTglAkadAkhir;
    }

    /**
     * @param errorTglAkadAkhir the errorTglAkadAkhir to set
     */
    public void setErrorTglAkadAkhir(String errorTglAkadAkhir) {
        this.errorTglAkadAkhir = errorTglAkadAkhir;
    }
}