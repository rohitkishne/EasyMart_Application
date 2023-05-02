package com.example.EasyMarket.Exception;

public class CartIsEmptyException extends Exception{
    public CartIsEmptyException(String message)
    {
        super(message);
    }
}
