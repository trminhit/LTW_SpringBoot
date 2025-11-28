package ltwebct4.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SitemeshConfig {
    @Bean
    public FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter() {
        FilterRegistrationBean<ConfigurableSiteMeshFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new ConfigurableSiteMeshFilter() {
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
            	builder.addDecoratorPath("/*", "main.jsp");

                // Loại trừ các file resource để không bị lỗi giao diện
                builder.addExcludedPath("/css/*").addExcludedPath("/js/*").addExcludedPath("/images/*");
            }
        });
        filter.addUrlPatterns("/*");
        return filter;
    }
}