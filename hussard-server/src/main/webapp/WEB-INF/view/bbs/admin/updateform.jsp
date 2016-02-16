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
    <title><spring:message code="ttl.bbs.admin.form.updateTitle"/></title>
</head>
<body>
<main>
    <div class="content_style">
        <div class="h3_wrap">
            <h3><spring:message code="ttl.bbs.admin.form.updateTitle"/></h3>
        </div>
        <form:form method="post" action="updatebbs.sag" commandName="config">
                <div class="config_list common_border">
                    <ul>
                        <li>
                            <div class="config_list_title"><span ><spring:message code="lbl.bbs.admin.form.bbsname"/></span></div>
                            <form:input path="bbsName" class="title_input"/>
                            <form:errors path="bbsName"/>
                        </li>

                        <li>
                            <div class="config_list_title"><span ><spring:message code="lbl.bbs.admin.form.function"/></span></div>
                            <div class="config_list_dtl"><span><spring:message code="lbl.bbs.admin.form.reply"/></span></div>
                            <form:select path="replyYn" class="select_box_config">
                                <form:option value="0">N</form:option>
                                <form:option value="1">Y</form:option>
                            </form:select>
                            <div class="config_list_dtl"><span ><spring:message code="lbl.bbs.admin.form.perpage"/></span></div>
                            <form:input path="perPage" value = "10" class="pages_input"/>
                            <form:errors path="perPage"/>
                        </li>
                        <li>
                            <div class="config_list_title"><span ><spring:message code="lbl.bbs.admin.form.auth"/></span></div>
                            <div class="config_list_dtl"><span ><spring:message code="lbl.bbs.admin.form.readAuth"/></span></div>
                            <form:select path="readAuth" class="select_box_config">
                                <form:option value="0"><spring:message code="lbl.bbs.admin.form.permitAll"/></form:option>
                                <form:option value="1"><spring:message code="lbl.bbs.admin.form.permitLogin"/></form:option>
                                <form:option value="2"><spring:message code="lbl.bbs.admin.form.permitAdmin"/></form:option>
                            </form:select>
                            <div class="config_list_dtl"><span><spring:message code="lbl.bbs.admin.form.writeAuth"/></span></div>
                            <form:select path="writeAuth" class="select_box_config">
                                <form:option value="1"><spring:message code="lbl.bbs.admin.form.permitLogin"/></form:option>
                                <form:option value="2"><spring:message code="lbl.bbs.admin.form.permitAdmin"/></form:option>
                            </form:select>
                            <div class="config_list_dtl"><span><spring:message code="lbl.bbs.admin.form.replyWriteAuth"/></span></div>
                            <form:select path="replyWriteAuth" class="select_box_config">
                                <form:option value="1"><spring:message code="lbl.bbs.admin.form.permitLogin"/></form:option>
                                <form:option value="2"><spring:message code="lbl.bbs.admin.form.permitAdmin"/></form:option>
                            </form:select>
                        </li>
                    </ul>
                </div>
                <div class="content_regi_btn">
                    <button class="cancel_btn"><a href="/bbs/admin/list.sag"><spring:message code="btn.bbs.admin.form.cancelBtn"/></a></button>
                    <form:button class="update_btn"><spring:message code="btn.bbs.admin.form.confirmBtn"/></form:button>
                </div>
            <form:hidden path="bbsId"/>
        </form:form>
    </div>
</main>
</body>
</html>