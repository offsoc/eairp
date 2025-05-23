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
package com.wansenai.api.financial;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wansenai.dto.financial.AddOrUpdatePaymentDTO;
import com.wansenai.dto.financial.QueryPaymentDTO;
import com.wansenai.service.financial.PaymentReceiptService;
import com.wansenai.utils.response.Response;
import com.wansenai.vo.financial.PaymentDetailVO;
import com.wansenai.vo.financial.PaymentVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RestController
@RequestMapping("/financial/payment")
public class PaymentReceiptController {

    private final PaymentReceiptService paymentReceiptService;

    public PaymentReceiptController(PaymentReceiptService paymentReceiptService) {
        this.paymentReceiptService = paymentReceiptService;
    }

    @PostMapping("addOrUpdate")
    public Response<String> addOrUpdatePaymentReceipt(@RequestBody AddOrUpdatePaymentDTO addOrUpdatePaymentDTO) {
        return paymentReceiptService.addOrUpdatePaymentReceipt(addOrUpdatePaymentDTO);
    }

    @PostMapping("pageList")
    public Response<Page<PaymentVO>> getPaymentReceiptPageList(@RequestBody QueryPaymentDTO queryPaymentDTO) {
        return paymentReceiptService.getPaymentReceiptPageList(queryPaymentDTO);
    }

    @GetMapping("getDetailById/{id}")
    public Response<PaymentDetailVO> getPaymentReceiptDetailById(@PathVariable("id") Long id) {
        return paymentReceiptService.getPaymentReceiptDetail(id);
    }

    @PutMapping("deleteByIds")
    public Response<String> deletePaymentReceiptByIds(@RequestParam("ids") List<Long> ids) {
        return paymentReceiptService.deleteBatchPaymentReceipt(ids);
    }

    @PutMapping("updateStatusByIds")
    public Response<String> updatePaymentReceiptStatusByIds(@RequestParam("ids") List<Long> ids, @RequestParam("status") Integer status) {
        return paymentReceiptService.updatePaymentReceiptStatus(ids, status);
    }

    @GetMapping("export")
    public void exportPaymentReceipt(@ModelAttribute QueryPaymentDTO queryPaymentDTO, HttpServletResponse response) {
        paymentReceiptService.exportPaymentReceipt(queryPaymentDTO, response);
    }

    @GetMapping("exportDetail/{receiptNumber}")
    public void exportPaymentReceiptDetail(@PathVariable("receiptNumber") String receiptNumber, HttpServletResponse response) {
        paymentReceiptService.exportPaymentReceiptDetail(receiptNumber, response);
    }
}
