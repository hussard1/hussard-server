<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
		<title><spring:message code="ttl.bbs.admin.list.title"/></title>
	</head>
	<body>
		<main>
			<div class="content_style">
				<div class="notice_list common_border">
					<div class="list_header common_header ">
						<div class="number_conf"><spring:message code="lbl.bbs.admin.list.number"/></div>
						<div class="titile_conf"><spring:message code="lbl.bbs.admin.list.bbsname"/></div>
						<div class="viewAuth_conf"><spring:message code="lbl.bbs.admin.list.readAuth"/></div>
						<div class="writeAuth_conf"><spring:message code="lbl.bbs.admin.list.writeAuth"/></div>
						<div class="replyYn_conf"><spring:message code="lbl.bbs.admin.list.replyYn"/></div>
						<div class="replyAuth_conf"><spring:message code="lbl.bbs.admin.list.replyWriteAuth"/></div>
						<div class="update_conf"><spring:message code="btn.bbs.admin.list.action"/></div>
					</div>
					<c:choose>
						<c:when test="${not empty configs}">
							<c:forEach var="config" items="${configs}">
					<ul>
						<li>
							<div class="number_conf"><c:out value="${config.bbsId}" /></div>
							<div class="titile_conf"><c:out value="${config.bbsName}" /></div>
							<div class="viewAuth_conf">
								<c:choose>
									<c:when test="${config.readAuth == 0}"><spring:message code="lbl.bbs.admin.list.permitAll"/></c:when>
									<c:when test="${config.readAuth == 1}"><spring:message code="lbl.bbs.admin.list.permitLogin"/></c:when>
									<c:when test="${config.readAuth == 2}"><spring:message code="lbl.bbs.admin.list.permitAdmin"/></c:when>
								</c:choose>
							</div>
							<div class="writeAuth_conf">
								<c:choose>
									<c:when test="${config.writeAuth == 1}"><spring:message code="lbl.bbs.admin.list.permitLogin"/></c:when>
									<c:when test="${config.writeAuth == 2}"><spring:message code="lbl.bbs.admin.list.permitAdmin"/></c:when>
								</c:choose>
							</div>
							<div class="replyYn_conf">
								<c:choose>
									<c:when test="${config.replyYn == 0}">N</c:when>
									<c:when test="${config.replyYn == 1}">Y</c:when>
								</c:choose>
							</div>
							<div class="replyAuth_conf">
								<c:choose>
									<c:when test="${config.replyWriteAuth == 1}"><spring:message code="lbl.bbs.admin.list.permitLogin"/></c:when>
									<c:when test="${config.replyWriteAuth == 2}"><spring:message code="lbl.bbs.admin.list.permitAdmin"/></c:when>
								</c:choose>
							</div>
							<div class="update_conf">
								<button><a href="<c:url value="/bbs/bbs/list.sag">
											<c:param name="bbsid" value="${config.bbsId}"/>
											<c:param name="pagenum" value="1"/>
										</c:url>"><spring:message code="btn.bbs.admin.list.move"/></a></button>
								<button><a href="<c:url value="/bbs/admin/updatebbs.sag">
											<c:param name="bbsid" value="${config.bbsId}"/>
										</c:url>"><spring:message code="btn.bbs.admin.list.update"/></a></button>

							</div>
						</li>
					</ul>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="none_result"><spring:message code="lbl.bbs.admin.list.noContent"/></div>
						</c:otherwise>
					</c:choose>

				</div>

				<div class="bbs_create_wrap">
					<div class="create_btn">
							<div class="create_icon"></div>
							<span><a href="<c:url value='/bbs/admin/savebbs.sag'/>"><spring:message code="btn.bbs.admin.list.generateBbs"/></a></span>
					</div>
				</div>
			</div>
		</main>
	</body>
</html>