package org.metasphere.adminservice.model.vo.resp;

import lombok.Data;
import org.metasphere.adminservice.constant.MsStatusCode;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:38
 * @Modified By:
 */
@Data
public class MsResponse {
    private int statusCode;
    private String message;
    private Object data;

    public  static MsResponse success() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(MsStatusCode.SUCCESS);
        return msResponse;
    }

    public static MsResponse error() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(MsStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static MsResponse systemError() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(MsStatusCode.SYSTEM_ERROR);
        return msResponse;
    }

    public static MsResponse accessDenied() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(MsStatusCode.ACCOUNT_AUTHENTICATION_FAILED);
        return msResponse;
    }

    public MsResponse data(Object data) {
        this.data = data;
        return this;
    }
}
