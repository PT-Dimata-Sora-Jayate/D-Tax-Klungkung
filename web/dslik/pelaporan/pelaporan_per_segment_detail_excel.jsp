<%-- 
    Document   : pelaporan_per_segment_detail_excel
    Created on : Dec 14, 2016, 5:11:05 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.util.Formater"%>
<%-- 
    Document   : pelaporan_per_segment
    Created on : Oct 2, 2016, 6:41:31 PM
    Author     : dimata005
--%>
<%@page import="com.dimata.gui.jsp.ControlList"%>
<%@page import="com.dimata.dslik.entity.debiturindividu.PstDebiturIndividu"%>
<%@page import="com.dimata.dslik.entity.suratberharga.PstSuratBerharga"%>
<%@page import="com.dimata.dslik.entity.penjamin.PstPenjamin"%>
<%@page import="com.dimata.dslik.entity.pengurusataupemilik.PstPengurusAtauPemilik"%>
<%@page import="com.dimata.dslik.entity.laporankeuangandebitur.PstLaporanKeuanganDebitur"%>
<%@page import="com.dimata.dslik.entity.kreditjoinaccount.PstKreditJoinAccount"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.irrevocablelc.PstIrrevocableLc"%>
<%@page import="com.dimata.dslik.entity.fasilitaslain.PstFasilitasLain"%>
<%@page import="com.dimata.dslik.entity.debiturbdnusaha.PstDebiturBdnUsaha"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.agunan.PstAgunan"%>
<%@page import="com.dimata.dslik.ajax.AjaxPelaporan"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.dslik.session.proses.ManagerTransferData"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page import="com.dimata.dslik.form.masterdata.FrmConnection"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%!
    public final static int SEGMEN_DEBITUR_INDIVIDU = 0;
    public final static int SEGMEN_DEBITUR_BADAN_USAHA = 1;
    public final static int SEGMEN_PENGURUS_PEMILIK = 2;
    public final static int SEGMEN_KREDIT = 3;
    public final static int SEGMEN_BANK_GARANSI = 4;
    public final static int SEGMEN_AGUNAN = 5;
    public final static int SEGMEN_PENJAMIN = 6;
    public final static int SEGMEN_LAPORAN_KEUANGAN_DEBITUR = 7;
    public final static int SEGMEN_SURAT_BERHARGA = 8;
    public final static int SEGMEN_KREDIT_JOIN = 9;
    public final static int SEGMEN_IRREVOCABLE_LC = 10;
    public final static int SEGMEN_FASILITAS_LAINNYA = 11;
    
    public final static String[] segmenNames = {
        "Debitur_Individu",
        "Debitur_Badan_Usaha",
        "Pengurus_Pemilik",
        "Kredit",
        "Bank_Garansi",
        "Agunan",
        "Penjamin",
        "Laporan_Keuangan_Debitur",
        "Surat_Berharga",
        "Kredit_Join",
        "Irrevocable",
        "Fasilitas_Lainnya"
    };

    public String getPerSegmentReport(JspWriter outObj, long periode, String cabang, int segment, int kelengkapan){
        String returnData = "";
        String whereClause = "";
        Vector listObj = new Vector(1,1);
        switch(segment){
            case SEGMEN_AGUNAN :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstAgunan.fieldNames[PstAgunan.FLD_STATUS_DATA_PERUBAHAN]+" IS NULL ";
                }else{}

                listObj = PstAgunan.list(0,0,PstAgunan.fieldNames[PstAgunan.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, ""+PstAgunan.fieldNames[PstAgunan.FLD_KODE_REGISTER_AGUNAN]+" ASC");
            break;
                
            case SEGMEN_BANK_GARANSI :
                
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstBankGaransi.list(0,0, PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause,""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_DEBITUR_BADAN_USAHA :
                if(cabang.length() > 0){
                    //whereClause +=" AND dup."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstDebiturBdnUsaha.listJoinReport(0,0,"dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_PERIODE_ID]+"='"+periode+"' "
                        + "AND dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_KODE_JENIS_NSB]+"!='1'"
                        + ""+whereClause, "dslik_debitur."+PstDebiturBdnUsaha.fieldNames[PstDebiturBdnUsaha.FLD_CIF]+" ASC",periode,cabang);
            break;
                
            case SEGMEN_FASILITAS_LAINNYA :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstFasilitasLain.list(0,0,""+PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstFasilitasLain.fieldNames[PstFasilitasLain.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_IRREVOCABLE_LC :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstIrrevocableLc.list(0,0,""+PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstIrrevocableLc.fieldNames[PstIrrevocableLc.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_KREDIT :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstKredit.list(0,0,""+PstKredit.fieldNames[PstKredit.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstKredit.fieldNames[PstKredit.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_KREDIT_JOIN :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstKreditJoinAccount.list(0,0,""+PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstKreditJoinAccount.fieldNames[PstKreditJoinAccount.FLD_NO_REKENING]+" ASC");
            break;
                
            case SEGMEN_LAPORAN_KEUANGAN_DEBITUR :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstLaporanKeuanganDebitur.list(0,0,""+PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstLaporanKeuanganDebitur.fieldNames[PstLaporanKeuanganDebitur.FLD_CIF]+" ASC");
            break;
                
            case SEGMEN_PENGURUS_PEMILIK :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstPengurusAtauPemilik.list(0,0,""+PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstPengurusAtauPemilik.fieldNames[PstPengurusAtauPemilik.FLD_NO_IDENTITAS]+" ASC");
            break;
                
            case SEGMEN_PENJAMIN : 
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstPenjamin.fieldNames[PstPenjamin.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstPenjamin.list(0,0,""+PstPenjamin.fieldNames[PstPenjamin.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstPenjamin.fieldNames[PstPenjamin.FLD_NO_ID_PENJAMIN]+" ASC");
            break;
                
            case SEGMEN_SURAT_BERHARGA :
                if(cabang.length() > 0){
                    whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstSuratBerharga.list(0,0,""+PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_PERIODE_ID]+"='"+periode+"'"
                        + ""+whereClause, PstSuratBerharga.fieldNames[PstSuratBerharga.FLD_NO_REKENING]+" ASC");
            break;
            default:
                if(cabang.length() > 0){
                   // whereClause +=" AND dup."+PstKredit.fieldNames[PstKredit.FLD_KODE_KANTOR_CABANG]+"='"+cabang+"' ";
                }
                if(kelengkapan==1){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+"='0' ";
                }else if(kelengkapan ==2 ){
                    whereClause +=" AND "+PstKredit.fieldNames[PstKredit.FLD_STATUS_PERUBAHAN_DATA]+" IS NULL ";
                }else{}
                listObj = PstDebiturIndividu.listJoinReport(0,0,"dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_PERIODE_ID]+"='"+periode+"' "
                        + "AND dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_KODE_JENIS_NSB]+"='1'"
                        + ""+whereClause, "dslik_debitur."+PstDebiturIndividu.fieldNames[PstDebiturIndividu.FLD_CIF]+" ASC",periode, cabang);
            break;
        }
        
        drawReportPerSegment( outObj, 0, listObj, segment);
        return"";
    }


    public void drawReportPerSegment(JspWriter outObj, int iCommand, Vector objectClass, int segment) {

        ControlList ctrlist = new ControlList();

        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        
        ctrlist.addHeader("NO", "200px");//1
        ctrlist.addHeader("Cabang", "200px");//2
        ctrlist = AjaxPelaporan.draControlList(ctrlist, segment);
        
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.reset();

        int index = -1;
        int count=0;
        Vector rowx = new Vector(1, 1);
        double total=0.0;
        
        lstData = AjaxPelaporan.drawRowx(objectClass,segment,lstData);

        ctrlist.drawBootstrapStrip(outObj);
    }


%>
<%@include file="../../main/javainit_slik.jsp" %>
<%    
    int iCommand = FRMQueryString.requestCommand(request);
    String startDate = FRMQueryString.requestString(request, "tanggalStart");
    String oDate = "";
    String startEnd = FRMQueryString.requestString(request, "tanggalEnd");
    int start = FRMQueryString.requestInt(request, "start");
    int prevCommand = FRMQueryString.requestInt(request, "prev_command");
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
    int getSegment = FRMQueryString.requestInt(request, "segment");
    int kelengkapan = FRMQueryString.requestInt(request, "kelengkapan");
    
    CabangBank cabangBank = new CabangBank();
     String namaCabang="";
    if(!cabang.equals("")){
        Vector listCabang = PstCabangBank.list(0, 0, ""+PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'", ""+PstCabangBank.fieldNames[PstCabangBank.FLD_NAMA_CABANG]+" ASC");
        if(listCabang.size() > 0){
            for(int i = 0; i < listCabang.size(); i++){
                 cabangBank = (CabangBank) listCabang.get(i);
            }
        }
    }
    Periode periodev = new Periode();
    if(periodeId!=0){
        periodev= PstPeriode.fetchExc(periodeId);
    }
    
    String file = "LAPORAN_PER_SEGMEN_DETAIL";
    
    if(cabang.length() > 0){
        file+="_CABANG/CAPEM_"+cabang;
    }
    if(getSegment==-1){
        file += "(SEMUA_SEGMEN)";
    }else{
        file += "("+segmenNames[getSegment].toUpperCase()+")";
    }
    
    response.setContentType("application/x-msexcel"); 
    response.setHeader("Content-Disposition","attachment; filename="+file+".xls" ); 
%>
<html>
    <head>
        <title>SLIK | Laporan Per Segment Detail</title>
    </head>
    <body class="<%= skin%>">
         <div class="row">
            <div class="col-md-12" id="reportsummarypersegment">
                <div class='row'>
                    <div class='col-md-12'>
                        <div class='form-group'>
                            <%if(getSegment==-1){
                                for(int i = 0; i < AjaxPelaporan.segmenNames.length; i++){%>
                                    <table width="100%" cellspacing="0">
                                            <tr align="left" valign="top">
                                                <td colspan="2">
                                                      <b><%=cabangBank.getNamaCabang()%> : </b>
                                                </td>
                                                 <td colspan="3">
                                                      <b><%=segmenNames[i]%></b>
                                                </td>
                                              </tr>
                                    </table>
                                    <%=getPerSegmentReport(out, periodeId, cabang, i,kelengkapan)%>
                                    <table width="100%" cellspacing="0">
                                            <tr align="left" valign="top">
                                                <td></td>
                                              </tr>
                                    </table>
                                    <table width="100%" cellspacing="0">
                                            <tr align="left" valign="top">
                                                <td></td>
                                              </tr>
                                    </table>            
                                <%}
                                %>
                            <%}else{%>
                                <b><%=cabangBank.getNamaBank()%> - <%=segmenNames[getSegment]%></b><br>
                                <%=getPerSegmentReport(out, periodeId, cabang, getSegment,kelengkapan)%>
                            <%}%>
                        </div>
                    </div>
                </div>
                <div class='row'>
                    <div class='col-md-12'>
                        <div class='form-group'>
                            &nbsp;
                        </div>
                    </div>
                </div>  
            </div>
        </div>
    </body>
</html>
