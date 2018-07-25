package ru.shop.service.mapper.order;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Status;
import ru.shop.service.dto.order.StatusDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class StatusMapper extends AbstractMapper<Status, StatusDTO> {

    public StatusMapper() {
        super(Status.class, StatusDTO.class);
    }

}
