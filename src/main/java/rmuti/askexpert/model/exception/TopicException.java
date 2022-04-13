package rmuti.askexpert.model.exception;

public class TopicException extends BaseException {
    public TopicException(String code) {
        super("Topic." + code);
    }

    public static TopicException notyourtopic() {
        return new TopicException("not.your.topic");
    }

}
