package org.metasphere.adminservice.exception;

import lombok.Getter;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:35
 * @Modified By:
 */
@Getter
public class MSException extends RuntimeException {

    private final Integer statusCode;

    public MSException(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public MSException(Integer statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
    }
}
