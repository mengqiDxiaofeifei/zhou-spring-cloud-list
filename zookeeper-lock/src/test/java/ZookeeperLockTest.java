import com.zhou.zookeeper.ZookeeperLockApp;
import com.zhou.zookeeper.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZookeeperLockApp.class)
public class ZookeeperLockTest {


    @Resource
    private OrderService orderService;



}
