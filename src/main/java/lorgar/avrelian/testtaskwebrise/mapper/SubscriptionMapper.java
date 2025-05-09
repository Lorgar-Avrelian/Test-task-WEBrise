package lorgar.avrelian.testtaskwebrise.mapper;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * @author Victor Tokovenko
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "newSubscriptionDTO.title")
    @Mapping(target = "description", source = "newSubscriptionDTO.description")
    @Mapping(target = "tariff", source = "newSubscriptionDTO.tariff")
    @Mapping(target = "subscriptionData", ignore = true)
    Subscription newSubscriptionDtoToSubscription(NewSubscriptionDTO newSubscriptionDTO);

    @Mapping(target = "id", source = "subscription.id")
    @Mapping(target = "title", source = "subscription.title")
    @Mapping(target = "description", source = "subscription.description")
    @Mapping(target = "tariff", source = "subscription.tariff")
    SubscriptionDTO subscriptionToSubscriptionNoUsers(Subscription subscription);
}
