package com.jxufe.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by liuburu on 2017/7/7.
 */
@Controller
@RequestMapping("springmvc")
public class SpringMvcUploadControl {

    /**
     * 该方法非常费时间，舍弃
     * @param multipartFile
     * @return
     */
    @RequestMapping("/upload-1")
    @ResponseBody
    public Object upload1Method(@RequestParam(value = "file",required = false) CommonsMultipartFile multipartFile) {
        //用来检测程序运行时间
        long startTime = System.currentTimeMillis();
        System.out.println("fileName：" + multipartFile.getOriginalFilename());

        try {
            //获取输出流
            OutputStream os = new FileOutputStream("E:\\home\\" + new Date().getTime() + multipartFile.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is = multipartFile.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while ((temp = is.read()) != (-1)) {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("方法一的运行时间：" + result + "ms");
        return result ;
    }

    @RequestMapping("/upload-2")
    @ResponseBody
    public Object upload2Method(@RequestParam(value = "file",required = false) CommonsMultipartFile multipartFile) {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+multipartFile.getOriginalFilename());
        String path="E:\\home\\"+new Date().getTime()+multipartFile.getOriginalFilename();
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        OutputStream os = null;
        InputStream is = null;
        try {
            os = new FileOutputStream("E:\\home\\" + new Date().getTime() + multipartFile.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            is = multipartFile.getInputStream();
            byte[] buffer = new byte[1024];
            //每次1024字节的读取并写入
            int len =0;
            while ((len = is.read(buffer)) != (-1)) {
                os.write(buffer,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long  endTime=System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("方法一的运行时间：" + result + "ms");
        return result ;
    }
    @RequestMapping("/upload-3")
    @ResponseBody
    public Object upload3Method(@RequestParam(value = "file",required = false) CommonsMultipartFile multipartFile) {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+multipartFile.getOriginalFilename());
        String path="E:\\home\\"+new Date().getTime()+multipartFile.getOriginalFilename();
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        OutputStream os = null;
        InputStream is = null;
        try {
            os = new FileOutputStream("E:\\home\\" + new Date().getTime() + multipartFile.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            is = multipartFile.getInputStream();
            /*封装成高效流*/
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(os);
            //每次1024字节的读取并写入
            int temp;
            while ((temp = bufferedInputStream.read()) != (-1)) {
                bufferedOutputStream.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long  endTime=System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("方法一的运行时间：" + result + "ms");
        return result ;
    }


    @RequestMapping("/upload-4")
    @ResponseBody
    public Object upload4Method(@RequestParam(value = "file",required = false) CommonsMultipartFile multipartFile) {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+multipartFile.getOriginalFilename());
        String path="E:\\home\\"+new Date().getTime()+multipartFile.getOriginalFilename();
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        OutputStream os = null;
        InputStream is = null;
        try {
            os = new FileOutputStream("E:\\home\\" + new Date().getTime() + multipartFile.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            is = multipartFile.getInputStream();
            byte[] buffer = new byte[1024];
            /*封装成高效流*/
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(os);
            //每次1024字节的读取并写入
            int len =0;
            while ((len = bufferedInputStream.read(buffer)) != (-1)) {
                bufferedOutputStream.write(buffer,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long  endTime=System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("方法一的运行时间：" + result + "ms");
        return result ;
    }


    @RequestMapping("/upload-5")
    @ResponseBody
    public Object upload5Method(@RequestParam(value = "file",required = false) CommonsMultipartFile multipartFile) throws IOException {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+multipartFile.getOriginalFilename());
        String path="E:\\home\\"+new Date().getTime()+multipartFile.getOriginalFilename();
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        multipartFile.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        long result = endTime-startTime;
        System.out.println("方法一的运行时间：" + result + "ms");
        return result ;
    }

    @RequestMapping("/upload-6")
    @ResponseBody
    public Object upload6Method(HttpServletRequest request) throws IOException {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="E:\\home\\"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        long result = endTime-startTime;
        System.out.println("方法一的运行时间：" + result + "ms");
        return result ;
    }

}
