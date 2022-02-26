package com.example.saga.deliveryservice.saga.service

import com.example.saga.deliveryservice.entity.SagaDeliveryEntity
import com.example.saga.deliveryservice.repository.SagaDeliverRepository
import com.example.saga.deliveryservice.saga.constants.DeliverSagaConstants.CURRENT_SERVICE_PATH_KEY
import com.example.saga.deliveryservice.saga.dto.SagaDeliverCompensationDTO
import com.example.saga.deliveryservice.saga.dto.SagaDeliverRequestDTO
import com.example.saga.deliveryservice.saga.model.SagaCompleteTransactionRequest
import com.example.saga.deliveryservice.saga.model.SagaDeliverTransactionResponse
import org.springframework.stereotype.Service
import ru.kmao.saga.sagahelperspringbootstarter.api.helper.SagaCompletingHelperService
import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import java.time.OffsetDateTime
import javax.transaction.Transactional

@Service
class SagaDeliverService(
    private val sagaDeliverRepository: SagaDeliverRepository
) : SagaCompletingHelperService<SagaCompleteTransactionRequest, SagaDeliverTransactionResponse> {

    override fun getCompensationPayload(transactionResult: SagaDeliverTransactionResponse): Any {

        val deliverId = checkNotNull(transactionResult.savedDeliverEntity.deliverId) {
            "deliver_id should not be null"
        }
        return SagaDeliverCompensationDTO(deliverId)
    }

    override fun getCode(): String {
        return CURRENT_SERVICE_PATH_KEY
    }

    @Transactional
    override fun executeTransaction(
        request: BaseServiceDTO,
        requestTransaction: SagaCompleteTransactionRequest?
    ): SagaDeliverTransactionResponse {
        val sagaDeliverRequestDTO = request as SagaDeliverRequestDTO

        val sagaDeliveryEntity = SagaDeliveryEntity(
            null, sagaDeliverRequestDTO.paymentId, OffsetDateTime.now(), OffsetDateTime.now(),
            sagaDeliverRequestDTO.orderValidTo, "random address"
        )

        val savedSagaDelverEntity = sagaDeliverRepository.save(sagaDeliveryEntity)

        return SagaDeliverTransactionResponse(savedSagaDelverEntity)
    }
}