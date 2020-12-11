package ru.vlapin.courses.spring.cloud;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDAO {
  private static Lock balanceLock = new ReentrantLock();

  AccountRepository repo;

  public void create(Integer clientId) {
    AccountEntity account = new AccountEntity();
    account.setClientId(clientId);
    account.setBalance(BigDecimal.ZERO);
    repo.save(account);
  }

  public boolean addBalance(Integer id, BigDecimal balance) {
    balanceLock.lock();
    try {
      AccountEntity account = repo.findById(id).orElse(null);
      if (account != null) {
        account.setBalance(account.getBalance().add(balance));
        if (account.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
          repo.save(account);
          return true;
        }
      }
    } finally {
      balanceLock.unlock();
    }
    return false;
  }

  public static void main(String[] args) {

  }

  @SneakyThrows
  @Contract(pure = true)
  public final @NotNull String m(@NotNull InputStream inputStream) {
    try (inputStream) {

      Arrays.stream("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                        .split(" "))
          .collect(Collectors.joining())

    }
    return "";
  }


}
