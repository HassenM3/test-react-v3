package com.hbz.react;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.hbz.react");

        noClasses()
            .that()
            .resideInAnyPackage("com.hbz.react.service..")
            .or()
            .resideInAnyPackage("com.hbz.react.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.hbz.react.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
