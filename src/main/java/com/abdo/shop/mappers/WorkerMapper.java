package com.abdo.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.abdo.shop.model.dto.response.WorkerResponse;
// import com.abdo.shop.model.entity.Role;
import com.abdo.shop.model.entity.WorkerEntity;

@Mapper(componentModel = "spring")
public interface WorkerMapper {

    WorkerMapper INSTANCE = Mappers.getMapper(WorkerMapper.class);

    WorkerResponse WorkerEntityToWorkerResponse(WorkerEntity workerEntity);

    // default Role map(WorkerEntity workerEntity) {
    // return workerEntity.getRole();
    // }
}
