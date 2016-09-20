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
package org.apache.airavata.gov.registry.db.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "ENTITY", schema = "")
public class EntityEntity {
    private final static Logger logger = LoggerFactory.getLogger(EntityEntity.class);
    private String entityId;
    private String name;
    private String description;
    private byte[] metadata;
    private String fullText;
    private String createdTime;
    private String updatedTime;
    private DomainEntity domainByDomainId;
    private EntityEntity entityByParentEntityId;

    @Id
    @Column(name = "ENTITY_ID")
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "METADATA")
    public byte[] getMetadata() {
        return metadata;
    }

    public void setMetadata(byte[] metadata) {
        this.metadata = metadata;
    }

    @Basic
    @Column(name = "FULL_TEXT")
    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @Basic
    @Column(name = "CREATED_TIME")
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "UPDATED_TIME")
    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityEntity that = (EntityEntity) o;

        if (entityId != null ? !entityId.equals(that.entityId) : that.entityId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!Arrays.equals(metadata, that.metadata)) return false;
        if (fullText != null ? !fullText.equals(that.fullText) : that.fullText != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (updatedTime != null ? !updatedTime.equals(that.updatedTime) : that.updatedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = entityId != null ? entityId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (metadata != null ? Arrays.hashCode(metadata) : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "DOMAIN_ID", referencedColumnName = "DOMAIN_ID")
    public DomainEntity getDomainByDomainId() {
        return domainByDomainId;
    }

    public void setDomainByDomainId(DomainEntity domainByDomainId) {
        this.domainByDomainId = domainByDomainId;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_ENTITY_ID", referencedColumnName = "ENTITY_ID")
    public EntityEntity getEntityByParentEntityId() {
        return entityByParentEntityId;
    }

    public void setEntityByParentEntityId(EntityEntity entityByParentEntityId) {
        this.entityByParentEntityId = entityByParentEntityId;
    }
}