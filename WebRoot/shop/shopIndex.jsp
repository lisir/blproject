<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>商铺管理</title>
	<link rel="stylesheet" type="text/css" href="../resource/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../resource/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../resource/js/jquery-easyui/demo.css">
	<script type="text/javascript" src="../resource/js/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="../resource/js/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../resource/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" type="text/css" href="../resource/css/common.css">
    <link rel="stylesheet" type="text/css" href="../resource/css/masklayer.css">
    <script type="text/javascript" src="../resource/js/masker.js"></script>
    <script type="text/javascript" src="../resource/js/common.js"></script>
    <script type="text/javascript" src="../resource/js/business.js"></script>
    <link rel="stylesheet" type="text/css" href="../resource/js/jquery-easyui/themes/default/masklayer.css">
    <script type="text/javascript" src="../resource/js/jquery-easyui/MaskLayer.js"></script>
    
    <script type="text/javascript">
        var sessionId="${jsessionId}";
		$(document).ready(function(){
			//检索数据(business.js)
			queryData();
			
			//分页属性设置
          	<%--var pager = $('#maintable').datagrid('getPager');--%>
          	<%--pager.pagination({--%>
              	<%--total:${totleSize},--%>
              	<%--pageNumber:${page},--%>
              	<%--pageSize:${rows}--%>
          	<%--});--%>
      	})
      	
      	//电脑信息检索
		function queryData(){
		
			var shopName=$("#shopName").val();
			var shopType=$("#shopType").val();
			var shopFloor=$("#shopFloor").combobox('getValue');
			$('#maintable').datagrid({
				pagination: true,
                pageSize:15,
                pageList:[10,15,20,30,40],
		  		url:'shopJsonData.action?date='+new Date(),
		  		queryParams:{
		  			shopName: shopName,
		  			shopType: shopType,
		  			shopFloor: shopFloor
		  		}
		  	});
		}
		
        function formatSatus(val,row){
            if (row.enabled=='1'){
                return "启用";
            } else {
                return "禁用";
            }
        }
        //格式化状态
        function formatSatuscz(val,row){
            var result="";
            if (row.enabled=='1'){
                result="<a href=\"javascript:operatecomputer('"+row.cid+"','禁用','0')\">禁用</a>";
            } else {
                result="<a href=\"javascript:operatecomputer('"+row.cid+"','启用','1')\">启用</a>";
            }
            return result;
        }
        //启动软件
        function operatecomputer(computerId,message,status){
            if(confirm("确定要"+message+"吗")){
                $('body').MaskLayer({'msg':'正在'+message+'请稍后。。。'});
                $('body').MaskLayer('show');
                $.post("./operateEnabled.action${jsessionId}",{"computerId":computerId,"enabled":status},function(data){
                    if (!data.result)
                        alert(data.cause);
                    else
                        alert(message+"成功!");
                    $('body').MaskLayer('hide');
                    queryData();
                })
            }
        }
    </script>
</head>
<body>
	<!-- 操作区域 -->
	<div id="tb" style="height:auto;padding-bottom: 10px;">
		<div style="width: 30%;float: left;">
			<a href="javascript:onAddClick();" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:onEditClick();" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
			<a href="javascript:onDeleteClick();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div style="width: 60%;float: right;text-align: right;margin-right: 10px;">
			商铺名称: <input id="shopName" name="shopName" type="text" value="${shopName}" style="width:100px">  	
			商铺种类: <input id="shopType" type="text" name="shopType" value="${shopType}" style="width:100px">
			商铺层数: <select id="shopFloor" class="easyui-combobox">
	               	<option value="一层">一层</option>
					<option value="二层">二层</option>
					<option value="三层">三层</option>
			</select>
			<a href="javascript:void(0);" onclick="queryData();" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a>
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<!-- 数据显示区域 -->
	<table id="maintable" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,fitColumns:true,fit:true,pagination:true,toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',hidden:true">ID主键</th>
				<th data-options="field:'shopName',width:100">商铺名称</th>
				<th data-options="field:'shopDesc',width:40">商铺简介</th>
                <th data-options="field:'shopType',width:80">商铺种类</th>
                <th data-options="field:'shopFloor',width:180">商铺楼层</th>
                <th data-options="field:'shopCreatedate',width:80">商铺创建日期</th>
               <!--  <th data-options="field:'rcon1',width:60,formatter:formatSatus">状态</th>
                <th data-options="field:'enabled',width:60,formatter:formatSatuscz">操作</th> -->
                <%--<th data-options="field:'remoteProtocol',width:80">协议类型</th>--%>
			</tr>
		</thead>
	</table>
	
	<!-- 增加、修改区域 -->
	<div id="mainDiv" style="padding:10px;width:600px;height:200px;display:none">
		<form id="mainForm" name="mainForm" method="post" enctype="multipart/form-data">
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="mainArea" id="mtabtab">
				<tr>
					<th nowrap="nowrap">商铺名称</th>
					<td align="left" >
						<input type="text" name="shopBean.shopName" id="shopBean.shopName" class="inputStyle" >
	                       <input type="hidden" name="shopBean.id" id="shopBean.id" />
	                       <font color="#FF0000">*</font>
					</td>
					<th nowrap="nowrap">商铺描述</th>
					<td align="left">
						<input type="text" name="shopBean.shopDesc" id="shopBean.shopDesc" class="inputStyle" />
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">商铺类型</th>
					<td align="left">
						<input type="text" name="shopBean.shopType" id="shopBean.shopType" class="inputStyle" >
					</td>
					<th nowrap="nowrap">商铺楼层</th>
					<td align="left"  >
					<select id="shopFlooradd" class="easyui-combobox">
					<option value="一层">一层</option>
					<option value="二层">二层</option>
					<option value="三层">三层</option>
					</select>
						<input type="hidden" name="shopBean.shopFloor" id="shopBean.shopFloor" class="inputStyle" >
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap">商铺图片</th>
					<td align="left" colspan="3">
					<input type="file" name="shopFile">
						<!-- <input type="text" name="shopBean.domainName" id="shopBean.domainName" class="inputStyle" > -->
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 遮罩层  -->
	<div id="fullbg" class="masklayer"></div>
	<div id="dialog" class="masklayer-msg">
		<div id="dialog_content"></div>
	</div>
    
</body>
</html>