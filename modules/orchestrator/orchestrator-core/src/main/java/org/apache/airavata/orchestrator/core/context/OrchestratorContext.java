/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package org.apache.airavata.orchestrator.core.context;

import org.apache.airavata.messaging.core.Publisher;
import org.apache.airavata.orchestrator.core.OrchestratorConfiguration;
import org.apache.airavata.registry.cpi.Registry;

/**
 * This is the context object used in orchestrator which
 */
public class OrchestratorContext {
    private OrchestratorConfiguration orchestratorConfiguration;
    private Registry registry;
    private Publisher publisher;
    private String gatewayId;

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public OrchestratorConfiguration getOrchestratorConfiguration() {
        return orchestratorConfiguration;
    }

    public void setOrchestratorConfiguration(OrchestratorConfiguration orchestratorConfiguration) {
        this.orchestratorConfiguration = orchestratorConfiguration;
    }
    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
