package lorgar.avrelian.testtaskwebrise.dao;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Entity
@Table(name = "subscription_data")
public class SubscriptionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    @OneToMany(mappedBy = "subscription")
    private Collection<DataValues> data;

    public SubscriptionData() {
    }

    public SubscriptionData(Long id, User user, Subscription subscription, Collection<DataValues> data) {
        this.id = id;
        this.user = user;
        this.subscription = subscription;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Collection<DataValues> getData() {
        return data;
    }

    public void setData(Collection<DataValues> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionData that = (SubscriptionData) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(subscription, that.subscription) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, subscription, data);
    }

    @Override
    public String toString() {
        return "SubscriptionData{" +
                "id=" + id +
                ", user=" + user +
                ", subscription=" + subscription +
                ", data=" + data +
                '}';
    }
}
