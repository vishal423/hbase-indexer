HBase Indexer
=============

HBase Indexer allows you to easily and quickly index HBase rows into Solr.
Usage documentation can be found on the hbase-indexer Wiki -
http://github.com/NGDATA/hbase-indexer/wiki.

## Subprojects

### HBase SEP

A standalone library for asynchronously processing HBase mutation events
by hooking into HBase replication, see [the SEP readme](hbase-sep/README.md).

### HBase SEP & replication monitoring

A standalone utility to monitor HBase replication progress,
see [the SEP-tools readme](hbase-sep/hbase-sep-tools/README.md).


## Building

You can build the full hbase-indexer project as follows:

    mvn clean install -DskipTests

**The profile 0.98, 0.96, 0.94 cannot be used at the moment since the code was modified to work with HBase 1.1.0**

The default build is linked to HBase 0.94. In order to build for HBase 0.98,
run the following command:

    mvn clean install -DskipTests -Dhbase.api=0.98

The default build is linked to HBase 0.94. In order to build for HBase 1.1.0,
run the following command:

    mvn clean install -DskipTests -Dhbase.api=1.1.0


### Building Hortonworks specific

In order to build the full hbase-indexer project with an specific version of Hortonworks.

      mvn clean package -Pdist -DskipTests -Dhbase.api=1.1.2 -Dhdp.version=".2.4.2.0-258"

**The profile 1.1.2 can be used with the hdp version suffix. (`-Dhdp.version`)**
