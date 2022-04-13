package rmuti.askexpert.model.exception;

public class TransactionExecption extends BaseException{
    public TransactionExecption(String code) {
        super("Transaction." + code);
    }

    public static TransactionExecption nothavemoney() {
        return new TransactionExecption("no.money");
    }
    
}
