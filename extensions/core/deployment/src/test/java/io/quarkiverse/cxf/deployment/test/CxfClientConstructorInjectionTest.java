package io.quarkiverse.cxf.deployment.test;

import java.lang.reflect.Proxy;

import jakarta.inject.Inject;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

public class CxfClientConstructorInjectionTest {

    @RegisterExtension
    public static final QuarkusUnitTest test = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClass(FruitWebService.class)
                    .addClass(Fruit.class)
                    .addClass(CxfClientConstructorInjectionBean.class))
            .withConfigurationResource("application-cxf-test.properties");

    @Inject
    CxfClientConstructorInjectionBean bean;

    @Test
    public void testInjectedBeans() {
        Assertions.assertNotNull(bean.getClientInfo());
        Assertions.assertNotNull(bean.getClientProxy());

        Assertions.assertFalse(Proxy.isProxyClass(bean.getClientInfo().getClass()));
        Assertions.assertTrue(Proxy.isProxyClass(bean.getClientProxy().getClass()));
    }

}
