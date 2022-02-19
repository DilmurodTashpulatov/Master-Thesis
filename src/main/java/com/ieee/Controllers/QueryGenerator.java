/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieee.Controllers;

/**
 *
 * @author Dilmurod Tashpulatov
 */

public class QueryGenerator implements java.io.Serializable{
    public static String getQuery(String criteria, String apiKey){
        try{
            return "http://ieeexploreapi.ieee.org/api/v1/search/articles?apikey="+apiKey+"&format=json&max_records=2000&start_record=1&sort_order=asc&sort_field=article_number&querytext="+(criteria.replaceAll(" ", "%20"));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
