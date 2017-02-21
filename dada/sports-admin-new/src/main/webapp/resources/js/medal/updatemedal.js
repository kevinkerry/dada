document.write("<script src='../resources/js/path.js'></script>");

$(function() {

	// 未点亮勋章
	$('#logoupload')
			.fileupload(
					{
						url : url + "upload",
						datatype : 'json',
						autoUpload : true,
						acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
						maxFileSize : 999000,
						disableImageResize : /Android(?!.*Chrome)|Opera/
								.test(window.navigator.userAgent),
						previewMaxWidth : 100,
						previewMaxHeight : 100,
						previewCrop : true
					})
			.on(
					'fileuploaddone',
					function(e, data) {
						var result = data.result;
						var img = "";
						img = image_url + result.results.path[0];
						// alert(img);
						var html = '<div class="imgbox_logo" data-src="'
								+ result.results.path[0]
								+ '" style="width:170px;z-index:1;background-image: url('
								+ img
								+ ');"><img src="'
								+ url
								+ 'resources/images/del.png" width="20px" height="20px;" /></div>';
						$("#logoimage").html(html);
						$('.logo-upload').hide();
						$("#logoimage").show();
					});

	$('#logoimage').on("click", "img", function() {
		$(this).parent(".imgbox_logo").remove();
		$('.btn-upload').show();
	});

	// 已点亮勋章
	$('#logoupload2')
			.fileupload(
					{
						url : url + "upload",
						datatype : 'json',
						autoUpload : true,
						acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
						maxFileSize : 999000,
						disableImageResize : /Android(?!.*Chrome)|Opera/
								.test(window.navigator.userAgent),
						previewMaxWidth : 100,
						previewMaxHeight : 100,
						previewCrop : true
					})
			.on(
					'fileuploaddone',
					function(e, data) {
						var result = data.result;
						var img = "";
						img = image_url + result.results.path[0];
						// alert(img);
						var html = '<div class="imgbox_logo2" data-src="'
								+ result.results.path[0]
								+ '" style="width:170px;z-index:1;background-image: url('
								+ img
								+ ');"><img src="'
								+ url
								+ 'resources/images/del.png" width="20px" height="20px;" /></div>';
						$("#logoimage2").html(html);
						$('.logo-upload2').hide();
						$("#logoimage2").show();
					});

	$('#logoimage2').on("click", "img", function() {
		$(this).parent(".imgbox_logo2").remove();
		$('.btn-upload2').show();
	});

	$("#sumit").on("click", addmedal);

})

function addmedal() {
	var dataSrc = $(".imgbox_logo").attr("data-src");
	if (!isEmpty(dataSrc)) {
		var logo = '<input type="hidden" name="notLightLogo" value="'
				+ $(".imgbox_logo").attr("data-src") + '" />';
		$("#logo_image").html(logo);
	} else {
		swal("请上传未点亮勋章图");
		return false;
	}
	
	var dataSrc2 = $(".imgbox_logo2").attr("data-src");
	if (!isEmpty(dataSrc2)) {
		var logo = '<input type="hidden" name="logo" value="'+$(".imgbox_logo2").attr("data-src") + '" />';
		$("#logo_image2").html(logo);
	} else {
		swal("请上传已点亮勋章图");
		return false;
	}

	
	if ($("#xzmc").val().length == 0) {
		swal("请输入勋章名称");
		return false;
	}

	 
	 
	 var val=$('input:radio[name="xz"]:checked').val();
	 if(val==1){
		 /*  val=$('input:radio[name="rdo"]:checked');
		   if(val.attr('id')!=null){
			   if(val.attr('id')=="r1"){
				   if($("#v1").val().length!=0){
					   $("#category").val($("#v1").val());
					   $("#type").val(1);
				   }else{
					   swal("请输入第一次完成");
					  return false;
				   }
			   }
				if(val.attr('id')=="r2"){
					 if($("#v2").val().length!=0){
						 $("#category").val($("#v2").val());
						 $("#type").val(0);
					   }else{
						   swal("请输入第一次完成");
						   return false;
					   }
				}
				if(val.attr('id')=="r3"){
					 if($("#v3").val().length!=0){
						 $("#category").val($("#v3").val());
						 $("#type").val(1);
					   }else{
						   swal("请输入累计跑步");
						   return false;
					   }
				}
				if(val.attr('id')=="r4"){
					 if($("#v4").val().length!=0){
						 $("#category").val($("#v4").val());
						 $("#type").val(0);
					   }else{
						   swal("请输入累计走路");
						   return false;
					   }
				}
		   }else{
			   swal("请选中一个项目");
			   return false;
		   }*/
		//活动类型
		 $("#category").val(1);
		 $("#type").val(0);
	 }else{
		 //活动类型
		 $("#category").val(2);
		 $("#type").val(0);
	 }
	 
	if ($("#note").val().length == 0) {
			swal("请输入勋章描述");
			return false;
		}
     
	//  return false;
}
