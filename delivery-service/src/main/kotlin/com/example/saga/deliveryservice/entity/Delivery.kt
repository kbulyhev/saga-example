package com.example.saga.deliveryservice.entity

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
open class BaseDeliverEntity(
    @Id @Column(name = "deliver_id", nullable = false) @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "seq_generator"
    ) @SequenceGenerator(
        name = "seq_generator",
        sequenceName = "deliver_id_seq",
        allocationSize = 1
    ) var deliverId: Long?,

    @Column(name = "payment_id", nullable = false) var paymentId: Long,

    @Column(name = "timestamp", nullable = false) var timestamp: OffsetDateTime,

    @Column(name = "deliver_date_from", nullable = false) var deliverDateFrom: OffsetDateTime,

    @Column(name = "deliver_date_to", nullable = false) var deliverDateTo: OffsetDateTime,

    @Column(name = "address", nullable = false) var address: String

) {
    constructor() : this(
        null, 1, OffsetDateTime.now(), OffsetDateTime.now(), OffsetDateTime.now(), ""
    )
}

@Entity
@Table(name = "deliver_service")
class DeliveryEntity(
    deliverId: Long?,
    paymentId: Long,
    timestamp: OffsetDateTime,
    deliverDateFrom: OffsetDateTime,
    deliverDateTo: OffsetDateTime,
    address: String
) : BaseDeliverEntity(deliverId, paymentId, timestamp, deliverDateFrom, deliverDateTo, address)



@Entity
@Table(name = "saga_deliver_service")
class SagaDeliveryEntity(
    deliverId: Long?,
    paymentId: Long,
    timestamp: OffsetDateTime,
    deliverDateFrom: OffsetDateTime,
    deliverDateTo: OffsetDateTime,
    address: String
) : BaseDeliverEntity(deliverId, paymentId, timestamp, deliverDateFrom, deliverDateTo, address)