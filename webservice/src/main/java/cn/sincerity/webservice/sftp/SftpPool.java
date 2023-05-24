package cn.sincerity.webservice.sftp;

import cn.sincerity.webservice.sftp.config.ClientProperties;
import cn.sincerity.webservice.sftp.config.PoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.DisposableBean;

import java.time.Duration;

/**
 * SftpPool: Sftp连接池
 *
 * @author Ht7_Sincerity
 * @date 2023/5/19
 */
@Slf4j
public class SftpPool implements DisposableBean {

    private GenericObjectPool<SftpClient> pool;

    public SftpPool(ClientProperties clientProperties, PoolProperties poolProperties) {
        this.pool = new GenericObjectPool<>(new PooledClientFactory(clientProperties), createPoolConfig(poolProperties));
        log.info("SftpPool created");
    }

    /**
     * 获取 Sftp 客户端对象
     *
     * @return sftp 客户端对象
     */
    public SftpClient borrowObject() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new SftpPoolException("Could not get a resource from the pool", e);
        }
    }

    /**
     * 失效 Sftp 客户端对象
     *
     * @param sftpClient sftp 客户端对象
     */
    public void invalidateObject(SftpClient sftpClient) {
        try {
            pool.invalidateObject(sftpClient);
        } catch (Exception e) {
            throw new SftpPoolException("Could not invalidate the broken resource", e);
        }
    }

    /**
     * 归还 Sftp 客户端对象
     *
     * @param sftpClient sftp 客户端对象
     */
    public void returnObject(SftpClient sftpClient) {
        try {
            pool.returnObject(sftpClient);
        } catch (Exception e) {
            throw new SftpPoolException("Could not return a resource from the pool", e);
        }
    }

    /**
     * 销毁 Sftp 连接池
     *
     * @throws Exception 异常
     */
    @Override
    public void destroy() throws Exception {
        try {
            if (pool != null) {
                pool.close();
            }
            log.info("SftpPool closed");
        } catch (Exception e) {
            log.error("SftpPool close failed", e);
        }
    }

    private GenericObjectPoolConfig<SftpClient> createPoolConfig(PoolProperties poolProperties) {
        GenericObjectPoolConfig<SftpClient> config = new GenericObjectPoolConfig<>();
        config.setMaxWait(Duration.ofMillis(poolProperties.getMaxWait()));
        config.setTestOnBorrow(poolProperties.isTestOnBorrow());
        config.setTestOnReturn(poolProperties.isTestOnReturn());
        config.setTestWhileIdle(poolProperties.isTestWhileIdle());
        config.setTimeBetweenEvictionRuns(Duration.ofMillis(poolProperties.getTimeBetweenEvictionRuns()));
        config.setMinEvictableIdleTime(Duration.ofMillis(poolProperties.getMinEvictableIdleTimeMillis()));
        config.setMinIdle(poolProperties.getMinIdle());
        config.setMaxIdle(poolProperties.getMaxIdle());
        config.setMaxTotal(poolProperties.getMaxActive());
        return config;
    }

    private static class PooledClientFactory extends BasePooledObjectFactory<SftpClient> {

        private final ClientProperties clientProperties;

        private PooledClientFactory(ClientProperties clientProperties) {
            this.clientProperties = clientProperties;
        }

        @Override
        public SftpClient create() throws Exception {
            return new SftpClient(clientProperties);
        }

        @Override
        public PooledObject<SftpClient> wrap(SftpClient sftpClient) {
            return new DefaultPooledObject<>(sftpClient);
        }

        @Override
        public void destroyObject(PooledObject<SftpClient> p) throws Exception {
            p.getObject().disconnect();
        }

        @Override
        public boolean validateObject(PooledObject<SftpClient> p) {
            return p.getObject().test();
        }
    }
}
