package org.metasphere.commonservice.exception;

import lombok.Data;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:35
 * @Modified By:
 */
@Data
public class MsException extends RuntimeException {

    private Integer statusCode;

    public MsException(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
