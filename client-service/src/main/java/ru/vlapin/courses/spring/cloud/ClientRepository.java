package ru.vlapin.courses.spring.cloud;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlapin.courses.spring.cloud.model.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
