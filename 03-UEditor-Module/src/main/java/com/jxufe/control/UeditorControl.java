package com.jxufe.control;

import com.jxufe.dto.UEditorUploadDTO;
import com.jxufe.mongo.gridfs.GenericGridFsTemplate;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuburu on 2017/5/9.
 */
@Controller
@RequestMapping("ueditor")
public class UeditorControl {

    @Qualifier("evaGridFsTemplate")
    @Autowired
    GenericGridFsTemplate gridFsTemplate;


    @RequestMapping("/form")
    @ResponseBody
    public Object formAction(
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "content",required = false) String content){
        System.out.println("文件描述:"+description);
        System.out.println("文件内容:"+content);
        System.out.println("Main Thread begin");
        System.out.println("Main Thread end");
        return content;
    }


    @RequestMapping(value = "/uploadscrawl/upload",method = RequestMethod.POST)
    @ResponseBody
    public UEditorUploadDTO uploadTuYa(@RequestParam("upfile")String upfile){
        UEditorUploadDTO uploadDTO = new UEditorUploadDTO();
        uploadDTO.setOriginal("upfile");
        uploadDTO.setTitle("uploadscrawl title");
        uploadDTO.setState("SUCCESS");
        String fileUrl = "ueditor/files/down/"+upfile;
        uploadDTO.setUrl(fileUrl);
        System.err.println("==>"+uploadDTO);
        return uploadDTO;
    }


    /**
     * 文件上传（图片、文件、视频）
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/imagefile/upload",method = RequestMethod.POST)
    @ResponseBody
    public UEditorUploadDTO singleImageUpload(
            @RequestParam(value = "upfile",required = false) CommonsMultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        UEditorUploadDTO uploadDTO = new UEditorUploadDTO();
        uploadDTO.setOriginal(originalFilename);
        uploadDTO.setTitle(originalFilename);
        uploadDTO.setState("SUCCESS");
        System.out.println("上传文件结果显示==>"+uploadDTO);
        GridFSFile fsFile = gridFsTemplate.store(multipartFile.getInputStream(), "sso_"+originalFilename);
        String fileUrl = "ueditor/files/down/"+fsFile.getId();
        uploadDTO.setUrl(fileUrl);
        System.out.println("卜铷+fsFile==>"+fsFile);
        return uploadDTO;
    }

    @RequestMapping("/files/down/{fileId}")
    public ResponseEntity<byte[]> download(@PathVariable("fileId") String fileId) throws IOException {
        GridFSDBFile loadFile = gridFsTemplate.load(fileId);
        InputStream is = loadFile.getInputStream();
        byte[] body = null;
        body = new byte[(int) loadFile.getLength()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String fileName = new String(loadFile.getFilename().getBytes("UTF-8"),"iso-8859-1");
        headers.add("Content-Disposition", "attchement;filename=" +fileName);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;
    }


    @RequestMapping("json")
    @ResponseBody
    public Object responseJson() throws IOException {
        Map<String,String> resultMap  = new HashMap<>();
        resultMap.put("id","0133846");
        resultMap.put("name","liuburu");
        resultMap.put("age","25");
        return resultMap;
    }
}
