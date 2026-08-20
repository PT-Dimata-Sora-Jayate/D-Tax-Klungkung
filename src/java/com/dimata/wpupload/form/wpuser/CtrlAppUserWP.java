/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.form.wpuser;

import com.dimata.qdep.db.DBException;
import com.dimata.qdep.form.Control;
import com.dimata.qdep.form.FRMMessage;
import com.dimata.qdep.system.I_DBExceptionInfo;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import com.dimata.wpupload.entity.wpuser.AppUserWP;
import com.dimata.wpupload.entity.wpuser.PstAppUserWP;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ardiadi
 */
public class CtrlAppUserWP extends Control implements I_Language {
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
private AppUserWP entAppUserWP;
private PstAppUserWP pstAppUserWP;
private FrmAppUserWP frmAppUserWP;
int language = LANGUAGE_DEFAULT;

public CtrlAppUserWP(HttpServletRequest request) {
msgString = "";
entAppUserWP = new AppUserWP();
try {
pstAppUserWP = new PstAppUserWP(0);
} catch (Exception e) {;
}
frmAppUserWP = new FrmAppUserWP(request, entAppUserWP);
}

private String getSystemMessage(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
this.frmAppUserWP.addError(frmAppUserWP.FRM_FIELD_USER_ID, resultText[language][RSLT_EST_CODE_EXIST]);
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

public AppUserWP getAppUserWP() {
return entAppUserWP;
}

public FrmAppUserWP getForm() {
return frmAppUserWP;
}

public String getMessage() {
return msgString;
}

public int getStart() {
return start;
}

public int action(int cmd, long oidAppUserWP) {
msgString = "";
int excCode = I_DBExceptionInfo.NO_EXCEPTION;
int rsCode = RSLT_OK;
switch (cmd) {
case Command.ADD:
break;

case Command.SAVE:
if (oidAppUserWP != 0) {
try {
entAppUserWP = PstAppUserWP.fetchExc(oidAppUserWP);
} catch (Exception exc) {
}
}

frmAppUserWP.requestEntityObject(entAppUserWP);

if (frmAppUserWP.errorSize() > 0) {
msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
return RSLT_FORM_INCOMPLETE;
}

if (entAppUserWP.getOID() == 0) {
try {
long oid = pstAppUserWP.insertExc(this.entAppUserWP);
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
long oid = pstAppUserWP.updateExc(this.entAppUserWP);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}

}
break;

case Command.EDIT:
if (oidAppUserWP != 0) {
try {
entAppUserWP = PstAppUserWP.fetchExc(oidAppUserWP);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.ASK:
if (oidAppUserWP != 0) {
try {
entAppUserWP = PstAppUserWP.fetchExc(oidAppUserWP);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.DELETE:
if (oidAppUserWP != 0) {
try {
long oid = PstAppUserWP.deleteExc(oidAppUserWP);
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
