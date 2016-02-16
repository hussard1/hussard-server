package com.hussard.web.base.bbs.service;

/**
 * Created by user on 2015-07-09.
 */

import com.hussard.web.base.bbs.domain.Reply;
import com.hussard.web.base.bbs.repository.ReplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    private static final Logger logger = LoggerFactory
            .getLogger(ReplyService.class);

    @Autowired
    private ReplyRepository replyMapper;

    public void saveReply(Reply reply){
        replyMapper.saveReply(reply);
    }

    public List<Reply> findReplyList(int contentId){
        return replyMapper.findReplyList(contentId);
    }

    public void deleteReply(Reply replyId) {
        replyMapper.deleteReply(replyId);
    }


    public boolean validOwn(int num, HttpServletRequest request) throws Exception{

        boolean resultBoolean = false;

        HttpSession session = request.getSession(false);
        logger.debug("login id "+session.getAttribute("userid"));

        if (session.getAttribute("userid") != null){
            logger.debug("login y ");
            //Reply reply = replyMapper.findById(num);

//			String replyUserid = reply.getUser().getUserid();
//			String sessionUserid = session.getAttribute("userid").toString();
//			resultBoolean = replyUserid.equals(sessionUserid);

            //userid
//			String userid = session.getAttribute("userid").toString();
//			User user = userDao.findAllByProperty("userid", userid).get(0);

        }else{
            resultBoolean = false;
        }

        if(resultBoolean==false){
            String message = "no own";
            throw new Exception(message);
        }

        return resultBoolean;
    }
}
