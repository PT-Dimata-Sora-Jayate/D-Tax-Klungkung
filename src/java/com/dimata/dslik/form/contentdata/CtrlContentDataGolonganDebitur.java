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
 Description : Controll ContentDataGolonganDebitur
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataGolonganDebitur extends Control implements I_Language {

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
    private ContentDataGolonganDebitur entContentDataGolonganDebitur;
    private PstContentDataGolonganDebitur pstContentDataGolonganDebitur;
    private FrmContentDataGolonganDebitur frmContentDataGolonganDebitur;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataGolonganDebitur(HttpServletRequest request) {
        msgString = "";
        entContentDataGolonganDebitur = new ContentDataGolonganDebitur();
        try {
            pstContentDataGolonganDebitur = new PstContentDataGolonganDebitur(0);
        } catch (Exception e) {;
        }
        frmContentDataGolonganDebitur = new FrmContentDataGolonganDebitur(request, entContentDataGolonganDebitur);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataGolonganDebitur.addError(frmContentDataGolonganDebitur.FRM_FIELD_GOLONGAN_DEBITUR_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataGolonganDebitur getContentDataGolonganDebitur() {
        return entContentDataGolonganDebitur;
    }

    public FrmContentDataGolonganDebitur getForm() {
        return frmContentDataGolonganDebitur;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataGolonganDebitur) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataGolonganDebitur != 0) {
                    try {
                        entContentDataGolonganDebitur = PstContentDataGolonganDebitur.fetchExc(oidContentDataGolonganDebitur);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataGolonganDebitur.requestEntityObject(entContentDataGolonganDebitur);

                if (frmContentDataGolonganDebitur.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataGolonganDebitur.getOID() == 0) {
                    try {
                        long oid = pstContentDataGolonganDebitur.insertExc(this.entContentDataGolonganDebitur);
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
                        long oid = pstContentDataGolonganDebitur.updateExc(this.entContentDataGolonganDebitur);
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
                if (oidContentDataGolonganDebitur != 0) {
                    try {
                        entContentDataGolonganDebitur = PstContentDataGolonganDebitur.fetchExc(oidContentDataGolonganDebitur);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataGolonganDebitur != 0) {
                    try {
                        entContentDataGolonganDebitur = PstContentDataGolonganDebitur.fetchExc(oidContentDataGolonganDebitur);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataGolonganDebitur != 0) {
                    try {
                        long oid = PstContentDataGolonganDebitur.deleteExc(oidContentDataGolonganDebitur);
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
