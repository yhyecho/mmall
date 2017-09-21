package com.njusc.service;

import com.njusc.common.ServerResponse;
import com.njusc.vo.CartVo;
import org.springframework.stereotype.Service;

/**
 * Created by Echo on 17/9/21.
 */
@Service("iCartService")
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelect (Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
