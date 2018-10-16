package sbwebapp

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.springframework.test.context.ActiveProfiles
import sbwebapp.validator.ValidateTest

/**`
 * Created by jinzhanga on 2018/8/10.
 *
 * RPC 需要先系统 SBWebApp 后在运行测试
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(ValidateTest::class)
@ActiveProfiles("test")
class AllTests