package com.example.demo.entity;

import com.example.demo.entity.enums.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.OverridesAttribute;
import java.time.LocalDateTime;
import java.util.*;
@Data
@Entity

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public User(Long id, String email, Collection<? extends GrantedAuthority> authorities) {
        this. id = id;
        this. username = username;
        this. password = password;
        this. email = email;
        this. authorities = authorities;
    }


    public User(Long id, String username, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public User() {


    }

    @Column (nullable = false)
    private String firstname;

    @Column (nullable = false)
    private String lastname;

    @Column (unique = true, updatable = false)
    private String username;

    @Column (length = 3000)
    private String password;

    @Column (unique = true)
    private String email;

    @Column (columnDefinition = "text")
    private String info;

    @Column (updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd нн:mm:ss")
    private LocalDateTime createDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

   @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
    @Override
    public String getPassword() { return password;}

    @Override
    public boolean isAccountNonExpired() { return true;}

    @Override
    public boolean isAccountNonLocked() { return true;}

    @Override
    public boolean isCredentialsNonExpired() { return true;}

    @Override
    public boolean isEnabled() { return true;}
}








