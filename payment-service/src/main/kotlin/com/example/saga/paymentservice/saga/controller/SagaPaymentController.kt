package com.example.saga.paymentservice.saga.controller

import com.example.saga.paymentservice.saga.constants.PaymentSagaConstants.CURRENT_SERVICE_PATH_KEY
import com.example.saga.paymentservice.saga.constants.PaymentSagaConstants.TARGET_DELIVER_SERVICE
import com.example.saga.paymentservice.saga.constants.PaymentSagaConstants.TARGET_DELIVER_SERVICE_PATH
import com.example.saga.paymentservice.saga.dto.SagaPaymentCompensationDTO
import com.example.saga.paymentservice.saga.dto.SagaPaymentRequestDTO
import com.example.saga.paymentservice.saga.model.SagaPaymentTransactionRequest
import com.example.saga.paymentservice.saga.model.SagaPaymentTransactionResponse
import com.example.saga.paymentservice.saga.service.SagaPaymentCompensationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.kmao.saga.sagahelperspringbootstarter.api.SagaContinuationService
import ru.kmao.saga.sagahelperspringbootstarter.builder.SagaParamsModel

@RestController
class SagaPaymentController {

    @Autowired
    private lateinit var sagaContinuationService:
            SagaContinuationService<SagaPaymentTransactionRequest, SagaPaymentTransactionResponse>

    @Autowired
    private lateinit var sagaPaymentCompensationService: SagaPaymentCompensationService

    @PostMapping("/saga/payment/request")
    fun startSaga(@RequestBody serviceDTO: SagaPaymentRequestDTO) {
        val sagaParamsModel = SagaParamsModel.builder()
            .targetServiceKey(TARGET_DELIVER_SERVICE)
            .currentServicePathKey(CURRENT_SERVICE_PATH_KEY)
            .sagaHelperServiceCode(CURRENT_SERVICE_PATH_KEY)
            .targetServicePathKey(TARGET_DELIVER_SERVICE_PATH)
            .build()

        //the last parameter can be sent as extra data for transaction
        //in my case this is null
        sagaContinuationService.continueTransaction(serviceDTO, sagaParamsModel, SagaPaymentTransactionRequest())
    }

    @PostMapping("/saga/payment/compensation")
    fun compensatePayment(@RequestBody sagaPaymentCompensationDTO: SagaPaymentCompensationDTO) {
        sagaPaymentCompensationService.compensatePayment(sagaPaymentCompensationDTO)
    }

    @PostMapping("/saga/payment/success-callback")
    fun successPayment() {

    }
}