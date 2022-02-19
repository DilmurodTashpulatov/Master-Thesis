/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieee.Controllers;

import com.ieee.Database.InserController;
import com.ieee.HttpRequest.MapRequest;
import com.ieee.Models.Author;
import com.ieee.Models.Paper;
import com.ieee.Models.SearchString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Dilmurod Tashpulatov
 */

public class PerfomrQuery implements java.io.Serializable{
    
    private String apiKey = null; // Please provide API Key 
    
    public void pefomrmQuery(String searchString){
        try{
           
            
            String query = QueryGenerator.getQuery(searchString, apiKey);
            String response = MapRequest.getResponse(query);
            
            if(response != null){
                                
                SearchString ss = this.parse(searchString, response);
                
                if(ss != null){
                    
                    InserController.insertAll(ss);
                    
                }
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
    }
    
    private SearchString parse(String criteria, String json){
        try{
            if(json == null){
                return null;
            }
            
            JSONParser parser = new JSONParser();
            
            JSONObject jsonObject = (JSONObject) parser.parse(json);
            
            List<Paper> papers = new ArrayList<>();
            
            String total_records = this.getData(new String[]{"total_records"}, jsonObject);
            
            String total_searched = this.getData(new String[]{"total_searched"}, jsonObject);
            
            JSONArray articlesJsonArray = (JSONArray) jsonObject.get("articles");
            
            if(articlesJsonArray == null) return null;
            
            Iterator i = articlesJsonArray.iterator();
            
            int countT = 0;
            while(i.hasNext()){
                 JSONObject article = (JSONObject)i.next();
                 
                 String title = this.getData(new String[]{"title"}, article);
                 
                 String publisher = this.getData(new String[]{"publisher"}, article);
                 
                 String isbn = this.getData(new String[]{"isbn"}, article);
                 
                 String rank = this.getData(new String[]{"rank"}, article);
                 
                 String access_type = this.clean(this.getData(new String[]{"access_type"}, article));
                 
                 String content_type = this.getData(new String[]{"content_type"}, article);
                 
                 String abstract_text = this.getData(new String[]{"abstract"}, article);
                 

                 String pdf_url = this.getData(new String[]{"pdf_url"}, article);
                 
                 String html_url = this.getData(new String[]{"html_url"}, article);
                 
                 String conference_location = this.getData(new String[]{"conference_location"}, article);
                 
                 String conference_dates =  this.getData(new String[]{"conference_dates"}, article);
                 
                 String publication_date = this.getData(new String[]{"publication_date"}, article);
                 
                 String publication_year = this.getData(new String[]{"publication_year"}, article);
                
                 String start_page  = this.getData(new String[]{"start_page"}, article);
                 
                 String end_page = this.getData(new String[]{"end_page"}, article);
                 
                 String citing_paper_count = this.getData(new String[]{"citing_paper_count"}, article);
                 
                 String citing_patent_count = this.getData(new String[]{"citing_patent_count"}, article);
                 
                 
                JSONObject authorsObj = (JSONObject) article.get("authors");
                
                List<Author> authors = new ArrayList<>();
                
                if(authorsObj != null){
                    
                    JSONArray authorsJsonArray = (JSONArray) authorsObj.get("authors");
                    
                    Iterator o = authorsJsonArray.iterator();
                    
                    while(o.hasNext()){
                        
                        JSONObject author = (JSONObject)o.next();
                        
                        String affiliation = this.getData(new String[]{"affiliation"}, author);
                        
                        if(affiliation != null && affiliation.length() > 100){
                            affiliation = affiliation.substring(0, 100);
                        }
                        
                        String authorUrl = this.getData(new String[]{"authorUrl"}, author);
                        
                        String full_name =   this.getData(new String[]{"full_name"}, author);   
                        
                        String author_order = this.getData(new String[]{"author_order"}, author);
                        
                        Author authorEntity = new Author(affiliation,full_name,author_order,authorUrl);
                        
                        authors.add(authorEntity);
                    }
                }
                
                
                
                JSONObject index_termsObj = (JSONObject) article.get("index_terms");
                
                JSONObject ieee_termsObj = (JSONObject) index_termsObj.get("ieee_terms");
                
                String ieee_terms = null;
                
                if(ieee_termsObj != null){
                    
                    JSONArray ieee_termsArray = (JSONArray) ieee_termsObj.get("terms");
                    
                    ieee_terms = ieee_termsArray.toJSONString();
                }
                
                
                
                JSONObject author_termsObj = (JSONObject) index_termsObj.get("author_terms");
                
                String author_terms = null;
                
                if(author_termsObj != null){
                    
                    JSONArray author_termsArray = (JSONArray) author_termsObj.get("terms");
                    
                    author_terms = author_termsArray.toJSONString();
                }
                

                Paper paper = new Paper(title, publisher, isbn, rank, access_type, content_type,  abstract_text,  pdf_url,  html_url,  conference_location,  conference_dates,  publication_date,  publication_year,  start_page,  end_page,  citing_paper_count,  citing_patent_count,  ieee_terms,  author_terms);
                
                paper.setAuthors(authors);
                
                papers.add(paper);
                 
            }
            
            System.out.println("Total number of records => "+ countT);
            
            String count =  this.getData(new String[]{"total_records"}, jsonObject);
            
            String search_count = this.getData(new String[]{"total_searched"}, jsonObject);
            
            SearchString searcString = new SearchString( count,  search_count, criteria);
            
            searcString.setPapers(papers);
            
            return searcString;
                        
        }catch(Exception e){
            
            e.printStackTrace();
            
        }
        return null;
    }
    
    
    private String getData(String[] list, JSONObject jsonObject){
        try{
            JSONObject newJsonObj = jsonObject;
  
            for(int i = 0; i < list.length; i++){
               if(i == list.length-1 && newJsonObj != null){
                   return this.clean(String.valueOf((Object)newJsonObj.get(list[i])));
                }
                //-------------------------------
                newJsonObj = (JSONObject)newJsonObj.get(list[i]);
                if(newJsonObj == null){
                    return null;
                }
               //-------------------------------
            } 
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    
    private String clean(String str){
        try{
            if(str != null && str.length() > 500){
                str = str.substring(0, 500);
            }
            return str.replaceAll("[^a-zA-Z0-9]", " ");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    private boolean skip(String abstract_text){
                         
                boolean bool = (
                                    abstract_text.toLowerCase().contains("asdfasdfasdf") && 
                                    abstract_text.toLowerCase().contains("continuous") && 
                                    abstract_text.toLowerCase().contains("machine") && 
                                    abstract_text.toLowerCase().contains("learning") 

                                 && (
                                    abstract_text.toLowerCase().contains("integrat") || 
                                    abstract_text.toLowerCase().contains("deploy")||
                                    abstract_text.toLowerCase().contains("testing") ||
                                    abstract_text.toLowerCase().contains("deliver") ||
                                    abstract_text.toLowerCase().contains("monitor") ||
                                    abstract_text.toLowerCase().contains("monitor") ||
                                    abstract_text.toLowerCase().contains("feedback") ||
                                    abstract_text.toLowerCase().contains("develop")
                                 )
                         );
                return bool;
    }
    
}
