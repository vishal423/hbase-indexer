/*
 * LucidWorks, Inc. Developer License Agreement
 * 
 */

package com.ngdata.hbaseindexer.indexer;

import org.apache.solr.client.solrj.impl.HttpClientConfigurer;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.Krb5HttpClientConfigurer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SecurityUtils {
    public static final String LWW_JAAS_FILE = "lww.jaas.file";
    public static final String LWW_JAAS_APPNAME = "lww.jaas.appname";

    private static Log log = LogFactory.getLog(SecurityUtils.class);

    public static void setSecurityConfig() {
        final String jaasFile = System.getProperty(LWW_JAAS_FILE);
        if (jaasFile != null) {
            log.info("Using kerberized Solr.");
            System.setProperty("java.security.auth.login.config", jaasFile);
            final String appname = System.getProperty(LWW_JAAS_APPNAME, "Client");
            System.setProperty("solr.kerberos.jaas.appname", appname);
            HttpClientUtil.setConfigurer(new Krb5HttpClientConfigurer());
        }
    }
}
