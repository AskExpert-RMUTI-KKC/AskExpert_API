package rmuti.askexpert.model.startup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rmuti.askexpert.model.repo.UserNameRepository;
import rmuti.askexpert.model.table.UserName;

import java.util.Optional;

@Component
public class UserAdmin {
    @Autowired
    private UserNameRepository userNameRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    public void addAdmin(ApplicationEvent event){
        UserName admin = new UserName();

        if(!userNameRepository.existsByEmail("Admin")){
            admin.setEmail("Admin");
            admin.setPassWordFb("0");
            admin.setPassWordGoogle("0");
            admin.setPassWord(passwordEncoder.encode("Admin"));
            admin.setRole("ADMIN");
            userNameRepository.save(admin);
        }


    }
}
