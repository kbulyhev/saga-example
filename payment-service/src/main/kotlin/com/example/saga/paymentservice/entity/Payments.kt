package com.example.saga.paymentservice.entity

import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@MappedSuperclass
abstract class BasePaymentEntity(
    @Id @Column(name = "payment_id", nullable = false) @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seq_generator"
    ) @SequenceGenerator(
        name = "seq_generator",
        sequenceName = "payment_id_seq",
        allocationSize = 1
    ) var paymentId: Long?,

    @Column(name = "cost", nullable = false) var cost: String,

    @Column(name = "timestamp", nullable = false) var timestamp: OffsetDateTime,

    @Column(name = "payment_number", nullable = false) var paymentNumber: String,

    @Column(name = "order_id", nullable = false) var orderId: Long,

    @Column(name = "order_valid_to", nullable = false) var orderValidTo: OffsetDateTime

) {
    constructor() : this(
        null, "", OffsetDateTime.now(),
        "", 0, OffsetDateTime.now()
    )
}

@Entity
@Table(name = "payment_service")
class PaymentEntity(
    paymentId: Long?,
    cost: String,
    timestamp: OffsetDateTime,
    paymentNumber: String,
    orderId: Long,
    orderValidTo: OffsetDateTime
) : BasePaymentEntity(paymentId, cost, timestamp, paymentNumber, orderId, orderValidTo)


@Entity
@Table(name = "saga_payment_service")
class SagaPaymentEntity(
    paymentId: Long?,
    cost: String,
    timestamp: OffsetDateTime,
    paymentNumber: String,
    orderId: Long,
    orderValidTo: OffsetDateTime
) : BasePaymentEntity(paymentId, cost, timestamp, paymentNumber, orderId, orderValidTo)

