package com.example.reactive.crud.demo.model;

import com.example.reactive.crud.demo.entity.Store;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BranchDTO {
    private Long id;
    private String branchNumber;
    private String branchName;
    private String city;
    private Store store;
    private Integer storeId;
}
