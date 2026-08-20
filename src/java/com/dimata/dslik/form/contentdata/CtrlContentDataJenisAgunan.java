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
 Description : Controll ContentDataJenisAgunan
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataJenisAgunan extends Control implements I_Language {

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
    private ContentDataJenisAgunan entContentDataJenisAgunan;
    private PstContentDataJenisAgunan pstContentDataJenisAgunan;
    private FrmContentDataJenisAgunan frmContentDataJenisAgunan;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataJenisAgunan(HttpServletRequest request) {
        msgString = "";
        entContentDataJenisAgunan = new ContentDataJenisAgunan();
        try {
            pstContentDataJenisAgunan = new PstContentDataJenisAgunan(0);
        } catch (Exception e) {;
        }
        frmContentDataJenisAgunan = new FrmContentDataJenisAgunan(request, entContentDataJenisAgunan);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataJenisAgunan.addError(frmContentDataJenisAgunan.FRM_FIELD_JENIS_AGUNAN_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataJenisAgunan getContentDataJenisAgunan() {
        return entContentDataJenisAgunan;
    }

    public FrmContentDataJenisAgunan getForm() {
        return frmContentDataJenisAgunan;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataJenisAgunan) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataJenisAgunan != 0) {
                    try {
                        entContentDataJenisAgunan = PstContentDataJenisAgunan.fetchExc(oidContentDataJenisAgunan);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataJenisAgunan.requestEntityObject(entContentDataJenisAgunan);

                if (frmContentDataJenisAgunan.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataJenisAgunan.getOID() == 0) {
                    try {
                        long oid = pstContentDataJenisAgunan.insertExc(this.entContentDataJenisAgunan);
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
                        long oid = pstContentDataJenisAgunan.updateExc(this.entContentDataJenisAgunan);
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
                if (oidContentDataJenisAgunan != 0) {
                    try {
                        entContentDataJenisAgunan = PstContentDataJenisAgunan.fetchExc(oidContentDataJenisAgunan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataJenisAgunan != 0) {
                    try {
                        entContentDataJenisAgunan = PstContentDataJenisAgunan.fetchExc(oidContentDataJenisAgunan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataJenisAgunan != 0) {
                    try {
                        long oid = PstContentDataJenisAgunan.deleteExc(oidContentDataJenisAgunan);
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
