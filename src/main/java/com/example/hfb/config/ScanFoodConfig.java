package com.example.hfb.config;

import com.example.hfb.entity.Food;
import com.example.hfb.entity.Request;
import com.example.hfb.repository.FoodRepository;
import com.example.hfb.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.List;

@Configuration
@EnableScheduling
public class ScanFoodConfig {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Scheduled(fixedDelay = 1000*60*60)
    public void scheduleFixedDelayTask() throws InterruptedException {
        List<Food> foods = foodRepository.findAll();
        for (Food item : foods) {
            Long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime > item.getExpirationDate()) {
                item.setStatus(3);
                foodRepository.save(item);
                List<Request> requests = requestRepository.findByFood(item);
                for (Request r : requests) {
                    if (r.getStatus() != 3) {
                        r.setStatus(4);
                        requestRepository.save(r);
                    }

                }
            }
        }
    }
}
