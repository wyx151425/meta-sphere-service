package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.exception.MSException;
import org.metasphere.adminservice.model.pojo.DAQTask;
import org.metasphere.adminservice.model.pojo.DAQTaskKeyword;
import org.metasphere.adminservice.repository.DAQTaskKeywordRepository;
import org.metasphere.adminservice.service.DAQTaskKeywordService;
import org.metasphere.adminservice.service.DAQTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private DAQTaskService daqTaskService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQTaskKeywords(Long daqTaskId, List<String> keywords) {
        DAQTask daqTask = daqTaskService.findDAQTaskById(daqTaskId);

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
    public List<DAQTaskKeyword> findDAQKeywordsByDAQProject(Long daqProjectId) {
        return daqTaskKeywordRepository.findAllByProjectId(daqProjectId);
    }
}
