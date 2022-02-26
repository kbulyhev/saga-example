package com.example.saga.paymentservice.saga.service

import com.example.saga.paymentservice.entity.SagaPaymentEntity
import com.example.saga.paymentservice.repository.SagaPaymentsRepository
import com.example.saga.paymentservice.saga.constants.PaymentSagaConstants
import com.example.saga.paymentservice.saga.dto.SagaDeliverDTO
import com.example.saga.paymentservice.saga.dto.SagaPaymentCompensationDTO
import com.example.saga.paymentservice.saga.dto.SagaPaymentRequestDTO
import com.example.saga.paymentservice.saga.model.SagaPaymentTransactionRequest
import com.example.saga.paymentservice.saga.model.SagaPaymentTransactionResponse
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.kmao.saga.sagahelperspringbootstarter.api.helper.SagaContinuationHelperService
import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import java.time.OffsetDateTime
import java.util.UUID
import javax.transaction.Transactional

@Service
class SagaPaymentService(
    private val paymentRepository: SagaPaymentsRepository
) : SagaContinuationHelperService<SagaPaymentTransactionRequest, SagaPaymentTransactionResponse> {

    @Autowired
    private lateinit var modelMapper: ModelMapper

    override fun getCompensationPayload(transactionResult: SagaPaymentTransactionResponse): Any {
        val paymentId = checkNotNull(transactionResult.paymentEntity.paymentId) {
            "payment id shoild not be null"
        }
        return SagaPaymentCompensationDTO(paymentId)
    }

    override fun getRequestPayloadToNextService(transactionResult: SagaPaymentTransactionResponse): BaseServiceDTO {
        val paymentEntity = transactionResult.paymentEntity

        val paymentId = checkNotNull(paymentEntity.paymentId)

        return SagaDeliverDTO(
            paymentId,
            paymentEntity.cost,
            paymentEntity.timestamp,
            paymentEntity.paymentNumber,
            paymentEntity.orderId,
            paymentEntity.orderValidTo
        )
    }

    override fun getCode(): String {
        return PaymentSagaConstants.CURRENT_SERVICE_PATH_KEY
    }

    @Transactional
    override fun executeTransaction(
        request: BaseServiceDTO,
        requestTransaction: SagaPaymentTransactionRequest?
    ): SagaPaymentTransactionResponse {
        val sagaPaymentRequestDTO = request as SagaPaymentRequestDTO

        val sagaPaymentEntity = SagaPaymentEntity(
            null,
            sagaPaymentRequestDTO.price, OffsetDateTime.now(), UUID.randomUUID().toString(),
            sagaPaymentRequestDTO.orderId, sagaPaymentRequestDTO.validTo
        )

        val savedPaymentEntity = paymentRepository.save(sagaPaymentEntity)

        return SagaPaymentTransactionResponse(paymentEntity = savedPaymentEntity)
    }

}