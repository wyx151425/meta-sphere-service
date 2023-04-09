package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.DAQSpider;
import org.metasphere.adminservice.repository.DAQSpiderRepository;
import org.metasphere.adminservice.service.DAQSpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2023-04-07 21:51
 * @Modified By:
 */
@Service(value = "daqSpiderService")
public class DAQSpiderServiceImpl implements DAQSpiderService {

    @Autowired
    private DAQSpiderRepository daqSpiderRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDAQSpider(DAQSpider daqSpider) {
        daqSpiderRepository.save(daqSpider);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDAQSpiderById(Long id) {
        daqSpiderRepository.deleteById(id);
    }

    @Override
    public List<DAQSpider> findDAQSpiders() {
        return daqSpiderRepository.findAll();
    }

    @Override
    public MSPage<DAQSpider> findDAQSpidersByPagination(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<DAQSpider> page = daqSpiderRepository.findAll(pageRequest);
        return MSPage.newInstance(page);
    }

    @Override
    public List<DAQSpider> findDAQSpidersByIds(List<Long> ids) {
        return daqSpiderRepository.findAllById(ids);
    }
}
