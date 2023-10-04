package de.flower.rmt2.db.entity

enum class ObjectStatus {
    /** Active object. */
    ACTIVE,

    /** Soft-deleted object. */
    DELETED,

    /** Fixed object (should never be deleted or deactivated). */
    FIXED
}
