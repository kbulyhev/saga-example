package com.example.saga.deliveryservice.saga.controller

import com.example.saga.deliveryservice.saga.constants.DeliverSagaConstants.CURRENT_SERVICE_PATH_KEY
import com.example.saga.deliveryservice.saga.dto.SagaDeliverCompensationDTO
import com.example.saga.deliveryservice.saga.dto.SagaDeliverRequestDTO
import com.example.saga.deliveryservice.saga.model.SagaCompleteTransactionRequest
import com.example.saga.deliveryservice.saga.model.SagaDeliverTransactionResponse
import com.example.saga.deliveryservice.saga.service.SagaCompensationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kmao.saga.sagahelperspringbootstarter.api.SagaCompletingService
import ru.kmao.saga.sagahelperspringbootstarter.builder.SagaParamsModel

@RestController
@RequestMapping("/saga")
class SagaDeliveryController {

    @Autowired
    private lateinit var sagaCompensationService: SagaCompensationService

    @Autowired
    private lateinit var sagaCompleteService:
            SagaCompletingService<SagaCompleteTransactionRequest, SagaDeliverTransactionResponse>

    @PostMapping("/deliver/request")
    fun deliverRequest(@RequestBody sagaDeliverRequestDTO: SagaDeliverRequestDTO) {
        val sagaParamsModel = SagaParamsModel.builder()
            .currentServicePathKey(CURRENT_SERVICE_PATH_KEY)
            .sagaHelperServiceCode(CURRENT_SERVICE_PATH_KEY)
            .build()

        //the last parameter can be sent as extra data for transaction
        //in my case this is null
        sagaCompleteService.completeSaga(sagaDeliverRequestDTO, sagaParamsModel, null)
    }

    @PostMapping("/deliver/compensation")
    fun compensateTransaction(@RequestBody sagaDeliverCompensationDTO: SagaDeliverCompensationDTO) {
        sagaCompensationService.compensate(sagaDeliverCompensationDTO)
    }
}