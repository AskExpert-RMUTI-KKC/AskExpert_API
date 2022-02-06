package rmuti.askexpert.exception;

import javax.persistence.MappedSuperclass;
import java.io.IOException;

@MappedSuperclass
public abstract class BaseException extends IOException {
    public BaseException(String code) {
        super(code);
    }

}
