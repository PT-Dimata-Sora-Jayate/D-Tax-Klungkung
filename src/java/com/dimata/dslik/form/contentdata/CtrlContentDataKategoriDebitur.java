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
 Description : Controll ContentDataKategoriDebitur
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataKategoriDebitur extends Control implements I_Language {

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
    private ContentDataKategoriDebitur entContentDataKategoriDebitur;
    private PstContentDataKategoriDebitur pstContentDataKategoriDebitur;
    private FrmContentDataKategoriDebitur frmContentDataKategoriDebitur;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataKategoriDebitur(HttpServletRequest request) {
        msgString = "";
        entContentDataKategoriDebitur = new ContentDataKategoriDebitur();
        try {
            pstContentDataKategoriDebitur = new PstContentDataKategoriDebitur(0);
        } catch (Exception e) {;
        }
        frmContentDataKategoriDebitur = new FrmContentDataKategoriDebitur(request, entContentDataKategoriDebitur);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataKategoriDebitur.addError(frmContentDataKategoriDebitur.FRM_FIELD_KATEGORI_DEBITUR_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataKategoriDebitur getContentDataKategoriDebitur() {
        return entContentDataKategoriDebitur;
    }

    public FrmContentDataKategoriDebitur getForm() {
        return frmContentDataKategoriDebitur;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataKategoriDebitur, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataKategoriDebitur != 0) {
                    try {
                        entContentDataKategoriDebitur = PstContentDataKategoriDebitur.fetchExc(oidContentDataKategoriDebitur);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataKategoriDebitur.requestEntityObject(entContentDataKategoriDebitur);

                if (frmContentDataKategoriDebitur.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataKategoriDebitur.getOID() == 0) {
                    try {
                        long oid = pstContentDataKategoriDebitur.insertExc(this.entContentDataKategoriDebitur);
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
                        long oid = pstContentDataKategoriDebitur.updateExc(this.entContentDataKategoriDebitur);
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
                if (oidContentDataKategoriDebitur != 0) {
                    try {
                        entContentDataKategoriDebitur = PstContentDataKategoriDebitur.fetchExc(oidContentDataKategoriDebitur);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataKategoriDebitur != 0) {
                    try {
                        entContentDataKategoriDebitur = PstContentDataKategoriDebitur.fetchExc(oidContentDataKategoriDebitur);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataKategoriDebitur != 0) {
                    try {
                        long oid = PstContentDataKategoriDebitur.deleteExc(oidContentDataKategoriDebitur);
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