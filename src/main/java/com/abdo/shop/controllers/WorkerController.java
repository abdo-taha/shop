package com.abdo.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdo.shop.model.dto.request.EditWorkerRequest;
import com.abdo.shop.model.dto.request.NewWorkerRequest;
import com.abdo.shop.model.dto.response.WorkerResponse;
import com.abdo.shop.services.WorkerService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/worker")
@RequiredArgsConstructor
public class WorkerController {

    final private WorkerService workerService;

    @PostMapping("")
    public ResponseEntity<WorkerResponse> createWorker(@RequestBody NewWorkerRequest newWorkerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(workerService.createWorker(newWorkerRequest));
    }

    @PutMapping("")
    public ResponseEntity<WorkerResponse> editWorker(@RequestBody EditWorkerRequest editWorkerRequest) {
        return ResponseEntity.ok(workerService.editWorker(editWorkerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkerResponse> getWorker(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(workerService.getWorker(id));
    }

    @GetMapping("")
    public ResponseEntity<List<WorkerResponse>> getWorkers() {
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

}
