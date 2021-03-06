package com.kedu.bimmer.controller;

import com.kedu.bimmer.service.MagicService;
import com.kedu.bimmer.threads.RunnableTask;
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

    @RequestMapping("/magic/query")
    public String query(Model model) {
		System.out.println("start...");
		Future<String> f1 = executorService.submit(() -> magicService.getInfo());
		System.out.println("future 1");
		Future<String> f2 = executorService.submit(() -> magicService.getData());
		System.out.println("future 2");
		try {
			model.addAttribute("info", f1.get());
			model.addAttribute("data", f2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("return page" + "   --- " + System.currentTimeMillis());
		return "test";
    }

	@RequestMapping("/magic/update")
	public String update(Model model) {
		System.out.println("start...");
		Future<?> f1 = executorService.submit(() -> magicService.modifyInfo());
		System.out.println("future 1");
		Future<?> f2 = executorService.submit(() -> magicService.modifyData());
		System.out.println("future 2");
		try {
			model.addAttribute("info", "info - 1,2,3");
			model.addAttribute("data", "data - a,b,c");
			f1.get();
			f2.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("return page" + "   --- " + System.currentTimeMillis());
		return "test";
	}

    @RequestMapping("/magic/modify")
    public String modify(Model model) {
        long t1 = System.currentTimeMillis();
//        Runnable runInfo = () -> magicService.modifyInfo();
		RunnableTask task = new RunnableTask(() -> magicService.modifyInfo());
//        magicService.modifyInfo();
        long t2 = System.currentTimeMillis();
//        Runnable runData = () -> magicService.modifyData();
//		RunnableTask task1 = new RunnableTask(() -> magicService.modifyData());
		long t3 = System.currentTimeMillis();
        executorService.submit(task);
//        executorService.submit(task1);
        magicService.modifyData();
        long t4 = System.currentTimeMillis();
        System.out.println("t1 - t2: " + (t2 - t1));
        System.out.println("t2 - t3: " + (t3 - t2));
        System.out.println("t3 - t4: " + (t4 - t3));
        System.out.println("Total: " + (t4 - t1));

        try {
            model.addAttribute("info", "info - x,y,z");
            model.addAttribute("data", "data - i,j,k");
            task.done();
//            task1.done();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("return page" + "   --- " + System.currentTimeMillis());
        return "test";
    }

    @RequestMapping("/magic/name")
    public String name(Model model) {
        Future<?> f1 = executorService.submit(() -> {
            String name = magicService.getName1();
            model.addAttribute("info", name);
        });
        Future<?> f2 = executorService.submit(() -> {
            String name = magicService.getName2();
            model.addAttribute("data", name);
        });
        model.addAttribute("name", magicService.getName());
        try {
            f1.get();
            f2.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "test";
    }
}
