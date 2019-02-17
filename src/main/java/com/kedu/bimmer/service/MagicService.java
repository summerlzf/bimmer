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
            System.out.println("getInfo: " + t + "   --- " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "info";
    }

    public String getData() {
        try {
            int t = new Random().nextInt(2000);
            Thread.sleep(t);
            System.out.println("getData: " + t + "   --- " + System.currentTimeMillis());
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

    public String getName() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "name";
    }

    public String getName1() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "name1";
    }

    public String getName2() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "name2";
    }
}
