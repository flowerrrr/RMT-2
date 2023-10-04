package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Enumerated(EnumType.ORDINAL)
    @Column
    var objectStatus: ObjectStatus = ObjectStatus.ACTIVE

    @Column
    var createDate: LocalDateTime = LocalDateTime.now()

    @Column
    var updateDate: LocalDateTime? = null

    fun isActive() = objectStatus == ObjectStatus.ACTIVE

    fun isDeleted() = objectStatus == ObjectStatus.DELETED

    fun isFixed() = objectStatus == ObjectStatus.FIXED

    override fun toString(): String {
        return "AbstractBaseEntity@${super.hashCode()}{" + "id=$id" + '}'
    }

}
