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

namespace java org.apache.airavata.gov.registry.service.cpi

include "./gov_sharing_models.thrift"

service GovRegistryService {

    /**
     * Domain Operations
    **/
    bool createDomain(1: required gov_sharing_models.thrift.Domain domain) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool updateDomain(1: required gov_sharing_models.thrift.Domain domain) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool deleteDomain(1: required string domainId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    gov_sharing_models.thrift.Domain getDomain(1: required string domainId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    list<gov_sharing_models.thrift.Domain> getDomains(1: required i32 offset, 2: required i32 limit)

    /**
     * User Operations
    **/
    bool registerUser(1: required gov_sharing_models.thrift.User user) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool updatedUser(1: required gov_sharing_models.thrift.User user) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool deleteUser(1: required string userId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    gov_sharing_models.thrift.User getUser(1: required string userId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    list<gov_sharing_models.thrift.User> getUsers(1: required string domain, 2: required i32 offset, 3: required i32 limit)

    /**
     * Group Operations
    **/
    bool createGroup(1: required gov_sharing_models.thrift.UserGroup group) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool updateGroup(1: required gov_sharing_models.thrift.UserGroup group) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool deleteGroup(1: required string groupId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    gov_sharing_models.thrift.UserGroup getGroup(1: required string groupId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    list<gov_sharing_models.thrift.UserGroup> getGroups(1: required string domain, 2: required i32 offset, 3: required i32 limit)

    bool addUsersToGroup(1: required list<string> userIds, 2: required string groupId) throws (1: gov_sharing_models.thrift.GovRegistryException gre);
    bool removeUsersFromGroup(1: required list<string> userIds, 2: required string groupId) throws (1: gov_sharing_models.thrift.GovRegistryException gre);
    map<string, gov_sharing_models.thrift.GroupType> getGroupMembers(1: required string groupId)

    /**
     * EntityType Operations
    **/
    bool createEntityType(1: required gov_sharing_models.thrift.EntityType entityType) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool updateEntityType(1: required gov_sharing_models.thrift.EntityType entityType) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool deleteEntityType(1: required string entityTypeId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    gov_sharing_models.thrift.EntityType getEntityType(1: required string entityTypeId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    list<gov_sharing_models.thrift.EntityType> getEntityTypes(1: required string domain, 2: required i32 offset, 3: required i32 limit)

    /**
     * Entity Operations
    **/
    bool registerEntity(1: required gov_sharing_models.thrift.Entity entity) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool updateEntity(1: required gov_sharing_models.thrift.Entity entity) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool deleteEntity(1: required string entityId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    gov_sharing_models.thrift.Entity getEntity(1: required string entityId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    list<gov_sharing_models.thrift.Entity> searchEntities(1: required string domain, 2: required string entityType,
            3: required map<string, string> filters, 4: required i32 offset, 5: required i32 limit) throws (1: gov_sharing_models.thrift.GovRegistryException gre)

    /**
     * EntityType Operations
    **/
    bool createPermissionType(1: required gov_sharing_models.thrift.PermissionType permisionType) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool updatePermissionType(1: required gov_sharing_models.thrift.PermissionType permisionType) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    bool deletePermissionType(1: required string entityTypeId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    gov_sharing_models.thrift.PermissionType getPermissionType(1: required string permisionTypeId) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
    list<gov_sharing_models.thrift.PermissionType> getPermissionTypes(1: required string domain, 2: required i32 offset, 3: required i32 limit) throws (1: gov_sharing_models.thrift.GovRegistryException gre)
}