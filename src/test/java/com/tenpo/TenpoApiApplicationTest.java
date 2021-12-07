package com.tenpo;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class TenpoApiApplicationTests extends TenpoApiApplication {

    @Test
    public void contextLoads() {
    }

    @Test
    public void mutantsApplication() {
        TenpoApiApplication.main(new String[]{});
    }
}