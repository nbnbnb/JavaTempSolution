package sbconsoleapp.demain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery

/**
 * Created by ZhangJin on 2018/6/14.
 */

@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and p.address=?2")
data class Person(
        @Id val id: Long,
        val name: String,
        val age: Int,
        val address: String
)