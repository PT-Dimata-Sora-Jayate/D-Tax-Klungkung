

package com.dimata.wpupload.entity.esptpd;
import com.dimata.qdep.entity.Entity;
import java.util.Date;

public class ViewPembayaranSimpatda {
    private String kodeSubLokasi ="";
    private double serviceTax=0;
    private String vrowid="";
    private String noSptpd="";
    private String tipeDaftar ="";
    private Date tglSspd=null;
    private String npwpd="";
    private String kodePajak="";
    private String noRekening="";
    private String masaPajak="";
    private String tahunPajak="";
    private String jenisSetoran="";
    private String noBuktiSetoran="";
    private double totalOmzet =0;
    private double tarif =0;
    private double totalPajak=0;
    private String keterangan="";
    private Date tglRekam= null;
    private String idRekam="";
    private Date tglUbah =null;
    private String idUbah ="";
    private double setoran=0;
    private String statusData="";
    private String namaPenyetor="";

   
    public String getKodeSubLokasi() {
        return kodeSubLokasi;
    }

    
    public void setKodeSubLokasi(String kodeSubLokasi) {
        this.kodeSubLokasi = kodeSubLokasi;
    }

    
    public double getServiceTax() {
        return serviceTax;
    }

    
    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }

    
    public String getVrowid() {
        return vrowid;
    }

    public void setVrowid(String vrowid) {
        this.vrowid = vrowid;
    }

    
    public String getNoSptpd() {
        return noSptpd;
    }

    public void setNoSptpd(String noSptpd) {
        this.noSptpd = noSptpd;
    }

   
    public String getTipeDaftar() {
        return tipeDaftar;
    }

   
    public void setTipeDaftar(String tipeDaftar) {
        this.tipeDaftar = tipeDaftar;
    }

   
    public Date getTglSspd() {
        return tglSspd;
    }

    public void setTglSspd(Date tglSspd) {
        this.tglSspd = tglSspd;
    }

   
    public String getNpwpd() {
        return npwpd;
    }

   
    public void setNpwpd(String npwpd) {
        this.npwpd = npwpd;
    }

   
    public String getKodePajak() {
        return kodePajak;
    }

    
    public void setKodePajak(String kodePajak) {
        this.kodePajak = kodePajak;
    }

    
    public String getNoRekening() {
        return noRekening;
    }

    
    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    
    public String getMasaPajak() {
        return masaPajak;
    }

    
    public void setMasaPajak(String masaPajak) {
        this.masaPajak = masaPajak;
    }

   
    public String getTahunPajak() {
        return tahunPajak;
    }

    
    public void setTahunPajak(String tahunPajak) {
        this.tahunPajak = tahunPajak;
    }

   
    public String getJenisSetoran() {
        return jenisSetoran;
    }

   
    public void setJenisSetoran(String jenisSetoran) {
        this.jenisSetoran = jenisSetoran;
    }

   
    public String getNoBuktiSetoran() {
        return noBuktiSetoran;
    }

    
    public void setNoBuktiSetoran(String noBuktiSetoran) {
        this.noBuktiSetoran = noBuktiSetoran;
    }

    
    public double getTotalOmzet() {
        return totalOmzet;
    }

    
    public void setTotalOmzet(double totalOmzet) {
        this.totalOmzet = totalOmzet;
    }

    
    public double getTarif() {
        return tarif;
    }

   
    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

   
    public double getTotalPajak() {
        return totalPajak;
    }

   
    public void setTotalPajak(double totalPajak) {
        this.totalPajak = totalPajak;
    }

    public String getKeterangan() {
        return keterangan;
    }

    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

   
    public Date getTglRekam() {
        return tglRekam;
    }

   
    public void setTglRekam(Date tglRekam) {
        this.tglRekam = tglRekam;
    }

   
    public String getIdRekam() {
        return idRekam;
    }

    public void setIdRekam(String idRekam) {
        this.idRekam = idRekam;
    }

    
    public Date getTglUbah() {
        return tglUbah;
    }

   
    public void setTglUbah(Date tglUbah) {
        this.tglUbah = tglUbah;
    }

    /**
     * @return the idUbah
     */
    public String getIdUbah() {
        return idUbah;
    }

    /**
     * @param idUbah the idUbah to set
     */
    public void setIdUbah(String idUbah) {
        this.idUbah = idUbah;
    }

    /**
     * @return the setoran
     */
    public double getSetoran() {
        return setoran;
    }

    /**
     * @param setoran the setoran to set
     */
    public void setSetoran(double setoran) {
        this.setoran = setoran;
    }

    /**
     * @return the statusData
     */
    public String getStatusData() {
        return statusData;
    }

    /**
     * @param statusData the statusData to set
     */
    public void setStatusData(String statusData) {
        this.statusData = statusData;
    }

    /**
     * @return the namaPenyetor
     */
    public String getNamaPenyetor() {
        return namaPenyetor;
    }

    /**
     * @param namaPenyetor the namaPenyetor to set
     */
    public void setNamaPenyetor(String namaPenyetor) {
        this.namaPenyetor = namaPenyetor;
    }
}
