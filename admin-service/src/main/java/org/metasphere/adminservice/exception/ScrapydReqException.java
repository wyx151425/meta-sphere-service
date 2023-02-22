package org.metasphere.adminservice.exception;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-02-22 16:00
 * @Modified By:
 */
public class ScrapydReqException extends RuntimeException {

    public ScrapydReqException() {
    }

    public ScrapydReqException(String message) {
        super(message);
    }

    public ScrapydReqException(String message, Throwable cause) {
        super(message, cause);
    }
}
