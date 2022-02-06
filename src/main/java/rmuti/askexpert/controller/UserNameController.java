package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.exception.BaseException;
import rmuti.askexpert.exception.UserException;
import rmuti.askexpert.model.req.ReqLogin;
import rmuti.askexpert.model.services.CommentDataRepository;
import rmuti.askexpert.model.services.TopicDataRepository;
import rmuti.askexpert.model.services.UserNameRepository;
import rmuti.askexpert.model.table.UserName;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserNameController {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserNameController() {
    }

    @PostMapping("/register")
    public Object register(@RequestBody UserName userName) throws BaseException {
        APIResponse res = new APIResponse();
        userName.setPassWord(passwordEncoder.encode(userName.getPassWord()));
        userNameRepository.save(userName);
        return userName;
    }

    @PostMapping("/login")
    public Object login(@RequestBody ReqLogin reqLogin) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(reqLogin.getEmail());
        if (opt.isPresent()) {
            if (passwordEncoder.matches(reqLogin.getPassword(), opt.get().getPassWord())) {
                res.setData("Pass");
            } else {
                throw UserException.accessDenied();
            }
        } else {
            throw UserException.accessDenied();
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("/findAllUser")
    public Object findAll() throws BaseException {
        APIResponse res = new APIResponse();
        List<UserName> data = userNameRepository.findAll();
        res.setData(data);
        return ResponseEntity.ok(res);

    }
}
