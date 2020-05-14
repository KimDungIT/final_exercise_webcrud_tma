package com.tma.apa.training.device.mgmt.service;

import com.tma.apa.training.device.mgmt.exception.ConsumerException;

public interface ConsumerService {

    void pollMessage() throws ConsumerException;
}
