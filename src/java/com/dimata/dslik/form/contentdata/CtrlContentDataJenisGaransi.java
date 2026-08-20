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
 Description : Controll ContentDataJenisGaransi
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataJenisGaransi extends Control implements I_Language {

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
    private ContentDataJenisGaransi entContentDataJenisGaransi;
    private PstContentDataJenisGaransi pstContentDataJenisGaransi;
    private FrmContentDataJenisGaransi frmContentDataJenisGaransi;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataJenisGaransi(HttpServletRequest request) {
        msgString = "";
        entContentDataJenisGaransi = new ContentDataJenisGaransi();
        try {
            pstContentDataJenisGaransi = new PstContentDataJenisGaransi(0);
        } catch (Exception e) {;
        }
        frmContentDataJenisGaransi = new FrmContentDataJenisGaransi(request, entContentDataJenisGaransi);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataJenisGaransi.addError(frmContentDataJenisGaransi.FRM_FIELD_JENIS_GARANSI_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataJenisGaransi getContentDataJenisGaransi() {
        return entContentDataJenisGaransi;
    }

    public FrmContentDataJenisGaransi getForm() {
        return frmContentDataJenisGaransi;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataJenisGaransi) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataJenisGaransi != 0) {
                    try {
                        entContentDataJenisGaransi = PstContentDataJenisGaransi.fetchExc(oidContentDataJenisGaransi);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataJenisGaransi.requestEntityObject(entContentDataJenisGaransi);

                if (frmContentDataJenisGaransi.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataJenisGaransi.getOID() == 0) {
                    try {
                        long oid = pstContentDataJenisGaransi.insertExc(this.entContentDataJenisGaransi);
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
                        long oid = pstContentDataJenisGaransi.updateExc(this.entContentDataJenisGaransi);
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
                if (oidContentDataJenisGaransi != 0) {
                    try {
                        entContentDataJenisGaransi = PstContentDataJenisGaransi.fetchExc(oidContentDataJenisGaransi);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataJenisGaransi != 0) {
                    try {
                        entContentDataJenisGaransi = PstContentDataJenisGaransi.fetchExc(oidContentDataJenisGaransi);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataJenisGaransi != 0) {
                    try {
                        long oid = PstContentDataJenisGaransi.deleteExc(oidContentDataJenisGaransi);
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