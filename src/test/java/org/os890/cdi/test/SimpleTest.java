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

package org.os890.cdi.test;

import org.apache.deltaspike.core.spi.scope.window.WindowContext;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.junit5.RunWithApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Jars;
import org.apache.openejb.testing.Module;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.os890.cdi.template.ApplicationScopedBean;
import org.os890.cdi.template.StatelessEJB;
import org.os890.cdi.template.WindowScopedBean;

import jakarta.inject.Inject;

/**
 * Integration test running inside an embedded OpenEJB/TomEE container.
 *
 * <p>Verifies that CDI, EJB, and DeltaSpike window-scope work together.
 * Activate with: {@code mvn clean verify -Ptomee}
 */
@RunWithApplicationComposer
public class SimpleTest
{
    @Module
    @Classes(cdi = true, value = {
        ApplicationScopedBean.class,
        StatelessEJB.class,
        WindowScopedBean.class
    })
    @Jars("deltaspike-core-")
    public EjbJar ejbJar()
    {
        return new EjbJar("test");
    }

    @Inject
    private WindowScopedBean windowScopedBean;

    @Inject
    private WindowContext windowContext;

    @Test
    void injectionTest()
    {
        windowContext.activateWindow("w1");
        Assertions.assertEquals(14, windowScopedBean.getValue());
    }
}
