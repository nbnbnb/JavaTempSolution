package basicconsoleapp.springdemo

import basicconsoleapp.config.ProfileSwitchConfig
import basicconsoleapp.springdemo.ee.entities.TestBean
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by ZhangJin on 2018/6/3.
 */

// 他提供了 Spring TestContext Framework 的功能
@RunWith(SpringJUnit4ClassRunner::class)
// 使用配置类来配置 Applicatioin Context
@ContextConfiguration(classes = [ProfileSwitchConfig::class])
// 确定活动的 profile
@ActiveProfiles("prod")
class ProfileSwichTest {

    @Autowired
    private lateinit var testBean: TestBean

    @Test
    fun prodBeanShouldInject() {
        val expected = "from production profile"
        val actual = testBean.content
        Assert.assertEquals(expected, actual)
    }

}