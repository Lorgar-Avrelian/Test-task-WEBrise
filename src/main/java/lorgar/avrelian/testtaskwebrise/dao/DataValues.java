package lorgar.avrelian.testtaskwebrise.dao;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Victor Tokovenko
 */
@Entity
@Table(name = "data_values")
public class DataValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private SubscriptionData subscription;
    @Column(name = "key", nullable = false)
    private String key;
    @Column(name = "value")
    private String value;

    public DataValues() {
    }

    public DataValues(Long id, SubscriptionData subscription, String key, String value) {
        this.id = id;
        this.subscription = subscription;
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubscriptionData getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionData subscription) {
        this.subscription = subscription;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DataValues that = (DataValues) o;
        return Objects.equals(id, that.id) && Objects.equals(subscription, that.subscription) && Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscription, key, value);
    }

    @Override
    public String toString() {
        return "DataValues{" +
                "id=" + id +
                ", subscription=" + subscription +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
