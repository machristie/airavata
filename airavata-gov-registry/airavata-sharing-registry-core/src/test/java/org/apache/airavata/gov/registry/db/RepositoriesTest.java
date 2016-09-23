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
package org.apache.airavata.gov.registry.db;

import junit.framework.Assert;
import org.apache.airavata.gov.registry.db.entities.DomainEntity;
import org.apache.airavata.gov.registry.db.entities.UserEntity;
import org.apache.airavata.gov.registry.db.repositories.DomainRepository;
import org.apache.airavata.gov.registry.db.repositories.UserRepository;
import org.apache.airavata.gov.registry.db.utils.DBConstants;
import org.apache.airavata.gov.registry.models.Domain;
import org.apache.airavata.gov.registry.models.GovRegistryException;
import org.apache.airavata.gov.registry.models.User;
import org.apache.airavata.gov.registry.models.UserGroup;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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

        DomainRepository domainRepository = new DomainRepository(Domain.class, DomainEntity.class);
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

        UserRepository userRepository = new UserRepository(User.class, UserEntity.class);
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

        User user3 = new User();
        String userName3 = "test-user." + System.currentTimeMillis();
        String userId3 = domainId + ":" + userName3;
        user3.setUserId(userId3);
        user3.setUserName(userName3);
        user3.setDomainId(domainId);
        user3.setCreatedTime(System.currentTimeMillis());
        user3.setUpdatedTime(System.currentTimeMillis());

        filters = new HashMap<>();
        filters.put(DBConstants.UserTable.USER_NAME, "test");
        Assert.assertTrue(userRepository.select(filters, 0, 10).size() > 0);

        //Creating Groups
        UserGroup userGroup1 = new UserGroup();
        String groupName1 = "test-group";
        String groupId1 = domainId + ":" + groupName1 + "." + System.currentTimeMillis();
        userGroup1.setGroupId(groupId1);
        userGroup1.setDomainId(domainId);
        userGroup1.setName(groupName1);
        userGroup1.setDescription("test group description");
        userGroup1.setOwnerId(userId1);
        userGroup1.setCreatedTime(System.currentTimeMillis());
        userGroup1.setUpdatedTime(System.currentTimeMillis());

    }
}