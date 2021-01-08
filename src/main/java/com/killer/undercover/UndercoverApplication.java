package com.killer.undercover;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huanghuiqiang
 */
@MapperScan("com.killer.undercover.mapper")
@SpringBootApplication
public class UndercoverApplication {

  public static void main(String[] args) {
    SpringApplication.run(UndercoverApplication.class, args);
  }

}
