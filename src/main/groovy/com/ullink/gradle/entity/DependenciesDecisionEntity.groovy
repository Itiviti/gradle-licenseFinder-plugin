package com.ullink.gradle.entity

class DependenciesDecisionEntity {
    String projectName
    String version
    String shortLicenseName
    String homepage
    boolean approve

    DependenciesDecisionEntity(String projectName, String version, String shortLicenseName, String homepage, boolean approve) {
        this.projectName = projectName
        this.version = version
        this.shortLicenseName = shortLicenseName
        this.homepage = homepage
        this.approve = approve
    }

    String getProjectName() {
        return projectName
    }

    void setProjectName(String projectName) {
        this.projectName = projectName
    }

    String getVersion() {
        return version
    }

    void setVersion(String version) {
        this.version = version
    }

    String getShortLicenseName() {
        return shortLicenseName
    }

    void setShortLicenseName(String shortLicenseName) {
        this.shortLicenseName = shortLicenseName
    }

    String getHomepage() {
        return homepage
    }

    void setHomepage(String homepage) {
        this.homepage = homepage
    }

    boolean getApprove() {
        return approve
    }

    void setApprove(boolean approve) {
        this.approove = approve
    }
}
