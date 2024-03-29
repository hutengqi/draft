package cn.sincerity.webservice.sftp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * PoolProperties
 *
 * @author Ht7_Sincerity
 * @date 2023/5/22
 */
@Data
@ConfigurationProperties("sftp.pool")
public class PoolProperties {

    /**
     * Target for the minimum number of idle connections to maintain in the pool.
     * This setting only has an effect if both it and time between eviction runs are positive.
     */
    private int minIdle = 1;

    /**
     * Maximum number of "idle" connections in the pool. Use a negative value to
     * indicate an unlimited number of idle connections.
     */
    private int maxIdle = 8;

    /**
     * Maximum number of connections that can be allocated by the pool at a given
     * time. Use a negative value for no limit.
     */
    private int maxActive = 8;

    /**
     * Maximum amount of time a connection allocation should block before throwing an
     * exception when the pool is exhausted. Use a negative value to block
     * indefinitely.
     */
    private long maxWait = -1;

    /**
     * Whether objects borrowed from the pool will be validated. Validation is
     * performed by the validateConnect() method of the SftpClient. If the object
     * fails to validate, it will be removed from the pool and destroyed, and a
     * new attempt will be made to borrow an object from the pool.
     */
    private boolean testOnBorrow = true;

    /**
     * Whether objects borrowed from the pool will be validated when they are returned
     * to the pool. Validation is performed by the validateConnect() method of the
     * SftpClient. Returning objects that fail validation are destroyed rather then
     * being returned the pool.
     */
    private boolean testOnReturn = false;

    /**
     * Whether objects sitting idle in the pool will be validated by the idle object
     * evictor. Validation is performed by the validateConnect() method of the
     * SftpClient. If the object fails to validate, it will be removed from the pool
     * and destroyed.
     */
    private boolean testWhileIdle = true;

    /**
     * Time between runs of the idle object evictor thread. When positive, the idle
     * object evictor thread starts, otherwise no idle object eviction is performed.
     */
    private long timeBetweenEvictionRuns = 1000L * 60L * 10L;

    /**
     * Returns the minimum amount of time an object may sit idle in the pool before
     * it is eligible for eviction by the idle object evictor. When non-positive,
     * no objects will be evicted from the pool due to idle time alone.
     */
    private long minEvictableIdleTimeMillis = 1000L * 60L * 30L;

    /**
     * Sets the target for the minimum number of idle objects to maintain in
     * each of the keyed sub-pools. This setting only has an effect if it is
     * positive and timeBetweenEvictionRuns is greater than zero. If this is
     * the case, an attempt is made to ensure that each sub-pool has the required
     * minimum number of instances during idle object eviction runs.
     * If the configured value of minIdlePerKey is greater than the configured
     * value for maxIdlePerKey then the value of maxIdlePerKey will be used
     * instead.
     */
    private int minIdlePerKey = 1;

    /**
     * Sets the cap on the number of "idle" instances per key in the pool.
     * If maxIdlePerKey is set too low on heavily loaded systems it is possible
     * you will see objects being destroyed and almost immediately new objects
     * being created. This is a result of the active threads momentarily
     * returning objects faster than they are requesting them, causing the
     * number of idle objects to rise above maxIdlePerKey. The best value for
     * maxIdlePerKey for heavily loaded system will vary but the default is a
     * good starting point.
     */
    private int maxIdlePerKey = 8;

    /**
     * Sets the limit on the number of object instances allocated by the pool
     * (checked out or idle), per key. When the limit is reached, the sub-pool
     * is said to be exhausted. A negative value indicates no limit.
     */
    private int maxActivePerKey = 8;
}
