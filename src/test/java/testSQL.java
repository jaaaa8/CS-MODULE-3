import entity.Account;
import repository.AccountRepository;

public class testSQL {
    public static void main(String[] args) {
        AccountRepository a = new AccountRepository();
        Account ab = new Account("usertest","passtest");
        a.addNewAccount(ab);
    }
}
