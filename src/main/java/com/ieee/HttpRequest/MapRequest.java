/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieee.HttpRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Iterator;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

public class MapRequest implements java.io.Serializable {
    
    private static String proxyServer = null; 
  
    private static int proxyPort = 8080;
    
    private static String login = null;
    
    private static String password = null;
    
    public static String getResponse(String url){
        
        try{
            if(url == null){
                return null;
            }
            
            String result = null;

            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(proxyServer, proxyPort), 
                    new UsernamePasswordCredentials(login, password));

            HttpClientBuilder clientbuilder = HttpClients.custom();

            //Setting the credentials
            clientbuilder = clientbuilder.setDefaultCredentialsProvider(credsProvider);  

            //Building the CloseableHttpClient object
            CloseableHttpClient httpclient = clientbuilder.build();

            
            HttpHost proxyHost = new HttpHost(proxyServer, proxyPort); 

            RequestConfig.Builder reqconfigconbuilder= RequestConfig.custom();
            reqconfigconbuilder = reqconfigconbuilder.setProxy(proxyHost);
            RequestConfig config = reqconfigconbuilder.build();

            HttpGet request = new HttpGet(url);
            
            //request.setConfig(config); //#Important needs to be unselected

            HttpResponse response = httpclient.execute(request);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                 result = EntityUtils.toString(entity);
            }

            httpclient.close();

            return result; 
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
