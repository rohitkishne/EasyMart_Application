package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.SellerRequestDto;
import com.example.EasyMarket.Dto.RequestDto.UpdateSellerEmailRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.DeleteResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerByParamResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.UpdateSellerEmailResponseDto;
import com.example.EasyMarket.Exception.EmailAlreadyExist;
import com.example.EasyMarket.Exception.SellerIsNotFoundException;

import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExist;

    public SellerByParamResponseDto getSellerByEmailId(String email) throws SellerIsNotFoundException;

    public SellerByParamResponseDto getSellerById(int id) throws SellerIsNotFoundException;

   public List<SellerResponseDto> getAllSeller();

    public List<SellerResponseDto> getAllSellerByAge(int age);

    public UpdateSellerEmailResponseDto updateSellerEmailId(UpdateSellerEmailRequestDto updateSellerEmail);

    public DeleteResponseDto deleteSellerByEmail(String email);

    public String deleteAllSellerByAge(int age);
}
