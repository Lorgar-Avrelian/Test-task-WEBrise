package lorgar.avrelian.testtaskwebrise.mapper;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.NewUserDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.dto.UserDTO;
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
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "newUserDTO.login")
    @Mapping(target = "name", source = "newUserDTO.name")
    @Mapping(target = "surname", source = "newUserDTO.surname")
    @Mapping(target = "subscriptionData", ignore = true)
    User newUserDtoToUser(NewUserDTO newUserDTO);

    @Mapping(target = "id", source = "userNoSubscriptions.id")
    @Mapping(target = "login", source = "userNoSubscriptions.login")
    @Mapping(target = "name", source = "userNoSubscriptions.name")
    @Mapping(target = "surname", source = "userNoSubscriptions.surname")
    @Mapping(target = "subscriptionData", ignore = true)
    User userNoSubscriptionsToUser(UserNoSubscriptions userNoSubscriptions);

    default UserDTO userToUserDTO(User user, Collection<Subscription> subscriptions) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        if (subscriptions == null || subscriptions.isEmpty()) {
            userDTO.setSubscriptions(new ArrayList<>());
        } else {
            Collection<SubscriptionNoUsers> subscriptionNoUsers = new ArrayList<>();
            subscriptions.forEach(subscription -> {
                SubscriptionNoUsers subscriptionNoUser = new SubscriptionNoUsers();
                subscriptionNoUser.setId(subscription.getId());
                subscriptionNoUser.setTitle(subscription.getTitle());
                subscriptionNoUser.setDescription(subscription.getDescription());
                subscriptionNoUser.setTariff(subscription.getTariff());
                subscriptionNoUsers.add(subscriptionNoUser);
            });
            userDTO.setSubscriptions(subscriptionNoUsers);
        }
        return userDTO;
    }

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "login", source = "user.login")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "surname", source = "user.surname")
    UserNoSubscriptions userToUserNoSubscriptions(User user);
}
