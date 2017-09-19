package com.njusc.controller.backend;

import com.njusc.common.Const;
import com.njusc.common.ResponseCode;
import com.njusc.common.ServerResponse;
import com.njusc.pojo.User;
import com.njusc.service.ICategoryService;
import com.njusc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Echo on 17/9/19.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 新增分类
     * @param session 会话信息
     * @param categoryName 分类名称
     * @param parentId 父分类ID
     * @return
     */
    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,
                                      String categoryName,
                                      @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        // 校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 是管理员
            // 增加我们处理分类的逻辑
            return iCategoryService.addCategory(categoryName, parentId);
        } else {
            return ServerResponse.createByErrorMessage("无操作权限, 需要管理员权限");
        }
    }

    /**
     * 更新分类名称
     * @param session 会话信息
     * @param categoryId 分类ID
     * @param categoryName 新分类名称
     * @return
     */
    @RequestMapping(value = "set_category_name.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 更新categoryName
            return iCategoryService.updateCategoryName(categoryId, categoryName);
        } else {
            return ServerResponse.createByErrorMessage("无操作权限, 需要管理员权限");
        }
    }

    /**
     * 获取分类的子分类(平级获取,不递归)
     * @param session 会话信息
     * @param categoryId 分类ID
     * @return
     */
    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 查询子节点的category信息,并且不递归,保持平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无操作权限, 需要管理员权限");
        }
    }

    /**
     * 递归获取当前分类及其子分类
     * @param session 会话信息
     * @param categoryId 分类ID
     * @return
     */
    @RequestMapping(value = "get_deep_category.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,
                                                      @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            // 查询当前节点的id和递归子节点的id
            // 0 -> 10000 -> 100000
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        } else {
            return ServerResponse.createByErrorMessage("无操作权限, 需要管理员权限");
        }
    }

}
