package org.metasphere.adminservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
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
public class MSExceptionHandler {

    @ExceptionHandler(value = MSException.class)
    public MSResponse handleMSException(MSException e) {
        return MSResponse.error(e.getStatusCode());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public MSResponse handleAccessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage(), e);
        return MSResponse.accessDenied();
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public MSResponse handleUserNotFoundException(UserNotFoundException e) {
        log.error("UserNotFoundException", e);
        return MSResponse.accessDenied();
    }

    @ExceptionHandler(value = AccountDisabledException.class)
    public MSResponse handleAccountDisabledException(AccountDisabledException e) {
        log.error("AccountDisabledException", e);
        return MSResponse.accessDenied();
    }

    @ExceptionHandler(value = Exception.class)
    public MSResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return MSResponse.systemError();
    }
}
