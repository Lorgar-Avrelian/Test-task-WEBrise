package lorgar.avrelian.testtaskwebrise.mapper;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.dto.UserNoSubscriptions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.Collection;

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

    @Mapping(target = "id", source = "newSubscriptionDTO.id")
    @Mapping(target = "title", source = "newSubscriptionDTO.title")
    @Mapping(target = "description", source = "newSubscriptionDTO.description")
    @Mapping(target = "tariff", source = "newSubscriptionDTO.tariff")
    @Mapping(target = "subscriptionData", ignore = true)
    Subscription subscriptionDtoToSubscription(SubscriptionDTO subscriptionDTO);

    default SubscriptionDTO subscriptionToSubscriptionDTO(Subscription subscription, Collection<User> users) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setId(subscription.getId());
        subscriptionDTO.setTitle(subscription.getTitle());
        subscriptionDTO.setDescription(subscription.getDescription());
        subscriptionDTO.setTariff(subscription.getTariff());
        if (users == null || users.isEmpty()) {
            subscriptionDTO.setUsers(new ArrayList<>());
        } else {
            Collection<UserNoSubscriptions> userNoSubscriptions = new ArrayList<>();
            for (User user : users) {
                UserNoSubscriptions userNoSubscription = new UserNoSubscriptions();
                userNoSubscription.setId(user.getId());
                userNoSubscription.setLogin(user.getLogin());
                userNoSubscription.setName(user.getName());
                userNoSubscription.setSurname(user.getSurname());
                userNoSubscriptions.add(userNoSubscription);
            }
            subscriptionDTO.setUsers(userNoSubscriptions);
        }
        return subscriptionDTO;
    }
    @Mapping(target = "id", source = "subscription.id")
    @Mapping(target = "title", source = "subscription.title")
    @Mapping(target = "description", source = "subscription.description")
    @Mapping(target = "tariff", source = "subscription.tariff")
    SubscriptionNoUsers subscriptionToSubscriptionNoUsers(Subscription subscription);
}
