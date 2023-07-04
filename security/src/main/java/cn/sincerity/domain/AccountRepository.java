package cn.sincerity.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * AccountRepository
 *
 * @author Ht7_Sincerity
 * @date 2023/6/21
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(String username);
}
