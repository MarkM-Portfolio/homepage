package com.ibm.lconn.homepage.services.utils;

public class CnxNewUIEnabledThreadLocal {
    

    public static ThreadLocal<Boolean> cnxNewUiEnabledThreadLocal = new ThreadLocal<Boolean>();

    public static ThreadLocal<Boolean> getThreadLocal() {
        if (cnxNewUiEnabledThreadLocal == null) {
            cnxNewUiEnabledThreadLocal = new ThreadLocal<Boolean>();
        }
        return cnxNewUiEnabledThreadLocal;
    }

    public static void unset() {
        getThreadLocal().remove();
    }
    
    public static void setEnabled(boolean isCnxNewUiEnabled) {
        getThreadLocal().set(isCnxNewUiEnabled);
    }

    public static boolean isEnabled() {
        return getThreadLocal().get();
    }
}
