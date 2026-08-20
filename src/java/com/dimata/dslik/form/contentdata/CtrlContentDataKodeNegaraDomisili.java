/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataKodeNegaraDomisili;
import com.dimata.dslik.entity.contentdata.PstContentDataKodeNegaraDomisili;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataKodeNegaraDomisili
 Date : Sun Sep 25 2016
 Author : Dewa
 */
public class CtrlContentDataKodeNegaraDomisili extends Control implements I_Language {

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
    private ContentDataKodeNegaraDomisili entContentDataKodeNegaraDomisili;
    private PstContentDataKodeNegaraDomisili pstContentDataKodeNegaraDomisili;
    private FrmContentDataKodeNegaraDomisili frmContentDataKodeNegaraDomisili;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataKodeNegaraDomisili(HttpServletRequest request) {
        msgString = "";
        entContentDataKodeNegaraDomisili = new ContentDataKodeNegaraDomisili();
        try {
            pstContentDataKodeNegaraDomisili = new PstContentDataKodeNegaraDomisili(0);
        } catch (Exception e) {;
        }
        frmContentDataKodeNegaraDomisili = new FrmContentDataKodeNegaraDomisili(request, entContentDataKodeNegaraDomisili);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataKodeNegaraDomisili.addError(frmContentDataKodeNegaraDomisili.FRM_FIELD_NEGARA_DOMISILI_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataKodeNegaraDomisili getContentDataKodeNegaraDomisili() {
        return entContentDataKodeNegaraDomisili;
    }

    public FrmContentDataKodeNegaraDomisili getForm() {
        return frmContentDataKodeNegaraDomisili;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataKodeNegaraDomisili, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataKodeNegaraDomisili != 0) {
                    try {
                        entContentDataKodeNegaraDomisili = PstContentDataKodeNegaraDomisili.fetchExc(oidContentDataKodeNegaraDomisili);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataKodeNegaraDomisili.requestEntityObject(entContentDataKodeNegaraDomisili);

                if (frmContentDataKodeNegaraDomisili.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataKodeNegaraDomisili.getOID() == 0) {
                    try {
                        long oid = pstContentDataKodeNegaraDomisili.insertExc(this.entContentDataKodeNegaraDomisili);
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
                        long oid = pstContentDataKodeNegaraDomisili.updateExc(this.entContentDataKodeNegaraDomisili);
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
                if (oidContentDataKodeNegaraDomisili != 0) {
                    try {
                        entContentDataKodeNegaraDomisili = PstContentDataKodeNegaraDomisili.fetchExc(oidContentDataKodeNegaraDomisili);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataKodeNegaraDomisili != 0) {
                    try {
                        entContentDataKodeNegaraDomisili = PstContentDataKodeNegaraDomisili.fetchExc(oidContentDataKodeNegaraDomisili);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataKodeNegaraDomisili != 0) {
                    try {
                        long oid = PstContentDataKodeNegaraDomisili.deleteExc(oidContentDataKodeNegaraDomisili);
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
