package com.rates.currency.api.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Profile("!test")
@Configuration
public class SwaggerConfig {
    @Autowired
    private TypeResolver typeResolver;

		@Bean
		public Docket systemSettingsAPI() {
			return new Docket(DocumentationType.SWAGGER_2).select()
					.apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex("/model/settings/.*"))
					.build().pathMapping("/").groupName("System settings")
					.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
					.alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)), typeResolver.resolve(WildcardType.class)))
					.useDefaultResponseMessages(false)
					.globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error")).build()))
					.apiInfo(apiInfo());

		}

    @Bean
    public Docket currencyAPI() {
	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.regex("/api/.*")).build().pathMapping("/").groupName("Currency API")
		.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
		.useDefaultResponseMessages(false)
		.apiInfo(apiInfo());
	}


    private ApiInfo apiInfo() {
	@SuppressWarnings("deprecation")
	ApiInfo apiInfo = new ApiInfo("Currency API", "Curency API use external API. Integration", "0.1", "https://github.com/KamilAdd-Byte/currency-module",
		"KamilAdd-Byte",
		"MET 2.0 LICENCE",
		"https://github.com/KamilAdd-Byte/currency-module");
	return apiInfo;
	}
	}
