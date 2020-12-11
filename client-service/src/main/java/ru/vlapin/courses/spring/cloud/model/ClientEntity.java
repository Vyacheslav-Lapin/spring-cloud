package ru.vlapin.courses.spring.cloud.model;

import static lombok.AccessLevel.PRIVATE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.vlapin.courses.spring.cloud.Client;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
//@Setter(PRIVATE)
public class ClientEntity implements Client {

    @Id
    @GeneratedValue
    Integer id;

    @NonNull
    String name;
}
