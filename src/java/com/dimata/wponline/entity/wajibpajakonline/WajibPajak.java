
package com.dimata.wponline.entity.wajibpajakonline;

import com.dimata.qdep.entity.Entity;
import java.util.Date;

public class WajibPajak extends Entity{
    
    public final static int STATUS_NEW =0;
    public final static int STATUS_LOGOUT =1;
    public final static int STATUS_LOGIN =2;
    
    public final static String[] statusTxt= {"New", "Logged out", "Logged In"};
    
    private String namaUser="";
    private String password="";
    private String namaWajibPajak="";
    private String alamat="";
    private String nop="";
    private String telp="";
    private String fax="";
    private String status="";
    private String email="";
    private String kodeKonfirmasi ="";
    private Date tglPendaftaran =null;
    
    private int userStatus=0;
    private String lastLoginIp="";
    private Date lastLoginDate=new Date();

    public String getNamaUser() {
        return namaUser;
    }

    
    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    
    public String getPassword() {
        return password;
    }

   
    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getNamaWajibPajak() {
        return namaWajibPajak;
    }

   
    public void setNamaWajibPajak(String namaWajibPajak) {
        this.namaWajibPajak = namaWajibPajak;
    }

   
    public String getAlamat() {
        return alamat;
    }

    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

   
    public String getNop() {
        return nop;
    }

   
    public void setNop(String nop) {
        this.nop = nop;
    }

   
    public String getTelp() {
        return telp;
    }

    
    public void setTelp(String telp) {
        this.telp = telp;
    }

  
    public String getFax() {
        return fax;
    }

   
    public void setFax(String fax) {
        this.fax = fax;
    }

    
    public String getStatus() {
        return status;
    }

   
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    public String getKodeKonfirmasi() {
        return kodeKonfirmasi;
    }

   
    public void setKodeKonfirmasi(String kodeKonfirmasi) {
        this.kodeKonfirmasi = kodeKonfirmasi;
    }
    
    public void setTglPendaftaran(Date tglPendaftaran){
        this.tglPendaftaran = tglPendaftaran;
    }
    
    public Date getTglPendaftaran(){
        return tglPendaftaran;
    }
    
    public void setUserStatus(int status){
        this.userStatus = status;
    }
    
    public int getUserStatus(){
        return userStatus;
    }
    
    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp = lastLoginIp;
    }
    
    public String getLastLoginIp(){
        return lastLoginIp;
    }
    
    public void setLastLoginDate(Date lastLoginDate){
        this.lastLoginDate = lastLoginDate;
    }
    
    public Date getLastLoginDate(){
        return lastLoginDate;
    }
    
    
    
    
    
    
    
    
}
