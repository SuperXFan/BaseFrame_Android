package cc.ewell.componentbridgebase.defaultservice;


import cc.ewell.componentbridgebase.service.IAccountService;

public class DefaultAccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
