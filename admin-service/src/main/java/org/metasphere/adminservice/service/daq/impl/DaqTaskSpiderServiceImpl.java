package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.daq.DaqSpider;
import org.metasphere.adminservice.model.pojo.daq.DaqTask;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskServer;
import org.metasphere.adminservice.model.pojo.daq.DaqTaskSpider;
import org.metasphere.adminservice.repository.daq.DaqTaskSpiderRepository;
import org.metasphere.adminservice.service.daq.DaqSpiderService;
import org.metasphere.adminservice.service.daq.DaqTaskSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:26
 * @Modified By:
 */
@Service(value = "daqTaskSpiderService")
public class DaqTaskSpiderServiceImpl implements DaqTaskSpiderService {

    @Autowired
    private DaqTaskSpiderRepository daqTaskSpiderRepository;

    @Autowired
    private DaqSpiderService daqSpiderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqTaskSpiders(DaqTask daqTask, List<DaqTaskServer> daqTaskServers, List<Long> daqSpiderIds) {
        daqTaskSpiderRepository.deleteAllByTaskId(daqTask.getId());

        List<DaqSpider> daqSpiders = daqSpiderService.findDaqSpidersByIds(daqSpiderIds);

        List<DaqTaskSpider> taskSpiders = new ArrayList<>();
        daqTaskServers.forEach(daqTaskServer ->
                daqSpiders.forEach(daqSpider -> {
                    DaqTaskSpider taskSpider = new DaqTaskSpider();
                    taskSpider.setTaskId(daqTask.getId());
                    taskSpider.setTaskName(daqTask.getName());
                    taskSpider.setTaskCode(daqTask.getCode());
                    taskSpider.setSpiderId(daqSpider.getId());
                    taskSpider.setSpiderName(daqSpider.getName());
                    taskSpider.setSpiderCode(daqSpider.getCode());
                    taskSpider.setSpiderStatus(MsConst.DaqTaskSpider.SpiderStatus.NEW);
                    taskSpider.setServerIpAddress(daqTaskServer.getServerIpAddress());
                    taskSpider.setServerPort(daqTaskServer.getServerPort());
                    taskSpiders.add(taskSpider);
                }));
        daqTaskSpiderRepository.saveAll(taskSpiders);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDaqTaskSpider(DaqTaskSpider taskSpider) {
        taskSpider.setUpdateAt(LocalDateTime.now());
        daqTaskSpiderRepository.save(taskSpider);
    }

    @Override
    public List<DaqTaskSpider> findDaqTaskSpiders(Long daqTaskId) {
        return daqTaskSpiderRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    public List<Long> findDaqSpiderIdsByTaskId(Long daqTaskId) {
        return daqTaskSpiderRepository.findDaqSpiderIdsByTaskId(daqTaskId);
    }

    @Override
    public MsPage<DaqTaskSpider> findDaqTaskSpidersByPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DaqTaskSpider> page = daqTaskSpiderRepository.findAll((Specification<DaqTaskSpider>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("taskId"), daqTaskId);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MsPage.newInstance(page);
    }
}
