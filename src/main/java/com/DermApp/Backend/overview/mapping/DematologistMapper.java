package com.DermApp.Backend.overview.mapping;

import com.DermApp.Backend.overview.domain.model.entity.Dermatologist;
import com.DermApp.Backend.overview.resource.CreateDermatologistResource;
import com.DermApp.Backend.overview.resource.DermatologistResource;
import com.DermApp.Backend.overview.resource.UpdateDermatologistResource;
import com.DermApp.Backend.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DematologistMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public DermatologistResource toResource(Dermatologist model){
        return mapper.map(model, DermatologistResource.class);
    }

    public Dermatologist toModel(CreateDermatologistResource resource){
        return mapper.map(resource, Dermatologist.class);
    }

    public Dermatologist toModel(UpdateDermatologistResource resource){
        return mapper.map(resource, Dermatologist.class);
    }

    public Page<DermatologistResource> modelListPage(List<Dermatologist> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, DermatologistResource.class), pageable, modelList.size());
    }

}
