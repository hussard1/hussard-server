package com.hussard.web.base.bbs.service;


import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.domain.Content;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015-07-09.
 */
public interface BbsService {

    Config findConfigByBbsId(int bbsId);

    List<Content> findContentList(int bbsId, int pageNum, int perPage, int searchMode, String searchContent);

    long findContentCount(int bbsId, int searchMode, String searchContent);

    void saveContent(Content content);

    Content findContentById(int contentId);

    void updateContent(Content content);

    void updateViewCnt(Content content);

    void deleteContent(int contentId, String userid);

    List<Config> findConfigList();

    void saveConfig(Config config);

    void updateConfig(Config config);

    boolean validAdmin();

    boolean validReadAuth(int bbsId);

    boolean validWriteAuth(int bbsId) ;

    boolean validReply(int bbsId);

    List<Content> getBbsNoticeThumnailList();

    List<Content> getBbsEventThumnailList();

    boolean validReplyWriteAuth(int bbsId);


}
