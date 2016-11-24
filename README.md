Opposite to `one-module-with-unnamed-ok` from [First steps with Java 9 and Project Jigsaw – Part 2](https://blog.codecentric.de/en/2015/12/first-steps-with-java9-jigsaw-part-2/), this sample demonstrates `com.greeting` module that depends on lecacy `org.astro` code.

# Instruction

1. Download, unpack and add to PATH [jdk 9](https://jdk9.java.net/download/)
2. Create dirs: `mkdir -p mods/org.astro mods/com.greetings`
3. Compile unnamed module: `javac -d mods/org.astro src/org.astro/org/astro/World.java`
4. Compile module that uses unnamed module above: `javac --add-reads com.greetings=ALL-UNNAMED -d mods -cp mods/org.astro/ src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java`
5. Run `java --add-reads com.greetings=ALL-UNNAMED -cp mods/org.astro/ -p mods -m com.greetings/com.greetings.Main`

# Problem scenario
6. Compile java.xml.bind module-patch that uses unnamed module above: `javac -cp mods/org.astro -d mods/java.xml.bind  src/java.xml.bind/javax/xml/bind/Main.java`. Note, does not need `--add-reads`, because we're not compiling a module.
7. Run Main as java.xml.bind module-patch: `java --add-modules java.xml.bind --patch-module java.xml.bind=mods/java.xml.bind/ -cp mods/org.astro javax.xml.bind.Main`

    Expected:

        IllegalAccessError: class javax.xml.bind.Main (in module java.xml.bind) cannot access class org.astro.World (in unnamed module @0x6a41eaa2) because module java.xml.bind does not read unnamed module @0x6a41eaa2

    Actual:

        NoClassDefFoundError: org/astro/World
	        at java.xml.bind@9-ea/javax.xml.bind.Main.main(Main.java:8)
        Caused by: java.lang.ClassNotFoundException: org.astro.World
            at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:367)
            at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:477)
            ... 1 more

8. Run Main as java.xml.bind module-patch with `--add-reads java.base=ALL-UNNAMED` and `-add-reads java.xml.bind=ALL-UNNAMED`, error is the same: `NoClassDefFoundError: org/astro/World`