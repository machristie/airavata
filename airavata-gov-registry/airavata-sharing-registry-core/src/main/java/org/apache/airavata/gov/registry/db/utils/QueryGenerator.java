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
package org.apache.airavata.gov.registry.db.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class QueryGenerator {
    private final static Logger logger = LoggerFactory.getLogger(QueryGenerator.class);

    private String tableName;


    public QueryGenerator(String tableName){
        this.tableName = tableName;
    }

    public String getSelectQuery(Map<String, String> filters){
        String query = "SELECT p from " + tableName + " as p WHERE ";
        if(filters == null || filters.size() == 0){
            query +=  "1";
        }else{
            for(String k : filters.keySet()){
                query += "p." + k + " LIKE '%" + filters.get(k) + "%' AND ";
            }
            query = query.substring(0, query.length()-5);
        }

        query += " ORDER BY p.createdTime DESC";

        return query;
    }
}