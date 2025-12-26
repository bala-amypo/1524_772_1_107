package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Parcel;
import com.example.demo.service.ParcelService;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }

    @PostMapping
    public Parcel add(@RequestBody Parcel parcel) {
        return service.addParcel(parcel);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public Parcel get(@PathVariable String trackingNumber) {
        return service.getByTrackingNumber(trackingNumber);
    }
}

