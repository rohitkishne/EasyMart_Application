package com.example.EasyMarket.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class SellerByParamResponseDto {
    String name;
    String email;
    int age;
    String mobNo;
}
