/*
 * Copyright 2026 IQKV Foundation Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.multimoduleservice.servicename;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests using Spring Modulith's testing capabilities. These tests verify module integration and bootstrap behavior.
 */
@SpringBootTest
@ActiveProfiles("test")
class ModulithIntegrationTest {

  /**
   * Verifies that the application context loads successfully with all modules properly configured.
   */
  @Test
  void applicationContextLoads() {
    // This test ensures that all modules can be loaded together
    // and that there are no circular dependencies or configuration issues
  }

  /**
   * Base class for module-specific integration tests. Extend this class to create tests for individual modules.
   */
  @ApplicationModuleTest
  static class BaseModuleTest {

    @Test
    void moduleBootstrapsSuccessfully() {
      // This test verifies that individual modules can bootstrap independently
    }
  }
}
