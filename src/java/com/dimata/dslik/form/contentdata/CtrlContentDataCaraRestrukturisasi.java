/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

/**
 *
 * @author m20n9
 */
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;
import com.dimata.dslik.entity.contentdata.*;

/*
 Description : Controll ContentDataCaraRestrukturisasi
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataCaraRestrukturisasi extends Control implements I_Language {

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
    private ContentDataCaraRestrukturisasi entContentDataCaraRestrukturisasi;
    private PstContentDataCaraRestrukturisasi pstContentDataCaraRestrukturisasi;
    private FrmContentDataCaraRestrukturisasi frmContentDataCaraRestrukturisasi;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataCaraRestrukturisasi(HttpServletRequest request) {
        msgString = "";
        entContentDataCaraRestrukturisasi = new ContentDataCaraRestrukturisasi();
        try {
            pstContentDataCaraRestrukturisasi = new PstContentDataCaraRestrukturisasi(0);
        } catch (Exception e) {;
        }
        frmContentDataCaraRestrukturisasi = new FrmContentDataCaraRestrukturisasi(request, entContentDataCaraRestrukturisasi);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataCaraRestrukturisasi.addError(frmContentDataCaraRestrukturisasi.FRM_FIELD_RESTRUKTURISASI_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataCaraRestrukturisasi getContentDataCaraRestrukturisasi() {
        return entContentDataCaraRestrukturisasi;
    }

    public FrmContentDataCaraRestrukturisasi getForm() {
        return frmContentDataCaraRestrukturisasi;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataCaraRestrukturisasi) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataCaraRestrukturisasi != 0) {
                    try {
                        entContentDataCaraRestrukturisasi = PstContentDataCaraRestrukturisasi.fetchExc(oidContentDataCaraRestrukturisasi);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataCaraRestrukturisasi.requestEntityObject(entContentDataCaraRestrukturisasi);

                if (frmContentDataCaraRestrukturisasi.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataCaraRestrukturisasi.getOID() == 0) {
                    try {
                        long oid = pstContentDataCaraRestrukturisasi.insertExc(this.entContentDataCaraRestrukturisasi);
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
                        long oid = pstContentDataCaraRestrukturisasi.updateExc(this.entContentDataCaraRestrukturisasi);
                        msgString = FRMMessage.getMsg(FRMMessage.MSG_SAVED);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidContentDataCaraRestrukturisasi != 0) {
                    try {
                        entContentDataCaraRestrukturisasi = PstContentDataCaraRestrukturisasi.fetchExc(oidContentDataCaraRestrukturisasi);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataCaraRestrukturisasi != 0) {
                    try {
                        entContentDataCaraRestrukturisasi = PstContentDataCaraRestrukturisasi.fetchExc(oidContentDataCaraRestrukturisasi);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataCaraRestrukturisasi != 0) {
                    try {
                        long oid = PstContentDataCaraRestrukturisasi.deleteExc(oidContentDataCaraRestrukturisasi);
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
