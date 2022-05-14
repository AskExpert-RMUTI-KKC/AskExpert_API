package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rmuti.askexpert.model.config.BaseUrlFile;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.FileException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.mapper.ResTopicMapper;
import rmuti.askexpert.model.repo.ExpertGroupDataRepository;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.request.ReqRegister;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.AResponse;
import rmuti.askexpert.model.response.ResTopicUserInfo;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.request.ReqLogin;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.table.ExpertGroupListData;
import rmuti.askexpert.model.table.UserInfoData;
import rmuti.askexpert.model.table.UserName;
import rmuti.askexpert.model.table.VerifyData;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    private ResTopicMapper resTopicMapper;

    @Autowired
    private ExpertGroupDataRepository expertGroupDataRepository;

    @Value("${app.token.passWordForAdmin}")
    private String passWordForAdmin;

    private UserInfoData createUserInfo(String userId, String eMail) {
        //create UserInfo
        UserInfoData info = new UserInfoData();
        info.setUserInfoId(userId);
        info.setUserName(eMail);
        info.setFirstName("FN : " + eMail);
        info.setLastName("LN : " + eMail);
        info.setToken(0.0);
        info.setProfilePic("no_profile_pic.png");
        info.setVerifyStatus(false);
        info.setExpertGroupId("none");
        info.setUserCaption("none");
        info.setLikeCount(0);
        info.setTokenCount(0.0);
        userInfoRepository.save(info);
        return info;
    }

    //Add UserName-UserInfo
    @PostMapping("/register")
    public Object register(@RequestBody ReqRegister reqRegister) throws BaseException {
        Optional<UserName> opt = userNameRepository.findByEmail(reqRegister.getEmail());
        if (opt.isPresent()) {
            throw UserException.createEmailDuplicated();
        }
        APIResponse res = new APIResponse();

        //create UserName 
        UserName userName = new UserName();
        userName.setEmail(reqRegister.getEmail());
        userName.setPassWordFb("0");
        userName.setPassWordGoogle("0");
        userName.setPassWord(passwordEncoder.encode(reqRegister.getPassWord()));
        if (reqRegister.getPassWordForAdmin().toString().equals(passWordForAdmin)) {
            userName.setRole("ADMIN");
        } else {
            userName.setRole("USER");
        }
        userNameRepository.save(userName);

        //Create userInfoData
        UserInfoData userInfoData = createUserInfo(userName.getUserId(), userName.getEmail());

        res.setData(tokenService.tokenize(Optional.of(userName)));
        return res;
    }

    @PostMapping("/update")
    public Object updateUserInfo(@RequestBody UserInfoData userInfoData) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserInfoData> duplicated = userInfoRepository.findByUserName(userInfoData.getUserName());
        if(duplicated.isPresent())
        {
            if(tokenService.userId() != duplicated.get().getUserInfoId())
            throw UserException.createUserNameDuplicated();
        }
        Optional<UserInfoData> userInfoDataOptional = userInfoRepository.findById(tokenService.userId());
        userInfoDataOptional.get().setUserName(userInfoData.getUserName());
        userInfoDataOptional.get().setFirstName(userInfoData.getFirstName());
        userInfoDataOptional.get().setLastName(userInfoData.getLastName());
        userInfoDataOptional.get().setUserCaption(userInfoData.getUserCaption());
        if (!userInfoData.getExpertGroupId().equals(userInfoDataOptional.get().getExpertGroupId())) {
            userInfoDataOptional.get().setExpertGroupId(userInfoData.getExpertGroupId());
            userInfoDataOptional.get().setVerifyStatus(false);
        }
        userInfoRepository.save(userInfoDataOptional.get());
        res.setData(userInfoDataOptional.get());
        return res;
    }


    //findByEMail-passWord
    @PostMapping("/login")
    public Object login(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassWord(), opt.get().getPassWord())) {
                res.setData(tokenService.tokenize(opt));
            } else {
                throw UserException.accessDenied();
            }
        } else {
            throw UserException.accessDenied();
        }
        //Optional<UserInfoData> optionalUserInfoData = userInfoRepository.findById(opt.get().getUserId());
        if (!userInfoRepository.existsByUserInfoId(opt.get().getUserId())) {
            //Create userInfoData
            UserInfoData userInfoData = createUserInfo(opt.get().getUserId(), opt.get().getEmail());
            res.setMessage("register");
        }
        return ResponseEntity.ok(res);
    }

    //Add-FindBy_FB
    @PostMapping("/loginFb")
    public Object loginFb(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        UserName fbregister = new UserName();
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassWord(), opt.get().getPassWordFb())) {
                res.setData(tokenService.tokenize(opt));
            } else if (opt.get().getPassWordFb().equals("0")) {
                fbregister = opt.get();
                fbregister.setPassWordFb(passwordEncoder.encode(user.getPassWord()));
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
            fbregister.setPassWordFb(passwordEncoder.encode(user.getPassWord()));
            userNameRepository.save(fbregister);
            //Create userInfoData
            UserInfoData userInfoData = createUserInfo(fbregister.getUserId(), fbregister.getEmail());
            res.setMessage("register");
            res.setData(tokenService.tokenize(Optional.of(fbregister)));
        }
        //Optional<UserInfoData> optionalUserInfoData = userInfoRepository.findById(opt.get().getUserId());
        if (!userInfoRepository.existsByUserInfoId(fbregister.getUserId())) {
            UserInfoData userInfoData = createUserInfo(fbregister.getUserId(), fbregister.getEmail());
            res.setMessage("register");
        }
        return ResponseEntity.ok(res);
    }

    //Add-FindBy_Gooole
    @PostMapping("/loginGoogle")
    public Object logingoogle(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        UserName googleRegister = new UserName();
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassWord(), opt.get().getPassWordGoogle())) {
                res.setData(tokenService.tokenize(opt));
            } else if (opt.get().getPassWordGoogle().equals("0")) {
                googleRegister = opt.get();
                googleRegister.setPassWordGoogle(passwordEncoder.encode(user.getPassWord()));
                userNameRepository.save(googleRegister);
            } else {
                throw UserException.accessDenied();
            }
        } else {
            googleRegister = new UserName();

            googleRegister.setEmail(user.getEmail());
            googleRegister.setPassWordFb("0");
            googleRegister.setPassWordGoogle("0");
            googleRegister.setPassWord("0");
            googleRegister.setPassWordFb(passwordEncoder.encode(user.getPassWord()));
            userNameRepository.save(googleRegister);
            UserInfoData userInfoData = createUserInfo(googleRegister.getUserId(), googleRegister.getEmail());
            res.setMessage("register");
            res.setData(tokenService.tokenize(Optional.of(googleRegister)));
        }
        if (!userInfoRepository.existsByUserInfoId(googleRegister.getUserId())) {
            UserInfoData userInfoData = createUserInfo(googleRegister.getUserId(), googleRegister.getEmail());
            res.setMessage("register");
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/findAll")
    public Object findAll() throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        List<UserName> data = userNameRepository.findAll();
        res.setData(data);
        return ResponseEntity.ok(res);

    }

    @PostMapping("/findById")
    public Object findById() throws BaseException{
        APIResponse res = new APIResponse();
        res.setData(userInfoRepository.findById(tokenService.userId()));
        return res;
    }

    @PostMapping("/findByUserId")
    public Object findById(@RequestBody String userId) throws BaseException{
        APIResponse res = new APIResponse();
        res.setData(userInfoRepository.findById(userId));
        return res;
    }

    @PostMapping("/findByText")
    public Object findByText(@RequestBody String text){
        APIResponse res = new APIResponse();
        res.setData(userInfoRepository.findByUserNameContainingOrFirstNameContainingOrLastNameContaining(
                text,
                text,
                text
        ));
        return  res;
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
        Optional<UserName> opt = userNameRepository.findByUserId(tokenService.userId());
        res.setData(tokenService.tokenize(opt));
        return ResponseEntity.ok(res);
    }

}
