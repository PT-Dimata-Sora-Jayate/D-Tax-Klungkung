/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataStatusPerkawinanDebitur;
import com.dimata.dslik.entity.contentdata.PstContentDataStatusPerkawinanDebitur;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataStatusPerkawinanDebitur
 Date : Sat Sep 24 2016
 Author : Dewa
 */
public class CtrlContentDataStatusPerkawinanDebitur extends Control implements I_Language {

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
    private ContentDataStatusPerkawinanDebitur entContentDataStatusPerkawinanDebitur;
    private PstContentDataStatusPerkawinanDebitur pstContentDataStatusPerkawinanDebitur;
    private FrmContentDataStatusPerkawinanDebitur frmContentDataStatusPerkawinanDebitur;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataStatusPerkawinanDebitur(HttpServletRequest request) {
        msgString = "";
        entContentDataStatusPerkawinanDebitur = new ContentDataStatusPerkawinanDebitur();
        try {
            pstContentDataStatusPerkawinanDebitur = new PstContentDataStatusPerkawinanDebitur(0);
        } catch (Exception e) {;
        }
        frmContentDataStatusPerkawinanDebitur = new FrmContentDataStatusPerkawinanDebitur(request, entContentDataStatusPerkawinanDebitur);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataStatusPerkawinanDebitur.addError(frmContentDataStatusPerkawinanDebitur.FRM_FIELD_STATUS_PERKAWINAN_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataStatusPerkawinanDebitur getContentDataStatusPerkawinanDebitur() {
        return entContentDataStatusPerkawinanDebitur;
    }

    public FrmContentDataStatusPerkawinanDebitur getForm() {
        return frmContentDataStatusPerkawinanDebitur;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataStatusPerkawinanDebitur, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataStatusPerkawinanDebitur != 0) {
                    try {
                        entContentDataStatusPerkawinanDebitur = PstContentDataStatusPerkawinanDebitur.fetchExc(oidContentDataStatusPerkawinanDebitur);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataStatusPerkawinanDebitur.requestEntityObject(entContentDataStatusPerkawinanDebitur);

                if (frmContentDataStatusPerkawinanDebitur.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataStatusPerkawinanDebitur.getOID() == 0) {
                    try {
                        long oid = pstContentDataStatusPerkawinanDebitur.insertExc(this.entContentDataStatusPerkawinanDebitur);
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
                        long oid = pstContentDataStatusPerkawinanDebitur.updateExc(this.entContentDataStatusPerkawinanDebitur);
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
                if (oidContentDataStatusPerkawinanDebitur != 0) {
                    try {
                        entContentDataStatusPerkawinanDebitur = PstContentDataStatusPerkawinanDebitur.fetchExc(oidContentDataStatusPerkawinanDebitur);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataStatusPerkawinanDebitur != 0) {
                    try {
                        entContentDataStatusPerkawinanDebitur = PstContentDataStatusPerkawinanDebitur.fetchExc(oidContentDataStatusPerkawinanDebitur);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataStatusPerkawinanDebitur != 0) {
                    try {
                        long oid = PstContentDataStatusPerkawinanDebitur.deleteExc(oidContentDataStatusPerkawinanDebitur);
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
