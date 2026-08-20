/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.dslik.entity.masterdata.Bank;
import com.dimata.dslik.entity.masterdata.PstBank;
import com.dimata.dslik.form.masterdata.CtrlBank;
import com.dimata.dslik.form.masterdata.FrmBank;
import com.dimata.qdep.form.FRMQueryString;
import com.dimata.util.Command;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Dewa
 */
public class AjaxBank extends HttpServlet {

    //DATATABLES
    private String searchTerm;
    private String colName;
    private int colOrder;
    private String dir;
    private int start;
    private int amount;

    //OBJECT
    private JSONObject jSONObject = new JSONObject();
    private JSONArray jSONArray = new JSONArray();

    //LONG
    private long oid = 0;
    private long oidReturn = 0;
    
    //BOOLEAN
    private boolean privDelete = false;
    private boolean privUpdate = false;

    //STRING
    private String dataFor = "";
    private String oidDelete = "";
    private String approot = "";
    private String htmlReturn = "";
    private String dateStart = "";
    private String dateEnd = "";

    //INT
    private int iCommand = 0;
    private int iErrCode = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //LONG
        this.oid = FRMQueryString.requestLong(request, "FRM_FIELD_OID");
        this.oidReturn = 0;

        //STRING
        this.dataFor = FRMQueryString.requestString(request, "FRM_FIELD_DATA_FOR");
        this.oidDelete = FRMQueryString.requestString(request, "FRM_FIELD_OID_DELETE");
        this.approot = FRMQueryString.requestString(request, "FRM_FIELD_APPROOT");
        this.htmlReturn = "";
        
        //BOOLEAN
        this.privDelete = FRMQueryString.requestBoolean(request, "privdelete");
        this.privUpdate = FRMQueryString.requestBoolean(request, "privupdate");

        //INT
        this.iCommand = FRMQueryString.requestCommand(request);
        this.iErrCode = 0;

        //OBJECT
        this.jSONObject = new JSONObject();

        switch (this.iCommand) {
            case Command.SAVE:
                commandSave(request);
                break;

            case Command.LIST:
                commandList(request, response);
                break;

            case Command.DELETEALL:
                commandDeleteAll(request);
                break;

            case Command.DELETE:
                commandDelete(request);
                break;

            default:
                commandNone(request);
        }
        try {

            this.jSONObject.put("FRM_FIELD_HTML", this.htmlReturn);
            this.jSONObject.put("FRM_FIELD_RETURN_OID", this.oidReturn);
            this.jSONObject.put("FRM_FIELD_DATE_START", this.dateStart);
            this.jSONObject.put("FRM_FIELD_DATE_END", this.dateEnd);
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }

