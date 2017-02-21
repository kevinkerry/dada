Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "h+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) {
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
}


function strToDate(str) {
	 var tempStrs = str.split(" ");
	 var dateStrs = tempStrs[0].split("-");
	 var year = parseInt(dateStrs[0], 10);
	 var month = parseInt(dateStrs[1], 10) - 1;
	 var day = parseInt(dateStrs[2], 10);
	 var timeStrs = tempStrs[1].split("-");
	 var hour = parseInt(timeStrs [0], 10);
	 var minute = parseInt(timeStrs[1], 10) - 1;
	 var second = parseInt(timeStrs[2], 10);
	 var date = new Date(year, month, day, hour, minute, second);
	 return date;
}

function timeToStr(time){
	return new Date(time).format('yyyy-MM-dd hh:mm:ss');
}

function timeToStrHTML(time){
	return new Date(time).format('yyyy-MM-dd hh:mm');
}


function ages(str) {
	str = new Date(parseInt(str)).format('yyyy-MM-dd');
	var r = str.toString().match(
			/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return 0;
	var d = new Date(r[1], r[3] - 1, r[4]);
	if (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
			&& d.getDate() == r[4]) {
		var Y = new Date().getFullYear();
		return (Y - r[1]);
	}
	return ("输入的日期格式错误！");
}