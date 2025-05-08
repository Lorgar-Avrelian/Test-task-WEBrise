package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.DataValues;
import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.SubscriptionData;
import lorgar.avrelian.testtaskwebrise.dao.User;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.repository.DataValuesRepository;
import lorgar.avrelian.testtaskwebrise.repository.SubscriptionsDataRepository;
import lorgar.avrelian.testtaskwebrise.repository.SubscriptionsRepository;
import lorgar.avrelian.testtaskwebrise.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Victor Tokovenko
 */
@Service
@Transactional
public class ManageServiceImpl implements ManageService {
    private final Logger log = LoggerFactory.getLogger(ManageServiceImpl.class);
    private final SubscriptionsDataRepository subscriptionsDataRepository;
    private final SubscriptionsRepository subscriptionsRepository;
    private final UsersRepository usersRepository;
    private final DataValuesRepository dataValuesRepository;

    public ManageServiceImpl(
            SubscriptionsDataRepository subscriptionsDataRepository,
            SubscriptionsRepository subscriptionsRepository,
            UsersRepository usersRepository,
            DataValuesRepository dataValuesRepository
    ) {
        this.subscriptionsDataRepository = subscriptionsDataRepository;
        this.subscriptionsRepository = subscriptionsRepository;
        this.usersRepository = usersRepository;
        this.dataValuesRepository = dataValuesRepository;
    }

    @Override
    public Collection<Subscription> getUserSubscriptions(User user) {
        List<SubscriptionData> subscriptionsData;
        try {
            subscriptionsData = subscriptionsDataRepository.findAllByUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        Collection<Subscription> subscriptions = new ArrayList<>();
        subscriptionsData.forEach(subscriptionData -> {
            subscriptions.add(subscriptionData.getSubscription());
        });
        return subscriptions;
    }

    @Override
    public void deleteUser(User user) {
        for (SubscriptionData subscriptionData : subscriptionsDataRepository.findAllByUser(user)) {
            try {
                Collection<DataValues> data = subscriptionData.getData();
                dataValuesRepository.deleteAll(data);
                subscriptionsDataRepository.delete(subscriptionData);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void deleteSubscription(Subscription subscription) {
        for (SubscriptionData subscriptionData : subscriptionsDataRepository.findAllBySubscription(subscription)) {
            try {
                Collection<DataValues> data = subscriptionData.getData();
                dataValuesRepository.deleteAll(data);
                subscriptionsDataRepository.delete(subscriptionData);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean createUserSubscription(User user, SubscriptionNoUsers subscription) {
        Subscription currentSubscription = subscriptionsRepository.findSubscriptionByIdAndTitleIgnoreCaseAndTariffIgnoreCaseAndDescriptionIgnoreCase(subscription.getId(), subscription.getTitle(), subscription.getTariff(), subscription.getDescription()).orElse(null);
        if (currentSubscription == null || !subscriptionsDataRepository.findAllByUserAndSubscription(user, currentSubscription).isEmpty()) return true;
        SubscriptionData subscriptionData = new SubscriptionData();
        subscriptionData.setSubscription(currentSubscription);
        subscriptionData.setUser(user);
        SubscriptionData saved;
        try {
            saved = subscriptionsDataRepository.save(subscriptionData);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return saved == null;
    }

    @Override
    public boolean deleteUserSubscription(User user, Long id) {
        Subscription subscription = subscriptionsRepository.findById(id).orElse(null);
        if (subscription == null) return true;
        List<SubscriptionData> data = subscriptionsDataRepository.findAllByUserAndSubscription(user, subscription);
        if (data == null || data.isEmpty()) return true;
        data.forEach(subscriptionData -> {
            Collection<DataValues> dataValues = subscriptionData.getData();
            try {
                dataValuesRepository.deleteAll(dataValues);
                subscriptionsDataRepository.delete(subscriptionData);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });
        return false;
    }
}
