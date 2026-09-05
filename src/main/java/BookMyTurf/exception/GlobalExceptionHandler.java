package BookMyTurf.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import BookMyTurf.exception.InvalidSlotTimeException;
@RestControllerAdvice
public class GlobalExceptionHandler 
{

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleEmailAlreadyExists
    (EmailAlreadyExistsException ex)
    {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return response;
    }
    

    @ExceptionHandler(SlotAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleSlotAlreadyExists
    (SlotAlreadyExistsException ex)
    {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return response;
    }
    
    @ExceptionHandler(InvalidSlotTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidSlotTime(InvalidSlotTimeException ex)
    {
        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return response;
    }
    
    @ExceptionHandler(InvalidSlotDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidSlotDate(InvalidSlotDateException ex)
    {
        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return response;
    }
    
    
    @ExceptionHandler(SlotNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleSlotNotFound(SlotNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Slot already exists");
        return response;
    }
}