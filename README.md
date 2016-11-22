Opposite to `one-module-with-unnamed-ok` from [First steps with Java 9 and Project Jigsaw – Part 2](https://blog.codecentric.de/en/2015/12/first-steps-with-java9-jigsaw-part-2/), this sample demonstrates `com.greeting` module that depends on lecacy `org.astro` code.

# Instruction

1. Download, unpack and add to PATH [jdk 9](https://jdk9.java.net/download/)
2. Create dirs: `mkdir -p mods/org.astro mods/com.greetings`
3. Compile unnamed module: `javac -d mods/org.astro src/org.astro/org/astro/World.java`
4. Compile module that uses unnamed module above: `javac --add-reads com.greetings=ALL-UNNAMED -d mods -cp mods/org.astro/ src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java`
5. Run `java --add-reads com.greetings=ALL-UNNAMED -cp mods/org.astro/ -p mods -m com.greetings/com.greetings.Main`
