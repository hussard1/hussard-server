package com.hussard.web.base.bbs.repository;


import com.hussard.web.base.bbs.domain.Reply;

import java.util.List;

/**
 * Created by user on 2015-06-30.
 */

public interface ReplyRepository {

    void saveReply(Reply reply);

    List<Reply> findReplyList(int contentId);

    void deleteReply(Reply replyId);
}
