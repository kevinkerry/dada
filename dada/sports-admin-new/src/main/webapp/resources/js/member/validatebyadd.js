function validate(){
	
	if($('#memberAlias').val()==null || $('#memberAlias').val()==''){
		swal("请输入昵称");
		return false;
	}
	
	if($('#memberLogo2').val()==null){
		swal("请选择头像");
		return false;
	 }
	
	if($('#memberAlias').length>=20){
		swal("昵称已超过限制,限制字数20");
		return false;
	}
	
	
	if($('#mobile').val()==null || $('#mobile').val()==''){
		swal("请输入手机号码");
		return false;
	}
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test($('#mobile').val())){
		swal("手机号码必须是数字");
		return false;
	}
	
	if($('#mobile').val().length < 11){
		swal("手机号码必须是11位");
		return false;
	}
	
	
	
	if($('#pwd').val()==null || $('#pwd').val()==''){
		swal("请输入密码");
		return false;
	}
	
	if($('#job').val()==0){
		swal("请选择职业");
		return false;
	}
	
	if($('#school').val()==null|| $('#school').val().length<3){
		swal("请输入毕业院校");
		return false;
	}
	
	if($('#to_cn').val()==0){
		swal("请选择省份");
		return false;
	}
	
	
	if($('#GPS_X').val()==''||$('#GPS_Y').val()==''){
		swal("请在地图上选择位置");
		return false;
	}
	
	if($('#keyword').val()==null){
		swal("请输入地点");
		return false;
	}
	
	
	if($('#job2').val()!=0){
		$('#occupation').val($('#job').val()+";"+$('#job2').val());
	}else{
		$('#occupation').val($('#job').val());
	}
	
	if($('#city').val()!=0){
		$('#hometown').val($('#to_cn').val()+";"+$('#city').val());
	}else {
		$('#hometown').val($('#to_cn').val());
	}
	
	getCategorys();
	if(categorys.length<3){
		swal('至少选择三种喜爱类型!');
		return false;
	}
	if(categorys.length>3){
		swal('最多选择三种喜爱类型!');
		return false;
	}
	$('#sportCategory01').val(categorys[0]);
	$('#sportCategory02').val(categorys[1]);
	$('#sportCategory03').val(categorys[2]);
	$('#sportCategory04').val(categorys[3]);
	$('#sportCategory05').val(categorys[4]);
	categorys = null;
	
	$('#memberLogo').val($('#memberLogo2').val())
	$('#maritalStatus').val($('#select_status').val());
	$('#sex').val($('#select_sex').val());
	
	//组装图片
		var html="";
		var i=0;
	$(".imgbox").each(function(index){
			html+='<input type="hidden" name="memberImages['+i+'].imgUrl" value="'+$(this).attr("data-src")+'" />';
			i++;
		});
     $("#img_list").html(html);
	
		return true;
}