package eclp;

public class UAssertException extends RuntimeException
{
    public UAssertException() {
        super("Assert failed");  //NORES
    }

    public UAssertException(String message) {
        super(message);
    }
}
