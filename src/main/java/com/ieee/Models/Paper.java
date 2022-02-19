/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ieee.Models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ditashpu
 */
@Entity
@Table
public class Paper implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    String title;
    String publisher;
    String isbn;
    String rank;
    String access_type;
    String content_type;
    
    @Lob
    @Column(length=5000)
    String abstract_text;
                 
    String pdf_url;
    String html_url;
    String conference_location;
    String conference_dates;
    String publication_date;
    String publication_year;
    String start_page;
    String end_page;
    String citing_paper_count;
    String citing_patent_count;
    
    String ieee_terms;
    String author_terms;
    
    @OneToMany(cascade=CascadeType.PERSIST)
    private List<Author> authors = new ArrayList<>();

    public Paper() {
    }

    
    
    public Paper(String title, String publisher, String isbn, String rank, String access_type, String content_type, 
            String abstract_text, String pdf_url, String html_url, 
            String conference_location, String conference_dates, String publication_date, 
            String publication_year, String start_page, String end_page, 
            String citing_paper_count, String citing_patent_count, 
            String ieee_terms, String author_terms) {
        this.title = title;
        this.publisher = publisher;
        this.isbn = isbn;
        this.rank = rank;
        this.access_type = access_type;
        this.content_type = content_type;
        this.abstract_text = abstract_text;
        this.pdf_url = pdf_url;
        this.html_url = html_url;
        this.conference_location = conference_location;
        this.conference_dates = conference_dates;
        this.publication_date = publication_date;
        this.publication_year = publication_year;
        this.start_page = start_page;
        this.end_page = end_page;
        this.citing_paper_count = citing_paper_count;
        this.citing_patent_count = citing_patent_count;
        this.ieee_terms = ieee_terms;
        this.author_terms = author_terms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAccess_type() {
        return access_type;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getAbstract_text() {
        return abstract_text;
    }

    public void setAbstract_text(String abstract_text) {
        this.abstract_text = abstract_text;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getConference_location() {
        return conference_location;
    }

    public void setConference_location(String conference_location) {
        this.conference_location = conference_location;
    }

    public String getConference_dates() {
        return conference_dates;
    }

    public void setConference_dates(String conference_dates) {
        this.conference_dates = conference_dates;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getStart_page() {
        return start_page;
    }

    public void setStart_page(String start_page) {
        this.start_page = start_page;
    }

    public String getEnd_page() {
        return end_page;
    }

    public void setEnd_page(String end_page) {
        this.end_page = end_page;
    }

    public String getCiting_paper_count() {
        return citing_paper_count;
    }

    public void setCiting_paper_count(String citing_paper_count) {
        this.citing_paper_count = citing_paper_count;
    }

    public String getCiting_patent_count() {
        return citing_patent_count;
    }

    public void setCiting_patent_count(String citing_patent_count) {
        this.citing_patent_count = citing_patent_count;
    }

    public String getIeee_terms() {
        return ieee_terms;
    }

    public void setIeee_terms(String ieee_terms) {
        this.ieee_terms = ieee_terms;
    }

    public String getAuthor_terms() {
        return author_terms;
    }

    public void setAuthor_terms(String author_terms) {
        this.author_terms = author_terms;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
