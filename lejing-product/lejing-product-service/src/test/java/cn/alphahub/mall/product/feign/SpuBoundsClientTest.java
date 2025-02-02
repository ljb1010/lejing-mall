package cn.alphahub.mall.product.feign;

import cn.alphahub.common.core.controller.BaseController;
import cn.alphahub.common.core.domain.BaseResult;
import cn.alphahub.common.core.page.PageResult;
import cn.alphahub.mall.coupon.domain.SpuBounds;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@SpringBootTest
class SpuBoundsClientTest extends BaseController {

    @Mock
    @Resource
    private SpuBoundsClient spuBoundsClient;

    @BeforeEach
    void setUp() {
        System.out.println("--------------------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("--------------------");
    }

    @Test
    void testSave() {
        SpuBounds spuBounds = new SpuBounds();
        spuBounds.setSpuId(10068L);
        spuBounds.setGrowBounds(new BigDecimal("100"));
        spuBounds.setBuyBounds(new BigDecimal("150"));
        spuBounds.setWork(1);
        BaseResult<Boolean> save = spuBoundsClient.save(spuBounds);
        System.out.println(save);
    }

    @Test
    void testInfo() {
        BaseResult<SpuBounds> info = spuBoundsClient.info(1L);
        System.out.println(info.getCode());
        System.out.println(info.getMessage());
        System.out.println(info.getData());
    }
}
