package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.LoginRequest;
import com.abdo.shop.model.dto.request.NewWorkerRequest;
import com.abdo.shop.model.dto.response.LoginResponse;
import com.abdo.shop.model.dto.response.WorkerResponse;
import com.abdo.shop.services.WorkerService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    final private WorkerService workerService;

    /**
     * create worker (admin)
     * delete worker (admin)
     * delete admin (admin can't delete himself)
     * 
     * login (any)
     * logout
     * 
     */

    @PostMapping("/signup")
    public ResponseEntity<WorkerResponse> signUp(@RequestBody NewWorkerRequest newWorkerRequest) {
        return ResponseEntity.ok(workerService.createWorker(newWorkerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workerService.deleteWorker(id);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(workerService.login(loginRequest));

    }

}
