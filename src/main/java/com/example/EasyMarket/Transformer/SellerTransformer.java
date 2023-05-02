package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.RequestDto.SellerRequestDto;
import com.example.EasyMarket.Dto.RequestDto.UpdateSellerEmailRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.DeleteResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerByParamResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.UpdateSellerEmailResponseDto;
import com.example.EasyMarket.Entity.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {

    public static Seller SellerRequestDtotoSeller(SellerRequestDto sellerRequestDto)
    {
        return Seller.builder().
                name(sellerRequestDto.getName()).
                email(sellerRequestDto.getEmail()).
                age(sellerRequestDto.getAge()).
                mobNo(sellerRequestDto.getMobNo()).
                build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller)
    {
        return SellerResponseDto.builder().
                name(seller.getName()).
                age(seller.getAge()).
                email(seller.getEmail()).
                mobNo(seller.getMobNo()).
                build();
    }

    public static SellerByParamResponseDto SellerToSellerByParamsResponseDto(Seller seller)
    {
        return SellerByParamResponseDto.builder().
                name(seller.getName()).
                email(seller.getEmail()).
                age(seller.getAge()).
                mobNo(seller.getMobNo()).
                build();
    }

    public static UpdateSellerEmailResponseDto SellerToUpdateSellerEmailResponseDto(Seller seller)
    {
        return UpdateSellerEmailResponseDto.builder().
                message("Email of the Seller "+seller.getName()+" has been updated Successfully to "+seller.getEmail()).
                build();
    }

    public static DeleteResponseDto SellerToDeleteResponseDto(Seller seller){
        return DeleteResponseDto.builder()
                .message("Seller "+seller.getName()+" deleted successfully").
                build();
    }

}
