load("@build_stack_rules_proto//rules:proto_compile.bzl", "proto_compile")
load("@build_stack_rules_proto//rules/java:grpc_java_library.bzl", "grpc_java_library")
load("@build_stack_rules_proto//rules/java:proto_java_library.bzl", "proto_java_library")
load("@build_stack_rules_proto//rules:proto_compile.bzl", "proto_compile")

proto_compile(
    name = "google_api_java_compile",
    outs = {
        "@build_stack_rules_proto//plugin/builtin:java": "google_api.srcjar",
    },
    outputs = [
        "google_api.srcjar",
    ],
    plugins = [
        "@build_stack_rules_proto//plugin/builtin:java",
    ],
    proto = "@go_googleapis//google/api:annotations_proto",
)

proto_java_library(
    name = "google_api_java_library",
    srcs = ["google_api.srcjar"],
    visibility = ["//visibility:public"],
    exports = [
        "@maven//:com_google_protobuf_protobuf_java",
    ],
    deps = [
        "@maven//:com_google_api_grpc_proto_google_common_protos",
        "@maven//:com_google_protobuf_protobuf_java",
    ],
)
