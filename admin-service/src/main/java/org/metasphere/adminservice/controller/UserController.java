package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.pojo.User;
import org.metasphere.adminservice.model.vo.req.UserLoginVO;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: WangZhenqi
 * @Description: 系统用户操作API
 * @Date: Created in 2022-11-13 19:48
 * @Modified By:
 */
@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public MsResponse actionUserLogin(@RequestBody UserLoginVO userLoginVO) {
        User targetUser = userService.login(userLoginVO.getEmail(), userLoginVO.getPassword());
        return MsResponse.success(targetUser);
    }

    @GetMapping(value = "query")
    public MsResponse actionUserQuery() {
        return MsResponse.success();
    }
}
