package atomic;

import java.util.concurrent.atomic.AtomicReference;

public class ConfigurationUpdater {
  private final AtomicReference<Configuration> configuration;

  public ConfigurationUpdater(Configuration configuration) {
    this.configuration = new AtomicReference<>(configuration);
  }

  public void updateConfiguration(Configuration newConfiguration) {
    Configuration oldConfiguration;
    do {
      oldConfiguration = configuration.get();
    } while (!configuration.compareAndSet(oldConfiguration, newConfiguration));
  }
}
