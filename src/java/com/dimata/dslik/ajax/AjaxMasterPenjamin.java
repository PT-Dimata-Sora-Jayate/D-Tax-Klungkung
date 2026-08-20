/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimata.dslik.ajax;

import com.dimata.dslik.entity.contentdata.ContentDataGolonganDebitur;
import com.dimata.dslik.entity.contentdata.ContentDataJenisIdentitas;
import com.dimata.dslik.entity.contentdata.PstContentDataGolonganDebitur;
import com.dimata.dslik.entity.contentdata.PstContentDataJenisIdentitas;
import com.dimata.dslik.entity.masterdata.MasterPenjamin;
import com.dimata.dslik.entity.masterdata.PstMasterPenjamin;
import com.dimata.dslik.form.masterdata.CtrlMasterPenjamin;
import com.dimata.dslik.form.masterdata.FrmMasterPenjamin;
import com.dimata.gui.jsp.ControlCombo;
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
 * @author Dimata 007
 */
public class AjaxMasterPenjamin extends HttpServlet {

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
            this.jSONObject.put("RETURN_DATA_MASTER_PENJAMIN", this.jSONArray);
        } catch (JSONException jSONException) {
            jSONException.printStackTrace();
        }

        response.getWriter().print(this.jSONObject);
    }
    
    public void commandNone(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = showForm(request);
        } else if (this.dataFor.equals("selectmasterpenjamin")) {
            getDataMasterPenjamin(request);
        }
    }
    
    public void getDataMasterPenjamin(HttpServletRequest request){
        MasterPenjamin penjamin = new MasterPenjamin();
        try {
            penjamin = PstMasterPenjamin.fetchExc(oid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("no_identitas", "" + penjamin.getNoIdPenjamin());
            jsonObject.put("jenis_identitas", "" + penjamin.getJenisIdentitas());
            jsonObject.put("nama_sesuai_identitas", "" + penjamin.getNamaIdentitas());
            jsonObject.put("nama_lengkap", "" + penjamin.getNamaLengkap());
            jsonObject.put("kode_golongan_penjamin", "" + penjamin.getKodeGolPenjamin());
            jsonObject.put("alamat_penjamin", "" + penjamin.getAlamatPenjamin());
            jsonObject.put("keterangan", "" + penjamin.getKeterangan());
            this.jSONArray.put(jsonObject);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public void commandSave(HttpServletRequest request) {
        if (this.dataFor.equals("showform")) {
            this.htmlReturn = saveMasterPenjamin(request);
        }
    }

    public void commandDeleteAll(HttpServletRequest request) {
        if (this.dataFor.equals("deleteAll")) {
//            this.htmlReturn = deleteAll(request);
        }
    }
    
    public void commandList(HttpServletRequest request, HttpServletResponse response) {
        if (this.dataFor.equals("list") || this.dataFor.equals("listselect")) {
            String[] cols = {
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NO_ID_PENJAMIN],
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_JENIS_IDENTITAS],
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_IDENTITAS],
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_LENGKAP],
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KODE_GOL_PENJAMIN],
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_ALAMAT_PENJAMIN],
                PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KETERANGAN]                
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

        if (dataFor.equals("list") || dataFor.equals("listselect")) {

            if (whereClause.length() > 0) {
                whereClause += "AND "
                        + " (" + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NO_ID_PENJAMIN] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_JENIS_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_LENGKAP] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KODE_GOL_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_ALAMAT_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                        + " )";
            } else {
                whereClause += ""
                        + " (" + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NO_ID_PENJAMIN] + " LIKE '%" + searchTerm + "%' "
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_JENIS_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_LENGKAP] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KODE_GOL_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_ALAMAT_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                        + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                        + " )";
            }
        }

        String colName = cols[col];
        int total = -1;

        if (dataFor.equals("list") || dataFor.equals("listselect")) {
            total = PstMasterPenjamin.getCount(whereClause);
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
        MasterPenjamin masterPenjamin = new MasterPenjamin();
        String whereClause = "";
        String order = "";

        String appRoot = FRMQueryString.requestString(request, "FRM_FLD_APP_ROOT");

        if (this.searchTerm == null) {
            whereClause += "";
        } else {
            if (datafor.equals("list") || datafor.equals("listselect")) {

                if (whereClause.length() > 0) {
                    whereClause += "AND "
                            + " (" + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NO_ID_PENJAMIN] + " LIKE '%" + searchTerm + "%' "
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_JENIS_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_LENGKAP] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KODE_GOL_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_ALAMAT_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                            + " )";
                } else {
                    whereClause += ""
                            + " (" + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NO_ID_PENJAMIN] + " LIKE '%" + searchTerm + "%' "
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_JENIS_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_IDENTITAS] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_NAMA_LENGKAP] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KODE_GOL_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_ALAMAT_PENJAMIN] + " LIKE '%" + searchTerm + "%'"
                            + " OR " + PstMasterPenjamin.fieldNames[PstMasterPenjamin.FLD_KETERANGAN] + " LIKE '%" + searchTerm + "%'"
                            + " )";
                }
            }
        }

        if (this.colOrder >= 0) {
            order += "" + colName + " " + dir + "";
        }

        Vector listData = new Vector(1, 1);
        if (datafor.equals("list") || datafor.equals("listselect")) {
            listData = PstMasterPenjamin.list(start, amount, whereClause, order);
        }

        for (int i = 0; i <= listData.size() - 1; i++) {
            JSONArray ja = new JSONArray();            
            String buttonAction = "";
            if (datafor.equals("list") || datafor.equals("listselect")) {
                masterPenjamin = (MasterPenjamin) listData.get(i);
                Vector<ContentDataJenisIdentitas> listJenisIdentitas = PstContentDataJenisIdentitas.listJenisIdentitasPengurusWithoutOid(0, 0, "" + PstContentDataJenisIdentitas.fieldNames[PstContentDataJenisIdentitas.FLD_KODE_OJK] + " = '" + masterPenjamin.getJenisIdentitas() + "'", "");
                Vector<ContentDataGolonganDebitur> listGolonganDebitur = PstContentDataGolonganDebitur.listWithoutOid(0, 0, "" + PstContentDataGolonganDebitur.fieldNames[PstContentDataGolonganDebitur.FLD_KODE_OJK] + " = '"  + masterPenjamin.getKodeGolPenjamin() + "'", "");
                ja.put("" + (this.start + i + 1));
                ja.put("" + masterPenjamin.getNoIdPenjamin());
                ja.put("" + listJenisIdentitas.get(0).getNamaIdentitas());
                ja.put("" + masterPenjamin.getNamaIdentitas());
                ja.put("" + masterPenjamin.getNamaLengkap());
                ja.put("" + listGolonganDebitur.get(0).getGolonganDebitur());
                ja.put("" + masterPenjamin.getAlamatPenjamin());
                ja.put("" + masterPenjamin.getKeterangan());
                
                if (datafor.equals("listselect")) {
                    ja.put("<button type='button' class='btn btn-sm btn-success btn-selectnoidentitas' data-oid='" + masterPenjamin.getOID() + "' data-for='selectmasterpenjamin' data-command='" + Command.NONE + "' data-target='AjaxMasterPenjamin'><i class='fa fa-check'></i> Select</button>");
                } else {
                    if (privUpdate) {
                        buttonAction += "<button type='button' class='btn btn-warning btneditgeneral' data-oid='" + masterPenjamin.getOID() + "' data-for='showform'><i class='fa fa-pencil'></i> Edit</button>";
                    }

                    if (privDelete) {
                        buttonAction += "<button class='btn btn-danger button-delete' type='button' data-oid='" + masterPenjamin.getOID() + "' data-for='delete' data-command = '" + Command.DELETE + "'>Delete</button>";
                    }
                    ja.put(buttonAction);
                }

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
        MasterPenjamin masterPenjamin = new MasterPenjamin();
        if (oid != 0) {
            try {
                masterPenjamin = PstMasterPenjamin.fetchExc(oid);
            } catch (Exception e) {
            }
        }
        String returnData = ""
            + "<div class='row'>"
                + "<div class='col-md-12'>"
                
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Nomor Identitas Penjamin</label>"
                        + "</div>"
                        + "<div class='col-sm-8'>"
                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_NO_ID_PENJAMIN] + "' value='" + masterPenjamin.getNoIdPenjamin()+ "'>"
                        + "</div>"                        
                    + "</div>"
                
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Jenis Identitas</label>"
                        + "</div>"
//                        + "<div class='col-sm-8'>"
//                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_JENIS_IDENTITAS] + "' value='" + masterPenjamin.getJenisIdentitas()+ "'>"
//                        + "</div>"   
                        + "<div class='col-sm-8'>";
                            Vector listJenisIdentitas = PstContentDataJenisIdentitas.listJenisIdentitasPengurusWithoutOid(0, 0, "", "");
                            Vector jenisIdentitasKey = new Vector(1,1);
                            Vector jenisIdentitasVal = new Vector(1,1);
                            if(listJenisIdentitas.size() > 0){
                                for(int i = 0; i < listJenisIdentitas.size(); i++){
                                    ContentDataJenisIdentitas contentDataJenisIdentitas = (ContentDataJenisIdentitas) listJenisIdentitas.get(i);
                                    jenisIdentitasKey.add(""+contentDataJenisIdentitas.getKodeOjk());
                                    jenisIdentitasVal.add(""+contentDataJenisIdentitas.getNamaIdentitas());
                                }
                            }
                            returnData += ""
			    + ControlCombo.draw(FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_JENIS_IDENTITAS], "-- Pilih --", ""+masterPenjamin.getJenisIdentitas(), jenisIdentitasKey, jenisIdentitasVal, "", "form-control")
			+ "</div>"
                    + "</div>"              
                
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Nama Identitas</label>"
                        + "</div>"
                        + "<div class='col-sm-8'>"
                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_NAMA_IDENTITAS] + "' value='" + masterPenjamin.getNamaIdentitas()+ "'>"
                        + "</div>"
                    + "</div>"
                
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Nama Lengkap</label>"
                        + "</div>"
                        + "<div class='col-sm-8'>"
                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_NAMA_LENGKAP] + "' value='" + masterPenjamin.getNamaLengkap()+ "'>"
                        + "</div>"
                    + "</div>"
                
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Kode Golongan Penjamin</label>"
                        + "</div>"
