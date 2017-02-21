
var myChart;
function initChart(object){
	myChart = echarts.init(document.getElementById('main'));
	// 指定图表的配置项和数据
	    var option = {
	    		title: {
	    	        text: '实时统计'
	    	    },
	   	    tooltip : {
	   	        trigger: 'axis'
	   	    },
	   	    legend: {
	   	        data:['注册数','活动数','聊天条数','聊天个数']
	   	    },
		   	 grid: {
		         left: '3%',
		         right: '4%',
		         bottom: '3%',
		         containLabel: true
		     },
	   	    toolbox: {
	   	        show : true,
	   	        feature : {
	   	            mark : {show: true},
	   	            dataView : {show: true, readOnly: false},
	   	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	   	            restore : {show: true},
	   	            saveAsImage : {show: true}
	   	        }
	   	    },
	   	    xAxis : [
	   	             {
	   	                 type : 'category',
	   	                 boundaryGap : false,
	   	                 data : object.dateRange
	   	             }
	   	         ],
	   	      yAxis: {
	   	               type: 'value'
	   	           },
	   	       series: [
	   	           {
			           name: "注册数",
			           type: "line",
			           stack: '总量',
			           data: object.registerNum
			
			       },
			       {
			           name: "活动数",
			           type: "line",
			           stack: '总量',
			           data: object.topicNum
			
			       },
			       {
			           name: "聊天条数",
			           type: "line",
			           stack: '总量',
			           data: object.chatNum
			
			       },
			       {
			           name: "聊天个数",
			           type: "line",
			           stack: '总量',
			           data: object.chatPeopleNum
			
			       }
	   	       ]
	   	};


	   // 使用刚指定的配置项和数据显示图表。
	   myChart.setOption(option);
	
}

