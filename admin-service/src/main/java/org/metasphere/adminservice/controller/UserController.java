package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.vo.req.UserLoginVO;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
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

    @PostMapping(value = "login")
    public MSResponse actionUserLogin(@RequestBody UserLoginVO userLoginVO) {
        return MSResponse.success();
    }

    @GetMapping(value = "query")
    public MSResponse actionUserQuery() {
        return MSResponse.success();
    }
}
