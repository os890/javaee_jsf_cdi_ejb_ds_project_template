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

package org.os890.cdi.template;

import org.apache.deltaspike.core.api.scope.WindowScoped;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Window-scoped JSF managed bean backed by DeltaSpike's {@code @WindowScoped}.
 *
 * <p>The bean value is initialised from the {@link ApplicationScopedBean}
 * at construction time, demonstrating constructor injection across CDI scopes.
 */
@Named
@WindowScoped
public class WindowScopedBean implements Serializable
{
    private int value;

    protected WindowScopedBean()
    {
    }

    /**
     * Creates a new instance, reading the initial value from the given bean.
     *
     * @param applicationScopedBean the application-scoped bean to read from
     */
    @Inject
    public WindowScopedBean(ApplicationScopedBean applicationScopedBean)
    {
        this.value = applicationScopedBean.getValue();
    }

    /**
     * Returns the current value.
     *
     * @return the value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(int value)
    {
        this.value = value;
    }
}
