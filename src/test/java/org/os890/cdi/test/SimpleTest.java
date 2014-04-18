/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.os890.cdi.test;

import org.apache.deltaspike.core.spi.scope.window.WindowContext;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.os890.cdi.template.WindowScopedBean;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
//OpenEJB alternative: @RunWith(EJBContainerRunner.class)
public class SimpleTest
{
    @Inject
    private WindowScopedBean windowScopedBean;

    @Inject
    private WindowContext windowContext;

    @Test
    public void injectionTest()
    {
        windowContext.activateWindow("w1");
        Assert.assertEquals(14, windowScopedBean.getValue());
    }
}
