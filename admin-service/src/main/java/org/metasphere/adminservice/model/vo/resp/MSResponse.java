package org.metasphere.adminservice.model.vo.resp;

import lombok.Data;
import org.metasphere.adminservice.constant.MSStatusCode;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:38
 * @Modified By:
 */
@Data
public class MSResponse {
    private int statusCode;
    private Object data;

    public  static MSResponse success() {
        MSResponse msResponse = new MSResponse();
        msResponse.setStatusCode(MSStatusCode.SUCCESS);
        return msResponse;
    }

    public  static MSResponse success(Object data) {
        MSResponse msResponse = new MSResponse();
        msResponse.setStatusCode(MSStatusCode.SUCCESS);
        msResponse.setData(data);
        return msResponse;
    }

    public static MSResponse error() {
        MSResponse msResponse = new MSResponse();
        msResponse.setStatusCode(MSStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static MSResponse systemError() {
        MSResponse msResponse = new MSResponse();
        msResponse.setStatusCode(MSStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static MSResponse accessDenied() {
        MSResponse msResponse = new MSResponse();
        msResponse.setStatusCode(MSStatusCode.ACCOUNT_AUTHENTICATION_FAILED);
        return msResponse;
    }

    public static MSResponse usernameOrPasswordError() {
        MSResponse msResponse = new MSResponse();
        msResponse.setStatusCode(MSStatusCode.USERNAME_OR_PASSWORD_ERROR);
        return msResponse;
    }
}
