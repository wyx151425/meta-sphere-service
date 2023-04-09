package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DaqSpider;
import org.metasphere.adminservice.model.pojo.DaqTask;
import org.metasphere.adminservice.model.pojo.DaqTaskSpider;
import org.metasphere.adminservice.repository.DAQTaskSpiderRepository;
import org.metasphere.adminservice.service.DaqSpiderService;
import org.metasphere.adminservice.service.DaqTaskSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
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
    private DAQTaskSpiderRepository daqTaskSpiderRepository;

    @Autowired
    private DaqSpiderService daqSpiderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQTaskSpiders(DaqTask daqTask, List<Long> daqSpiderIds) {
        daqTaskSpiderRepository.deleteAllByTaskId(daqTask.getId());

        List<DaqSpider> daqSpiders = daqSpiderService.findDAQSpidersByIds(daqSpiderIds);

        List<DaqTaskSpider> taskSpiders = new ArrayList<>();
        for (DaqSpider daqSpider : daqSpiders) {
            DaqTaskSpider taskSpider = new DaqTaskSpider();
            taskSpider.setTaskId(daqTask.getId());
            taskSpider.setTaskName(daqTask.getName());
            taskSpider.setTaskCode(daqTask.getCode());
            taskSpider.setSpiderId(daqSpider.getId());
            taskSpider.setSpiderName(daqSpider.getName());
            taskSpider.setSpiderStatus(MSConstant.DAQTaskSpider.SpiderStatus.NEW);
            taskSpiders.add(taskSpider);
        }
        daqTaskSpiderRepository.saveAll(taskSpiders);
    }

    @Override
    public List<DaqTaskSpider> findDAQTaskSpidersByDAQTask(Long daqTaskId) {
        return daqTaskSpiderRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    public MSPage<DaqTaskSpider> findDAQTaskSpidersByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DaqTaskSpider> page = daqTaskSpiderRepository.findAll((Specification<DaqTaskSpider>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("taskId"), daqTaskId);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MSPage.newInstance(page);
    }
}
