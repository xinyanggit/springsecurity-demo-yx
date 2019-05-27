package com.yx.simple.datahavedbqq.service;


import com.yx.simple.datahavedbqq.entity.UserDO;

/**
 * @author yx start
 * @create 2019/5/26,21:58
 */
public interface UserService {

    UserDO getByUsername(String username);
}
