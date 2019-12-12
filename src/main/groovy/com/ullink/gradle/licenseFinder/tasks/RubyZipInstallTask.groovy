package com.ullink.gradle.licenseFinder.tasks

import com.github.jrubygradle.JRubyExec
import org.gradle.api.Project

import javax.inject.Inject

class RubyZipInstallTask extends JRubyExec {
    private final String RubyZip = "rubyzip"
    private final String RubyZipVersion = "1.3.0"
    private ArrayList<String> params = []

    @Inject
    RubyZipInstallTask(Project project) {
        this.setProxy()
        this.script "gem"
        this.scriptArgs "install", params.join(' '), RubyZip, "-v ${RubyZipVersion}"
    }

    void setProxy() {
        String hostProperty = "proxyHost"
        String portProperty = "proxyPort"
        if (project.properties.find { it.key.contains(hostProperty) } && project.properties.find {
            it.key.contains(portProperty)
        }) {
            String keyPorpertyHost = project.properties.find { it.key.contains(hostProperty) }.key
            String keyPropertyPort = project.properties.find { it.key.contains(portProperty) }.key
            params.add("--http-proxy=http://${project.findProperty(keyPorpertyHost)}:${project.findProperty(keyPropertyPort)}")
        }
    }
}
