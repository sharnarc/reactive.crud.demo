package com.example.reactive.crud.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

/**
 * <code>User</code> is user object as in postgres
 *
 * @author SUMANPattanaik
 */
@Table("users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column("user_id")
    private String userId;

    @Column("common_name")
    private String userName;

    @Column("user_status")
    private String status;

    @Column("lock_status")
    private String lockedStatus;

    @Column("carriers")
    private String carriers;

    @Column("entitlements")
    private String entitlements;

    @Column("registered_date")
    private String registeredOn;

    @Column("modified_date")
    private Timestamp modifiedOn;
}
