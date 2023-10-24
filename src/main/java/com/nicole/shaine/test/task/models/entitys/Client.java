package com.nicole.shaine.test.task.models.entitys;

import com.nicole.shaine.test.task.models.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @GenericGenerator(
        name = "client_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "client_sequence"),
            @Parameter(name = "initial_value", value = "10"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Column(name = "client_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender = Gender.UNKNOWN;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Email> emails = Collections.emptySet();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Phone> phones = Collections.emptySet();

    @PrePersist
    public void prePersist() {
        if (gender == null) {
            gender = Gender.UNKNOWN;
        }
        phones.forEach(phone -> phone.setClient(this));
        emails.forEach(email -> email.setClient(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client that = (Client) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
