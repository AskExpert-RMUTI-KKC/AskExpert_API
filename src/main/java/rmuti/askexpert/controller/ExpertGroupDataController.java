package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.ExpertGroupDataRepository;
import rmuti.askexpert.model.request.ReqExpertDataAdd;
import rmuti.askexpert.model.request.ReqExpertDataUpdate;
import rmuti.askexpert.model.request.ReqTopicGroupDataUpdate;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.ExpertGroupListData;
import rmuti.askexpert.model.table.TopicGroupListData;

import java.util.Optional;

@RestController
@RequestMapping("/expertGroupList")
public class ExpertGroupDataController {
    @Autowired
    private ExpertGroupDataRepository expertGroupDataRepository;

    @Autowired
    private TokenService tokenService;

    //Add
    @PostMapping("/add")
    public Object add(@RequestBody ReqExpertDataAdd reqExpertDataAdd) throws BaseException {
        if(!tokenService.isAdmin()){
            throw UserException.youarenotadmin();
        } 
        APIResponse res = new APIResponse();
        ExpertGroupListData expert = new ExpertGroupListData();    
        expert.setExpertPath(reqExpertDataAdd.getExpertPath());
        expert.setExpertStatus(1);
        expertGroupDataRepository.save(expert);
        res.setData(expert);
        res.setData(expert);
        return res;
    }
    //Remove
    @PostMapping("/remove")
    public Object remove(@RequestBody String expertId) throws BaseException {
        if(!tokenService.isAdmin()){
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        Optional<ExpertGroupListData> optionalExpertGroupListData = expertGroupDataRepository.findById(expertId);
        optionalExpertGroupListData.get().setExpertStatus(0);
        expertGroupDataRepository.save(optionalExpertGroupListData.get());
        res.setData(optionalExpertGroupListData.get());
        return res;
    }


    //ListView
    @PostMapping("/findAll")
    public Object findAll() throws BaseException {
        APIResponse res = new APIResponse(); 
        res.setData(expertGroupDataRepository.findByExpertStatus(1));
        return res;
    }

    //findById
    @PostMapping("/findById")
    public Object findById(@RequestBody String expertGroupId)throws BaseException{
        APIResponse res = new APIResponse();
        res.setData(expertGroupDataRepository.findById(expertGroupId));
        return res;
    }
    //update
    @PostMapping("/update")
    public Object update(@RequestBody ReqExpertDataUpdate reqExpertDataUpdate)throws BaseException{
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        Optional<ExpertGroupListData> expertGroupListData = expertGroupDataRepository.findById(
                reqExpertDataUpdate.getExpertGroupId()
        );
        APIResponse res = new APIResponse();
        if(expertGroupListData.isPresent())
        {
            expertGroupListData.get().setExpertPath(reqExpertDataUpdate.getExpertPath());
            expertGroupDataRepository.save(expertGroupListData.get());
            res.setData(expertGroupListData.get());
        }
        else{
            //throw
        }
        return res;
    }
}
