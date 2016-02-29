package com.hussard.web.base.bbs.controller;

import com.hussard.web.base.bbs.domain.BbsFile;
import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.domain.Content;
import com.hussard.web.base.bbs.domain.Reply;
import com.hussard.web.base.bbs.service.BbsService;
import com.hussard.web.base.bbs.service.FileService;
import com.hussard.web.base.bbs.service.ReplyService;
import com.hussard.web.base.bbs.validator.FileSizeValidator;
import com.hussard.web.base.util.PageNation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/bbs/bbs")
public class BbsController {

    private static final Logger logger = LoggerFactory.getLogger(BbsController.class);

    @Autowired
    private BbsService bbsService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileSizeValidator fileSizeValidator;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String bbsList(@RequestParam(value = "bbsId", defaultValue= "1") int bbsId, @RequestParam(value="page", defaultValue = "1") int page,
                                       @RequestParam(value = "searchMode", required = false, defaultValue = "0") int searchMode,
                                       @RequestParam(value = "searchContent", required = false) String searchContent,
                                       Model model) {

        Config config =  bbsService.findConfigByBbsId(bbsId);
        long totalContentCnt = bbsService.findContentCount(bbsId, searchMode, searchContent);

        PageNation pageNation = new PageNation(page, config.getPerPage(), totalContentCnt);

        List<Content> contents = bbsService.findContentList(bbsId, page, config.getPerPage(), searchMode, searchContent);

        model.addAttribute("config", config);
        model.addAttribute("contents", contents);
        model.addAttribute("pageNation", pageNation);
        model.addAttribute("searchMode", searchMode);
        model.addAttribute("searchContent", searchContent);

        return "bbs/list";
    }


    @RequestMapping(value = "/writeform", method = RequestMethod.GET)
    public String showForm(@RequestParam("bbsId" ) int bbsId, Model model) {

        Config config =  bbsService.findConfigByBbsId(bbsId);
        Content content = new Content();
        content.setBbsId(bbsId);
        model.addAttribute("config", config);
        model.addAttribute("content", content);

        return "bbs/writeform";
    }

    @RequestMapping(value = "/writeform", method = RequestMethod.POST)
    public String processForm(@RequestParam("bbsId") int bbsId,
                              @Valid Content content, BindingResult result,
                              Model model, HttpServletRequest request) {

        fileSizeValidator.validate(content, result);
        if (result.hasErrors()) {
            model.addAttribute("bbsId", content.getBbsId());
            model.addAttribute("content", content);
            return "bbs/writeform";
        }

        content.setRegiIpAddress(request.getRemoteAddr());

        bbsService.saveContent(content);
        fileService.saveFile(content, content.getFileUpload());

        return "redirect:/bbs/bbs/list?bbsId=" + content.getBbsId() + "&pagenum=1";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String showBbsDetail(@RequestParam("bbsId") int bbsId,
                                @RequestParam("contentid") int contentId, Model model) {

        Content content = bbsService.findContentById(contentId);
        bbsService.updateViewCnt(content);
        Config config =  bbsService.findConfigByBbsId(bbsId);

        model.addAttribute("config", config);
        model.addAttribute("content", content);
        model.addAttribute("reply", new Reply());


        return "bbs/detail";
    }

    @RequestMapping(value = "/updateform", method = RequestMethod.GET)
    public String showUpdateForm(@RequestParam("bbsId") int bbsId, @RequestParam("contentId") int contentId, Model model) {

        Config config =  bbsService.findConfigByBbsId(bbsId);
        Content content = bbsService.findContentById(contentId);

        model.addAttribute("config", config);
        model.addAttribute("content", content);

        return "bbs/updateform";
    }

    @RequestMapping(value = "/updateform", method = RequestMethod.POST)
    public String processUpdateForm(@Valid Content content, BindingResult result,
                                    Model model){

        fileSizeValidator.validate(content, result);
        if (result.hasErrors()) {
            model.addAttribute("bbsId", content.getBbsId());
            model.addAttribute("contentId", content.getId());
            return "bbs/updateform";
        }

        if(content.getFileDelId() != null) {
            fileService.deleteFile(content);
        }
        bbsService.updateContent(content);
        fileService.saveFile(content, content.getFileUpload());

        return "redirect:/bbs/bbs/detail?bbsId=" + content.getBbsId() + "&contentid=" + content.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("bbsId") int bbsId, @RequestParam("contentId") int contentId, Model model) {

        String userid = "admin";

        bbsService.deleteContent(contentId, userid);

        return "redirect:/bbs/bbs/list?bbsId=" + bbsId + "&pagenum=1";
    }

    @RequestMapping(value = "/addreply", method = RequestMethod.POST)
    public String addReply(@RequestParam("bbsId") int bbsId, @Valid Reply reply, BindingResult result, Model model, HttpServletRequest request) {

        String userid = "admin";

        if (result.hasErrors()) {
            Content content = bbsService.findContentById(reply.getContentId());
            List<BbsFile> bbsFiles = fileService.findFileByContentId(reply.getContentId());
            model.addAttribute("bbsId", bbsId);
            model.addAttribute("content", content);
            model.addAttribute("files", bbsFiles);
            List<Reply> replys = replyService.findReplyList(reply.getContentId());
            model.addAttribute("reply", reply);
            model.addAttribute("replys", replys);
            model.addAttribute("replyCnt", replys.size());
            return "bbs/detail";
        }

        reply.setRegiIpAddress(request.getRemoteAddr());
        reply.getDefaultColumns().setModifier(userid);

        replyService.saveReply(reply);

        return "redirect:/bbs/bbs/detail?bbsId="+bbsId+"&contentid="+reply.getContentId();
    }


    @RequestMapping(value = "/deletereply", method = RequestMethod.POST)
    public String removeReply(@RequestParam("bbsId") int bbsId, @Valid Reply reply, BindingResult result, Model model) {

        //replyService.validOwn(rnum, request);

        String userId = "test";

//        reply.setModiId(user.getUsername());
        reply.getDefaultColumns().setModifier(userId);
        replyService.deleteReply(reply);


        return  "redirect:/bbs/bbs/detail?bbsId="+bbsId+"&contentid="+reply.getContentId();
    }

    @RequestMapping(value = "/filedownload", method = RequestMethod.GET)
    public String fileDownload(@RequestParam("contentid") int contentId, @RequestParam("fileid") int fileId, @RequestParam("filename") String fileName, Model model, HttpServletResponse response) {

        //권한체크
        String user = "admin";

        try {
            BbsFile bbsFile = fileService.findFileByFileId(fileId);
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
            response.setContentType("application/octet-stream;");
            IOUtils.copy(fileService.getArchiveContent(user, bbsFile), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    public String updoadImage(@RequestParam("upload") MultipartFile imageFile, @RequestParam("CKEditorFuncNum") int CKEditorFuncNum, Model model, HttpServletResponse response){

        String fileUrl = null;

        if(fileService.validImage(imageFile)){
            fileUrl = fileService.saveImage(imageFile);
        }

        model.addAttribute("fileUrl", fileUrl);
        model.addAttribute("CKEditorFuncNum", CKEditorFuncNum);
        response.setHeader("X-Frame-Options", "SAMEORGIN");

        return "bbs/imageupload";
    }
}