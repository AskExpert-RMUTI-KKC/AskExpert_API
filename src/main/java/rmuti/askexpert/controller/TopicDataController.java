package rmuti.askexpert.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.mapper.ResTopicMapper;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.response.ResTopic;
import rmuti.askexpert.model.services.TokenService;
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
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResTopicMapper resTopicMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;


    @PostMapping("/add")
    public Object addTopic(@RequestBody TopicData topicData) {
        APIResponse res = new APIResponse();
        System.out.printf("userid : " + tokenService.userId());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        topicData.setTopicCreateDate(timeStamp);
        topicData.setTopicOwnerId(tokenService.userId());
        //TODO: FK topicData.setUserInfoData(userInfoRepository.findById(tokenService.userId()).get());
        topicDataRepository.save(topicData);
        res.setData(topicData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeTopic(@RequestParam String topicIdRemove, @RequestHeader String Authorization) throws Exception {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<TopicData> opt = topicDataRepository.findByTopicId(topicIdRemove);
        System.out.println("topicIdRemove : " + topicIdRemove);
        System.out.println("userId : " + userId);
        System.out.println("OwnerId : " + opt.get().getTopicOwnerId());
        if (userId.equals(opt.get().getTopicOwnerId())) {
            topicDataRepository.deleteById(topicIdRemove);
        } else {
            throw TopicException.notyourtopic();
        }
        res.setData(opt);
        return res;
    }

    @PostMapping("/findAll")
    public Object findAllTopic() {
        APIResponse res = new APIResponse();
        //List<ResTopic> data = mapper.toListTopicResponse(topicDataRepository.findAll());
        //List<ResTopic> data =  topicDataRepository.findAll().stream().map(List);
        //List<ResTopic> data = mapper.map(topicDataRepository.findAll());
        //List<ResTopic> data = new ResTopicMap().toResTopicListMapper(topicDataRepository.findAll());
        List<ResTopic> data = resTopicMapper.toListResTopic(topicDataRepository.findAll());
        for(ResTopic dataindex : data){
            String id = dataindex.getTopicOwnerId();
            System.out.printf("\n dataindex.getTopicOwnerId : "+ id+"\n");
            Optional<UserInfoData> userInfoData = userInfoRepository.findById(id);
            dataindex.setUserInfoData(resTopicMapper.toResTopicUserInfo(userInfoData.get()));
        }
        res.setData(data);
        if(data.isEmpty()){
            data.add(new ResTopic());
        }
        return res;
    }

    @PostMapping("/findMyTopic")
    public Object findMyTopic(@RequestHeader String Authorization) {
        APIResponse res = new APIResponse();
        System.out.println("userID : " + tokenService.userId());
        List<TopicData> data = topicDataRepository.findAllByTopicOwnerIdOrderByTopicCreateDateDesc(tokenService.userId());
        res.setData(data);
        return res;
    }
}
