package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.Content;

import java.util.List;

/**
 * Created by user on 2016-02-16.
 */
public interface ContentRepository {

    List<Content> findListBySearchMode(int bbsId, int pageNum, int perPage, int searchMode, String searchContent);

    long findCountByBbsId(int bbsId, int searchMode, String searchContent);

    void saveContent(Content content);

    Content findContentByContentId(int contentId);

    void updateContent(Content content);

    void updateViewCnt(int contentId);

    void deleteContent(int contentId, String userid);

    List<Content> getBbsNoticeThumnailList(int bbsId);

}
