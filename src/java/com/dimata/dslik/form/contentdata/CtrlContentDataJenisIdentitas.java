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
 Description : Controll ContentDataJenisIdentitas
 Date : Sat Sep 24 2016
 Author : surya
 */
public class CtrlContentDataJenisIdentitas extends Control implements I_Language {

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
    private ContentDataJenisIdentitas entContentDataJenisIdentitas;
    private PstContentDataJenisIdentitas pstContentDataJenisIdentitas;
    private FrmContentDataJenisIdentitas frmContentDataJenisIdentitas;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataJenisIdentitas(HttpServletRequest request) {
        msgString = "";
        entContentDataJenisIdentitas = new ContentDataJenisIdentitas();
        try {
            pstContentDataJenisIdentitas = new PstContentDataJenisIdentitas(0);
        } catch (Exception e) {;
        }
        frmContentDataJenisIdentitas = new FrmContentDataJenisIdentitas(request, entContentDataJenisIdentitas);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataJenisIdentitas.addError(frmContentDataJenisIdentitas.FRM_FIELD_JENIS_IDENTITAS_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataJenisIdentitas getContentDataJenisIdentitas() {
        return entContentDataJenisIdentitas;
    }

    public FrmContentDataJenisIdentitas getForm() {
        return frmContentDataJenisIdentitas;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataJenisIdentitas) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataJenisIdentitas != 0) {
                    try {
                        entContentDataJenisIdentitas = PstContentDataJenisIdentitas.fetchExc(oidContentDataJenisIdentitas);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataJenisIdentitas.requestEntityObject(entContentDataJenisIdentitas);

                if (frmContentDataJenisIdentitas.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataJenisIdentitas.getOID() == 0) {
                    try {
                        long oid = pstContentDataJenisIdentitas.insertExc(this.entContentDataJenisIdentitas);
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
                        long oid = pstContentDataJenisIdentitas.updateExc(this.entContentDataJenisIdentitas);
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
                if (oidContentDataJenisIdentitas != 0) {
                    try {
                        entContentDataJenisIdentitas = PstContentDataJenisIdentitas.fetchExc(oidContentDataJenisIdentitas);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataJenisIdentitas != 0) {
                    try {
                        entContentDataJenisIdentitas = PstContentDataJenisIdentitas.fetchExc(oidContentDataJenisIdentitas);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataJenisIdentitas != 0) {
                    try {
                        long oid = PstContentDataJenisIdentitas.deleteExc(oidContentDataJenisIdentitas);
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
