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
package org.apache.airavata.gov.registry.db.repositories;

import org.apache.airavata.gov.registry.db.utils.JPAUtils;
import org.apache.airavata.gov.registry.db.utils.ObjectMapperSingleton;
import org.apache.airavata.gov.registry.db.utils.QueryGenerator;
import org.apache.airavata.gov.registry.models.GovRegistryException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<T, E, Id> {
    private final static Logger logger = LoggerFactory.getLogger(AbstractRepository.class);

    private Class<T> thriftGenericClass;
    private Class<E> dbEntityGenericClass;
    private QueryGenerator queryGenerator;

    public AbstractRepository(Class<T> thriftGenericClass, Class<E> dbEntityGenericClass){
        this.thriftGenericClass = thriftGenericClass;
        this.dbEntityGenericClass = dbEntityGenericClass;
        this.queryGenerator = new QueryGenerator(dbEntityGenericClass.getSimpleName());
    }

    public T create(T t) throws GovRegistryException {
        return update(t);
    }

    public  T update(T t) throws GovRegistryException {
        Mapper mapper = ObjectMapperSingleton.getInstance();
        E entity = mapper.map(t, dbEntityGenericClass);
        E persistedCopy = JPAUtils.execute(entityManager -> entityManager.merge(entity));
        return mapper.map(persistedCopy, thriftGenericClass);
    }

    public boolean delete(Id id) throws GovRegistryException {
        JPAUtils.execute(entityManager -> {
            E entity = entityManager.find(dbEntityGenericClass, id);
            entityManager.remove(entity);
            return entity;
        });
        return true;
    }

    public T get(Id id) throws GovRegistryException {
        E entity = JPAUtils.execute(entityManager -> entityManager
                .find(dbEntityGenericClass, id));
        Mapper mapper = ObjectMapperSingleton.getInstance();
        return mapper.map(entity, thriftGenericClass);
    }

    public List<T> select(Map<String, String> filters, int offset, int limit) throws GovRegistryException {
        String queryString = queryGenerator.getSelectQuery(filters);

        List resultSet = JPAUtils.execute(entityManager -> entityManager.createQuery(queryString).setFirstResult(offset)
                .setMaxResults(limit).getResultList());
        Mapper mapper = ObjectMapperSingleton.getInstance();
        List<T> gatewayList = new ArrayList<>();
        resultSet.stream().forEach(rs -> gatewayList.add(mapper.map(rs, thriftGenericClass)));
        return gatewayList;
    }
}