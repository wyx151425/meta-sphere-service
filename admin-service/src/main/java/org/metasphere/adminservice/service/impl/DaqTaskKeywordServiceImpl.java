package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MsConst;
import org.metasphere.adminservice.exception.MsException;
import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.DaqTask;
import org.metasphere.adminservice.model.pojo.DaqTaskKeyword;
import org.metasphere.adminservice.repository.DaqTaskKeywordRepository;
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
    private DaqTaskKeywordRepository daqTaskKeywordRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDaqTaskKeywords(DaqTask daqTask, List<String> keywords) {
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
    public void deleteDaqTaskKeyword(Long id) {
        DaqTaskKeyword daqTaskKeyword = daqTaskKeywordRepository.findById(id).orElseThrow(MsException::getDataNotFoundException);
        daqTaskKeyword.setStatus(MsConst.MetaSphereEntity.Status.DISABLED);
        daqTaskKeyword.setUpdateAt(LocalDateTime.now());
        daqTaskKeywordRepository.save(daqTaskKeyword);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteDaqTaskKeywords(List<Long> ids) {
        daqTaskKeywordRepository.deleteAllById(ids);
    }

    @Override
    public List<DaqTaskKeyword> findDaqTaskKeywordsByDaqTask(Long daqTaskId) {
        return daqTaskKeywordRepository.findAllByTaskId(daqTaskId);
    }

    @Override
    public MsPage<DaqTaskKeyword> findDaqTaskKeywordsByDaqTaskAndPagination(Long daqTaskId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<DaqTaskKeyword> page = daqTaskKeywordRepository.findAll((Specification<DaqTaskKeyword>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.equal(root.get("taskId"), daqTaskId);
            return query.where(new Predicate[]{predicate}).getRestriction();
        }, pageable);
        return MsPage.newInstance(page);
    }
}
