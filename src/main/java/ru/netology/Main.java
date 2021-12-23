package ru.netology;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS ");
    private static final int COUNT_CALLS = 60;
    private static final int COUNT_EMPLOYEE = 10;



    public static void main(String[] args) throws InterruptedException {

        ATS ats = new ATS();

        Thread atsThread = new Thread(null, () -> {
            for(int i=1;i<= COUNT_CALLS; i++){
                String name = "Звонок " + i;
                if (ats.addCall(name)) myLog(name + " - входящий звонок в очереди ожидания ответа специалиста...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    myLog("InterruptedException while ATS running: " + e.getMessage());
                }
            }
        }, "АТС");

        //ExecutorService executorService = Executors.newFixedThreadPool(COUNT_EMPLOYEE);


        ThreadGroup employeeGroup = new ThreadGroup("Employee");
        for(int i=1;i<= COUNT_EMPLOYEE; i++){
            new Thread(employeeGroup, new Employee(ats), "Специалист_" + i).start();
            //executorService.submit(new Employee(ats));
        }
        atsThread.start();

        atsThread.join();
        ats.setWorking(false);
        //employeeGroup.interrupt();
        //executorService.shutdown();
    }

    public static void myLog(String s){
        System.out.println(simpleDateFormat.format(new Date()) + s);
    }
}
