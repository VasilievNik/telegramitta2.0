package pro.sky.telegrambot;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NotifyNotExistException extends RuntimeException{
    public NotifyNotExistException() {
    }
}