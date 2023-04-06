package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.constant.MSConstant;
import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQProject;
import org.metasphere.adminservice.repository.DAQProjectRepository;
import org.metasphere.adminservice.service.DAQProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
@Service(value = "daqProjectService")
public class DAQProjectServiceImpl implements DAQProjectService {

    @Autowired
    private DAQProjectRepository daqProjectRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDAQProject(DAQProject daqProject) {
        String uuid = UUID.randomUUID().toString();
        daqProject.setCode(uuid);
        daqProject.setStage(MSConstant.DAQProject.Stage.CREATED);
        daqProject.setCreatedTime(LocalDateTime.now());
        daqProjectRepository.save(daqProject);
    }

    @Override
    public MSPage<DAQProject> findDAQProjectByParams(Integer pageNum, Integer pageSize, Integer stage) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<DAQProject> page;
        if (null != stage) {
            page = daqProjectRepository.findAll((Specification<DAQProject>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("stage"), stage));
                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }, pageable);
        } else {
            page = daqProjectRepository.findAll(pageable);
        }
        return MSPage.newInstance(page);
    }
}
