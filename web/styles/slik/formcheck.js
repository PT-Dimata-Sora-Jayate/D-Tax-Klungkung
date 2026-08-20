$(document).ready(function(){
    /*$("body").on("keypress",".numericonly",function(e){  
        if(e.keyCode == '9' || e.keyCode == '16'){  
            return;  
        }  
        var code;  
        if (e.keyCode) code = e.keyCode;  
        else if (e.which) code = e.which;   
        if(e.which == 46)  
              return false;  
        if (code == 8 || code == 46 || code == 37 || code == 39)  
              return true;  
        if (code < 48 || code > 57)  
              return false;  
        }  
     );  
     
     $('body').on('keypress', ".nospecial", function (event) {
        var regex = new RegExp("^[a-zA-Z0-9]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        var keyCode = event.keyCode;
        if(keyCode == 8 || keyCode == 46 || keyCode == 37 || keyCode == 39){
            return true;
        }else if (!regex.test(key)) {
           event.preventDefault();
           return false;
        }
    });*/
    $('body').on('keypress', "input", function (event) {
        var numb_allowed = $(this).data("number");
        var alpha_allowed = $(this).data("alpha");
        var spec_allowed = $(this).data("special");
        
        var regexString = "";
        
        if(alpha_allowed == true || numb_allowed == "true"){
            regexString+="a-zA-Z";
        }
        if(numb_allowed == true || numb_allowed == "true"){
            regexString+="0-9";
        }
        console.log(numb_allowed+" "+alpha_allowed+" "+spec_allowed);
        regexString+=spec_allowed;
        var regex = new RegExp("^["+regexString+"]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        var keyCode = event.keyCode;
        if(alpha_allowed !== undefined && numb_allowed !== undefined && spec_allowed !== numb_allowed){
            if(keyCode == 8 || keyCode == 46 || keyCode == 37 || keyCode == 39){
                return true;
            }else if (!regex.test(key)) {
               event.preventDefault();
               return false;
            }
        }
        
    });
})


