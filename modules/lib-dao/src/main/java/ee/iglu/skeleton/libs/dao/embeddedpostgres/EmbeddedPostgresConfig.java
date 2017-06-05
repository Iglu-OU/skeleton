package ee.iglu.skeleton.libs.dao.embeddedpostgres;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnProperty("lib.dao.enable-embedded-postgres")
class EmbeddedPostgresConfig {

    @Bean
    EmbeddedPostgresBean embeddedPostgresInitializer() {
        return new EmbeddedPostgresBean();
    }

    @Bean
    @Primary
    DataSourceProperties dataSourceProperties(EmbeddedPostgresBean embeddedPostgresBean){
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setUrl(embeddedPostgresBean.getUrl());
        return dataSourceProperties;
    }
}
