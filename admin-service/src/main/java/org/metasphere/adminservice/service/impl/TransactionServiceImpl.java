package org.metasphere.adminservice.service.impl;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2024-01-30 11:28
 * @Modified By:
 */
import org.metasphere.adminservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.Callable;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private PlatformTransactionManager transactionManager;

    private final ThreadLocal<TransactionStatus> threadLocal = new ThreadLocal<>();

    @Override
    public void begin() {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        threadLocal.set(transactionManager.getTransaction(transactionDefinition));
    }

    @Override
    public void commit() {
        transactionManager.commit(threadLocal.get());
        threadLocal.remove();
    }

    @Override
    public void rollback() {
        transactionManager.rollback(threadLocal.get());
        threadLocal.remove();
    }

    @Override
    public <V> V execute(Callable<V> callable) {
        try {
            begin();
            V result = callable.call();
            commit();
            return result;
        } catch (Exception e) {
            rollback();
            throw new RuntimeException(e);
        }
    }
}
