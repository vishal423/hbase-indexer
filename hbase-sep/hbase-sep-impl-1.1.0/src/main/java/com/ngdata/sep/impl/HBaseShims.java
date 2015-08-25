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

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.wal.DefaultWALProvider;
import org.apache.hadoop.hbase.zookeeper.EmptyWatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HBaseShims {
  public static Get newGet() { return new Get(Bytes.toBytes(" ")); }
  public static Result newResult(List<Cell> list) { return Result.create(new ArrayList<Cell>(list)); }
  public static Result newResultFromObject(List<Object> list) { 
	  List<Cell> listKeys = new ArrayList<Cell>();
	  for(Object key: list){
		  listKeys.add((Cell) key);
	  }
	  return Result.create(new ArrayList<Cell>(listKeys)); 
  }
  public static EmptyWatcher getEmptyWatcherInstance() { return EmptyWatcher.instance; }
  public static String getHLogDirectoryName(String serverName) { return DefaultWALProvider.getWALDirectoryName(serverName); }

  public static boolean isDelete(Object keyValue){
	  Cell kv = (Cell) keyValue;
	  return CellUtil.isDelete(kv);
  }
  public static boolean isDeleteFamily(Object keyValue){
	  Cell kv = (Cell) keyValue;
	  return CellUtil.isDeleteFamily(kv);
  }
  public static List<Cell> sort(List<Object> listKeyValues){
	  List<Cell> listKeys = new ArrayList<Cell>();
	  for(Object key : listKeyValues){
		  listKeys.add((Cell) key);
	  }
	  Collections.sort(listKeys, KeyValue.COMPARATOR);
	  return listKeys;
  }
  public static byte[] cloneRow(Object keyValue){
	  return CellUtil.cloneRow((Cell)keyValue);
  }
  public static byte[] cloneFamily(Object keyValue){
	  return CellUtil.cloneFamily((Cell)keyValue);
  }
  public static byte[] cloneQualifier(Object keyValue){
	  return CellUtil.cloneQualifier((Cell)keyValue);
  }
  public static byte getTypeByte(Object keyValue){
	  Cell kv = (Cell) keyValue;
	  return kv.getTypeByte();
  }
  public static boolean matchingFamily(Object keyValue, byte[] columnFamily){
	  return CellUtil.matchingFamily((Cell)keyValue, columnFamily);
  }
  
  public static boolean matchingColumn(Object keyValue, byte[] columnFamily, byte[] columnQualifier){
	  return CellUtil.matchingColumn((Cell) keyValue, columnFamily, columnQualifier);
  }
  public static List<Object> getKeyValues(Result result){
	  List<Object> listKeys = new ArrayList<Object>();
	  for(Cell key: result.listCells()){
		  listKeys.add((Object) key);
	  }
	  return listKeys;
  }
  public static Cell castToCellOrKey(Object key){
	  return (Cell) key;
  }
}
