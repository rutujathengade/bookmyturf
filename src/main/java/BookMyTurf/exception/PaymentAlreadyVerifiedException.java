package BookMyTurf.exception;

public class PaymentAlreadyVerifiedException extends RuntimeException 
{

    public PaymentAlreadyVerifiedException(String message) 
    {
        super(message);
    }
}