//                        + "<div class='col-sm-8'>"
//                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_KODE_GOL_PENJAMIN] + "' value='" + masterPenjamin.getKodeGolPenjamin()+ "'>"
//                        + "</div>"
                        + "<div class='col-sm-8'>";
                            Vector listGolonganPenjamin = PstContentDataGolonganDebitur.listWithoutOid(0, 0, "", "");
                            Vector golonganPenjaminKey = new Vector(1,1);
                            Vector golonganPenjaminVal = new Vector(1,1);
                            if(listGolonganPenjamin.size() > 0){
                                for(int i = 0; i < listGolonganPenjamin.size(); i++){
                                    ContentDataGolonganDebitur contentDataGolonganDebitur = (ContentDataGolonganDebitur) listGolonganPenjamin.get(i);
                                    golonganPenjaminKey.add(""+contentDataGolonganDebitur.getKodeOjk());
                                    golonganPenjaminVal.add(""+contentDataGolonganDebitur.getGolonganDebitur());
                                }
                            }
                            returnData +=""
                            + ControlCombo.draw(FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_KODE_GOL_PENJAMIN], "-- Pilih --", ""+masterPenjamin.getKodeGolPenjamin(), golonganPenjaminKey, golonganPenjaminVal, "", "form-control")
                        + "</div>"
                    + "</div>"                                                                                            
                                    
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Alamat Penjamin</label>"
                        + "</div>"
                        + "<div class='col-sm-8'>"
                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_ALAMAT_PENJAMIN] + "' value='" + masterPenjamin.getAlamatPenjamin()+ "'>"
                        + "</div>"
                    + "</div>"
                
                    + "<div class='form-group'>"
                        + "<div class='col-sm-4'>"
                            + "<label>Keterangan</label>"
                        + "</div>"
                        + "<div class='col-sm-8'>"
                            + "<input type='text' class='form-control' name='" + FrmMasterPenjamin.fieldNames[FrmMasterPenjamin.FRM_FIELD_KETERANGAN] + "' value='" + masterPenjamin.getKeterangan()+ "'>"
                        + "</div>"
                    + "</div>"
                
                + "</div>"
            + "</div>";

        return returnData;
    }

    public String saveMasterPenjamin(HttpServletRequest request) {
        String returnData = "";
        CtrlMasterPenjamin penjamin = new CtrlMasterPenjamin(request);
        penjamin.action(iCommand, oid);
        returnData = penjamin.getMessage();
        return returnData;
    }
    
    public String deleteAll(HttpServletRequest request) {
        String returnData = "";
        CtrlMasterPenjamin penjamin = new CtrlMasterPenjamin(request);
        penjamin.action(iCommand, oid);
        returnData = penjamin.getMessage();
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
