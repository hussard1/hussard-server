package com.hussard.web.base.bbs.repository;


import com.hussard.web.base.bbs.domain.BbsFile;
import com.hussard.web.base.bbs.domain.Content;

import java.util.List;

/**
 * Created by user on 2015-06-30.
 */

public interface FileRepository {

    void saveFile(BbsFile bbsFile);

    List<BbsFile>  findFileByContentId(int contentId);

    BbsFile findFileByFileId(int fileId);

    void deleteFile(Content content, String userId);
}
