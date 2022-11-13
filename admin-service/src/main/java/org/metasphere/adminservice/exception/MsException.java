package org.metasphere.adminservice.exception;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:35
 * @Modified By:
 */
@Getter
public class MsException extends RuntimeException {

    private final Integer statusCode;

    public MsException(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public MsException(Integer statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
    }
}
