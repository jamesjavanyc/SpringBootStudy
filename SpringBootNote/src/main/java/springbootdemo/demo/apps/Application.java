package springbootdemo.demo.apps;

import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import springbootdemo.demo.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springbootdemo.demo.componentfilter.MyComponentTypeFilter;

@EnableScheduling
@SpringBootApplication //springboot的启动类， 这个项目的代码需要放在Application同级目录或者同级目录下的目录
@EnableAutoConfiguration
@ComponentScan("springbootdemo.demo") //主程序类所在的包
/*@ComponentScan(value = "springbootdemo.demo",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class}),
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyComponentTypeFilter.class}) //自定义组件filter
},useDefaultFilters = false)  //扫描注解的filter 有include(需要useDefaultFilters = false)和exclude，
//classes数组不仅可以包含组件注解,type = FilterType.ASSIGNABLE_TYPE，还可以包含自己定义的组件类.*/
public class Application {
    public static void main(String[] args) {
        //返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

    }

    public static void getBeansDemo(ConfigurableApplicationContext run){
        //根据系统 conditional添加组件
        String os = run.getEnvironment().getProperty("os.name");

        //查看容器内的组件
        String[] names=run.getBeanDefinitionNames();
        for (String str:names) {
            System.out.println(str);
        }
        //从容器中获取组件
        // MyConfig bean = run.getBean(MyConfig.class);
        User james = run.getBean("James",User.class);
        System.out.println(james);
    }
}