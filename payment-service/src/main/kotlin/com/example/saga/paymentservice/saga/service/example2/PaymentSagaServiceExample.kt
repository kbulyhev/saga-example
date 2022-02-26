//package com.example.saga.paymentservice.saga.service.example2
//
//import com.example.saga.paymentservice.saga.constants.PaymentSagaConstants
//import com.example.saga.paymentservice.saga.dto.SagaDeliverDTO
//import com.example.saga.paymentservice.saga.dto.SagaPaymentCompensationDTO
//import com.example.saga.paymentservice.saga.model.SagaPaymentTransactionRequest
//import com.example.saga.paymentservice.saga.model.SagaPaymentTransactionResponse
//import org.springframework.stereotype.Service
//import ru.kmao.saga.sagahelperspringbootstarter.dto.BaseServiceDTO
//import javax.transaction.Transactional
//
//@Service
//class PaymentSagaServiceExample {
//
//    fun getCompensationPayload(transactionResult: SagaPaymentTransactionResponse): Any {
//        val paymentId = checkNotNull(transactionResult.paymentEntity.paymentId) {
//            "payment id shoild not be null"
//        }
//        return SagaPaymentCompensationDTO(paymentId)
//    }
//
//    fun getRequestPayloadToNextService(transactionResult: SagaPaymentTransactionResponse): BaseServiceDTO {
//        val paymentEntity = transactionResult.paymentEntity
//
//        val paymentId = checkNotNull(paymentEntity.paymentId)
//
//        return SagaDeliverDTO(
//            paymentId,
//            paymentEntity.cost,
//            paymentEntity.timestamp,
//            paymentEntity.paymentNumber,
//            paymentEntity.orderId,
//            paymentEntity.orderValidTo
//        )
//    }
//
//    fun getCode(): String {
//        return PaymentSagaConstants.CURRENT_SERVICE_PATH_KEY
//    }
//
//    @Transactional
//    @Saga
//    fun executeTransaction(
//        request: BaseServiceDTO,
//        requestTransaction: SagaPaymentTransactionRequest?
//    ): SagaPaymentTransactionResponse {
//    }
//
//}