package com.hussard.web.base.bbs.service;

import com.hussard.web.base.bbs.domain.BbsFile;
import com.hussard.web.base.bbs.domain.Content;
import com.hussard.web.base.bbs.repository.ConfigRepository;
import com.hussard.web.base.bbs.repository.FileRepository;
import com.hussard.web.base.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by user on 2015-07-03.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileMapper;

    @Autowired
    ConfigRepository configMapper;

    @Override
    public void saveFile(Content content, MultipartFile fileUpload[]) {

        BbsFile bbsFile = null;
        String fileName = null;
        String filePath = null;

        for (int i = 0; i < fileUpload.length; i++) {
            if(fileUpload[i].getSize() > 0) {
                fileName = UUID.randomUUID().toString();
                filePath = ObjectUtils.hashcodeByCurrentDate() + File.separator + ObjectUtils.hashcodeByCurrentHour();
                saveArchive(filePath, fileName, fileUpload[i]);

                bbsFile = new BbsFile();
                bbsFile.setContentId(content.getId());
                bbsFile.getDefaultColumns().setRegistrant(content.getDefaultColumns().getRegistrant());
                bbsFile.setFileName(fileName);
                bbsFile.setFileOriName(fileUpload[i].getOriginalFilename());
                bbsFile.setFileSize(fileUpload[i].getSize());
                bbsFile.setFileUrl(filePath);
                fileMapper.saveFile(bbsFile);
            }
        }
    }

    @Override
    public String saveArchive(String pathId, String fileId, MultipartFile file) {
        File archiveFile = getArchiveFile(pathId, fileId);

        try {
            if (!archiveFile.getParentFile().exists()) {
                archiveFile.getParentFile().mkdirs();
            }

            file.transferTo(archiveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileId;
    }

    @Override
    public File getArchiveFile(String pathId, String fileId) {

//        CodeDetail detail = codeService.getDetailByName(CodeMaster.CODE_SETTINGS, CodeDetail.CODENAME_BBS_LOCATION);
//        return getBaseContentFile(detail.getDescription(), pathId, fileId);

//        return getBaseContentFile(baseFilePath, pathId, fileId);

          return null;
    }

    private File getBaseContentFile(final String base, final String pathId, final String fileId) {

        @SuppressWarnings("serial")
        ArrayList<String> list = new ArrayList<String>() {{
            add(base);
            add(pathId);
            add(fileId);
        }};
        String filename = ObjectUtils.concatFilenames(list);

//        return new File(globalConfiguration.getSeobaksahome(), filename);
        return null;
    }


    @Override
    public List<BbsFile> findFileByContentId(int contentId) {
        return fileMapper.findFileByContentId(contentId);
    }

    @Override
    public BbsFile findFileByFileId(int FileId) {
        return fileMapper.findFileByFileId(FileId);
    }

    @Override
    public void deleteFile(Content content) {

        // TODO : change
        String userId = "system";

        fileMapper.deleteFile(content, userId);
    }

    @Override
    public InputStream getArchiveContent(String user, BbsFile bbsFile) throws IOException {
        File file = getArchiveFile(bbsFile.getFileUrl(), bbsFile.getFileName());

        return new BufferedInputStream(new FileInputStream(file));
    }

    @Override
    public boolean validImage(MultipartFile imageFile) {
        if(imageFile.getContentType().contains("image")){
            return true;
        }
        return false;
    }

    @Override
    public String saveImage(MultipartFile imageFile) {

//        CodeDetail detail = codeService.getDetailByName(CodeMaster.CODE_SETTINGS, CodeDetail.CODENAME_BBS_LOCATION);
//
//        String fileOriName = imageFile.getOriginalFilename();
//        int idx = fileOriName.lastIndexOf(".");
//        String fileName = UUID.randomUUID().toString() + fileOriName.substring(idx);
//        String filePath = ObjectUtils.hashcodeByCurrentDate() + File.separator + ObjectUtils.hashcodeByCurrentHour();
//
//        saveArchive(filePath, fileName, imageFile);
//
//        ArrayList<String> list = new ArrayList<String>();
//        list.add(detail.getDescription());
//        list.add(filePath);
//        list.add(fileName);
//
//        String fileUrl = ObjectUtils.concatFilenames(list);
//
//        fileUrl = fileUrl.replace("/data", "");
//        fileUrl = fileUrl.replace("\\", "/");
//
//        return fileUrl;
          return null;
    }
}
