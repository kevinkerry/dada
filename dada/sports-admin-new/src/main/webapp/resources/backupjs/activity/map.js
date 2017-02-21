var windowsArr = [];
		var markers = [];
		var mapObj = new AMap.Map("mapContainer", {
			resizeEnable: true,
	        view: new AMap.View2D({
	        	resizeEnable: true,
	            city: "",//地图中心点
	            zoom:13//地图显示的缩放级别
	        }),
	        keyboardEnable:false
	    });
		AMap.event.addListener(mapObj,'click',getLnglat);		
	    document.getElementById("keyword").onkeyup = keydown;
		//输入提示
		function autoSearch() {
		    var keywords = document.getElementById("keyword").value;
		    var auto;
		    var defaultCity="";
		    var province = document.getElementById("province").value;
		    var city = document.getElementById("city").value;
	    	if(city != 'undifined' && city != null && city != '' && city != '市辖区') {
		    	defaultCity = city;
		    }else if(province != 'undifined' && province != null && province != '') {
		    	defaultCity = province;
		    }
	    	
	     	//加载输入提示插件
	        AMap.service(["AMap.Autocomplete"], function() {
		        var autoOptions = {
		            city: defaultCity //城市，默认全国
		        };
		        auto = new AMap.Autocomplete(autoOptions);
		        //查询成功时返回查询结果
		        if ( keywords.length > 0) {
		            auto.search(keywords, function(status, result){
		            	autocomplete_CallBack(result);
		            });
		        }
		        else {
		            document.getElementById("result1").style.display = "none";
		        }
		    });
		}
		 
		//输出输入提示结果的回调函数
		function autocomplete_CallBack(data) {
		    var resultStr = "";
		    var tipArr = data.tips;
		    if (tipArr&&tipArr.length>0) {                 
		        for (var i = 0; i < tipArr.length; i++) {
		            resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)
		                        + ",this)' onclick='selectResult(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)
		                        + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\"" + "data=" + tipArr[i].adcode + ">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";
		        }
		    }
		    else  {
		        resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";
		    }
		    document.getElementById("result1").curSelect = -1;
		    document.getElementById("result1").tipArr = tipArr;
		    document.getElementById("result1").innerHTML = resultStr;
		    document.getElementById("result1").style.display = "block";
		}
		 
		//输入提示框鼠标滑过时的样式
		function openMarkerTipById(pointid, thiss) {  //根据id打开搜索结果点tip 
		    thiss.style.background = '#CAE1FF';
		}
		 
		//输入提示框鼠标移出时的样式
		function onmouseout_MarkerStyle(pointid, thiss) {  //鼠标移开后点样式恢复 
		    thiss.style.background = "";
		}
		 
		//从输入提示框中选择关键字并查询
		function selectResult(index) {
		    if(index<0){
		        return;
		    }
		    if (navigator.userAgent.indexOf("MSIE") > 0) {
		        document.getElementById("keyword").onpropertychange = null;
		        document.getElementById("keyword").onfocus = focus_callback;
		    }
		    //截取输入提示的关键字部分
		    var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");
			var cityCode = document.getElementById("divid" + (index + 1)).getAttribute('data');
		    document.getElementById("keyword").value = text;
		    
		    document.getElementById("result1").style.display = "none";
		    //根据选择的输入提示关键字查询
		    mapObj.plugin(["AMap.PlaceSearch"], function() {       
		        var msearch = new AMap.PlaceSearch();  //构造地点查询类
		        AMap.event.addListener(msearch, "complete", placeSearch_CallBack); //查询成功时的回调函数
				msearch.setCity(cityCode);
		        msearch.search(text);  //关键字查询查询
		    });
		    
		   geocoder(text);
		   
		 
		
				
		}
		 
		//定位选择输入提示关键字
		function focus_callback() {
		    if (navigator.userAgent.indexOf("MSIE") > 0) {
		        document.getElementById("keyword").onpropertychange = autoSearch;
		   }
		}
		 
		//输出关键字查询结果的回调函数
		function placeSearch_CallBack(data) {
		    //清空地图上的InfoWindow和Marker
		    windowsArr = [];
		    markers     = [];
		    //console.info(data);
		    var pois = data.poiList.pois;
		    if(pois.length > 0) {
		    	var lngY =pois[0].location.getLng();
		    	var latX = pois[0].location.getLat();
		    	document.getElementById("GPS_X").value=latX;
			    document.getElementById("GPS_Y").value=lngY;
		    	mapObj.setCenter(new AMap.LngLat(lngY, latX));
		    }
		    mapObj.clearMap();
		    mapObj.setFitView();
		}
		 
		//鼠标滑过查询结果改变背景样式，根据id打开信息窗体
		function openMarkerTipById1(pointid, thiss) {
		    thiss.style.background = '#CAE1FF';
		    windowsArr[pointid].open(mapObj, markers[pointid]);
		}
		 
		//添加查询结果的marker&infowindow   
		function addmarker(i, d) {
		    var lngY = d.location.getLng();
		    var latX = d.location.getLat();
		    
		    var markerOption = {
		        map:mapObj,
		        icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",
		        position:new AMap.LngLat(lngY, latX)
		    };
		    
		    var mar = new AMap.Marker(markerOption);  
		    markers.push(new AMap.LngLat(lngY, latX));
		 	
		    var infoWindow = new AMap.InfoWindow({
		        content:"<h3><font color=\"#00a6ac\">  " + (i + 1) + ". " + d.name + "</font></h3>" + TipContents(d.type, d.address, d.tel),
		        size:new AMap.Size(300, 0),
		        autoMove:true, 
		        offset:new AMap.Pixel(0,-30)
		    });
		    
		    windowsArr.push(infoWindow);
		    
		    var aa = function (e) {infoWindow.open(mapObj, mar.getPosition());};
		    AMap.event.addListener(mar, "mouseover", aa);
		}
		 
		//infowindow显示内容
		function TipContents(type, address, tel) {  //窗体内容
		    if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {
		        type = "暂无";
		    }
		    if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {
		        address = "暂无";
		    }
		    if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {
		        tel = "暂无";
		    }
		    var str = "  地址：" + address + "<br />  电话：" + tel + " <br />  类型：" + type;
		    return str;
		}
		function keydown(event){
		    var key = (event||window.event).keyCode;
		    var result = document.getElementById("result1");
		    var cur = result.curSelect;
		    if(key===40){//down
		        if(cur + 1 < result.childNodes.length){
		            if(result.childNodes[cur]){
		                result.childNodes[cur].style.background='';
		            }
		            result.curSelect=cur+1;
		            result.childNodes[cur+1].style.background='#CAE1FF';
		            document.getElementById("keyword").value = result.tipArr[cur+1].name;
		        }
		    }else if(key===38){//up
		        if(cur-1>=0){
		            if(result.childNodes[cur]){
		                result.childNodes[cur].style.background='';
		            }
		            result.curSelect=cur-1;
		            result.childNodes[cur-1].style.background='#CAE1FF';
		            document.getElementById("keyword").value = result.tipArr[cur-1].name;
		        }
		    }else if(key === 13){
		        var res = document.getElementById("result1");
				if(res && res['curSelect'] !== -1){
					selectResult(document.getElementById("result1").curSelect);
				}
		    }else{
		        autoSearch();
		    }
		}
		function geocoder(location) {
			
		    var MGeocoder;
		   
		    //加载地理编码插件
		    AMap.service(["AMap.Geocoder"], function() {        
		        MGeocoder = new AMap.Geocoder({ 
		            city:"", //城市，默认：“全国”
		            radius:500 //范围，默认：500
		        });
		        //返回地理编码结果  
		        //地理编码
		        MGeocoder.getLocation(location, function(status, result){
		        	//alert(status);
		        	//console.log(result);
		        	if(status === 'complete' && result.info === 'OK'){
		        		geocoder_CallBack(result);
		        	}
		        });
		    });
		}  
		function geocoder_CallBack(data){
			
		    var lngY="";
		    var latX="";
		    //地理编码结果数组
		    var geocode = new Array();
		    geocode = data.geocodes;  
		    for (var i = 0; i < geocode.length; i++) {
		        //拼接输出html
		        //resultStr += "<span style=\"font-size: 12px;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\">"+"<b>地址</b>："+geocode[i].formattedAddress+""+ "<b>&nbsp;&nbsp;&nbsp;&nbsp;坐标</b>：" + geocode[i].location.getLng() +", "+ geocode[i].location.getLat() +""+ "<b>&nbsp;&nbsp;&nbsp;&nbsp;匹配级别</b>：" + geocode[i].level +"</span>";   
		    	lngY =geocode[i].location.getLng();
		    	latX = geocode[i].location.getLat();
		        addmarker(i, geocode[i]);
		    }  
		    mapObj.setFitView(); 
		    document.getElementById("GPS_X").value=latX;
		    document.getElementById("GPS_Y").value=lngY;
		    //document.getElementById("result").innerHTML = resultStr;  
		   
		}
		
		function getLnglat(e){ 
			document.getElementById('GPS_Y').value = e.lnglat.getLng();  
			document.getElementById('GPS_X').value = e.lnglat.getLat();
			addMarker_location();
		}
		function addMarker_location(){
			mapObj.clearMap();
			var LngLatX = document.getElementById("GPS_X").value; //获取Lng值
			var LngLatY = document.getElementById("GPS_Y").value; //获取Lat值
			var marker = new AMap.Marker({				  
				icon: "http://webapi.amap.com/images/marker_sprite.png",
				position:new AMap.LngLat(LngLatY,LngLatX)
			});
			
			marker.setMap(mapObj);  //在地图上添加点
			mapObj.setFitView(); //调整到合理视野
		}
