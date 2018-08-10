package cc.ewell.login;

import android.app.Application;

import cc.ewell.componentbridgebase.ServiceFactory;
import cc.ewell.common.BaseApp;

public class LoginApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        initModuleApp(this);
        initModuleData(this);
    }

    @Override
    public void initModuleApp(Application application) {
        ServiceFactory.getInstance().setAccountService(new AccountService());
    }

    @Override
    public void initModuleData(Application application) {

    }
}
