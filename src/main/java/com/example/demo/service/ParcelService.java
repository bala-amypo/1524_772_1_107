package com.example.demo.service;

import com.example.demo.model.Parcel;

import java.util.List;

public interface ParcelService {

    Parcel createParcel(Parcel parcel);

    Parcel getParcelById(Long id);

    List<Parcel> getAllParcels();
}
