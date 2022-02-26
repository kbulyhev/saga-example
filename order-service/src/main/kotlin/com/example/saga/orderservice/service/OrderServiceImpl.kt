package com.example.saga.orderservice.service

import com.example.saga.orderservice.api.OrderService
import com.example.saga.orderservice.constants.OrderSagaConstants.CURRENT_SERVICE_PATH_ORDERS
import com.example.saga.orderservice.constants.OrderSagaConstants.TARGET_PAYMENT_SERVICE
import com.example.saga.orderservice.constants.OrderSagaConstants.TARGET_PAYMENT_SERVICE_PATH
import com.example.saga.orderservice.dto.CreateOrderDTO
import com.example.saga.orderservice.entity.SagaOrderEntity
import com.example.saga.orderservice.saga.dto.PaymentRequestDTO
import com.example.saga.orderservice.saga.model.SagaTransactionModelRequest
import com.example.saga.orderservice.saga.model.SagaTransactionModelResponse
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import ru.kmao.saga.sagahelperspringbootstarter.api.StartSagaService
import ru.kmao.saga.sagahelperspringbootstarter.builder.SagaParamsModel.builder

@Service
class OrderServiceImpl(
    private val orderStartSagaService: StartSagaService<SagaTransactionModelRequest, SagaTransactionModelResponse>,
    private val modelMapper: ModelMapper
) : OrderService {

    override fun createOrder(createOrderDTO: CreateOrderDTO) {
        val orderEntity = modelMapper.map(createOrderDTO, SagaOrderEntity::class.java)
        val sagaTransactionModelRequest = SagaTransactionModelRequest(orderEntity)

        val sagaParamsModel = builder()
            .targetServiceKey(TARGET_PAYMENT_SERVICE)
            .currentServicePathKey(CURRENT_SERVICE_PATH_ORDERS)
            .sagaHelperServiceCode(CURRENT_SERVICE_PATH_ORDERS)
            .targetServicePathKey(TARGET_PAYMENT_SERVICE_PATH)
            .build()

        orderStartSagaService.startSaga(sagaTransactionModelRequest, sagaParamsModel)
    }
}