package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class WhiteListTask extends DefaultTask {
    private ArrayList<String> whiteList
    private File entrypointFile

    @Inject
    WhiteListTask(Project project) {
        if (project.extensions.licenseFinder.whiteList.size() > 0)
            this.whiteList = project.extensions.licenseFinder.whiteList
        else
            this.whiteList = []

        this.entrypointFile = project.extensions.licenseFinder.checkLicensesScript
    }

    @TaskAction
    exec() {
        this.whiteList.forEach { it ->
            def args = ["whitelist", "add", it]
            def ex = new JRubyExec(script: this.entrypointFile, scriptArgs: args)
            ex.exec()
        }
    }
}
