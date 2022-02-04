package rmuti.askexpert.controller;

import lombok.Data;

@Data
public class APIResponse {
    private int status;
    private String message;
    private Object data;
}
