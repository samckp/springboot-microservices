package com.micro.hotelservice.service;

import com.micro.hotelservice.HotelApplication;
import com.micro.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getHotels();

    Hotel getHotelById(String hotelId);
}
