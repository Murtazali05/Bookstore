package ru.shop.core.service.mapper.order;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Status;
import ru.shop.core.service.dto.order.StatusDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class StatusMapper extends AbstractMapper<Status, StatusDTO> {

    public StatusMapper() {
        super(Status.class, StatusDTO.class);
    }

}
