package sbwebapp

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.springframework.test.context.ActiveProfiles
import sbwebapp.hessian.HessianClientTest

/**
 * Created by jinzhanga on 2018/8/10.
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(HessianClientTest::class)
@ActiveProfiles("test")
class AllTests