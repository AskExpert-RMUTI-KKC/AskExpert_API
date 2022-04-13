package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.mapper.ResCommentMapper;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.LikeDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.TransactionRepository;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.TransactionData;
import rmuti.askexpert.model.table.UserInfoData;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TopicDataRepository topicDataRepository;

    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ResCommentMapper resCommentMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private LikeDataRepository likeDataRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transfer")
    public Object transfer(@RequestBody TransactionData transactionData) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<UserInfoData> txUser = userInfoRepository.findById(userId);
        Optional<UserInfoData> rxUser = userInfoRepository.findById(transactionData.getTranRx());
        transactionData.setTranTx(userId);
        if (transactionData.getTranAmount() <= txUser.get().getToken()) {
            txUser.get().setToken(
                    txUser.get().getToken() - transactionData.getTranAmount());
            rxUser.get().setToken(
                    rxUser.get().getToken() + transactionData.getTranAmount());
            userInfoRepository.save(txUser.get());
            userInfoRepository.save(rxUser.get());

        } else {

        }
        return res;
    }

    @PostMapping("/withdraw")
    public Object withdraw(@RequestBody TransactionData transactionData) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<UserInfoData> txUser = userInfoRepository.findById(userId);
        transactionData.setTranTx(userId);

        if (transactionData.getTranAmount() <= txUser.get().getToken()) {
            txUser.get().setToken(
                    txUser.get().getToken() - transactionData.getTranAmount());
            userInfoRepository.save(txUser.get());

            // TODO: SENDMONEYTOREALACCOUNT
            transactionData.setTranRx("System");
            transactionRepository.save(transactionData);
            res.setData(transactionData);

        } else {

        }
        return res;
    }

    @PostMapping("/deposit")
    public Object deposit(@RequestBody TransactionData transactionData) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<UserInfoData> txUser = userInfoRepository.findById(userId);
        transactionData.setTranRx(userId); 
        transactionData.setTranTx("System");

        if (transactionData.getTranAmount() <= txUser.get().getToken()) {
            txUser.get().setToken(
                    txUser.get().getToken() - transactionData.getTranAmount());
            userInfoRepository.save(txUser.get());

            // TODO: SENDMONEYTOREALACCOUNT
            transactionRepository.save(transactionData);
            res.setData(transactionData);

        } else {

        }
        return res;
    }

    @PostMapping("/transactionHistory")
    public Object transactionHistory(@RequestParam String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<TransactionData> transactionData = transactionRepository.findByTranTxOrTranRxOrderByCreatedDateDesc(userId,userId);
        res.setData(transactionData);
        return res;
    }
}
