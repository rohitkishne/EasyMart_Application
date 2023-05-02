package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.SellerRequestDto;
import com.example.EasyMarket.Dto.RequestDto.UpdateSellerEmailRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.DeleteResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerByParamResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.UpdateSellerEmailResponseDto;
import com.example.EasyMarket.Entity.Seller;
import com.example.EasyMarket.Exception.EmailAlreadyExist;
import com.example.EasyMarket.Exception.SellerIsNotFoundException;
import com.example.EasyMarket.Repository.SellerRepository;
import com.example.EasyMarket.Service.SellerService;
import com.example.EasyMarket.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlreadyExist {
        //conversion from request dto to entity
        //first way --> create an object with new keyword & getter, setter

//        Seller seller = new Seller();
//        seller.setName(sellerRequestDto.getName());
//        seller.setEmail(sellerRequestDto.getEmail());
//        seller.setAge(sellerRequestDto.getAge());
//        seller.setMobNo(sellerRequestDto.getMobNo());

        //Second way --> using a Builder
//        Seller seller = Seller.builder().
//                name(sellerRequestDto.getName()).
//                email(sellerRequestDto.getEmail()).
//                age(sellerRequestDto.getAge()).
//                mobNo(sellerRequestDto.getMobNo()).
//                build();

        //More Clear way is to create Transformer/Converter Package
        //Do every conversion in class in Transformer Package
        //Benefit --> Make Clearer code

        if(sellerRepository.findByEmail(sellerRequestDto.getEmail())!=null)
        {
            throw new EmailAlreadyExist("User with Same Email is already Present");
        }

        //convert a sellerRequestDto to seller entity
        Seller seller = SellerTransformer.SellerRequestDtotoSeller(sellerRequestDto);

        //save the entity to database
        Seller addedSeller = sellerRepository.save(seller);

        //prepare a response
        SellerResponseDto sellerResponse = SellerTransformer.SellerToSellerResponseDto(addedSeller);

        return sellerResponse;
    }

    @Override
    public SellerByParamResponseDto getSellerByEmailId(String email) throws SellerIsNotFoundException {
        try{
             Seller seller = sellerRepository.findByEmail(email);

            SellerByParamResponseDto sellerByParam = SellerTransformer.SellerToSellerByParamsResponseDto(seller);

            return sellerByParam;

        }
        catch(Exception e)
        {
            throw new SellerIsNotFoundException("Seller is not found with the email address");
        }
    }

    @Override
    public SellerByParamResponseDto getSellerById(int id) throws SellerIsNotFoundException {
        try{
            Seller seller = sellerRepository.findById(id).get();

            SellerByParamResponseDto sellerByParam = SellerTransformer.SellerToSellerByParamsResponseDto(seller);

            return sellerByParam;

        }
        catch(Exception e)
        {
            throw new SellerIsNotFoundException("Seller is not found with a given id");
        }
    }

    @Override
    public List<SellerResponseDto> getAllSeller() {

        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> allSellers = new ArrayList<>();

        for(Seller seller : sellers)
        {
            SellerResponseDto sellerResponse = SellerTransformer.SellerToSellerResponseDto(seller);
            allSellers.add(sellerResponse);
        }
        return allSellers;
    }

    @Override
    public List<SellerResponseDto> getAllSellerByAge(int age) {

        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> allSellers = new ArrayList<>();

        for(Seller seller : sellers)
        {
            int sellerAge = seller.getAge();
            if(sellerAge == age)
            {
                SellerResponseDto sellerResponse = SellerTransformer.SellerToSellerResponseDto(seller);
                allSellers.add(sellerResponse);
            }

        }
        return allSellers;
    }

    @Override
    public UpdateSellerEmailResponseDto updateSellerEmailId(UpdateSellerEmailRequestDto updateSellerEmail) {

        Seller seller = sellerRepository.findById(updateSellerEmail.getId()).get();
        seller.setEmail(updateSellerEmail.getEmail());
        Seller updatedSeller = sellerRepository.save(seller);

        UpdateSellerEmailResponseDto updateEmail = SellerTransformer.SellerToUpdateSellerEmailResponseDto(updatedSeller);

        return updateEmail;
    }

    @Override
    public DeleteResponseDto deleteSellerByEmail(String email) {

        Seller seller = sellerRepository.findByEmail(email);
        DeleteResponseDto deleteSeller = SellerTransformer.SellerToDeleteResponseDto(seller);
        sellerRepository.delete(seller);
        return deleteSeller;
    }

    @Override
    public String deleteAllSellerByAge(int age) {

        List<Seller> sellers= sellerRepository.findAll();

        for(Seller seller : sellers)
        {
            int sellerAge = seller.getAge();
            if(sellerAge == age)
            {
                sellerRepository.delete(seller);
            }
        }

        return "All Seller of Age "+age+" deleted Successfully";
    }


}
