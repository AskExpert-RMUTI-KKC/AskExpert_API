package rmuti.askexpert.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.response.APIResponse;

@RestController
@RequestMapping("/image")
public class ImageDataController {
    //TODO : imageUploade and showImage
    // uploadImg
    @PostMapping("/verifyUploadImg")
    public Object verifyUploadImg() throws BaseException {
        APIResponse res = new APIResponse();
        return res;
    }

    //uploadProfileImg
    //uploadTopicImg
    //uploadCommentImg
    
}
