package org.metasphere.adminservice.model.vo.resp;

import lombok.Data;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:38
 * @Modified By:
 */
@Data
public class MsResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public  static MsResponse success() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(10000);
        return msResponse;
    }

    public static MsResponse error() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(10000);
        return msResponse;
    }

    public static MsResponse systemError() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(10000);
        return msResponse;
    }

    public static MsResponse accessDenied() {
        MsResponse msResponse = new MsResponse();
        msResponse.setStatusCode(10000);
        return msResponse;
    }

    public MsResponse data(T data) {
        this.data = data;
        return this;
    }
}
