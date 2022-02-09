package rmuti.askexpert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.req.ReqLogin;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.UserNameRepository;
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

    @Autowired
    private TokenService tokenService;

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
    public Object login(@RequestBody ReqLogin user) throws BaseException {
        APIResponse res = new APIResponse();
        Optional<UserName> opt = userNameRepository.findByEmail(user.getEmail());
        if (opt.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), opt.get().getPassWord())) {
                res.setData("Pass");
                res.setData( tokenService.tokenize(opt));
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
    @PostMapping("/checkJWT")
    public Object checkJWT(@RequestHeader String Authorization) throws BaseException {
        System.out.println("userId : "+ tokenService.userId());
        System.out.println("author : "+ tokenService.author());
        APIResponse res  = new APIResponse();
        res.setData(tokenService.GetuserformJWT());
        return ResponseEntity.ok(res);
    }
}
