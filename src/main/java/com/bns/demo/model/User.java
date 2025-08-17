package com.bns.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "email_id", nullable = false, unique = true, length = 100)
    private String emailId;

    @Column(name = "contact_num", length = 15)
    private String contactNum;

    @Column(name = "usr_pwd", length = 255)
    private String password;

    @Column(name = "crt_ts", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime crtTs;

    @Column(name = "upt_ts")
    private LocalDateTime uptTs;

    @PrePersist
    protected void onCreate() {
        this.crtTs = LocalDateTime.now();
        this.uptTs = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.uptTs = LocalDateTime.now();
    }
}