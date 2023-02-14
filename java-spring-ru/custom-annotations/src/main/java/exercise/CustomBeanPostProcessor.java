package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private Map<String, String> map = new HashMap<>();

    private Map<String, Object> map2 = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String level = bean.getClass().getAnnotation(Inspect.class).level();
            map.put(bean.getClass().getName(), level);
            map2.put(beanName, new CalculatorImpl());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            Logger logger = LoggerFactory.getLogger(bean.getClass());
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        String level = map.get(bean.getClass().getName());
                        if (level.equals("debug")) {
                            logger.debug(String.format("Was called method: %s() with arguments: %s",
                                    method.getName(), Arrays.toString(args)));
                        } else if (level.equals("info")) {
                            logger.info(String.format("Was called method: %s() with arguments: %s",
                                    method.getName(), Arrays.toString(args)));
                        } else {
                            throw new UnsupportedOperationException("Unsupported method: " + method.getName());
                        }
                        //return method.invoke(args);
                        return method.invoke(bean, args);
                    });
        }
        return bean;
    }
}
// END
