package org.metasphere.adminservice.util;

import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 11:35
 * @Modified By:
 */
public class UUIDUtils {

    public static String getMSObjectId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getDaqTaskCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
