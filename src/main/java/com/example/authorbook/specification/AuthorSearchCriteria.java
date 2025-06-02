package com.example.authorbook.specification;


import com.example.authorbook.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSearchCriteria {

    private String name;
    private String surname;
    private String phone;
    private Gender gender;
    private String email;
}
