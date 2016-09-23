/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

namespace java org.apache.airavata.sharing.registry.service.cpi

include "./sharing_models.thrift"

service GovRegistryService {

    /**
     * Domain Operations
    **/
    bool createDomain(1: required sharing_models.Domain domain) throws (1: sharing_models.GovRegistryException gre)
    bool updateDomain(1: required sharing_models.Domain domain) throws (1: sharing_models.GovRegistryException gre)
    bool deleteDomain(1: required string domainId) throws (1: sharing_models.GovRegistryException gre)
    sharing_models.Domain getDomain(1: required string domainId) throws (1: sharing_models.GovRegistryException gre)
    list<sharing_models.Domain> getDomains(1: required i32 offset, 2: required i32 limit)

    /**
     * User Operations
    **/
    bool registerUser(1: required sharing_models.User user) throws (1: sharing_models.GovRegistryException gre)
    bool updatedUser(1: required sharing_models.User user) throws (1: sharing_models.GovRegistryException gre)
    bool deleteUser(1: required string userId) throws (1: sharing_models.GovRegistryException gre)
    sharing_models.User getUser(1: required string userId) throws (1: sharing_models.GovRegistryException gre)
    list<sharing_models.User> getUsers(1: required string domain, 2: required i32 offset, 3: required i32 limit)

    /**
     * Group Operations
    **/
    bool createGroup(1: required sharing_models.UserGroup group) throws (1: sharing_models.GovRegistryException gre)
    bool updateGroup(1: required sharing_models.UserGroup group) throws (1: sharing_models.GovRegistryException gre)
    bool deleteGroup(1: required string groupId) throws (1: sharing_models.GovRegistryException gre)
    sharing_models.UserGroup getGroup(1: required string groupId) throws (1: sharing_models.GovRegistryException gre)
    list<sharing_models.UserGroup> getGroups(1: required string domain, 2: required i32 offset, 3: required i32 limit)

    bool addUsersToGroup(1: required list<string> userIds, 2: required string groupId) throws (1: sharing_models.GovRegistryException gre);
    bool removeUsersFromGroup(1: required list<string> userIds, 2: required string groupId) throws (1: sharing_models.GovRegistryException gre);
    map<string, sharing_models.GroupType> getGroupMembers(1: required string groupId)

    /**
     * EntityType Operations
    **/
    bool createEntityType(1: required sharing_models.EntityType entityType) throws (1: sharing_models.GovRegistryException gre)
    bool updateEntityType(1: required sharing_models.EntityType entityType) throws (1: sharing_models.GovRegistryException gre)
    bool deleteEntityType(1: required string entityTypeId) throws (1: sharing_models.GovRegistryException gre)
    sharing_models.EntityType getEntityType(1: required string entityTypeId) throws (1: sharing_models.GovRegistryException gre)
    list<sharing_models.EntityType> getEntityTypes(1: required string domain, 2: required i32 offset, 3: required i32 limit)

    /**
     * Entity Operations
    **/
    bool registerEntity(1: required sharing_models.Entity entity) throws (1: sharing_models.GovRegistryException gre)
    bool updateEntity(1: required sharing_models.Entity entity) throws (1: sharing_models.GovRegistryException gre)
    bool deleteEntity(1: required string entityId) throws (1: sharing_models.GovRegistryException gre)
    sharing_models.Entity getEntity(1: required string entityId) throws (1: sharing_models.GovRegistryException gre)
    list<sharing_models.Entity> searchEntities(1: required string domain, 2: required string entityType,
            3: required map<string, string> filters, 4: required i32 offset, 5: required i32 limit) throws (1: sharing_models.GovRegistryException gre)

    /**
     * EntityType Operations
    **/
    bool createPermissionType(1: required sharing_models.PermissionType permisionType) throws (1: sharing_models.GovRegistryException gre)
    bool updatePermissionType(1: required sharing_models.PermissionType permisionType) throws (1: sharing_models.GovRegistryException gre)
    bool deletePermissionType(1: required string entityTypeId) throws (1: sharing_models.GovRegistryException gre)
    sharing_models.PermissionType getPermissionType(1: required string permisionTypeId) throws (1: sharing_models.GovRegistryException gre)
    list<sharing_models.PermissionType> getPermissionTypes(1: required string domain, 2: required i32 offset, 3: required i32 limit) throws (1: sharing_models.GovRegistryException gre)
}