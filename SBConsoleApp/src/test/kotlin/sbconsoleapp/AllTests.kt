package sbconsoleapp

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import sbconsoleapp.dao.PersonRepository
import sbconsoleapp.demain.Person
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Created by ZhangJin on 2018/6/15.
 */

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(SBConsoleApp::class)])
//@Suite.SuiteClasses(MiscHelperTest::class, QConfigHelperTest::class, CtripLoggerTest::class)
//@Import(ConsoleApplication::class)
class AllTests {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Test
    @Ignore
    fun basicTest() {
        assertEquals(personRepository.findAll().size, 6)
        assertEquals(personRepository.findByAddress("北京").size, 1)

        assertEquals(personRepository.findByNameAndAddress("AAA", "北京").address, "北京")
        assertEquals(personRepository.withNameAndAddressQuery("AAA", "北京").address, "北京")
        assertEquals(personRepository.withNameAndAddressNamedQuery("AAA", "北京").address, "北京")

        personRepository.save(Person(7, "GGG", 30, "天津"))
        personRepository.save(Person(8, "HHH", 31, "重启"))

        assertEquals(personRepository.findAll().size, 8)
    }
}