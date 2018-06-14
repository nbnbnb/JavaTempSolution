package sbconsoleapp.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sbconsoleapp.demain.Person

/**
 * Created by ZhangJin on 2018/6/15.
 */

interface PersonRepository : JpaRepository<Person, Long> {

    // 使用方法名查询，接受一个 name 参数，返回值为列表
    fun findByAddress(address: String): List<Person>

    // 使用方法名查询，接受 name 和 address，返回值为单个对象
    fun findByNameAndAddress(name: String, address: String): Person

    // 使用 @Query 查询，参数按照名称绑定
    @Query("select p from Person p where p.name= :name and p.address= :address")
    fun withNameAndAddressQuery(@Param("name") name: String, @Param("address") address: String): Person

    // 使用 @NamedQuery 查询，请注意我们在实体类中做的 @NamedQuery 的定义
    fun withNameAndAddressNamedQuery(name: String, address: String): Person
}