        response.getWriter().print(this.jSONObject);

    }

    public void commandNone(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = showForm(request);
        }
    }

    public void commandSave(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = saveBank(request);
        }
    }

    public void commandDeleteAll(HttpServletRequest request) {
        if (this.dataFor.equals("deleteAll")) {
            this.htmlReturn = deleteAll(request);
        }
    }

    public void commandList(HttpServletRequest request, HttpServletResponse response) {
        if (this.dataFor.equals("list")) {
            String[] cols = {
                PstBank.fieldNames[PstBank.FLD_BANK_ID],
                PstBank.fieldNames[PstBank.FLD_NAMA_BANK],
                PstBank.fieldNames[PstBank.FLD_ALAMAT_BANK],
                PstBank.fieldNames[PstBank.FLD_PROVINSI],
                PstBank.fieldNames[PstBank.FLD_KABUPATEN],
                PstBank.fieldNames[PstBank.FLD_EMAIL]
            };
            jSONObject = listDataTables(request, response, cols, this.dataFor, this.jSONObject);
        }
    }

    public void commandDelete(HttpServletRequest request) {
        if (this.dataFor.equals("delete")) {
            this.htmlReturn = deleteAll(request);
        }
    }

    public JSONObject listDataTables(HttpServletRequest request, HttpServletResponse response, String[] cols, String dataFor, JSONObject result) {
        this.searchTerm = FRMQueryString.requestString(request, "sSearch");
        int amount = 10;
        int start = 0;
        int col = 0;
        String dir = "asc";
        String sStart = request.getParameter("iDisplayStart");
        String sAmount = request.getParameter("iDisplayLength");
        String sCol = request.getParameter("iSortCol_0");
        String sdir = request.getParameter("sSortDir_0");

        if (sStart != null) {
            start = Integer.parseInt(sStart);
            if (start < 0) {
                start = 0;
            }
        }
        if (sAmount != null) {
            amount = Integer.parseInt(sAmount);
            if (amount < 10) {
                amount = 10;
            }
        }
        if (sCol != null) {
            col = Integer.parseInt(sCol);
            if (col < 0) {
                col = 0;
            }
        }
        if (sdir != null) {
            if (!sdir.equals("asc")) {
                dir = "desc";
            }
        }

        String whereClause = "";

        if (dataFor.equals("list")) {

            if (whereClause.length() > 0) {
                whereClause += "AND (" + PstBank.fieldNames[PstBank.FLD_NAMA_BANK] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstBank.fieldNames[PstBank.FLD_ALAMAT_BANK] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstBank.fieldNames[PstBank.FLD_PROVINSI] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstBank.fieldNames[PstBank.FLD_KABUPATEN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstBank.fieldNames[PstBank.FLD_EMAIL] + " LIKE '%" + searchTerm + "%')";
            } else {
                whereClause += " (" + PstBank.fieldNames[PstBank.FLD_NAMA_BANK] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstBank.fieldNames[PstBank.FLD_ALAMAT_BANK] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstBank.fieldNames[PstBank.FLD_PROVINSI] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstBank.fieldNames[PstBank.FLD_KABUPATEN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstBank.fieldNames[PstBank.FLD_EMAIL] + " LIKE '%" + searchTerm + "%')";
            }
        }

        String colName = cols[col];
        int total = -1;

        if (dataFor.equals("list")) {
            total = PstBank.getCount(whereClause);
        }

        this.amount = amount;

        this.colName = colName;
        this.dir = dir;
        this.start = start;
        this.colOrder = col;

        try {
            result = getData(total, request, dataFor);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return result;
    }

    public JSONObject getData(int total, HttpServletRequest request, String datafor) {

        int totalAfterFilter = total;
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        Bank bank = new Bank();
        String whereClause = "";
        String order = "";

        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");

        if (this.searchTerm == null) {
            whereClause += "";
        } else {
            if (datafor.equals("list")) {

                if (whereClause.length() > 0) {
                    whereClause += "AND (" + PstBank.fieldNames[PstBank.FLD_NAMA_BANK] + " LIKE '%" + searchTerm + "%' "
                            + " OR " + PstBank.fieldNames[PstBank.FLD_ALAMAT_BANK] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstBank.fieldNames[PstBank.FLD_PROVINSI] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstBank.fieldNames[PstBank.FLD_KABUPATEN] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstBank.fieldNames[PstBank.FLD_EMAIL] + " LIKE '%" + searchTerm + "%')";
                } else {
                    whereClause += " (" + PstBank.fieldNames[PstBank.FLD_NAMA_BANK] + " LIKE '%" + searchTerm + "%' "
                            + " OR " + PstBank.fieldNames[PstBank.FLD_ALAMAT_BANK] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstBank.fieldNames[PstBank.FLD_PROVINSI] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstBank.fieldNames[PstBank.FLD_KABUPATEN] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstBank.fieldNames[PstBank.FLD_EMAIL] + " LIKE '%" + searchTerm + "%')";
                }
            }
        }

        if (this.colOrder >= 0) {
            order += "" + colName + " " + dir + "";
        }

        Vector listData = new Vector(1, 1);
        if (datafor.equals("list")) {
            listData = PstBank.list(start, amount, whereClause, order);
        }

        for (int i = 0; i <= listData.size() - 1; i++) {
            JSONArray ja = new JSONArray();
            String buttonAction = "";
            if (datafor.equals("list")) {
                bank = (Bank) listData.get(i);
                ja.put("" + (this.start + i + 1));
                ja.put("" + bank.getNamaBank());
                ja.put("" + bank.getAlamatBank());
                ja.put("" + bank.getProvinsi());
                ja.put("" + bank.getKabupaten());
                ja.put("" + bank.getEmail());
                
                if(privUpdate){
                  buttonAction += "<button type='button' class='btn btn-warning btneditgeneral' data-oid='" + bank.getOID() + "' data-for='showform'><i class='fa fa-pencil'></i> Edit</button>";  
                }
                
                if(privDelete){
                    buttonAction += "<button class='btn btn-danger button-delete' type='button' data-oid='" + bank.getOID() + "' data-for='delete' data-command = '" + Command.DELETE + "'>Delete</button>";
                }
                ja.put(buttonAction);

                array.put(ja);
            }
        }

        totalAfterFilter = total;

        try {
            result.put("iTotalRecords", total);
            result.put("iTotalDisplayRecords", totalAfterFilter);
            result.put("aaData", array);
        } catch (Exception e) {

        }

        return result;
    }

    public String showForm(HttpServletRequest request) {
        Bank bank = new Bank();
        if (oid != 0) {
            try {
                bank = PstBank.fetchExc(oid);
            } catch (Exception e) {
            }
        }
        String returnData = ""
            + "<div class='row'>"
                + "<div class='col-md-12'>"
                    + "<div class='form-group'>"
                        + "<label>Nama Bank</label>"
                        + "<input type='text' class='form-control' name='" + FrmBank.fieldNames[FrmBank.FRM_FIELD_NAMA_BANK] + "' value='" + bank.getNamaBank()+ "'>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Alamat Bank</label>"
                        + "<input type='text' class='form-control' name='" + FrmBank.fieldNames[FrmBank.FRM_FIELD_ALAMAT_BANK] + "' value='" + bank.getAlamatBank()+ "'>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Nama Provinsi</label>"
                        + "<input type='text' class='form-control' name='" + FrmBank.fieldNames[FrmBank.FRM_FIELD_PROVINSI] + "' value='" + bank.getProvinsi()+ "'>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Nama Kabupaten</label>"
                        + "<input type='text' class='form-control' name='" + FrmBank.fieldNames[FrmBank.FRM_FIELD_KABUPATEN] + "' value='" + bank.getKabupaten()+ "'>"
                    + "</div>"
                    + "<div class='form-group'>"
                        + "<label>Email</label>"
                        + "<input type='text' class='form-control' name='" + FrmBank.fieldNames[FrmBank.FRM_FIELD_EMAIL] + "' value='" + bank.getEmail()+ "'>"
                    + "</div>"
                + "</div>"
            + "</div>";

        return returnData;
    }

    public String saveBank(HttpServletRequest request) {
        String returnData = "";
        CtrlBank bank = new CtrlBank(request);
        bank.action(iCommand, oid, oidDelete);
        returnData = bank.getMessage();
        return returnData;
    }

    public String deleteAll(HttpServletRequest request) {
        String returnData = "";
        CtrlBank bank = new CtrlBank(request);
        bank.action(iCommand, oid, oidDelete);
        returnData = bank.getMessage();
        return returnData;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
