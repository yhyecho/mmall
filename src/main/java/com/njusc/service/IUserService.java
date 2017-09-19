package com.njusc.service;

import com.njusc.common.ServerResponse;
import com.njusc.pojo.User;

/**
 * Created by Echo on 17/9/18.
 */
public interface IUserService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    ServerResponse<User> login(String username, String password);

    /**
     * 用户注册
     * @param user 用户对象
     * @return
     */
    ServerResponse<String> register(User user);

    /**
     * 注册表单校验
     * @param str 需要校验的值
     * @param type 校验类型 (用户名, 邮箱等)
     * @return
     */
    ServerResponse<String> checkValid(String str, String type);

    /**
     * 获取找回密码的密保问题
     * @param username 用户名
     * @return
     */
    ServerResponse selectQuestion(String username);

    /**
     * 根据密保问题和答案修改密码(未登录状态)
     * @param username 用户名
     * @param question 密保问题
     * @param answer 密保答案
     * @return
     */
    ServerResponse<String> checkAnswer(String username, String question, String answer);

    /**
     * 根据用户名和token修改密码(未登录状态)
     * @param username 用户名
     * @param passwordNew 新密码
     * @param forgetToken 修改密码所需的令牌
     * @return
     */
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    /**
     * 根据旧密码修改用户密码(登录状态)
     * @param passwordOld 旧密码
     * @param passwordNew 新密码
     * @param user 当前登录用户
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    /**
     * 根据当前登录用户更新用户信息
     * @param user 当前登录用户
     * @return
     */
    ServerResponse<User> updateInformation(User user);

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);
}
