package com.pavan.app.repositories;

import com.pavan.app.entities.Account;
import com.pavan.app.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account findByAccountName(String accountName);
}
