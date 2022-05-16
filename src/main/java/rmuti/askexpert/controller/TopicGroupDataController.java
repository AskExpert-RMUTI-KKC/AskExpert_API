package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.TopicGroupListDataRepository;
import rmuti.askexpert.model.request.ReqTopicGroupDataAdd;
import rmuti.askexpert.model.request.ReqTopicGroupDataUpdate;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.TopicGroupListData;

import java.util.Optional;

@RestController
@RequestMapping("/topicGroupList")
public class TopicGroupDataController {
    @Autowired
    private TopicGroupListDataRepository topicGroupDataRepository;

    @Autowired
    private TokenService tokenService;

    //Add
    @PostMapping("/add")
    public Object add(@RequestBody ReqTopicGroupDataAdd reqTopicGroupDataAdd)throws BaseException{
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        TopicGroupListData topicGroupListData = new TopicGroupListData();
        topicGroupListData.setTopicGroupPath(reqTopicGroupDataAdd.getTopicGroupPath());
        topicGroupListData.setTopicGroupStatus(1);
        topicGroupDataRepository.save(topicGroupListData);
        res.setData(topicGroupListData);
        return res;
    }


    //Remove
    @PostMapping("/remove")
    public Object remove(@RequestBody String topicGroupId)throws BaseException{
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        Optional<TopicGroupListData> optionalTopicGroupListData = topicGroupDataRepository.findById(topicGroupId);
        optionalTopicGroupListData.get().setTopicGroupStatus(0);
        topicGroupDataRepository.save(optionalTopicGroupListData.get());
        res.setData(optionalTopicGroupListData.get());
        return res; 
    }
    //ListView
    @PostMapping("/findAll")
    public Object findAll()throws BaseException{
        APIResponse res = new APIResponse(); 
        res.setData(topicGroupDataRepository.findByTopicGroupStatus(1));
        return res; 
    }

    //findById
    @PostMapping("/findById")
    public Object findById(@RequestBody String topicGroupId)throws BaseException{
        APIResponse res = new APIResponse();
        res.setData(topicGroupDataRepository.findById(topicGroupId));
        return res;
    }
    //update
    @PostMapping("/update")
    public Object update(@RequestBody ReqTopicGroupDataUpdate reqTopicGroupDataUpdate)throws BaseException{
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        Optional<TopicGroupListData> topicGroupListDataOptional = topicGroupDataRepository.findById(
                reqTopicGroupDataUpdate.getTopicGroupId()
        );
        APIResponse res = new APIResponse();
        if(topicGroupListDataOptional.isPresent())
        {
            topicGroupListDataOptional.get().setTopicGroupPath(reqTopicGroupDataUpdate.getTopicGroupPath());
            topicGroupListDataOptional.get().setTopicGroupStatus(reqTopicGroupDataUpdate.getTopicGroupStatus());
            topicGroupDataRepository.save(topicGroupListDataOptional.get());
        }
        else{
            //throw
        }
        return res;
    }
}
