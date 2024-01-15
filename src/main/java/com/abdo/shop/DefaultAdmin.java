package com.abdo.shop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.abdo.shop.model.dto.request.NewWorkerRequest;
import com.abdo.shop.services.WorkerService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultAdmin implements ApplicationRunner {
    final private WorkerService workerService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (workerService.getAllWorkers().size() == 0)
            workerService
                    .createWorker(NewWorkerRequest.builder().admin(true).name("admin").password("12345678").build());
    }

}
