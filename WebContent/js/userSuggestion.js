$(document).ready(function() {
	getPatientSuggestion();
	getDoctorSuggestion();
});
function htmlEscapeCharacter(str){
	str = str.replace(/\'/g, "&#39;");
	str = str.replace(/\"/g, "&#34;");
	str = str.replace(/\\/g, "&#92;");
	return str;
}
function returnEscapeCharacter(str){
	str = str.replace(/&#39;/g, "\'");
	str = str.replace(/&#34;/g, '\"');
	str = str.replace(/&#92;/g, '\\');
	return str;
}


function completeP(id, time){
	if(confirm("確認完成？")){
		$.ajax({
			type : "POST",
			url : "UserSuggestionServlet",
			data : {
				state : "completePatientSuggestion",
				patientID: id,
				time: time
			},
			success : function(response) {
				getPatientSuggestion();
			},
			error : function() {
				console.log("錯誤訊息");
			}
		});	
	}
}

function completeD(id, time){
	if(confirm("確認完成？")){
		$.ajax({
			type : "POST",
			url : "UserSuggestionServlet",
			data : {
				state : "completeDoctorSuggestion",
				doctorID: id,
				time: time
			},
			success : function(response) {
				getDoctorSuggestion();
			},
			error : function() {
				console.log("錯誤訊息");
			}
		});		
	}
}

function delP(id, time){
	if(confirm("確定刪除？")){
		$.ajax({
			type : "POST",
			url : "UserSuggestionServlet",
			data : {
				state : "deletePatientSuggestion",
				patientID: id,
				time: time
			},
			success : function(response) {
				getPatientSuggestion();
			},
			error : function() {
				console.log("錯誤訊息");
			}
		});	
	}
}

function delD(id, time){
	if(confirm("確定刪除？")){
		$.ajax({
			type : "POST",
			url : "UserSuggestionServlet",
			data : {
				state : "deleteDoctorSuggestion",
				doctorID: id,
				time: time
			},
			success : function(response) {
				getDoctorSuggestion();
			},
			error : function() {
				console.log("錯誤訊息");
			}
		});	
	}
}

function getPatientSuggestion(){
	$.ajax({
		type : "POST",
		url : "UserSuggestionServlet",
		data : {
			state : "getPatientSuggestion",
		},
		dataType : "json",

		success : function(response) {
			$("#patientTable").empty();
			var temp = "目前無病患端系統回報";
			if(response.length > 0){
				temp = "";
				for(var i = 0; i < response.length; i += 4){
					if(response[i+3] == "0"){
					temp += '<div class="panel panel-danger center-content">'+
								'<div class="panel-heading">病患編號：'+response[i]+'</div>'+
								'<div class="panel-body"><span style="color:#949494">'+response[i+1].substr(0,16)+'</span><br>'+returnEscapeCharacter(response[i+2])+
									'<br><br>'+
									'<div align="right">'+
										'<button type="button" class="btn btn-primary" onclick="completeP(\''+response[i]+'\',\''+response[i+1]+'\')">完成</button>'+
									'</div>'+										
								'</div>'+
							'</div>';	
					}else{
					temp += '<div class="panel panel-info center-content">'+
								'<div class="panel-heading">病患編號：'+response[i]+'</div>'+
								'<div class="panel-body"><span style="color:#949494">'+response[i+1].substr(0,16)+'</span><br>'+returnEscapeCharacter(response[i+2])+
									'<br><br>'+
									'<div align="right">'+
										'<button type="button" class="btn btn-danger" onclick="delP(\''+response[i]+'\',\''+response[i+1]+'\')">刪除</button>'+
									'</div>'+
								'</div>'+
							'</div>';
					}
					temp += '</div></div>';			
				}
			}
			$(patientTable).append(temp);	
		},
		error : function() {
			console.log("錯誤訊息");
		}
	});
}


function getDoctorSuggestion(){
	$.ajax({
		type : "POST",
		url : "UserSuggestionServlet",
		data : {
			state : "getDoctorSuggestion",
		},
		dataType : "json",

		success : function(response) {
			$("#doctorTable").empty();
			var temp ="目前無醫生端系統回報";
			if(response.length > 0){
				temp = "";
				for(var i = 0; i < response.length; i += 4){
					if(response[i+3] == "0"){
					temp += '<div class="panel panel-danger center-content">'+
								'<div class="panel-heading">醫生編號：'+response[i]+'</div>'+
								'<div class="panel-body"><span style="color:#949494">'+response[i+1].substr(0,16)+'</span><br>'+returnEscapeCharacter(response[i+2])+
									'<br><br>'+
									'<div align="right">'+
										'<button type="button" class="btn btn-primary" onclick="completeD(\''+response[i]+'\',\''+response[i+1]+'\')">完成</button>'+
									'</div>'+										
								'</div>'+
							'</div>';	
					}else{
					temp += '<div class="panel panel-info center-content">'+
								'<div class="panel-heading">醫生編號：'+response[i]+'</div>'+
								'<div class="panel-body"><span style="color:#949494">'+response[i+1].substr(0,16)+'</span><br>'+returnEscapeCharacter(response[i+2])+
									'<br><br>'+
									'<div align="right">'+
										'<button type="button" class="btn btn-danger" onclick="delD(\''+response[i]+'\',\''+response[i+1]+'\')">刪除</button>'+
									'</div>'+
								'</div>'+
							'</div>';
					}		
				}
			}
			$(doctorTable).append(temp);
		},
		error : function() {
			console.log("錯誤訊息");
		}
	});
}