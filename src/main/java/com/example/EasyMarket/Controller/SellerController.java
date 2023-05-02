package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.SellerRequestDto;
import com.example.EasyMarket.Dto.RequestDto.UpdateSellerEmailRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.DeleteResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerByParamResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.SellerResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.UpdateSellerEmailResponseDto;
import com.example.EasyMarket.Exception.SellerIsNotFoundException;
import com.example.EasyMarket.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/addSeller")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto)
    {
        try{
            SellerResponseDto sellerResponse = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getSellerByEmailId")
    public SellerByParamResponseDto getSellerByEmailId(@RequestParam String email) throws SellerIsNotFoundException {
        return sellerService.getSellerByEmailId(email);
    }

    @GetMapping("/getSellerById")
    public SellerByParamResponseDto getSellerById(@RequestParam int id) throws SellerIsNotFoundException {
        return sellerService.getSellerById(id);
    }

    @GetMapping("/getAllSeller")
    public List<SellerResponseDto> getAllSeller(){
        return sellerService.getAllSeller();
    }

    @GetMapping("/getAllSellerByAge")
    public List<SellerResponseDto> getAllSellerByAge(@RequestParam int age){
        return sellerService.getAllSellerByAge(age);
    }

    @PutMapping("UpdateSellerEmailId")
    public UpdateSellerEmailResponseDto updateSellerEmailId(@RequestBody UpdateSellerEmailRequestDto updateSellerEmail)
    {
        return sellerService.updateSellerEmailId(updateSellerEmail);
    }

    @DeleteMapping("/SellerDeleteByEmail")
    public DeleteResponseDto deleteSellerByEmail(@RequestParam String email){
        return sellerService.deleteSellerByEmail(email);
    }

    @DeleteMapping("/AllSellerDeleteByAge")
    public String deleteAllSellerByAge(@RequestParam int age){
        return sellerService.deleteAllSellerByAge(age);
    }

}
