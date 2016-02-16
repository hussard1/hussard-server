package com.hussard.web.base.bbs.service;

import com.hussard.web.base.bbs.domain.Reply;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface ReplyService {

	void saveReply(Reply reply);

	List<Reply> findReplyList(int contentId);

	void deleteReply(Reply replyId);

	boolean validOwn(int num, HttpServletRequest request) throws Exception;
}
