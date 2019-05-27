package com.yx.demo.main.service;

import com.yx.demo.main.entity.UserDO;

/**
 * @author yx start
 * @create 2019/5/26,21:58
 */
public interface UserService {

    UserDO getByUsername(String username);
}
