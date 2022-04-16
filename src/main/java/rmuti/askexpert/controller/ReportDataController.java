package rmuti.askexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.CommentDataRepository;
import rmuti.askexpert.model.repo.ReportDataRepository;
import rmuti.askexpert.model.repo.TopicDataRepository;
import rmuti.askexpert.model.request.ReqReportUpdate;
import rmuti.askexpert.model.response.APIResponse;
import rmuti.askexpert.model.services.TokenService;
import rmuti.askexpert.model.table.CommentData;
import rmuti.askexpert.model.table.ReportData;
import rmuti.askexpert.model.table.TopicData;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportDataController {
    @Autowired
    private TopicDataRepository topicDataRepository;


    @Autowired
    private CommentDataRepository commentDataRepository;

    @Autowired
    private ReportDataRepository reportDataRepository;

    @Autowired
    private TokenService tokenService;


    //Add
    @PostMapping("/add")
    public Object reportAdd(@RequestBody ReportData reportData) throws BaseException {
        if(!tokenService.isAdmin()){
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        String userId = tokenService.userId();
        reportData.setReportFrom(userId);
        Optional<TopicData> topicDataOptional = topicDataRepository.findById(reportData.getReportContentId());
        Optional<CommentData> commentDataOptional = commentDataRepository.findById(reportData.getReportContentId());
        if (topicDataOptional.isPresent()) {
            reportData.setReportContentType('T');
            topicDataOptional.get().setTopicReport('R');
            topicDataRepository.save(topicDataOptional.get());
        } else if (commentDataOptional.isPresent()) {
            reportData.setReportContentType('C');
            commentDataOptional.get().setCommentReport('R');
            commentDataRepository.save(commentDataOptional.get());
        }
        reportData.setReportWhoDecide("none");
        reportData.setReportStatus('W');
        reportDataRepository.save(reportData);
        res.setData(reportData);
        return res;
    }

    //update
    @PostMapping("/update")
    public Object reportUpdate(@RequestBody ReqReportUpdate reportUpdate) throws BaseException {
        if (!tokenService.isAdmin()) {
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        List<ReportData> reportDataOptional = reportDataRepository.findByReportContentId(reportUpdate.getReportId());
        Optional<TopicData> topicDataOptional = topicDataRepository.findById(reportUpdate.getReportContentId());
        Optional<CommentData> commentDataOptional = commentDataRepository.findById(reportUpdate.getReportContentId());
        if (topicDataOptional.isPresent()) {
            if (reportUpdate.getReportStatus() == 'D') {
                topicDataOptional.get().setTopicReportStatus(1);
                topicDataRepository.save(topicDataOptional.get());
                res.setData(topicDataOptional.get());
            }
        } else if (commentDataOptional.isPresent()) {
            if (reportUpdate.getReportStatus() == 'D') {
                commentDataOptional.get().setCommentReportStatus(1);
                commentDataRepository.save(commentDataOptional.get());
                res.setData(commentDataOptional.get());
            }
        }
        if (reportUpdate.getReportStatus() == 'A') {
            for (ReportData reportData : reportDataOptional) {
                reportData.setReportStatus('A');
                reportDataRepository.save(reportData);
            }
        }
        return res;
    }

    //findymyUpdate
    //findAll
    @PostMapping("/findAll")
    public Object findAll(@RequestBody char reportContentType) throws BaseException {
        if(!tokenService.isAdmin()){
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        if (reportContentType == 'T') {
            List<TopicData> topicData = topicDataRepository.findByTopicReport('R');
            res.setData(topicData);
        }
        if (reportContentType == 'C') {
            List<CommentData> commentData = commentDataRepository.findByCommentReport('R');
            res.setData(commentData);
        }
        return res;
    }

    //findByContentId
    @PostMapping("/findByContentId")
    public Object findByIdContent(@RequestBody String contentId) throws BaseException {
        if(!tokenService.isAdmin()){
            throw UserException.youarenotadmin();
        }
        APIResponse res = new APIResponse();
        List<ReportData> reportDataList = reportDataRepository.findByReportContentId(contentId);
        Optional<TopicData> topicDataOptional = topicDataRepository.findById(contentId);
        Optional<CommentData> commentDataOptional = commentDataRepository.findById(contentId);
        if (topicDataOptional.isPresent()) {
            topicDataOptional.get().setTopicReportStatus(1);
            topicDataRepository.save(topicDataOptional.get());
            res.setData(topicDataOptional.get());
        }if (commentDataOptional.isPresent()) {
            commentDataOptional.get().setCommentReportStatus(1);
            commentDataRepository.save(commentDataOptional.get());
            res.setData(commentDataOptional.get());
        }
        return res;
    }

}