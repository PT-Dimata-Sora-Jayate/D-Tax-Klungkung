<%-- 
    Document   : jenisLc
    Created on : Sep 27, 2016, 11:35:09 AM
    Author     : Dewa
--%>

<%@page import="com.dimata.dslik.form.contentdata.FrmContentDataJenisLc"%>
<%@page import="com.dimata.gui.jsp.ControlCombo"%>
<%@page import="java.util.Vector"%>
<%@page import="com.dimata.util.Command"%>
<!DOCTYPE html>
<%@include file="../../main/javainit_slik.jsp" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>SLIK | Configurasi Databases</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <%@include file="../../template-component/css-component.jsp" %>
    </head>
    <body class="<%= skin%>">
        <input type="hidden" name="command" id="command" value="<%= Command.NONE%>">
        <input type="hidden" name="approot" id="approot" value="<%= approot%>">
        <div class="wrapper">

            <%@include file="../../template-component/header-component.jsp" %>
            <%@include file="../../template-component/sidebar-component.jsp" %>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Mapping
                        <small>Content Data</small>
                    </h1>
                </section>
                <!-- Main content -->
                <section class="content">
                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class='box box-primary'>
                                <div class='box-header'>
                                    Jenis LC
                                </div>
                                <div class='box-footer'>
                                    <button class="btn btn-primary btnaddgeneral" data-oid="0" data-for="showform">
                                        <i class="fa fa-plus"></i> Add Jenis LC
                                    </button>
                                    <!--<button class="btn btn-danger btndeleteareatype" data-for="country">
                                        <i class="fa fa-trash"></i> Delete
                                    </button>-->
                                </div>
                                <div class="box-body">
                                    <div id="countryElement">
                                        <table class="table table-bordered table-striped">
                                            <thead>
                                                <tr>
                                                    <th>No.</th>
                                                    <th>Nama LC</th>
                                                    <th>Kode Core Banking</th>
                                                    <th>Kode OJK</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                                
                            </div>
                        </div><!-- ./col -->
                    </div><!-- /.row -->

                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
            <div class='control-sidebar-bg'></div>
            <%@include file="../../template-component/footer-component.jsp" %>
            <%@include file="../../template-component/plugins-component.jsp" %>
            <script type="text/javascript">
                $(document).ready(function () {
                    //SET ACTIVE MENU
                    var activeMenu = function (parentId, childId) {
                        $(parentId).addClass("active").find(".treeview-menu").slideDown();
                        $(childId).addClass("active");
                    }

                    activeMenu("#configurasi", "#databases");


                    var approot = $("#approot").val();
                    var command = $("#command").val();
                    var dataSend = null;

                    var oid = null;
                    var dataFor = null;

                    //FUNCTION VARIABLE
                    var onDone = null;
                    var onSuccess = null;
                    var callBackDataTables = null;
                    var iCheckBox = null;
                    var addeditgeneral = null;
                    var areaTypeForm = null;
                    var deletegeneral = null;

                    //MODAL SETTING
                    var modalSetting = function (elementId, backdrop, keyboard, show) {
                        $(elementId).modal({
                            backdrop: backdrop,
                            keyboard: keyboard,
                            show: show
                        });
                    };

                    var getDataFunction = function (onDone, onSuccess, approot, command, dataSend, servletName, dataAppendTo, notification, dataType) {
                        /*
                         * getDataFor	: # FOR PROCCESS FILTER
                         * onDone	: # ON DONE FUNCTION,
                         * onSuccess	: # ON ON SUCCESS FUNCTION,
                         * approot	: # APPLICATION ROOT,
                         * dataSend	: # DATA TO SEND TO THE SERVLET,
                         * servletName  : # SERVLET'S NAME,
                         * dataType	: # Data Type (JSON, HTML, TEXT)
                         */
                        $(this).getData({
                            onDone: function (data) {
                                onDone(data);
                            },
                            onSuccess: function (data) {
                                onSuccess(data);
                            },
                            approot: approot,
                            dataSend: dataSend,
                            servletName: servletName,
                            dataAppendTo: dataAppendTo,
                            notification: notification,
                            ajaxDataType: dataType
                        });
                    }

                    //SHOW ADD OR EDIT FORM
                    addeditgeneral = function (elementId) {
                        $(elementId).click(function () {
                            $("#addeditgeneral").modal("show");
                            command = $("#command").val();
                            oid = $(this).data('oid');
                            dataFor = $(this).data('for');
                            $("#generaldatafor").val(dataFor);
                            $("#oid").val(oid);
                            //SET TITLE MODAL
                            if (oid != 0) {
                                if (dataFor == 'showform') {
                                    $(".addeditgeneral-title").html("Edit Jenis LC");
                                }

                            } else {
                                if (dataFor == 'showform') {
                                    $(".addeditgeneral-title").html("Add Jenis LC");
                                }
                            }


                            dataSend = {
                                "FRM_FIELD_DATA_FOR": dataFor,
                                "FRM_FIELD_OID": oid,
                                "command": command
                            }
                            onDone = function (data) {
                                $(".colorpicker").colorpicker();
                            };
                            onSuccess = function (data) {

                            };
                            getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxCdJenisLc", ".addeditgeneral-body", false, "json");
                        });
                    };

                    //DELETE GENERAL
                    deletegeneral = function (tableId, elementId) {

                        $(tableId).on("click", elementId, function () {
                            dataFor = $(this).data("for");
                            command = $(this).data("command");
                            oid = $(this).data("oid");
                            var confirmText = "Are you sure want to delete these data?";
                            if (confirm(confirmText)) {


                                var currentHtml = $(this).html();
                                $(this).html("Deleting...").attr({"disabled": true});
                                dataSend = {
                                    "FRM_FIELD_DATA_FOR": dataFor,
                                    "FRM_FIELD_OID": oid,
                                    "command": command
                                };
                                onSuccess = function (data) {

                                };
                                if (dataFor == "delete") {
                                    onDone = function (data) {
                                        runDataTables();
                                    };
                                }
                                getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxCdJenisLc", null, true, "json");
                                $(this).removeAttr("disabled").html(currentHtml);
                            }

                        });
                    };

                    //FUNCTION FOR DATA TABLES
                    callBackDataTables = function () {
                        addeditgeneral(".btneditgeneral");
                        iCheckBox();

                    }

                    //FORM HANDLER
              chanelForm = function ()       {
                        validateOptions("#<%=FrmContentDataJenisLc.fieldNames[FrmContentDataJenisLc.FRM_FIELD_JENIS_LC_OID]%>", 'text', 'has-error', 1, null);
                    }

                    //VALIDATE FORM
                    function validateOptions(elementId, checkType, classError, minLength, matchValue) {

                        /* OPTIONS
                         * minLength    : INT VALUE,
                         * matchValue   : STRING OR INT VALUE,
                         * classError   : STRING VALUE,
                         * checkType    : STRING VALUE ('text' for default, 'email' for email check
                         */

                        $(elementId).validate({
                            minLength: minLength,
                            matchValue: matchValue,
                            classError: classError,
                            checkType: checkType
                        });
                    }

                    //iCheck
                    iCheckBox = function () {
                        $("input[type='checkbox'], input[type='radio']").iCheck({
                            checkboxClass: 'icheckbox_minimal-blue',
                            radioClass: 'iradio_minimal-blue'
                        });


                    }

                    //DATA TABLES SETTING
                    function dataTablesOptions(elementIdParent, elementId, servletName, dataFor, callBackDataTables) {
                        var datafilter = $("#datafilter").val();
                        var privUpdate = $("#privUpdate").val();
                        $(elementIdParent).find('table').addClass('table-bordered table-striped table-hover').attr({'id': elementId});
                        $("#" + elementId).dataTable({"bDestroy": true,
                            "iDisplayLength": 10,
                            "bProcessing": true,
                            "oLanguage": {
                                "sProcessing": "<div class='progress progress-striped active'><div class='progress-bar progress-bar-primary' style='width: 100%'><b>Please Wait...</b></div></div>"
                            },
                      "bServerSide":       true,
                            "sAjaxSource": "<%= approot%>/" + servletName + "?command=<%= Command.LIST%>&FRM_FIELD_DATA_FILTER=" + datafilter + "&FRM_FIELD_DATA_FOR=" + dataFor + "&privUpdate=" + privUpdate + "&FRM_FLD_APP_ROOT=<%=approot%>",
                            aoColumnDefs: [
                                {
                                    bSortable: false,
                                    aTargets: [-1, -2]
                                }
                            ],
                            "initComplete": function (settings, json) {
                                if (callBackDataTables != null) {
                                    callBackDataTables();
                                }
                            },
                            "fnDrawCallback": function (oSettings) {
                                if (callBackDataTables != null) {
                                    callBackDataTables();
                                }
                            },
                            "fnPageChange": function (oSettings) {

                            }
                        });
                        $(elementIdParent).find("#" + elementId + "_filter").find("input").addClass("form-control");
                        $(elementIdParent).find("#" + elementId + "_length").find("select").addClass("form-control");
                        $("#" + elementId).css("width", "100%");
                    }

                    function runDataTables() {
                        dataTablesOptions("#countryElement", "tableCountryElement", "AjaxCdJenisLc", "list", callBackDataTables);
                    }

                    modalSetting("#addeditgeneral", "static", false, false);
                    addeditgeneral(".btnaddgeneral");
                    runDataTables();
                    deletegeneral("#tableCountryElement", ".button-delete");


                    //FORM SUBMIT
                    $("form#generalform").submit(function () {
                        var currentBtnHtml = $("#btngeneralform").html();
                        $("#btngeneralform").html("Saving...").attr({"disabled": "true"});
                        var generaldatafor = $("#generaldatafor").val();
                        if (generaldatafor == "showform") {
                            chanelForm();
                            onDone = function (data) {
                                runDataTables();
                            };
                        }


                        if ($(this).find(".has-error").length == 0) {
                            onSuccess = function (data) {
                                $("#btngeneralform").removeAttr("disabled").html(currentBtnHtml);
                                $("#addeditgeneral").modal("hide");
                            };

                            dataSend = $(this).serialize();
                            getDataFunction(onDone, onSuccess, approot, command, dataSend, "AjaxCdJenisLc", null, true, "json");
                        } else {
                            $("#btngeneralform").removeAttr("disabled").html(currentBtnHtml);
                        }

                        return false;
                    });
                })
            </script>
            <div id="addeditgeneral" class="modal fade nonprint" tabindex="-1">
                <div class="modal-dialog nonprint">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="addeditgeneral-title"></h4>
                        </div>
                        <form id="generalform">
                            <input type="hidden" name="FRM_FIELD_DATA_FOR" id="generaldatafor">
                            <input type="hidden" name="command" value="<%= Command.SAVE%>">
                            <input type="hidden" name="FRM_FIELD_OID" id="oid">
                            <div class="modal-body ">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box-body addeditgeneral-body">

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" id="btngeneralform"><i class="fa fa-check"></i> Save</button>
                                <button type="button" data-dismiss="modal" class="btn btn-danger"><i class="fa fa-ban"></i> Close</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div><!-- ./wrapper -->
    </body>
</html>
