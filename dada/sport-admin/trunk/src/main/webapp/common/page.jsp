<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <nav style="float:left;">
  	<c:set var="currentPageSize" value="${page.pageSize}"></c:set>
  	<ul class="pager" style="margin-top: 0px;">
		<li >
		第&nbsp;<input type="text" value="${page.currentPage}" style="width: 20px;text-align: center;"/>&nbsp;/&nbsp;${page.totalPages}&nbsp;页
		&nbsp;每页&nbsp;
		<select id="page_size" onchange="javascript:goPage(${page.currentPage}, this.value);" style="cursor: pointer;">
			<c:choose>
				<c:when test="${page.pageSize eq 5}">
					<option value="5" selected="selected">5</option>
				</c:when>
				<c:otherwise>
					<option value="5">5</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${page.pageSize eq 10}">
					<option value="10" selected="selected">10</option>
				</c:when>
				<c:otherwise>
					<option value="10">10</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${page.pageSize eq 20}">
					<option value="20" selected="selected">20</option>
				</c:when>
				<c:otherwise>
					<option value="20">20</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${page.pageSize eq 50}">
					<option value="50" selected="selected">50</option>
				</c:when>
				<c:otherwise>
					<option value="50">50</option>
				</c:otherwise>
			</c:choose>
		</select>
		&nbsp;条&nbsp;
		共&nbsp;${page.totalCount}&nbsp;条记录
		</li>
  	</ul>
  </nav>
  <nav style="float:right;">
  	  <ul class="pagination" style="margin-top: 0px;">
		<c:if test="${page.currentPage>1}">  
		  <li><a href="javascript:goPage(1, ${currentPageSize});"><span aria-hidden="true">&laquo;</span></a></li>
		</c:if>	
		<c:if test="${page.currentPage<=1}">  
		  <li class="disabled"><a class="disabled" href="javascript:void(0);"><span aria-hidden="true">&laquo;</span></a></li>
		</c:if>	
		<c:if test="${page.currentPage<=1}">  
		  <li class="disabled"><a class="disabled" href="javascript:void(0);"><span aria-hidden="true">&lt;</span></a></li>
		</c:if>	
		<c:if test="${page.currentPage>1}">  
		  <li><a href="javascript:goPage(${page.prePage}, ${currentPageSize});"><span aria-hidden="true">&lt;</span></a></li>
		</c:if>	
		<c:set var="start" value="1"></c:set>
		<c:choose>
			<c:when test="${page.totalPages>5}">
				<c:choose>
					<c:when test="${page.currentPage>3}">
						<c:set var="start" value="${page.currentPage-2}"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="start" value="1"></c:set>
					</c:otherwise>
				</c:choose>
				<c:if test="${page.currentPage+2>page.totalPages}">
					<c:set var="start" value="${page.totalPages-4}"></c:set>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:set var="start" value="1"></c:set>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${page.totalPages<5}">
				<c:set var="count" value="${page.totalPages}"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="count" value="${start+4}"></c:set>
			</c:otherwise>
		</c:choose>
		<c:if test="${count<0}">
			<c:set var="count" value="0"></c:set>
		</c:if>
		<c:forEach var="idx" begin="${start }" end="${count}" step="1">
			<c:choose>
				<c:when test="${idx==page.currentPage}">
					<li class="active"><a href="#">${idx}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:goPage(${idx}, ${currentPageSize});">${idx}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${page.currentPage<page.totalPages}">
			<li><a href="javascript:goPage(${page.currentPage+1}, ${currentPageSize});"><span aria-hidden="true">&gt;</span></a></li>
		</c:if>
		<c:if test="${page.currentPage>=page.totalPages}">
			<li class="disabled"><a href="javascript:void(0);"><span aria-hidden="true">&gt;</span></a></li>
		</c:if>	
		<c:if test="${page.currentPage<page.totalPages}">
			<li><a href="javascript:goPage(${page.totalPages}, ${currentPageSize});"><span aria-hidden="true">&raquo;</span></a></li>
		</c:if>	
		<c:if test="${page.currentPage>=page.totalPages}">
			<li class="disabled"><a href="javascript:void(0);"><span aria-hidden="true">&raquo;</span></a></li>
		</c:if>	
     </ul>
  </nav>
 <!--   <script type="text/javascript">
$(function(){
function goPage(num) {
	        var keyword = $(".search_txt").val();
	        var url="${ctx }/search?keyword="+keyword+"&pageNo="+num;
			location.href = url;
		}
});
</script> -->
 
