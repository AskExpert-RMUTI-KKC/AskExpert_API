package rmuti.askexpert.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import rmuti.askexpert.model.config.BaseUrlFile;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.FileException;
import rmuti.askexpert.model.mapper.ResVerifyMapper;
import rmuti.askexpert.model.repo.VerifyDataRepository;
import rmuti.askexpert.model.request.ReqVerifySendData;
import rmuti.askexpert.model.request.ReqVerifyUpdateData;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.AResponse;
import rmuti.askexpert.model.response.ResVerify;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.VerifyData;

import static rmuti.askexpert.model.config.PathConfig.uploadDirectory;


@RestController
@RequestMapping("/verify")
public class VerifyDataController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private VerifyDataRepository verifyDataRepository;

    @Autowired
    private ResVerifyMapper resVerifyMapper;

    // sendDataToVerify
    @PostMapping("/add")
    public Object addVerifyData(@RequestBody ReqVerifySendData verifyData,@RequestHeader String Authorization)
        throws BaseException { 
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        VerifyData optVerifyData = new VerifyData();

        optVerifyData.setVerifyFrom(userId);
        optVerifyData.setVerifyExpert(verifyData.getVerifyExpert());
        optVerifyData.setVerifyStatus('W');
        optVerifyData.setVerifyPassOf("none");
        verifyDataRepository.save(optVerifyData);
        res.setData(optVerifyData);
        return  res;
    }
     
    //ViewDataToVerifyAll
    @PostMapping("/findAll")
    public Object findAllVerifyData()throws BaseException {
        APIResponse res = new APIResponse();
        List<ResVerify> resVerifyList = resVerifyMapper.toListResVerify(verifyDataRepository.findAll());
        res.setData(resVerifyList);
        return res;
    }


    // ViewDataToVerifyById
    @PostMapping("/findById")
    public Object findByIdVerifyData(@RequestBody String verifyId)throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<VerifyData> optVerifyData = verifyDataRepository.findById(verifyId);
        res.setData(optVerifyData.get());
        return res;
    }

    @PostMapping("/findMyVerify")
    public Object findMyVerify()throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<VerifyData> optVerifyData = verifyDataRepository.findByVerifyPassOf(userId);
        res.setData(optVerifyData);
        return res;
    }


    //updateDataToVerify
    @PostMapping("/update")
    public Object updateVerifyData(@RequestBody ReqVerifyUpdateData reqVerifyUpdateData,@RequestHeader String Authorization)throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<VerifyData> optVerifyData = verifyDataRepository.findById(reqVerifyUpdateData.getVerifyId());
        optVerifyData.get().setVerifyStatus(reqVerifyUpdateData.getStatus());
        optVerifyData.get().setVerifyPassOf(userId);
        verifyDataRepository.save(optVerifyData.get());
        res.setData(optVerifyData.get());
        return res;
    }
}
