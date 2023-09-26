package de.flower.rmt2.db.entity

enum class RSVPStatus {

    /**
     * Don't change values they are used as database keys.
     */
    ACCEPTED,
    DECLINED,
    UNSURE,
    NORESPONSE;

}