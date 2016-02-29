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
 * Created by user on 2015-10-21.
 */

@Controller
@RequestMapping(value = "/bbs/api")
public class BbsApiController {

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
    @ResponseBody
    public Map<String, Object> ajaxBbsList(@RequestParam(value = "bbsId", defaultValue= "1") int bbsId, @RequestParam(value="page", defaultValue = "1") int page,
                                           @RequestParam(value = "searchMode", required = false, defaultValue = "0") int searchMode,
                                           @RequestParam(value = "searchContent", required = false) String searchContent,
                                           ModelMap model, HttpServletResponse response) {

        Config config =  bbsService.findConfigByBbsId(bbsId);
        List<Content> contents = bbsService.findContentList(bbsId, page, config.getPerPage(), searchMode, searchContent);
        long totalContentCnt = bbsService.findContentCount(bbsId, searchMode, searchContent);
        PageNation pageNation = new PageNation(page, config.getPerPage(), totalContentCnt);

        Map <String, Object> map = new HashMap<>();

        map.put("bbsId", bbsId);
        map.put("bbsName", config.getBbsName());
        map.put("bbsDesc", config.getBbsDesc());
        map.put("contents", contents);
        map.put("pageNation", pageNation);
        map.put("searchContent", searchContent);
        map.put("searchMode", searchMode);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return map;
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> processForm(@RequestParam(value="bbsId", defaultValue= "1") int bbsId,
                                               @RequestBody Content content, BindingResult result,
                              Model model, HttpServletRequest request, HttpServletResponse response) {

//        fileSizeValidator.validate(content, result);
//        if (result.hasErrors()) {
//            model.addAttribute("bbsId", content.getBbsId());
//            model.addAttribute("content", content);
//            return "bbs/bbs/writeform";
//        }
        content.setBbsId(bbsId);
        content.setRegiIpAddress(request.getRemoteAddr());
//        content.setRegiId(userId);

        bbsService.saveContent(content);
//        fileService.saveFile(content, content.getFileUpload());

        HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put("result", "success");
        return resultMap;
    }

    @RequestMapping(value = "/detail.{suffix}", method = RequestMethod.GET)
    public String showBbsDetail(@PathVariable String suffix, @RequestParam("bbsId") int bbsId,
                                @RequestParam("contentid") int contentId, Model model) {

        if (!bbsService.validReadAuth(bbsId)) {
            return "redirect:/auth/c/login." + suffix;
        }
        if (bbsService.validWriteAuth(bbsId)) {
            model.addAttribute("writeAuth", "true");
        }
        Content content = bbsService.findContentById(contentId);
        bbsService.updateViewCnt(content);


//        if(bbsService.validReply(bbsId)) {
//            List<Reply> replys = replyService.findReplyList(contentId);
//            if(bbsService.validReplyWriteAuth(bbsId)){
//                model.addAttribute("replyWriteAuth", "true");
//            }
//            Reply reply = new Reply();
//            model.addAttribute("reply", reply);
//            model.addAttribute("replys", replys);
//            model.addAttribute("replyCnt", replys.size());
//        }

        String userId = "test";

        Config config =  bbsService.findConfigByBbsId(bbsId);
        List<BbsFile> bbsFiles = fileService.findFileByContentId(contentId);
        model.addAttribute("userId", userId);
        model.addAttribute("bbsId", bbsId);
        model.addAttribute("bbsName", config.getBbsName());
        model.addAttribute("bbsDesc", config.getBbsDesc());
        model.addAttribute("content", content);
        model.addAttribute("files", bbsFiles);

        return "bbs/bbs/detail";
    }

    @RequestMapping(value = "/updateform", method = RequestMethod.GET)
    public String showUpdateForm(@RequestParam("bbsId") int bbsId, @RequestParam("contentid") int contentId, Model model) {

        String userid = "admin";

        if (!bbsService.validWriteAuth(bbsId)) {
        }

        Config config =  bbsService.findConfigByBbsId(bbsId);
        Content content = bbsService.findContentById(contentId);
        List<BbsFile> bbsFiles = fileService.findFileByContentId(contentId);

        model.addAttribute("bbsName", config.getBbsName());
        model.addAttribute("bbsDesc", config.getBbsDesc());
        model.addAttribute("bbsId", bbsId);
        model.addAttribute("contentId", contentId);
        model.addAttribute("content", content);
        model.addAttribute("files", bbsFiles);

        return "bbs/bbs/updateform";
    }

    @RequestMapping(value = "/updateform.{suffix}", method = RequestMethod.POST)
    public String processUpdateForm(@PathVariable String suffix, @Valid Content content, BindingResult result,
                                    Model model){

        String userid = "admin";

        fileSizeValidator.validate(content, result);
        if (result.hasErrors()) {
            model.addAttribute("bbsId", content.getBbsId());
            model.addAttribute("contentId", content.getId());
            return "bbs/bbs/updateform";
        }

        //�ڽ��� ���� �´��� Ȯ��
//        bbsService.validOwn(num, request){}
        content.getDefaultColumns().setModifier(userid);

        if(content.getFileDelId() != null) {
            fileService.deleteFile(content);
        }
        bbsService.updateContent(content);
        fileService.saveFile(content, content.getFileUpload());

        return "redirect:/bbs/bbs/detail." + suffix + "?bbsId=" + content.getBbsId() + "&contentid=" + content.getId();
    }

    @RequestMapping(value = "/delete.{suffix}", method = RequestMethod.GET)
    public String delete(@PathVariable String suffix, @RequestParam("bbsId") int bbsId, @RequestParam("contentid") int contentId, Model model) {


        String userid = "admin";

        //�ڽ��� ���� �´��� Ȯ��
//            bbsService.validOwn(num, request);

        bbsService.deleteContent(contentId, userid);

        return "redirect:/bbs/bbs/list."+ suffix + "?bbsId=" + bbsId + "&pagenum=1";
    }

    @RequestMapping(value = "/addreply.{suffix}", method = RequestMethod.POST)
    public String addReply(@PathVariable String suffix, @RequestParam("bbsId") int bbsId, @Valid Reply reply, BindingResult result, Model model, HttpServletRequest request) {

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
            return "bbs/bbs/detail";
        }

        reply.setRegiIpAddress(request.getRemoteAddr());
        reply.getDefaultColumns().setRegistrant(userid);

        replyService.saveReply(reply);

        return "redirect:/bbs/bbs/detail.sag?bbsId="+bbsId+"&contentid="+reply.getContentId();
    }


    @RequestMapping(value = "/deletereply.{suffix}", method = RequestMethod.POST)
    public String removeReply(@PathVariable String suffix, @RequestParam("bbsId") int bbsId, @Valid Reply reply, BindingResult result, Model model) {

        //replyService.validOwn(rnum, request);

        String userId = "test";

//        reply.setModiId(user.getUsername());
        reply.getDefaultColumns().setModifier(userId);
        replyService.deleteReply(reply);


        return  "redirect:/bbs/bbs/detail."+ suffix +"?bbsId="+bbsId+"&contentid="+reply.getContentId();
    }

    @RequestMapping(value = "/filedownload", method = RequestMethod.GET)
    public String fileDownload(@RequestParam("contentid") int contentId, @RequestParam("fileid") int fileId, @RequestParam("filename") String fileName, Model model, HttpServletResponse response) {

        //����üũ
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

        return "bbs/bbs/imageupload";
    }
}
