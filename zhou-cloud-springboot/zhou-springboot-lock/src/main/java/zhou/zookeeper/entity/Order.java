package zhou.zookeeper.entity;


import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "tb_order")
public class Order{



    private String id;


    private BigDecimal price;


    private Date createTime;

}
