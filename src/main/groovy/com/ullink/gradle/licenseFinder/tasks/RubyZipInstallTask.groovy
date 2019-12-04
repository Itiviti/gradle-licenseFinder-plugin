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
        super()
        this.setProxy("http")
        this.script "gem"
        this.scriptArgs "install", params.join(' ') ,RubyZip, "-v ${RubyZipVersion}"
    }

    void setProxy(String protocol) {
        String hostProperty = "systemProp.${protocol}.proxyHost"
        String portProperty = "systemProp.${protocol}.proxyPort"
        if (project.property(hostProperty) && project.property(portProperty))
            params.add("--http-proxy=${protocol}://${project.property(hostProperty)}:${project.property(portProperty)}")
    }
}
