package eu.sig.training.ch04;

// tag::SavingsAccount[]
public class SavingsAccount {
    private static final float INTEREST_PERCENTAGE = 0.04f;
    private Money balance = new Money();
    private CheckingAccount registeredCounterAccount;

    public Transfer makeTransfer(String counterAccount, Money amount) 
        throws BusinessException {
        if (elevenTest(counterAccount)) {
            Transfer result = createTransfer(String counterAccount, Money amount)
            // 3. Check whether withdrawal is to registered counter account:
            if (result.getCounterAccount().equals(this.registeredCounterAccount)) 
            {
                return result;
            } else {
                throw new BusinessException("Counter-account not registered!");
            }
        } else {
            throw new BusinessException("Invalid account number!!");
        }
    }

    public void addInterest() {
        Money interest = balance.multiply(INTEREST_PERCENTAGE);
        if (interest.greaterThan(0)) {
            balance.add(interest);
        } else {
            balance.substract(interest);
        }
    }
    
    private boolean elevenTest(String counterAccount){
        // 1. Assuming result is 9-digit bank account number, validate 11-test:
        int sum = 0; // <1>
        for (int i = 0; i < counterAccount.length(); i++) {
            char character = counterAccount.charAt(i);
            int characterValue = Character.getNumericValue(character);
            sum = sum + (9 - i) * characterValue;
        }
        
        return (sum % 11 == 0)
    }
    
    private Transfer createTransfer(String counterAccount, Money amount){
        // 2. Look up counter account and make transfer object:
        CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
        return new Transfer(this, acct, amount); // <2>
    }
}
// end::SavingsAccount[]
