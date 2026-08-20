/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataStatusAgunan;
import com.dimata.dslik.entity.contentdata.PstContentDataStatusAgunan;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataStatusAgunan
 Date : Sat Sep 24 2016
 Author : Dewa
 */
public class CtrlContentDataStatusAgunan extends Control implements I_Language {

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
    private ContentDataStatusAgunan entContentDataStatusAgunan;
    private PstContentDataStatusAgunan pstContentDataStatusAgunan;
    private FrmContentDataStatusAgunan frmContentDataStatusAgunan;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataStatusAgunan(HttpServletRequest request) {
        msgString = "";
        entContentDataStatusAgunan = new ContentDataStatusAgunan();
        try {
            pstContentDataStatusAgunan = new PstContentDataStatusAgunan(0);
        } catch (Exception e) {;
        }
        frmContentDataStatusAgunan = new FrmContentDataStatusAgunan(request, entContentDataStatusAgunan);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataStatusAgunan.addError(frmContentDataStatusAgunan.FRM_FIELD_STATUS_AGUNAN_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataStatusAgunan getContentDataStatusAgunan() {
        return entContentDataStatusAgunan;
    }

    public FrmContentDataStatusAgunan getForm() {
        return frmContentDataStatusAgunan;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataStatusAgunan, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataStatusAgunan != 0) {
                    try {
                        entContentDataStatusAgunan = PstContentDataStatusAgunan.fetchExc(oidContentDataStatusAgunan);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataStatusAgunan.requestEntityObject(entContentDataStatusAgunan);

                if (frmContentDataStatusAgunan.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataStatusAgunan.getOID() == 0) {
                    try {
                        long oid = pstContentDataStatusAgunan.insertExc(this.entContentDataStatusAgunan);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
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
                        long oid = pstContentDataStatusAgunan.updateExc(this.entContentDataStatusAgunan);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_UPDATED);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidContentDataStatusAgunan != 0) {
                    try {
                        entContentDataStatusAgunan = PstContentDataStatusAgunan.fetchExc(oidContentDataStatusAgunan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataStatusAgunan != 0) {
                    try {
                        entContentDataStatusAgunan = PstContentDataStatusAgunan.fetchExc(oidContentDataStatusAgunan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataStatusAgunan != 0) {
                    try {
                        long oid = PstContentDataStatusAgunan.deleteExc(oidContentDataStatusAgunan);
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
