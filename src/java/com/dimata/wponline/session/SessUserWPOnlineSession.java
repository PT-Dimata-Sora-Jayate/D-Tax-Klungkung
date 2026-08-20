

package com.dimata.wponline.session;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import com.dimata.wponline.entity.wajibpajakonline.WajibPajak;
import com.dimata.wponline.entity.wajibpajakonline.PstWajibPajak;

public class SessUserWPOnlineSession {
    public final static String HTTP_SESSION_NAME = "USER_SESSION";
    public final static String HTTP_SESSION_SALES = "SALES_SESSION";
    public final static String HTTP_SESSION_WPNAME = "WPUSER_SESSION";
    
    public final static int DO_LOGIN_OK = 0;
    public final static int DO_LOGIN_ALREADY_LOGIN =1;
    public final static int DO_LOGIN_NOT_VALID =2;
    public final static int DO_LOGIN_SYSTEM_FAIL =3;
    public final static int DO_LOGIN_GET_PRIV_ERROR =4;
    public final static int DO_LOGIN_NO_PRIV_ASIGNED =5;
    
    public final static String[] soLoginTxt={"Login succeed","User is already logged in",
    "Login ID or Password are invalid","System cannot login you", "Can't get privilege",
    "No access asigned, please contact your system administrator"};
    
    private Vector  privObj= new Vector();
    private WajibPajak wajibPajak = new WajibPajak();
    
    public SessUserWPOnlineSession(){
        wajibPajak.setUserStatus(WajibPajak.STATUS_LOGOUT);
    }
    
    public SessUserWPOnlineSession(String hostIP){
        wajibPajak.setUserStatus(WajibPajak.STATUS_LOGOUT);
        wajibPajak.setLastLoginIp(hostIP);
    }
    
    public WajibPajak getWajibPajak(){
    	return this.wajibPajak;
    }
    
    public boolean isLoggedIn(){
        if( /*(this.appUser!=null) && (this.appUser.getOID()!=0) && */
            (this.wajibPajak.getUserStatus()==WajibPajak.STATUS_LOGIN))
            return true;
        else
            return false;
    }
    
    
    public int doLogin(String namaUser, String password){
        WajibPajak userWajibPajak = PstWajibPajak.getByLoginIDAndPassword(namaUser, password);

        if(userWajibPajak==null)
            return DO_LOGIN_SYSTEM_FAIL;

        if(userWajibPajak.getNamaUser().length()==0)
            return DO_LOGIN_NOT_VALID;

        userWajibPajak.setLastLoginIp(this.wajibPajak.getLastLoginIp());
        userWajibPajak.setUserStatus(WajibPajak.STATUS_LOGIN);
        userWajibPajak.setLastLoginDate(new Date());

        this.wajibPajak = userWajibPajak;
        
        System.out.println(" User login OK status ->" + wajibPajak.getUserStatus());
        return DO_LOGIN_OK;
    }
    
    public int doApproval(String namaUser, String password){
        WajibPajak userWajibPajak = PstWajibPajak.getByLoginIDAndPassword(namaUser,password);

        if(userWajibPajak==null)
            return DO_LOGIN_SYSTEM_FAIL;

        if(userWajibPajak.getOID()==0)
            return DO_LOGIN_NOT_VALID;
        
        return DO_LOGIN_NOT_VALID;
    }
    
    public void doLogout(){
        if((this.wajibPajak !=null) && (this.wajibPajak.getUserStatus() == wajibPajak.STATUS_LOGIN)){
        
        }
    }
    
}
