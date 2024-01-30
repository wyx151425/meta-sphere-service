package org.metasphere.adminservice.service;

import java.util.concurrent.Callable;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-30 11:23
 * @Modified By:
 */
public interface TransactionService {
    /**
     * 开启事务
     */
    void begin();

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();

    /**
     * 在事务中执行回调函数
     *
     * @param callable 回调函数
     * @return 回调函数返回结果
     */
    <V> V execute(Callable<V> callable);
}
