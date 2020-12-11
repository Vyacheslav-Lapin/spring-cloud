package ru.vlapin.courses.spring.cloud;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Component;
import ru.vlapin.courses.spring.cloud.model.ClientEntity;

@Component
@RequiredArgsConstructor
public class ClientDao {

  @Delegate
  ClientRepository clientRepository;

  public ClientEntity create(String name) {
    return clientRepository.save(
        new ClientEntity(name));
  }

  public boolean update(Integer id, String name) {
    return
        clientRepository.findById(id).stream()
            .map(clientEntity -> clientEntity.setName(name))
            .peek(clientRepository::save)
            .findAny()
            .isPresent();
  }
}
