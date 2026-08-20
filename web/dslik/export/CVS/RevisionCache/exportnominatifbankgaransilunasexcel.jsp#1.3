<%-- 
    Document   : exportnominatifbankgaransilunasexcel
    Created on : Jan 11, 2017, 8:52:29 AM
    Author     : dimata005
--%>

<%@page import="com.dimata.dslik.entity.bankgaransi.BankGaransi"%>
<%@page import="com.dimata.dslik.entity.bankgaransi.PstBankGaransi"%>
<%@page import="com.dimata.dslik.entity.kredit.Kredit"%>
<%@page import="com.dimata.dslik.entity.kredit.PstKredit"%>
<%@page import="com.dimata.dslik.entity.masterdata.CabangBank"%>
<%@page import="com.dimata.util.Formater"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.dslik.entity.masterdata.PstCabangBank"%>
<%@page import="com.dimata.qdep.form.FRMQueryString"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../../main/javainit_slik.jsp" %>
<!DOCTYPE html>
<%
    String cabang = FRMQueryString.requestString(request, "cabangpersegment");
    String file = "LAPORAN_NOMINATIF_BANK_GARANSI_LUNAS";
    if(cabang.length() > 0){
        file+="_CABANG/CAPEM_"+cabang;
    }
    
    response.setContentType("application/x-msexcel"); 
    response.setHeader("Content-Disposition","attachment; filename="+file+".xls" ); 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            long periodeId = FRMQueryString.requestLong(request, "periodepersegment");
            String statusreport = FRMQueryString.requestString(request, "statusreport");
            
            String periodeName = "";
            Periode entPeriode = new Periode();
            if(periodeId != 0){
                try{
                    entPeriode = PstPeriode.fetchExc(periodeId);
                    periodeName = entPeriode.getNama();
                }catch(Exception ex){

                }
            }
            
            String whereClauseCabang = "";
            if(cabang.length() > 0){
                whereClauseCabang = PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+"='"+cabang+"'";
            }
            Vector listCabangReport = PstCabangBank.list(0, 0, whereClauseCabang, PstCabangBank.fieldNames[PstCabangBank.FLD_KODE_CABANG]+" ASC");
        %>
        <table border="1" cellpadding="1" cellspacing="1">
            <thead>
                <tr>
                    <td align='left' colspan='8'>
                        PT. BANK PEMBANGUNAN DAERAH BALI<br>
                        JL. RAYA PUPUTAN - NITI MANDALA, RENON, DENPASAR
                    </td>
                    <td></td>
                    <td align='right' colspan='7'>
                        Rpt-ID : <%= userName %><br>
                        Tgl-Rpt : <%= Formater.formatDate(new Date(), "dd/MM/yyyy") %>
                    </td>
                </tr>
                <tr>
                    <td colspan='16' align='center'>
                        DAFTAR NOMINATIF BANK GARANSI LUNAS<br>
                        PER : <%= periodeName %><br>
                        VALUTA : INDONESIAN RUPIAH(IDR)
                    </td>
                </tr>
            <%

                if(listCabangReport.size() > 0){
                    for(int i = 0; i < listCabangReport.size(); i++){
                        CabangBank cabangBank = (CabangBank) listCabangReport.get(i);
            %>
                <tr>
                    <td align='left' colspan='8'>
                        <%= cabangBank.getNamaCabang().toUpperCase()+"("+cabangBank.getKodeCabang()+")" %>
                    </td>
                    <td></td>
                    <td align='right' colspan='7'>
                    </td>
                </tr>
                <tr>
                    <th rowspan="2">NO</th>
                    <th rowspan="2">No<br>Urut</th>
                    <th rowspan="2">NO<br>REK</th>
                    <th rowspan="2">NAMA<br>NASABAH</th>
                    <th rowspan="2">NO. SURAT<br>BG</th>
                    <th rowspan="2">JNS<br>GUNA</th>
                    <th rowspan="2">TUJ<br>BG</th>
                    <th colspan="2">MASA BERLAKU</th>
                    <th rowspan="2">NILAI<br>GARANSI</th>
                    <th rowspan="2">SETORAN<br>JAMINAN</th>
                    <th rowspan="2">%<br>PROV</th>
                    <th rowspan="2">NILAI<br>PROVINSI</th>
                    <th rowspan="2">GOL.<br>JAMIN</th>
                    <th rowspan="2">%<br>DIJAMIN</th>
                    <th rowspan="2">KOL</th>
                </tr>
                <tr>
                    <th>MULAI</th>
                    <th>AKHIR</th>
                </tr>
            </thead>
            <%
            int nourut=0;    
            double totalNominal=0.0;
            double totalNominalPenjamin=0.0;
            //Vector listJenisBankGaransi = PstContentDataJenisGaransi.list(0, 0, "", ""+PstContentDataJenisGaransi.fieldNames[PstContentDataJenisGaransi.FLD_JENIS_GARANSI_OID]);
            Vector listJenisBankGaransi = PstBankGaransi.listDistinct(0, 0, ""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"' "
                                + "AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"'", ""+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" ASC");
            if(listJenisBankGaransi.size() > 0){
                for(int iK = 0; iK < listJenisBankGaransi.size(); iK++){
                    //ContentDataJenisGaransi contentDataJenisGaransi = (ContentDataJenisGaransi) listJenisBankGaransi.get(iK);
                    BankGaransi contentDataJenisGaransi = (BankGaransi) listJenisBankGaransi.get(iK);
            %>
                    <tbody>
                        <tr>
                            <td></td>
                            <td colspan="15" align="left" style='mso-number-format:"@"'><%= ""+String.valueOf(contentDataJenisGaransi.getNoRekening()) %></td>
                        </tr>
                        <%
                        String tglAkadAkhir="";    
                        tglAkadAkhir=" AND ("+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KONDISI]+"='02"
                                    //+ " AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_TGL_JATUH_TEMPO]+" BETWEEN '"+Formater.formatDate(periode.getTglAwal(),"yyyy-MM-dd")+"' AND '"+Formater.formatDate(periode.getTglAkhir(),"yyyy-MM-dd")
                                    +"' )";
                        Vector listBankGaransi = PstBankGaransi.list(0, 0, "SUBSTRING("+PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+",6,2)='"+contentDataJenisGaransi.getNoRekening()+"' "
                                + "AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_PERIODE_ID]+"='"+periodeId+"' "
                                + "AND "+PstBankGaransi.fieldNames[PstBankGaransi.FLD_KODE_KANTOR_CABANG]+"='"+cabangBank.getKodeCabang()+"' "+tglAkadAkhir, PstBankGaransi.fieldNames[PstBankGaransi.FLD_NO_REKENING]+" ASC");
                        if(listBankGaransi.size() > 0){
                            for(int iA = 0; iA < listBankGaransi.size(); iA++){
                                BankGaransi bankGaransi = (BankGaransi) listBankGaransi.get(iA);
                                totalNominal=totalNominal+bankGaransi.getNominal();
                                totalNominalPenjamin=totalNominalPenjamin+bankGaransi.getSetoranJaminan();
                                nourut=nourut+1;
                                %>
                                    <tr>
                                        <td><%= nourut %></td>
                                        <td><%= (iA+1) %></td>
                                        <td><%= PstKredit.formatCredit("###.##.##.#####-#",bankGaransi.getNoRekening()) %></td>
                                        <td><%= bankGaransi.getNamaYgDijamin()%></td>
                                        <td><%= bankGaransi.getNoAkadAwal()%></td>
                                        <td><%= bankGaransi.getKodeKolektibilitas()%></td>
                                        <td><%= bankGaransi.getKodeTujuanGaransi()%></td>
                                        <td><%= Formater.formatDate(bankGaransi.getTglAkadAwal(),"dd/MM/yy") %></td>
                                        <td><%= Formater.formatDate(bankGaransi.getTglJatuhTempo(),"dd/MM/yy") %></td>
                                        <td align="right"><%= Formater.formatNumber(bankGaransi.getNominal(),"#,###.##") %></td>
                                        <td align="right"><%= Formater.formatNumber(bankGaransi.getSetoranJaminan(),"#,###.##") %></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                <%
                            }
                        }
                }
                        %>
                    </tbody>
            <%
            }%>
                <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>TOTAL</td>
                <td align="right"><%= Formater.formatNumber(totalNominal,"#,###.##") %></td>
                <td align="right"><%= Formater.formatNumber(totalNominalPenjamin,"#,###.##") %></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>

           <%
        }
    }
    %>
        </table>
    </body>
</html>
