package cc.ewell.login;

import cc.ewell.componentbridgebase.service.IAccountService;


public class AccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public String getAccountId() {
        return "1000";
    }
}
