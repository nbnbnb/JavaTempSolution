package basicconsoleapp

import basicconsoleapp.helper.SerializerHelperTest
import basicconsoleapp.srpingdemo.ee.fortest.DemoBeanIntegrationTests
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by jinzhanga on 2017/12/13.
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(SerializerHelperTest::class, DemoBeanIntegrationTests::class)
class AllTest

