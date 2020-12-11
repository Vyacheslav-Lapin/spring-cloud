package ru.vlapin.courses.spring.cloud;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.NonFinal;

@Data
@Entity
public class AccountEntity implements Account {

  @Id
  @NonFinal
  @GeneratedValue
  Integer id;

  @NonFinal
  Integer clientId;

  @NonFinal
  BigDecimal balance;
}
