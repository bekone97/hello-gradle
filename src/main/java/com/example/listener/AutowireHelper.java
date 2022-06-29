package com.example.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class AutowireHelper implements ApplicationContextAware {
    private static final AutowireHelper INSTANCE = new AutowireHelper();
    private static ApplicationContext applicationContext;
    private AutowireHelper(){}
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AutowireHelper.applicationContext=applicationContext;
    }

    public static AutowireHelper getInstance(){
        return INSTANCE;
    }

    public static void autowire(Object classToAutowire, Object... beansToAutowireInClass) {
        for (Object bean : beansToAutowireInClass) {
            if (bean == null) {
                applicationContext.getAutowireCapableBeanFactory().autowireBean(classToAutowire);
            }
        }
    }
}
