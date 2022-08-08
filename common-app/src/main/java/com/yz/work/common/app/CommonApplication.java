package com.yz.work.common.app;

import com.yz.work.common.framework.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.yz.work.common"})
public class CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
		SpringContextUtils.getBeanByType(TestPa.class).te();
	}

}
