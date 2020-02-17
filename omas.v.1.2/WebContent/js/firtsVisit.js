var i=1;
function add(){

	++i;
	var name="zhengzhuang" + i;
	var cd = "cd" + i;
	//console.log(i);
	var html =  "<tr><td><input style='width:99%;height:20px' type='text' id='"+ name +"' "+"/></td><td><input  style='width:99%;height:20px' type='text' id='"+cd+"'/></td></tr>";
	 $("#ex").append(html)
}


function saveBZ(){
	console.log(i);
	var myArray=new Array();
	var myArray1=new Array();
	for(var j=0;j<i;j++){
		var id = "zhengzhuang" + (j+1);
		var id1 = "cd" + (j+1);
		//console.log("#"+ id);
		var CD = $("#"+id1).val();
		//alert(CD);
		var BZ = $("#"+id).val();
		myArray.push(BZ);
		myArray1.push(CD);
	}
	var CDstring = myArray1.join(',');
	//alert(CDstring);
	var BZstring = myArray.join(',');
	var patientId = document.getElementById("patient").value; 
	var BZdetail = $("#textArea").val();
	//console.log(BZdetail);
	//console.log(patientId);
	//alert("病症：" + BZstring + "症状详细："+ BZdetail +"病人："+ patientId);
	
	var formdata = {};
	formdata.symptoms = BZstring;
	formdata.patientId = patientId;
	formdata.symptomDetail = BZdetail;
	formdata.cdString = CDstring;
	
	$.ajax({
		url: 'FirstVisitController/saveRecord',
		data:formdata,
		dataType: 'json',
		type: 'post',
		success:function(rtn){
			
			//成功
			if(rtn.success){
				alert(rtn.message +'    即将前往选择药物');
				location.href="http://localhost:8080/omas.v.1.2/selectMedicient";
				
			}else{
				$.messager.alert('提示',rtn.message,'info');
			}
		}
	});
}