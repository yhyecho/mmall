package com.njusc.service;

import com.github.pagehelper.PageInfo;
import com.njusc.common.ServerResponse;
import com.njusc.vo.OrderVo;

/**
 * Created by Echo on 17/9/26.
 */
public interface IOrderService {

    ServerResponse createOrder(Integer userId, Integer shippindId);

    ServerResponse<String> cancel(Integer userId, Long orderNo);

    ServerResponse getOrderCartProduct(Integer userId);

    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);

    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    // backend
    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<OrderVo> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSendGoods(Long orderNo);
}
