package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldCreateCreditAccountIfInitialBalancePositive() {
        Assertions.assertDoesNotThrow(() -> {
            new CreditAccount(1_000, 50_000, 5);
        });
    }
    @Test
    public void shouldCreateCreditAccountIfInitialBalanceZero() {
        Assertions.assertDoesNotThrow(()->{
            new CreditAccount(0, 50_000, 5);
        });
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-1_000, 50_000, 5);
        });
    }

    @Test
    public void shouldThrowExceptionIfCreditLimitNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000, -50_000, 5);
        });
    }

    @Test
    public void shouldThrowExceptionIfRateNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(1_000, 50_000, -5);
        });
    }

    @Test
    public void shouldThrowExceptionIfCreditLimitZero() {

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new CreditAccount(1_000, 0, 5);
        });
    }

    @Test
    public void shouldThrowExceptionIfRateZero() {

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            new CreditAccount(1_000, 50_000, 0);
        });
    }

    @Test
    public void shouldPayIfAmountInBalance() {
        CreditAccount account = new CreditAccount(1_000, 50_000, 5);
        Assertions.assertTrue(account.pay(1_000));
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldPayIfAmountMoreThanBalanceAndLessThanLimit() {

        CreditAccount account = new CreditAccount(1_000, 50_000, 5);
        Assertions.assertTrue(account.pay(10_000));
        Assertions.assertEquals(-9_000, account.getBalance());
    }

    @Test
    public void shouldPayIfAmountEqualsBalancePlusCreditLimit() {

        CreditAccount account = new CreditAccount(1_000, 50_000, 5);
        Assertions.assertTrue(account.pay (51_000));
        Assertions.assertEquals(-50_000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfAmountMoreThanBalancePlusCreditLimit() {
        CreditAccount account = new CreditAccount(1_000, 50_000, 5);
        Assertions.assertFalse(account.pay(52_000));
    }
    @Test
    public void shouldNotPayIfAmountZero() {
        CreditAccount account2 = new CreditAccount(1_000, 50_000, 5);
        Assertions.assertFalse(account2.pay(0));
    }
    @Test
    public void shouldNotPayIfAmountNegative() {
        CreditAccount account3 = new CreditAccount(1_000, 50_000, 5);
        Assertions.assertFalse(account3.pay (-1_000));
    }

    @Test
    public void shouldAddToZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        Assertions.assertTrue(account.add(3_000));
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(0, 50_000, 15);

        Assertions.assertTrue(account.pay(5_000));
        Assertions.assertEquals(-5_000, account.getBalance());

        Assertions.assertTrue(account.add(3_000));
        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalanceToSetBalancePositive() {
        CreditAccount account = new CreditAccount(0, 50_000, 15);

        Assertions.assertTrue(account.pay(5_000));
        Assertions.assertEquals(-5_000, account.getBalance());

        Assertions.assertTrue(account.add(10_000));
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldNotAddToBalanceWhenAmountLessOrEqualsZero() {
        CreditAccount account = new CreditAccount(0, 50_000, 15);
        Assertions.assertFalse(account.add(0));
        Assertions.assertFalse(account.add(-1_000));
    }

    @Test
    public void shouldYearChangeReturnZeroWhenBalanceIsZero() {
        CreditAccount account = new CreditAccount(0, 50_000, 15);
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldYearChangeReturnZeroWhenBalancePositive() {
        CreditAccount account2 = new CreditAccount(50_000, 50_000, 15);
        Assertions.assertEquals(0, account2.yearChange());
    }

   @Test
    public void shouldYearChangeReturnNegativeInteger() {
        CreditAccount account = new CreditAccount(0, 50_000, 15);
        Assertions.assertTrue(account.pay(25_000));
        Assertions.assertEquals(-25_000, account.getBalance());

        int expected = account.getBalance() * account.rate / 100;
        int actual = account.yearChange();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 15);

        Assertions.assertTrue(account.add(3_000));
        Assertions.assertEquals(4_000, account.getBalance());
    }

}
