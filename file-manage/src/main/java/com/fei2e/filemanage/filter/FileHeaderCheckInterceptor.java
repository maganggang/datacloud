package com.fei2e.filemanage.filter;

import com.fei2e.filemanage.tools.FileType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName FileHeaderCheckInterceptor
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 17:51
 * @Version 1.0
 **/
public class FileHeaderCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否为文件上传请求
        if (request != null && request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> files = multipartRequest.getFileMap();
            Iterator<String> iterator = files.keySet().iterator();
            while (iterator.hasNext()) {
                String formKey = iterator.next();
                MultipartFile multipartFile = multipartRequest.getFile(formKey);
                String content=request.getContentType();
                //获取InputStream
                InputStream in = multipartFile.getInputStream();
                //根据文件头获取文件类型
                String type = FileType.getFileType(in);
                if(multipartFile.getOriginalFilename().endsWith(".txt")){
                    return true;
                }
                if (type==null) {
                    //改为有识别与源文件名不符合的不允许
//                		throw new BaseRunException("上传文件有异常，已被系统禁止！") ;
                    System.out.println("----------上传文件有异常，已被系统禁止！头部信息：" + content);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = response.getWriter();
                    printWriter.write("上传文件有异常，已被系统禁止！");
                    return false;
                }
                return true;
            }
        }
            return true;
    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
