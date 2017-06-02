package ee.iglu.tools.daogen;

import org.flywaydb.core.Flyway;
import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Jdbc;
import org.jooq.util.jaxb.Target;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import java.nio.file.Paths;

import static ru.yandex.qatools.embed.postgresql.EmbeddedPostgres.cachedRuntimeConfig;

public class Daogen {

    public static void main(String[] args) throws Exception {
        EmbeddedPostgres postgres = new EmbeddedPostgres();

        try {
            String url = postgres.start(cachedRuntimeConfig(Paths.get(args[2], ".gradle", "postgres")));
            migrate(url);
            generate(url, args[1], args[0]);
        } finally {
            postgres.stop();
        }
    }

    private static void generate(String url, String packageName, String directory) throws Exception {
        GenerationTool jooq = new GenerationTool();
        Configuration configuration = GenerationTool.load(Daogen.class.getResourceAsStream("/jooq.xml"));

        configuration.withJdbc(new Jdbc()
                .withUrl(url)
        );

        configuration.getGenerator().setTarget(new Target()
                .withPackageName(packageName)
                .withDirectory(directory)
        );

        jooq.run(configuration);
    }

    private static void migrate(String url) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, null, null);
        flyway.migrate();
    }

}
