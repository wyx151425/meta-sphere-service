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
public class MSPage<T> {

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

    public MSPage(Page<T> page) {
        this.pageNum = page.getNumber();
        this.pageSize = page.getSize();
        this.data = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalData = page.getTotalElements();
    }

    public static <T> MSPage<T> newInstance(Page<T> page) {
        return new MSPage<>(page);
    }
}
