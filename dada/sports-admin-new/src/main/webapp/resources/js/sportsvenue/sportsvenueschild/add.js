function validateTime() {
	var bookingStartTime = $('#child_venue_form input[name="bookingStartTime"]').val();
   	var bookingEndTime = $('#child_venue_form input[name="bookingEndTime"]').val();
    
    bookingStartTime = new Date("2015-07-27 " + bookingStartTime).getTime();
    bookingEndTime = new Date("2015-07-27 " + bookingEndTime).getTime();
    if(bookingEndTime <= bookingStartTime) {
    	swal("预约截止时间必须在预约开始时间之后！");
    	return false;
    }
    
    return true;
}

$(function(){
   //子场地信息提交
   $("#sumit").on("click",function(){
	   validateTime();
   });
   
});

