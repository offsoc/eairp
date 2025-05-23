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
package com.wansenai.api.warehouse;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.warehouse.OtherStorageDTO;
import com.wansenai.dto.warehouse.QueryOtherStorageDTO;
import com.wansenai.service.warehouse.OtherStorageService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.warehouse.OtherStorageDetailVO;
import com.wansenai.vo.warehouse.OtherStorageVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("warehouse/otherStorage")
public class OtherStorageController {

    private final OtherStorageService otherStorageService;

    public OtherStorageController(OtherStorageService otherStorageService) {
        this.otherStorageService = otherStorageService;
    }

    @PostMapping("addOrUpdate")
    public Response<String> addOrUpdateOtherStorage(@RequestBody OtherStorageDTO otherStorageDTO) {
        return otherStorageService.addOrUpdateOtherStorage(otherStorageDTO);
    }

    @PostMapping("pageList")
    public Response<Page<OtherStorageVO>> getOtherStoragePageList(@RequestBody QueryOtherStorageDTO queryOtherStorageDTO) {
        return otherStorageService.getOtherStoragePageList(queryOtherStorageDTO);
    }

    @GetMapping("getDetailById/{id}")
    public Response<OtherStorageDetailVO> getOtherStorageDetailById(@PathVariable("id") Long id) {
        return otherStorageService.getOtherStorageDetail(id);
    }

    @PutMapping("deleteByIds")
    public Response<String> deleteOtherStorageByIds(@RequestParam("ids") List<Long> ids) {
        return otherStorageService.deleteBatchOtherStorage(ids);
    }

    @PutMapping("updateStatusByIds")
    public Response<String> updateOtherStorageStatusByIds(@RequestParam("ids") List<Long> ids, @RequestParam("status") Integer status) {
        return otherStorageService.updateOtherStorageStatus(ids, status);
    }

    @GetMapping("export")
    public void exportOtherStorage(@ModelAttribute QueryOtherStorageDTO queryOtherStorageDTO, HttpServletResponse response) throws Exception {
        otherStorageService.exportOtherStorage(queryOtherStorageDTO, response);
    }

    @GetMapping("exportDetail/{receiptNumber}")
    public void exportOtherStorageDetail(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) throws Exception {
        otherStorageService.exportOtherStorageDetail(receiptNumber, response);
    }

}
