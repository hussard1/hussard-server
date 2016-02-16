<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
		<script src="<c:url value="/static/js/bbs/bbs.js"/>"></script>

		<title></title>
	</head>
	<body>
		<main>
			<div class="content_style">
				<div class="h3_wrap">
					<div class="notice_list common_border">
						<div class="detail_header common_header">
							<div class="th_detail_title"><c:out value="${content.contentSubject}"/></div>
							<div class="th_detail_author">${content.regiId}</div>
							<div class="th_detail_date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${content.regiDtime}"/></div>
							<div class="th_detail_count"><spring:message code="lbl.bbs.detail.viewCnt"/>${content.contentViewCnt}</div>
						</div>
						<c:if test="${not empty files}">
							<div class="th_upload">
							<c:forEach var="file" items="${files}">
								<div class="th_upload_item"><a href="<c:url value="/bbs/bbs/filedownload.sag">
									<c:param name="contentid" value="${file.contentId}"/>
									<c:param name="fileid" value="${file.fileId}"/>
									<c:param name="filename" value="${file.fileOriName}"/>
								</c:url>">
									<c:choose>
										<c:when test="${fn:length(file.fileOriName)>20}">
											<c:out value="${fn:substring(file.fileOriName,0,20)}"/>...
										</c:when>
										<c:otherwise><c:out value="${file.fileOriName}"/></c:otherwise>
									</c:choose>
								</a></div>
							</c:forEach>
							</div>
						</c:if>
						<div class="td_detail_content">
							<c:out value="${content.contentDetails}" escapeXml="false" />
						</div>
					</div>
					<c:if test="${not empty reply}">
					<div class="reply_titile"><spring:message code="lbl.bbs.detail.reply"/><span class="reply_number"> (<c:out value="${replyCnt}"/>)</span></div>
					<div class="notice_list common_border">
						<c:forEach var="item" items="${replys}">
						<div class="common_reply_wrap" id="replys">
							<div class="reply_info_wrap">
								<form:form method="post" action="deletereply.sag" commandName="reply">
								<div class="reply_owner_icon"></div><span class="reply_username"><c:out value="${item.regiId}"/> </span>
								<span class="reply_date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${item.regiDtime}"/>
										<form:button class="reply_del_btn"/></span>
										<input type="hidden" name="bbsid" value="${bbsId}"/>
										<form:hidden path="replyId" value="${item.replyId}"/>
										<form:hidden path="contentId" value="${content.contentId}"/>
								</div>
								</form:form>
							<div class="reply_content_wrap">
								<span class="reply_content"><c:out value="${item.replyDetails}"/></span>
							</div>
						</div>
						</c:forEach>
						<div class="common_reply_wrap reply_input_wrap">
							<form:form method="post" action="addreply.sag" commandName="reply" id="reply_add_form">
							<div class="reply_owner_icon"></div><span class="reply_username">김시용님</span>
							<div class="reply_text_info"><span class="inputCnt">0</span>/300</div>
							<div class="reply_text_input">
								<form:textarea path="replyDetails" />
								<div class="write_reply_content">
									<div class="write_check_icon" ></div>
									<span><a href="#" id="reply_add_btn"><spring:message code="btn.bbs.detail.writeBtn"/></a></span>
								</div>
							</div>
							<input type="hidden" name="bbsid" value="${bbsId}"/>
							<form:hidden path="contentId" value="${content.contentId}"/>
							<form:errors path="replyDetails"/>
							</form:form>
						</div>
					</div>
					</c:if>
					<div class="button_wrap">
						<c:if test="${'test' == content.regiId }">
							<button class="button_wrap_update"><a href="<c:url value='/bbs/bbs/updateform.sag'>
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="contentid" value="${content.contentId}"/>
									</c:url>"><spring:message code="btn.bbs.detail.updateBtn"/></a></button>

							<button class="button_wrap_delete"><a href="<c:url value='/bbs/bbs/delete.sag'>
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="contentid" value="${content.contentId}"/>
									</c:url>"><spring:message code="btn.bbs.detail.deleteBtn"/></a></button>
						</c:if>
						<a href="<c:url value='/bbs/bbs/list.sag'>
											<c:param name="bbsid" value="${bbsId}"/>
											<c:param name="pagenum" value="1"/>
									</c:url>"><button class="button_wrap_list gray_42_button"><spring:message code="btn.bbs.detail.listBtn"/></button></a>
					</div>
				</div>
			</div>
		</main>
		<script>
			$(document).ready(function(){
				$('#reply_add_btn').click(function(){
					$('#reply_add_form').submit();
				});
			});
		</script>
	</body>
</html>