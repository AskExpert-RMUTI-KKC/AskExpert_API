package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.TransactionExecption;
import rmuti.askexpert.model.mapper.ResTransactionMapper;
import rmuti.askexpert.model.repo.CommentDataRepository; 
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.repo.TransactionRepository;
import rmuti.askexpert.model.repo.UserInfoRepository;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.response.ResTransaction; 
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.*;

import java.util.ArrayList;
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
    private UserInfoRepository userInfoRepository; 

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ResTransactionMapper resTransactionMapper;

    @PostMapping("/transfer")
    public Object transfer(@RequestBody TransactionData transactionData) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<UserInfoData> txUser = userInfoRepository.findById(userId);
        Optional<UserInfoData> rxUser = userInfoRepository.findById(transactionData.getTranRx());
        if(txUser.isPresent() && rxUser.isPresent()){
            if(txUser.get().getUserInfoId().equals(rxUser.get().getUserInfoId()))
            {
                throw TransactionExecption.loopTranfer();
            }
        }
        transactionData.setTranTx(userId);
        transactionData.setTranStatus("transfer");
        if (transactionData.getTranAmount() <= txUser.get().getToken()) {
            txUser.get().setToken(
                    txUser.get().getToken() - transactionData.getTranAmount());
            rxUser.get().setToken(
                    rxUser.get().getToken() + transactionData.getTranAmount());


            rxUser.get().setTokenCount(rxUser.get().getTokenCount()+transactionData.getTranAmount());

            userInfoRepository.save(txUser.get());
            userInfoRepository.save(rxUser.get());
            transactionRepository.save(transactionData);
            res.setData(transactionData);


            //UPDATE DONATE
            Optional<TopicData> topicData = topicDataRepository.findById(transactionData.getTranContentId());
            Optional<CommentData> commentData = commentDataRepository.findById(transactionData.getTranContentId());
            if (topicData.isPresent()) {
                topicData.get().setTopicDonateCount(topicData.get().getTopicLikeCount() + transactionData.getTranAmount());
                topicDataRepository.save(topicData.get());
            } else if (commentData.isPresent()) {
                commentData.get().setCommentDonateCount(commentData.get().getCommentLikeCount() + transactionData.getTranAmount());
                commentDataRepository.save(commentData.get());
            }
        } else {
            throw TransactionExecption.notenoughtoken();
        }
        return res;
    }

    @PostMapping("/withdraw")
    public Object withdraw(@RequestBody TransactionData transactionData) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<UserInfoData> txUser = userInfoRepository.findById(userId);
        transactionData.setTranTx(userId);
        transactionData.setTranStatus("withdraw");
        transactionData.setTranRx("SystemWithdraw");
        transactionData.setTranStatus("SystemWithdraw");
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

    @PostMapping("/deposit")
    public Object deposit(@RequestBody TransactionData transactionData) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        Optional<UserInfoData> txUser = userInfoRepository.findById(userId);
        transactionData.setTranRx(userId);
        transactionData.setTranTx("SystemDeposit");
        transactionData.setTranStatus("SystemDeposit");

        txUser.get().setToken(
                txUser.get().getToken() + transactionData.getTranAmount()
        );
        userInfoRepository.save(txUser.get());

        // TODO: SENDMONEYTOREALACCOUNT
        transactionRepository.save(transactionData);
        res.setData(transactionData);
        return res;
    }

    @PostMapping("/transactionHistory")
    public Object transactionHistory(@RequestHeader String Authorization) throws BaseException {
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<ResTransaction> transactionData = resTransactionMapper.toListResTransaction(
                transactionRepository.findByTranTxOrTranRxOrderByCreatedDateDesc(userId, userId));
        for (ResTransaction data : transactionData) {
            Optional<UserInfoData> txUser = userInfoRepository.findById(data.getTranTx());
            Optional<UserInfoData> rxUser = userInfoRepository.findById(data.getTranRx());
            if (txUser.isEmpty()) {
                txUser = Optional.of(new UserInfoData());
                txUser.get().setFirstName(data.getTranTx());
                txUser.get().setLastName(data.getTranTx());
                txUser.get().setUserName(data.getTranTx());
            }
            if (rxUser.isEmpty()) {
                rxUser = Optional.of(new UserInfoData());
                rxUser.get().setFirstName(data.getTranRx());
                rxUser.get().setLastName(data.getTranRx());
                rxUser.get().setUserName(data.getTranRx());
            }
            data.setUserInfoDataTx(resTransactionMapper.toResTransactionUserInfo(txUser.get()));
            data.setUserInfoDataRx(resTransactionMapper.toResTransactionUserInfo(rxUser.get()));
        }

        res.setData(transactionData);
        return res;
    }

    @PostMapping("/findMyPay")
    public Object findMyPay(@RequestBody char contentType) throws BaseException{
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        List<TransactionData> transactionData = transactionRepository.findByTranTx(userId);
        if(contentType == 'T'){
            List<Optional<TopicData>> topicDataList = new ArrayList<>();
            for(TransactionData data:transactionData){
                topicDataList.add(topicDataRepository.findById(data.getTranContentId()));
            }
            res.setData(topicDataList);
        }
        if(contentType == 'C'){
            List<Optional<CommentData>> commentDataList = new ArrayList<>();
            for(TransactionData data:transactionData){
                commentDataList.add(commentDataRepository.findById(data.getTranContentId()));
            }
            res.setData(commentDataList);
        }
        return res;
    }
}
