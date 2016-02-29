package com.hussard.web.base.bbs.service;

import com.hussard.web.base.bbs.domain.BbsFile;
import com.hussard.web.base.bbs.domain.Content;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by user on 2015-07-03.
 */
public interface FileService {

    void saveFile(Content content, MultipartFile fileUpload[]);

    String saveArchive(String pathId, String fileId, MultipartFile file);

    File getArchiveFile(String pathId, String fileId);

    List<BbsFile> findFileByContentId(int contentId);

    BbsFile findFileByFileId(int FileId);

    void deleteFile(Content content);

    InputStream getArchiveContent(String user, BbsFile bbsFile) throws IOException;

    boolean validImage(MultipartFile imageFile);

    String saveImage(MultipartFile imageFile);
}
