package lorgar.avrelian.testtaskwebrise.service;

import lorgar.avrelian.testtaskwebrise.dao.Subscription;
import lorgar.avrelian.testtaskwebrise.dao.SubscriptionData;
import lorgar.avrelian.testtaskwebrise.dto.NewSubscriptionDTO;
import lorgar.avrelian.testtaskwebrise.dto.SubscriptionNoUsers;
import lorgar.avrelian.testtaskwebrise.mapper.SubscriptionMapper;
import lorgar.avrelian.testtaskwebrise.repository.SubscriptionsDataRepository;
import lorgar.avrelian.testtaskwebrise.repository.SubscriptionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Victor Tokovenko
 */
@Service
@Transactional
public class SubscriptionsServiceImpl implements SubscriptionsService {
    private final Logger log = LoggerFactory.getLogger(SubscriptionsServiceImpl.class);
    private final SubscriptionsRepository subscriptionsRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final ManageService manageService;
    private final SubscriptionsDataRepository subscriptionsDataRepository;

    public SubscriptionsServiceImpl(
            SubscriptionsRepository subscriptionsRepository,
            SubscriptionMapper subscriptionMapper,
            @Qualifier(value = "manageServiceImpl") ManageService manageService, SubscriptionsDataRepository subscriptionsDataRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.manageService = manageService;
        this.subscriptionsDataRepository = subscriptionsDataRepository;
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
        Subscription optional = subscriptionsRepository.findSubscriptionByTitleIgnoreCaseAndTariffIgnoreCase(subscription.getTitle(), subscription.getTariff()).orElse(null);
        if (optional != null) return null;
        SubscriptionNoUsers subscriptionNoUsers;
        try {
            subscriptionNoUsers = subscriptionMapper.subscriptionToSubscriptionNoUsers(subscriptionsRepository.save(subscriptionMapper.newSubscriptionDtoToSubscription(subscription)));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return subscriptionNoUsers;
    }

    @Override
    public SubscriptionNoUsers readSubscription(Long id) {
        Subscription subscription;
        try {
            subscription = subscriptionsRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (subscription == null) return null;
        return subscriptionMapper.subscriptionToSubscriptionNoUsers(subscription);
    }

    @Override
    public SubscriptionNoUsers putSubscription(Long id, NewSubscriptionDTO subscription) {
        if (readSubscription(id) == null) return null;
        Subscription current = subscriptionsRepository.findById(id).get();
        current.setTitle(subscription.getTitle());
        current.setTariff(subscription.getTariff());
        current.setDescription(subscription.getDescription());
        Subscription saved;
        try {
            saved = subscriptionsRepository.save(current);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return subscriptionMapper.subscriptionToSubscriptionNoUsers(saved);
    }

    @Override
    public SubscriptionNoUsers deleteSubscription(Long id) {
        if (readSubscription(id) == null) return null;
        Subscription current = subscriptionsRepository.findById(id).get();
        manageService.deleteSubscription(current);
        try {
            subscriptionsRepository.delete(current);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return subscriptionMapper.subscriptionToSubscriptionNoUsers(current);
    }

    @Override
    public Collection<SubscriptionNoUsers> readSubscriptionsTop() {
        List<Subscription> subscriptions;
        try {
            subscriptions = subscriptionsRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if (subscriptions == null || subscriptions.isEmpty()) return new ArrayList<>();
        Collection<SubscriptionNoUsers> result = new ArrayList<>();
        if (subscriptions.size() <= 3) {
            for (Subscription subscription : subscriptions) {
                result.add(subscriptionMapper.subscriptionToSubscriptionNoUsers(subscription));
            }
        } else {
            Map<Subscription, Integer> counts = new HashMap<>();
            for (Subscription subscription : subscriptions) {
                List<SubscriptionData> allBySubscription = subscriptionsDataRepository.findAllBySubscription(subscription);
                counts.put(subscription, allBySubscription.size());
            }
            List<Subscription> collect = counts.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(3)
                    .map(Map.Entry::getKey)
                    .toList();
            for (Subscription subscription : collect) {
                result.add(subscriptionMapper.subscriptionToSubscriptionNoUsers(subscription));
            }
        }
        return result;
    }
}
