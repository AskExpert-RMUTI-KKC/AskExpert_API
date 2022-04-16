package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.mapper.ResTopicMapper;
import rmuti.askexpert.model.repo.*;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.ResTopic;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.LikeData;
import rmuti.askexpert.model.table.TopicData;
import rmuti.askexpert.model.table.UserInfoData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topic")
public class TopicDataController {  
    @Autowired
    private TopicDataRepository topicDataRepository; 

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResTopicMapper resTopicMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LikeDataRepository likeDataRepository;

    @PostMapping("/add")
    public Object addTopic(@RequestBody TopicData topicData) {
        APIResponse res = new APIResponse();
        System.out.printf("userid : " + tokenService.userId());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        topicData.setTopicCreateDate(timeStamp);
        topicData.setTopicUserId(tokenService.userId());
        topicData.setTopicReportStatus(0);
        topicData.setTopicReport('R');
        // TODO: AFK
        // topicData.setUserInfoData(userInfoRepository.findById(tokenService.userId()).get());
        topicDataRepository.save(topicData);
        res.setData(topicData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeTopic(@RequestParam String topicIdRemove, @RequestHeader String Authorization)
            throws Exception {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<TopicData> opt = topicDataRepository.findByTopicId(topicIdRemove);
        System.out.println("topicIdRemove : " + topicIdRemove);
        System.out.println("userId : " + userId);
        System.out.println("UserId : " + opt.get().getTopicUserId());
        if (userId.equals(opt.get().getTopicUserId())) {
            topicDataRepository.deleteById(topicIdRemove);
        } else {
            throw TopicException.notyourtopic();
        }
        res.setData(opt);
        return res;
    }

    @PostMapping("read")
    public Object readTopic(@RequestParam String contentId) {
        Optional<TopicData> topicData = topicDataRepository.findById(contentId);
        topicData.get().setTopicReadCount(topicData.get().getTopicReadCount() + 1);
        topicDataRepository.save(topicData.get());
        return topicData;
    }

    // @PostMapping("Read")

    @PostMapping("/findAll")
    public Object findAllTopic() {
        APIResponse res = new APIResponse();
        List<ResTopic> data = resTopicMapper.toListResTopic(topicDataRepository.findAllByTopicReportStatus(0));
        for (ResTopic dataindex : data) {
            String userId = dataindex.getTopicUserId();
            Optional<UserInfoData> userInfoData = userInfoRepository.findById(userId);
            if (userInfoData.isPresent()) {
                dataindex.setUserInfoData(resTopicMapper.toResTopicUserInfo(userInfoData.get()));
            }
            String likeContentId = dataindex.getTopicId();
            Optional<LikeData> likeData = likeDataRepository.findByLikeUserIdAndLikeContentId(userId, likeContentId);
            if (likeData.isPresent()) {
                dataindex.setLikeStatus(likeData.get().getLikeStatus());
            }
        }
        res.setData(data);
        // checkNull
        if (data.isEmpty()) {
            data.add(new ResTopic());
        }
        return res;
    }

    @PostMapping("/findMyTopic")
    public Object findMyTopic(@RequestHeader String Authorization) {
        APIResponse res = new APIResponse();
        System.out.println("userID : " + tokenService.userId());
        List<TopicData> data = topicDataRepository
                .findAllByTopicUserIdOrderByTopicCreateDateDesc(tokenService.userId());
        res.setData(data);
        return res;
    }
}
