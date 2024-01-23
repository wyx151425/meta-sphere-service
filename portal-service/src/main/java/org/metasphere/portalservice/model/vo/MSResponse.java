package org.metasphere.portalservice.model.vo;

import lombok.extern.slf4j.Slf4j;
import org.metasphere.portalservice.context.constant.MSStatusCode;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:38
 * @Modified By:
 */
@Slf4j
public class MSResponse<T> {
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 数据
     */
    private T data;

    public MSResponse() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

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
