var i=0;
var medicineIdArray=new Array();
var medicinePriceArray=new Array();

var medicineUsageArray=new Array();
//自动执行函数，根据病症自动匹配药物
$(function() { 
	
	aotofindmedicine()
	

});





function saveMedicine() {

	var myArray=new Array();
//	for(j=1;j<=i;j++){
//		var medicineNameId = "medicineNameId" + j;
//		console.log(medicineNameId);
//		var medicineUsageId = "medicineUsageId" + j;
//		var meName =$("#"+medicineNameId).val();
//		alert(meName);
//
//		var meUsage =$("#"+medicineUsageId).val();
//		myArray.push(meName);
//
//		myArray1.push(meUsage);
//	}
//	var meNamestring = myArray1.join(',');
//	//alert(CDstring);
//	var mdUsagestring = myArray.join(',');
//	alert(meNamestring);
	
//	var tb = document.getElementById('syptomsTable');    // table 的 id
//	var rows = tb.rows;                           // 获取表格所有行
//	for(var i = 1; i<rows.length; i++ ){
//	   for(var j = 0; j<rows[i].cells.length-1; j=j+2 ){    // 遍历该行的 td
//	       //alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+rows[i].cells[j].innerHTML+"。");           // 输出每个td的内容
//	       myArray.push(rows[i].cells[j].innerHTML);
//	       myArray1.push(rows[i].cells[j+1].innerHTML);
//	   }
//	}
//	var meNamestring = myArray.join(',');	
//	var mdUsagestring = myArray1.join(';');
	var zengduanResult = $("#zengduanResult").val();
	//alert(zengduanResult);
	for(j=1;j<=i;j++){
		var medicineNumId = "medicineNumId" + j;
		var medicineUsageId = "medicineUsageId" + j;
		var mdNum = document.getElementById(medicineNumId).value;
		var medicineUsage = document.getElementById(medicineUsageId).value;
		medicineUsageArray.push(medicineUsage);

		myArray.push(mdNum);
	}
	var medicineMum = myArray.join(',');   //用量
	var meIdstring = medicineIdArray.join(','); //id
	var mdPriceString = medicinePriceArray.join(','); //价格
	var mdUsageString = medicineUsageArray.join(";");  //用法
	//alert(meIdstring);
	//alert(mdPriceString);
	//alert(mdUsageString);
	var formdata={};
	formdata.medicineIdString=meIdstring;
	formdata.medicineNumString = medicineMum;
	formdata.medicindePriceString = mdPriceString;
	formdata.medicineUsageString = mdUsageString;
	formdata.medicineZengduanResult = zengduanResult;
	$.ajax({
		url: 'FirstVisitController/saveMedicineList',
		data:formdata,
		dataType: 'json',
		type: 'post',
		success:function(rtn){
			
			//成功
			if(rtn.success){
				alert(rtn.message +'    即将前往选择药物');
				location.href="http://localhost:8080/omas.v.1.2/chufang";
				
			}else{
				alert(rtn.message);
			}
		}
	});
}


function addMedicine(medicinePrice , medicineId , medicineName , medicineUsage){
	//alert(medicineName);//syptomsTable
	++i;
	//medicineUsageArray.push(medicineUsage);
	medicinePriceArray.push(medicinePrice);
	medicineIdArray.push(medicineId);
	var medicineNumId = "medicineNumId" + i;
	var medicineUsageId = "medicineUsageId" + i;
	var h = "<tr><td>"+medicineName+"</td><td><input id='"+medicineNumId+"' Style='width:99%;text-align:center'  type='text'/></td><td><input id='"+medicineUsageId+"' Style='width:99%;text-align:center'  type='text' value='"+medicineUsage+"'></input></td><td><input type='buttun' value='删除' class='dlebtu'></input></td></tr>"
	$("#syptomsTable").append(h);
}

function aotofindmedicine(){
	$.ajax({
		url: 'medicine/findMedicineByMedicineSyptoms',
		dataType: 'json',
		type: 'post',
		success:function(rtn){
			
			//成功
			if(rtn.success){ 
				
				var jsons = JSON.parse(rtn.message);
				for(var i=0;i<jsons.length;i++){
					//var html = "<div class='medicine_item f1'><div onclick='addMedicine("+jsons[i].mName + ","+ jsons[i].mUsage + ")' class='medicinebox'><a href='#'><image src='"+jsons[i].medicineImg+"'></a></div><p><a href='#'>"+jsons[i].mName+"</a></p></div>" 
						//$("#medicineList").append(html);            
					var $html = `<div class="medicine_item f1"><div onclick="addMedicine('`+jsons[i].mPrice+`','`+jsons[i].mId+`','`+jsons[i].mName+`' ,'`+jsons[i].mUsage+`')" class="medicinebox"><a href="#"><image src="`+jsons[i].medicineImg+`"></a></div><p><a href="#">"`+jsons[i].mName+`"</a></p></div>`;
					$("#medicineList").append($html);
				}
				
			}else{
				alert(rtn.message);
			}
		}
	});
	
}







function findMedicine() {
	 var medicineName = $("#medicineSearchInput").val();
	 //alert(medicineName);
	 var formdata={};
	 formdata.medicineName = medicineName;
	 $.ajax({
			url: 'medicine/findMedicineByMedicineName',
			data:formdata,
			dataType: 'json',
			type: 'post',
			success:function(rtn){
				
				//成功
				if(rtn.success){ 
					//alert(JSON.parse(rtn.message)[0].mName);
					var jsons = JSON.parse(rtn.message);
					for(var i=0;i<jsons.length;i++){
						var $html = `<div class="medicine_item f1"><div onclick="addMedicine('`+jsons[i].mPrice+`','`+jsons[i].mId+`','`+jsons[i].mName+`' ,'`+jsons[i].mUsage+`')" class="medicinebox"><a href="#"><image src="`+jsons[i].medicineImg+`"></a></div><p><a href="#">"`+jsons[i].mName+`"</a></p></div>`;
						$("#medicineList").append($html);
					}
					
				}else{
					$.messager.alert('提示',rtn.message,'info');
				}
			}
		});
} 
