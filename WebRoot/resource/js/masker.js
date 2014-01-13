/** 遮罩效果实现
 *  2011-12-16
 *  
 *****************************************/
	//显示灰色JS遮罩层
	function showMasker(fullbg, ct,content){
		var bH=document.body.clientHeight - 2;
		var bW=document.body.clientWidth - 2;
		//alert(bH+"::"+bW);
		var objWH=getObjWh(ct);
		$("#"+fullbg).css({width:bW,height:bH,display:"block"});
		var tbT=objWH.split("|")[0]+"px";
		var tbL=objWH.split("|")[1]+"px";
		$("#"+ct).css({top:tbT,left:tbL,display:"block"});
		$("#"+content).html("<div style='text-align:center'>正在上传，请稍后...</div>");
		$(window).scroll(function(){resetBg(fullbg, ct,content)});
		$(window).resize(function(){resetBg(fullbg, ct,content)});
	}
	
	//显示灰色JS遮罩层
	function showMasker2(fullbg, ct,content, info){
		var bH=document.body.clientHeight - 2;
		var bW=document.body.clientWidth - 2;
		//alert(bH+"::"+bW);
		var objWH=getObjWh(ct);
		$("#"+fullbg).css({width:bW,height:bH,display:"block"});
		var tbT=objWH.split("|")[0]+"px";
		var tbL=objWH.split("|")[1]+"px";
		if("" == info || null == info){
			$("#"+ct).css({display:"none"});
		} else {
			$("#"+ct).css({top:tbT,left:tbL,display:"block"});
			var tempHtml = "<div style='text-align:center'>"+info+"</div>";
			$("#"+content).html(tempHtml);
		}
		$(window).scroll(function(){resetBg(fullbg, ct,content)});
		$(window).resize(function(){resetBg(fullbg, ct,content)});
	}
	
	function getObjWh(obj){
		var st=document.documentElement.scrollTop;//滚动条距顶部的距离
		var sl=document.documentElement.scrollLeft;//滚动条距左边的距离
		var ch=document.body.clientHeight - 258;//屏幕的高度
		var cw=document.body.clientWidth - 113;//屏幕的宽度
		var objH=$("#"+obj).height();//浮动对象的高度
		var objW=$("#"+obj).width();//浮动对象的宽度
		var objT=Number(st)+(Number(ch)-Number(objH))/2;
		//alert("st"+st+":"+"sl"+":"+sl+"ch"+":"+ch+"cw"+":"+cw+"objH"+":"+objH+"objW"+":"+objW);
		var objL=Number(sl)+(Number(cw)-Number(objW))/2;
		return objT+"|"+objL;
	}
	function resetBg(fullbg, ct,content){
		var fullbgSta=$("#"+fullbg).css("display");
		if(fullbgSta=="block"){
			var bH2=document.documentElement.clientHeight;
			var bW2=document.documentElement.clientWidth;
			$("#"+fullbg).css({width:bW2,height:bH2});
			var objV=getObjWh(ct);
			var tbT=objV.split("|")[0]+"px";
			var tbL=objV.split("|")[1]+"px";
			$("#"+ct).css({top:tbT,left:tbL});
		}
	}
	
	//关闭灰色JS遮罩层和操作窗口
	function closeMasker(fullbg, ct,content){
		$("#"+fullbg).css("display","none");
		$("#"+ct).css("display","none");
	}