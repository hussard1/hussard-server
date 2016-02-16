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

    List<Content> findList(int bbsId, int pageNum, int searchMode, String searchContent, int perPage);

    Map<String, Object> caculatePaging(int pageNum, int perPage, int totalContentCnt);

    int findCountByBbsId(int bbsId, int searchMode, String searchContent);

    void saveContent(Content content);

    Content findContentByContentId(int contentId);

    void updateContent(Content content);

    void updateViewCnt(int contentId);

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
