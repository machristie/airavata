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
package org.apache.airavata.sharing.registry.db;

import junit.framework.Assert;
import org.apache.airavata.sharing.registry.db.repositories.*;
import org.apache.airavata.sharing.registry.db.utils.DBConstants;
import org.apache.airavata.sharing.registry.models.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RepositoriesTest {
    private final static Logger logger = LoggerFactory.getLogger(RepositoriesTest.class);

    @Test
    public void test() throws GovRegistryException {

        //Creating domain
        Domain domain = new Domain();
        String domainId = "test-domain."+System.currentTimeMillis();
        domain.setDomainId(domainId);
        domain.setName(domainId);
        domain.setDescription("test domain description");
        domain.setCreatedTime(System.currentTimeMillis());
        domain.setUpdatedTime(System.currentTimeMillis());

        DomainRepository domainRepository = new DomainRepository();
        domain = domainRepository.create(domain);
        Assert.assertNotNull(domain);

        Map<String, String> filters = new HashMap<>();
        filters.put(DBConstants.DomainTable.DESCRIPTION, "test");
        Assert.assertTrue(domainRepository.select(filters, 0, 10).size() > 0);


        //Creating users
        User user1 = new User();
        String userName1 = "test-user." + System.currentTimeMillis();
        String userId1 = domainId + ":" + userName1;
        user1.setUserId(userId1);
        user1.setUserName(userName1);
        user1.setDomainId(domainId);
        user1.setCreatedTime(System.currentTimeMillis());
        user1.setUpdatedTime(System.currentTimeMillis());

        UserRepository userRepository = new UserRepository();
        user1 = userRepository.create(user1);
        Assert.assertNotNull(user1);

        User user2 = new User();
        String userName2 = "test-user." + System.currentTimeMillis();
        String userId2 = domainId + ":" + userName2;
        user2.setUserId(userId2);
        user2.setUserName(userName2);
        user2.setDomainId(domainId);
        user2.setCreatedTime(System.currentTimeMillis());
        user2.setUpdatedTime(System.currentTimeMillis());

        userRepository.create(user2);

        User user3 = new User();
        String userName3 = "test-user." + System.currentTimeMillis();
        String userId3 = domainId + ":" + userName3;
        user3.setUserId(userId3);
        user3.setUserName(userName3);
        user3.setDomainId(domainId);
        user3.setCreatedTime(System.currentTimeMillis());
        user3.setUpdatedTime(System.currentTimeMillis());

        userRepository.create(user3);

        filters = new HashMap<>();
        filters.put(DBConstants.UserTable.USER_NAME, "test");
        Assert.assertTrue(userRepository.select(filters, 0, 10).size() > 0);

        // Creating user groups
        UserGroup userGroup1 = new UserGroup();
        String groupName1 = "test-group";
        String groupId1 = domainId + ":" + groupName1 + "." + System.currentTimeMillis();
        userGroup1.setGroupId(groupId1);
        userGroup1.setDomainId(domainId);
        userGroup1.setName(groupName1);
        userGroup1.setDescription("test group description");
        userGroup1.setOwnerId(userId1);
        userGroup1.setGroupType(GroupType.MULTI_USER);
        userGroup1.setCreatedTime(System.currentTimeMillis());
        userGroup1.setUpdatedTime(System.currentTimeMillis());

        UserGroupRepository userGroupRepository = new UserGroupRepository();
        userGroup1 = userGroupRepository.create(userGroup1);
        Assert.assertNotNull(userGroup1);

        UserGroup userGroup2 = new UserGroup();
        String groupName2 = "test-group";
        String groupId2 = domainId + ":" + groupName2 + "." + System.currentTimeMillis();
        userGroup2.setGroupId(groupId2);
        userGroup2.setDomainId(domainId);
        userGroup2.setName(groupName2);
        userGroup2.setDescription("test group description");
        userGroup2.setOwnerId(userId2);
        userGroup2.setGroupType(GroupType.MULTI_USER);
        userGroup2.setCreatedTime(System.currentTimeMillis());
        userGroup2.setUpdatedTime(System.currentTimeMillis());

        userGroupRepository.create(userGroup2);

        UserGroup userGroup3 = new UserGroup();
        String groupName3 = "test-group";
        String groupId3 = domainId + ":" + groupName3 + "." + System.currentTimeMillis();
        userGroup3.setGroupId(groupId3);
        userGroup3.setDomainId(domainId);
        userGroup3.setName(groupName3);
        userGroup3.setDescription("test group description");
        userGroup3.setOwnerId(userId3);
        userGroup3.setGroupType(GroupType.MULTI_USER);
        userGroup3.setCreatedTime(System.currentTimeMillis());
        userGroup3.setUpdatedTime(System.currentTimeMillis());

        userGroupRepository.create(userGroup3);

        //Creating Groups for users (This is an implementation level abstract)
        UserGroup ug1 = new UserGroup();
        String ug1Name = "user1-group-name";
        String ug1GroupId = userId1;
        ug1.setGroupId(ug1GroupId);
        ug1.setDomainId(domainId);
        ug1.setName(ug1Name);
        ug1.setDescription("test group description");
        ug1.setOwnerId(userId1);
        ug1.setGroupType(GroupType.SINGLE_USER);
        ug1.setCreatedTime(System.currentTimeMillis());
        ug1.setUpdatedTime(System.currentTimeMillis());

        userGroupRepository.create(ug1);

        UserGroup ug2 = new UserGroup();
        String ug2Name = "user2-group-name";
        String ug2GroupId = userId2;
        ug2.setGroupId(ug2GroupId);
        ug2.setDomainId(domainId);
        ug2.setName(ug2Name);
        ug2.setDescription("test group description");
        ug2.setOwnerId(userId1);
        ug2.setGroupType(GroupType.SINGLE_USER);
        ug2.setCreatedTime(System.currentTimeMillis());
        ug2.setUpdatedTime(System.currentTimeMillis());

        userGroupRepository.create(ug2);

        UserGroup ug3 = new UserGroup();
        String ug3Name = "user1-group-name";
        String ug3GroupId = userId3;
        ug3.setGroupId(ug3GroupId);
        ug3.setDomainId(domainId);
        ug3.setName(ug3Name);
        ug3.setDescription("test group description");
        ug3.setOwnerId(userId1);
        ug3.setGroupType(GroupType.SINGLE_USER);
        ug3.setCreatedTime(System.currentTimeMillis());
        ug3.setUpdatedTime(System.currentTimeMillis());

        userGroupRepository.create(ug3);

        GroupMembership grpU2ToGrp2 = new GroupMembership();
        grpU2ToGrp2.setParentId(groupId2);
        grpU2ToGrp2.setChildId(userId2);
        grpU2ToGrp2.setChildType(GroupChildType.USER);

        GroupMembershipRepository groupMembershipRepository = new GroupMembershipRepository();
        grpU2ToGrp2 = groupMembershipRepository.create(grpU2ToGrp2);
        Assert.assertNotNull(grpU2ToGrp2);

        GroupMembership grpU3ToGrp2 = new GroupMembership();
        grpU3ToGrp2.setParentId(groupId2);
        grpU3ToGrp2.setChildId(userId3);
        grpU3ToGrp2.setChildType(GroupChildType.USER);

        groupMembershipRepository.create(grpU3ToGrp2);

        GroupMembership grpU1ToGrp1 = new GroupMembership();
        grpU1ToGrp1.setParentId(groupId1);
        grpU1ToGrp1.setChildId(userId1);
        grpU1ToGrp1.setChildType(GroupChildType.USER);

        groupMembershipRepository.create(grpU1ToGrp1);

        GroupMembership grpG2ToGrp1 = new GroupMembership();
        grpG2ToGrp1.setParentId(groupId1);
        grpG2ToGrp1.setChildId(groupId2);
        grpG2ToGrp1.setChildType(GroupChildType.GROUP);

        groupMembershipRepository.create(grpG2ToGrp1);

        filters = new HashMap<>();
        filters.put(DBConstants.GroupMembershipTable.PARENT_ID, groupId2);
        Assert.assertTrue(groupMembershipRepository.select(filters, 0, 10).size() == 2);

        Assert.assertTrue(groupMembershipRepository.getChildMembershipsOfGroup(groupId2).size() == 2);
        Assert.assertTrue(groupMembershipRepository.getAllChildUsers(groupId2).size() == 2);
        Assert.assertTrue(groupMembershipRepository.getAllChildGroups(groupId1).size() == 1);

        Assert.assertTrue(groupMembershipRepository.getAllParentMembershipsForChild(userId3).size() == 2);
        Assert.assertTrue(groupMembershipRepository.getAllParentGroupsForChild(userId3).size() == 2);

        //Creating permission types
        PermissionType permissionType1 = new PermissionType();
        String permissionName1 = "READ";
        String permissionType1Id = domainId + ":" + permissionName1;
        permissionType1.setPermissionTypeId(permissionType1Id);
        permissionType1.setDomainId(domainId);
        permissionType1.setName(permissionName1);
        permissionType1.setDescription("READ description");
        permissionType1.setCreatedTime(System.currentTimeMillis());
        permissionType1.setUpdatedTime(System.currentTimeMillis());

        PermissionTypeRepository permissionTypeRepository = new PermissionTypeRepository();
        permissionType1 = permissionTypeRepository.create(permissionType1);
        Assert.assertNotNull(permissionType1);

        PermissionType permissionType2 = new PermissionType();
        String permissionName2 = "WRITE";
        String permissionType2Id = domainId + ":" + permissionName2;
        permissionType2.setPermissionTypeId(permissionType2Id);
        permissionType2.setDomainId(domainId);
        permissionType2.setName(permissionName2);
        permissionType2.setDescription("WRITE description");
        permissionType2.setCreatedTime(System.currentTimeMillis());
        permissionType2.setUpdatedTime(System.currentTimeMillis());
        permissionTypeRepository.create(permissionType2);

        //Creating entity types
        EntityType entityType1 = new EntityType();
        String entityType1Name = "Project";
        String entityTypeId1 = domainId + ":" + entityType1Name;
        entityType1.setEntityTypeId(entityTypeId1);
        entityType1.setDomainId(domainId);
        entityType1.setName(entityType1Name);
        entityType1.setDescription("test entity type");
        entityType1.setCreatedTime(System.currentTimeMillis());
        entityType1.setUpdatedTime(System.currentTimeMillis());

        EntityTypeRepository entityTypeRepository = new EntityTypeRepository();
        entityType1 = entityTypeRepository.create(entityType1);
        Assert.assertNotNull(entityType1);

        EntityType entityType2 = new EntityType();
        String entityType2Name = "Experiment";
        String entityTypeId2 = domainId + ":" + entityType2Name;
        entityType2.setEntityTypeId(entityTypeId2);
        entityType2.setDomainId(domainId);
        entityType2.setName(entityType2Name);
        entityType2.setDescription("test entity type");
        entityType2.setCreatedTime(System.currentTimeMillis());
        entityType2.setUpdatedTime(System.currentTimeMillis());
        entityTypeRepository.create(entityType2);

        //Creating Entities
        String entityId1 = UUID.randomUUID().toString();
        Entity entity1 = new Entity();
        entity1.setEntityId(entityId1);
        entity1.setDomainId(domainId);
        entity1.setEntityTypeId(entityTypeId1);
        entity1.setOwnerId(userId1);
        entity1.setName("Project name");
        entity1.setDescription("Project description");
        Map<String, String> metadataMap = new HashMap<>();
        metadataMap.put("key", "val");
        entity1.setMetadata(metadataMap);
        entity1.setFullText("Project name project description");
        entity1.setCreatedTime(System.currentTimeMillis());
        entity1.setUpdatedTime(System.currentTimeMillis());

        EntityRepository entityRepository = new EntityRepository();
        entity1 = entityRepository.create(entity1);
        Assert.assertNotNull(entity1);

        String entityId2 = UUID.randomUUID().toString();
        Entity entity2 = new Entity();
        entity2.setEntityId(entityId2);
        entity2.setDomainId(domainId);
        entity2.setEntityTypeId(entityTypeId2);
        entity2.setOwnerId(userId1);
        entity2.setName("Experiment name");
        entity2.setDescription("Experiment description");
        entity2.setParentEntityId(entityId1);
        metadataMap = new HashMap<>();
        metadataMap.put("key", "val");
        entity2.setMetadata(metadataMap);
        entity2.setFullText("Project name project description");
        entity2.setCreatedTime(System.currentTimeMillis());
        entity2.setUpdatedTime(System.currentTimeMillis());

        entityRepository.create(entity2);

        String entityId3 = UUID.randomUUID().toString();
        Entity entity3 = new Entity();
        entity3.setEntityId(entityId3);
        entity3.setDomainId(domainId);
        entity3.setEntityTypeId(entityTypeId2);
        entity3.setOwnerId(userId1);
        entity3.setName("Experiment name");
        entity3.setDescription("Experiment description");
        entity3.setParentEntityId(entityId1);
        metadataMap = new HashMap<>();
        metadataMap.put("key", "val");
        entity3.setMetadata(metadataMap);
        entity3.setFullText("Project name project description");
        entity3.setCreatedTime(System.currentTimeMillis());
        entity3.setUpdatedTime(System.currentTimeMillis());

        entityRepository.create(entity3);

        // Creating sharing entries
        Sharing sharing1 = new Sharing();
        sharing1.setPermissionTypeId(permissionType1Id);
        sharing1.setEntityId(entityId1);
        sharing1.setGroupId(userId2);
        sharing1.setSharingType(SharingType.DIRECT);

        SharingRepository sharingRepository = new SharingRepository();
        sharing1 = sharingRepository.create(sharing1);
        Assert.assertNotNull(sharing1);

        Sharing sharing2 = new Sharing();
        sharing2.setPermissionTypeId(permissionType1Id);
        sharing2.setEntityId(entityId3);
        sharing2.setGroupId(groupId2);
        sharing2.setSharingType(SharingType.DIRECT);

        sharingRepository.create(sharing2);
    }
}