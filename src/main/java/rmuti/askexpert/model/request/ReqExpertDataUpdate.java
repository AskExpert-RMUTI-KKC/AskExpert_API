package rmuti.askexpert.model.request;

import lombok.Data;

@Data
public class ReqExpertDataUpdate {
    private String expertGroupId;
    private String expertPath;
    private int expertStatus;
}
