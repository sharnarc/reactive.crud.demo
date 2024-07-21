package com.example.reactive.crud.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

//@NoArgsConstructor
@Builder
@Setter
@Getter
@AllArgsConstructor
public class UpdateResponseDTO {

private String message;
private String rollNumber;
//private Set<Integer> id;



}
