package com.accio.librarymanagementsystem.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class updateRequest {

    private Integer studentId;
    private String emailId;
    private String address;

}
