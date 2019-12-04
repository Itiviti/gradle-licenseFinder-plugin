package com.ullink.gradle.licenseFinder

import com.github.jrubygradle.JRubyExec
import com.ullink.gradle.licenseFinder.extension.LicenseFinderExtension
import com.ullink.gradle.licenseFinder.tasks.GemInstallTask
import com.ullink.gradle.licenseFinder.tasks.MakeDecisionTask
import com.ullink.gradle.licenseFinder.tasks.ProjectNameTask
import com.ullink.gradle.licenseFinder.tasks.RubyZipInstallTask
import com.ullink.gradle.licenseFinder.tasks.WhiteListTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import nl.javadude.gradle.plugins.license.LicensePlugin
import com.github.jrubygradle.JRubyPlugin

class LicenseFinderPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('licenseFinder', LicenseFinderExtension, project)
        project.apply plugin: LicensePlugin
        project.apply plugin: JRubyPlugin

        project.tasks.downloadLicenses.dependencyConfiguration extension.dependencyConfiguration

        def installRubyZip = project.tasks.create('installRubyZip', RubyZipInstallTask, project)
        def installLicenseFinder = project.tasks.create('installLicenseFinder', GemInstallTask, project)
        installLicenseFinder.dependsOn(installRubyZip)

        def checkLicensesTask = project.tasks.create('checkLicenses', JRubyExec)
        checkLicensesTask.description extension.checkLicensesDescription
        checkLicensesTask.script extension.checkLicensesScript
        checkLicensesTask.scriptArgs extension.checkLicensesScriptArgs

        def addProjectNameTask = project.tasks.create("addProjectName", ProjectNameTask, project)
        def makeDecisionFileTask = project.tasks.create("makeDecisionFile", MakeDecisionTask, project)
        def makeWhiteList = project.tasks.create("makeWhiteList", WhiteListTask, project)

        makeDecisionFileTask.dependsOn(installLicenseFinder)
        makeDecisionFileTask.dependsOn(makeWhiteList)
        makeDecisionFileTask.dependsOn(addProjectNameTask)
        checkLicensesTask.dependsOn(makeDecisionFileTask)
    }
}
