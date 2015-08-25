/*
 * Copyright 2012 NGDATA nv
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ngdata.sep.impl;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.EmptyWatcher;
import org.apache.hadoop.hbase.regionserver.wal.HLog;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class HBaseShims {
  public static Get newGet() { return new Get(); }
  public static Result newResult(List<KeyValue> list) { return new Result(list); }
  public static Result newResultFromObject(List<Object> list) { 
	  List<KeyValue> listKeys = new ArrayList<KeyValue>();
	  for(Object key: list){
		  listKeys.add((KeyValue) key);
	  }
	  return new Result(listKeys);
  }  
  public static EmptyWatcher getEmptyWatcherInstance() { return EmptyWatcher.instance; }
  public static String getHLogDirectoryName(String serverName) { return HLog.getHLogDirectoryName(serverName); }
  public static boolean isDelete(Object keyValue){
      KeyValue kv = (KeyValue) keyValue;
	  return kv.isDelete();
  }
  public static boolean isDeleteFamily(Object keyValue){
	  KeyValue kv = (KeyValue) keyValue;
	  return kv.isDeleteFamily();
  }
  public static List<KeyValue> sort(List<Object> listKeyValues){
	  List<KeyValue> listKeys = new ArrayList<KeyValue>();
	  for(Object key : listKeyValues){
		  listKeys.add((KeyValue) key);
	  }
	  Collections.sort(listKeys, KeyValue.COMPARATOR);
	  return listKeys;
  }
  public static byte[] cloneRow(Object keyValue){
          KeyValue key = (KeyValue) keyValue;
	  return key.getRow();
  }
  public static byte[] cloneFamily(Object keyValue){
          KeyValue key = (KeyValue) keyValue;
	  return key.getFamily();
  }
  public static byte[] cloneQualifier(Object keyValue){
          KeyValue key = (KeyValue) keyValue;
	  return key.getQualifier();
  }
  public static byte getTypeByte(Object keyValue){
	  KeyValue kv = (KeyValue) keyValue;
	  return kv.getType();
  }
  public static boolean matchingFamily(Object keyValue, byte[] columnFamily){
          KeyValue kv = (KeyValue) keyValue;
	  return kv.matchingFamily(columnFamily);
  } 
  public static boolean matchingColumn(Object keyValue, byte[] columnFamily, byte[] columnQualifier){
          KeyValue kv = (KeyValue) keyValue;
	  return kv.matchingColumn(columnFamily, columnQualifier);
  }
  public static List<Object> getKeyValues(Result result){
	  List<Object> listKeys = new ArrayList<Object>();
	  for(KeyValue key: result.list()){
		  listKeys.add((Object) key);
	  }
	  return listKeys;
  }  
  public static KeyValue castToCellOrKey(Object key){
	  return (KeyValue) key;
  }
}
