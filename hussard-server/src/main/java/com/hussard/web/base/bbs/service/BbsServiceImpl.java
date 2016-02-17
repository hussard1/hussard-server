package com.hussard.web.base.bbs.service;


import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.domain.Content;
import com.hussard.web.base.bbs.repository.ConfigRepository;
import com.hussard.web.base.bbs.repository.ContentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BbsServiceImpl implements BbsService {

    private static final Logger logger = LoggerFactory.getLogger(BbsServiceImpl.class);

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public Config findConfigByBbsId(int bbsId) {
        return configRepository.findConfigByBbsId(bbsId);
    }

    @Override
    public List<Content> findList(int bbsId, int pageNum, int perPage, int searchMode, String searchContent){

        return contentRepository.findListBySearchMode(bbsId, pageNum, perPage, searchMode, searchContent);
    }

    public Map<String, Object> caculatePaging(int pageNum, int perPage, long totalContentCnt){

        Map<String, Object> pagingMap = new HashMap<>();

        int pageNum_list = 5;
        int block = 0;
        int totalpage = 0;
        int totalblock = 0;


        if (pageNum % pageNum_list == 0) {
            block = (pageNum / pageNum_list);
        } else {
            block = (pageNum / pageNum_list) + 1;
        }

        int startpage = ((block-1)*pageNum_list) + 1;
        int endpage = startpage + pageNum_list -1;

        if (totalContentCnt % perPage == 0) {
            totalpage = ((int)totalContentCnt / perPage);
        } else {
            totalpage = ((int)totalContentCnt / perPage) + 1;
        }

        if(endpage > totalpage)

            endpage = totalpage;

        if (totalpage % pageNum_list == 0) {
            totalblock = (totalpage / pageNum_list);
        }else{
            totalblock = (totalpage / pageNum_list) + 1;
        }

        pagingMap.put("block", block);
        pagingMap.put("totalblock", totalblock);
        pagingMap.put("startpage", startpage);
        pagingMap.put("endpage", endpage);
        pagingMap.put("totalpage", totalpage);

        return pagingMap;
    }

    @Override
    public long findCountByBbsId(int bbsId, int searchMode, String searchContent) {
        return contentRepository.findCountByBbsId(bbsId, searchMode, searchContent);
    }

    @Override
    public void saveContent(Content content) {
        contentRepository.saveContent(content);
    }

    @Override
    public Content findContentByContentId(int contentId) {
        return contentRepository.findContentByContentId(contentId);
    }

    @Override
    public void updateContent(Content content) {
        contentRepository.updateContent(content);
    }

    @Override
    public void updateViewCnt(int contentId) {
        contentRepository.updateViewCnt(contentId);
    }

    @Override
    public void deleteContent(int contentId, String userid) {
        contentRepository.deleteContent(contentId, userid);
    }

    @Override
    public List<Config> findConfigList() {
        return configRepository.findConfigList();
    }

    @Override
    public void saveConfig(Config config) {
        configRepository.saveConfig(config);
    }

    @Override
    public void updateConfig(Config config) {
        configRepository.updateConfig(config);
    }

    @Override
    public boolean validAdmin() {
        boolean resultBoolean = false;

        return true;
    }

    @Override
    public boolean validReadAuth(int bbsId) {
        //권한체크
        Config config = configRepository.findConfigByBbsId(bbsId);

        return true;

//        if(config.getReadAuth() == 0){
//            return true;
//        }else if(config.getReadAuth() == 1){
//            if(user != null){
//                return true;
//            }else{
//                return false;
//            }
//        }else{
//            return true;
//        }
    }

    @Override
    public boolean validWriteAuth(int bbsId) {



//        if(validReadAuth(bbsId)){
//            if(config.getWriteAuth() == 0){
//                return true;
//            }else if(config.getWriteAuth() == 1){
//                if(user != null){
//                    return true;
//                }else{
//                    return false;
//                }
//            }else{
//                return false;
//            }
//        }else{
//            return false;
//        }

        return true;

    }

    @Override
    public boolean validReply(int bbsId) {
        Config config = configRepository.findConfigByBbsId(bbsId);

        if(config.getReplyYn().equals("Y")) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean validReplyWriteAuth(int bbsId) {
        Config config = configRepository.findConfigByBbsId(bbsId);
        return true;
    }

    @Override
    public List<Content> getBbsNoticeThumnailList() {
        int bbsId = 1;
        return contentRepository.getBbsNoticeThumnailList(bbsId);
    }

    @Override
    public List<Content> getBbsEventThumnailList() {
        return null;
//        return contentRepository.getBbsEventThumnailList();
    }
}
