package com.abdo.shop.services;

import java.util.List;

import com.abdo.shop.model.dto.request.EditWorkerRequest;
import com.abdo.shop.model.dto.request.LoginRequest;
import com.abdo.shop.model.dto.request.NewWorkerRequest;
import com.abdo.shop.model.dto.response.LoginResponse;
import com.abdo.shop.model.dto.response.WorkerResponse;

public interface WorkerService {
    WorkerResponse createWorker(NewWorkerRequest newWorkerRequest);

    WorkerResponse editWorker(EditWorkerRequest editWorkerRequest);

    void deleteWorker(Long id);

    WorkerResponse getWorker(Long id);

    List<WorkerResponse> getAllWorkers();

    LoginResponse login(LoginRequest loginRequest);
}
