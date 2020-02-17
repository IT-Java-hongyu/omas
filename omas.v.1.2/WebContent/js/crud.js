//提交的方法名称
var method = "";
var listParam = "";
var saveParam = "";
$(function(){
	//加载表格数据
	$('#grid').datagrid({
		url:name + '/listByPage',
		columns:columns,
		singleSelect: true,
		pagination: true,
		toolbar: [{
			text: '新增',
			iconCls: 'icon-add',
			handler: function(){
				//设置保存按钮提交的方法为add
				method = "add";
				//关闭编辑窗口
				$('#editWindow').dialog('open');
			}
		},'-',{
			text: '导出',
			iconCls: 'icon-excel',
			handler: function(){
				
				//下载文件
				$.download(name + "/export");
			}
		},'-',{
			text: '导入',
			iconCls: 'icon-save',
			handler: function(){
				$('#importDlg').dialog('open');
			}
		}]
	});

	//点击查询按钮
	$('#btnSearch').bind('click',function(){
		//把表单数据转换成json对象
		var formData = $('#searchForm').serializeJSON();
		$('#grid').datagrid('load',formData);
	});

	var h = 200;
	var w = 300;
	if(typeof(height) != "undefined"){
		h = height;
	}
	if(typeof(width) != "undefined"){
		w = width;
	}
	//初始化编辑窗口
	$('#editDLg').dialog({
		title: '编辑',//窗口标题
		width: w,//窗口宽度
		height: h,//窗口高度
		closed: true,//窗口是是否为关闭状态, true：表示关闭
		modal: true//模式窗口
	});

	//点击保存按钮
	$('#btnSave').bind('click',function(){
		//做表单字段验证，当所有字段都有效的时候返回true。该方法使用validatebox(验证框)插件。
		var isValid = $('#editForm').form('validate');
		if(isValid == false){
			return;
		}
		var formData = $('#editForm').serializeJSON();
		$.ajax({
			url: name +"/"+ method + saveParam,
			data: formData,
			dataType: 'json',
			type: 'post',
			success:function(rtn){
				$.messager.alert("提示",rtn.message,'info',function(){
					//成功的话，我们要关闭窗口
					$('#editWindow').dialog('close');
					//刷新表格数据
					$('#grid').datagrid('reload');
				});
			}
		});
	});
	
	//判断是否有导入的功能
	var importForm = document.getElementById('importForm');
	if(importForm){
		
		$('#importDlg').dialog({
			title:'导入数据',
			width:330,
			height:140,
			modal:true,
			closed:true,
			buttons:[
			    {
			    	text: '导入',
			    	handler:function(){
			    		console.log(new FormData($('#importForm')[0]));
			    		$.ajax({
			    			url: name + '/doImport',
			    			data:new FormData($('#importForm')[0]),
			    			type:'post',
			    			processData:false,
			    			contentType:false,
			    			dataType:'json',
			    			success:function(rtn){
			    				$.messager.alert('提示',rtn.message,'info',function(){
			    					if(rtn.success){
			    						$('#importDlg').dialog('close');
			    						$('#importForm').form('clear');
			    						$('#grid').datagrid('reload');
			    					}
			    				});
			    			}
			    		});
			    	}
			    }
			]
		});
	}
});


/**
 * 删除
 */
function dele(uuid){
	$.messager.confirm("确认","确认要删除吗？",function(yes){
		if(yes){
			$.ajax({
				url: name + '/delete?'+objectId+'='+ uuid,
				dataType: 'json',
				type: 'post',
				success:function(rtn){
					$.messager.alert("提示",rtn.message,'info',function(){
						//刷新表格数据
						$('#grid').datagrid('reload');
					});
				}
			});
		}
	});
}

/**
 * 修改
 */
function edit(uuid){
	//弹出窗口
	$('#editWindow').dialog('open');

	//清空表单内容
	$('#editForm').form('clear');

	//设置保存按钮提交的方法为update
	method = "update"+name+"ById";

	//加载数据
	$('#editForm').form('load',name + '/get?id=' + uuid);
}



function timestampToTime(timestamp) {
	   var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
	   Y = date.getFullYear() + '-';
	   M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	   D = date.getDate() + ' ';
	   h = date.getHours() + ':';
	   m = date.getMinutes() + ':';
	   s = date.getSeconds();
	   return Y+M+D;

}


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
    
    return returnAge;//返回周岁年龄
    
}
