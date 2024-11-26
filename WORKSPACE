workspace(name = "example")

load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_jar")

COM_GOOGLE_PROTOBUF_VERSION = "25.1"

http_archive(
    name = "com_google_protobuf",
    #    sha256 = "8ff511a64fc46ee792d3fe49a5a1bcad6f7dc50dfbba5a28b0e5b979c17f9871",
    strip_prefix = "protobuf-" + COM_GOOGLE_PROTOBUF_VERSION,
    urls = [
        "https://github.com/protocolbuffers/protobuf/releases/download/v" + COM_GOOGLE_PROTOBUF_VERSION + "/protobuf-" + COM_GOOGLE_PROTOBUF_VERSION + ".tar.gz",
    ],
)

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()
################[ JVM external Utils ]################

RULES_JVM_EXTERNAL_TAG = "6.2"

RULES_JVM_EXTERNAL_SHA = "808cb5c30b5f70d12a2a745a29edc46728fd35fa195c1762a596b63ae9cebe05"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/%s/rules_jvm_external-%s.tar.gz" % (RULES_JVM_EXTERNAL_TAG, RULES_JVM_EXTERNAL_TAG),
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

PROTOVALIDATE_TAG = "0.1.4"

################[ go_rules (used for building TiCDC) ]################

http_archive(
    name = "go_googleapis",
    sha256 = "9d1a930e767c93c825398b8f8692eca3fe353b9aaadedfbcf1fca2282c85df88",
    strip_prefix = "googleapis-64926d52febbf298cb82a8f472ade4a3969ba922",
    urls = [
        "https://github.com/googleapis/googleapis/archive/64926d52febbf298cb82a8f472ade4a3969ba922.zip",
    ],
)

load("@go_googleapis//:repository_rules.bzl", "switched_rules_by_language")

switched_rules_by_language(
    name = "com_google_googleapis_imports",
)

###################### BUILD STACK #################################

http_archive(
    name = "build_stack_rules_proto",
    sha256 = "b7cbaf457d91e1d3c295df53b80f24e1d6da71c94ee61c42277ab938db6d1c68",
    strip_prefix = "rules_proto-3.2.0",
    url = "https://github.com/stackb/rules_proto/archive/refs/tags/v3.2.0.tar.gz",
)

register_toolchains("@build_stack_rules_proto//toolchain:standard")

load("@build_stack_rules_proto//deps:grpc_java_deps.bzl", "grpc_java_deps")

grpc_java_deps()

################[ jvm_rules ]################
git_repository(
    name = "contrib_rules_jvm",
    commit = "74a79f56e8a6d968381c3cbee5ed7740e33683a7",
    remote = "https://github.com/bazel-contrib/rules_jvm.git",
    shallow_since = "1700058052 +0000",
)

load("@contrib_rules_jvm//:repositories.bzl", "contrib_rules_jvm_deps")

contrib_rules_jvm_deps()

load("@contrib_rules_jvm//:setup.bzl", "contrib_rules_jvm_setup")

contrib_rules_jvm_setup()

################[ Unused_deps tool ]################

GRPC_VERSION = "1.65.1"

ARMERIA_VERSION = "1.30.0"

NETTY_VERSION = "4.1.112.Final"

maven_install(
    artifacts = [
        "com.google.guava:guava:33.0.0-jre",
        "com.google.protobuf:protobuf-java:3.25.1",
        "com.google.api.grpc:proto-google-common-protos:2.30.0",
        maven.artifact(
            artifact = "grpc-core",
            exclusions = [
                "io.grpc:grpc-util",  #  https://github.com/grpc/grpc-java/issues/10576#issuecomment-1741257443
            ],
            group = "io.grpc",
            version = GRPC_VERSION,
        ),
        "io.grpc:grpc-api:" + GRPC_VERSION,
        "io.grpc:grpc-context:" + GRPC_VERSION,
        "io.grpc:grpc-inprocess:" + GRPC_VERSION,
        "io.grpc:grpc-util:" + GRPC_VERSION,
        "io.grpc:grpc-protobuf:" + GRPC_VERSION,
        "io.grpc:grpc-services:" + GRPC_VERSION,
        "io.grpc:grpc-stub:" + GRPC_VERSION,

        #webapp: armeria & grpc
        "com.linecorp.armeria:armeria:" + ARMERIA_VERSION,
        "com.linecorp.armeria:armeria-grpc:" + ARMERIA_VERSION,
        "com.linecorp.armeria:armeria-grpc-protocol:" + ARMERIA_VERSION,
        "com.linecorp.armeria:armeria-protobuf:" + ARMERIA_VERSION,
        "io.netty:netty-common:" + NETTY_VERSION,
        "io.netty:netty-codec:" + NETTY_VERSION,
        "io.netty:netty-codec-http:" + NETTY_VERSION,
        "io.netty:netty-buffer:" + NETTY_VERSION,
        "io.netty:netty-transport:" + NETTY_VERSION,
        "io.netty:netty-handler:" + NETTY_VERSION,
        "com.google.errorprone:error_prone_annotations:2.24.1",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.9.0",
        "com.google.j2objc:j2objc-annotations:2.8",
        "org.apache.tomcat:annotations-api:6.0.53",
    ],
    fail_if_repin_required = True,
    fetch_sources = True,
    maven_install_json = "@//:maven_install.json",
    repositories = [
        "https://maven-central-eu.storage-download.googleapis.com/maven2",
        "https://repo.maven.apache.org/maven2",
        "https://maven.google.com",
        "https://repo1.maven.org/maven2",
    ],
    strict_visibility = True,
)

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()
