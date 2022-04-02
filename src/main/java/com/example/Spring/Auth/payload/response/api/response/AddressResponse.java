package com.example.Spring.Auth.payload.response.api.response;

public class AddressResponse {

    private AddressInfo addressInfo;

    public AddressResponse() {}

    public AddressResponse(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }
}
