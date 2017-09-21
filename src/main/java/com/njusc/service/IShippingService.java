package com.njusc.service;

import com.github.pagehelper.PageInfo;
import com.njusc.common.ServerResponse;
import com.njusc.pojo.Shipping;

/**
 * Created by Echo on 17/9/21.
 */
public interface IShippingService {

    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
