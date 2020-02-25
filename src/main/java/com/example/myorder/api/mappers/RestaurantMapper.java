package com.example.myorder.api.mappers;

import org.modelmapper.ModelMapper;

import com.example.myorder.api.dtos.RestaurantResponseDto;
import com.example.myorder.entities.Restaurant;

public class RestaurantMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static RestaurantResponseDto toResponseDto(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantResponseDto.class);
    }
}
