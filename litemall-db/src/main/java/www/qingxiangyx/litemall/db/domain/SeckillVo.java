package www.qingxiangyx.litemall.db.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class SeckillVo {

    private Long id;

    private LitemallGoods goods;

    private String name;

    private Integer inventory;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private Long version;
}