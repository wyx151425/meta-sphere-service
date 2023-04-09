package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQSpider;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskSpider;
import org.metasphere.adminservice.repository.DAQTaskSpiderRepository;
import org.metasphere.adminservice.service.DAQSpiderService;
import org.metasphere.adminservice.service.DAQTaskSpiderService;
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
public class DAQTaskSpiderServiceImpl implements DAQTaskSpiderService {

    @Autowired
    private DAQTaskSpiderRepository daqTaskSpiderRepository;

    @Autowired
    private DAQSpiderService daqSpiderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQTaskSpiders(DAQTask daqTask, List<Long> daqSpiderIds) {
        daqTaskSpiderRepository.deleteAllByTaskId(daqTask.getId());

        List<DAQSpider> daqSpiders = daqSpiderService.findDAQSpidersByIds(daqSpiderIds);

        List<DAQTaskSpider> taskSpiders = new ArrayList<>();
        for (DAQSpider daqSpider : daqSpiders) {
            DAQTaskSpider taskSpider = new DAQTaskSpider();
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
    public List<DAQTaskSpider> findDAQTaskSpidersByDAQTask(Long daqTaskId) {
        return daqTaskSpiderRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    public MSPage<DAQTaskSpider> findDAQTaskSpidersByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DAQTaskSpider> page = daqTaskSpiderRepository.findAll((Specification<DAQTaskSpider>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("taskId"), daqTaskId);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MSPage.newInstance(page);
    }
}
