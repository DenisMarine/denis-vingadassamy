package com.party.Party.controller;

import com.party.Party.dto.AddressDto;
import com.party.Party.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddresses(@RequestParam(name = "page") int page,
                                                         @RequestParam(name = "page-size") int pageSize) {
        return ResponseEntity.ok(addressService.getAll(page, pageSize));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.getById(addressId));
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.createAddress(addressDto));
    }

    @PostMapping("/{addressId}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long addressId, @RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(addressId, addressDto));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddressById(addressId);
        return ResponseEntity.noContent().build();
    }
}
