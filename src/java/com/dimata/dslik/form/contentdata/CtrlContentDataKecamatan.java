/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.form.contentdata;

/**
 *
 * @author dimata005
 */
import com.dimata.dslik.entity.contentdata.ContentDataKecamatan;
import com.dimata.dslik.entity.contentdata.PstContentDataKecamatan;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
Description : Controll ContentDataKecamatan
Date : Wed Jun 07 2017
Author : opie-eyek 20170607
 */
public class CtrlContentDataKecamatan extends Control implements I_Language {

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
    private ContentDataKecamatan entContentDataKecamatan;
    private PstContentDataKecamatan pstContentDataKecamatan;
    private FrmContentDataKecamatan frmContentDataKecamatan;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataKecamatan(HttpServletRequest request) {
        msgString = "";
        entContentDataKecamatan = new ContentDataKecamatan();
        try {
            pstContentDataKecamatan = new PstContentDataKecamatan(0);
        } catch (Exception e) {;
        }
        frmContentDataKecamatan = new FrmContentDataKecamatan(request, entContentDataKecamatan);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataKecamatan.addError(frmContentDataKecamatan.FRM_FIELD_KECAMATANID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataKecamatan getContentDataKecamatan() {
        return entContentDataKecamatan;
    }

    public FrmContentDataKecamatan getForm() {
        return frmContentDataKecamatan;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataKecamatan, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataKecamatan != 0) {
                    try {
                        entContentDataKecamatan = PstContentDataKecamatan.fetchExc(oidContentDataKecamatan);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataKecamatan.requestEntityObject(entContentDataKecamatan);

                if (frmContentDataKecamatan.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataKecamatan.getOID() == 0) {
                    try {
                        long oid = pstContentDataKecamatan.insertExc(this.entContentDataKecamatan);
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
                        long oid = pstContentDataKecamatan.updateExc(this.entContentDataKecamatan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }

                }
                break;

            case Command.EDIT:
                if (oidContentDataKecamatan != 0) {
                    try {
                        entContentDataKecamatan = PstContentDataKecamatan.fetchExc(oidContentDataKecamatan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataKecamatan != 0) {
                    try {
                        entContentDataKecamatan = PstContentDataKecamatan.fetchExc(oidContentDataKecamatan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataKecamatan != 0) {
                    try {
                        long oid = PstContentDataKecamatan.deleteExc(oidContentDataKecamatan);
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
