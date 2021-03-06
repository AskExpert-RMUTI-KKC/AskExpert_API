package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rmuti.askexpert.model.config.BaseUrlFile;
import rmuti.askexpert.model.config.PathConfig;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.FileException;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.*;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.AResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/image")
public class ImageDataController {
    //TODO : imageUploade and showImage

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private VerifyDataRepository verifyDataRepository;


    public static String uploadDirectory = System.getProperty("user.dir");

    @PostMapping("/userInfoDataProfile")
    public Object userInfoDataProfile(@RequestPart MultipartFile file) throws IOException, BaseException {
        String userId = tokenService.userId();
        BaseUrlFile baseUrlFile = new BaseUrlFile();

        String dir = baseUrlFile.getPathSet() + baseUrlFile.getImageProfileUrl();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String tempname = UUID.randomUUID().toString().replaceAll("-", "");
        //String imgName = userId +"timeStamp:"+ timeStamp +"random:"+ tempname + ".png";
        String imgName = "ID-"+userId+"-T"+timeStamp+"-R-"+tempname+ ".png";
        StringBuilder fileNames = new StringBuilder();


        Optional<UserInfoData> userInfoData = userInfoRepository.findById(userId);
        if (userInfoData.isEmpty()) {
            throw UserException.notFound();
        }

        //validate file
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }

        //validate size
        if (file.getSize() > 1048576 * 5) {
            //throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw  error
            throw FileException.unsupported();
        }


        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename() + " ");
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //update
        ImageData  imageData = new ImageData();
        imageData.setImgPath(baseUrlFile.getImageProfileUrl());
        imageData.setImgContentId(userId);
        imageData.setImgName(imgName);
        imageRepository.save(imageData);

        userInfoData.get().setProfilePic(imgName);
        APIResponse res = new APIResponse();
        userInfoRepository.save(userInfoData.get());

        Map<Object, Object> img = new HashMap<>();
        img.put("url", baseUrlFile.ipAddress() + ":8080" + dir + "/" + imgName);
        img.put("name", imgName);
        img.put("userInfoData",userInfoData.get());
        img.put("imageData",imageData);

        res.setMessage("upload success");
        res.setData(img);

        return res;
    }

    @PostMapping("/topicImg")
    public Object topicImg(@RequestParam String topicId,@RequestHeader String Authorization,@RequestPart MultipartFile file) throws IOException, BaseException {
        String userId = tokenService.userId();
        BaseUrlFile baseUrlFile = new BaseUrlFile();

        String dir = baseUrlFile.getPathSet() + baseUrlFile.getImageTopicUrl();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String tempname = UUID.randomUUID().toString().replaceAll("-", "");
        //String imgName = userId +"timeStamp:"+ timeStamp +"random:"+ tempname + ".png";
        String imgName = "ID-"+topicId+"-T"+timeStamp+"-R-"+tempname+ ".png";
        StringBuilder fileNames = new StringBuilder();


        Optional<TopicData> topicData = topicDataRepository.findByTopicIdAndTopicUserId(topicId,userId);
        if (topicData.isEmpty()) {
            throw TopicException.notyourtopic();
        }

        //validate file
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }

        //validate size
        if (file.getSize() > 1048576 * 5) {
            //throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw  error
            throw FileException.unsupported();
        }


        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename() + " ");
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //update
        APIResponse res = new APIResponse();

        ImageData  imageData = new ImageData();
        imageData.setImgPath(baseUrlFile.getImageTopic());
        imageData.setImgContentId(topicData.get().getTopicId());
        imageData.setImgName(imgName);
        imageRepository.save(imageData);

        Map<Object, Object> img = new HashMap<>();
        img.put("url", baseUrlFile.ipAddress() + ":8080" + dir + "/" + imgName);
        img.put("name", imgName);
        img.put("topicId",topicId);
        img.put("imageData",imageData);

        res.setMessage("upload success");
        res.setData(img);

        return res;
    }

    @PostMapping("/commentImg")
    public Object commentImg(@RequestParam String commentId,@RequestHeader String Authorization,@RequestPart MultipartFile file) throws IOException, BaseException {
        String userId = tokenService.userId();
        BaseUrlFile baseUrlFile = new BaseUrlFile();

        String dir = baseUrlFile.getPathSet() + baseUrlFile.getImageCommentUrl();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String tempname = UUID.randomUUID().toString().replaceAll("-", "");
        //String imgName = userId +"timeStamp:"+ timeStamp +"random:"+ tempname + ".png";
        String imgName = "ID-"+commentId+"-T"+timeStamp+"-R-"+tempname+ ".png";
        StringBuilder fileNames = new StringBuilder();


        Optional<CommentData> commentData = commentDataRepository.findByCommentIdAndCommentUserId(commentId,userId);
        if (commentData.isEmpty()) {
            throw UserException.accessDenied();
        }

        //validate file
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }

        //validate size
        if (file.getSize() > 1048576 * 5) {
            //throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw  error
            throw FileException.unsupported();
        }


        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename() + " ");
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //update
        APIResponse res = new APIResponse();

        ImageData  imageData = new ImageData();
        imageData.setImgPath(baseUrlFile.getImageComment());
        imageData.setImgContentId(commentData.get().getCommentId());
        imageData.setImgName(imgName);
        imageRepository.save(imageData);

        Map<Object, Object> img = new HashMap<>();
        img.put("url", baseUrlFile.ipAddress() + ":8080" + dir + "/" + imgName);
        img.put("name", imgName);
        img.put("commentId",commentId);
        img.put("imageData",imageData);

        res.setMessage("upload success");
        res.setData(img);

        return res;
    }
    @PostMapping("/verifyImg")
    public Object verifyImg(@RequestParam String verifyId,@RequestHeader String Authorization,@RequestPart MultipartFile file) throws IOException, BaseException {
        String userId = tokenService.userId();
        BaseUrlFile baseUrlFile = new BaseUrlFile();

        String dir = baseUrlFile.getPathSet() + baseUrlFile.getImageVerifyUrl();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String tempname = UUID.randomUUID().toString().replaceAll("-", "");
        //String imgName = userId +"timeStamp:"+ timeStamp +"random:"+ tempname + ".png";
        String imgName = "ID-"+verifyId+"-T"+timeStamp+"-R-"+tempname+ ".png";
        StringBuilder fileNames = new StringBuilder();


        Optional<VerifyData> commentData = verifyDataRepository.findByVerifyIdAndAndVerifyFrom(verifyId,userId);
        if (commentData.isEmpty()) {
            throw UserException.accessDenied();
        }

        //validate file
        if (file == null) {
            //throw error
            throw FileException.fileNull();
        }

        //validate size
        if (file.getSize() > 1048576 * 5) {
            //throw error
            throw FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw  error
            throw FileException.unsupported();
        }


        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename() + " ");
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //RemoveOldFile
        List<ImageData> imageDataList = imageRepository.findByImgContentId(verifyId);
        for(ImageData imageData:imageDataList){
            imageRepository.delete(imageData);
        }
        //update
        APIResponse res = new APIResponse();

        ImageData  imageData = new ImageData();
        imageData.setImgPath(baseUrlFile.getImageVerify());
        imageData.setImgContentId(commentData.get().getVerifyId());
        imageData.setImgName(imgName);
        imageRepository.save(imageData);

        Map<Object, Object> img = new HashMap<>();
        img.put("url", baseUrlFile.ipAddress() + ":8080" + dir + "/" + imgName);
        img.put("name", imgName);
        img.put("verifyId",verifyId);
        img.put("imageData",imageData);

        res.setMessage("upload success");
        res.setData(img);
        return res;
    }
}
