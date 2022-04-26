package rmuti.askexpert.model.exception;

public class TransactionExecption extends BaseException {
    public TransactionExecption(String code) {
        super("Transaction." + code);
    }

    public static TransactionExecption notenoughtoken() {return new TransactionExecption("not.enough.token");
    }

    public static TransactionExecption loopTranfer() {
        return new TransactionExecption("loop.transfer");
    }

}
