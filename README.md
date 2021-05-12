# Pig Spawner Seed Finder

This java application will find seeds with a pig spawner if it is around the spawn area, radius can be changed.

**WARNING:** This will take some time to find a seed, if you want to just check a seed please use [this](https://github.com/hube12/PigSpawnerFinder).
Only checking one seed is not the purpose of this application.

## Run the application

Goto [the latest release](https://github.com/booky10/PigSpawnerSeedFinder/releases/latest) and download the file ending with `.jar`. Java version 8 to 16 are supported, you can download a Java 16 installer for windows [here](https://github.com/AdoptOpenJDK/openjdk16-binaries/releases/download/jdk-16.0.1%2B9/OpenJDK16U-jre_x64_windows_hotspot_16.0.1_9.msi) for windows, on linux (debian/ubuntu) run `sudo apt install openjdk-11-jre` and on macOS use [this](https://github.com/AdoptOpenJDK/openjdk16-binaries/releases/download/jdk-16.0.1%2B9/OpenJDK16U-jre_x64_mac_hotspot_16.0.1_9.pkg) if you don't already have java.

## Build it yourself

Please ensure that you have installed `git` and `java` on your system. If you want to do this step, you should know how to install/use them.
The output file should be in `build/libs` after the build finished.

### Linux/macOS

* `git clone https://github.com/booky10/PigSpawnerSeedFinder.git`
* `cd PigSpawnerSeedFinder`
* `./gradlew shadowJar`

### Windows

* `git clone https://github.com/booky10/PigSpawnerSeedFinder.git`
* `cd PigSpawnerSeedFinder`
* `gradlew shadowJar`
