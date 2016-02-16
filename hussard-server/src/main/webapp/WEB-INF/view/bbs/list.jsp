<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="stylesheet" type="text/css" href="/static/css/bbs/common.css">
		<link rel="stylesheet" type="text/css" href="/static/css/bbs/bbs.css">
		<link rel="stylesheet" type="text/css" href="/static/css/bbs/buttons.css">
		<link rel="stylesheet" type="text/css" href="/static/css/bbs/custom_select_box.css">
		<script src="<c:url value="/static/js/jquery-1.7.1.min.js"/>"></script>
		<title>	<c:out value="${bbsName}" /></title>
	</head>
	<body>
		<main>
			<div class="content_style">
				<div class="h3_wrap">
					<h3><c:out value="${bbsName}"/></h3>
					<div class="input_wrap">
						<form:form method="get" action="/bbs/bbs/list.sag" id="search_form">
							<select name="searchMode" class="select_box input_hp_1">
								<option value="1"><spring:message code="lbl.bbs.list.subject"/></option>
								<option value="2"><spring:message code="lbl.bbs.list.content"/></option>
								<option value="3"><spring:message code="lbl.bbs.list.subjectContent"/></option>
							</select>
						<input class="input_hp_2" id="content" name="searchContent" type="text">
						<a href="#" id="search_button" class="gray_42_button"><spring:message code="btn.bbs.list.searchBtn"/></a>
						<input type="hidden" name="bbsid" value="${bbsId}"/>
						<input type="hidden" name="pagenum" value="1">
						</form:form>
					</div>
				</div>
				<div class="notice_list common_border">
					<div class="list_header common_header ">
						<div class="th_number"><spring:message code="lbl.bbs.list.number"/></div>
						<div class="th_title"><spring:message code="lbl.bbs.list.subject"/></div>
						<div class="th_author"><spring:message code="lbl.bbs.list.writer"/></div>
						<div class="th_date"><spring:message code="lbl.bbs.list.writeDate"/></div>
						<div class="th_count"><spring:message code="lbl.bbs.list.viewCnt"/></div>
					</div>
					<ul>
					<c:choose>
						<c:when test="${not empty contents}">
							<c:forEach var="content" items="${contents}">
								<li>
									<c:choose>
										<c:when test="${content.contentType == 1}">
											<div class="td_notice"><spring:message code="lbl.bbs.list.notice"/></div>
										</c:when>
										<c:when test="${content.contentType == 2}">
											<div class="td_event"><spring:message code="lbl.bbs.list.event"/></div>
										</c:when>
										<c:otherwise>
											<div class="td_number"><c:out value="${content.contentId}" /></div>
										</c:otherwise>
									</c:choose>
									<div class="td_title"><a href="<c:url value="/bbs/bbs/detail.sag">
												<c:param name="bbsid" value="${content.bbsId}"/>
												<c:param name="contentid" value="${content.contentId}"/>
											</c:url>">
											<c:out value="${content.contentSubject}"/>
											<c:if test="${not empty content.replyCnt}">
											[<c:out value="${content.replyCnt}"/>]</c:if></a></div>
									<div class="td_author"><c:out value="${content.regiId}" /></div>
									<div class="td_date">
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
														value="${content.regiDtime}" />
									</div>
									<div class="td_count"><c:out value="${content.contentViewCnt}"/></div>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="none_result"><spring:message code="lbl.bbs.list.noContent"/></div>
						</c:otherwise>
					</c:choose>
					</ul>
				</div>

				<!-- paging area -->
				<div class="page_wrap">
					<div class="page">
					<c:choose>
						<c:when test = "${(not empty searchMode) && (not empty searchContent)}">
							<c:choose>
								<c:when test="${block <= 1}">
									<button class="sel common_bg_page pre_page"><</button>
								</c:when>
								<c:otherwise>
									<button class="sel common_bg_page pre_page">
									<a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="${startpage-1}"/>
											<c:param name="searchMode" value="${searchMode}"/>
											<c:param name="searchContent" value="${searchContent}"/>
											</c:url>"><</a>
									</button>
								</c:otherwise>
							</c:choose>
							<c:forEach  var="i" begin="${startpage}" end="${endpage}" step="1">
								<c:choose>
									<c:when test="${pageNum == i}">
										<button class="sel focus"><c:out value="${i}"/></button>
									</c:when>
									<c:otherwise>
										<button class="sel"><a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="${i}"/>
											<c:param name="searchMode" value="${searchMode}"/>
											<c:param name="searchContent" value="${searchContent}"/>
											</c:url>">
										${i}</a></button>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${block >= totalblock}">
									<button class="sel common_bg_page next_page">></button>
								</c:when>
								<c:otherwise>
									<button class="sel common_bg_page next_page"><a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="${endpage+1}"/>
											<c:param name="searchMode" value="${searchMode}"/>
											<c:param name="searchContent" value="${searchContent}"/>
											</c:url>">></a>
									</button>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${block <= 1}">
									<button class="sel common_bg_page pre_page"><</button>
								</c:when>
								<c:otherwise>
									<button class="sel common_bg_page pre_page">
										<a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="${startpage-1}"/>
											<c:param name="searchMode" value="${searchMode}"/>
											<c:param name="searchContent" value="${searchContent}"/>
											</c:url>"><</a>
									</button>
								</c:otherwise>
							</c:choose>
							<c:forEach  var="i" begin="${startpage}" end="${endpage}" step="1">
								<c:choose>
									<c:when test="${pageNum == i}">
										<button class="sel focus"><c:out value="${i}"/></button>
									</c:when>
									<c:otherwise>
										<button class="sel"><a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="${i}"/>
											<c:param name="searchMode" value="${searchMode}"/>
											<c:param name="searchContent" value="${searchContent}"/>
											</c:url>">
												${i}</a></button>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${block >= totalblock}">
									<button class="sel common_bg_page next_page">></button>
								</c:when>
								<c:otherwise>
									<button class="sel common_bg_page next_page"><a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="${endpage+1}"/>
											<c:param name="searchMode" value="${searchMode}"/>
											<c:param name="searchContent" value="${searchContent}"/>
											</c:url>">></a>
									</button>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					</div>
					<div class="write_content">
						<div class="write_content_icon"></div>
						<span><a href="<c:url value="/bbs/bbs/writeform.sag">
								<c:param name="bbsid" value="${bbsId}"/>
								</c:url>"><spring:message code="btn.bbs.list.writeBtn"/></a></span>
					</div>
				</div>




			</div>
		</main>
		<script>
			$(document).ready(function(){
				$('#search_button').click(function(){
					$('#search_form').submit();
				});
			});
		</script>

	</body>
</html>