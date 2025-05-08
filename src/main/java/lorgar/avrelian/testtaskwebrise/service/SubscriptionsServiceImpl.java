package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.mapper.SubscriptionMapper;
import lorgar.avrelian.testtaskwebrise.repository.SubscriptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Victor Tokovenko
 */
@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {
    private final Logger log = LoggerFactory.getLogger(SubscriptionsServiceImpl.class);
    private final SubscriptionsRepository subscriptionsRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionsServiceImpl(SubscriptionsRepository subscriptionsRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public List<SubscriptionNoUsers> getAll() {
        List<Subscription> subscriptions;
        try {
            subscriptions = subscriptionsRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (subscriptions.isEmpty()) return new ArrayList<>();
        List<SubscriptionNoUsers> result = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            result.add(subscriptionMapper.subscriptionToSubscriptionNoUsers(subscription));
        }
        return result;
    }

    @Override
    public List<SubscriptionNoUsers> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Subscription> subscriptions;
        try {
            subscriptions = subscriptionsRepository.findAll(pageRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (subscriptions.getContent().isEmpty()) return new ArrayList<>();
        List<SubscriptionNoUsers> result = new ArrayList<>();
        for (Subscription subscription : subscriptions.getContent()) {
            result.add(subscriptionMapper.subscriptionToSubscriptionNoUsers(subscription));
        }
        return result;
    }

    @Override
    public SubscriptionNoUsers createSubscription(NewSubscriptionDTO subscription) {
        Optional<Subscription> optional = subscriptionsRepository.findByTitleIgnoreCaseAndTariffIgnoreCase(subscription.getTitle(), subscription.getTariff());
        if (optional.isPresent()) return null;
        SubscriptionNoUsers subscriptionNoUsers;
        try {
            subscriptionNoUsers = subscriptionMapper.subscriptionToSubscriptionNoUsers(subscriptionsRepository.save(subscriptionMapper.newSubscriptionDtoToSubscription(subscription)));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return subscriptionNoUsers;
    }
}
