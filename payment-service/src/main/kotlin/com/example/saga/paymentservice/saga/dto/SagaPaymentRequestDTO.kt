package com.example.saga.paymentservice.saga.dto

import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import java.time.OffsetDateTime

data class SagaPaymentRequestDTO(
    var orderId: Long,

    var name: String,

    var price: String,

    var path: String,

    var validTo: OffsetDateTime
): BaseServiceDTO()