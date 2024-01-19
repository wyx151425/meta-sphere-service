package org.metasphere.portalservice.context.constant;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-10 20:44
 * @Modified By:
 */
public class MSStatusCode {

    public static final int SUCCESS = 10000;
    public static final int SERVER_UNREACHABLE = 11000;
    public static final int PORT_UNREACHABLE = 11001;
    public static final int SYSTEM_ERROR = 19999;

    public static final int USER_NOT_FOUND = 20001;
    public static final int ACCOUNT_DISABLED = 20002;
    public static final int ACCOUNT_AUTHENTICATION_FAILED = 20003;
    public static final int USERNAME_OR_PASSWORD_ERROR = 20004;

    public static final int DATA_NOT_FOUND = 30001;

    public static final int DAQ_TASK_STAGE_ERROR = 41001;
}
