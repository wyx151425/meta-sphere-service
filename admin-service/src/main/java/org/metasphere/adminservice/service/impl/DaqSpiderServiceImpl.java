package org.metasphere.adminservice.service.impl;

import org.metasphere.adminservice.model.dto.MsPage;
import org.metasphere.adminservice.model.pojo.DaqSpider;
import org.metasphere.adminservice.repository.DaqSpiderRepository;
import org.metasphere.adminservice.service.DaqSpiderService;
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
    public MsPage<DaqSpider> findDaqSpidersByPagination(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<DaqSpider> page = daqSpiderRepository.findAll(pageRequest);
        return MsPage.newInstance(page);
    }

    @Override
    public List<DaqSpider> findDaqSpidersByIds(List<Long> ids) {
        return daqSpiderRepository.findAllById(ids);
    }
}
