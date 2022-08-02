package ee.cloudmore.app.api;

import ee.cloudmore.app.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ClientRegistryControllerAdvice {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(BusinessException exception) {
        return new ErrorResponse()
                .setMessage(exception.getMessage());
    }

}
