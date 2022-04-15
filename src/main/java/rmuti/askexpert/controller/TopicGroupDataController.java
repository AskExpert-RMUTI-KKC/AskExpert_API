package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.TopicGroupDataRepository;
import rmuti.askexpert.model.request.ReqTopicGroupDataAdd;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.TopicGroupListData;

@RestController
@RequestMapping("/topicGroupList")
public class TopicGroupDataController {
    @Autowired
    private TopicGroupDataRepository topicGroupDataRepository;

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
        topicGroupDataRepository.save(topicGroupListData);
        return res;
    }


    //Remove
    @PostMapping("/remove")
    public Object remove(@RequestBody String topicGroupId)throws BaseException{
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse(); 
        topicGroupDataRepository.deleteById(topicGroupId);
        res.setData("remove topicGroupId : " + topicGroupId);
        return res; 
    }
    //ListView
    @PostMapping("/findAll")
    public Object findAll()throws BaseException{
        APIResponse res = new APIResponse(); 
        res.setData(topicGroupDataRepository.findAll());
        return res; 
    }

    //findById
    //update
}
