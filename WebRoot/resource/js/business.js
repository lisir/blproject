//电脑信息增加
function onAddClick(){
    showMasker2('fullbg','dialog','dialog_content','');
    $("#mainDiv").css("display","block");
    $('#mainDiv').dialog({
        title:'商铺增加',
        buttons:[{
            text:'保存',
            plain: true,
            iconCls:'icon-save',
            handler:function(){
                //商铺名称验证
                var shopName = $("#shopBean\\.shopName").val();
                if(!shopName){
                    $.messager.alert('输入错误','请输入商铺名称!', 'error');
                    return;
                }
                //商铺类型验证
                var shopType = $("#shopBean\\.shopType").val();
                if(!shopType){
                    $.messager.alert('输入错误','请输入商铺类型!', 'error');
                    return;
                }
                $("#shopBean\\.shopFloor").val($("#shopFlooradd").combobox('getValue'));
                $('#mainDiv').dialog('close');
                $('body').MaskLayer({'msg':'正在添加，请稍候......'});
                $('body').MaskLayer('show');
                var formData = $("#mainForm").serialize();
                $.ajax({
                    type: "post",
                    url: "addShop.action",
                    data: formData,
                    success: function(msg){
                    	 $('body').MaskLayer('hide');
                        if(!msg.result){
                            $.messager.alert("增加错误", msg.cause, 'error');
                        } else {
                            $.messager.alert("添加成功", "添加成功！", 'message');
                            queryData();
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
        //要编辑的信息（编辑前）
        $("#shopBean\\.shopName").val(row.shopName);
        $("#shopBean\\.shopDesc").val(row.shopDesc);
        $("#shopBean\\.shopType").val(row.shopType);
        $("#shopBean\\.id").val(row.id);//主键
        $("#shopFlooradd").combobox('setValue',row.shopFloor);
        //显示div
        $("#mainDiv").css("display","block");
        $('#mainDiv').dialog({
            title:'计算机信息编辑',
            buttons:[{
                text:'保存',
                plain: true,
                iconCls:'icon-save',
                handler:function(){
                	   //商铺名称验证
                    var shopName = $("#shopBean\\.shopName").val();
                    if(!shopName){
                        $.messager.alert('输入错误','请输入商铺名称!', 'error');
                        return;
                    }
                    //商铺类型验证
                    var shopType = $("#shopBean\\.shopType").val();
                    if(!shopType){
                        $.messager.alert('输入错误','请输入商铺类型!', 'error');
                        return;
                    }
                   $("#shopBean\\.shopFloor").val($("#shopFlooradd").combobox('getValue'));
                    $('#mainDiv').dialog('close');
                    closeMasker('fullbg','dialog','dialog_content');
                    showMasker2('fullbg','dialog','dialog_content','正在更新，请稍候......');
//                    $('body').MaskLayer({'msg':'正在更新，请稍候......'});
//                    $('body').MaskLayer('show');
                    var formData = $("#mainForm").serialize();
                    $.ajax({
                        type: "post",
                        url: "updateShop.action",
                        data: formData,
                        success: function(msg){
                        	  closeMasker('fullbg','dialog','dialog_content');
	                            if(!msg.result){
	                                $.messager.alert("修改错误", msg.cause, 'error');
	                            } else {
	                                $.messager.alert("修改成功", "修改成功！", 'message');
	                                queryData();
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
			        $.post("./deleteShop.action",{"shopBean.id":row.id},function(data){
			            if(!data.result){
			                alert(data.cause);
			            }else{
			            	alert("删除成功!");
			            	  queryData();
			            }
			        });
             }
         });

    }else{
        $.messager.alert("删除错误","请选择要删除的商铺！！", 'error');
    }
}


//安装软件首页跳转
function onInstallSoftwareClick(){
    var row = $('#maintable').datagrid('getSelected');
    if (row){
        //$("#shopBean\\.cid").val(row.cid);//主键
        //document.mainForm.action= "softwareIndex.action?operateType=installSoftware";
        //document.mainForm.submit();
        window.location.href="softwareIndex.action?computerCid="+row.cid;
    }else{
        $.messager.alert("选择错误","请选择目标计算机！！", 'error');
    }
}

