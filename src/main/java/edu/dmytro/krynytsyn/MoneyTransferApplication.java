package edu.dmytro.krynytsyn;

import edu.dmytro.krynytsyn.model.InMemoryAccountEngine;
import edu.dmytro.krynytsyn.resources.MoneyTransferResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }

    @Override
    public String getName() {
        return "money-transfer";
    }

    @Override
    public void initialize(final Bootstrap<MoneyTransferConfiguration> bootstrap) {
    }

    @Override
    public void run(final MoneyTransferConfiguration configuration,
                    final Environment environment) {
        final MoneyTransferResource resource = new MoneyTransferResource(new InMemoryAccountEngine());
        environment.jersey().register(resource);
    }
}
