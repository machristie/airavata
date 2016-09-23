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
package org.apache.airavata.gov.registry.server;

import org.apache.airavata.gov.registry.models.*;
import org.apache.airavata.gov.registry.service.cpi.GovRegistryService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class GovRegistryServerHandler implements GovRegistryService.Iface{
    private final static Logger logger = LoggerFactory.getLogger(GovRegistryServerHandler.class);

    /**
     * * Domain Operations
     * *
     */
    @Override
    public boolean createDomain(Domain domain) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean updateDomain(Domain domain) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean deleteDomain(String domainId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public Domain getDomain(String domainId) throws GovRegistryException, TException {
        return null;
    }

    @Override
    public List<Domain> getDomains(int offset, int limit) throws TException {
        return null;
    }

    /**
     * * User Operations
     * *
     */
    @Override
    public boolean registerUser(User user) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean updatedUser(User user) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public User getUser(String userId) throws GovRegistryException, TException {
        return null;
    }

    @Override
    public List<User> getUsers(String domain, int offset, int limit) throws TException {
        return null;
    }

    /**
     * * Group Operations
     * *
     */
    @Override
    public boolean createGroup(Group group) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean updateGroup(Group group) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean deleteGroup(String groupId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public Group getGroup(String groupId) throws GovRegistryException, TException {
        return null;
    }

    @Override
    public List<Group> getGroups(String domain, int offset, int limit) throws TException {
        return null;
    }

    @Override
    public boolean addUsersToGroup(List<String> userIds, String groupId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean removeUsersFromGroup(List<String> userIds, String groupId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public Map<String, GroupType> getGroupMembers(String groupId) throws TException {
        return null;
    }

    /**
     * * EntityType Operations
     * *
     */
    @Override
    public boolean createEntityType(EntityType entityType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean updateEntityType(EntityType entityType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean deleteEntityType(String entityTypeId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public EntityType getEntityType(String entityTypeId) throws GovRegistryException, TException {
        return null;
    }

    @Override
    public List<EntityType> getEntityTypes(String domain, int offset, int limit) throws TException {
        return null;
    }

    /**
     * * Entity Operations
     * *
     */
    @Override
    public boolean registerEntity(Entity entity) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean updateEntity(Entity entity) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean deleteEntity(String entityId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public Entity getEntity(String entityId) throws GovRegistryException, TException {
        return null;
    }

    @Override
    public List<Entity> searchEntities(String domain, String entityType, Map<String, String> filters, int offset, int limit) throws GovRegistryException, TException {
        return null;
    }

    /**
     * * EntityType Operations
     * *
     */
    @Override
    public boolean createPermissionType(PermissionType permisionType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean updatePermissionType(PermissionType permisionType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean deletePermissionType(String entityTypeId) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public PermissionType getPermissionType(String permisionTypeId) throws GovRegistryException, TException {
        return null;
    }

    @Override
    public List<PermissionType> getPermissionTypes(String domain, int offset, int limit) throws GovRegistryException, TException {
        return null;
    }
}