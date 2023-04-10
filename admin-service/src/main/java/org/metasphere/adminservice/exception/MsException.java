package org.metasphere.adminservice.exception;

import lombok.Getter;
import org.metasphere.adminservice.constant.MsStatusCode;

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

    public static MsException getDataNotFoundException() {
        return new MsException(MsStatusCode.DATA_NOT_FOUND);
    }
}
