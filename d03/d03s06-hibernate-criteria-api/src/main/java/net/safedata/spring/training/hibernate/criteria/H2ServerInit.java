package net.safedata.spring.training.hibernate.criteria;

import org.h2.tools.Server;

import java.util.Optional;

class H2ServerInit {

    private static Server h2Server;

    static void initializeDatabaseServer() {
        try {
            if (!Optional.ofNullable(h2Server).isPresent()) {
                h2Server = Server.createTcpServer();
                Class.forName("org.h2.Driver");
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            System.exit(13);
        }
    }
}
