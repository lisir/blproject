// 数字check
function numValueCheck(obj){
	var str = obj.value;
	var newPar=/^[0-9]+$/;
	//var newPar=/^\+?[1-9][0-9]*$/;
	if("" != str){
		if(!newPar.test(str)){
			obj.value = obj.value.substr(0, obj.value.length - 1);
			obj.blur();
			$.messager.alert('输入错误','属性名称只能输入数字!','error');
		}
	}
}

//IP地址验证
function ipCheck(pValue){
	var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	var reg = pValue.match(exp);
	if(reg == null){
		return false;
	} else {
		return true;
	}
}

//端口号验证
function portCheck(pValue){
	var port = 99999;
	var exp = /^([1-9][0-9]{0,4}|0)$/;
	var reg = pValue.match(exp);
	if(reg != null){
		port = parseInt(pValue);
		if(port < 65536){
			return true;
		} else {
			return false;
		}
	}
	return false;
}