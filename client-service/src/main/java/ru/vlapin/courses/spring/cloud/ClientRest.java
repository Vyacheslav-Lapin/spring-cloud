package ru.vlapin.courses.spring.cloud;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import io.vavr.Function0;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vlapin.courses.spring.cloud.model.ClientEntity;

@RestController
@RequiredArgsConstructor
@RequestMapping("client")
public class ClientRest {

  ClientDao clientDao;

  @NotNull
  @PostMapping("{name}")
  public ClientEntity getByName(@PathVariable @NotNull String name) {
    return clientDao.create(name);
  }

  @NotNull
  @PutMapping("{id}")
  public ResponseEntity<Void> getById(@PathVariable @NotNull Integer id,
                                      @RequestParam @NotNull String name) {
    return (clientDao.update(id, name)
                ? noContent()
                : notFound()
    ).build();
  }

  @NotNull
  @SneakyThrows
  @Contract(pure = true)
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull Integer id) {
    clientDao.deleteById(id);
    return noContent().build();
  }

  @NotNull
  @GetMapping
  public Iterable<ClientEntity> all() {
    return clientDao.findAll();
  }

  @NotNull
  @GetMapping("{id}")
  public ResponseEntity<ClientEntity> getById(@PathVariable @NotNull Integer id) {
    return clientDao.findById(id)
               .map(ResponseEntity::ok)
               .orElseGet(() -> notFound().build());
  }
}
