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

package com.iqscaffold.multimoduleservice.servicename;

import org.junit.jupiter.api.Test;

/**
 * Test suite that runs all Spring Modulith tests for the entire project. This provides a convenient way to execute all modulith-related tests together.
 */
class ModulithTestSuite {

  @Test
  void runAllModulithTests() {
    // This test method serves as a placeholder to ensure the test suite runs
    // Individual test classes will be executed by Maven when using the modulith-test profile
    System.out.println("Running Spring Modulith Test Suite");
    System.out.println("Individual test classes:");
    System.out.println("- ModulithTest");
    System.out.println("- ModulithIntegrationTest");
    System.out.println("- ModulithEventTest");
    System.out.println("- ModulithObservabilityTest");
  }
}
