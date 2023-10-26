/*
 * ISC License
 *
 * Copyright (c) 2022. Wolf-Martell Montwé (bitfunk)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

package eu.upwolf.gradle.blueprint.publish

import eu.upwolf.gradle.blueprint.dependency.byEnv
import eu.upwolf.gradle.blueprint.dependency.byProperty
import org.gradle.api.Project

// TODO groupId needed?
object PublishConfig {
    const val description = "A template for Kotlin Android projects"
    const val year = "2021"

    // GitHub
    const val gitHubOwner = "wmontwe"
    const val gitHubRepository = "mobile-project-blueprint"

    // URL
    const val host = "github.com"
    const val path = "$gitHubOwner/$gitHubRepository"
    const val url = "https://$host/$path"

    // LICENSE
    const val licenseName = "Copyright"
    const val licenseUrl = "$url/blob/main/LICENSE"
    const val licenseDistribution = "repo"

    // DEVELOPER
    const val developerId = "wmontwe"
    const val developerName = "Wolf-Martell Montwé"

    // SCM
    const val scmConnection = "scm:git:git://$host/$path.git"
    const val scmDeveloperConnection = "scm:git:ssh://$host/$path.git"
    const val scmUrl = url

    // Issues
    const val issueSystem = "GitHub Issues"
    const val issueUrl = "$url/issues"

    // GitHub Maven Repository
    const val gitHubPackageRegistryName = "GitHubPackages"
    const val gitHubPackageRegistryUrl = "https://maven.pkg.github.com/$gitHubOwner/$gitHubRepository"

    private const val GITHUB_PACKAGE_UPLOAD_USER_PROPERTY_NAME = "githubPackageUpload.user"
    private const val GITHUB_PACKAGE_UPLOAD_PASSWORD_PROPERTY_NAME = "githubPackageUpload.password"

    private const val GITHUB_PACKAGE_UPLOAD_USER_ENV_NAME = "GITHUB_PACKAGE_UPLOAD_USER"
    private const val GITHUB_PACKAGE_UPLOAD_PASSWORD_ENV_NAME = "GITHUB_PACKAGE_UPLOAD_KEY"

    fun loadGitHubPackageUser(project: Project): String? {
        return GITHUB_PACKAGE_UPLOAD_USER_PROPERTY_NAME.byProperty(project)
            ?: GITHUB_PACKAGE_UPLOAD_USER_ENV_NAME.byEnv()
    }

    fun loadGitHubPackagePassword(project: Project): String? {
        return GITHUB_PACKAGE_UPLOAD_PASSWORD_PROPERTY_NAME.byProperty(project)
            ?: GITHUB_PACKAGE_UPLOAD_PASSWORD_ENV_NAME.byEnv()
    }
}
