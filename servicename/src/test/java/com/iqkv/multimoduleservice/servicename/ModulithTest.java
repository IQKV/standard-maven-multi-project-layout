/*
 * Copyright 2025 IQKV Foundation Team.
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
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.core.DependencyDepth;
import org.springframework.modulith.docs.Documenter;

/**
 * Comprehensive Spring Modulith tests for the entire application. These tests verify the modular structure and generate documentation.
 */
class ModulithTest {

  private final ApplicationModules modules = ApplicationModules.of(ServicenameApplication.class);

  /**
   * Verifies that the application modules are properly structured and follow Spring Modulith conventions.
   */
  @Test
  void verifiesModularStructure() {
    modules.verify();
  }

  /**
   * Generates comprehensive documentation for all application modules. This includes: - Module structure diagrams - Component diagrams - Module dependencies - API documentation
   */
  @Test
  void generateModuleDocumentation() {
    new Documenter(modules)
        .writeDocumentation()
        .writeModulesAsPlantUml()
        .writeIndividualModulesAsPlantUml();
  }

  /**
   * Prints detailed information about discovered modules for debugging and verification purposes.
   */
  @Test
  void printModuleInformation() {
    System.out.println("=== Application Modules ===");
    modules.forEach(module -> {
      System.out.println("Module: " + module.getDisplayName());
      System.out.println("  Display Name: " + module.getDisplayName());
      System.out.println("  Base Package: " + module.getBasePackage());
      System.out.println("  Dependencies: " + module.getDependencies(modules, DependencyDepth.ALL));
      System.out.println("  Spring Beans: " + module.getSpringBeans().size());
      System.out.println("---");
    });
  }
}
