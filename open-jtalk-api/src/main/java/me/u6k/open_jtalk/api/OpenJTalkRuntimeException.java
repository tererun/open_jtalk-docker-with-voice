
package me.u6k.open_jtalk.api;

@SuppressWarnings("serial")
public class OpenJTalkRuntimeException extends RuntimeException {

    public OpenJTalkRuntimeException() {
        super();
    }

    public OpenJTalkRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OpenJTalkRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenJTalkRuntimeException(String message) {
        super(message);
    }

    public OpenJTalkRuntimeException(Throwable cause) {
        super(cause);
    }

}
