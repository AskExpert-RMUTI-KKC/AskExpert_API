package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.repo.ChatContactRepository;
import rmuti.askexpert.model.repo.ChatMesRepository;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.ChatContactData;
import rmuti.askexpert.model.table.ChatMesData;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatContactMesDataController {



    @Autowired
    ChatContactRepository chatContactRepository;

    @Autowired
    ChatMesRepository chatMesRepository;


    @Autowired
    private TokenService tokenService;


    //firstContract
    @PostMapping("/firstContact")
    public Object firstContact(@RequestBody String chatRx) throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();

        //findConcat
        Optional<ChatContactData> chatContactTx = chatContactRepository.findByChatTxAndChatRx(userId,chatRx);
        //Optional<ChatContactData> chatContactRx = chatContactRepository.findByChatTxAndChatRx(chatRx,userId);


        //if null Create
        if(chatContactTx.isEmpty() && chatContactRx.isEmpty())
        {
            ChatContactData newChatContactTx =  new ChatContactData();
            newChatContactTx.setChatTx(userId);
            newChatContactTx.setChatRx(chatRx);
            newChatContactTx.setChatReadStatus(false);
//             ChatContactData newChatContactRx =  new ChatContactData();
//            newChatContactRx.setChatTx(chatRx);
//            newChatContactRx.setChatRx(userId);
//            newChatContactRx.setChatReadStatus(false);
            chatContactRepository.save(newChatContactTx);
            //chatContactRepository.save(newChatContactRx);
             res.setData(newChatContactTx);
        }
        return res;
    }

    @PostMapping("/myContact")
    public Object myContact() throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<ChatContactData> chatContactTx = chatContactRepository.findByChatTx(userId);
        res.setData(chatContactTx);
        return res;
    }

    @PostMapping("/itRead")
    public Object itRead(@RequestBody String chatRx) throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<ChatContactData> chatContactRx = chatContactRepository.findByChatTxAndChatRx(userId,chatRx);
        if(chatContactRx.isPresent()){
            chatContactRx.get().setChatReadStatus(true);
            chatContactRepository.save(chatContactRx.get());
        }
        return res;
    }

    //AddMes-SendMes-CreateMes

    @PostMapping("/sendMes")
    public Object send(@RequestBody ChatMesData temp)throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        ChatMesData mesData = new ChatMesData();
        mesData.setChatTx(userId);
        mesData.setChatRx(temp.getChatRx());
        mesData.setChatMes(temp.getChatMes());
        chatMesRepository.save(mesData);
        res.setData(mesData);
        return res;

    }

    @PostMapping("/chatMesWithRx")
    public Object chatMesWithRx(@RequestBody String chatRx)throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<ChatMesData> chatMesDataList =  ;
        res.setData(chatMesDataList);
        return res;

    }
}
