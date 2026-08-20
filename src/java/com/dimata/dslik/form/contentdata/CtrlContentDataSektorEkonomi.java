/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

import com.dimata.dslik.entity.contentdata.ContentDataSektorEkonomi;
import com.dimata.dslik.entity.contentdata.PstContentDataSektorEkonomi;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataSektorEkonomi
 Date : Sat Sep 24 2016
 Author : Dewa
 */
public class CtrlContentDataSektorEkonomi extends Control implements I_Language {

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
    private ContentDataSektorEkonomi entContentDataSektorEkonomi;
    private PstContentDataSektorEkonomi pstContentDataSektorEkonomi;
    private FrmContentDataSektorEkonomi frmContentDataSektorEkonomi;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataSektorEkonomi(HttpServletRequest request) {
        msgString = "";
        entContentDataSektorEkonomi = new ContentDataSektorEkonomi();
        try {
            pstContentDataSektorEkonomi = new PstContentDataSektorEkonomi(0);
        } catch (Exception e) {;
        }
        frmContentDataSektorEkonomi = new FrmContentDataSektorEkonomi(request, entContentDataSektorEkonomi);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataSektorEkonomi.addError(frmContentDataSektorEkonomi.FRM_FIELD_SEKTOR_EKONOMI_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataSektorEkonomi getContentDataSektorEkonomi() {
        return entContentDataSektorEkonomi;
    }

    public FrmContentDataSektorEkonomi getForm() {
        return frmContentDataSektorEkonomi;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataSektorEkonomi, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataSektorEkonomi != 0) {
                    try {
                        entContentDataSektorEkonomi = PstContentDataSektorEkonomi.fetchExc(oidContentDataSektorEkonomi);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataSektorEkonomi.requestEntityObject(entContentDataSektorEkonomi);

                if (frmContentDataSektorEkonomi.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataSektorEkonomi.getOID() == 0) {
                    try {
                        long oid = pstContentDataSektorEkonomi.insertExc(this.entContentDataSektorEkonomi);
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
                        long oid = pstContentDataSektorEkonomi.updateExc(this.entContentDataSektorEkonomi);
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
                if (oidContentDataSektorEkonomi != 0) {
                    try {
                        entContentDataSektorEkonomi = PstContentDataSektorEkonomi.fetchExc(oidContentDataSektorEkonomi);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataSektorEkonomi != 0) {
                    try {
                        entContentDataSektorEkonomi = PstContentDataSektorEkonomi.fetchExc(oidContentDataSektorEkonomi);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataSektorEkonomi != 0) {
                    try {
                        long oid = PstContentDataSektorEkonomi.deleteExc(oidContentDataSektorEkonomi);
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
