package com.kedu.bimmer.service;

import org.springframework.stereotype.Service;

/**
 * @author Jef
 */
@Service
public class MagicService {

    public String getInfo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "info";
    }

    public String getData() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data";
    }
}
