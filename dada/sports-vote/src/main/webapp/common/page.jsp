<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page2014sec align-l"> 
<c:if test="${page.currentPage>1}">  
<a href="javascript:goPage(${page.prePage});" class="leftI" ><b></b>上一页</a>
</c:if>		
<c:set var="start" value="1"></c:set>
<c:choose>
<c:when test="${page.totalPages>10}">
<c:choose>
<c:when test="${page.currentPage>6}">
<c:set var="start" value="${page.currentPage-4}"></c:set>
</c:when>
<c:otherwise>
<c:set var="start" value="1"></c:set>
</c:otherwise>
</c:choose>
<c:if test="${page.currentPage+4>page.totalPages}">
<c:set var="start" value="${page.totalPages-9}"></c:set>
</c:if>
</c:when>
<c:otherwise>
<c:set var="start" value="1"></c:set>
</c:otherwise>
</c:choose>
<c:choose>
<c:when test="${page.totalPages<10}">
<c:set var="count" value="${page.totalPages}"></c:set>
</c:when>
<c:otherwise>
<c:set var="count" value="${start+9}"></c:set>
</c:otherwise>
</c:choose>
<c:if test="${count<0}">
<c:set var="count" value="0"></c:set>
</c:if>

<c:forEach var="idx" begin="${start }" end="${count}" step="1">
<c:choose>
<c:when test="${idx==page.currentPage}">
 <span class="num">${idx}</span>
</c:when>
<c:otherwise>
<a href="javascript:goPage(${idx});">${idx}</a>
</c:otherwise>
</c:choose>
</c:forEach>
<c:if test="${page.currentPage<page.totalPages}">
<a href="javascript:goPage(${page.currentPage+1});" class="rightI">下一页<b></b></a>
</c:if>	
   </div>
