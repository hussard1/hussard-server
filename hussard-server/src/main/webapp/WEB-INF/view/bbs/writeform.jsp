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
		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
		<script src="<c:url value="/static/js/jquery-1.7.1.min.js"/>"></script>
		<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
		<script src="/static/lib/ckeditor/ckeditor.js"></script>

	</head>
		<body>
			<main>
				<form:form method="post" action="writeform" commandName="content" enctype="multipart/form-data">
				<div class="content_wrap">
					<div class="content content_style">
						<div class="date_search strong_box">			
							<div class="division_input">
								<span><spring:message code="lbl.bbs.form.contentType"/></span>
									<form:select path="contentType" cssClass="select_box input_se">
										<form:option value="0"><spring:message code="lbl.bbs.form.contentType.select"/></form:option>
										<form:option value="1"><spring:message code="lbl.bbs.form.contentType.notice"/></form:option>
										<form:option value="2"><spring:message code="lbl.bbs.form.contentType.event"/></form:option>
									</form:select>
								<form:input id="datePicker1" type="text" path="contentStaDtime" /> ~
								<form:input id="datePicker2" type="text" path="contentEndDtime"/>
							</div>
							<div class="popup_input">
								<span class="poppup_setting_text"><spring:message code="lbl.bbs.form.popup"/></span>
								<form:checkbox path="contentPopupYn" value="1"/>
								<form:hidden path="contentPopupYn" value="0"/>
								<span class="checkbox_desc"><spring:message code="lbl.bbs.form.popupUse"/></span>
								<form:input id="datePicker3" type="text" path="contentPopupStaDtime" /> ~
								<form:input id="datePicker4" type="text" path="contentPopupEndDtime" />
							</div>
						</div>
						<div class="content_detail_wrap">
							<div class="content_detail_wrap_title">
								<span><spring:message code="lbl.bbs.form.subject"/></span>
								<form:input path="contentSubject" /><form:errors path="contentSubject" />
							</div>
							<div class="content_detail_wrap_editor">
								<form:textarea id="ckeditor" path="contentDetails" /><form:errors path="contentDetails" />
							</div>
							<div class="content_detail_wrap_attach">
								<div class="attach_1">
									<span class="attach_file"><spring:message code="lbl.bbs.form.fileUpload1"/></span><span class="limit_10m">(<spring:message code="lbl.bbs.form.fileMaxSize"/> : 10M)</span>
									<form:input path="fileUpload" type="file" />
								</div>
								<div class="attach_2">
									<span class="attach_file"><spring:message code="lbl.bbs.form.fileUpload2"/></span><span class="limit_10m">(<spring:message code="lbl.bbs.form.fileMaxSize"/>: 10M)</span>
									<form:input path="fileUpload" type="file" />
								</div>
								<form:errors path="fileUpload"/>
							</div>
						</div>
						<div class="content_regi_btn">
							<button class="cancel_btn"><a href="<c:url value='/bbs/bbs/list'>
											<c:param name="bbsid" value="${config.id}"/>
											<c:param name="pagenum" value="1"/>
									</c:url>"><spring:message code="btn.bbs.form.cancelBtn"/></a></button>
							<form:button class="regi_btn"><spring:message code="btn.bbs.form.writeBtn"/></form:button>
						</div>
					</div>
				</div>
			</main>
			<form:hidden path="bbsId"/>
			</form:form>

			<script>
				$(document).ready(function(){
					$( "#datePicker1" ).datepicker({
						changeMonth: true,
						changeYear: true,
						dateFormat: "yy-mm-dd",
						dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
						monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
					});
					$( "#datePicker2" ).datepicker({
						changeMonth: true,
						changeYear: true,
						dateFormat: "yy-mm-dd",
						dayNamesMin: ['일', '월', '화', '수', '목', '금', '토' ],
						monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
					});
					$( "#datePicker3" ).datepicker({
						changeMonth: true,
						changeYear: true,
						dateFormat: "yy-mm-dd",
						dayNamesMin: ['일', '월', '화', '수', '목', '금', '토' ],
						monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
					});
					$( "#datePicker4" ).datepicker({
						changeMonth: true,
						changeYear: true,
						dateFormat: "yy-mm-dd",
						dayNamesMin: ['일', '월', '화', '수', '목', '금', '토' ],
						monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
					});
				});
			</script>
		</body>
	<script>
		$(function(){

			CKEDITOR.replace( 'ckeditor', {//해당 이름으로 된 textarea에 에디터를 적용
				width:'100%',
				filebrowserImageUploadUrl: '/bbs/bbs/uploadimage' //여기 경로로 파일을 전달하여 업로드 시킨다.
			});


			CKEDITOR.on('dialogDefinition', function( ev ){
				var dialogName = ev.data.name;
				var dialogDefinition = ev.data.definition;

				switch (dialogName) {
					case 'image': //Image Properties dialog
						//dialogDefinition.removeContents('info');
						dialogDefinition.removeContents('Link');
						dialogDefinition.removeContents('advanced');
						break;
				}
			});

		});

		//window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}', '${fileUrl}', '파일 전송 완료.');

		//window.parent.CKEDITOR.tools.callFunction('${CKEditorFuncNum}', '${fileUrl}', '파일 전송 완료.');
		//에디터 이미지 업로드 팝업창에서 파일을 업로드 하면 controller에서 파일을 받고 저장한 후에
		// window.parent.CKEDITOR.tools.callFunction을 호출하면서 저장하고난 이미지 url을 넘겨주면 이미지를 불러오게 됩니다.
		//window.parent.CKEDITOR.tools.callFunction('" + CKEditorFuncNum + "', '" + sSaveName + "', '');
	</script>

</html>