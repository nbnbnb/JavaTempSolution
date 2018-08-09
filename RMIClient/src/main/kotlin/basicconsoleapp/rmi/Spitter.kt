package basicconsoleapp.rmi

import java.io.Serializable


/**
 * Created by jinzhanga on 2018/8/9.
 */
class Spitter : Serializable {

    var id: Long? = null

    var username: String? = null

    var password: String? = null

    var firstName: String? = null

    var lastName: String? = null

    var email: String? = null

    constructor()

    constructor(username: String, password: String, firstName: String, lastName: String, email: String) :
            this(null, username, password, firstName, lastName, email)

    constructor(id: Long?, username: String, password: String, firstName: String, lastName: String, email: String) {
        this.id = id
        this.username = username
        this.password = password
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    companion object {
        private const val serialVersionUID = 1563056272025335248L
    }

}