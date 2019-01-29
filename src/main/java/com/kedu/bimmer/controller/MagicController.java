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
        long p1 = System.currentTimeMillis();
//        String info = magicService.getInfo();
        FutureTask<String> infoTask = new FutureTask<>(() -> magicService.getInfo());
        long p2 = System.currentTimeMillis();
//        String data = magicService.getData();
        FutureTask<String> dataTask = new FutureTask<>(() -> magicService.getData());
        long p3 = System.currentTimeMillis();
        executorService.submit(infoTask);
        executorService.submit(dataTask);
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
}
