package rmuti.askexpert.model.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super("user." + code);
    }

    public static UserException notId() {
        return new UserException("not.find.id");
    }

    public static UserException notFound() {
        return new UserException("not.found");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("register.email.duplicated");
    }
    public static UserException createPhoneDuplicated() {
        return new UserException("register.phone.duplicated");
    }
    public static UserException createUserNameDuplicated() {
        return new UserException("username.duplicated");
    }

    public static UserException createEmailNull() {
        return new UserException("register.email.null");
    }

    public static UserException createPasswordNull() {
        return new UserException("register.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("register.name.null");
    }

    public static UserException requestInvalid() {
        return new UserException("request.invalid ");
    }

    public static UserException accessDenied() {
        return new UserException("access.denied");
    }
    public static UserException passwordIncorrect () {
        return new UserException("password.incorrect");
    }
    public static UserException expires () {
        return new UserException("token.expires");
    }

    public static UserException nouserinfo () {
        return new UserException("no.user.info");
    }
    public static UserException youarenotadmin () {
        return new UserException("you.are.not.fucking.admin");
    }


    public static BaseException youarealreadyverify() { return new UserException("youarealreadyverify");
    }
}
