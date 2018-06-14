package sbconsoleapp

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import sbconsoleapp.dao.PersonRepository
import sbconsoleapp.demain.Person
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by ZhangJin on 2018/6/15.
 */

@RunWith(SpringRunner::class)
@SpringBootTest
@Import(ConsoleApplication::class)
class AllTests {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    fun basicTest() {
        personRepository.save(Person(1, "AAA", 30, "北京"))
        personRepository.save(Person(2, "BBB", 31, "上海"))

        assertEquals(personRepository.findAll().size, 2)
        assertEquals(personRepository.findByAddress("北京").size, 1)

        assertEquals(personRepository.findByNameAndAddress("AAA", "北京").address, "北京")
        assertEquals(personRepository.withNameAndAddressQuery("AAA", "北京").address, "北京")
        assertEquals(personRepository.withNameAndAddressNamedQuery("AAA", "北京").address, "北京")

    }
}