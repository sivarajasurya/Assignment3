
import core.*;
import core.chartofaccounts.ChartOfAccounts;
import core.chartofaccounts.ChartOfAccountsBuilder;
import core.transaction.AccountingTransaction;
import java.math.BigDecimal;


import static core.account.AccountSide.CREDIT;
import static core.account.AccountSide.DEBIT;

public class Assignment3 {
    public static void main(String[] args) {

        String cashAccountNumber = "000002";
        String checkingAccountNumber = "000003";
        String accountsReceivableAccountNumber = "000004";

        // Setup ledger
        ChartOfAccounts coa = ChartOfAccountsBuilder.create()
                .addAccount(cashAccountNumber, "Cash", CREDIT)
                .addAccount(checkingAccountNumber, "Checking", CREDIT)
                .addAccount(accountsReceivableAccountNumber, "Accounts Receivable", DEBIT)
                .build();
        Ledger ledger = new Ledger(coa);

        // Accounts Receivable 35 was settled with cash 10 and wire transfer 25
        AccountingTransaction t = ledger.createTransaction(null)
                .debit(new BigDecimal(100), cashAccountNumber)
                .debit(new BigDecimal(250), checkingAccountNumber)
                .credit(new BigDecimal(350), accountsReceivableAccountNumber)
                .build();
        ledger.commitTransaction(t);

        // Print ledger
        System.out.println(ledger.toString());

        // Print trial balance
        TrialBalanceResult trialBalanceResult = ledger.computeTrialBalance();
        System.out.println(trialBalanceResult.toString());
    }
}
