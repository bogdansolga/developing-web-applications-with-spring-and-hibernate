package net.safedata.spring.training.d02.s02.filewriting;

import net.safedata.spring.training.d02.s02.Profiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Primary
@Service
@Profile(Profiles.PROD)
public class ProdFileWriter implements FileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProdFileWriter.class);

    @Override
    public void writeFile() {
        LOGGER.info("[prod] Writing the file on an SFTP server...");
    }
}
