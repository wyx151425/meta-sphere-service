package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.repository.DAQTaskRepository;
import org.metasphere.adminservice.service.DAQTaskKeywordService;
import org.metasphere.adminservice.service.DAQTaskService;
import org.metasphere.adminservice.service.ScrapydService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-06 20:13
 * @Modified By:
 */
@Service(value = "daqTaskService")
public class DAQTaskServiceImpl implements DAQTaskService {

    @Autowired
    private DAQTaskRepository daqTaskRepository;

    @Autowired
    private DAQTaskKeywordService daqTaskKeywordService;

    @Autowired
    private ScrapydService scrapydService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDAQTask(DAQTask daqTask) {
        String uuid = UUID.randomUUID().toString();
        daqTask.setCode(uuid);
        daqTask.setStage(MSConstant.DAQProject.Stage.CREATED);
        daqTask.setCreatedAt(LocalDateTime.now());
        daqTaskRepository.save(daqTask);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startDataAcquiring(Long daqProjectId) {
        DAQTask daqTask = daqTaskRepository.findById(daqProjectId).orElseThrow(MSException::getDataNotFoundException);

        List<DAQTaskKeyword> daqTaskKeywords = daqTaskKeywordService.findDAQKeywordsByDAQProject(daqProjectId);


        String daqUrls = daqTask.getName() + ":" + daqTask.getCode() + ":daqUrls";


//        redisTemplate.opsForList().leftPush(daqUrls, new ArrayList<>());
    }

    @Override
    public DAQTask findDAQTaskById(Long id) {
        return daqTaskRepository.findById(id).orElseThrow(MSException::getDataNotFoundException);
    }

    @Override
    public MSPage<DAQTask> findDAQTasksByParams(Integer pageNum, Integer pageSize, Integer stage) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<DAQTask> page;
        if (null != stage) {
            page = daqTaskRepository.findAll((Specification<DAQTask>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("stage"), stage));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }, pageable);
        } else {
            page = daqTaskRepository.findAll(pageable);
        }
        return MSPage.newInstance(page);
    }
}
