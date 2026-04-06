/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.os890.cdi.test.cdi;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.os890.cdi.addon.dynamictestbean.EnableTestBeans;
import org.os890.cdi.addon.dynamictestbean.TestBean;
import org.os890.cdi.template.ApplicationScopedBean;

/**
 * CDI SE test demonstrating the dynamic-cdi-test-bean-addon.
 *
 * <p>Uses an inline {@code @Produces @TestBean} field to replace the real
 * {@link ApplicationScopedBean} with a Mockito mock, avoiding the need
 * for a full EJB container.
 *
 * <p>Run with: {@code mvn clean verify} (default profile)
 */
@EnableTestBeans(limitToTestBeans = true)
class CdiInjectionTest
{
    @Produces
    @TestBean
    private static ApplicationScopedBean appBean = createMock();

    @Inject
    private ApplicationScopedBean applicationScopedBean;

    @Test
    void inlineMockProducerTest()
    {
        Assertions.assertEquals(42, applicationScopedBean.getValue());
        Mockito.verify(appBean).getValue();
    }

    private static ApplicationScopedBean createMock()
    {
        ApplicationScopedBean mock = Mockito.mock(ApplicationScopedBean.class);
        Mockito.when(mock.getValue()).thenReturn(42);
        return mock;
    }
}
