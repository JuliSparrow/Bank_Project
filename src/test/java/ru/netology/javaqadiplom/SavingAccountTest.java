package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SavingAccountTest {

    @Test
    public void shouldCreateAllBalancesArePositive() {
        Assertions.assertDoesNotThrow(() -> {
            new SavingAccount(2000, 1000, 10000, 5);
        });
    }

    @Test
    public void shouldCreateMinBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, -1000, 10000, 5);
        });
    }

    @Test
    public void shouldCreateRateIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 1000, 10000, -5);
        });
    }

    @Test
    public void shouldCreateMinBalanceIsGreaterThanMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10000, 10000, 1000, 5);
        });
    }

    @Test
    public void shouldCreateInitialBalanceIsGreaterThanMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(20000, 1000, 10000, 5);
        });
    }

    @Test
    public void shouldCreateInitialBalanceIsLessThanMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(200, 1000, 10000, 5);
        });
    }

    @Test
    public void shouldCreateMinBalanceAndMaxBalanceAreEqual() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(10000, 10000, 10000, 5);
        });
    }

    @Test
    public void payingForPurchasesWorthMoreThanZero() {
        SavingAccount account = new SavingAccount(2000, 1000, 10000, 5);
        account.pay(500);
        int expected = 1500;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void payingForPurchasesWorthZero() {
        SavingAccount account = new SavingAccount(2000, 1000, 10000, 5);
        account.pay(0);
        int expected = 2000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void payingForPurchasesWorthLessThanZero() {
        SavingAccount account = new SavingAccount(2000, 1000, 10000, 5);
        account.pay(-500);
        int expected = 2000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void balanceLessThanMinimumAfterPayment() {
        SavingAccount account = new SavingAccount(2000, 1000, 10000, 5);
        account.pay(1500);
        int expected = 2000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.add(3_000);
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.add(30_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddZero() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.add(0);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldAddLessThanZero() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);
        account.add(-100);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldYearChangeRateGreaterThanZero() {
        SavingAccount account = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertEquals(100, account.yearChange());
    }
}
