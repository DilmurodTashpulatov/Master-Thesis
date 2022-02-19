/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieee.Models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dilmurod Tashpulatov
 */
@Entity
@Table
public class SearchString implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String count;
    private String search_count;
    private String search_string;
    
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Paper> papers = new ArrayList<>();

    public SearchString() {
    }

    
    
    public SearchString(String count, String search_count, String search_string) {
        this.count = count;
        this.search_count = search_count;
        this.search_string = search_string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSearch_count() {
        return search_count;
    }

    public void setSearch_count(String search_count) {
        this.search_count = search_count;
    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    @Override
    public String toString() {
        return "SearchString{" + "count=" + count + ", search_count=" + search_count + ", search_string=" + search_string + '}';
    }
    
}
