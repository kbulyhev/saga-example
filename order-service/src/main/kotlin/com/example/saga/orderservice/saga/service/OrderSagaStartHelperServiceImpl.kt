package com.example.saga.orderservice.saga.service

import com.example.saga.orderservice.constants.OrderSagaConstants.CURRENT_SERVICE_PATH_ORDERS
import com.example.saga.orderservice.repository.SagaOrderRepository
import com.example.saga.orderservice.saga.dto.PaymentRequestDTO
import com.example.saga.orderservice.saga.dto.SagaCompensationDTO
import com.example.saga.orderservice.saga.model.SagaTransactionModelRequest
import com.example.saga.orderservice.saga.model.SagaTransactionModelResponse
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.kmao.saga.sagahelperspringbootstarter.api.helper.StartSagaHelperService
import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
import javax.transaction.Transactional

@Service
class OrderSagaStartHelperServiceImpl :
    StartSagaHelperService<SagaTransactionModelRequest, SagaTransactionModelResponse> {

    @Autowired
    private lateinit var sagaOrderRepository: SagaOrderRepository

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Transactional
    override fun executeTransaction(request: SagaTransactionModelRequest): SagaTransactionModelResponse {
        val orderEntity = request.orderEntity
        val savedOrder = sagaOrderRepository.save(orderEntity)

        return SagaTransactionModelResponse(savedOrder)
    }

    override fun getCode(): String {
        return CURRENT_SERVICE_PATH_ORDERS
    }

    override fun getCompensationPayload(transactionResult: SagaTransactionModelResponse): Any {
        val orderId = checkNotNull(transactionResult.savedOrder.orderId) {
            "order id should not be null"
        }
        return SagaCompensationDTO(orderId)
    }

    override fun getRequestPayloadToNextService(transactionResult: SagaTransactionModelResponse): BaseServiceDTO {
        val paymentDTO = modelMapper.map(transactionResult.savedOrder, PaymentRequestDTO::class.java)
        return paymentDTO
    }
}