package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;
import rmuti.askexpert.model.config.BaseUrlFile;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.FileException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.AResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.UserInfoData;

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


    public static String uploadDirectory = System.getProperty("user.dir");

    @PostMapping("/userInfoDataProfile")
    public Object userInfoDataProfile(@RequestPart MultipartFile file) throws IOException,BaseException {
        String userId = tokenService.userId();
        Optional<UserInfoData> userInfoData =userInfoRepository.findById(userId);
        if(userInfoData.isEmpty()){
            throw UserException.notFound();
        }

        String dir = new BaseUrlFile().getPathSet() + new BaseUrlFile().getImageProfileUrl();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_").format(new Date());
        String tempname = UUID.randomUUID().toString().replaceAll("-", "");
        String imgName = timeStamp + tempname + ".png";

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

        StringBuilder fileNames = new StringBuilder();

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

        Map<Object, Object> img = new HashMap<>();
        img.put("url", new BaseUrlFile().ipAddress() + ":8080" + dir + "/" + imgName);
        img.put("name", imgName);

        Object res = new AResponse().ok("upload success", "img", img);
        return res;
    }

    // @PostMapping("/editimgprofile")
    // public Object editimgprofile(@RequestPart MultipartFile file) throws IOException {
    //     String userId = tokenService.userId();
    //     Optional<UserInfoData> opt_userinfo = userInfoRepository.findById(userId);
    //     if (opt_userinfo.isEmpty()) {
    //         throw UserException.nouserinfo();
    //     }


    //     String dir = new BaseUrlFile().getPathSet() + new BaseUrlFile().getImageProfileUrl();
    //     String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_").format(new Date());
    //     String tempname = UUID.randomUUID().toString().replaceAll("-", "");
    //     String imgName = timeStamp + tempname + ".png";

    //     //validate file
    //     if (file == null) {
    //         //throw error
    //         throw FileException.fileNull();
    //     }

    //     //validate size
    //     if (file.getSize() > 1048576 * 5) {
    //         //throw error
    //         throw FileException.fileMaxSize();
    //     }
    //     String contentType = file.getContentType();
    //     if (contentType == null) {
    //         //throw  error
    //         throw FileException.unsupported();
    //     }

    //     StringBuilder fileNames = new StringBuilder();

    //     Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
    //     fileNames.append(file.getOriginalFilename() + " ");
    //     try {
    //         Files.write(fileNameAndPath, file.getBytes());
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     try {
    //         byte[] bytes = file.getBytes();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }

    //     Map<Object, Object> img = new HashMap<>();
    //     img.put("url", new BaseUrlFile().ipAddress() + ":8080" + dir + "/" + imgName);
    //     img.put("name", imgName);

    //     Object res = new AResponse().ok("upload success", "img", img);

    //     opt_userinfo.get().setProfilePic(imgName);
    //     userInfoRepository.save(opt_userinfo.get());
    //     return res;
    // }


    // uploadImg
    //uploadProfileImg
    //uploadTopicImg
    //uploadCommentImg

}
