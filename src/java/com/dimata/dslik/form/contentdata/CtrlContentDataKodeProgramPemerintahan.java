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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.dimata.dslik.entity.contentdata.ContentDataKodeProgramPemerintah;
import com.dimata.dslik.entity.contentdata.PstContentDataKodeProgramPemerintah;
import javax.servlet.http.*;
import com.dimata.util.*;
import com.dimata.util.lang.*;
import com.dimata.qdep.system.*;
import com.dimata.qdep.form.*;
import com.dimata.qdep.db.*;

/*
 Description : Controll ContentDataKodePekerjaan
 Date : Sun Sep 25 2016
 Author : Dewa
 */
public class CtrlContentDataKodeProgramPemerintahan extends Control implements I_Language {

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
    private ContentDataKodeProgramPemerintah entContentDataKodeProgramPemerintah;
    private PstContentDataKodeProgramPemerintah pstContentDataKodeProgramPemerintah;
    private FrmContentDataKodeProgramPemerintahan frmContentDataKodeProgramPemerintah;
    int language = LANGUAGE_DEFAULT;

    public CtrlContentDataKodeProgramPemerintahan(HttpServletRequest request) {
        msgString = "";
        entContentDataKodeProgramPemerintah = new ContentDataKodeProgramPemerintah();
        try {
            pstContentDataKodeProgramPemerintah = new PstContentDataKodeProgramPemerintah(0);
        } catch (Exception e) {;
        }
        frmContentDataKodeProgramPemerintah = new FrmContentDataKodeProgramPemerintahan(request, entContentDataKodeProgramPemerintah);
    }

    private String getSystemMessage(int msgCode) {
        switch (msgCode) {
            case I_DBExceptionInfo.MULTIPLE_ID:
                this.frmContentDataKodeProgramPemerintah.addError(frmContentDataKodeProgramPemerintah.FRM_FIELD_KREDIT_PROGRAM_PEMERINTAH_OID, resultText[language][RSLT_EST_CODE_EXIST]);
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

    public ContentDataKodeProgramPemerintah getContentDataKodeProgramPemerintah() {
        return entContentDataKodeProgramPemerintah;
    }

    public FrmContentDataKodeProgramPemerintahan getForm() {
        return frmContentDataKodeProgramPemerintah;
    }

    public String getMessage() {
        return msgString;
    }

    public int getStart() {
        return start;
    }

    public int action(int cmd, long oidContentDataKodePekerjaan, String oidDelete) {
        msgString = "";
        int excCode = I_DBExceptionInfo.NO_EXCEPTION;
        int rsCode = RSLT_OK;
        switch (cmd) {
            case Command.ADD:
                break;

            case Command.SAVE:
                if (oidContentDataKodePekerjaan != 0) {
                    try {
                        entContentDataKodeProgramPemerintah = PstContentDataKodeProgramPemerintah.fetchExc(oidContentDataKodePekerjaan);
                    } catch (Exception exc) {
                    }
                }

                frmContentDataKodeProgramPemerintah.requestEntityObject(entContentDataKodeProgramPemerintah);

                if (frmContentDataKodeProgramPemerintah.errorSize() > 0) {
                    msgString = FRMMessage.getMsg(FRMMessage.MSG_INCOMPLATE);
                    return RSLT_FORM_INCOMPLETE;
                }

                if (entContentDataKodeProgramPemerintah.getOID() == 0) {
                    try {
                        long oid = PstContentDataKodeProgramPemerintah.insertExc(this.entContentDataKodeProgramPemerintah);
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
                        long oid = PstContentDataKodeProgramPemerintah.updateExc(this.entContentDataKodeProgramPemerintah);
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
                if (oidContentDataKodePekerjaan != 0) {
                    try {
                        entContentDataKodeProgramPemerintah = PstContentDataKodeProgramPemerintah.fetchExc(oidContentDataKodePekerjaan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.ASK:
                if (oidContentDataKodePekerjaan != 0) {
                    try {
                        entContentDataKodeProgramPemerintah = PstContentDataKodeProgramPemerintah.fetchExc(oidContentDataKodePekerjaan);
                    } catch (DBException dbexc) {
                        excCode = dbexc.getErrorCode();
                        msgString = getSystemMessage(excCode);
                    } catch (Exception exc) {
                        msgString = getSystemMessage(I_DBExceptionInfo.UNKNOWN);
                    }
                }
                break;

            case Command.DELETE:
                if (oidContentDataKodePekerjaan != 0) {
                    try {
                        long oid = PstContentDataKodeProgramPemerintah.deleteExc(oidContentDataKodePekerjaan);
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
