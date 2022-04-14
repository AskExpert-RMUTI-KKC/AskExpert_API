package rmuti.askexpert.model.request;

import lombok.Data;

@Data
public class ReqReportUpdate {

    private String reportId;
    private String reportContentId;
    private char reportStatus;

}
