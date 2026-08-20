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
 Description : Controll ContentDataJenisSuratBerharga
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataJenisSuratBerharga extends Control implements I_Language {

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
    private ContentDataJenisSuratBerharga entContentDataJenisSuratBerharga;
    private PstContentDataJenisSuratBerharga pstContentDataJenisSuratBerharga;
    private FrmContentDataJenisSuratBerharga frmContentDataJenisSuratBerharga;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataJenisSuratBerharga(HttpServletRequest request) {
        msgString = "";
        entContentDataJenisSuratBerharga = new ContentDataJenisSuratBerharga();
        try {
            pstContentDataJenisSuratBerharga = new PstContentDataJenisSuratBerharga(0);
        } catch (Exception e) {;
        }
        frmContentDataJenisSuratBerharga = new FrmContentDataJenisSuratBerharga(request, entContentDataJenisSuratBerharga);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataJenisSuratBerharga.addError(frmContentDataJenisSuratBerharga.FRM_FIELD_JENIS_SURAT_BERHARGA_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataJenisSuratBerharga getContentDataJenisSuratBerharga() {
        return entContentDataJenisSuratBerharga;
    }

    public FrmContentDataJenisSuratBerharga getForm() {
        return frmContentDataJenisSuratBerharga;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataJenisSuratBerharga, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataJenisSuratBerharga != 0) {
                    try {
                        entContentDataJenisSuratBerharga = PstContentDataJenisSuratBerharga.fetchExc(oidContentDataJenisSuratBerharga);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataJenisSuratBerharga.requestEntityObject(entContentDataJenisSuratBerharga);

                if (frmContentDataJenisSuratBerharga.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataJenisSuratBerharga.getOID() == 0) {
                    try {
                        long oid = pstContentDataJenisSuratBerharga.insertExc(this.entContentDataJenisSuratBerharga);
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
                        long oid = pstContentDataJenisSuratBerharga.updateExc(this.entContentDataJenisSuratBerharga);
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
                if (oidContentDataJenisSuratBerharga != 0) {
                    try {
                        entContentDataJenisSuratBerharga = PstContentDataJenisSuratBerharga.fetchExc(oidContentDataJenisSuratBerharga);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataJenisSuratBerharga != 0) {
                    try {
                        entContentDataJenisSuratBerharga = PstContentDataJenisSuratBerharga.fetchExc(oidContentDataJenisSuratBerharga);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataJenisSuratBerharga != 0) {
                    try {
                        long oid = PstContentDataJenisSuratBerharga.deleteExc(oidContentDataJenisSuratBerharga);
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