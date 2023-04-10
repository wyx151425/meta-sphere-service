package org.metasphere.adminservice.model.vo.resp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.metasphere.adminservice.constant.MsStatusCode;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:38
 * @Modified By:
 */
@Slf4j
@Data
public class MsResponse<T> {
    private int statusCode;
    private T data;

    public static <T> MsResponse<T> success() {
        MsResponse<T> msResponse = new MsResponse<>();
        msResponse.setStatusCode(MsStatusCode.SUCCESS);
        return msResponse;
    }

    public static <T> MsResponse<T> success(T data) {
        MsResponse<T> msResponse = new MsResponse<>();
        msResponse.setStatusCode(MsStatusCode.SUCCESS);
        msResponse.setData(data);
        return msResponse;
    }

    public static <T> MsResponse<T> error() {
        MsResponse<T> msResponse = new MsResponse<>();
        msResponse.setStatusCode(MsStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static <T> MsResponse<T> systemError() {
        MsResponse<T> msResponse = new MsResponse<>();
        msResponse.setStatusCode(MsStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static <T> MsResponse<T> accessDenied() {
        MsResponse<T> msResponse = new MsResponse<>();
        msResponse.setStatusCode(MsStatusCode.ACCOUNT_AUTHENTICATION_FAILED);
        return msResponse;
    }

    public static <T> MsResponse<T> usernameOrPasswordError() {
        MsResponse<T> msResponse = new MsResponse<>();
        msResponse.setStatusCode(MsStatusCode.USERNAME_OR_PASSWORD_ERROR);
        return msResponse;
    }
}
