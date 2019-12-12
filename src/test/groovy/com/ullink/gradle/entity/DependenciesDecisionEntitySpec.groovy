package com.ullink.gradle.entity

import spock.lang.Specification

class DependenciesDecisionEntitySpec extends Specification {
    def 'create Dependencies Decision entity'() {
        when:
        def e = new DependenciesDecisionEntity("gradle-ullink-plugin", "1.42", "ITIVITI", "", false)

        then:
        e.shortLicenseName == "ITIVITI"
    }
}
