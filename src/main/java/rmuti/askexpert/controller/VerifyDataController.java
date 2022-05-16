package rmuti.askexpert.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.mapper.ResVerifyMapper;
import rmuti.askexpert.model.repo.ExpertGroupDataRepository;
import rmuti.askexpert.model.repo.ImageRepository;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.repo.VerifyDataRepository;
import rmuti.askexpert.model.request.ReqVerifySendData;
import rmuti.askexpert.model.request.ReqVerifyUpdateData;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.ResVerify;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.ExpertGroupListData;
import rmuti.askexpert.model.table.ImageData;
import rmuti.askexpert.model.table.UserInfoData;
import rmuti.askexpert.model.table.VerifyData;

@RestController
@RequestMapping("/verify")
public class VerifyDataController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private VerifyDataRepository verifyDataRepository;

    @Autowired
    private ResVerifyMapper resVerifyMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ExpertGroupDataRepository expertGroupDataRepository;


    public ResVerify verifyCreateDisplay(ResVerify resVerify) {

        Optional<UserInfoData> optUserInfo = userInfoRepository.findById(resVerify.getVerifyFrom());
        if (optUserInfo.isPresent()) {
            resVerify.setUserInfoData(optUserInfo.get());
            Optional<ExpertGroupListData> expertGroupListData = expertGroupDataRepository.findById(resVerify.getVerifyExpert());
            resVerify.setExpertGroupListData(expertGroupListData.get());
        }
        List<ImageData> optImage = imageRepository.findByImgContentId(resVerify.getVerifyId());
        if (optImage.size() > 0) {
            for (ImageData imageData : optImage) {
                resVerify.setImageData(imageData);
            }
        }
        return resVerify;
    }


    // sendDataToVerify
    @PostMapping("/add")
    public Object addVerifyData(@RequestBody ExpertGroupListData expertGroupListData, @RequestHeader String Authorization)
            throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        VerifyData optVerifyData = new VerifyData();
        Optional<VerifyData> updateVerifyData = verifyDataRepository.findByVerifyFrom(userId);

        if (updateVerifyData.isPresent()) {
            updateVerifyData.get().setVerifyExpert(expertGroupListData.getExpertGroupId());
            updateVerifyData.get().setVerifyStatus('W');
            updateVerifyData.get().setVerifyPassOf("none");
            verifyDataRepository.save(updateVerifyData.get());
            res.setData(updateVerifyData.get());
        } else {
            optVerifyData.setVerifyFrom(userId);
            optVerifyData.setVerifyExpert(expertGroupListData.getExpertGroupId());
            optVerifyData.setVerifyStatus('W');
            optVerifyData.setVerifyPassOf("none");
            verifyDataRepository.save(optVerifyData);
            res.setData(optVerifyData);
        }

        return res;
    }

    //ViewDataToVerifyAll
    @PostMapping("/findAll")
    public Object findAllVerifyData() throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        List<ResVerify> resVerifyList = resVerifyMapper.toListResVerify(verifyDataRepository.findAll());
        for (ResVerify resVerify : resVerifyList) {
            resVerify = verifyCreateDisplay(resVerify);
        }
        res.setData(resVerifyList);
        return res;
    }

    @PostMapping("/findWaitList")
    public Object findWaitList() throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        List<ResVerify> resVerifyList = resVerifyMapper.toListResVerify(verifyDataRepository.findByVerifyStatus('W'));
        for (ResVerify resVerify : resVerifyList) {
            resVerify = verifyCreateDisplay(resVerify);
        }
        res.setData(resVerifyList);
        return res;
    }


    // ViewDataToVerifyById
    @PostMapping("/findById")
    public Object findByIdVerifyData(@RequestBody String verifyId) throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        Optional<VerifyData> optVerifyData = verifyDataRepository.findById(verifyId);
        ResVerify resVerify = resVerifyMapper.toResVerify(optVerifyData.get());
        resVerify = verifyCreateDisplay(resVerify);
        res.setData(resVerify);
        return res;
    }

    @PostMapping("/findMyVerify")
    public Object findMyVerify() throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<VerifyData> optVerifyData = verifyDataRepository.findByVerifyPassOf(userId);
        res.setData(optVerifyData);
        return res;
    }


    //updateDataToVerify
    @PostMapping("/update")
    public Object updateVerifyData(@RequestBody ReqVerifyUpdateData reqVerifyUpdateData, @RequestHeader String Authorization) throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        //LOAD
        Optional<VerifyData> optVerifyData = verifyDataRepository.findById(reqVerifyUpdateData.getVerifyId());
        Optional<UserInfoData> optUserInfoData = userInfoRepository.findById(reqVerifyUpdateData.getVerifyUserId());

        //UPDATE SET DATA
        if(reqVerifyUpdateData.isVerified()){
            optVerifyData.get().setVerifyStatus('A');
            optUserInfoData.get().setVerifyStatus(true);
            optVerifyData.get().setVerifyPassOf(userId);
        }
        else {
            optUserInfoData.get().setVerifyStatus(false);
            optVerifyData.get().setVerifyStatus('D');
        }

        //SAVE
        verifyDataRepository.save(optVerifyData.get());
        userInfoRepository.save(optUserInfoData.get());

        res.setData(optVerifyData.get());
        return res;
    }
}
