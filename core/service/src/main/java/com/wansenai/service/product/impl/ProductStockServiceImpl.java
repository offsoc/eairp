/*
 * Copyright 2023-2025 EAIRP Team, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://opensource.wansenai.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.wansenai.service.product.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wansenai.dto.product.QueryProductStockKeepUnitDTO;
import com.wansenai.entities.product.ProductStock;
import com.wansenai.mappers.product.ProductStockMapper;
import com.wansenai.service.product.ProductStockService;
import com.wansenai.service.user.ISysUserService;
import com.wansenai.service.warehouse.WarehouseService;
import com.wansenai.utils.redis.RedisUtil;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.product.ProductStockKeepUnitVO;
import com.wansenai.vo.product.ProductStockVO;
import com.wansenai.vo.report.ProductStockSkuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductStockServiceImpl extends ServiceImpl<ProductStockMapper, ProductStock> implements ProductStockService {

    private final WarehouseService warehouseService;

    private final ProductStockMapper productStockMapper;

    private final RedisUtil redisUtil;

    private final ISysUserService userService;

    public ProductStockServiceImpl(WarehouseService warehouseService, ProductStockMapper productStockMapper, RedisUtil redisUtil, ISysUserService userService) {
        this.warehouseService = warehouseService;
        this.productStockMapper = productStockMapper;
        this.redisUtil = redisUtil;
        this.userService = userService;
    }

    @Override
    public IPage<ProductStockKeepUnitVO> getProductExtendPriceInfo(QueryProductStockKeepUnitDTO priceDTO) {
        var page = new Page<QueryProductStockKeepUnitDTO>(priceDTO.getPage(), priceDTO.getPageSize());
        return productStockMapper.getProductSkuList(page, priceDTO);
    }

    @Override
    public Response<ProductStockKeepUnitVO> getProductByBarCode(String barCode, Long warehouseId) {
        var data = productStockMapper.getProductSkuByBarCode(barCode, warehouseId);
        if (data == null) {
            return Response.responseData(null);
        }
        return Response.responseData(data);
    }

    @Override
    public List<ProductStockVO> getProductStockList(Long productSukId) {
        var productStockVos = new ArrayList<ProductStockVO>();

        var productStocks = lambdaQuery()
                .eq(ProductStock::getProductSkuId, productSukId)
                .list();
        productStocks.forEach(productStock -> {
            ProductStockVO productStockVO = new ProductStockVO();
            productStockVO.setProductStockId(productStock.getId());
            BeanUtils.copyProperties(productStock, productStockVO);
            productStockVos.add(productStockVO);

            // set warehouse name
            var warehouse = warehouseService.getById(productStock.getWarehouseId());
            productStockVO.setWarehouseName(warehouse.getWarehouseName());
        });

        return productStockVos;
    }

    @Override
    public Response<List<ProductStockSkuVO>> getProductStockSkuList() {
        // 先查redis缓存，如果缓存里没有再去数据库中查询
//        if (redisUtil.hasKey("productStockSkuList:" + userService.getCurrentTenantId())) {
//            var data = redisUtil.get("productStockSkuList:" + userService.getCurrentTenantId());
//            return Response.responseData((List<ProductStockSkuVO>) data);
//        } else {
//            var data = productStockMapper.getProductStockList();
//            redisUtil.set("productStockSkuList:" + userService.getCurrentTenantId(), data);
//            return Response.responseData(data);
//        }
        var data = productStockMapper.getProductStockList();
        redisUtil.set("productStockSkuList:" + userService.getCurrentTenantId(), data);
        return Response.responseData(data);
    }

    @Override
    public Boolean removeBySkuId(Long skuId) {
        return lambdaUpdate().eq(ProductStock::getProductSkuId, skuId).remove();
    }
}
