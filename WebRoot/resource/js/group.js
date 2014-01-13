//电脑信息增加
function onAddClick(){
	showMasker2('fullbg','dialog','dialog_content','');
	$("#mainDiv").css("display","block");
	$('#mainDiv').dialog({
			title:'计算机组增加',
			buttons:[{
				text:'保存',
				plain: true,
				iconCls:'icon-save',
				handler:function(){
					//组名称
					var groupName = $("#groupBean\\.groupName").val();
					if(null==groupName||groupName==""){
						$.messager.alert('输入错误','输入的组名称不正确，请重新输入!', 'error');
						return;
					}
					$('#mainDiv').dialog('close');
					showMasker2('fullbg','dialog','dialog_content','正在增加，请稍候......');
					var formData = $("#mainForm").serialize();
					$.ajax({
						   type: "post",
						   url: "addGroup.action",
						   data: formData,
						   success: function(msg){
							   closeMasker('fullbg','dialog','dialog_content');
							   var result = eval(msg);
							   if(result[0].code == "1"){
								   $.messager.alert("增加错误", result[0].message, 'error');
							   } else {
								   window.location.href="groupIndex.action";
							   }
						   }	
						});
				}
			},{
				text:'取消',
				plain: true,
				iconCls:'icon-cancel',
				handler:function(){
					$('#mainDiv').dialog('close');
					closeMasker('fullbg','dialog','dialog_content');
				}
			}],
		onClose:function(){
			closeMasker('fullbg','dialog','dialog_content');
		}	
	});
	$(".dialog-button").css("text-align","center");
	$('#mainDiv').dialog('open');
}

//电脑信息编辑
function onEditClick(){
	var row = $('#maintable').datagrid('getSelected');
	if (row){
		showMasker2('fullbg','dialog','dialog_content','');
        $("#groupBean\\.groupName").val(row.groupName)
		$("#groupBean\\.id").val(row.id);//主键
		//显示div
		$("#mainDiv").css("display","block");
		$('#mainDiv').dialog({
				title:'计算机组编辑',
				buttons:[{
					text:'保存',
					plain: true,
					iconCls:'icon-save',
					handler:function(){
                        //验证组名称
                        var groupName = $("#groupBean\\.groupName").val();
                        if(null==groupName||groupName==""){
                            $.messager.alert('输入错误','输入的组名称不正确，请重新输入!', 'error');
                            return;
                        }
						$('#mainDiv').dialog('close');
						showMasker2('fullbg','dialog','dialog_content','正在更新，请稍候......');
						var formData = $("#mainForm").serialize();
						$.ajax({
							   type: "post",
							   url: "addGroup.action?operateType=edit",
							   data: formData,
							   success: function(msg){
								   closeMasker('fullbg','dialog','dialog_content');
								   var result = eval(msg);
								   if(result[0].code == "1"){
									   $.messager.alert("编辑错误", result[0].message, 'error');
								   } else {
									   $.messager.alert("", "编辑成功", 'info');
									   var rowIndex = $('#maintable').datagrid('getRowIndex',row);
									   $('#maintable').datagrid('updateRow',{
											index: rowIndex,
											row: {
                                                groupName: result[0].groupName
											}
										});
								   }
							   }	
							});
					}
				},{
					text:'取消',
					plain: true,
					iconCls:'icon-cancel',
					handler:function(){
						$('#mainDiv').dialog('close');
						closeMasker('fullbg','dialog','dialog_content');
					}
				}],
			onClose:function(){
				closeMasker('fullbg','dialog','dialog_content');
			}	
		});
		$(".dialog-button").css("text-align","center");
		$('#mainDiv').dialog('open');
	} else {
		 $.messager.alert("编辑错误","请选择要编辑的计算机信息！", 'error');
	}
	
}

//删除
function onDeleteClick(){
	 var row = $('#maintable').datagrid('getSelected');
	 if (row){
         $.messager.confirm('计算机信息删除', '确认删除该计算机信息?', function(r){
     		if (r){
     			$("#computerBean\\.cid").val(row.cid);//主键
     			document.mainForm.action= "computerIndex.action?operateType=delete";
     			document.mainForm.submit();
     		}
     	});
     }else{
         $.messager.alert("删除错误","请选择要删除的计算机！！", 'error');
     }
}


//安装软件首页跳转
function onInstallSoftwareClick(){
    var row = $('#maintable').datagrid('getSelected');
    if (row){
        //$("#computerBean\\.cid").val(row.cid);//主键
        //document.mainForm.action= "softwareIndex.action?operateType=installSoftware";
        //document.mainForm.submit();
        window.location.href="softwareIndex.action?computerCid="+row.cid;
    }else{
        $.messager.alert("选择错误","请选择目标计算机！！", 'error');
    }
}

