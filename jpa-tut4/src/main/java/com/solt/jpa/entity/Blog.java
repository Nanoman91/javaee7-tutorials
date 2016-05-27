package com.solt.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Blog implements Serializable {

	private static final long serialVersionUID = 1L;

	public Blog() {
		security = new SecurityInfo();
		tags = new HashSet<>();
		rate = new ArrayList<>();
		comments = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String body;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    @ElementCollection
    @CollectionTable
    private Set<String> tags;

    @ElementCollection
    @CollectionTable
    private List<Integer> rate;
    
    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "blog")
    private List<Comment> comments;

    @Enumerated
    private Status status;

    @Embedded
    private SecurityInfo security;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    
    
    public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		comments.forEach(this::addComment);
		this.comments = comments;
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		this.security.setCreateUser(user.getLoginId());
		this.security.setModUser(user.getLoginId());
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public List<Integer> getRate() {
		return rate;
	}

	public void setRate(List<Integer> rate) {
		this.rate = rate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public SecurityInfo getSecurity() {
		return security;
	}

	public void setSecurity(SecurityInfo security) {
		this.security = security;
	}
	
	public void addComment(Comment comment) {
		comment.setBlog(this);
		comments.add(comment);
	}

	public void addTag(String tag) {
		tags.add(tag);
    }

    public List<String> getTagList() {
        return new ArrayList<>(tags);
    }

    public enum Status {
        Edit,
        Published
    }

}