package lorgar.avrelian.testtaskwebrise.mapper;

import lorgar.avrelian.testtaskwebrise.dao.DataValues;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Victor Tokovenko
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionDataMapper {
    default Collection<DataValues> subscriptionDataToDataValues(SubscriptionDataDTO subscriptionDataDTO) {
        Collection<DataValues> dataValues = new ArrayList<>();
        subscriptionDataDTO.getData().entrySet().forEach(entry -> {
            DataValues dataValue = new DataValues();
            dataValue.setKey(entry.getKey());
            dataValue.setValue(entry.getValue());
            dataValues.add(dataValue);
        });
        return dataValues;
    }

    default SubscriptionDataDTO subscriptionDataToSubscriptionDataDTO(Collection<DataValues> dataValues) {
        Map<String, String> data = new HashMap<>();
        dataValues.forEach(entry -> {
            data.put(entry.getKey(), entry.getValue());
        });
        return new SubscriptionDataDTO(data);
    }
}
