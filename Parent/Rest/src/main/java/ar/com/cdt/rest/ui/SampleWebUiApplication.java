/*
 * Copyright 2012-2013 the original author or authors.
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

package ar.com.cdt.rest.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import ar.com.cdt.socios.Socio;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleWebUiApplication {

	@Bean
	public SocioRepository socioRepository() {
		return new InMemorySocioRespository();
	}

	@Bean
	public Converter<String, Socio> socioConverter() {
		return new Converter<String, Socio>() {
			@Override
			public Socio convert(String id) {
				return socioRepository().findSocio(Long.valueOf(id));
			}
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);
	}

}
