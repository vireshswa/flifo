/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.heroku;

import org.jscience.physics.amount.Amount;
import org.jscience.physics.model.RelativisticModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.measure.quantity.Mass;
import java.util.Map;

import static javax.measure.unit.SI.KILOGRAM;

@RestController
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class HerokuApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HerokuApplication.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/hello")
    String hello(Map<String, Object> model) {
        RelativisticModel.select();
        Amount<Mass> m = Amount.valueOf("12 GeV").to(KILOGRAM);
        model.put("science", "E=mc^2: 12 GeV = " + m.toString());
        return "hello";
    }

}
