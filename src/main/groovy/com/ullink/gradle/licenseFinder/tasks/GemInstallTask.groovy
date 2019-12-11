package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import org.gradle.api.Project

import javax.inject.Inject

class GemInstallTask extends JRubyExec {
    private final String licenseFinder = "license_finder"
    private final String licenseFinderVersion = "5.10.2"
    private ArrayList<String> params = []
    @Inject
    GemInstallTask(Project project) {
        super()
        this.setProxy()
        this.script "gem"
        this.scriptArgs "install", params.join(' ') ,licenseFinder, "-v ${licenseFinderVersion}"
    }

    void setProxy() {
        String hostProperty = "proxyHost"
        String portProperty = "proxyPort"
        if (project.properties.find { it.key.contains(hostProperty)}  && project.properties.find {it.key.contains(portProperty)} ){
            String keyPorpertyHost = project.properties.find { it.key.contains(hostProperty)}.key
            String keyPropertyPort = project.properties.find {it.key.contains(portProperty)}.key
            params.add("--http-proxy=http://${project.findProperty(keyPorpertyHost)}:${project.findProperty(keyPropertyPort)}")
        }
    }
}
