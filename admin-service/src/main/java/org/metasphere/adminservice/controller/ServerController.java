package org.metasphere.adminservice.controller;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.Server;
import org.metasphere.adminservice.model.vo.resp.MSResponse;
import org.metasphere.adminservice.service.core.ServerService;
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
    public MSResponse<Server> actionSaveServer(@RequestBody Server server) {
        serverService.saveServer(server);
        return MSResponse.success();
    }

    @GetMapping(value = "queryByTypeAndPagination")
    public MSResponse<MSPage<Server>> actionQueryServersByTypeAndPagination(
            @RequestParam(value = "type") Integer type,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<Server> page = serverService.findServersByTypeAndPagination(type, pageNum, pageSize);
        return MSResponse.success(page);
    }

    @GetMapping(value = "queryByPagination")
    public MSResponse<MSPage<Server>> actionQueryServersByPagination(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        MSPage<Server> page = serverService.findServersByPagination(pageNum, pageSize);
        return MSResponse.success(page);
    }
}
