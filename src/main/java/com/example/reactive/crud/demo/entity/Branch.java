package com.example.reactive.crud.demo.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Table("branch")
public class Branch {

    @Id
    private Long id;
    @NonNull
    private String branchNumber;
    @NonNull
    private String branchName;
    @NonNull
    private String city;
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Integer store_id;

}
