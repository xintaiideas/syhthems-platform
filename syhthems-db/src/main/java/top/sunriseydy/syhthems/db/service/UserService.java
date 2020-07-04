package top.sunriseydy.syhthems.db.service;

import top.sunriseydy.syhthems.db.model.Device;
import top.sunriseydy.syhthems.db.model.User;

import java.util.List;

/**
 * @author dengmin
 * @date 2019-03-26 15:30
 */
public interface UserService extends BaseService<User> {

    /**
     * 根据User ID 删除User
     * @param userId
     */
    void deleteUserByPrimaryKey(Long userId);
    /**
     * 新增 user
     * @param user
     */
    void insertUser(User user);
    /**
     * 更新 user
     * @param user
     */
    void updateUser(User user);



    /**
     * 查询用户
     * @return
     */
    List<User> selectByUserList();

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    List<User> selectByUser(Long userId);

    /**
     * 根据用户名检测用户是否存在
     *
     * @param username
     * @return
     */
    Boolean existsWithUsername(String username);

    /**
     * 根据用户名来查询用户
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 根据邮箱来查询用户
     * @param email
     * @return
     */
    User selectByEmail(String email);

    /**
     * 根据用户邮箱检测用户是否存在
     * @param email
     * @return
     */
    Boolean existsWithEmail(String email);

    /**
     * 注册用户
     *
     * @param newUser
     * @return
     */
    User registerUser(User newUser);

    /**
     * 更新用户中的登录相关字段
     *
     * @param user
     * @return
     */
    int updateLogin(User user);

    /**
     * 处理用户的登出
     */
    void userLogout(String username);
}
