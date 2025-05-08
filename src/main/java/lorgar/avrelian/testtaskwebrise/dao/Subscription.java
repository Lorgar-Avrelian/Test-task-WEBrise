package lorgar.avrelian.testtaskwebrise.dao;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "tariff", nullable = false, length = 30)
    private String tariff;
    @OneToMany(mappedBy = "subscription")
    private Collection<SubscriptionData> subscriptionData;

    public Subscription() {
    }

    public Subscription(Long id, String title, String description, String tariff, Collection<SubscriptionData> subscriptionData) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tariff = tariff;
        this.subscriptionData = subscriptionData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public Collection<SubscriptionData> getSubscriptionData() {
        return subscriptionData;
    }

    public void setSubscriptionData(Collection<SubscriptionData> subscriptionData) {
        this.subscriptionData = subscriptionData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(tariff, that.tariff) && Objects.equals(subscriptionData, that.subscriptionData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tariff, subscriptionData);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tariff='" + tariff + '\'' +
                ", subscriptionData=" + subscriptionData +
                '}';
    }
}
