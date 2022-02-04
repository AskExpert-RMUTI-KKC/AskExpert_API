package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.services.UserNameRepository;
import rmuti.askexpert.model.table.UserName;


@RestController
@RequestMapping("/user-name")
public class UserNameController {
    @Autowired
    private UserNameRepository userNameRepository;

    @PostMapping("/save")
    public  Object save(@RequestBody UserName userName){
        APIResponse res = new APIResponse();
        userNameRepository.save(userName);
        return res;
    }
}
