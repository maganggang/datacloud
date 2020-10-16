package com.fei2e.filemanage.service;

import com.fei2e.filemanage.entity.BaseResult;
import com.fei2e.filemanage.entity.FileBase;
import com.fei2e.filemanage.repository.FileBaseRepository;
import com.fei2e.filemanage.tools.FileType;
import com.sun.deploy.net.URLEncoder;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName FileBaseBiz
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 10:09
 * @Version 1.0
 **/
@Service
public class FileBaseBiz  {
    @Value("${web.upload-path}")
    private String BASE_PATH;
    @Value("${server.port:8080}")
    private int port;
   /*;
        System.out.println(suffix);*/
    @Autowired
    private FileBaseRepository fileBaseRepository;

    private void saveFile(MultipartFile file,String filePath,String fileName2){
        try {
            File dest = new File(filePath + fileName2);
            File root = new File(filePath);
            if (!root.isDirectory()) {
                root.mkdirs();
            }
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @Author mgg
     * @Description 上传图片和保存数据
     * @Date 9:15 2020/9/17
     * @Param [file, request]
     * @return com.fei2e.filemanage.entity.BaseResult<com.fei2e.filemanage.entity.FileBase>
     **/
    public BaseResult<FileBase> uploadFile(MultipartFile file, HttpServletRequest request) {

        BaseResult baseResult=new BaseResult();
        if (file.isEmpty()) {
            baseResult.setStatus(500);
            baseResult.setMessage("上传失败，请选择文件");
            return  baseResult;
        }
        //获取文件名
        Calendar calendar=Calendar.getInstance();
        String filePath = getRealFilePath(calendar);
        String fileName2=getRealFileName(file.getOriginalFilename(),calendar);
        saveFile(file,filePath,fileName2);
        FileBase fileBase=updateOrSaveData(file,filePath,fileName2,null,request.getHeader("user-agent"),request.getRemoteHost(),calendar);
        baseResult.setData(fileBase);
        return baseResult;
    }

    public BaseResult<List<FileBase>> uploadFiles(List<MultipartFile> files, HttpServletRequest request) {
        BaseResult<List<FileBase>> baseResult=new BaseResult();
        if(files.isEmpty()){
            baseResult.setStatus(501);
            baseResult.setMessage("上传失败，请选择文件");
            return baseResult;
        }
        //获取文件名
        Calendar calendar=Calendar.getInstance();
        String filePath = getRealFilePath(calendar);
        List<FileBase> fileBases=new ArrayList<>();
        for(MultipartFile file:files){
            String fileName2=getRealFileName(file.getOriginalFilename(),calendar);
            if(file.isEmpty()){
                baseResult.setStatus(501);
                baseResult.setMessage("上传失败，空文件");
                return baseResult;
            }else{
                saveFile(file,filePath,fileName2);
                FileBase fileBase=updateOrSaveData(file,filePath,fileName2,null,request.getHeader("user-agent"),request.getRemoteHost(),calendar);
                fileBases.add(fileBase);
            }
        }
        baseResult.setData(fileBases);
        return baseResult;
    }

    public BaseResult<Boolean> downloadFile(Integer id, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException{
        BaseResult<Boolean> baseResult=new BaseResult();
        FileBase fileBase=new FileBase();
        fileBase.setId(id);
        fileBase.setBaseUrl(request.getRemoteHost()+":"+port);
        fileBase=fileBaseRepository.findById(id).get();
        // 如果文件名不为空，则进行下载
        if (fileBase!=null&&fileBase.getName() != null) {
            File file = new File(fileBase.getPath());
            // 如果文件存在，则进行下载
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileBase.getName(), "UTF-8"));
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download  successfully!");
                    baseResult.setData(true);

                } catch (Exception e) {
                    System.out.println("Download  failed!");
                    baseResult.setData(false);
                    baseResult.setMessage("未找到文件");
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }else{
            baseResult.setData(false);
            baseResult.setMessage("未找到文件");
        }
        return baseResult;
    }

    public void downloadZipFile(List<Integer> ids, HttpServletResponse response) {
        //存放--服务器上zip文件的目录
        //获取文件名
        String filePath = BASE_PATH+"/temp/";
        File directoryFile=new File(filePath);
        if(!directoryFile.isDirectory() && !directoryFile.exists()){
            directoryFile.mkdirs();
        }
        //设置最终输出zip文件的目录+文件名
        SimpleDateFormat formatter  = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String zipFileName = formatter.format(new Date())+".zip";
        String strZipPath = filePath+"\\"+zipFileName;

        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        File zipFile = new File(strZipPath);
        List<FileBase> fileBases=fileBaseRepository.findAllById(ids);
        if(fileBases!=null&&fileBases.size()>0){
            //构造最终压缩包的输出流
            try {
                zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
                int i=1;
                for(FileBase fileBase:fileBases){
                    //解码获取真实路径与文件名
                    String realFileName = java.net.URLDecoder.decode("("+i+")"+fileBase.getName(),"UTF-8");
                    String realFilePath = java.net.URLDecoder.decode(fileBase.getPath(),"UTF-8");
                    File file = new File(realFilePath);
                    if(file.exists()){
                        zipSource = new FileInputStream(file);//将需要压缩的文件格式化为输入流
                        /**
                         * 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样这里的name就是文件名,
                         * 文件名和之前的重复就会导致文件被覆盖
                         */
                        ZipEntry zipEntry = new ZipEntry(realFileName);//在压缩目录中文件的名字
                        zipStream.putNextEntry(zipEntry);//定位该压缩条目位置，开始写入文件到压缩包中
                        bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                        int read = 0;
                        byte[] buf = new byte[1024 * 10];
                        while((read = bufferStream.read(buf, 0, 1024 * 10)) != -1){
                            zipStream.write(buf, 0, read);
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                //关闭流
                try {
                    if(null != bufferStream) bufferStream.close();
                    if(null != zipStream){
                        zipStream.flush();
                        zipStream.close();
                    }
                    if(null != zipSource) zipSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //判断系统压缩文件是否存在：true-把该压缩文件通过流输出给客户端后删除该压缩文件  false-未处理
        if(zipFile.exists()){
            downImg(response,zipFileName,strZipPath);
            zipFile.delete();
        }
    }
    public void downImg(HttpServletResponse response,String filename,String path ){
        if (filename != null) {
            FileInputStream is = null;
            BufferedInputStream bs = null;
            OutputStream os = null;
            try {
                File file = new File(path);
                if (file.exists()) {
                    //设置Headers
                    response.setHeader("Content-Type","application/octet-stream");
                    //设置下载的文件的名称-该方式已解决中文乱码问题
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                    is = new FileInputStream(file);
                    bs =new BufferedInputStream(is);
                    os = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while((len = bs.read(buffer)) != -1){
                        os.write(buffer,0,len);
                    }
                }else{
                    String error = Base64Util.encode("下载的文件资源不存在");
                }
            }catch(IOException ex){
                ex.printStackTrace();
            }finally {
                try{
                    if(is != null){
                        is.close();
                    }
                    if( bs != null ){
                        bs.close();
                    }
                    if( os != null){
                        os.flush();
                        os.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void deleteData(Integer id){
       fileBaseRepository.deleteById(id);
    }
    /**
     * @Author mgg
     * @Description 删除本地附件
     * @Date 15:50 2020/10/16
     * @Param [filePath]
     * @return boolean
     **/
    private boolean deleteFile(String filePath){
        File file = new File(filePath);
        if (file.exists()){
            return file.delete();
        }else{
            return false;
        }
    }
    public BaseResult<FileBase> updateFile(Integer id,MultipartFile file, HttpServletRequest request) {
        BaseResult<FileBase> result=new BaseResult<>();
        FileBase fileBase=fileBaseRepository.findById(id).get();
        if(fileBase!=null&&fileBase.getId()!=null){
            deleteFile(fileBase.getPath());
        }else{
            result.setStatus(500);
            result.setMessage("无相关数据");
            return result;
        }
        Calendar calendar=Calendar.getInstance();
        String filePath = getRealFilePath(calendar);
        String fileName2=getRealFileName(file.getOriginalFilename(),calendar);
        fileBase=updateOrSaveData(file,filePath,fileName2,fileBase,request.getHeader("user-agent"),request.getRemoteHost(),calendar);
        saveFile(file,filePath,fileName2);
        result.setData(fileBase);
        return result;
    }
private String getRealFileName(String fileName,Calendar calendar){
    String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
    String fileName2=UUID.randomUUID().toString()+"."+suffix;
    return fileName2;
}
    private String getRealFilePath(Calendar calendar){
        String tempPath=new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        String filePath = BASE_PATH+"/file/"+tempPath+"/";
        return filePath;
    }

    private FileBase updateOrSaveData(MultipartFile file, String filePath,String fileName2, FileBase fileBase,String origin,String baseUrl,Calendar calendar) {
        String fileName = file.getOriginalFilename();
        String type=null;
        try {
            InputStream in = file.getInputStream();
            //根据文件头获取文件类型
            type = FileType.getFileType(in);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        return saveFileData(fileName,origin,baseUrl,calendar,file.getSize(),type,filePath+fileName2,fileName2,fileBase);
    }
/**
 * @Author mgg
 * @Description  删除附件和数据
 * @Date 15:50 2020/10/16
 * @Param
 * @return
 **/
    public BaseResult<Boolean> deleteFile(Integer id) {
        BaseResult<Boolean> result=new BaseResult<>();
        FileBase fileBase=fileBaseRepository.findById(id).get();
        if(fileBase!=null){
            boolean b=deleteFile(fileBase.getPath());
            deleteData(id);
            result.setData(b);
        }else{
            result.setData(false);
            result.setMessage("数据不存在");
        }
        return result;
    }
    /**
     * @Author mgg
     * @Description  保存附件数据
     * @Date 9:17 2020/10/16
     * @Param 
     * @return 
     **/
    public FileBase saveFileData(String fileName,String origin,String baseUrl,Calendar calendar,Long size,String type,String path,String code, FileBase fileBase ){
        if(fileBase==null||fileBase.getId()==null){
            fileBase=new FileBase();
        }
        //插入数据
        fileBase.setName(fileName);
        fileBase.setOrigin(origin);//来源
        fileBase.setBaseUrl(baseUrl+":"+port);
        fileBase.setCreateTime(calendar.getTime());
        fileBase.setYear(calendar.get(Calendar.YEAR));
        fileBase.setMonth(calendar.get(Calendar.MONTH)+1);
        fileBase.setDay(calendar.get(Calendar.DATE));
        fileBase.setSize(size);
        fileBase.setType(type);
        fileBase.setUseCount(0);
        fileBase.setPath(path);
        fileBase.setCode(code);
        fileBaseRepository.save(fileBase);
        return fileBase;
    }
    /**
     * @Author mgg
     * @Description  使用状态更改
     * @Date 10:47 2020/10/16
     * @Param 
     * @return 
     **/
    public Integer updateFileCount(List<Integer> ids){
        //使用状态更改
        return fileBaseRepository.updateCount(ids);
    }

    public List<FileBase> selectByIds(List<Integer> list) {
        return fileBaseRepository.findByIds(list);
    }
}
