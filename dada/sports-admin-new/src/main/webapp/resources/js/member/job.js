jobs = new Object();

jobs['信息技术'] = new Array('互联网', 'IT', '通讯', '电信运营', '网络游戏');

jobs['金融保险'] = new Array('投资', '股票/基金', '保险', '银行', '信托/担保');

jobs['商业服务'] = new Array('咨询', '个体经营', '美容美发', '旅游', '酒店餐饮','休闲娱乐','贸易','汽车','房地产','物业管理','装修/装潢');

jobs['工程制造'] = new Array('建筑', '土木工程', '机械制造', '电子', '生物医药','食品','服装','能源');

jobs['交通运输'] = new Array('航空', '铁路', '航运/船舶', '公共交通', '物流运输');

jobs['文化传媒'] = new Array('媒体出版', '设计', '文化传播', '广告创意', '动漫','公关/会展','摄影');

jobs['娱乐事业'] = new Array('影视', '运动体育', '音乐', '模特');

jobs['公共事业'] = new Array('医疗', '法律', '教育', '政府机关','科研','公益');

jobs['学生'] = new Array('学生');

jobs['无'] = new Array('无');


function set_job(job1, job2,exist){
	
	var array=[];
	if(exist!=null){
		array = exist.split(";");
		if(array[0] !=null || array[0]!=''){
			for (var i = 0; i < job1.length; i++) {
				if(job1[i].value==array[0]){
					job1[i].selected = true;
				}
			}
		}
		if(array[1]!=null && array[1]!=''){
			job2.options[0] = new Option();
			job2.options[0].text = array[1];
			job2.options[0].value = array[1];
			job2.options[0].selected = true;
		}
	}else {
		job2.options[0] = new Option();
		job2.options[0].text ="请选择";
		job2.options[0].value = 0;
		job2.options[0].selected = true;
	}
	
	
	var pv, cv;
	var i, ii;
	pv = job1.value;
	cv = job2.value;
	job2.length = 1;

	if (pv == '0'){
		return;
	 }

	if (typeof (jobs[pv]) == 'undefined'){
		return;
	}

	for (i = 0; i < jobs[pv].length; i++){
		ii = i + 1;
		job2.options[ii] = new Option();
		job2.options[ii].text = jobs[pv][i];
		job2.options[ii].value = jobs[pv][i];
	}
}