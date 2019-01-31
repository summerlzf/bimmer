package com.kedu.bimmer.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Jef
 */
@Service
public class MagicService {

    public String getInfo() {
        try {
            int t = new Random().nextInt(2000);
            Thread.sleep(t);
            System.out.println("getInfo: " + t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "info";
    }

    public String getData() {
        try {
            int t = new Random().nextInt(2000);
            Thread.sleep(t);
            System.out.println("getData: " + t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data";
    }

    public void modifyInfo() {
        try {
            int t = new Random().nextInt(3000);
            Thread.sleep(t);
            System.out.println("modifyInfo: " + t + "   --- " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void modifyData() {
        try {
            int t = new Random().nextInt(3000);
            Thread.sleep(t);
            System.out.println("modifyData: " + t + "   --- " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
