package sbwebapp

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.springframework.test.context.ActiveProfiles
import sbwebapp.rpc.HessianClientTest
import sbwebapp.rpc.HttpInvokerClientTest
import sbwebapp.rpc.JaxWsClientTest

/**`
 * Created by jinzhanga on 2018/8/10.
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(HessianClientTest::class, HttpInvokerClientTest::class, JaxWsClientTest::class)
@ActiveProfiles("test")
class AllTests