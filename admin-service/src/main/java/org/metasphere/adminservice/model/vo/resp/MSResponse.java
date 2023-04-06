package org.metasphere.adminservice.model.vo.resp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.constant.MSStatusCode;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:38
 * @Modified By:
 */
@Slf4j
@Data
public class MSResponse<T> {
    private int statusCode;
    private T data;

    public static <T> MSResponse<T> success() {
        MSResponse<T> msResponse = new MSResponse<>();
        msResponse.setStatusCode(MSStatusCode.SUCCESS);
        return msResponse;
    }

    public static <T> MSResponse<T> success(T data) {
        MSResponse<T> msResponse = new MSResponse<>();
        msResponse.setStatusCode(MSStatusCode.SUCCESS);
        msResponse.setData(data);
        return msResponse;
    }

    public static <T> MSResponse<T> error() {
        MSResponse<T> msResponse = new MSResponse<>();
        msResponse.setStatusCode(MSStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static <T> MSResponse<T> systemError() {
        MSResponse<T> msResponse = new MSResponse<>();
        msResponse.setStatusCode(MSStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static <T> MSResponse<T> accessDenied() {
        MSResponse<T> msResponse = new MSResponse<>();
        msResponse.setStatusCode(MSStatusCode.ACCOUNT_AUTHENTICATION_FAILED);
        return msResponse;
    }

    public static <T> MSResponse<T> usernameOrPasswordError() {
        MSResponse<T> msResponse = new MSResponse<>();
        msResponse.setStatusCode(MSStatusCode.USERNAME_OR_PASSWORD_ERROR);
        return msResponse;
    }
}
