package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import com.ullink.gradle.utils.StreamUtil
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class ProjectNameTask extends DefaultTask {
    private File scriptEntrypoint
    private ArrayList<String> scriptArgs

    @Inject
    ProjectNameTask(Project project) {
        scriptEntrypoint = project.extensions.licenseFinder.checkLicensesScript
        scriptArgs = ["project_name", "add"]
    }

    @TaskAction
    exec() {
        def ex = new JRubyExec(script: this.scriptEntrypoint, scriptArgs: scriptArgs)
        ex.exec()
    }
}
