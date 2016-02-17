package com.hussard.web.base.bbs.repository.mybatis;


import com.hussard.web.base.bbs.domain.Content;

import java.util.List;

/**
 * Created by user on 2015-07-02.
 */
public interface ContentRepository {

    List<Content> findListBySearchMode(int bbsId, int pageNum, int searchMode, String searchContent, int perPage);

    int findCountByBbsId(int bbsId, int searchMode, String searchContent);

    void saveContent(Content content);

    Content findContentByContentId(int contentId);

    void updateContent(Content content);

    void updateViewCnt(int contentId);

    void deleteContent(int contentId, String userid);

    List<Content> getBbsNoticeThumnailList(int bbsId);
}
