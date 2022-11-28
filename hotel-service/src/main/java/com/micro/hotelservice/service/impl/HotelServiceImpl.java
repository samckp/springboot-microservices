package com.micro.hotelservice.service.impl;

import com.micro.hotelservice.entities.Hotel;
import com.micro.hotelservice.exception.ResourceNotFoundException;
import com.micro.hotelservice.repositories.HotelRepository;
import com.micro.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(
                ()-> new ResourceNotFoundException("Hotel with given Id not found " + hotelId));

    }
}
