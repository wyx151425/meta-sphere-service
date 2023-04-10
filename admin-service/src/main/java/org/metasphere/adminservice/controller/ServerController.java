package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.model.vo.resp.MsResponse;
import org.metasphere.adminservice.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-03-07 20:14
 * @Modified By:
 */
@RestController
@RequestMapping(value = "servers")
public class ServerController {

    @Autowired
    private ServerService serverService;

    @PostMapping(value = "")
    public MsResponse<Server> actionSaveServer(@RequestBody Server server) {
        serverService.saveServer(server);
        return MsResponse.success();
    }

    @GetMapping(value = "queryByPagination")
    public MsResponse<MsPage<Server>> actionQueryServersByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MsPage<Server> page = serverService.findServersByPagination(pageNum, pageSize);
        return MsResponse.success(page);
    }
}
