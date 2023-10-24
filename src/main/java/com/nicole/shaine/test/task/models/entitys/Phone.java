package com.nicole.shaine.test.task.models.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_generator")
    @GenericGenerator(
        name = "phone_generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "phone_sequence"),
            @Parameter(name = "initial_value", value = "100"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    @Column(name = "phone_id", nullable = false)
    private Long id;

    @Column(name = "phone_value", nullable = false, unique = true)
    private String value;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Phone that = (Phone) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}



