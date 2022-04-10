package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rmuti.askexpert.model.config.BaseUrlFile;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.FileException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.request.ReqLogin;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.table.UserInfoData;
import rmuti.askexpert.model.table.UserName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserNameController {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserInfoRepository userInfoRepository;

    public UserNameController() {
    }

    @PostMapping("/edituserinfo")
    public Object edituserinfo(@RequestBody UserInfoData info) {
        String userId = tokenService.userId();
        APIResponse res = new APIResponse();
        Optional<UserInfoData> opt_userinfo = userInfoRepository.findById(userId);
        info.setUserInfoId(userId);
        info.setProfilePic("no_profile_pic.png");
        userInfoRepository.save(info);
        res.setData(info);
        return res;
    }

    @PostMapping("/editimgprofile")
    public Object editimgprofile(@RequestPart MultipartFile file) throws IOException {
        String userId = tokenService.userId();
        Optional<UserInfoData> opt_userinfo = userInfoRepository.findById(userId);
        if (opt_userinfo.isEmpty()) {
            throw UserException.nouserinfo();
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

        Object res = new Response().ok("upload success", "img", img);

        opt_userinfo.get().setProfilePic(imgName);
        userInfoRepository.save(opt_userinfo.get());
        return res;
    }

    @PostMapping("/register")
    public Object register(@RequestBody UserName userName) throws BaseException {
        //create UserName
        //System.out.printf("dataGetRegistre: "+userName.toString());
        APIResponse res = new APIResponse();
        userName.setPassWordFb("0");
        userName.setPassWordGoogle("0");
        userName.setPassWord(passwordEncoder.encode(userName.getPassWord()));
        userNameRepository.save(userName);

        //create UserInfo
        UserInfoData info = new UserInfoData();
        info.setUserInfoId(userName.getUserId());
        info.setUserName(userName.getUserId());
        info.setFirstName("FN : "+userName.getEmail());
        info.setLastName("LN : "+userName.getEmail());
        info.setToken(0.0);
        info.setProfilePic("no_profile_pic.png");
        userInfoRepository.save(info);

        res.setData(tokenService.tokenize(Optional.of(userName)));
        return res;
    }

    @PostMapping("/login")
    public Object login(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), opt.get().getPassWord())) {
                res.setData(tokenService.tokenize(opt));
            } else {
                throw UserException.accessDenied();
            }
        } else {
            throw UserException.accessDenied();
        }
        //Optional<UserInfoData> optionalUserInfoData = userInfoRepository.findById(opt.get().getUserId());
        if (!userInfoRepository.existsByUserInfoId(opt.get().getUserId())) {
            res.setMessage("register");
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping("/loginfb")
    public Object loginfb(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        UserName fbregister = new UserName();
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), opt.get().getPassWordFb())) {
                res.setData(tokenService.tokenize(opt));
            } else if (opt.get().getPassWordFb().equals("0")) {
                fbregister = opt.get();
                fbregister.setPassWordFb(passwordEncoder.encode(user.getPassword()));
                userNameRepository.save(fbregister);
            } else {
                throw UserException.accessDenied();
            }
        } else {
            fbregister = new UserName();
            fbregister.setEmail(user.getEmail());
            fbregister.setPassWordFb("0");
            fbregister.setPassWordGoogle("0");
            fbregister.setPassWord("0");
            fbregister.setPassWordFb(passwordEncoder.encode(user.getPassword()));
            userNameRepository.save(fbregister);
            res.setMessage("register");
            res.setData(tokenService.tokenize(Optional.of(fbregister)));
        }
        //Optional<UserInfoData> optionalUserInfoData = userInfoRepository.findById(opt.get().getUserId());
        if (!userInfoRepository.existsByUserInfoId(fbregister.getUserId())) {
            res.setMessage("register");
        }

        return ResponseEntity.ok(res);
    }

    @PostMapping("/logingoogle")
    public Object logingoogle(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        UserName googleregister = new UserName();
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), opt.get().getPassWordGoogle())) {
                res.setData(tokenService.tokenize(opt));
            } else if (opt.get().getPassWordGoogle().equals("0")) {
                googleregister = opt.get();
                googleregister.setPassWordGoogle(passwordEncoder.encode(user.getPassword()));
                userNameRepository.save(googleregister);
            } else {
                throw UserException.accessDenied();
            }
        } else {
            googleregister = new UserName();

            googleregister.setEmail(user.getEmail());
            googleregister.setPassWordFb("0");
            googleregister.setPassWordGoogle("0");
            googleregister.setPassWord("0");
            googleregister.setPassWordFb(passwordEncoder.encode(user.getPassword()));
            userNameRepository.save(googleregister);
            res.setMessage("register");
            res.setData(tokenService.tokenize(Optional.of(googleregister)));
        }
        //Optional<UserInfoData> optionalUserInfoData = userInfoRepository.findById(googleregister.getUserId());
        if (!userInfoRepository.existsByUserInfoId(googleregister.getUserId())) {
            res.setMessage("register");
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/findall")
    public Object findAll() throws BaseException {
        APIResponse res = new APIResponse();
        List<UserName> data = userNameRepository.findAll();
        res.setData(data);
        return ResponseEntity.ok(res);

    }

    @PostMapping("/checkJWT")
    public Object checkJWT(@RequestHeader String Authorization) throws BaseException {
        System.out.println("userId : " + tokenService.userId());
        System.out.println("author : " + tokenService.author());
        APIResponse res = new APIResponse();
        res.setData(tokenService.GetuserformJWT());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/refreshJWT")
    public Object refreshJWT(@RequestHeader String Authorization) throws BaseException {
        System.out.println("userId : " + tokenService.userId());
        System.out.println("author : " + tokenService.author());
        APIResponse res = new APIResponse();
        res.setData(tokenService.GetuserformJWT());
        return ResponseEntity.ok(res);
    }


    public static String uploadDirectory = System.getProperty("user.dir");

    @PostMapping("/uploadProfile")
    public Object uploadpic(@RequestPart MultipartFile file) throws IOException {
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

        Object res = new Response().ok("upload success", "img", img);
        return res;
    }
}
