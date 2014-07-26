package ccampo133.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends Exception {

    public PostNotFoundException(final String message) {
        super(message);
    }
}
