package com.abdo.shop.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abdo.shop.exceptions.NotFoundException;
import com.abdo.shop.mappers.WorkerMapper;
import com.abdo.shop.model.dto.request.EditWorkerRequest;
import com.abdo.shop.model.dto.request.LoginRequest;
import com.abdo.shop.model.dto.request.NewWorkerRequest;
import com.abdo.shop.model.dto.response.LoginResponse;
import com.abdo.shop.model.dto.response.WorkerResponse;
import com.abdo.shop.model.entity.Role;
import com.abdo.shop.model.entity.WorkerEntity;
import com.abdo.shop.repositories.WorkerRepository;
import com.abdo.shop.services.JwtService;
import com.abdo.shop.services.WorkerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

    final private WorkerRepository workerRepository;
    final private WorkerMapper workerMapper;
    final private JwtService jwtService;
    final private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public WorkerResponse createWorker(NewWorkerRequest newWorkerRequest) {
        WorkerEntity worker = WorkerEntity.builder()
                .name(newWorkerRequest.name())
                .password(
                        passwordEncoder.encode(newWorkerRequest.password()))
                .roles(newWorkerRequest.admin() ? Role.ADMIN : Role.WORKER)
                .build();
        WorkerEntity savedWorker = workerRepository.save(worker);
        return workerMapper.WorkerEntityToWorkerResponse(savedWorker);
    }

    @Override
    public WorkerResponse editWorker(EditWorkerRequest editWorkerRequest) {
        WorkerEntity worker = workerRepository.findById(editWorkerRequest.id())
                .orElseThrow(() -> new NotFoundException());
        worker.setName(editWorkerRequest.name());
        if (editWorkerRequest.password() != null)
            worker.setPassword(passwordEncoder.encode(editWorkerRequest.password()));
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

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        WorkerEntity workerEntity = workerRepository.findByName(loginRequest.name())
                .orElseThrow(() -> new NotFoundException());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.name(), loginRequest.Password()));

        LoginResponse loginResponse = LoginResponse.builder()
                .userName(workerEntity.getUsername())
                .isAdmin(workerEntity.getRoles().equals(Role.ADMIN))
                .jwt(jwtService.generateToken(workerEntity.getUsername(),
                        workerEntity.getRoles().equals(Role.ADMIN)))
                .build();
        return loginResponse;

    }

}
