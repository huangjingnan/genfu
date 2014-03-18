function viewModelGenfu(urlVal) {
	var iTop = (window.screen.availHeight - 500) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; // 获得窗口的水平位置;

	window
			.open(
					urlVal,
					'winViewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}
function newModelGenfu(urlVal) {
	var iTop = (window.screen.availHeight - 500) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; // 获得窗口的水平位置;

	window
			.open(
					urlVal,
					'winViewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}
function deleteModelGenfu(urlVal) {
	var iTop = (window.screen.availHeight - 500) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; // 获得窗口的水平位置;

	window
			.open(
					urlVal,
					'winViewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');

}
function deleteModelGenfu_bak(urlVal) {
	// alert(urlVal);
	$.ajax({
		url : urlVal,
		// data: {
		// zipcode: 97201
		// },
		type : "POST",
		beforeSend : function() {
			// $("#large").html('');
		},
		success : function(data) {
			// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
		},
		complete : function() {
			//
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});

}
function deleteModelsGenfu(ids, className) {
	// alert(urlVal);
	$.ajax({
		url : '/genfu/genfu-common/' + ids + '?_method=DELETE',
		data : {
			className : className
		},
		type : "POST",
		beforeSend : function() {
			// $("#large").html('');
		},
		success : function(data) {
			// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
		},
		complete : function() {
			//
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});

}

// /////////////////////new///////////////////////
function hiddenSearchDIV() {
	// $("#search_div").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无
	// $("#search_div").slideDown();//窗帘效果展开
	// $("#search_div").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
	// $("#search_div").show();//显示,参数说明同上
	$("#search_div").toggle(1000);// 显示隐藏切换,参数可以无,参数说明同上

}
// /////////////////////new///////////////////////
function hiddenResultDIV() {
	// $("#search_div").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无
	// $("#search_div").slideDown();//窗帘效果展开
	// $("#search_div").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
	// $("#search_div").show();//显示,参数说明同上
	$("#resultSet_div").toggle(1000);// 显示隐藏切换,参数可以无,参数说明同上

}
// /////////////////////new///////////////////////
function isJson(obj) {
	var isjson = typeof (obj) == "object"
			&& Object.prototype.toString.call(obj).toLowerCase() == "[object object]"
			&& !obj.length;
	return isjson;
}