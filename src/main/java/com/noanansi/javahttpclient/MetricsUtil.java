package com.noanansi.javahttpclient;

import io.micrometer.cloudwatch2.CloudWatchConfig;
import io.micrometer.cloudwatch2.CloudWatchMeterRegistry;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;

public final class MetricsUtil {

  private static MeterRegistry registry;

  private MetricsUtil() {
  }

  public static MeterRegistry getInstance() {
    if (registry == null) {
      createRegistry();
    }
    return registry;
  }

  private static void createRegistry() {
    final var cloudWatchConfig = new CloudWatchConfig() {
      @Override
      public String get(String s) {
        return null;
      }

      @Override
      public String namespace() {
        return "lambda-test";
      }
    };
    registry =
        new CloudWatchMeterRegistry(cloudWatchConfig, Clock.SYSTEM, CloudWatchAsyncClient.create());
  }

}
