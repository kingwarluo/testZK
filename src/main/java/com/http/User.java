package com.http;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 461053599556782048L;

    private Long id;

    private String name;

    private Integer age;

}
