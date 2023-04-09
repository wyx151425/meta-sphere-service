package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.metasphere.adminservice.repository.DAQTaskKeywordRepository;
import org.metasphere.adminservice.service.DAQTaskKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
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
public class DAQTaskKeywordServiceImpl implements DAQTaskKeywordService {

    @Autowired
    private DAQTaskKeywordRepository daqTaskKeywordRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQTaskKeywords(DAQTask daqTask, List<String> keywords) {
        Set<String> keywordSet = new HashSet<>(keywords);
        List<DAQTaskKeyword> daqTaskKeywords = new ArrayList<>(keywordSet.size());
        for (String keyword : keywordSet) {
            DAQTaskKeyword daqTaskKeyword = new DAQTaskKeyword();
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
        daqTaskKeywordRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteDAQTaskKeywords(List<Long> ids) {
        daqTaskKeywordRepository.deleteAllById(ids);
    }

    @Override
    public List<DAQTaskKeyword> findDAQTaskKeywordsByDAQTask(Long daqTaskId) {
        return daqTaskKeywordRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    public MSPage<DAQTaskKeyword> findDAQTaskKeywordsByDAQTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DAQTaskKeyword> page = daqTaskKeywordRepository.findAll((Specification<DAQTaskKeyword>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("taskId"), daqTaskId);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MSPage.newInstance(page);
    }
}
