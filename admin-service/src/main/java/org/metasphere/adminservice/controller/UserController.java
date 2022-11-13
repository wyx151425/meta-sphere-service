package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.vo.req.UserLoginVO;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-13 19:48
 * @Modified By:
 */
@RequestMapping(value = "/admin/user")
public class UserController {

    @PostMapping(value = "/login")
    public MsResponse actionUserLogin(@RequestBody UserLoginVO userLoginVO) {
        return MsResponse.success();
    }
}
