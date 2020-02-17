$(function(){
	$('#tree').tree({
		animate:true,
		checkbox:true
	});

	$('#grid').datagrid({
		url: 'getUserList',
		columns:[[
			{field:'userid',title:'编号',width:100},
			{field:'username',title:'名称',width:100},

		]],
		singleSelect:true,
		onClickRow:function(rowIndex, rowData){
			$('#tree').tree({
				url: 'getUserAndRoleById?id=' + rowData.userid,
				animate:true,
				checkbox:true
			});
		}
	});
	
	$('#btnSave').bind('click',function(){
		var nodes = $('#tree').tree('getChecked');
		var ids = new Array();
		$.each(nodes,function(i, node){
			ids.push(node.id);
		});
		//[1,2,3,4] => "1,2,3,4"
		var checkedStr = ids.join(',');//把数组里的每个元都拼接上逗号
		//构建提交数据
		var formdata = {};
		//id赋值
		formdata.id= $('#grid').datagrid('getSelected').userid;
		//选中的菜单ID
		formdata.checkedStr=checkedStr;
		//alert(JSON.stringify(formdata));
		$.ajax({
			url: 'updateUserRoles',
			data:formdata,
			type:'post',
			dataType: 'json',
			success:function(rtn){
				$.messager.alert('提示',rtn.message,'info');
				
			}
		});
	});
});