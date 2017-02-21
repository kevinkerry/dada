
function ajaxPage(ajaxPageSize,ajaxCurrentPage,ajaxTotalPages,ajaxTotalCount){
	
	var currentPageSize =ajaxPageSize;
	var currentPage =ajaxCurrentPage;
	var totalPages = ajaxTotalPages;
	var totalCount = ajaxTotalCount;
	var pageSize =ajaxPageSize;
	
 	var stringbuilder = [];
	
	var start = 1;
	var count = 0;
	
	if(totalPages>5){
		if(currentPage>3){
			start = currentPage-2;
		}else {
			start = 1;
		}
		if((currentPage+2)>totalPages){
			start = totalPages-4;
		}
	}else {
		start = 1;
	}
	
	if(totalPages<5){
		count = totalPages;
	}else {
		count = start+4;
	}
	
	if(count<0){
		count = 0;
	}
	
	
	for (var x = start; x < count; x++) {
		if(x==currentPage){
			stringbuilder.push("<li class='active'><a href='#'>"+x+"</a></li>");
		}else {
			stringbuilder.push("<li><a href='javascript:ajaxGoPage("+x+", "+currentPageSize+");'>"+x+"</a></li>");
		}
	}
	
	var pages = "<nav style='float:left;'>\n" +
	"  	<ul class='pager' style='margin-top: 0px;'>\n" +
	"		<li >\n" +
	"		第&nbsp;<input type='text'  onchange='javascript:ajaxGoPageSkip(this.value);' value='"+currentPage+"' style='width: 20px;text-align: center;'/>&nbsp;/&nbsp;"+totalPages+"&nbsp;页\n" +
	"		&nbsp;每页&nbsp;\n7" +
	"		&nbsp;条&nbsp;\n" +
	"		共&nbsp;"+totalCount+"&nbsp;条记录\n" +
	"		</li>\n" +
	"  	</ul>\n" +
	"  </nav>\n" +
	"  <nav style='float:right;'>\n" +
	"  	  <ul class='pagination' style='margin-top: 0px;'>\n" +
			(currentPage>1?"<li><a href='javascript:ajaxGoPage(1, "+currentPageSize+");'><span aria-hidden='true'>&laquo;</span></a></li>\n":"")+
			(currentPage<=1?"<li class='disabled'><a class='disabled' href='javascript:void(0);'><span aria-hidden='true'>&laquo;</span></a></li>\n":"")+
			(currentPage<=1?"<li class='disabled'><a class='disabled' href='javascript:void(0);'><span aria-hidden='true'>&lt;</span></a></li>\n":"")+
			(currentPage>1?"<li><a href='javascript:ajaxGoPage("+(currentPage-1)+", "+currentPageSize+");'><span aria-hidden='true'>&lt;</span></a></li>\n":"")+
			stringbuilder.join("") +
			(currentPage<totalPages?"<li><a href='javascript:ajaxGoPage("+(currentPage+1)+", "+currentPageSize+");'><span aria-hidden='true'>&gt;</span></a></li>\n":"")+
			(currentPage>=totalPages?"<li class='disabled'><a href='javascript:void(0);'><span aria-hidden='true'>&gt;</span></a></li>\n":"")+
			(currentPage<totalPages?"<li><a href='javascript:ajaxGoPage("+totalPages+", "+currentPageSize+");'><span aria-hidden='true'>&raquo;</span></a></li>\n":"")+
			(currentPage>=totalPages?"<li class='disabled'><a href='javascript:void(0);'><span aria-hidden='true'>&raquo;</span></a></li>\n":"")+
	"     </ul>\n" +
	"  </nav>"
	
	return pages;
}

