package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.constant.MSStatusCode;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DaqTask;
import org.metasphere.adminservice.model.pojo.DaqTaskKeyword;
import org.metasphere.adminservice.model.pojo.DaqTaskSpider;
import org.metasphere.adminservice.repository.DAQTaskRepository;
import org.metasphere.adminservice.service.DaqTaskKeywordService;
import org.metasphere.adminservice.service.DaqTaskService;
import org.metasphere.adminservice.service.DaqTaskSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 20:13
 * @Modified By:
 */
@Service(value = "daqTaskService")
public class DaqTaskServiceImpl implements DaqTaskService {

    @Autowired
    private DAQTaskRepository daqTaskRepository;

    @Autowired
    private DaqTaskKeywordService daqTaskKeywordService;

    @Autowired
    private DaqTaskSpiderService daqTaskSpiderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDaqTask(DaqTask daqTask) {
        daqTask.setStage(MSConstant.DAQTask.Stage.CREATED);
        daqTask.setCreatedAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDaqTask(Long id) {
        DaqTask daqTask = daqTaskRepository.findById(id).orElseThrow(MSException::getDataNotFoundException);
        if (MSConstant.DAQTask.Stage.CREATED != daqTask.getStage()) {
            throw new MSException(MSStatusCode.DAQ_TASK_STAGE_ERROR);
        }
        daqTask.setStatus(MSConstant.MSEntity.Status.DISABLED);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startDaqTask(Long id) {
        String taskCode = UUID.randomUUID().toString();

        DaqTask daqTask = daqTaskRepository.findById(id).orElseThrow(MSException::getDataNotFoundException);
        daqTask.setCode(taskCode);
        daqTask.setStatus(MSConstant.DAQTask.Stage.DATA_TO_ACQUIRE);
        daqTask.setUpdateAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startDataAcquiring(Long daqProjectId) {
        DaqTask daqTask = daqTaskRepository.findById(daqProjectId).orElseThrow(MSException::getDataNotFoundException);

        List<DaqTaskKeyword> daqTaskKeywords = daqTaskKeywordService.findDAQTaskKeywordsByDAQTask(daqProjectId);


        String daqUrls = daqTask.getName() + ":" + daqTask.getCode() + ":daqUrls";


//        redisTemplate.opsForList().leftPush(daqUrls, new ArrayList<>());
    }

    @Override
    public MSPage<DaqTask> findDaqTasksByParams(Integer pageNum, Integer pageSize, Integer stage) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<DaqTask> page;
        if (null != stage) {
            page = daqTaskRepository.findAll((Specification<DaqTask>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("stage"), stage));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }, pageable);
        } else {
            page = daqTaskRepository.findAll(pageable);
        }
        return MSPage.newInstance(page);
    }

    @Override
    public void addDaqTaskSpiders(Long daqTaskId, List<Long> daqSpiderIds) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);
        daqTaskSpiderService.addDAQTaskSpiders(daqTask, daqSpiderIds);
    }

    @Override
    public List<DaqTaskSpider> findDaqTaskSpidersByDaqTask(Long daqTaskId) {
        return daqTaskSpiderService.findDAQTaskSpidersByDAQTask(daqTaskId);
    }

    @Override
    public MSPage<DaqTaskSpider> findDaqTaskSpidersByDaqTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        return daqTaskSpiderService.findDAQTaskSpidersByDAQTaskAndPagination(daqTaskId, pageNum, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskKeywords(Long daqTaskId, List<String> keywords) {
        DaqTask daqTask = daqTaskRepository.findById(daqTaskId).orElseThrow(MSException::getDataNotFoundException);
        daqTaskKeywordService.addDAQTaskKeywords(daqTask, keywords);
    }

    @Override
    public MSPage<DaqTaskKeyword> findDaqTaskKeywordsByDaqTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        return daqTaskKeywordService.findDAQTaskKeywordsByDAQTaskAndPagination(daqTaskId, pageNum, pageSize);
    }
}
