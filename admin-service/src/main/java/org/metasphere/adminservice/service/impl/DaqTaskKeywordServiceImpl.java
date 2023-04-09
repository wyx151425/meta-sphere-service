package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DaqTask;
import org.metasphere.adminservice.model.pojo.DaqTaskKeyword;
import org.metasphere.adminservice.repository.DAQTaskKeywordRepository;
import org.metasphere.adminservice.service.DaqTaskKeywordService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 14:51
 * @Modified By:
 */
@Service(value = "daqTaskKeywordService")
public class DaqTaskKeywordServiceImpl implements DaqTaskKeywordService {

    @Autowired
    private DAQTaskKeywordRepository daqTaskKeywordRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQTaskKeywords(DaqTask daqTask, List<String> keywords) {
        Set<String> keywordSet = new HashSet<>(keywords);
        List<DaqTaskKeyword> daqTaskKeywords = new ArrayList<>(keywordSet.size());
        for (String keyword : keywordSet) {
            DaqTaskKeyword daqTaskKeyword = new DaqTaskKeyword();
            daqTaskKeyword.setTaskId(daqTask.getId());
            daqTaskKeyword.setTaskName(daqTask.getName());
            daqTaskKeyword.setTaskCode(daqTask.getCode());
            daqTaskKeyword.setKeyword(keyword);
            daqTaskKeywords.add(daqTaskKeyword);
        }

        daqTaskKeywordRepository.saveAll(daqTaskKeywords);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDAQTaskKeyword(Long id) {
        DaqTaskKeyword daqTaskKeyword = daqTaskKeywordRepository.findById(id).orElseThrow(MSException::getDataNotFoundException);
        daqTaskKeyword.setStatus(MSConstant.MSEntity.Status.DISABLED);
        daqTaskKeyword.setUpdateAt(LocalDateTime.now());
        daqTaskKeywordRepository.save(daqTaskKeyword);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteDAQTaskKeywords(List<Long> ids) {
        daqTaskKeywordRepository.deleteAllById(ids);
    }

    @Override
    public List<DaqTaskKeyword> findDAQTaskKeywordsByDAQTask(Long daqTaskId) {
        return daqTaskKeywordRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    public MSPage<DaqTaskKeyword> findDAQTaskKeywordsByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DaqTaskKeyword> page = daqTaskKeywordRepository.findAll((Specification<DaqTaskKeyword>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("taskId"), daqTaskId);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MSPage.newInstance(page);
    }
}
