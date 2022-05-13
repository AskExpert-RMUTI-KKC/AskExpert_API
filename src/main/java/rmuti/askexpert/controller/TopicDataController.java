package rmuti.askexpert.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.TopicException;
import rmuti.askexpert.model.mapper.ResTopicMapper;
import rmuti.askexpert.model.repo.*;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.ResTopic;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/topic")
public class TopicDataController {

    private TopicDataRepository topicDataRepository;
    private TokenService tokenService;
    private ResTopicMapper resTopicMapper;
    private UserInfoRepository userInfoRepository;
    private LikeDataRepository likeDataRepository;
    private ImageRepository imageRepository;
    private TopicGroupListDataRepository topicGroupListDataRepository;

    private ExpertGroupDataRepository expertGroupDataRepository;


    public ResTopic topicBuildResponse(ResTopic dataIndex, String userIdLike) {

        String userId = dataIndex.getTopicUserId();
        Optional<UserInfoData> userInfoData = userInfoRepository.findById(userId);
        if (userInfoData.isPresent()) {
            dataIndex.setUserInfoData(resTopicMapper.toResTopicUserInfo(userInfoData.get()));
            Optional<ExpertGroupListData> expertGroupListData = expertGroupDataRepository.findById(userInfoData.get().getExpertGroupId());
            dataIndex.getUserInfoData().setExpert(expertGroupListData.get().getExpertPath());
        }
        String likeContentId = dataIndex.getTopicId();
        Optional<LikeData> likeData = likeDataRepository
                .findByLikeUserIdAndLikeContentId(
                        userIdLike,
                        likeContentId);
        if (likeData.isPresent()) {
            dataIndex.setLikeStatus(likeData.get().getLikeStatus());
        }
        List<ImageData> topicImgData = imageRepository.findByImgContentId(dataIndex.getTopicId());
        if (!topicImgData.isEmpty()) {
            dataIndex.setTopicImg(topicImgData);
        }
        Optional<TopicGroupListData> topicGroupListDataOptional = topicGroupListDataRepository.findById(dataIndex.getTopicGroupId());
        if (!topicGroupListDataOptional.isEmpty()) {
            dataIndex.setTopicGroupName(topicGroupListDataOptional.get().getTopicGroupPath());
        }

        return dataIndex;
    }

    @PostMapping("/add")
    public Object addTopic(@RequestBody TopicData topicData) {
        APIResponse res = new APIResponse();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //topicData.setTopicCreateDate(timeStamp);
        topicData.setTopicUserId(tokenService.userId());
        topicData.setTopicReportStatus(0);
        // TODO: AFK
        // topicData.setUserInfoData(userInfoRepository.findById(tokenService.userId()).get());
        topicDataRepository.save(topicData);
        res.setData(topicData);
        return res;
    }

    @PostMapping("/remove")
    public Object removeTopic(@RequestParam String topicIdRemove)
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

    @PostMapping("/findById")
    public Object readTopic(@RequestBody String contentId) {
        Optional<TopicData> topicData = topicDataRepository.findById(contentId);
        if (topicData.isPresent()) {
            topicData.get().setTopicReadCount(topicData.get().getTopicReadCount() + 1);
            topicDataRepository.save(topicData.get());

        }

        String userIdLike = tokenService.userId();
        APIResponse res = new APIResponse();
        ResTopic data = resTopicMapper.toResTopic(topicDataRepository.findById(contentId).get());
        data = topicBuildResponse(data, userIdLike);
        res.setData(data);
        return data;
    }

    // @PostMapping("Read")

    @PostMapping("/findAll")
    public Object findAllTopic() {
        String userIdLike = tokenService.userId();
        APIResponse res = new APIResponse();
        List<ResTopic> data = resTopicMapper
                .toListResTopic(
                        topicDataRepository
                                .findAllByTopicReportStatus(0)
                );
        for (ResTopic dataIndex : data) {
            dataIndex = topicBuildResponse(dataIndex, userIdLike);
//            String userId = dataIndex.getTopicUserId();
//            Optional<UserInfoData> userInfoData = userInfoRepository.findById(userId);
//            if (userInfoData.isPresent()) {
//                dataIndex.setUserInfoData(resTopicMapper.toResTopicUserInfo(userInfoData.get()));
//                Optional<ExpertGroupListData> expertGroupListData = expertGroupDataRepository.findById(userInfoData.get().getExpertGroupId());
//                dataIndex.getUserInfoData().setExpert(expertGroupListData.get().getExpertPath());
//            }
//            String likeContentId = dataIndex.getTopicId();
//            Optional<LikeData> likeData = likeDataRepository
//                    .findByLikeUserIdAndLikeContentId(
//                            userIdLike,
//                            likeContentId);
//            if (likeData.isPresent()) {
//                dataIndex.setLikeStatus(likeData.get().getLikeStatus());
//            }
//            List<ImageData> topicImgData = imageRepository.findByImgContentId(dataIndex.getTopicId());
//            if (!topicImgData.isEmpty()) {
//                dataIndex.setTopicImg(topicImgData);
//            }
//            Optional<TopicGroupListData> topicGroupListDataOptional = topicGroupListDataRepository.findById(dataIndex.getTopicGroupId());
//            if (!topicGroupListDataOptional.isEmpty()) {
//                dataIndex.setTopicGroupName(topicGroupListDataOptional.get().getTopicGroupPath());
//            }
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

    @PostMapping("/findByText")
    public Object findByText(@RequestBody String text) {
        APIResponse res = new APIResponse();
        List<TopicData> topicDataList = topicDataRepository
                .findByTopicHeadlineContainingOrTopicCaptionContaining(
                        text,
                        text
                );
        res.setData(topicDataList);
        return res;
    }
}
