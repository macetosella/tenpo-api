package com.tenpo;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class TenpoApiApplicationTest extends TenpoApiApplication {

    @Test
    public void contextLoads() {
    }

    @Test
    public void tenpoApiApplication() {
        TenpoApiApplication.main(new String[]{});
    }
}