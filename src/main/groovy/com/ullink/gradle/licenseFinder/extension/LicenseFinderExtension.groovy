package com.ullink.gradle.licenseFinder.extension

import com.ullink.gradle.utils.StreamUtil
import com.ullink.gradle.entity.DependenciesDecisionEntity
import org.gradle.api.Project

class LicenseFinderExtension {
    String dependencyConfiguration
    ArrayList<String> checkLicensesScriptArgs
    String checkLicensesDescription
    File checkLicensesScript
    ArrayList<DependenciesDecisionEntity> decisions
    ArrayList<String> whiteList
    String licenseFinderVersion

    LicenseFinderExtension(Project project) {
        this.dependencyConfiguration = "runtime"
        this.checkLicensesScriptArgs = ["--project-path=${project.buildDir.parent.toString()}"]
        this.checkLicensesDescription = "check License for dependencies project"
        this.checkLicensesScript = StreamUtil.streamToFile(this.class.getResourceAsStream('/entrypoint.rb'))
        this.decisions = []
        this.whiteList = ["MIT", "AGPL-3.0", "GPL-3.0", "LGPL-3.0", "MPL-2.0", "APACHE-2.0", "UNLICENSE"]
        this.licenseFinderVersion = "license_finder:5.10.2"
    }

    def  DependencyConfiguration(String value) {
        this.dependencyConfiguration = value
    }

    def ScriptArgs(ArrayList<String> value) {
        this.checkLicensesScriptArgs = value
    }

    def CheckLicensesScript(File value) {
        this.checkLicensesScript = value
    }

    def CheckLicensesDescription(){
        return this.checkLicensesDescription
    }

    def Decisions(ArrayList<DependenciesDecisionEntity> value) {
        this.decisions = value
    }

    def Decisions() {
        return  this.decisions
    }

    def WhiteList(ArrayList<String> value){
        this.whiteList = value
    }

    def WhitheList(){
        return this.whiteList
    }
}
