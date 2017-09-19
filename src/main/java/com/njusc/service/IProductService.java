package com.njusc.service;

import com.njusc.common.ServerResponse;
import com.njusc.pojo.Product;
import com.njusc.vo.ProductDetailVo;

/**
 * Created by Echo on 17/9/19.
 */
public interface IProductService {

    /**
     * 新增或更新商品
     * @param product 商品对象
     * @return
     */
    ServerResponse saveOrUpdateProduct(Product product);

    /**
     * 更新商品销售状态
     * @param productId 商品ID
     * @param status 状态
     * @return
     */
    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse getProductList(int pageNum, int pageSize);
}
