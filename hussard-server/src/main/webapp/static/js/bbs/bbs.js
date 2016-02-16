$(document).ready(function(){

	var updateInputCount = function() {
		var replytext = $('.reply_text_input textarea').val();
		var count = replytext.length;
		$('span.inputCnt').text(count);
		if (count >= 300) {
			replytext = replytext.substring(0, 300);
			$('.reply_text_input textarea').val(replytext);
		}
	}

	$('.reply_text_input textarea')
		.focus(updateInputCount)
		.blur(updateInputCount)
		.keypress(updateInputCount)
		.keyup(updateInputCount);

});