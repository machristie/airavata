/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.airavata.model.messaging.event;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all") public class TaskIdentifier implements org.apache.thrift.TBase<TaskIdentifier, TaskIdentifier._Fields>, java.io.Serializable, Cloneable, Comparable<TaskIdentifier> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TaskIdentifier");

  private static final org.apache.thrift.protocol.TField TASK_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("taskId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField WORKFLOW_NODE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("workflowNodeId", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EXPERIMENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("experimentId", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TaskIdentifierStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TaskIdentifierTupleSchemeFactory());
  }

  private String taskId; // required
  private String workflowNodeId; // required
  private String experimentId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  @SuppressWarnings("all") public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TASK_ID((short)1, "taskId"),
    WORKFLOW_NODE_ID((short)2, "workflowNodeId"),
    EXPERIMENT_ID((short)3, "experimentId");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TASK_ID
          return TASK_ID;
        case 2: // WORKFLOW_NODE_ID
          return WORKFLOW_NODE_ID;
        case 3: // EXPERIMENT_ID
          return EXPERIMENT_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TASK_ID, new org.apache.thrift.meta_data.FieldMetaData("taskId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WORKFLOW_NODE_ID, new org.apache.thrift.meta_data.FieldMetaData("workflowNodeId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXPERIMENT_ID, new org.apache.thrift.meta_data.FieldMetaData("experimentId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TaskIdentifier.class, metaDataMap);
  }

  public TaskIdentifier() {
  }

  public TaskIdentifier(
    String taskId,
    String workflowNodeId,
    String experimentId)
  {
    this();
    this.taskId = taskId;
    this.workflowNodeId = workflowNodeId;
    this.experimentId = experimentId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TaskIdentifier(TaskIdentifier other) {
    if (other.isSetTaskId()) {
      this.taskId = other.taskId;
    }
    if (other.isSetWorkflowNodeId()) {
      this.workflowNodeId = other.workflowNodeId;
    }
    if (other.isSetExperimentId()) {
      this.experimentId = other.experimentId;
    }
  }

  public TaskIdentifier deepCopy() {
    return new TaskIdentifier(this);
  }

  @Override
  public void clear() {
    this.taskId = null;
    this.workflowNodeId = null;
    this.experimentId = null;
  }

  public String getTaskId() {
    return this.taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public void unsetTaskId() {
    this.taskId = null;
  }

  /** Returns true if field taskId is set (has been assigned a value) and false otherwise */
  public boolean isSetTaskId() {
    return this.taskId != null;
  }

  public void setTaskIdIsSet(boolean value) {
    if (!value) {
      this.taskId = null;
    }
  }

  public String getWorkflowNodeId() {
    return this.workflowNodeId;
  }

  public void setWorkflowNodeId(String workflowNodeId) {
    this.workflowNodeId = workflowNodeId;
  }

  public void unsetWorkflowNodeId() {
    this.workflowNodeId = null;
  }

  /** Returns true if field workflowNodeId is set (has been assigned a value) and false otherwise */
  public boolean isSetWorkflowNodeId() {
    return this.workflowNodeId != null;
  }

  public void setWorkflowNodeIdIsSet(boolean value) {
    if (!value) {
      this.workflowNodeId = null;
    }
  }

  public String getExperimentId() {
    return this.experimentId;
  }

  public void setExperimentId(String experimentId) {
    this.experimentId = experimentId;
  }

  public void unsetExperimentId() {
    this.experimentId = null;
  }

  /** Returns true if field experimentId is set (has been assigned a value) and false otherwise */
  public boolean isSetExperimentId() {
    return this.experimentId != null;
  }

  public void setExperimentIdIsSet(boolean value) {
    if (!value) {
      this.experimentId = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TASK_ID:
      if (value == null) {
        unsetTaskId();
      } else {
        setTaskId((String)value);
      }
      break;

    case WORKFLOW_NODE_ID:
      if (value == null) {
        unsetWorkflowNodeId();
      } else {
        setWorkflowNodeId((String)value);
      }
      break;

    case EXPERIMENT_ID:
      if (value == null) {
        unsetExperimentId();
      } else {
        setExperimentId((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TASK_ID:
      return getTaskId();

    case WORKFLOW_NODE_ID:
      return getWorkflowNodeId();

    case EXPERIMENT_ID:
      return getExperimentId();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TASK_ID:
      return isSetTaskId();
    case WORKFLOW_NODE_ID:
      return isSetWorkflowNodeId();
    case EXPERIMENT_ID:
      return isSetExperimentId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TaskIdentifier)
      return this.equals((TaskIdentifier)that);
    return false;
  }

  public boolean equals(TaskIdentifier that) {
    if (that == null)
      return false;

    boolean this_present_taskId = true && this.isSetTaskId();
    boolean that_present_taskId = true && that.isSetTaskId();
    if (this_present_taskId || that_present_taskId) {
      if (!(this_present_taskId && that_present_taskId))
        return false;
      if (!this.taskId.equals(that.taskId))
        return false;
    }

    boolean this_present_workflowNodeId = true && this.isSetWorkflowNodeId();
    boolean that_present_workflowNodeId = true && that.isSetWorkflowNodeId();
    if (this_present_workflowNodeId || that_present_workflowNodeId) {
      if (!(this_present_workflowNodeId && that_present_workflowNodeId))
        return false;
      if (!this.workflowNodeId.equals(that.workflowNodeId))
        return false;
    }

    boolean this_present_experimentId = true && this.isSetExperimentId();
    boolean that_present_experimentId = true && that.isSetExperimentId();
    if (this_present_experimentId || that_present_experimentId) {
      if (!(this_present_experimentId && that_present_experimentId))
        return false;
      if (!this.experimentId.equals(that.experimentId))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TaskIdentifier other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTaskId()).compareTo(other.isSetTaskId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTaskId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.taskId, other.taskId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWorkflowNodeId()).compareTo(other.isSetWorkflowNodeId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWorkflowNodeId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.workflowNodeId, other.workflowNodeId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExperimentId()).compareTo(other.isSetExperimentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExperimentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.experimentId, other.experimentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TaskIdentifier(");
    boolean first = true;

    sb.append("taskId:");
    if (this.taskId == null) {
      sb.append("null");
    } else {
      sb.append(this.taskId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("workflowNodeId:");
    if (this.workflowNodeId == null) {
      sb.append("null");
    } else {
      sb.append(this.workflowNodeId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("experimentId:");
    if (this.experimentId == null) {
      sb.append("null");
    } else {
      sb.append(this.experimentId);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetTaskId()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'taskId' is unset! Struct:" + toString());
    }

    if (!isSetWorkflowNodeId()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'workflowNodeId' is unset! Struct:" + toString());
    }

    if (!isSetExperimentId()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'experimentId' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TaskIdentifierStandardSchemeFactory implements SchemeFactory {
    public TaskIdentifierStandardScheme getScheme() {
      return new TaskIdentifierStandardScheme();
    }
  }

  private static class TaskIdentifierStandardScheme extends StandardScheme<TaskIdentifier> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TaskIdentifier struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TASK_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.taskId = iprot.readString();
              struct.setTaskIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // WORKFLOW_NODE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.workflowNodeId = iprot.readString();
              struct.setWorkflowNodeIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXPERIMENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.experimentId = iprot.readString();
              struct.setExperimentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TaskIdentifier struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.taskId != null) {
        oprot.writeFieldBegin(TASK_ID_FIELD_DESC);
        oprot.writeString(struct.taskId);
        oprot.writeFieldEnd();
      }
      if (struct.workflowNodeId != null) {
        oprot.writeFieldBegin(WORKFLOW_NODE_ID_FIELD_DESC);
        oprot.writeString(struct.workflowNodeId);
        oprot.writeFieldEnd();
      }
      if (struct.experimentId != null) {
        oprot.writeFieldBegin(EXPERIMENT_ID_FIELD_DESC);
        oprot.writeString(struct.experimentId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TaskIdentifierTupleSchemeFactory implements SchemeFactory {
    public TaskIdentifierTupleScheme getScheme() {
      return new TaskIdentifierTupleScheme();
    }
  }

  private static class TaskIdentifierTupleScheme extends TupleScheme<TaskIdentifier> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TaskIdentifier struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.taskId);
      oprot.writeString(struct.workflowNodeId);
      oprot.writeString(struct.experimentId);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TaskIdentifier struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.taskId = iprot.readString();
      struct.setTaskIdIsSet(true);
      struct.workflowNodeId = iprot.readString();
      struct.setWorkflowNodeIdIsSet(true);
      struct.experimentId = iprot.readString();
      struct.setExperimentIdIsSet(true);
    }
  }

}

