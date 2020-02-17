var birthday="";
$(function() {
	
			$.ajax({
				url: 'FirstVisitController/CreateChufang',
				//data:formdata,
				dataType: 'json',
				type: 'post',
				success:function(rtn){
					var jsons = JSON.parse(rtn.message);
					//成功
					var sexInt = jsons.patientSex;
					var sex =" ";
					if(sexInt=='1') sex="男";
					else sex="女";
					
					birthday = jsons.patientBirthday;
					var date = jsons.date.split(" ")[0];  //日期格式化yy-MM-dd
					  
					
					//*****************************由生日计算年龄********************************
					
					
					
					var returnAge;
				    var strBirthdayArr=birthday.split("-");
				    var birthYear = strBirthdayArr[0];
				    var birthMonth = strBirthdayArr[1];
				    var birthDay = strBirthdayArr[2];
				    
				    d = new Date();
				    var nowYear = d.getFullYear();
				    var nowMonth = d.getMonth() + 1;
				    var nowDay = d.getDate();
				    
				    if(nowYear == birthYear){
				        returnAge = 0;//同年 则为0岁
				    }
				    else{
				        var ageDiff = nowYear - birthYear ; //年之差
				        if(ageDiff > 0){
				            if(nowMonth == birthMonth) {
				                var dayDiff = nowDay - birthDay;//日之差
				                if(dayDiff < 0)
				                {
				                    returnAge = ageDiff - 1;
				                }
				                else
				                {
				                    returnAge = ageDiff ;
				                }
				            }
				            else
				            {
				                var monthDiff = nowMonth - birthMonth;//月之差
				                if(monthDiff < 0)
				                {
				                    returnAge = ageDiff - 1;
				                }
				                else
				                {
				                    returnAge = ageDiff ;
				                }
				            }
				        }
				        else
				        {
				            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
				        }
				    }
				    
				    //*******************************************************
					
					
					
					console.log(returnAge);
					
					
					var medicineList ="";
					
					for(var i=0;i<jsons.chufanMedicineList.length;i++){
						medicineList = medicineList + `<tr><td>`+jsons.chufanMedicineList[i].medicineName+`</td><td>`+jsons.chufanMedicineList[i].medicineNum+`</td><td>`+jsons.chufanMedicineList[i].medicineUsage+`</td></tr>
												`;
					}
					
					var $html = `<!--startprint1--><div class="top_box">
									<div class="menzhen">
										<p class="wenzi fl">门诊：</P>
										<p class="riqi fr">日期：`+date+`</p>
									</div>
									
										</div>
										<div class="big_box">
									<form>
										<table border="0" cellspacing="0" cellpadding="0" class="paperbox clearfix">
											<tr><td class="name">姓名：`+jsons.patientname+`</td><td class="other1">姓别：`+sex+`</td><td class="other1">年龄：`+returnAge+`</td><td class="other">费别：自 </td></tr>
											<tr><td class="name1">临床诊断：`+jsons.zengduanResult+`<td class="other"></td></td ><td class="other1">科别：呼吸内科</td><td class="other">床号： </td></tr>
										</table>
									</form>
									<div class="medicine_box">
										<span>Rp：</span>
										<form>
											<table border="0" cellspacing="0" cellpadding="0" class="medicine_item">` +medicineList +`
											</table>
										</form>
										</div>
										<div class="bto_item">
											<form>
												<table border="0" cellspacing="0" cellpadding="0" class="bto_list">
												<tr><td>地点：`+jsons.pharmacy+`</td><td></td><td></td><td></td></tr>
												<tr><td>医师：李医生</td><td>调配：0</td><td>复核：0</td><td></td><tr>
												<tr class="last_box"><td>药品费：`+jsons.fee+`</td><td>号诊费：0</td><td>治疗费：0</td><td>合计：￥`+jsons.fee+`</td><tr>
												</table>
											</form>
										</div>
									</div>
										</div>
									
										<div class="bto_box">
										<div class="print_box">
											<input type="buttun" value="打印"  onclick="preview(1)" class="print"></input>
											</div>
										</div><!--endprint1-->`;
					
					$("#body").append($html);
				}
			});
		});


function jsGetAge(strBirthday){       
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];
    
    d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    
    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
            else
            {
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
        }
        else
        {
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    }
