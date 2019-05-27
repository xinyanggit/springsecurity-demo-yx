package com.yx.demo.main.service.serviceImpl;

import com.yx.demo.main.dao.UserRepository;
import com.yx.demo.main.entity.UserDO;
import com.yx.demo.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yx start
 * @create 2019/5/26,22:06
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDO getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
