package HibernateUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BackgroundJobManager implements ServletContextListener {
    
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Khởi tạo luồng chạy ngầm khi Server Tomcat Start
        scheduler = Executors.newSingleThreadScheduledExecutor();
        
        // Cấu hình: Chạy lần đầu tiên sau 0 phút, sau đó cứ 1 GIỜ sẽ lặp lại 1 lần
        scheduler.scheduleAtFixedRate(new OverdueCheckTask(), 0, 1, TimeUnit.HOURS);
        
        System.out.println("[Scheduler] Hệ thống kiểm tra quá hạn đã được khởi động!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Dừng an toàn các luồng chạy ngầm khi Tắt Server (Stop Tomcat)
        if (scheduler != null) {
            scheduler.shutdownNow();
            System.out.println("[Scheduler] Đã tắt hệ thống kiểm tra quá hạn.");
        }
    }
}
