package com.kedu.bimmer.controller;

import com.kedu.bimmer.service.MagicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.*;

/**
 * @author Jef
 */
@Controller
public class MagicController {

    @Autowired
    private MagicService magicService;
    @Autowired
    private ExecutorService executorService;

    @RequestMapping("/magic/test")
    public String test(Model model) {
//        String info = magicService.getInfo();
        FutureTask<String> infoTask = new FutureTask<>(() -> magicService.getInfo());
//        String data = magicService.getData();
        FutureTask<String> dataTask = new FutureTask<>(() -> magicService.getData());
        long p1 = System.currentTimeMillis();
        executorService.submit(infoTask);
        long p2 = System.currentTimeMillis();
        executorService.submit(dataTask);
        long p3 = System.currentTimeMillis();
        try {
            model.addAttribute("info", infoTask.get());
            model.addAttribute("data", dataTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        long p4 = System.currentTimeMillis();
        System.out.println("p1 ~ p2: " + (p2 - p1));
        System.out.println("p2 ~ p3: " + (p3 - p2));
        System.out.println("p3 ~ p4: " + (p4 - p3));
        System.out.println("总共: " + (p4 - p1));
        return "test";
    }

    @RequestMapping("/magic/modify")
    public String modify(Model model) {
        long t1 = System.currentTimeMillis();
        Runnable runInfo = () -> magicService.modifyInfo();
//        magicService.modifyInfo();
        long t2 = System.currentTimeMillis();
        FutureTask<String> dataTask = new FutureTask<>(() -> magicService.getData());
//        Runnable runData = () -> magicService.modifyData();
        long t3 = System.currentTimeMillis();
        executorService.submit(runInfo);
        executorService.submit(dataTask);
        long t4 = System.currentTimeMillis();
        System.out.println("t1 - t2: " + (t2 - t1));
        System.out.println("t2 - t3: " + (t3 - t2));
        System.out.println("t3 - t4: " + (t4 - t3));
        System.out.println("Total: " + (t4 - t1));

        try {
            model.addAttribute("info", "info - x,y,z");
            model.addAttribute("data", "data - i,j,k - " + dataTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "test";
    }
}
