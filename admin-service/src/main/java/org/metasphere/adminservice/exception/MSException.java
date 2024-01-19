package org.metasphere.adminservice.exception;

import lombok.Getter;
import org.metasphere.adminservice.context.constant.MSStatusCode;

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

    public static MSException getDataNotFoundException() {
        return new MSException(MSStatusCode.DATA_NOT_FOUND);
    }
}
