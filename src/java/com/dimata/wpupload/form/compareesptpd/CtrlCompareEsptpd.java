/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.form.compareesptpd;

import com.dimata.qdep.db.DBException;
import com.dimata.qdep.db.OIDFactory;
import com.dimata.qdep.form.Control;
import com.dimata.qdep.form.FRMMessage;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.qdep.system.I_DBExceptionInfo;
import com.dimata.util.Command;
import com.dimata.util.Formater;
import com.dimata.util.lang.I_Language;
import com.dimata.wpupload.entity.compareesptpd.CompareEsptpd;
import com.dimata.wpupload.entity.compareesptpd.PstCompareEsptpd;
import com.dimata.wpupload.entity.esptpd.ESPTPD;
import com.dimata.wpupload.entity.esptpd.PstESPTPD;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ardiadi
 */
public class CtrlCompareEsptpd extends Control implements I_Language {
    public static int RSLT_OK = 0;
    public static int RSLT_UNKNOWN_ERROR = 1;
    public static int RSLT_EST_CODE_EXIST = 2;
    public static int RSLT_FORM_INCOMPLETE = 3;
    public static String[][] resultText = {
	{"Berhasil", "Tidak dapat diproses", "NoPerkiraan sudah ada", "Data tidak lengkap"},
	{"Succes", "Can not process", "Estimation code exist", "Data incomplete"}
    };
    private int start;
    private String msgString;
    private CompareEsptpd entCompareEsptpd;
    private PstCompareEsptpd pstCompareEsptpd;
    private FrmCompareEsptpd frmCompareEsptpd;
    int language = LANGUAGE_DEFAULT;

    public CtrlCompareEsptpd(HttpServletRequest request) {
	msgString = "";
	entCompareEsptpd = new CompareEsptpd();
	try {
	    pstCompareEsptpd = new PstCompareEsptpd(0);
	} catch (Exception e) {;
	}
	frmCompareEsptpd = new FrmCompareEsptpd(request, entCompareEsptpd);
    }

    private String getSystemMessage(int msgCode) {
	switch (msgCode) {
	    case I_DBExceptionInfo.MULTIPLE_ID:
		this.frmCompareEsptpd.addError(frmCompareEsptpd.FRM_FIELD_E_NPWPD, resultText[language][RSLT_EST_CODE_EXIST]);
		return resultText[language][RSLT_EST_CODE_EXIST];
	    default:
		return resultText[language][RSLT_UNKNOWN_ERROR];
	}
    }

    private int getControlMsgId(int msgCode) {
	switch (msgCode) {
	    case I_DBExceptionInfo.MULTIPLE_ID:
		return RSLT_EST_CODE_EXIST;
	    default:
		return RSLT_UNKNOWN_ERROR;
	}
    }

    public int getLanguage() {
	return language;
    }

    public void setLanguage(int language) {
	this.language = language;
    }

    public CompareEsptpd getCompareEsptpd() {
	return entCompareEsptpd;
    }

    public FrmCompareEsptpd getForm() {
	return frmCompareEsptpd;
    }

    public String getMessage() {
	return msgString;
    }

    public int getStart() {
	return start;
    }

