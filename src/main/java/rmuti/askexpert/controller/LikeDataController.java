package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.repo.LikeDataRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.LikeData;

import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeDataController {
    @Autowired
    LikeDataRepository likeDataRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/setStatus")
    public Object setStatus (@RequestBody LikeData likeData,@RequestHeader String Authorization) throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        likeData.setLikeOwnerId(userId);
        likeDataRepository.save(likeData);
        res.setData(likeData);
        return res;
    }

    @PostMapping("/getStatus")
    public Object getStatus(@RequestParam String contentId,@RequestHeader String Authorization) throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        System.out.printf("userId : "+ userId);
        Optional<LikeData> opt = likeDataRepository.findByLikeOwnerIdAndLikeContentId(userId,contentId);
        res.setData(opt);
        return res;
    }

    @PostMapping("/count")
    public Object count(@RequestParam boolean status) throws BaseException{
        APIResponse res = new APIResponse();
        long count = 0;
//        if(status){
            //count = likeDataRepository.countAllByStatusIsTrue();
//        }else {
//            count = likeDataRepository.countAllByContentIsFalse();
//        }
        res.setData(count);
        return res;
    }


}
