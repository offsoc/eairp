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
package com.wansenai.bo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.wansenai.NoArg
import lombok.Data
import java.math.BigDecimal

@NoArg
@Data
data class AdvanceChargeDataBO(

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    val accountId: Long,

    val accountName: String,

    @JsonSerialize(using = BigDecimalSerializerBO::class)
    val amount : BigDecimal,

    val remark : String? = null,
)
