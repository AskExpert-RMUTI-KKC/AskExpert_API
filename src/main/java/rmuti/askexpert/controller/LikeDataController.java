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
        likeData.setLikeOwner(userId);
        likeDataRepository.save(likeData);
        return res;
    }

    @PostMapping("/getStatus")
    public Object getStatus(@RequestParam String content,@RequestHeader String Authorization) throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<LikeData> opt = likeDataRepository.findByLikeIdAndLikeOwner(content,userId);
        return res;
    }

    @PostMapping("/count")
    public Object count(@RequestPart boolean status) throws BaseException{
        APIResponse res = new APIResponse();
        long count = 0;
        if(status){
            count = likeDataRepository.countAllByContentIsTrue();
        }else {
            count = likeDataRepository.countAllByContentIsFalse();
        }
        res.setData(count);
        return res;
    }
}
