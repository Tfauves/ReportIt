package com.example.Spring.Auth.payload.response.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressInfo {

    private class Data {

        private String city;
        private String state;
        private String state_fullname;

        public Data() {}

        public Data(String city, String state, String state_fullname) {
            this.city = city;
            this.state = state;
            this.state_fullname = state_fullname;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState_fullname() {
            return state_fullname;
        }

        public void setState_fullname(String state_fullname) {
            this.state_fullname = state_fullname;
        }
    }

    private Data data;

    public AddressInfo() {}

    public AddressInfo(Data data) {
        this.data = data;

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
