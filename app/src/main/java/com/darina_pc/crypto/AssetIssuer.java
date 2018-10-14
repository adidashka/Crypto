package com.darina_pc.crypto;

class AssetIssuer {
    private String name;
    private String domain;
    private String address;

    public AssetIssuer(String name, String domain, String address) {
        this.name = name;
        this.domain = domain;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
