package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GemInstallTask extends DefaultTask {
    private final String licenseFinder = "license_finder:5.10.2"

    @TaskAction
    def exec() {
        def ex = new JRubyExec(script: "gem", scriptArgs: ["install", licenseFinder])
        ex.exec()
    }
}
