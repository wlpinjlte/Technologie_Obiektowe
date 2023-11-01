package command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Account;
import model.Transaction;

import java.util.List;

public class RemoveTransactionsCommand implements Command{
    private final List<Transaction> transactionsToRemove;
    private final Account account;

    public RemoveTransactionsCommand(Account account,List<Transaction>transactionsToRemove) {
        this.account = account;
        this.transactionsToRemove=transactionsToRemove;
    }
    @Override
    public void execute() {
        for(Transaction transaction:transactionsToRemove){
            account.removeTransaction(transaction);
        }
    }

    @Override
    public String getName() {
        return "New transaction: " + transactionsToRemove.toString();
    }

    @Override
    public void undo() {
        for(Transaction transaction:transactionsToRemove){
            account.addTransaction(transaction);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
