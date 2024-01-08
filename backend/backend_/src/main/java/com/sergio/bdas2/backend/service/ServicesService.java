package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.ServicesDto;
import com.sergio.bdas2.backend.repository.ServicesDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {

    private final ServicesDao servicesDao;
    private static final Logger logger = LoggerFactory.getLogger(SectionsService.class);

    public List<ServicesDto> getAllServices() { return servicesDao.getAllServices();
    }


    public ServicesDto getServiceById(Long serviceId) {
        return servicesDao.getServiceById(serviceId);
    }

    public void addService(ServicesDto service) {
        servicesDao.addService(service);
    }

    public void updateService(Integer id, ServicesDto service) {
        servicesDao.updateService(id, service);
    }

    public void deleteService(Integer serviceId) {
        servicesDao.deleteService(serviceId);
    }
}
