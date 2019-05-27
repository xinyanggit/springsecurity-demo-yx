package com.yx.simple.datahavedbqq.dao;


import com.yx.simple.datahavedbqq.entity.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Long> {

    UserDO findByUsername(String username);

}
