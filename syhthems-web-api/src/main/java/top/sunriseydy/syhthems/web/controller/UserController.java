package top.sunriseydy.syhthems.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.sunriseydy.syhthems.common.enums.ResultEnum;
import top.sunriseydy.syhthems.common.util.ResultUtils;
import top.sunriseydy.syhthems.common.vo.ResultVO;
import top.sunriseydy.syhthems.db.model.DataPoint;
import top.sunriseydy.syhthems.db.model.DataStream;
import top.sunriseydy.syhthems.db.model.Device;
import top.sunriseydy.syhthems.db.model.User;
import top.sunriseydy.syhthems.db.service.DataPointService;
import top.sunriseydy.syhthems.db.service.DataStreamService;
import top.sunriseydy.syhthems.db.service.DeviceService;
import top.sunriseydy.syhthems.db.service.UserService;
import top.sunriseydy.syhthems.db.vo.DataPointQueryVO;
import top.sunriseydy.syhthems.web.vo.DataStreamVO;
import top.sunriseydy.syhthems.web.vo.DeviceVO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dengmin
 * @date 2019-05-27 15:42
 */
@RestController
@RequestMapping("/web/api/user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;


    @PostMapping("/list")
    public ResultVO getUsers() {
        return ResultUtils.success(userService.selectByUserList());
    }


    @GetMapping
    public ResultVO getUser(@RequestParam(required = true) Long userId) {
        return ResultUtils.success(userService.selectByUser(userId));
    }

    @PostMapping
    public ResultVO addUser(@Validated User user) {
        user.setUserId(null);
        userService.insertUser(user);
        return ResultUtils.success();
    }



    @PatchMapping("/{did}")
    public ResultVO updateUser(@PathVariable(required = true, name = "did") Long userId,
                                 @Validated User user) {
        user.setUserId(userId);
        userService.updateUser(user);
        return ResultUtils.success();
    }

    @DeleteMapping("/{did}")
    public ResultVO deleteUser(@PathVariable(required = true, name = "did") Long userId) {
        userService.deleteUserByPrimaryKey(userId);
        return ResultUtils.success();
    }

}
