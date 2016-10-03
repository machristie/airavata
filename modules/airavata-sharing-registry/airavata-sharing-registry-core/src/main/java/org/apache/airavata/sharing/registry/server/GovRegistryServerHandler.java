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
package org.apache.airavata.sharing.registry.server;

import org.apache.airavata.sharing.registry.db.entities.GroupMembershipEntityPK;
import org.apache.airavata.sharing.registry.db.repositories.*;
import org.apache.airavata.sharing.registry.db.utils.DBConstants;
import org.apache.airavata.sharing.registry.models.*;
import org.apache.airavata.sharing.registry.service.cpi.GovRegistryService;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class GovRegistryServerHandler implements GovRegistryService.Iface{
    private final static Logger logger = LoggerFactory.getLogger(GovRegistryServerHandler.class);

    private DomainRepository domainRepository;
    private UserRepository userRepository;
    private UserGroupRepository userGroupRepository;
    private GroupMembershipRepository groupMembershipRepository;
    private EntityTypeRepository entityTypeRepository;
    private PermissionTypeRepository permissionTypeRepository;
    private EntityRepository entityRepository;

    public GovRegistryServerHandler(){
        this.domainRepository = new DomainRepository();
        this.userRepository = new UserRepository();
        this.userGroupRepository = new UserGroupRepository();
        this.groupMembershipRepository = new GroupMembershipRepository();
        this.entityTypeRepository = new EntityTypeRepository();
        this.permissionTypeRepository = new PermissionTypeRepository();
        this.entityRepository = new EntityRepository();
    }

    /**
     * * Domain Operations
     * *
     */
    @Override
    public String createDomain(Domain domain) throws GovRegistryException, TException {
        domain.setDomainId(domain.name);
        domain.setCreatedTime(System.currentTimeMillis());
        domain.setUpdatedTime(System.currentTimeMillis());
        domainRepository.create(domain);
        return domain.domainId;
    }

    @Override
    public boolean updateDomain(Domain domain) throws GovRegistryException, TException {
        Domain oldDomain = domainRepository.get(domain.domainId);
        domain.setCreatedTime(oldDomain.createdTime);
        domain.setUpdatedTime(System.currentTimeMillis());
        domain = getUpdatedObject(oldDomain, domain);
        domainRepository.update(domain);
        return true;
    }

    @Override
    public boolean deleteDomain(String domainId) throws GovRegistryException, TException {
        domainRepository.delete(domainId);
        return true;
    }

    @Override
    public Domain getDomain(String domainId) throws GovRegistryException, TException {
        return domainRepository.get(domainId);
    }

    @Override
    public List<Domain> getDomains(int offset, int limit) throws TException {
        return domainRepository.select(new HashMap<>(), offset, limit);
    }

    /**
     * * User Operations
     * *
     */
    @Override
    public String createUser(User user) throws GovRegistryException, TException {
        user.setUserId(user.domainId + ":" + user.userName);
        user.setCreatedTime(System.currentTimeMillis());
        user.setUpdatedTime(System.currentTimeMillis());
        userRepository.create(user);

        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(user.userId);
        userGroup.setDomainId(user.domainId);
        userGroup.setName(user.userName);
        userGroup.setDescription("user " + user.userName + " group");
        userGroup.setOwnerId(user.userId);
        userGroup.setGroupType(GroupType.SINGLE_USER);
        createGroup(userGroup);

        return user.userId;
    }

    @Override
    public boolean updatedUser(User user) throws GovRegistryException, TException {
        User oldUser = userRepository.get(user.userId);
        user.setCreatedTime(oldUser.createdTime);
        user.setUpdatedTime(System.currentTimeMillis());
        user = getUpdatedObject(oldUser, user);
        userRepository.update(user);

        UserGroup userGroup = userGroupRepository.get(user.userId);
        userGroup.setName(user.userName);
        userGroup.setDescription("user " + user.userName + " group");
        updateGroup(userGroup);
        return true;
    }

    @Override
    public boolean deleteUser(String userId) throws GovRegistryException, TException {
        userRepository.delete(userId);
        userGroupRepository.delete(userId);
        return true;
    }

    @Override
    public User getUser(String userId) throws GovRegistryException, TException {
        return userRepository.get(userId);
    }

    @Override
    public List<User> getUsers(String domain, int offset, int limit) throws  GovRegistryException, TException {
        HashMap<String, String> filters = new HashMap<>();
        filters.put(DBConstants.UserTable.DOMAIN_ID, domain);
        return userRepository.select(filters, offset, limit);
    }

    /**
     * * Group Operations
     * *
     */
    @Override
    public String createGroup(UserGroup group) throws GovRegistryException, TException {
        group.setGroupId(group.domainId+":"+group.name);
        group.setCreatedTime(System.currentTimeMillis());
        group.setUpdatedTime(System.currentTimeMillis());
        userGroupRepository.create(group);
        return group.groupId;
    }

    @Override
    public boolean updateGroup(UserGroup group) throws GovRegistryException, TException {
        group.setUpdatedTime(System.currentTimeMillis());
        UserGroup oldGroup = userGroupRepository.get(group.groupId);
        group.setCreatedTime(oldGroup.createdTime);
        group = getUpdatedObject(oldGroup, group);
        userGroupRepository.update(group);
        return true;
    }

    @Override
    public boolean deleteGroup(String groupId) throws GovRegistryException, TException {
        userGroupRepository.delete(groupId);
        return true;
    }

    @Override
    public UserGroup getGroup(String groupId) throws GovRegistryException, TException {
        return userGroupRepository.get(groupId);
    }

    @Override
    public List<UserGroup> getGroups(String domain, int offset, int limit) throws TException {
        HashMap<String, String> filters = new HashMap<>();
        filters.put(DBConstants.UserTable.DOMAIN_ID, domain);
        return userGroupRepository.select(filters, offset, limit);
    }

    @Override
    public boolean addUsersToGroup(List<String> userIds, String groupId) throws GovRegistryException, TException {
        for(int i=0; i < userIds.size(); i++){
            GroupMembership groupMembership = new GroupMembership();
            groupMembership.setParentId(groupId);
            groupMembership.setChildId(userIds.get(i));
            groupMembership.setChildType(GroupChildType.USER);
            groupMembership.setCreatedTime(System.currentTimeMillis());
            groupMembership.setUpdatedTime(System.currentTimeMillis());
            groupMembershipRepository.create(groupMembership);
        }
        return true;
    }

    @Override
    public boolean removeUsersFromGroup(List<String> userIds, String groupId) throws GovRegistryException, TException {
        for(int i=0; i < userIds.size(); i++){
            GroupMembershipEntityPK groupMembershipEntityPK = new GroupMembershipEntityPK();
            groupMembershipEntityPK.setParentId(groupId);
            groupMembershipEntityPK.setChildId(userIds.get(i));
            groupMembershipRepository.delete(groupMembershipEntityPK);
        }
        return true;
    }

    @Override
    public Map<String, GroupChildType> getGroupMembers(String groupId, int offset, int limit) throws  GovRegistryException, TException {
        HashMap<String, GroupChildType> groupMembers = new HashMap<>();
        HashMap<String, String> filters = new HashMap<>();
        filters.put(DBConstants.GroupMembershipTable.PARENT_ID, groupId);
        List<GroupMembership> groupMembershipList = groupMembershipRepository.select(filters, 0, -1);
        groupMembershipList.stream().forEach(gm->{groupMembers.put(gm.getChildId(), gm.getChildType());});
        return groupMembers;
    }

    @Override
    public boolean addChildGroupToParentGroup(String childId, String groupId) throws GovRegistryException, TException {
        //Todo check for cyclic dependencies
        GroupMembership groupMembership = new GroupMembership();
        groupMembership.setParentId(groupId);
        groupMembership.setChildId(childId);
        groupMembership.setChildType(GroupChildType.GROUP);
        groupMembership.setCreatedTime(System.currentTimeMillis());
        groupMembership.setUpdatedTime(System.currentTimeMillis());
        groupMembershipRepository.create(groupMembership);
        return true;
    }

    @Override
    public boolean removeChildGroupFromParentGroup(String childId, String groupId) throws GovRegistryException, TException {
        GroupMembershipEntityPK groupMembershipEntityPK = new GroupMembershipEntityPK();
        groupMembershipEntityPK.setParentId(groupId);
        groupMembershipEntityPK.setChildId(childId);
        groupMembershipRepository.delete(groupMembershipEntityPK);
        return true;
    }

    /**
     * * EntityType Operations
     * *
     */
    @Override
    public String createEntityType(EntityType entityType) throws GovRegistryException, TException {
        entityType.setEntityTypeId(entityType.domainId + ":" + entityType.name);
        entityType.setCreatedTime(System.currentTimeMillis());
        entityType.setUpdatedTime(System.currentTimeMillis());
        entityTypeRepository.create(entityType);
        return entityType.entityTypeId;
    }

    @Override
    public boolean updateEntityType(EntityType entityType) throws GovRegistryException, TException {
        entityType.setUpdatedTime(System.currentTimeMillis());
        EntityType oldEntityType = entityTypeRepository.get(entityType.entityTypeId);
        entityType.setCreatedTime(oldEntityType.createdTime);
        entityType = getUpdatedObject(oldEntityType, entityType);
        entityTypeRepository.update(entityType);
        return true;
    }

    @Override
    public boolean deleteEntityType(String entityTypeId) throws GovRegistryException, TException {
        entityTypeRepository.delete(entityTypeId);
        return true;
    }

    @Override
    public EntityType getEntityType(String entityTypeId) throws GovRegistryException, TException {
        return entityTypeRepository.get(entityTypeId);
    }

    @Override
    public List<EntityType> getEntityTypes(String domain, int offset, int limit) throws TException {
        HashMap<String, String> filters = new HashMap<>();
        filters.put(DBConstants.EntityTypeTable.DOMAIN_ID, domain);
        return entityTypeRepository.select(domain, offset, limit);
    }

    /**
     * * Permission Operations
     * *
     */
    @Override
    public String createPermissionType(PermissionType permissionType) throws GovRegistryException, TException {
        permissionType.setPermissionTypeId(permissionType.domainId+":"+permissionType.name);
        permissionType.setCreatedTime(System.currentTimeMillis());
        permissionType.setUpdatedTime(System.currentTimeMillis());
        permissionTypeRepository.create(permissionType);
        return permissionType.permissionTypeId;
    }

    @Override
    public boolean updatePermissionType(PermissionType permissionType) throws GovRegistryException, TException {
        permissionType.setUpdatedTime(System.currentTimeMillis());
        PermissionType oldPermissionType = permissionTypeRepository.get(permissionType.permissionTypeId);
        permissionType = getUpdatedObject(oldPermissionType, permissionType);
        permissionTypeRepository.update(permissionType);
        return true;
    }

    @Override
    public boolean deletePermissionType(String entityTypeId) throws GovRegistryException, TException {
        permissionTypeRepository.delete(entityTypeId);
        return true;
    }

    @Override
    public PermissionType getPermissionType(String permissionTypeId) throws GovRegistryException, TException {
        return permissionTypeRepository.get(permissionTypeId);
    }

    @Override
    public List<PermissionType> getPermissionTypes(String domain, int offset, int limit) throws GovRegistryException, TException {
        HashMap<String, String> filters = new HashMap<>();
        filters.put(DBConstants.PermissionTypeTable.DOMAIN_ID, domain);
        return permissionTypeRepository.select(filters, offset, limit);
    }

    /**
     * * Entity Operations
     * *
     */
    @Override
    public String createEntity(Entity entity) throws GovRegistryException, TException {
        entity.setEntityId(entity.domainId + ":" + entity.name);
        entity.setCreatedTime(System.currentTimeMillis());
        entity.setUpdatedTime(System.currentTimeMillis());
        entityRepository.create(entity);
        return entity.entityId;
    }

    @Override
    public boolean updateEntity(Entity entity) throws GovRegistryException, TException {
        entity.setUpdatedTime(System.currentTimeMillis());
        Entity oldEntity = entityRepository.get(entity.getEntityId());
        entity.setCreatedTime(oldEntity.createdTime);
        entity = getUpdatedObject(oldEntity, entity);
        entityRepository.update(entity);
        return true;
    }

    @Override
    public boolean deleteEntity(String entityId) throws GovRegistryException, TException {
        entityRepository.delete(entityId);
        return true;
    }

    @Override
    public Entity getEntity(String entityId) throws GovRegistryException, TException {
        return entityRepository.get(entityId);
    }

    @Override
    public List<Entity> searchEntities(String domain, String entityType, Map<String, String> filters, int offset, int limit) throws GovRegistryException, TException {
        return null;
    }



    private <T> T getUpdatedObject(T oldEntity, T newEntity) throws GovRegistryException {
        Field[] newEntityFields = newEntity.getClass().getDeclaredFields();
        Hashtable newHT = fieldsToHT(newEntityFields, newEntity);

        Class oldEntityClass = oldEntity.getClass();
        Field[] oldEntityFields = oldEntityClass.getDeclaredFields();

        for (Field field : oldEntityFields){
            field.setAccessible(true);
            Object o = newHT.get(field.getName());
            if (o != null){
                Field f = null;
                try {
                    f = oldEntityClass.getDeclaredField(field.getName());
                    f.setAccessible(true);
                    logger.debug("setting " + f.getName());
                    f.set(oldEntity, o);
                } catch (Exception e) {
                    throw new GovRegistryException(e.getMessage());
                }
            }
        }
        return oldEntity;
    }

    private static Hashtable<String, Object> fieldsToHT(Field[] fields, Object obj){
        Hashtable<String,Object> hashtable = new Hashtable<>();
        for (Field field: fields){
            field.setAccessible(true);
            try {
                Object retrievedObject = field.get(obj);
                if (retrievedObject != null){
                    logger.debug("scanning " + field.getName());
                    hashtable.put(field.getName(), field.get(obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return hashtable;
    }

    /**
     * * Sharing Entity with Users and Groups
     * *
     *
     * @param entityId
     * @param userList
     * @param permissionType
     */
    @Override
    public boolean shareEntityWithUsers(String entityId, List<String> userList, PermissionType perssionType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean revokeEntitySharingFromUsers(String entityId, List<String> userList, PermissionType perssionType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean shareEntityWithGroups(String entityId, List<String> groupList, PermissionType perssionType) throws GovRegistryException, TException {
        return false;
    }

    @Override
    public boolean revokeEntitySharingFromGroups(String entityId, List<String> groupList, PermissionType perssionType) throws GovRegistryException, TException {
        return false;
    }
}