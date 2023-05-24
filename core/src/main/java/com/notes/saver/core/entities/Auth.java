package com.notes.saver.core.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_auth_data")
public class Auth {
    @Id
    @Column(name = "userId")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    
    @OneToMany(mappedBy = "auth")
    private List<Notes> notes;
    
    @CreationTimestamp
    private LocalDateTime creationTime;

    public Auth() {
    }

    public Auth(int id, String username, String password, String email,List<Notes> notes,LocalDateTime creationTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.notes = notes;
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Notes> getNotes() {
		return notes;
	}

	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Auth [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", creationTime=" + creationTime + "]";
	}

}
