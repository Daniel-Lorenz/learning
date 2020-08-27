package customer.springjdbctests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Sergey Tselovalnikov
 * @since 13.06.2014
 */
@Component
public class SpringContext {

    @Autowired
    private static ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        SpringContext.context = context;
    }

    public static void autowireBean(Object bean) {
        AutowireCapableBeanFactory factory = context.getAutowireCapableBeanFactory();
        factory.autowireBean(bean);
    }
}