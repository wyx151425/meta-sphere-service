package org.metasphere.adminservice.service.daq.impl;

import org.metasphere.adminservice.model.dto.MSPage;
import org.metasphere.adminservice.model.pojo.daq.DaqSpider;
import org.metasphere.adminservice.repository.daq.DaqSpiderRepository;
import org.metasphere.adminservice.service.daq.DaqSpiderService;
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
public class DaqSpiderServiceImpl implements DaqSpiderService {

    @Autowired
    private DaqSpiderRepository daqSpiderRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDaqSpider(DaqSpider daqSpider) {
        daqSpiderRepository.save(daqSpider);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDaqSpiderById(Long id) {
        daqSpiderRepository.deleteById(id);
    }

    @Override
    public List<DaqSpider> findDaqSpiders() {
        return daqSpiderRepository.findAll();
    }

    @Override
    public MSPage<DaqSpider> findDaqSpidersByPagination(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<DaqSpider> page = daqSpiderRepository.findAll(pageRequest);
        return MSPage.newInstance(page);
    }

    @Override
    public List<DaqSpider> findDaqSpidersByIds(List<Long> ids) {
        return daqSpiderRepository.findAllById(ids);
    }
}
