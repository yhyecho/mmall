package com.njusc.service;

import com.njusc.common.ServerResponse;
import com.njusc.pojo.Category;

import java.util.List;

/**
 * Created by Echo on 17/9/19.
 */
public interface ICategoryService {

    /**
     * 新增商品分类
     * @param categoryName 分类名称
     * @param parentId 父级分类id
     * @return
     */
    ServerResponse addCategory(String categoryName, Integer parentId);

    /**
     * 更新商品分类名称
     * @param categoryId 分类id
     * @param categoryName 新商品分类名称
     * @return
     */
    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    /**
     * 获取商品分类的子分类
     * @param categoryId 分类id
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 根据分类id递归获当前分类id及其所有子分类id
     * @param categoryId 分类id
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
