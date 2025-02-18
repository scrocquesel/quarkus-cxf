package io.quarkiverse.cxf.deployment.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import io.quarkiverse.cxf.CXFClientInfo;
import io.quarkiverse.cxf.annotation.CXFClient;

@ApplicationScoped
public class CxfClientConstructorInjectionInstanceBean {

    private final Instance<CXFClientInfo> clientInfoInstance;
    private final Instance<FruitWebService> clientProxyInstance;

    @Inject
    public CxfClientConstructorInjectionInstanceBean(
            // @Named is omitted here because not required
            Instance<CXFClientInfo> clientInfoInstance,
            @CXFClient Instance<FruitWebService> clientProxyInstance) {
        this.clientInfoInstance = clientInfoInstance;
        this.clientProxyInstance = clientProxyInstance;
    }

    public Instance<CXFClientInfo> getClientInfoInstance() {
        return clientInfoInstance;
    }

    public Instance<FruitWebService> getClientProxyInstance() {
        return clientProxyInstance;
    }
}
