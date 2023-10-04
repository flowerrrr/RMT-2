package de.flower.rmt2.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User : AbstractClubRelatedEntity() {

    @Column(name = "username")
    var email: String? = null

    /**
     * On popular demand users can add second email address for home and work-emails.
     */
    @Column
    var secondEmail: String? = null

    @Column
    var phoneNumber: String? = null

    /**
     * Encrypted password.
     */
    @Column(name = "password")
    var encryptedPassword: String? = null

    @Column(name = "enabled")
    var isEnabled = false

    @Column
    var fullname: String? = null

    /**
     * A user can be part of several teams.
     */
    @OneToMany(mappedBy = "user")
    val players: List<Player> = ArrayList()

    val username: String?
        get() = email

    override fun toString(): String {
        return ("User{" +
                "id='" + id + '\'').toString() +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                '}'
    }

}

