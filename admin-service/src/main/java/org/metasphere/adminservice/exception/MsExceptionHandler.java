package org.metasphere.adminservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:36
 * @Modified By:
 */
@Slf4j
@RestControllerAdvice
public class MsExceptionHandler {

    @ExceptionHandler(value = MsException.class)
    public MsResponse handleMsException(MsException e) {
        return MsResponse.error();
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public MsResponse handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return MsResponse.accessDenied();
    }

    @ExceptionHandler(value = Exception.class)
    public MsResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return MsResponse.systemError();
    }
}
