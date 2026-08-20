/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.wpupload.form.wpnamabadan;

import com.dimata.qdep.db.DBException;
import com.dimata.qdep.form.Control;
import com.dimata.qdep.form.FRMMessage;
import com.dimata.qdep.system.I_DBExceptionInfo;
import com.dimata.util.Command;
import com.dimata.util.lang.I_Language;
import com.dimata.wpupload.entity.wpnamabadan.NamaBadan;
import com.dimata.wpupload.entity.wpnamabadan.PstNamaBadan;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ardiadi
 */
public class CtrlNamaBadan extends Control implements I_Language {
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
private NamaBadan entNamaBadan;
private PstNamaBadan pstNamaBadan;
private FrmNamaBadan frmNamaBadan;
int language = LANGUAGE_DEFAULT;

public CtrlNamaBadan(HttpServletRequest request) {
msgString = "";
entNamaBadan = new NamaBadan();
try {
pstNamaBadan = new PstNamaBadan(0);
} catch (Exception e) {;
}
frmNamaBadan = new FrmNamaBadan(request, entNamaBadan);
}

private String getSystemMessage(int msgCode) {
switch (msgCode) {
case I_DBExceptionInfo.MULTIPLE_ID:
this.frmNamaBadan.addError(frmNamaBadan.FRM_FIELD_NPWPD, resultText[language][RSLT_EST_CODE_EXIST]);
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

public NamaBadan getNamaBadan() {
return entNamaBadan;
}

public FrmNamaBadan getForm() {
return frmNamaBadan;
}

public String getMessage() {
return msgString;
}

public int getStart() {
return start;
}

public int action(int cmd, long oidNamaBadan) {
msgString = "";
int excCode = I_DBExceptionInfo.NO_EXCEPTION;
int rsCode = RSLT_OK;
switch (cmd) {
case Command.ADD:
break;

case Command.SAVE:
if (oidNamaBadan != 0) {
try {
entNamaBadan = PstNamaBadan.fetchExc(oidNamaBadan);
} catch (Exception exc) {
}
}

frmNamaBadan.requestEntityObject(entNamaBadan);

if (frmNamaBadan.errorSize() > 0) {
msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
return RSLT_FORM_INCOMPLETE;
}

if (entNamaBadan.getOID() == 0) {
try {
long oid = pstNamaBadan.insertExc(this.entNamaBadan);
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
long oid = pstNamaBadan.updateExc(this.entNamaBadan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}

}
break;

case Command.EDIT:
if (oidNamaBadan != 0) {
    try {
        entNamaBadan = PstNamaBadan.fetchExc(oidNamaBadan);
    } catch (DBException dbexc) {
        excCode = dbexc.getErrorCode();
        msgString = getSystemMessage(excCode);
    } catch (Exception exc) {
        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
    }
}
break;

case Command.ASK:
if (oidNamaBadan != 0) {
try {
entNamaBadan = PstNamaBadan.fetchExc(oidNamaBadan);
} catch (DBException dbexc) {
excCode = dbexc.getErrorCode();
msgString = getSystemMessage(excCode);
} catch (Exception exc) {
msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
}
}
break;

case Command.DELETE:
if (oidNamaBadan != 0) {
try {
long oid = PstNamaBadan.deleteExc(oidNamaBadan);
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