package com.example.gururu_be.domain.repository.store.beautician;


import com.example.gururu_be.domain.dto.store.BeauticianDto;

import java.util.List;
import java.util.UUID;

public interface BeauticianRepositoryCustom {
    List<BeauticianDto> findAllBeauticianBystoreRegisterId_DSL(UUID storeRegisterId);
}
