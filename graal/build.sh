./gradlew shadowJar

# -Ob is quick build for development
native-image -jar ./app/build/libs/app-all.jar \
    -Ob \
    --strict-image-heap \
    --initialize-at-run-time=org.eclipse.jdt.internal.compiler.util.Messages \
    -Duser.country=US \
    -Duser.language=en \
    -H:+UnlockExperimentalVMOptions \
    -H:ReflectionConfigurationFiles=graal/reflect-config.json \
    -H:ResourceConfigurationFiles=graal/resource-config.json \
    -H:Log=registerResource:3