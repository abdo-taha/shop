package com.abdo.shop.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.mappers.WorkerMapper;
import com.abdo.shop.model.dto.request.EditWorkerRequest;
import com.abdo.shop.model.dto.request.NewWorkerRequest;
import com.abdo.shop.model.dto.response.WorkerResponse;
import com.abdo.shop.model.entity.WorkerEntity;
import com.abdo.shop.repositories.WorkerRepository;
import com.abdo.shop.services.WorkerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

    final private WorkerRepository workerRepository;
    final private WorkerMapper workerMapper;

    @Override
    public WorkerResponse createWorker(NewWorkerRequest newWorkerRequest) {
        // TODO encrypt
        WorkerEntity worker = WorkerEntity.builder()
                .name(newWorkerRequest.name())
                .password(newWorkerRequest.password())
                .build();
        WorkerEntity savedWorker = workerRepository.save(worker);
        return workerMapper.WorkerEntityToWorkerResponse(savedWorker);
    }

    @Override
    public WorkerResponse editWorker(EditWorkerRequest editWorkerRequest) {
        WorkerEntity worker = workerRepository.findById(editWorkerRequest.id())
                .orElseThrow(() -> new NotFoundException());
        worker.setName(editWorkerRequest.name());
        // TODO encrypt
        System.out.println(editWorkerRequest.password());
        if (editWorkerRequest.password() != null)
            worker.setPassword(editWorkerRequest.password());
        WorkerEntity savedWorker = workerRepository.save(worker);
        return workerMapper.WorkerEntityToWorkerResponse(savedWorker);
    }

    @Override
    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);

    }

    @Override
    public List<WorkerResponse> getAllWorkers() {
        List<WorkerEntity> workers = workerRepository.findAll();
        return workers.stream().map(workerMapper::WorkerEntityToWorkerResponse).collect(Collectors.toList());
    }

    @Override
    public WorkerResponse getWorker(Long id) {

        WorkerEntity workerEntity = workerRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return workerMapper.WorkerEntityToWorkerResponse(workerEntity);
    }

}
