package com.tma.apa.training.device.mgmt.rest.vo;

public enum Status {

    Pass("Pass"),
    Fail("Fail");

    private final String m_status;

    Status(String status) {
        this.m_status = status;
    }


    public String getStatus() {
        return m_status;
    }
}
