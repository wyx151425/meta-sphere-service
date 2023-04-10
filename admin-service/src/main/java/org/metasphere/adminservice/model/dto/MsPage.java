package org.metasphere.adminservice.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2022-11-14 11:37
 * @Modified By:
 */
@Data
public class MsPage<T> {

    /**
     * 分页页码
     */
    Integer pageNum;

    /**
     * 单页数据量
     */
    Integer pageSize;

    /**
     * 单页数据
     */
    List<T> data;

    /**
     * 总页数
     */
    Integer totalPages;

    /**
     * 总数据量
     */
    Long totalData;

    public MsPage(Page<T> page) {
        this.pageNum = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.data = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalData = page.getTotalElements();
    }

    public static <T> MsPage<T> newInstance(Page<T> page) {
        return new MsPage<>(page);
    }
}
