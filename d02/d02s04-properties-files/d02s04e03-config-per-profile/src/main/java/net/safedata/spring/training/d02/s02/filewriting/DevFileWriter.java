package net.safedata.spring.training.d02.s02.filewriting;

import net.safedata.spring.training.d02.s02.Profiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(Profiles.DEV)
public class DevFileWriter implements FileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevFileWriter.class);

    @Override
    public void writeFile() {
        LOGGER.info("[dev] Writing the file on the filesystem...");
    }
}