    public int action(int cmd, long oidESPTPD, HttpServletRequest request) {
	msgString = "";
	int excCode = I_DBExceptionInfo.NO_EXCEPTION;
	int rsCode = RSLT_OK;
	switch (cmd) {
	    case Command.ADD:
	    break;

	    case Command.SAVE:
		if (oidESPTPD != 0) {
		    try {
			entCompareEsptpd = PstCompareEsptpd.fetchExc(oidESPTPD);
		    } catch (Exception exc) {
		    }
		}

		frmCompareEsptpd.requestEntityObject(entCompareEsptpd);

		if (frmCompareEsptpd.errorSize() > 0) {
		    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
		    return RSLT_FORM_INCOMPLETE;
		}

		if (entCompareEsptpd.getOID() == 0) {
		    try {
			String getSecondCommand = FRMQueryString.requestString(request, "secondCommand");
			Date currentDate = new Date();
			//String noSPTPD = String.valueOf(OIDFactory.generateOID());
			//String noSSPD = String.valueOf(OIDFactory.generateOID());
			entCompareEsptpd.setCompareETglInput(Formater.reFormatDate(currentDate, "yyyy-MM-dd"));
			entCompareEsptpd.setCompareETglRekam(Formater.reFormatDate(currentDate, "yyyy-MM-dd"));
			entCompareEsptpd.setCompareETglUbah(Formater.reFormatDate(currentDate, "yyyy-MM-dd"));
			//entESPTPD.setENoSPTPD(noSPTPD);
			//entESPTPD.setENoSSPD(noSSPD);
			
			int existData = PstCompareEsptpd.checkExistData(0, 0,
				PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_NPWPD]+"='"+entCompareEsptpd.getCompareENpwpd()+"' "
				+ "AND "+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_TAHUN_PAJAK]+"='"+entCompareEsptpd.getCompareETahunPajak()+"' "
				+ "AND "+PstCompareEsptpd.fieldNames[PstCompareEsptpd.FLD_MASA_PAJAK]+"='"+entCompareEsptpd.getCompareEMasaPajak()+"'", "");
			if(existData==0){
			    long oid = pstCompareEsptpd.insertExc(this.entCompareEsptpd);
			    msgString = "SUCCESS";
			}else{
			    if(getSecondCommand.length() == 0){
				msgString = "EXIST";
			    }else{
				long oid = pstCompareEsptpd.updateExcCustom(this.entCompareEsptpd);
			    }
			    
			}
		    } catch (DBException dbexc) {
			excCode = dbexc.getErrorCode();
			msgString = getSystemMessage(excCode);
			return getControlMsgId(excCode);
		    } catch (Exception exc) {
			msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
			return getControlMsgId(I_DBExceptionInfo.UNKNOWN);
		    }

		} else {
		    try {
			long oid = pstCompareEsptpd.updateExc(this.entCompareEsptpd);
		    } catch (DBException dbexc) {
			excCode = dbexc.getErrorCode();
			msgString = getSystemMessage(excCode);
		    } catch (Exception exc) {
			msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
		    }

		}
	    break;

	    case Command.EDIT:
		if (oidESPTPD != 0) {
		    try {
			entCompareEsptpd = PstCompareEsptpd.fetchExc(oidESPTPD);
		    } catch (DBException dbexc) {
			excCode = dbexc.getErrorCode();
			msgString = getSystemMessage(excCode);
		    } catch (Exception exc) {
			msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
		    }
		}
	    break;

	    case Command.ASK:
		if (oidESPTPD != 0) {
		    try {
			entCompareEsptpd = PstCompareEsptpd.fetchExc(oidESPTPD);
		    } catch (DBException dbexc) {
			excCode = dbexc.getErrorCode();
			msgString = getSystemMessage(excCode);
		    } catch (Exception exc) {
			msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
		    }
		}
	    break;

	    case Command.DELETE:
		if (oidESPTPD != 0) {
		    try {
			long oid = PstCompareEsptpd.deleteExc(oidESPTPD);
			if (oid != 0) {
			    msgString = FRMMessage.getMessage(FRMMessage.MSG_DELETED);
			    excCode = RSLT_OK;
			} else {
			    msgString = FRMMessage.getMessage(FRMMessage.ERR_DELETED);
			    excCode = RSLT_FORM_INCOMPLETE;
			}
		    } catch (DBException dbexc) {
			excCode = dbexc.getErrorCode();
			msgString = getSystemMessage(excCode);
		    } catch (Exception exc) {
			msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
		    }
		}
	    break;

	    default:

	    }
	return rsCode;
	}
}
