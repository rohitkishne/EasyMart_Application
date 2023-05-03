package com.example.EasyMarket.Dto.RequestDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UpdateCustomerDetailRequestDto {

    String name;
    String address;
    String newEmail;
    String age;
    String mobNo;
}
